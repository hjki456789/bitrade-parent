package cn.ztuo.bitrade.exchanges.okex.service.ett.impl;

import retrofit2.*;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

interface EttAccountAPI {
    @GET("/api/ett/v3/accounts")
    Call<List<EttAccount>> getAccount();

    @GET("/api/ett/v3/accounts/{currency}")
    Call<EttAccount> getAccount(@Path("currency") final String p0);

    @GET("/api/ett/v3/accounts/{currency}/ledger")
    Call<List<EttLedger>> getLedger(@Path("currency") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final int p3);
}
