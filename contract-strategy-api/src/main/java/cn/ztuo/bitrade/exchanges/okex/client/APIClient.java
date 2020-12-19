package cn.ztuo.bitrade.exchanges.okex.client;

import cn.ztuo.bitrade.exchanges.okex.config.*;
import org.apache.commons.lang3.*;
import retrofit2.*;
import cn.ztuo.bitrade.exchanges.okex.constant.*;
import cn.ztuo.bitrade.exchanges.okex.exception.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.*;
import com.alibaba.fastjson.*;

import java.io.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

import java.util.function.*;

import okhttp3.*;
import cn.ztuo.bitrade.exchanges.okex.utils.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.enums.*;
import org.slf4j.*;
import retrofit2.Call;
import retrofit2.Response;

public class APIClient {
    private static final Logger LOG;
    private final APIConfiguration config;
    private final APICredentials credentials;
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private final ApiHttp apiHttp;

    public APIClient(final APIConfiguration config) {
        if (config == null || StringUtils.isEmpty((CharSequence) config.getEndpoint())) {
            throw new RuntimeException("The APIClient params can't be empty.");
        }
        this.config = config;
        this.credentials = new APICredentials(config);
        this.client = new APIHttpClient(config, this.credentials).client();
        this.retrofit = new APIRetrofit(config, this.client).retrofit();
        this.apiHttp = new ApiHttp(config, this.client);
    }

    public <T> T createService(final Class<T> service) {
        return (T) this.retrofit.create((Class) service);
    }

    public ApiHttp getApiHttp() {
        return this.apiHttp;
    }

    public <T> T executeSync(final Call<T> call) {
        try {
            final Response<T> response = call.execute();
            if (this.config.isPrint()) {
                this.printResponse(response);
            }
            final int status = response.code();
            final String message = response.code() + " / " + response.message();
            if (response.isSuccessful()) {
                return (T) response.body();
            }
            if (!APIConstants.resultStatusArray.contains(status)) {
                throw new APIException(message);
            }
            final HttpResult result = (HttpResult) JSON.parseObject(new String(response.errorBody().bytes()), (Class) HttpResult.class);
            if (result.getCode() == 0 && result.getMessage() == null) {
                throw new APIException(result.getErrorCode(), result.getErrorMessage());
            }
            throw new APIException(result.getCode(), result.getMessage());
        } catch (IOException e) {
            throw new APIException("APIClient executeSync exception.", (Throwable) e);
        }
    }

    public <T> CursorPager<T> executeSyncCursorPager(final Call<List<T>> call) {
        try {
            final Response<List<T>> response = call.execute();
            if (this.config.isPrint()) {
                this.printResponse(response);
            }
            final int status = response.code();
            final String message = response.code() + " / " + response.message();
            if (response.isSuccessful()) {
                final Headers headers = response.headers();
                final CursorPager<T> cursorPager = new CursorPager<T>();
                cursorPager.setData((List<T>) response.body());
                cursorPager.setBefore(headers.get("OK-BEFORE"));
                cursorPager.setAfter(headers.get("OK-AFTER"));
                cursorPager.setLimit(Optional.ofNullable(headers.get("OK-LIMIT")).map(Integer::valueOf).orElse(100));
                return cursorPager;
            }
            if (APIConstants.resultStatusArray.contains(status)) {
                final HttpResult result = (HttpResult) JSON.parseObject(new String(response.errorBody().bytes()), (Class) HttpResult.class);
                throw new APIException(result.getCode(), result.getMessage());
            }
            throw new APIException(message);
        } catch (IOException e) {
            System.out.println("\u5f02\u5e38\u4fe1\u606f");
            throw new APIException("APIClient executeSync exception.", (Throwable) e);
        }
    }

    private void printResponse(final Response response) {
        final StringBuilder responseInfo = new StringBuilder();
        responseInfo.append("\n\tResponse").append("(").append(DateUtils.timeToString((Date) null, 4)).append("):");
        if (response != null) {
            final String limit = response.headers().get(HttpHeadersEnum.OK_LIMIT.header());
            if (StringUtils.isNotEmpty((CharSequence) limit)) {
                responseInfo.append("\n\t\t").append("Headers: ");
                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_FROM.header()).append(": ").append(response.headers().get(HttpHeadersEnum.OK_FROM.header()));
                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_TO.header()).append(": ").append(response.headers().get(HttpHeadersEnum.OK_TO.header()));
                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_LIMIT.header()).append(": ").append(limit);
            }
            responseInfo.append("\n\t\t").append("Status: ").append(response.code());
            responseInfo.append("\n\t\t").append("Message: ").append(response.message());
            if (response.body() != null) {
                responseInfo.append("\n\t\t").append("Response Body: ").append(JSON.toJSONString(response.body()));
            }
        } else {
            responseInfo.append("\n\t\t").append("\n\tRequest Error: response is null");
        }
        APIClient.LOG.info(responseInfo.toString());
    }

    @Override
    public String toString() {
        return "APIClient{config=" + this.config + ", credentials=" + this.credentials + ", client=" + this.client + ", retrofit=" + this.retrofit + ", apiHttp=" + this.apiHttp + '}';
    }

    static {
        LOG = LoggerFactory.getLogger((Class) APIClient.class);
    }
}
