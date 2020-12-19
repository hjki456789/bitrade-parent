package cn.ztuo.bitrade.exchanges.okex.service.index.impl;

import cn.ztuo.bitrade.exchanges.okex.service.index.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

public class IndexMarketAPIServiceImpl implements IndexMarketAPIService {
    private final APIClient client;
    private final IndexMarketAPI indexMarketAPI;

    public IndexMarketAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.indexMarketAPI = this.client.createService(IndexMarketAPI.class);
    }

    public String getIndex(final String instrument_id) {
        return this.client.executeSync(this.indexMarketAPI.getIndex(instrument_id));
    }
}
