package cn.ztuo.bitrade.exchanges.huobi.common.util;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

import org.apache.commons.collections.*;

import java.net.*;

import cn.ztuo.bitrade.exchanges.huobi.common.exception.*;

import java.io.*;

import okhttp3.*;
import com.alibaba.fastjson.*;

import java.util.*;
import java.util.stream.*;

public class HbdmHttpClient {
    private OkHttpClient httpClient;
    private AtomicInteger numRequestFaild;
    static final MediaType JSON_TYPE;

    private HbdmHttpClient() {
        this.numRequestFaild = new AtomicInteger(0);
        final OkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool(200, 10L, TimeUnit.SECONDS)).connectTimeout(3L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.SECONDS);
        this.httpClient = builder.build();
    }

    public static HbdmHttpClient getInstance() {
        return new HbdmHttpClient();
    }

    public String doGet(final String url, final Map<String, String> params) {
        final Request.Builder reqBuild = new Request.Builder();
        final HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (MapUtils.isNotEmpty((Map) params)) {
            params.forEach((k, v) -> urlBuilder.addQueryParameter(k, v));
        }
        reqBuild.url(urlBuilder.build());
        Response response = null;
        try {
            response = this.httpClient.newCall(reqBuild.build()).execute();
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                this.numRequestFaild.getAndIncrement();
            }
            throw new HttpRequestException("http执行异常，url=" + url, e);
        }
        if (response.isSuccessful()) {
            try {
                this.reset();
                return response.body().string();
            } catch (IOException e) {
                throw new HttpRequestException("http结果解析异常", e);
            }
        }
        final int statusCode = response.code();
        throw new HttpRequestException("响应码不为200，返回响应码：" + statusCode + "，url：" + urlBuilder.build());
    }

    public String doPost(final String url, final Map<String, String> params) {
        final FormBody.Builder builder = new FormBody.Builder();
        if (MapUtils.isNotEmpty((Map) params)) {
            params.forEach((k, v) -> builder.add(k, v));
        }
        final Request.Builder reqBuilder = new Request.Builder().url(url);
        if (MapUtils.isNotEmpty((Map) params)) {
            reqBuilder.post((RequestBody) builder.build());
        }
        Response response = null;
        try {
            response = this.httpClient.newCall(reqBuilder.build()).execute();
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                this.numRequestFaild.getAndIncrement();
            }
            throw new HttpRequestException("http执行异常，url=" + url, e);
        }
        if (response.isSuccessful()) {
            try {
                this.reset();
                return response.body().string();
            } catch (IOException e) {
                throw new HttpRequestException("http结果解析异常", e);
            }
        }
        final int statusCode = response.code();
        throw new HttpRequestException("响应码不为200，返回响应码：" + statusCode + "，url：" + reqBuilder.build());
    }

    public String doPostJson(final String url, final Map<String, String> params) {
        final RequestBody body = RequestBody.create(HbdmHttpClient.JSON_TYPE, JSON.toJSONString((Object) params));
        final Request request = new Request.Builder().url(url).post(body).build();
        Response response = null;
        try {
            response = this.httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new HttpRequestException("http执行异常，url=" + url, e);
        }
        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (IOException e) {
                throw new HttpRequestException("http结果解析异常", e);
            }
        }
        final int statusCode = response.code();
        throw new HttpRequestException("响应码不为200，返回响应码：" + statusCode + "，url：" + request);
    }

    public String callJson(final String accessKeyId, final String accessKeySecret, final String method, final String uri, final String str, final Map<String, String> params) {
        final Map map;
        final JSONObject jasonObject = (JSONObject) (map = (Map) JSONObject.parseObject(str));
        return this.call(accessKeyId, accessKeySecret, method, uri, map, params);
    }

    public String call(final String accessKeyId, final String accessKeySecret, final String method, final String uri, final Object object, final Map<String, String> params) {
        final ApiSignature sign = new ApiSignature();
        sign.createSignature(accessKeyId, accessKeySecret, method, uri, params);
        try {
            Request.Builder builder = null;
            if ("POST".equals(method)) {
                final RequestBody body = RequestBody.create(HbdmHttpClient.JSON_TYPE, JSON.toJSONString(object));
                builder = new Request.Builder().url(uri + "?" + this.toQueryString(params)).post(body);
            } else {
                builder = new Request.Builder().url(uri + "?" + this.toQueryString(params)).get();
            }
            final Request request = builder.build();
            final Response response = this.httpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException("IOException 目标url：" + uri, e);
        }
    }

    private String toQueryString(final Map<String, String> params) {
        return String.join("&", params.entrySet().stream().map(entry -> entry.getKey() + "=" + ApiSignature.urlEncode((String) entry.getValue())).collect(Collectors.toList()));
    }

    private void reset() {
        this.numRequestFaild.set(0);
    }

    public int getRequestFaildTotal() {
        return this.numRequestFaild.get();
    }

    static {
        JSON_TYPE = MediaType.parse("application/json");
    }
}
