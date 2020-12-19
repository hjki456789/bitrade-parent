package cn.ztuo.bitrade.exchanges.okex.service.ett.impl;

import cn.ztuo.bitrade.exchanges.okex.service.ett.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

public class EttProductAPIServiceImpl implements EttProductAPIService {
    private final APIClient client;
    private final EttProductAPI api;

    public EttProductAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(EttProductAPI.class);
    }

    @Override
    public EttConstituentsResult getConstituents(final String ett) {
        return this.client.executeSync(this.api.getConstituents(ett));
    }

    @Override
    public List<EttSettlementDefinePrice> getDefinePrice(final String ett) {
        return this.client.executeSync(this.api.getDefinePrice(ett));
    }
}
