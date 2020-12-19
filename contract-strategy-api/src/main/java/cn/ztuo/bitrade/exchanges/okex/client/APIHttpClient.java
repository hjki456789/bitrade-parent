package cn.ztuo.bitrade.exchanges.okex.client;

import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.concurrent.*;

import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;
import cn.ztuo.bitrade.exchanges.okex.exception.*;

import java.io.*;
import java.security.*;

import okio.*;
import cn.ztuo.bitrade.exchanges.okex.constant.*;
import cn.ztuo.bitrade.exchanges.okex.utils.*;

import java.util.*;

import okhttp3.*;
import org.slf4j.*;

public class APIHttpClient {
    private static final Logger LOG;
    private final APIConfiguration config;
    private final APICredentials credentials;

    public APIHttpClient(final APIConfiguration config, final APICredentials credentials) {
        this.config = config;
        this.credentials = credentials;
    }

    public OkHttpClient client() {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(this.config.getConnectTimeout(), TimeUnit.SECONDS);
        clientBuilder.readTimeout(this.config.getReadTimeout(), TimeUnit.SECONDS);
        clientBuilder.writeTimeout(this.config.getWriteTimeout(), TimeUnit.SECONDS);
        clientBuilder.retryOnConnectionFailure(this.config.isRetryOnConnectionFailure());
        clientBuilder.addInterceptor(chain -> {
            final Request.Builder requestBuilder = chain.request().newBuilder();
            final String timestamp = DateUtils.getUnixTime();
            requestBuilder.headers(this.headers(chain.request(), timestamp));
            final Request request = requestBuilder.build();
            if (this.config.isPrint()) {
                this.printRequest(request, timestamp);
            }
            return chain.proceed(request);
        });
        return clientBuilder.build();
    }

    private Headers headers(final Request request, final String timestamp) {
        final Headers.Builder builder = new Headers.Builder();
        builder.add("Accept", ContentTypeEnum.APPLICATION_JSON.contentType());
        builder.add("Content-Type", ContentTypeEnum.APPLICATION_JSON_UTF8.contentType());
        builder.add("Cookie", this.getCookie());
        if (StringUtils.isNotEmpty((CharSequence) this.credentials.getSecretKey())) {
            builder.add(HttpHeadersEnum.OK_ACCESS_KEY.header(), this.credentials.getApiKey());
            builder.add(HttpHeadersEnum.OK_ACCESS_SIGN.header(), this.sign(request, timestamp));
            builder.add(HttpHeadersEnum.OK_ACCESS_TIMESTAMP.header(), timestamp);
            builder.add(HttpHeadersEnum.OK_ACCESS_PASSPHRASE.header(), this.credentials.getPassphrase());
        }
        return builder.build();
    }

    private String getCookie() {
        final StringBuilder cookie = new StringBuilder();
        cookie.append("locale=").append(this.config.getI18n().i18n());
        return cookie.toString();
    }

    private String sign(final Request request, final String timestamp) {
        String sign;
        try {
            sign = HmacSHA256Base64Utils.sign(timestamp, this.method(request), this.requestPath(request), this.queryString(request), this.body(request), this.credentials.getSecretKey());
        } catch (IOException e) {
            throw new APIException("Request get body io exception.", (Throwable) e);
        } catch (CloneNotSupportedException e2) {
            throw new APIException("Hmac SHA256 Base64 Signature clone not supported exception.", (Throwable) e2);
        } catch (InvalidKeyException e3) {
            throw new APIException("Hmac SHA256 Base64 Signature invalid key exception.", (Throwable) e3);
        }
        return sign;
    }

    private String url(final Request request) {
        return request.url().toString();
    }

    private String method(final Request request) {
        return request.method().toUpperCase();
    }

    private String requestPath(final Request request) {
        String url = this.url(request);
        String requestPath;
        url = (requestPath = url.replace(this.config.getEndpoint(), ""));
        if (requestPath.contains("?")) {
            requestPath = requestPath.substring(0, url.lastIndexOf("?"));
        }
        if (this.config.getEndpoint().endsWith("/")) {
            requestPath = "/" + requestPath;
        }
        return requestPath;
    }

    private String queryString(final Request request) {
        final String url = this.url(request);
        request.body();
        String queryString = "";
        if (url.contains("?")) {
            queryString = url.substring(url.lastIndexOf("?") + 1);
        }
        return queryString;
    }

    private String body(final Request request) throws IOException {
        final RequestBody requestBody = request.body();
        String body = "";
        if (requestBody != null) {
            final Buffer buffer = new Buffer();
            requestBody.writeTo((BufferedSink) buffer);
            body = buffer.readString(APIConstants.UTF_8);
        }
        return body;
    }

    private void printRequest(final Request request, final String timestamp) {
        final String method = this.method(request);
        final String requestPath = this.requestPath(request);
        final String queryString = this.queryString(request);
        String body;
        try {
            body = this.body(request);
        } catch (IOException e) {
            throw new APIException("Request get body io exception.", (Throwable) e);
        }
        final StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("\n\tRequest").append("(").append(DateUtils.timeToString((Date) null, 4)).append("):");
        requestInfo.append("\n\t\t").append("Url: ").append(this.url(request));
        requestInfo.append("\n\t\t").append("Method: ").append(method);
        requestInfo.append("\n\t\t").append("Headers: ");
        final Headers headers = request.headers();
        if (headers != null && headers.size() > 0) {
            for (final String name : headers.names()) {
                requestInfo.append("\n\t\t\t").append(name).append(": ").append(headers.get(name));
            }
        }
        requestInfo.append("\n\t\t").append("request body: ").append(body);
        final String preHash = HmacSHA256Base64Utils.preHash(timestamp, method, requestPath, queryString, body);
        requestInfo.append("\n\t\t").append("preHash: ").append(preHash);
        APIHttpClient.LOG.info(requestInfo.toString());
    }

    static {
        LOG = LoggerFactory.getLogger((Class) APIHttpClient.class);
    }
}
