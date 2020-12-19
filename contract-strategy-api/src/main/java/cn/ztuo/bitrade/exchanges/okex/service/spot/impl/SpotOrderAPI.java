package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import retrofit2.*;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;

public interface SpotOrderAPI {
    @POST("api/spot/v3/orders")
    Call<OrderResult> addOrder(@Body final PlaceOrderParam p0);

    @POST("api/spot/v3/batch_orders")
    Call<Map<String, List<OrderResult>>> addOrders(@Body final List<PlaceOrderParam> p0);

    @HTTP(method = "DELETE", path = "api/spot/v3/orders/{order_id}", hasBody = true)
    Call<OrderResult> cancleOrderByOrderId(@Path("order_id") final String p0, @Body final PlaceOrderParam p1);

    @HTTP(method = "POST", path = "api/spot/v3/cancel_orders/{order_id}", hasBody = true)
    Call<OrderResult> cancleOrderByOrderId_1(@Path("order_id") final String p0, @Body final PlaceOrderParam p1);

    @HTTP(method = "POST", path = "api/spot/v3/cancel_orders/{client_oid}", hasBody = true)
    Call<OrderResult> cancleOrderByClientOid(@Path("client_oid") final String p0, @Body final PlaceOrderParam p1);

    @HTTP(method = "DELETE", path = "api/spot/v3/batch_orders", hasBody = true)
    Call<Map<String, BatchOrdersResult>> batchCancleOrders(@Body final List<OrderParamDto> p0);

    @HTTP(method = "POST", path = "api/spot/v3/cancel_batch_orders", hasBody = true)
    Call<Map<String, Object>> batchCancleOrders_1(@Body final List<OrderParamDto> p0);

    @HTTP(method = "POST", path = "api/spot/v3/cancel_batch_orders", hasBody = true)
    Call<Map<String, Object>> batchCancleOrders_2(@Body final List<OrderParamDto> p0);

    @HTTP(method = "POST", path = "api/spot/v3/cancel_batch_orders", hasBody = true)
    Call<Map<String, Object>> batch_orderCle(@Body final List<OrderParamDto> p0);

    @GET("api/spot/v3/orders/{order_id}")
    Call<OrderInfo> getOrderByOrderId(@Path("order_id") final String p0, @Query("instrument_id") final String p1);

    @GET("api/spot/v3/orders/{client_oid}")
    Call<OrderInfo> getOrderByClientOid(@Path("client_oid") final String p0, @Query("instrument_id") final String p1);

    @GET("api/spot/v3/orders")
    Call<List<OrderInfo>> getOrders(@Query("instrument_id") final String p0, @Query("state") final String p1, @Query("after") final String p2, @Query("before") final String p3, @Query("limit") final String p4);

    @GET("api/spot/v3/orders_pending")
    Call<List<PendingOrdersInfo>> getPendingOrders(@Query("before") final String p0, @Query("after") final String p1, @Query("limit") final String p2, @Query("instrument_id") final String p3);

    @GET("api/spot/v3/fills")
    Call<List<Fills>> getFills(@Query("order_id") final String p0, @Query("instrument_id") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    @POST("api/spot/v3/order_algo")
    Call<OrderAlgoResult> addorder_algo(@Body final OrderAlgoParam p0);

    @POST("api/spot/v3/cancel_batch_algos")
    Call<CancelAlgoResult> cancelOrder_algo(@Body final CancelAlgoParam p0);

    @GET("/api/spot/v3/algo")
    Call<String> getAlgoOrder(@Query("instrument_id") final String p0, @Query("order_type") final String p1, @Query("status") final String p2, @Query("algo_id") final String p3, @Query("before") final String p4, @Query("after") final String p5, @Query("limit") final String p6);
}
