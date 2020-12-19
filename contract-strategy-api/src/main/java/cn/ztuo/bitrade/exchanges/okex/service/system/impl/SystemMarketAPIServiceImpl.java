package cn.ztuo.bitrade.exchanges.okex.service.system.impl;

import cn.ztuo.bitrade.exchanges.okex.service.system.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

public class SystemMarketAPIServiceImpl implements SystemMarketAPIService {
    private final APIClient client;
    private final SystemMarketAPI systemMarketAPI;

    public SystemMarketAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.systemMarketAPI = this.client.createService(SystemMarketAPI.class);
    }

    public String getMaintenance(final String status) {
        return this.client.executeSync(this.systemMarketAPI.getMaintenance(status));
    }
}
