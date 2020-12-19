package cn.ztuo.bitrade.exchanges.okex.service.index.impl;

import retrofit2.*;
import retrofit2.http.*;

interface IndexMarketAPI {
    @GET("/api/index/v3/{instrument_id}/constituents")
    Call<String> getIndex(@Path("instrument_id") final String p0);
}
