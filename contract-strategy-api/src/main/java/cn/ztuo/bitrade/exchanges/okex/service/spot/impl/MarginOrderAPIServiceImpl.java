package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import cn.ztuo.bitrade.exchanges.okex.service.spot.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;

public class MarginOrderAPIServiceImpl implements MarginOrderAPIService {
    private final APIClient client;
    private final MarginOrderAPI marginOrderAPI;

    public MarginOrderAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.marginOrderAPI = this.client.createService(MarginOrderAPI.class);
    }

    public OrderResult addOrder(final PlaceOrderParam order) {
        return this.client.executeSync(this.marginOrderAPI.addOrder(order));
    }

    public Map<String, List<OrderResult>> addOrders(final List<PlaceOrderParam> order) {
        return this.client.executeSync(this.marginOrderAPI.addOrders(order));
    }

    public OrderResult cancleOrderByOrderId(final PlaceOrderParam order, final String order_id) {
        return this.client.executeSync(this.marginOrderAPI.cancleOrdersByProductIdAndOrderId(order_id, order));
    }

    public OrderResult cancleOrdersByOrderId(final PlaceOrderParam order, final String order_id) {
        return this.client.executeSync(this.marginOrderAPI.cancleOrdersByOrderId(order_id, order));
    }

    public OrderResult cancleOrdersByClientOid(final PlaceOrderParam order, final String client_oid) {
        return this.client.executeSync(this.marginOrderAPI.cancleOrdersByClientOid(client_oid, order));
    }

    public Map<String, JSONObject> cancleOrders(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.marginOrderAPI.batchCancleOrders(cancleOrders));
    }

    public Map<String, Object> cancleOrders_post(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.marginOrderAPI.batchCancleOrders_1(cancleOrders));
    }

    public OrderInfo getOrderByProductIdAndOrderId(final String instrument_id, final String order_id) {
        return this.client.executeSync(this.marginOrderAPI.getOrderByProductIdAndOrderId(order_id, instrument_id));
    }

    public OrderInfo getOrderByClientOid(final String client_oid, final String instrument_id) {
        return this.client.executeSync(this.marginOrderAPI.getOrderByClientOid(client_oid, instrument_id));
    }

    public List<OrderInfo> getOrders(final String instrument_id, final String state, final String after, final String before, final String limit) {
        return this.client.executeSync(this.marginOrderAPI.getOrders(instrument_id, state, after, before, limit));
    }

    public List<PendingOrdersInfo> getPendingOrders(final String before, final String after, final String limit, final String instrument_id) {
        return this.client.executeSync(this.marginOrderAPI.getPendingOrders(before, after, limit, instrument_id));
    }

    public List<MarginFills> getFills(final String order_id, final String instrument_id, final String after, final String before, final String limit) {
        return this.client.executeSync(this.marginOrderAPI.getFills(order_id, instrument_id, after, before, limit));
    }
}
