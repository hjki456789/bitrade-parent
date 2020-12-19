package cn.ztuo.bitrade.exchanges.okex.service.ett.impl;

import retrofit2.*;
import retrofit2.http.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

interface EttProductAPI {
    @GET("/api/ett/v3/constituents/{ett}")
    Call<EttConstituentsResult> getConstituents(@Path("ett") final String p0);

    @GET("/api/ett/v3/define-price/{ett}")
    Call<List<EttSettlementDefinePrice>> getDefinePrice(@Path("ett") final String p0);
}
