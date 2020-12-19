package cn.ztuo.bitrade.exchanges.okex.service.ett.impl;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.param.*;
import retrofit2.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;
import retrofit2.http.*;

interface EttOrderAPI {
    @POST("/api/ett/v3/orders")
    Call<EttCreateOrderResult> createOrder(@Body final EttCreateOrderParam p0);

    @DELETE("/api/ett/v3/orders/{order_id}")
    Call<EttCancelOrderResult> cancelOrder(@Path("order_id") final String p0);

    @GET("/api/ett/v3/orders")
    Call<List<EttOrder>> getOrder(@Query("ett") final String p0, @Query("type") final Integer p1, @Query("status") final Integer p2, @Query("before") final String p3, @Query("after") final String p4, @Query("limit") final int p5);

    @GET("/api/ett/v3/orders/{order_id}")
    Call<EttOrder> getOrder(@Path("order_id") final String p0);
}
