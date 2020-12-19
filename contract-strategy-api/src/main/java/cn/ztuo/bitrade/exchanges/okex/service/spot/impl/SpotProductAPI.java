package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import retrofit2.*;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import com.alibaba.fastjson.*;

public interface SpotProductAPI {
    @GET("/api/spot/v3/instruments")
    Call<List<Product>> getProducts();

    @GET("/api/spot/v3/instruments/{instrument_id}/book")
    Call<Book> bookProductsByProductId(@Path("instrument_id") final String p0, @Query("size") final String p1, @Query("depth") final String p2);

    @GET("/api/spot/v3/instruments/ticker")
    Call<String> getTickers();

    @GET("/api/spot/v3/instruments/ticker")
    Call<List<Ticker>> getTickers1();

    @GET("/api/spot/v3/instruments/{instrument_id}/ticker")
    Call<Ticker> getTickerByProductId(@Path("instrument_id") final String p0);

    @GET("/api/spot/v3/instruments/{instrument_id}/trades")
    Call<List<Trade>> getTrades(@Path("instrument_id") final String p0, @Query("limit") final String p1);

    @GET("/api/spot/v3/instruments/{instrument_id}/candles")
    Call<JSONArray> getCandles(@Path("instrument_id") final String p0, @Query("granularity") final String p1, @Query("start") final String p2, @Query("end") final String p3);

    @GET("/api/spot/v3/instruments/{instrument_id}/history/candles")
    Call<JSONArray> getHistoryCandles(@Path("instrument_id") final String p0, @Query("granularity") final String p1, @Query("start") final String p2, @Query("end") final String p3);
}
