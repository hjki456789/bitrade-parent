package cn.ztuo.bitrade.exchanges.okex.service.futures.impl;

import cn.ztuo.bitrade.exchanges.okex.service.futures.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.utils.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.futures.param.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;

public class FuturesTradeAPIServiceImpl implements FuturesTradeAPIService {
    private APIClient client;
    private FuturesTradeAPI api;

    public FuturesTradeAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(FuturesTradeAPI.class);
    }

    @Override
    public JSONObject getPositions() {
        return this.client.executeSync(this.api.getPositions());
    }

    @Override
    public JSONObject getInstrumentPosition(final String instrument_id) {
        return this.client.executeSync(this.api.getInstrumentPosition(instrument_id));
    }

    @Override
    public JSONObject getAccounts() {
        return this.client.executeSync(this.api.getAccounts());
    }

    @Override
    public JSONObject getAccountsByCurrency(final String underlying) {
        return this.client.executeSync(this.api.getAccountsByCurrency(underlying));
    }

    @Override
    public JSONArray getAccountsLedgerByCurrency(final String underlying, final String after, final String before, final String limit, final String type) {
        return this.client.executeSync(this.api.getAccountsLedgerByCurrency(underlying, after, before, limit, type));
    }

    @Override
    public JSONObject getAccountsHoldsByInstrumentId(final String instrumentId) {
        return this.client.executeSync(this.api.getAccountsHoldsByInstrumentId(instrumentId));
    }

    @Override
    public OrderResult order(final Order order) {
        return this.client.executeSync(this.api.order(JsonUtils.convertObject((Object) order, (Class) Order.class)));
    }

    @Override
    public JSONObject orders(final Orders orders) {
        final JSONObject params = new JSONObject();
        params.put("instrument_id", (Object) orders.getInstrument_id());
        params.put("orders_data", (Object) JsonUtils.convertList((List) orders.getOrders_data(), (Class) OrdersItem.class));
        System.out.println(params.toString());
        return this.client.executeSync(this.api.orders(params));
    }

    @Override
    public JSONObject cancelOrderByOrderId(final String instrument_id, final String order_id) {
        return this.client.executeSync(this.api.cancelOrderByOrderId(instrument_id, order_id));
    }

    @Override
    public JSONObject cancelOrderByClientOid(final String instrument_id, final String client_oid) {
        return this.client.executeSync(this.api.cancelOrderByClientOid(instrument_id, client_oid));
    }

    @Override
    public JSONObject cancelOrders(final String instrumentId, final CancelOrders cancelOrders) {
        return this.client.executeSync(this.api.cancelOrders(instrumentId, JsonUtils.convertObject((Object) cancelOrders, (Class) CancelOrders.class)));
    }

    @Override
    public JSONObject amendOrderByOrderId(final String instrument_id, final AmendOrder amendOrder) {
        return this.client.executeSync(this.api.amendOrderByOrderId(instrument_id, amendOrder));
    }

    @Override
    public JSONObject amendOrderByClientOId(final String instrument_id, final AmendOrder amendOrder) {
        return this.client.executeSync(this.api.amendOrderByClientOid(instrument_id, amendOrder));
    }

    @Override
    public JSONObject amendBatchOrdersByOrderId(final String instrument_id, final AmendDateParam amendOrder) {
        return this.client.executeSync(this.api.amendBatchOrdersByOrderId(instrument_id, amendOrder));
    }

    @Override
    public JSONObject amendBatchOrdersByClientOid(final String instrument_id, final AmendDateParam amendOrder) {
        return this.client.executeSync(this.api.amendBatchOrdersByClientOid(instrument_id, amendOrder));
    }

    @Override
    public JSONObject getOrders(final String instrument_id, final String state, final String after, final String before, final String limit) {
        return this.client.executeSync(this.api.getOrders(instrument_id, state, after, before, limit));
    }

    @Override
    public JSONObject getOrderByOrderId(final String instrumentId, final String orderId) {
        return this.client.executeSync(this.api.getOrderByOrderId(instrumentId, orderId));
    }

    @Override
    public JSONObject getOrderByClientOid(final String instrumentId, final String client_oid) {
        return this.client.executeSync(this.api.getOrderByClientOid(instrumentId, client_oid));
    }

    @Override
    public JSONArray getFills(final String instrument_id, final String order_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getFills(instrument_id, String.valueOf(order_id), before, after, limit));
    }

    @Override
    public JSONObject getInstrumentLeverRate(final String underlying) {
        return this.client.executeSync(this.api.getLeverRate(underlying));
    }

    @Override
    public JSONObject changeLeverageOnFixed(final String underlying, final String instrument_id, final String direction, final String leverage) {
        final JSONObject params = new JSONObject();
        params.put("instrument_id", (Object) instrument_id);
        params.put("direction", (Object) direction);
        params.put("leverage", (Object) leverage);
        return this.client.executeSync(this.api.changeLeverageOnFixed(underlying, params));
    }

    @Override
    public JSONObject changeLeverageOnCross(final String underlying, final String leverage) {
        final JSONObject params = new JSONObject();
        params.put("leverage", (Object) leverage);
        return this.client.executeSync(this.api.changeLeverageOnCross(underlying, params));
    }

    @Override
    public JSONObject closePositions(final ClosePositions closePositions) {
        return this.client.executeSync(this.api.closePositions(JsonUtils.convertObject((Object) closePositions, (Class) ClosePositions.class)));
    }

    @Override
    public JSONObject cancelAll(final CancelAll cancelAll) {
        return this.client.executeSync(this.api.cancelAll(JsonUtils.convertObject((Object) cancelAll, (Class) CancelAll.class)));
    }

    @Override
    public JSONObject changeMarginMode(final ChangeMarginMode changeMarginMode) {
        return this.client.executeSync(this.api.changeMarginMode(JsonUtils.convertObject((Object) changeMarginMode, (Class) ChangeMarginMode.class)));
    }

    @Override
    public JSONObject changeLiquiMode(final ChangeLiquiMode changeLiquiMode) {
        return this.client.executeSync(this.api.changeLiquiMode(JsonUtils.convertObject((Object) changeLiquiMode, (Class) ChangeLiquiMode.class)));
    }

    @Override
    public FuturesOrderResult futuresOrder(final FuturesOrderParam futuresOrderParam) {
        System.out.println(JsonUtils.convertObject((Object) futuresOrderParam, (Class) FuturesOrderParam.class));
        return this.client.executeSync(this.api.futuresOrder(futuresOrderParam));
    }

    @Override
    public CancelFuturesOrdeResult cancelFuturesOrder(final CancelFuturesOrder cancelFuturesOrder) {
        System.out.println(JsonUtils.convertObject((Object) cancelFuturesOrder, (Class) CancelFuturesOrder.class));
        return this.client.executeSync(this.api.cancelFuturesOrder(cancelFuturesOrder));
    }

    @Override
    public String findFuturesOrder(final String instrument_id, final String order_type, final String status, final String algo_id, final String after, final String before, final String limit) {
        return this.client.executeSync(this.api.findFuturesOrder(instrument_id, order_type, status, algo_id, after, before, limit));
    }

    @Override
    public JSONObject getTradeFee() {
        return this.client.executeSync(this.api.getTradeFee());
    }

    @Override
    public JSONObject modifyMargin(final ModifyMarginParam modifyMarginParam) {
        return this.client.executeSync(this.api.modifyMargin(modifyMarginParam));
    }

    @Override
    public JSONObject modifyFixedMargin(final ModifyFixedMargin modifyFixedMargin) {
        return this.client.executeSync(this.api.modifyFixedMargin(modifyFixedMargin));
    }

    @Override
    public Holds getHolds(final String instrument_id) {
        return this.client.executeSync(this.api.getHolds(instrument_id));
    }
}
