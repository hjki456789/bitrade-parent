package cn.ztuo.bitrade.exchanges.okex.service.option;

import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.option.param.*;

public interface OptionTradeAPIService {
    JSONObject getAccount(final String p0);

    JSONObject amendOrder(final String p0, final AmentDate p1);

    JSONObject amendBatchOrders(final String p0, final AmendDateParam p1);

    JSONObject cancelOrders(final String p0, final String p1);

    JSONObject cancelOrderByClientOid(final String p0, final String p1);

    JSONObject cancelBatchOrders(final String p0, final CancelOrders p1);

    JSONArray getFills(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5);

    JSONArray getLedger(final String p0, final String p1, final String p2, final String p3);

    JSONObject getOrder(final OrderParam p0);

    JSONObject getOrders1(final OrderDataParam p0);

    JSONObject getOrderInfo(final String p0, final String p1);

    JSONObject getOrderInfoByClientOid(final String p0, final String p1);

    JSONObject getOrderList(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5);

    JSONObject getPosition(final String p0, final String p1);

    JSONObject getTradeFee();
}
