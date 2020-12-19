package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import cn.ztuo.bitrade.exchanges.okex.service.spot.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

public class MarginProductAPIServiceImpl implements MarginProductAPIService {
    private final APIClient client;
    private final MarginProductAPI marginProductAPI;

    public MarginProductAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.marginProductAPI = this.client.createService(MarginProductAPI.class);
    }

    public String getMarginMarkPrice(final String instrument_id) {
        return this.client.executeSync(this.marginProductAPI.getMarginMarkPrice(instrument_id));
    }
}
