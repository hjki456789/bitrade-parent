package cn.ztuo.bitrade.exchanges.okex.service.spot;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;

public interface MarginOrderAPIService {
    OrderResult addOrder(final PlaceOrderParam p0);

    Map<String, List<OrderResult>> addOrders(final List<PlaceOrderParam> p0);

    OrderResult cancleOrderByOrderId(final PlaceOrderParam p0, final String p1);

    OrderResult cancleOrdersByOrderId(final PlaceOrderParam p0, final String p1);

    OrderResult cancleOrdersByClientOid(final PlaceOrderParam p0, final String p1);

    Map<String, JSONObject> cancleOrders(final List<OrderParamDto> p0);

    Map<String, Object> cancleOrders_post(final List<OrderParamDto> p0);

    OrderInfo getOrderByProductIdAndOrderId(final String p0, final String p1);

    OrderInfo getOrderByClientOid(final String p0, final String p1);

    List<OrderInfo> getOrders(final String p0, final String p1, final String p2, final String p3, final String p4);

    List<PendingOrdersInfo> getPendingOrders(final String p0, final String p1, final String p2, final String p3);

    List<MarginFills> getFills(final String p0, final String p1, final String p2, final String p3, final String p4);
}
