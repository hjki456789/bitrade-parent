package cn.ztuo.bitrade.exchanges.okex.service.futures.impl;

import retrofit2.*;
import com.alibaba.fastjson.*;
import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.param.*;

interface FuturesTradeAPI {
    @GET("/api/futures/v3/position")
    Call<JSONObject> getPositions();

    @GET("/api/futures/v3/{instrument_id}/position")
    Call<JSONObject> getInstrumentPosition(@Path("instrument_id") final String p0);

    @GET("/api/futures/v3/accounts")
    Call<JSONObject> getAccounts();

    @GET("/api/futures/v3/accounts/{underlying}")
    Call<JSONObject> getAccountsByCurrency(@Path("underlying") final String p0);

    @GET("/api/futures/v3/accounts/{underlying}/ledger")
    Call<JSONArray> getAccountsLedgerByCurrency(@Path("underlying") final String p0, @Query("after") final String p1, @Query("before") final String p2, @Query("limit") final String p3, @Query("type") final String p4);

    @GET("/api/futures/v3/accounts/{instrument_id}/holds")
    Call<JSONObject> getAccountsHoldsByInstrumentId(@Path("instrument_id") final String p0);

    @POST("/api/futures/v3/order")
    Call<OrderResult> order(@Body final JSONObject p0);

    @POST("/api/futures/v3/orders")
    Call<JSONObject> orders(@Body final JSONObject p0);

    @POST("/api/futures/v3/cancel_order/{instrument_id}/{order_id}")
    Call<JSONObject> cancelOrderByOrderId(@Path("instrument_id") final String p0, @Path("order_id") final String p1);

    @POST("/api/futures/v3/cancel_order/{instrument_id}/{client_oid}")
    Call<JSONObject> cancelOrderByClientOid(@Path("instrument_id") final String p0, @Path("client_oid") final String p1);

    @POST("/api/futures/v3/cancel_batch_orders/{instrument_id}")
    Call<JSONObject> cancelOrders(@Path("instrument_id") final String p0, @Body final JSONObject p1);

    @POST("/api/futures/v3/amend_order/{instrument_id}")
    Call<JSONObject> amendOrderByOrderId(@Path("instrument_id") final String p0, @Body final AmendOrder p1);

    @POST("/api/futures/v3/amend_order/{instrument_id}")
    Call<JSONObject> amendOrderByClientOid(@Path("instrument_id") final String p0, @Body final AmendOrder p1);

    @POST("/api/futures/v3/amend_batch_orders/{instrument_id}")
    Call<JSONObject> amendBatchOrdersByOrderId(@Path("instrument_id") final String p0, @Body final AmendDateParam p1);

    @POST("/api/futures/v3/amend_batch_orders/{instrument_id}")
    Call<JSONObject> amendBatchOrdersByClientOid(@Path("instrument_id") final String p0, @Body final AmendDateParam p1);

    @GET("/api/futures/v3/orders/{instrument_id}")
    Call<JSONObject> getOrders(@Path("instrument_id") final String p0, @Query("state") final String p1, @Query("after") final String p2, @Query("before") final String p3, @Query("limit") final String p4);

    @GET("/api/futures/v3/orders/{instrument_id}/{order_id}")
    Call<JSONObject> getOrderByOrderId(@Path("instrument_id") final String p0, @Path("order_id") final String p1);

    @GET("/api/futures/v3/orders/{instrument_id}/{client_oid}")
    Call<JSONObject> getOrderByClientOid(@Path("instrument_id") final String p0, @Path("client_oid") final String p1);

    @GET("/api/futures/v3/fills")
    Call<JSONArray> getFills(@Query("instrument_id") final String p0, @Query("order_id") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    @GET("/api/futures/v3/accounts/{underlying}/leverage")
    Call<JSONObject> getLeverRate(@Path("underlying") final String p0);

    @POST("/api/futures/v3/accounts/{underlying}/leverage")
    Call<JSONObject> changeLeverageOnFixed(@Path("underlying") final String p0, @Body final JSONObject p1);

    @POST("/api/futures/v3/accounts/{underlying}/leverage")
    Call<JSONObject> changeLeverageOnCross(@Path("underlying") final String p0, @Body final JSONObject p1);

    @POST("/api/futures/v3/close_position")
    Call<JSONObject> closePositions(@Body final JSONObject p0);

    @POST("/api/futures/v3/cancel_all")
    Call<JSONObject> cancelAll(@Body final JSONObject p0);

    @POST("/api/futures/v3/accounts/margin_mode")
    Call<JSONObject> changeMarginMode(@Body final JSONObject p0);

    @POST("/api/futures/v3/accounts/liqui_mode")
    Call<JSONObject> changeLiquiMode(@Body final JSONObject p0);

    @POST("api/futures/v3/order_algo")
    Call<FuturesOrderResult> futuresOrder(@Body final FuturesOrderParam p0);

    @POST("api/futures/v3/cancel_algos")
    Call<CancelFuturesOrdeResult> cancelFuturesOrder(@Body final CancelFuturesOrder p0);

    @GET("api/futures/v3/order_algo/{instrument_id}")
    Call<String> findFuturesOrder(@Path("instrument_id") final String p0, @Query("order_type") final String p1, @Query("status") final String p2, @Query("algo_id") final String p3, @Query("after") final String p4, @Query("before") final String p5, @Query("limit") final String p6);

    @GET("/api/futures/v3/trade_fee")
    Call<JSONObject> getTradeFee();

    @GET("/api/futures/v3/accounts/{instrument_id}/holds")
    Call<Holds> getHolds(@Path("instrument_id") final String p0);

    @POST("/api/futures/v3/position/margin")
    Call<JSONObject> modifyMargin(@Body final ModifyMarginParam p0);

    @POST("/api/futures/v3/accounts/auto_margin")
    Call<JSONObject> modifyFixedMargin(@Body final ModifyFixedMargin p0);
}
