package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import retrofit2.*;
import retrofit2.http.*;

public interface MarginProductAPI {
    @GET("/api/margin/v3/instruments/{instrument_id}/mark_price")
    Call<String> getMarginMarkPrice(@Path("instrument_id") final String p0);
}
