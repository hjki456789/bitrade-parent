package cn.ztuo.bitrade.exchanges.okex.service.option.impl;

import retrofit2.*;
import retrofit2.http.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.option.param.*;

public interface OptionTradeAPI {
    @GET("/api/option/v3/accounts/{underlying}")
    Call<JSONObject> getAccount(@Path("underlying") final String p0);

    @POST("/api/option/v3/amend_order/{underlying}")
    Call<JSONObject> amendOrder(@Path("underlying") final String p0, @Body final AmentDate p1);

    @POST("/api/option/v3/amend_batch_orders/{underlying}")
    Call<JSONObject> amendBatchOrders(@Path("underlying") final String p0, @Body final AmendDateParam p1);

    @POST("/api/option/v3/cancel_order/{underlying}/{order_id}")
    Call<JSONObject> cancelOrder(@Path("underlying") final String p0, @Path("order_id") final String p1);

    @POST("/api/option/v3/cancel_order/{underlying}/{client_oid}")
    Call<JSONObject> cancelOrderByClientOid(@Path("underlying") final String p0, @Path("client_oid") final String p1);

    @POST("/api/option/v3/cancel_batch_orders/{underlying}")
    Call<JSONObject> cancelBatchOrders(@Path("underlying") final String p0, @Body final CancelOrders p1);

    @GET("/api/option/v3/fills/{underlying}")
    Call<JSONArray> getFills(@Path("underlying") final String p0, @Query("order_id") final String p1, @Query("instrument_id") final String p2, @Query("before") final String p3, @Query("after") final String p4, @Query("limit") final String p5);

    @GET("/api/option/v3/accounts/{underlying}/ledger")
    Call<JSONArray> getLedger(@Path("underlying") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3);

    @POST("/api/option/v3/order")
    Call<JSONObject> getOrder(@Body final OrderParam p0);

    @GET("/api/option/v3/orders/{underlying}/{order_id}")
    Call<JSONObject> getOrderInfo(@Path("underlying") final String p0, @Path("order_id") final String p1);

    @GET("/api/option/v3/orders/{underlying}/{client_oid}")
    Call<JSONObject> getOrderInfoByClientOid(@Path("underlying") final String p0, @Path("client_oid") final String p1);

    @GET("/api/option/v3/orders/{underlying}")
    Call<JSONObject> getOrderList(@Path("underlying") final String p0, @Query("state") final String p1, @Query("instrument_id") final String p2, @Query("before") final String p3, @Query("after") final String p4, @Query("limit") final String p5);

    @POST("/api/option/v3/orders")
    Call<JSONObject> getOrders1(@Body final OrderDataParam p0);

    @GET("/api/option/v3/{underlying}/position")
    Call<JSONObject> getPosition(@Path("underlying") final String p0, @Query("instrument_id") final String p1);

    @GET("/api/option/v3/trade_fee")
    Call<JSONObject> getTradeFee();
}
