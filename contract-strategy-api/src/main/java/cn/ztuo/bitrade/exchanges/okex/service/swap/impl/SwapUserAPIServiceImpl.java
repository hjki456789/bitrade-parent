package cn.ztuo.bitrade.exchanges.okex.service.swap.impl;

import cn.ztuo.bitrade.exchanges.okex.service.swap.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;
import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;
import cn.ztuo.bitrade.exchanges.okex.utils.*;

public class SwapUserAPIServiceImpl implements SwapUserAPIServive {
    private APIClient client;
    private SwapUserAPI api;

    public SwapUserAPIServiceImpl() {
    }

    public SwapUserAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(SwapUserAPI.class);
    }

    public SwapUserAPIServiceImpl(final String url, final String apiKey, final String secretKey, final String passphrase) {
        final APIConfiguration config = new APIConfiguration();
        config.setEndpoint(url);
        config.setApiKey(apiKey);
        config.setSecretKey(secretKey);
        config.setPassphrase(passphrase);
        config.setPrint(false);
        config.setI18n(I18nEnum.ENGLISH);
        this.client = new APIClient(config);
        this.api = this.client.createService(SwapUserAPI.class);
    }

    public String getPositions() {
        return this.client.executeSync(this.api.getPositions());
    }

    public String getPosition(final String instrument_id) {
        return this.client.executeSync(this.api.getPosition(instrument_id));
    }

    public String getAccounts() {
        return this.client.executeSync(this.api.getAccounts());
    }

    public String selectAccount(final String instrument_id) {
        return this.client.executeSync(this.api.selectAccount(instrument_id));
    }

    public String selectContractSettings(final String instrument_id) {
        return this.client.executeSync(this.api.selectContractSettings(instrument_id));
    }

    public String updateLevelRate(final String instrument_id, final LevelRateParam levelRateParam) {
        return this.client.executeSync(this.api.updateLevelRate(instrument_id, JsonUtils.convertObject((Object) levelRateParam, (Class) LevelRateParam.class)));
    }

    public String selectOrders(final String instrument_id, final String state, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.selectOrders(instrument_id, state, before, after, limit));
    }

    public String selectOrderByOrderId(final String instrument_id, final String order_id) {
        return this.client.executeSync(this.api.selectOrderByOrderId(instrument_id, order_id));
    }

    public String selectOrderByClientOid(final String instrument_id, final String client_oid) {
        return this.client.executeSync(this.api.selectOrderByClientOid(instrument_id, client_oid));
    }

    public String selectDealDetail(final String instrument_id, final String order_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.selectDealDetail(instrument_id, order_id, before, after, limit));
    }

    public String getLedger(final String instrument_id, final String before, final String after, final String limit, final String type) {
        return this.client.executeSync(this.api.getLedger(instrument_id, before, after, limit, type));
    }

    public String getHolds(final String instrument_id) {
        return this.client.executeSync(this.api.getHolds(instrument_id));
    }

    public String getTradeFee() {
        return this.client.executeSync(this.api.getTradeFee());
    }
}
