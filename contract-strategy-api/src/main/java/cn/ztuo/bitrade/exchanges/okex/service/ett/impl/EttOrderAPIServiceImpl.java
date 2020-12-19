package cn.ztuo.bitrade.exchanges.okex.service.ett.impl;

import cn.ztuo.bitrade.exchanges.okex.service.ett.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.math.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.param.*;
import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

public class EttOrderAPIServiceImpl implements EttOrderAPIService {
    private final APIClient client;
    private final EttOrderAPI api;

    public EttOrderAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(EttOrderAPI.class);
    }

    @Override
    public EttCreateOrderResult createOrder(final String ett, final Integer type, final BigDecimal amount, final BigDecimal size, final String clientOid) {
        final EttCreateOrderParam param = new EttCreateOrderParam();
        param.setEtt(ett);
        param.setType(type);
        param.setAmount(amount);
        param.setSize(size);
        param.setClientOid(clientOid);
        return this.client.executeSync(this.api.createOrder(param));
    }

    @Override
    public EttCancelOrderResult cancelOrder(final String order_id) {
        return this.client.executeSync(this.api.cancelOrder(order_id));
    }

    @Override
    public CursorPager<EttOrder> getOrder(final String ett, final Integer type, final Integer status, final String before, final String after, final int limit) {
        return this.client.executeSyncCursorPager(this.api.getOrder(ett, type, status, before, after, limit));
    }

    @Override
    public EttOrder getOrder(final String order_id) {
        return this.client.executeSync(this.api.getOrder(order_id));
    }
}
