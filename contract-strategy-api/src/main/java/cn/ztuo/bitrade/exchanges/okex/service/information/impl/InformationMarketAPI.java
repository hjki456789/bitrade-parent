package cn.ztuo.bitrade.exchanges.okex.service.information.impl;

import retrofit2.*;
import com.alibaba.fastjson.*;
import retrofit2.http.*;

public interface InformationMarketAPI {
    @GET("/api/information/v3/{currency}/long_short_ratio")
    Call<JSONArray> getLongShortRatio(@Path("currency") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/information/v3/{currency}/volume")
    Call<JSONArray> getVolume(@Path("currency") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/information/v3/{currency}/taker")
    Call<JSONArray> getTaker(@Path("currency") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/information/v3/{currency}/sentiment")
    Call<JSONArray> getSentiment(@Path("currency") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/information/v3/{currency}/margin")
    Call<JSONArray> getMargin(@Path("currency") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);
}
