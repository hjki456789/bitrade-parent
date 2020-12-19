package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import retrofit2.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import retrofit2.http.*;
import com.alibaba.fastjson.*;

public interface SpotAccountAPI {
    @GET("api/spot/v3/miningdata")
    Call<Map<String, Object>> getMiningdata();

    @GET("api/spot/v3/accounts")
    Call<List<Account>> getAccounts();

    @GET("api/spot/v3/accounts/{currency}")
    Call<Account> getAccountByCurrency(@Path("currency") final String p0);

    @GET("api/spot/v3/accounts/{currency}/ledger")
    Call<JSONArray> getLedgersByCurrency(@Path("currency") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3, @Query("type") final String p4);

    @GET("/api/spot/v3/trade_fee")
    Call<JSONObject> getTradeFee();
}
