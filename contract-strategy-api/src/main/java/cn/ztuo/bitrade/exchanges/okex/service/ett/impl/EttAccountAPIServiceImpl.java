package cn.ztuo.bitrade.exchanges.okex.service.ett.impl;

import cn.ztuo.bitrade.exchanges.okex.service.ett.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

public class EttAccountAPIServiceImpl implements EttAccountAPIService {
    private final APIClient client;
    private final EttAccountAPI api;

    public EttAccountAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(EttAccountAPI.class);
    }

    @Override
    public List<EttAccount> getAccount() {
        return this.client.executeSync(this.api.getAccount());
    }

    @Override
    public EttAccount getAccount(final String currency) {
        return this.client.executeSync(this.api.getAccount(currency));
    }

    @Override
    public CursorPager<EttLedger> getLedger(final String currency, final String before, final String after, final int limit) {
        return this.client.executeSyncCursorPager(this.api.getLedger(currency, before, after, limit));
    }
}
