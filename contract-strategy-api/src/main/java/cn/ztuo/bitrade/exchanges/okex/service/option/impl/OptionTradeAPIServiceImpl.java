package cn.ztuo.bitrade.exchanges.okex.service.option.impl;

import cn.ztuo.bitrade.exchanges.okex.service.option.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.option.param.*;

public class OptionTradeAPIServiceImpl implements OptionTradeAPIService {
    private APIClient client;
    private OptionTradeAPI api;

    public OptionTradeAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(OptionTradeAPI.class);
    }

    public JSONObject getAccount(final String underLying) {
        return this.client.executeSync(this.api.getAccount(underLying));
    }

    public JSONObject amendOrder(final String underlying, final AmentDate amentDate) {
        return this.client.executeSync(this.api.amendOrder(underlying, amentDate));
    }

    public JSONObject amendBatchOrders(final String underlying, final AmendDateParam amendDateParam) {
        return this.client.executeSync(this.api.amendBatchOrders(underlying, amendDateParam));
    }

    public JSONObject cancelOrders(final String underlying, final String order_id) {
        return this.client.executeSync(this.api.cancelOrder(underlying, order_id));
    }

    public JSONObject cancelOrderByClientOid(final String underlying, final String client_oid) {
        return this.client.executeSync(this.api.cancelOrderByClientOid(underlying, client_oid));
    }

    public JSONObject cancelBatchOrders(final String underlying, final CancelOrders cancelOrders) {
        return this.client.executeSync(this.api.cancelBatchOrders(underlying, cancelOrders));
    }

    public JSONArray getFills(final String underlying, final String order_id, final String instrument_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getFills(underlying, order_id, instrument_id, before, after, limit));
    }

    public JSONArray getLedger(final String underlying, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getLedger(underlying, before, after, limit));
    }

    public JSONObject getOrder(final OrderParam orderParam) {
        return this.client.executeSync(this.api.getOrder(orderParam));
    }

    public JSONObject getOrders1(final OrderDataParam orderDataParam) {
        return this.client.executeSync(this.api.getOrders1(orderDataParam));
    }

    public JSONObject getOrderInfo(final String underlying, final String order_id) {
        return this.client.executeSync(this.api.getOrderInfo(underlying, order_id));
    }

    public JSONObject getOrderInfoByClientOid(final String underlying, final String client_oid) {
        return this.client.executeSync(this.api.getOrderInfoByClientOid(underlying, client_oid));
    }

    public JSONObject getOrderList(final String underlying, final String state, final String instrument_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getOrderList(underlying, state, instrument_id, before, after, limit));
    }

    public JSONObject getPosition(final String underlying, final String instrument_id) {
        return this.client.executeSync(this.api.getPosition(underlying, instrument_id));
    }

    public JSONObject getTradeFee() {
        return this.client.executeSync(this.api.getTradeFee());
    }
}
