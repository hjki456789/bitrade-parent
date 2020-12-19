package cn.ztuo.bitrade.exchanges.okex.websocket;

import io.netty.handler.codec.http.*;
import io.netty.util.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.channel.*;

import java.util.zip.*;

import io.netty.buffer.*;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private ChannelPromise handshakeFuture;
    private final WebSocketClientHandshaker handshaker;
    private WebSocketClient webSocketClient;
    private WebSocketListener listener;

    public WebSocketClientHandler(final WebSocketClient webSocketClient, final WebSocketClientHandshaker handshaker, final WebSocketListener listener) {
        this.handshaker = handshaker;
        this.webSocketClient = webSocketClient;
        this.listener = listener;
    }

    protected void channelRead0(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        final Channel ch = ctx.channel();
        if (!this.handshaker.isHandshakeComplete()) {
            this.handshaker.finishHandshake(ch, (FullHttpResponse) msg);
            this.handshakeFuture.setSuccess();
            this.listener.onWebsocketOpen((WebSocket) this.webSocketClient);
            this.webSocketClient.beginTimer();
            return;
        }
        if (msg instanceof FullHttpResponse) {
            final FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        }
        final WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof PongWebSocketFrame) {
            this.listener.onWebsocketPong((WebSocket) this.webSocketClient);
        } else if (frame instanceof CloseWebSocketFrame) {
            final CloseWebSocketFrame closeFrame = (CloseWebSocketFrame) frame;
            final int closeCode = closeFrame.statusCode();
            this.listener.onWebsocketClose((WebSocket) this.webSocketClient, closeCode);
        } else if (frame instanceof BinaryWebSocketFrame) {
            final String msgStr = decode(msg);
            if (msgStr.equals("pong")) {
                this.listener.onWebsocketPong((WebSocket) this.webSocketClient);
            } else {
                this.listener.onTextMessage((WebSocket) this.webSocketClient, msgStr);
            }
        } else if (frame instanceof TextWebSocketFrame) {
            final String txt = ((TextWebSocketFrame) frame).text();
            if (txt.equals("pong")) {
                this.listener.onWebsocketPong((WebSocket) this.webSocketClient);
            } else {
                this.listener.onTextMessage((WebSocket) this.webSocketClient, txt);
            }
        } else {
            this.listener.handleCallbackError((WebSocket) this.webSocketClient, (Throwable) new RuntimeException("cannot decode message"));
        }
    }

    public void handlerAdded(final ChannelHandlerContext ctx) {
        this.handshakeFuture = ctx.newPromise();
    }

    public ChannelFuture handshakeFuture() {
        return (ChannelFuture) this.handshakeFuture;
    }

    public void channelActive(final ChannelHandlerContext ctx) {
        this.handshaker.handshake(ctx.channel());
    }

    public void channelInactive(final ChannelHandlerContext ctx) {
    }

    private static String uncompress(final ByteBuf buf) {
        try {
            final byte[] temp = new byte[buf.readableBytes()];
            final ByteBufInputStream bis = new ByteBufInputStream(buf);
            bis.read(temp);
            bis.close();
            final Inflater decompresser = new Inflater(true);
            decompresser.setInput(temp, 0, temp.length);
            final StringBuilder sb = new StringBuilder();
            final byte[] result = new byte[1024];
            while (!decompresser.finished()) {
                final int resultLength = decompresser.inflate(result);
                sb.append(new String(result, 0, resultLength, "UTF-8"));
            }
            decompresser.end();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String decode(final Object msg) {
        final BinaryWebSocketFrame frameBinary = (BinaryWebSocketFrame) msg;
        final byte[] bytes = new byte[frameBinary.content().readableBytes()];
        frameBinary.content().readBytes(bytes);
        final ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        final String str = uncompress(byteBuf);
        return str;
    }
}
