package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import cn.ztuo.bitrade.exchanges.okex.service.spot.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import com.alibaba.fastjson.*;

public class SpotAccountAPIServiceImpl implements SpotAccountAPIService {
    private final APIClient client;
    private final SpotAccountAPI api;

    public SpotAccountAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(SpotAccountAPI.class);
    }

    public Map<String, Object> getMiningData() {
        return this.client.executeSync(this.api.getMiningdata());
    }

    public List<Account> getAccounts() {
        return this.client.executeSync(this.api.getAccounts());
    }

    public JSONArray getLedgersByCurrency(final String currency, final String before, final String after, final String limit, final String type) {
        return this.client.executeSync(this.api.getLedgersByCurrency(currency, before, after, limit, type));
    }

    public Account getAccountByCurrency(final String currency) {
        return this.client.executeSync(this.api.getAccountByCurrency(currency));
    }

    public JSONObject getTradeFee() {
        return this.client.executeSync(this.api.getTradeFee());
    }
}
