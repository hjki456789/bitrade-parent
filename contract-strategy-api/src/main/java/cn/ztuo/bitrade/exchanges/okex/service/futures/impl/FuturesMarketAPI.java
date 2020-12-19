package cn.ztuo.bitrade.exchanges.okex.service.futures.impl;

import retrofit2.*;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;
import com.alibaba.fastjson.*;

interface FuturesMarketAPI {
    @GET("/api/general/v3/time")
    Call<ServerTime> getServerTime();

    @GET("/api/futures/v3/rate")
    Call<ExchangeRate> getExchangeRate();

    @GET("/api/futures/v3/instruments")
    Call<List<Instruments>> getInstruments();

    @GET("/api/futures/v3/instruments/currencies")
    Call<List<Currencies>> getCurrencies();

    @GET("/api/futures/v3/instruments/{instrument_id}/book")
    Call<Book> getInstrumentBook(@Path("instrument_id") final String p0, @Query("size") final String p1, @Query("depth") final String p2);

    @GET("/api/futures/v3/instruments/{instrument_id}/ticker")
    Call<Ticker> getInstrumentTicker(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/instruments/ticker")
    Call<List<Ticker>> getAllInstrumentTicker();

    @GET("/api/futures/v3/instruments/{instrument_id}/trades")
    Call<List<Trades>> getInstrumentTrades(@Path("instrument_id") final String p0, @Query("after") final String p1, @Query("before") final String p2, @Query("limit") final String p3);

    @GET("/api/futures/v3/instruments/{instrument_id}/candles")
    Call<JSONArray> getInstrumentCandles(@Path("instrument_id") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3);

    @GET("/api/futures/v3/instruments/{instrument_id}/history/candles")
    Call<JSONArray> getHistoryCandels(@Path("instrument_id") final String p0, @Query("start") final String p1, @Query("end") final String p2, @Query("granularity") final String p3, @Query("limit") final String p4);

    @GET("/api/futures/v3/instruments/{instrument_id}/index")
    Call<Index> getInstrumentIndex(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/instruments/{instrument_id}/estimated_price")
    Call<EstimatedPrice> getInstrumentEstimatedPrice(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/instruments/{instrument_id}/open_interest")
    Call<Holds> getInstrumentHolds(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/instruments/{instrument_id}/price_limit")
    Call<PriceLimit> getInstrumentPriceLimit(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/instruments/{instrument_id}/liquidation")
    Call<List<Liquidation>> getInstrumentLiquidation(@Path("instrument_id") final String p0, @Query("status") final String p1, @Query("from") final String p2, @Query("to") final String p3, @Query("limit") final String p4);

    @GET("/api/futures/v3/instruments/{instrument_id}/mark_price")
    Call<JSONObject> getMarkPrice(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/settlement/history")
    Call<JSONArray> getSettlementHistory(@Query("instrument_id") final String p0, @Query("underlying") final String p1, @Query("start") final String p2, @Query("limit") final String p3, @Query("end") final String p4);
}
