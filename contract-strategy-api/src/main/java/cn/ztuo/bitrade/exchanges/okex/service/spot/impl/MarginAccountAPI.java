package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import retrofit2.*;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import com.alibaba.fastjson.*;

public interface MarginAccountAPI {
    @GET("/api/margin/v3/accounts")
    Call<List<Map<String, Object>>> getAccounts();

    @GET("/api/margin/v3/accounts/{instrument_id}")
    Call<Map<String, Object>> getAccountsByProductId(@Path("instrument_id") final String p0);

    @GET("/api/margin/v3/accounts/{instrument_id}/ledger")
    Call<List<UserMarginBillDto>> getLedger(@Path("instrument_id") final String p0, @Query("type") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    @GET("/api/margin/v3/accounts/availability")
    Call<List<Map<String, Object>>> getAvailability();

    @GET("/api/margin/v3/accounts/{instrument_id}/availability")
    Call<List<Map<String, Object>>> getAvailabilityByProductId(@Path("instrument_id") final String p0);

    @GET("/api/margin/v3/accounts/borrowed")
    Call<List<MarginBorrowOrderDto>> getBorrowedAccounts(@Query("status") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3);

    @GET("/api/margin/v3/accounts/{instrument_id}/borrowed")
    Call<List<MarginBorrowOrderDto>> getBorrowedAccountsByProductId(@Path("instrument_id") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3, @Query("status") final String p4);

    @POST("/api/margin/v3/accounts/borrow")
    Call<BorrowResult> borrow_1(@Body final BorrowRequestDto p0);

    @POST("/api/margin/v3/accounts/repayment")
    Call<RepaymentResult> repayment_1(@Body final RepaymentRequestDto p0);

    @POST("/api/margin/v3/accounts/{instrument_id}/leverage")
    Call<JSONObject> setLeverage(@Path("instrument_id") final String p0, @Body final MarginLeverage p1);

    @GET("/api/margin/v3/accounts/{instrument_id}/leverage")
    Call<JSONObject> getLeverage(@Path("instrument_id") final String p0);
}
