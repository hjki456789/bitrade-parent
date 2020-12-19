package cn.ztuo.bitrade.exchanges.okex.service.swap.impl;

import cn.ztuo.bitrade.exchanges.okex.service.swap.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;
import cn.ztuo.bitrade.exchanges.okex.utils.*;
import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;

public class SwapTradeAPIServiceImpl implements SwapTradeAPIService {
    private APIClient client;
    private SwapTradeAPI api;
    private SwapTradeAPI swapTradeAPI;

    public SwapTradeAPIServiceImpl() {
    }

    public SwapTradeAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(SwapTradeAPI.class);
    }

    public SwapTradeAPIServiceImpl(final String url, final String apiKey, final String secretKey, final String passphrase) {
        final APIConfiguration config = new APIConfiguration();
        config.setEndpoint(url);
        config.setApiKey(apiKey);
        config.setSecretKey(secretKey);
        config.setPassphrase(passphrase);
        config.setPrint(false);
        config.setI18n(I18nEnum.ENGLISH);
        this.client = new APIClient(config);
        this.api = this.client.createService(SwapTradeAPI.class);
    }

    public String order(final PpOrder ppOrder) {
        return this.client.executeSync(this.api.order(ppOrder));
    }

    public String orders(final PpOrders ppOrders) {
        return this.client.executeSync(this.api.orders(JsonUtils.convertObject((Object) ppOrders, (Class) PpOrders.class)));
    }

    public String cancelOrderByOrderId(final String instrument_id, final String order_id) {
        return this.client.executeSync(this.api.cancelOrderByOrderId(instrument_id, order_id));
    }

    public String cancelOrderByClientOid(final String instrument_id, final String client_oid) {
        return this.client.executeSync(this.api.cancelOrderByClientOid(instrument_id, client_oid));
    }

    public String cancelOrders(final String instrumentId, final PpCancelOrderVO ppCancelOrderVO) {
        return this.client.executeSync(this.api.cancelOrders(instrumentId, JsonUtils.convertObject((Object) ppCancelOrderVO, (Class) PpCancelOrderVO.class)));
    }

    public String amendOrder(final String instrumentId, final AmendOrder amendOrder) {
        return this.client.executeSync(this.api.amendOrder(instrumentId, amendOrder));
    }

    public String amendOrderByClientOid(final String instrument_id, final AmendOrder amendOrder) {
        return this.client.executeSync(this.api.amendOrderByClientOid(instrument_id, amendOrder));
    }

    public String amendBatchOrderByOrderId(final String instrument_id, final AmendOrderParam amendOrder) {
        return this.client.executeSync(this.api.amendBatchOrderByOrderId(instrument_id, amendOrder));
    }

    public String amendBatchOrderByClientOid(final String instrument_id, final AmendOrderParam amendOrder) {
        return this.client.executeSync(this.api.amendBatchOrderByClientOid(instrument_id, amendOrder));
    }

    public String swapOrderAlgo(final SwapOrderParam swapOrderParam) {
        System.out.println("begin swapOrder-----");
        return this.client.executeSync(this.api.swapOrderAlgo(swapOrderParam));
    }

    public String cancelOrderAlgo(final CancelOrderAlgo cancelOrderAlgo) {
        System.out.println("canceling the algo order");
        return this.client.executeSync(this.api.cancelOrderAlgo(cancelOrderAlgo));
    }

    public String getSwapOrders(final String instrument_id, final String order_type, final String status, final String algo_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getSwapOrders(instrument_id, order_type, status, algo_id, before, after, limit));
    }

    public String closePosition(final ClosePosition closePosition) {
        return this.client.executeSync(this.api.closePosition(closePosition));
    }

    public String CancelAll(final CancelAllParam cancelAllParam) {
        return this.client.executeSync(this.api.CancelAll(cancelAllParam));
    }
}
