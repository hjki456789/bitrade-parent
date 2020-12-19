package cn.ztuo.bitrade.exchanges.okex.service.swap.impl;

import retrofit2.*;
import retrofit2.http.*;

public interface SwapMarketAPI {
    @GET("/api/swap/v3/instruments")
    Call<String> getContractsApi();

    @GET("/api/swap/v3/instruments/{instrument_id}/depth")
    Call<String> getDepthApi(@Path("instrument_id") final String p0, @Query("size") final String p1, @Query("depth") final String p2);

    @GET("/api/swap/v3/instruments/ticker")
    Call<String> getTickersApi();

    @GET("/api/swap/v3/instruments/{instrument_id}/ticker")
    Call<String> getTickerApi(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/instruments/{instrument_id}/trades")
    Call<String> getTradesApi(@Path("instrument_id") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3);

    @GET("/api/swap/v3/instruments/{instrument_id}/candles")
    Call<String> getCandlesApi(@Path("instrument_id") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/swap/v3/instruments/{instrument_id}/history/candles")
    Call<String> getHistoryCandlesApi(@Path("instrument_id") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/swap/v3/instruments/{instrument_id}/index")
    Call<String> getIndexApi(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/rate")
    Call<String> getRateApi();

    @GET("/api/swap/v3/instruments/{instrument_id}/open_interest")
    Call<String> getOpenInterestApi(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/instruments/{instrument_id}/price_limit")
    Call<String> getPriceLimitApi(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/instruments/{instrument_id}/liquidation")
    Call<String> getLiquidationApi(@Path("instrument_id") final String p0, @Query("status") final String p1, @Query("from") final String p2, @Query("to") final String p3, @Query("limit") final String p4);

    @GET("/api/swap/v3/instruments/{instrument_id}/funding_time")
    Call<String> getFundingTimeApi(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/instruments/{instrument_id}/historical_funding_rate")
    Call<String> getHistoricalFundingRateApi(@Path("instrument_id") final String p0, @Query("limit") final String p1);

    @GET("/api/swap/v3/instruments/{instrument_id}/mark_price")
    Call<String> getMarkPriceApi(@Path("instrument_id") final String p0);
}
