package cn.ztuo.bitrade.exchanges.okex.client;

import cn.ztuo.bitrade.exchanges.okex.config.*;
import okhttp3.*;
import retrofit2.converter.scalars.*;
import retrofit2.converter.gson.*;
import retrofit2.adapter.rxjava.*;
import retrofit2.*;

public class APIRetrofit {
    private APIConfiguration config;
    private OkHttpClient client;

    public APIRetrofit(final APIConfiguration config, final OkHttpClient client) {
        this.config = config;
        this.client = client;
    }

    public Retrofit retrofit() {
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(this.client);
        builder.addConverterFactory((Converter.Factory) ScalarsConverterFactory.create());
        builder.addConverterFactory((Converter.Factory) GsonConverterFactory.create());
        builder.addCallAdapterFactory((CallAdapter.Factory) RxJavaCallAdapterFactory.create());
        builder.baseUrl(this.config.getEndpoint());
        return builder.build();
    }
}
