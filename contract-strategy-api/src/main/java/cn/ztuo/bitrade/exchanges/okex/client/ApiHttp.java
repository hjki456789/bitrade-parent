package cn.ztuo.bitrade.exchanges.okex.client;

import cn.ztuo.bitrade.exchanges.okex.config.*;
import cn.ztuo.bitrade.exchanges.okex.constant.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.exception.*;

import java.io.*;

import okhttp3.*;
import cn.ztuo.bitrade.exchanges.okex.utils.*;

import java.util.*;

import org.slf4j.*;

public class ApiHttp {
    private static final Logger LOG;
    private OkHttpClient client;
    private APIConfiguration config;
    static final MediaType JSON;

    public ApiHttp(final APIConfiguration config, final OkHttpClient client) {
        this.config = config;
        this.client = client;
    }

    public String get(final String url) {
        final Request request = new Request.Builder().url(this.url(url)).build();
        return this.execute(request);
    }

    public String post(final String url, final JSONObject params) {
        final String body = params.toJSONString();
        final RequestBody requestBody = RequestBody.create(ApiHttp.JSON, body);
        final Request request = new Request.Builder().url(this.url(url)).post(requestBody).build();
        return this.execute(request);
    }

    public String delete(final String url, final JSONObject params) {
        final String body = params.toJSONString();
        final RequestBody requestBody = RequestBody.create(ApiHttp.JSON, body);
        final Request request = new Request.Builder().url(this.url(url)).delete(requestBody).build();
        return this.execute(request);
    }

    public String execute(final Request request) {
        try {
            final Response response = this.client.newCall(request).execute();
            final int status = response.code();
            final String bodyString = response.body().string();
            final boolean responseIsNotNull = response != null;
            if (this.config.isPrint()) {
                this.printResponse(status, response.message(), bodyString, responseIsNotNull);
            }
            final String message = response.code() + " / " + response.message();
            if (response.isSuccessful()) {
                return bodyString;
            }
            if (APIConstants.resultStatusArray.contains(status)) {
                final HttpResult result = (HttpResult) com.alibaba.fastjson.JSON.parseObject(bodyString, (Class) HttpResult.class);
                throw new APIException(result.getCode(), result.getMessage());
            }
            throw new APIException(message);
        } catch (IOException e) {
            throw new APIException("APIClient executeSync exception.", (Throwable) e);
        }
    }

    private void printResponse(final int status, final String message, final String body, final boolean responseIsNotNull) {
        final StringBuilder responseInfo = new StringBuilder();
        responseInfo.append("\n\tResponse").append("(").append(DateUtils.timeToString((Date) null, 4)).append("):");
        if (responseIsNotNull) {
            responseInfo.append("\n\t\t").append("Status: ").append(status);
            responseInfo.append("\n\t\t").append("Message: ").append(message);
            responseInfo.append("\n\t\t").append("Response Body: ").append(body);
        } else {
            responseInfo.append("\n\t\t").append("\n\tRequest Error: response is null");
        }
        ApiHttp.LOG.info(responseInfo.toString());
    }

    public String url(final String url) {
        return this.config.getEndpoint() + url;
    }

    static {
        LOG = LoggerFactory.getLogger((Class) ApiHttp.class);
        JSON = MediaType.parse("application/json; charset=utf-8");
    }
}
