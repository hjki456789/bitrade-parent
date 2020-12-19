package cn.ztuo.bitrade.exchanges.okex.service.account.impl;

import cn.ztuo.bitrade.exchanges.okex.bean.account.result.Currency;
import retrofit2.*;

import java.util.*;

import retrofit2.http.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.account.result.*;

public interface AccountAPI {
    @GET("/api/account/v3/wallet")
    Call<List<Wallet>> getWallet();

    @GET("/api/account/v3/wallet/{currency}")
    Call<List<Wallet>> getWallet(@Path("currency") final String p0);

    @POST("/api/account/v3/transfer")
    Call<JSONObject> transfer(@Body final JSONObject p0);

    @POST("/api/account/v3/withdrawal")
    Call<JSONObject> withdraw(@Body final JSONObject p0);

    @GET("/api/account/v3/currencies")
    Call<List<Currency>> getCurrencies();

    @GET("/api/account/v3/ledger")
    Call<JSONArray> getLedger(@Query("type") final String p0, @Query("currency") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    @GET("/api/account/v3/deposit/address")
    Call<JSONArray> getDepositAddress(@Query("currency") final String p0);

    @GET("/api/account/v3/withdrawal/fee")
    Call<List<WithdrawFee>> getWithdrawFee(@Query("currency") final String p0);

    @GET("/api/account/v3/onhold")
    Call<JSONArray> getOnHold(@Query("currency") final String p0);

    @POST("/api/account/v3/lock")
    Call<JSONObject> lock(@Body final JSONObject p0);

    @POST("/api/account/v3/unlock")
    Call<JSONObject> unlock(@Body final JSONObject p0);

    @GET("/api/account/v3/deposit/history")
    Call<JSONArray> getDepositHistory();

    @GET("/api/account/v3/deposit/history/{currency}")
    Call<JSONArray> getDepositHistory(@Path("currency") final String p0);

    @GET("/api/account/v3/withdrawal/history")
    Call<JSONArray> getWithdrawalHistory();

    @GET("/api/account/v3/withdrawal/history/{currency}")
    Call<JSONArray> getWithdrawalHistory(@Path("currency") final String p0);

    @GET("/api/account/v3/sub-account")
    Call<JSONObject> getSubAccount(@Query("sub-account") final String p0);

    @GET("/api/account/v3/asset-valuation")
    Call<JSONObject> getAllAccount(@Query("account_type") final String p0, @Query("valuation_currency") final String p1);
}
