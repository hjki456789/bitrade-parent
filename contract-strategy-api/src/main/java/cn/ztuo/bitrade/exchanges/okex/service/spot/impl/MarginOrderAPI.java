package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import retrofit2.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import com.alibaba.fastjson.*;
import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;

public interface MarginOrderAPI {
    @POST("api/margin/v3/orders")
    Call<OrderResult> addOrder(@Body final PlaceOrderParam p0);

    @POST("api/margin/v3/batch_orders")
    Call<Map<String, List<OrderResult>>> addOrders(@Body final List<PlaceOrderParam> p0);

    @HTTP(method = "DELETE", path = "api/margin/v3/orders/{order_id}", hasBody = true)
    Call<OrderResult> cancleOrdersByProductIdAndOrderId(@Path("order_id") final String p0, @Body final PlaceOrderParam p1);

    @HTTP(method = "POST", path = "api/margin/v3/cancel_orders/{order_id}", hasBody = true)
    Call<OrderResult> cancleOrdersByOrderId(@Path("order_id") final String p0, @Body final PlaceOrderParam p1);

    @HTTP(method = "POST", path = "api/margin/v3/cancel_orders/{client_oid}", hasBody = true)
    Call<OrderResult> cancleOrdersByClientOid(@Path("client_oid") final String p0, @Body final PlaceOrderParam p1);

    @HTTP(method = "DELETE", path = "api/margin/v3/batch_orders", hasBody = true)
    Call<Map<String, JSONObject>> batchCancleOrders(@Body final List<OrderParamDto> p0);

    @HTTP(method = "POST", path = "api/margin/v3/cancel_batch_orders", hasBody = true)
    Call<Map<String, Object>> batchCancleOrders_1(@Body final List<OrderParamDto> p0);

    @GET("api/margin/v3/orders/{order_id}")
    Call<OrderInfo> getOrderByProductIdAndOrderId(@Path("order_id") final String p0, @Query("instrument_id") final String p1);

    @GET("api/margin/v3/orders/{client_oid}")
    Call<OrderInfo> getOrderByClientOid(@Path("client_oid") final String p0, @Query("instrument_id") final String p1);

    @GET("api/margin/v3/orders")
    Call<List<OrderInfo>> getOrders(@Query("instrument_id") final String p0, @Query("state") final String p1, @Query("after") final String p2, @Query("before") final String p3, @Query("limit") final String p4);

    @GET("api/margin/v3/orders_pending")
    Call<List<PendingOrdersInfo>> getPendingOrders(@Query("before") final String p0, @Query("after") final String p1, @Query("limit") final String p2, @Query("instrument_id") final String p3);

    @GET("api/margin/v3/fills")
    Call<List<MarginFills>> getFills(@Query("order_id") final String p0, @Query("instrument_id") final String p1, @Query("after") final String p2, @Query("before") final String p3, @Query("limit") final String p4);
}
