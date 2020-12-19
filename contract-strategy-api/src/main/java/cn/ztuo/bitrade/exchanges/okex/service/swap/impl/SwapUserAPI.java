package cn.ztuo.bitrade.exchanges.okex.service.swap.impl;

import retrofit2.*;
import com.alibaba.fastjson.*;
import retrofit2.http.*;

public interface SwapUserAPI {
    @GET("/api/swap/v3/position")
    Call<String> getPositions();

    @GET("/api/swap/v3/{instrument_id}/position")
    Call<String> getPosition(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/accounts")
    Call<String> getAccounts();

    @GET("/api/swap/v3/{instrument_id}/accounts")
    Call<String> selectAccount(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/accounts/{instrument_id}/settings")
    Call<String> selectContractSettings(@Path("instrument_id") final String p0);

    @POST("/api/swap/v3/accounts/{instrument_id}/leverage")
    Call<String> updateLevelRate(@Path("instrument_id") final String p0, @Body final JSONObject p1);

    @GET("/api/swap/v3/orders/{instrument_id}")
    Call<String> selectOrders(@Path("instrument_id") final String p0, @Query("state") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    @GET("/api/swap/v3/orders/{instrument_id}/{order_id}")
    Call<String> selectOrderByOrderId(@Path("instrument_id") final String p0, @Path("order_id") final String p1);

    @GET("/api/swap/v3/orders/{instrument_id}/{client_oid}")
    Call<String> selectOrderByClientOid(@Path("instrument_id") final String p0, @Path("client_oid") final String p1);

    @GET("/api/swap/v3/fills")
    Call<String> selectDealDetail(@Query("instrument_id") final String p0, @Query("order_id") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    @GET("/api/swap/v3/accounts/{instrument_id}/ledger")
    Call<String> getLedger(@Path("instrument_id") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3, @Query("type") final String p4);

    @GET("/api/swap/v3/accounts/{instrument_id}/holds")
    Call<String> getHolds(@Path("instrument_id") final String p0);

    @GET("/api/swap/v3/trade_fee")
    Call<String> getTradeFee();
}
