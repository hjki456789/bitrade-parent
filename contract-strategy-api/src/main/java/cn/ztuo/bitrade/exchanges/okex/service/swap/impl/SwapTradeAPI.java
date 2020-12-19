package cn.ztuo.bitrade.exchanges.okex.service.swap.impl;

import retrofit2.*;
import com.alibaba.fastjson.*;
import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;

public interface SwapTradeAPI {
    @POST("/api/swap/v3/order")
    Call<String> order(@Body final PpOrder p0);

    @POST("/api/swap/v3/orders")
    Call<String> orders(@Body final JSONObject p0);

    @POST("/api/swap/v3/cancel_order/{instrument_id}/{order_id}")
    Call<String> cancelOrderByOrderId(@Path("instrument_id") final String p0, @Path("order_id") final String p1);

    @POST("/api/swap/v3/cancel_order/{instrument_id}/{client_oid}")
    Call<String> cancelOrderByClientOid(@Path("instrument_id") final String p0, @Path("client_oid") final String p1);

    @POST("/api/swap/v3/cancel_batch_orders/{instrument_id}")
    Call<String> cancelOrders(@Path("instrument_id") final String p0, @Body final JSONObject p1);

    @POST("/api/swap/v3/amend_order/{instrument_id}")
    Call<String> amendOrder(@Path("instrument_id") final String p0, @Body final AmendOrder p1);

    @POST("/api/swap/v3/amend_order/{instrument_id}")
    Call<String> amendOrderByClientOid(@Path("instrument_id") final String p0, @Body final AmendOrder p1);

    @POST("/api/swap/v3/amend_batch_orders/{instrument_id}")
    Call<String> amendBatchOrderByOrderId(@Path("instrument_id") final String p0, @Body final AmendOrderParam p1);

    @POST("/api/swap/v3/amend_batch_orders/{instrument_id}")
    Call<String> amendBatchOrderByClientOid(@Path("instrument_id") final String p0, @Body final AmendOrderParam p1);

    @POST("/api/swap/v3/order_algo")
    Call<String> swapOrderAlgo(@Body final SwapOrderParam p0);

    @POST("/api/swap/v3/cancel_algos")
    Call<String> cancelOrderAlgo(@Body final CancelOrderAlgo p0);

    @GET("/api/swap/v3/order_algo/{instrument_id}")
    Call<String> getSwapOrders(@Path("instrument_id") final String p0, @Query("order_type") final String p1, @Query("status") final String p2, @Query("algo_id") final String p3, @Query("before") final String p4, @Query("after") final String p5, @Query("limit") final String p6);

    @POST("/api/swap/v3/close_position")
    Call<String> closePosition(@Body final ClosePosition p0);

    @POST("/api/swap/v3/cancel_all")
    Call<String> CancelAll(@Body final CancelAllParam p0);
}
