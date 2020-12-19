package cn.ztuo.bitrade.exchanges.okex.service.futures.impl;

import cn.ztuo.bitrade.exchanges.okex.service.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;

public class GeneralAPIServiceImpl implements GeneralAPIService {
    private APIClient client;
    private FuturesMarketAPI api;

    public GeneralAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(FuturesMarketAPI.class);
    }

    public ServerTime getServerTime() {
        return this.client.executeSync(this.api.getServerTime());
    }

    public ExchangeRate getExchangeRate() {
        return this.client.executeSync(this.api.getExchangeRate());
    }
}
