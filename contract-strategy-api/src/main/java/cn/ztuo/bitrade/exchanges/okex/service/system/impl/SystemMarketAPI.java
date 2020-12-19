package cn.ztuo.bitrade.exchanges.okex.service.system.impl;

import retrofit2.*;
import retrofit2.http.*;

interface SystemMarketAPI {
    @GET("/api/system/v3/maintenance")
    Call<String> getMaintenance(@Query("status") final String p0);
}
