package cn.ztuo.bitrade.exchanges.okex.service.spot;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;

public interface SpotOrderAPIServive {
    OrderResult addOrder(final PlaceOrderParam p0);

    Map<String, List<OrderResult>> addOrders(final List<PlaceOrderParam> p0);

    OrderResult cancleOrderByOrderId(final PlaceOrderParam p0, final String p1);

    OrderResult cancleOrderByOrderId_post(final PlaceOrderParam p0, final String p1);

    OrderResult cancleOrderByClientOid(final PlaceOrderParam p0, final String p1);

    Map<String, BatchOrdersResult> cancleOrders(final List<OrderParamDto> p0);

    Map<String, Object> cancleOrders_post(final List<OrderParamDto> p0);

    Map<String, Object> batchCancleOrders_2(final List<OrderParamDto> p0);

    Map<String, Object> batch_orderCle(final List<OrderParamDto> p0);

    OrderInfo getOrderByOrderId(final String p0, final String p1);

    OrderInfo getOrderByClientOid(final String p0, final String p1);

    List<OrderInfo> getOrders(final String p0, final String p1, final String p2, final String p3, final String p4);

    List<PendingOrdersInfo> getPendingOrders(final String p0, final String p1, final String p2, final String p3);

    List<Fills> getFills(final String p0, final String p1, final String p2, final String p3, final String p4);

    OrderAlgoResult addorder_algo(@Body final OrderAlgoParam p0);

    CancelAlgoResult cancelOrder_algo(@Body final CancelAlgoParam p0);

    String getAlgoOrder(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6);
}
