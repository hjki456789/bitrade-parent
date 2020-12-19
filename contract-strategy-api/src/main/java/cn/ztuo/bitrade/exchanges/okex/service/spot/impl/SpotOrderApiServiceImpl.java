package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import cn.ztuo.bitrade.exchanges.okex.service.spot.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;

public class SpotOrderApiServiceImpl implements SpotOrderAPIServive {
    private final APIClient client;
    private final SpotOrderAPI spotOrderAPI;

    public SpotOrderApiServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.spotOrderAPI = this.client.createService(SpotOrderAPI.class);
    }

    public OrderResult addOrder(final PlaceOrderParam order) {
        return this.client.executeSync(this.spotOrderAPI.addOrder(order));
    }

    public Map<String, List<OrderResult>> addOrders(final List<PlaceOrderParam> order) {
        return this.client.executeSync(this.spotOrderAPI.addOrders(order));
    }

    public OrderResult cancleOrderByOrderId(final PlaceOrderParam order, final String order_id) {
        return this.client.executeSync(this.spotOrderAPI.cancleOrderByOrderId(order_id, order));
    }

    public OrderResult cancleOrderByOrderId_post(final PlaceOrderParam order, final String order_id) {
        return this.client.executeSync(this.spotOrderAPI.cancleOrderByOrderId_1(order_id, order));
    }

    public Map<String, Object> batchCancleOrders_2(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.spotOrderAPI.batchCancleOrders_2(cancleOrders));
    }

    public Map<String, Object> batch_orderCle(final List<OrderParamDto> orderParamDto) {
        return this.client.executeSync(this.spotOrderAPI.batch_orderCle(orderParamDto));
    }

    public OrderResult cancleOrderByClientOid(final PlaceOrderParam order, final String client_oid) {
        return this.client.executeSync(this.spotOrderAPI.cancleOrderByOrderId(client_oid, order));
    }

    public Map<String, BatchOrdersResult> cancleOrders(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.spotOrderAPI.batchCancleOrders(cancleOrders));
    }

    public Map<String, Object> cancleOrders_post(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.spotOrderAPI.batchCancleOrders_1(cancleOrders));
    }

    public OrderInfo getOrderByOrderId(final String instrument_id, final String order_id) {
        return this.client.executeSync(this.spotOrderAPI.getOrderByOrderId(order_id, instrument_id));
    }

    public OrderInfo getOrderByClientOid(final String instrument_id, final String client_oid) {
        return this.client.executeSync(this.spotOrderAPI.getOrderByClientOid(client_oid, instrument_id));
    }

    public List<OrderInfo> getOrders(final String instrument_id, final String state, final String after, final String before, final String limit) {
        return this.client.executeSync(this.spotOrderAPI.getOrders(instrument_id, state, after, before, limit));
    }

    public List<PendingOrdersInfo> getPendingOrders(final String before, final String after, final String limit, final String instrument_id) {
        return this.client.executeSync(this.spotOrderAPI.getPendingOrders(before, after, limit, instrument_id));
    }

    public List<Fills> getFills(final String order_id, final String instrument_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.spotOrderAPI.getFills(order_id, instrument_id, before, after, limit));
    }

    public OrderAlgoResult addorder_algo(final OrderAlgoParam order) {
        return this.client.executeSync(this.spotOrderAPI.addorder_algo(order));
    }

    public CancelAlgoResult cancelOrder_algo(final CancelAlgoParam cancelAlgoParam) {
        return this.client.executeSync(this.spotOrderAPI.cancelOrder_algo(cancelAlgoParam));
    }

    public String getAlgoOrder(final String instrument_id, final String order_type, final String status, final String algo_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.spotOrderAPI.getAlgoOrder(instrument_id, order_type, status, algo_id, before, after, limit));
    }
}
