package cn.ztuo.bitrade.exchanges.okex.service.option.impl;

import retrofit2.*;
import retrofit2.http.*;
import com.alibaba.fastjson.*;

public interface OptionMarketAPI {
    @GET("/api/option/v3/instruments/{instrument_id}/book")
    Call<JSONObject> getDepthData(@Path("instrument_id") final String p0, @Query("size") final String p1);

    @GET("/api/option/v3/instruments/{instrument_id}/trades")
    Call<JSONArray> getTradeList(@Path("instrument_id") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3);

    @GET("/api/option/v3/instruments/{underlying}")
    Call<JSONArray> getInstruments(@Path("underlying") final String p0, @Query("delivery") final String p1, @Query("instrument_id") final String p2);

    @GET("/api/option/v3/instruments/{underlying}/summary")
    Call<JSONArray> getAllSummary(@Path("underlying") final String p0, @Query("delivery") final String p1);

    @GET("/api/option/v3/instruments/{underlying}/summary/{instrument_id}")
    Call<JSONObject> getDetailPrice(@Path("underlying") final String p0, @Path("instrument_id") final String p1);

    @GET("/api/option/v3/instruments/{instrument_id}/candles")
    Call<JSONArray> getCandles(@Path("instrument_id") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/option/v3/instruments/{instrument_id}/ticker")
    Call<JSONObject> getTicker(@Path("instrument_id") final String p0);

    @GET("/api/option/v3/underlying")
    Call<JSONArray> getUnderlying();

    @GET("/api/option/v3/settlement/history/{underlying}")
    Call<JSONArray> getHistorySettlement(@Path("underlying") final String p0);
}
