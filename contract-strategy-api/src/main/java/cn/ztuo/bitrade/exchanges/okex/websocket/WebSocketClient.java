package cn.ztuo.bitrade.exchanges.okex.websocket;

import java.net.*;

import io.netty.handler.ssl.util.*;
import io.netty.channel.nio.*;
import io.netty.handler.codec.http.*;
import io.netty.bootstrap.*;
import io.netty.channel.socket.nio.*;
import io.netty.handler.ssl.*;
import io.netty.channel.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.codec.binary.*;
import com.alibaba.fastjson.*;

import java.nio.charset.*;

import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.*;

import java.util.concurrent.*;

import com.google.common.hash.*;

public class WebSocketClient implements WebSocket {
    private static HashFunction crc32;
    private Channel ch;
    private WebSocketListener listener;
    private String URL;
    private Timer timer;

    public WebSocketClient(WebSocketListener listener) {
        this.URL = "wss://okexcomreal.bafang.com:8443/ws/v3";
        this.timer = (Timer) new HashedWheelTimer(Executors.defaultThreadFactory());
        this.listener = listener;
    }

    public WebSocketClient(String url, WebSocketListener listener) {
        this.URL = "wss://okexcomreal.bafang.com:8443/ws/v3";
        this.timer = (Timer) new HashedWheelTimer(Executors.defaultThreadFactory());
        this.URL = url;
        this.listener = listener;
    }

    private void init() {
        try {
            URI uri = new URI(this.URL);
            String scheme = (uri.getScheme() == null) ? "ws" : uri.getScheme();
            String host = (uri.getHost() == null) ? "127.0.0.1" : uri.getHost();
            int port = uri.getPort();
            if (!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
                this.listener.handleCallbackError(this, new RuntimeException("Only WS(S) is supported."));
                return;
            }
            boolean ssl = "wss".equalsIgnoreCase(scheme);
            SslContext sslCtx;
            if (ssl) {
                sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            } else {
                sslCtx = null;
            }
            EventLoopGroup group = new NioEventLoopGroup();
            WebSocketClientHandshaker shaker = WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, (String) null, true, (HttpHeaders) new DefaultHttpHeaders());
            WebSocketClientHandler handler = new WebSocketClientHandler(this, shaker, this.listener);
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) {
                    ChannelPipeline pipeline = channel.pipeline();
                    if (sslCtx != null) {
                        pipeline.addLast(sslCtx.newHandler(channel.alloc(), host, port));
                    }
                    pipeline.addLast(new HttpClientCodec(), new HttpObjectAggregator(8192), handler);
                }
            });
            this.ch = b.connect(uri.getHost(), port).sync().channel();
            handler.handshakeFuture().sync();
        } catch (Exception e) {
            this.listener.handleCallbackError(this, e);
        }
    }

    @Override
    public void close() {
        this.ch.close();
    }

    @Override
    public void connect() {
        this.init();
    }

    @Override
    public void login(String apiKey, String apiSecret, String passphrase) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000L);
        String str = timeStamp + "GET/users/self/verify";
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            hash = Base64.encodeBase64String(sha256_HMAC.doFinal(str.getBytes()));
        } catch (Throwable t) {
            t.printStackTrace();
            this.listener.handleCallbackError((WebSocket) this, t);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{\"args\":[\"" + apiKey + "\",\"" + passphrase + "\",\"" + timeStamp + "\",\"" + hash + "\"],\"op\":\"login\"}");
        this.send(builder.toString());
    }

    @Override
    public void subscribe(String... args) {
        String channels = this.formatArgs(args);
        this.send("{\"args\":[\"" + channels + "\"],\"op\":\"subscribe\"}");
    }

    @Override
    public void unSubscribe(String... args) {
        String channels = this.formatArgs(args);
        this.send("{\"args\":[\"" + channels + "\"],\"op\":\"unsubscribe\"}");
    }

    @Override
    public void sendPing() {
        this.send("ping");
    }

    @Override
    public boolean checkSum(String data) {
        try {
            JSONObject obj = (JSONObject) JSON.parse(data);
            JSONArray bidsArray = null;
            JSONArray asksArray = null;
            int checksum = 0;
            if (obj.get((Object) "table") != null && obj.get((Object) "action") != null) {
                JSONArray dataObj = (JSONArray) obj.get((Object) "data");
                JSONObject targetObj = (JSONObject) dataObj.get(0);
                bidsArray = (JSONArray) targetObj.get((Object) "bids");
                asksArray = (JSONArray) targetObj.get((Object) "asks");
                checksum = (int) targetObj.get((Object) "checksum");
            } else {
                bidsArray = (JSONArray) obj.get((Object) "bids");
                asksArray = (JSONArray) obj.get((Object) "asks");
                checksum = (int) obj.get((Object) "checksum");
            }
            StringBuilder targetStr = new StringBuilder();
            for (int index = 0; index < 25; ++index) {
                if (bidsArray != null && index < bidsArray.size()) {
                    JSONArray bidsObject = (JSONArray) bidsArray.get(index);
                    for (int num = 0; num < 2; ++num) {
                        targetStr.append(bidsObject.get(num));
                        targetStr.append(":");
                    }
                }
                if (asksArray != null && index < asksArray.size()) {
                    JSONArray asksObject = (JSONArray) asksArray.get(index);
                    for (int num = 0; num < 2; ++num) {
                        targetStr.append(asksObject.get(num));
                        targetStr.append(":");
                    }
                }
            }
            String strs;
            if (targetStr.length() > 0) {
                strs = targetStr.substring(0, targetStr.length() - 1);
            } else {
                strs = "";
            }
            int fianlStr = WebSocketClient.crc32.hashString((CharSequence) strs, StandardCharsets.UTF_8).asInt();
            return fianlStr == checksum;
        } catch (Exception e) {
            this.listener.handleCallbackError((WebSocket) this, (Throwable) e);
            return false;
        }
    }

    private void send(String msg) {
        WebSocketFrame frame = (WebSocketFrame) new TextWebSocketFrame(msg);
        this.ch.writeAndFlush((Object) frame);
    }

    private String formatArgs(String... args) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (String str : args) {
            if (!str.isEmpty()) {
                builder.append(str);
                if (args.length > 1 && ++count < args.length) {
                    builder.append("\",\"");
                }
            }
        }
        return builder.toString();
    }

    public void beginTimer() {
        TimerTask timerTask = (TimerTask) new WebSocketClient(this.listener);
        this.addTask(timerTask);
    }

    private void addTask(TimerTask task) {
        this.timer.newTimeout(task, 5000L, TimeUnit.MILLISECONDS);
    }

    private void timerTask() {
        this.send("ping");
    }

    static {
        crc32 = Hashing.crc32();
    }
}
