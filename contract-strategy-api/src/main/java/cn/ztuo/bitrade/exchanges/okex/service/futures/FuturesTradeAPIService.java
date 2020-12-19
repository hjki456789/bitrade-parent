package cn.ztuo.bitrade.exchanges.okex.service.futures;

import com.alibaba.fastjson.*;
import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;
import cn.ztuo.bitrade.exchanges.okex.bean.futures.param.*;

public interface FuturesTradeAPIService {
    JSONObject getPositions();

    JSONObject getInstrumentPosition(final String p0);

    JSONObject getAccounts();

    JSONObject getAccountsByCurrency(final String p0);

    JSONArray getAccountsLedgerByCurrency(final String p0, final String p1, final String p2, final String p3, final String p4);

    JSONObject getAccountsHoldsByInstrumentId(final String p0);

    OrderResult order(final Order p0);

    JSONObject orders(final Orders p0);

    JSONObject cancelOrderByOrderId(final String p0, final String p1);

    JSONObject cancelOrderByClientOid(final String p0, final String p1);

    JSONObject cancelOrders(final String p0, final CancelOrders p1);

    JSONObject amendOrderByOrderId(final String p0, final AmendOrder p1);

    JSONObject amendOrderByClientOId(final String p0, final AmendOrder p1);

    JSONObject amendBatchOrdersByOrderId(final String p0, final AmendDateParam p1);

    JSONObject amendBatchOrdersByClientOid(final String p0, final AmendDateParam p1);

    JSONObject getOrders(final String p0, final String p1, final String p2, final String p3, final String p4);

    JSONObject getOrderByOrderId(final String p0, final String p1);

    JSONObject getOrderByClientOid(final String p0, final String p1);

    JSONArray getFills(final String p0, final String p1, final String p2, final String p3, final String p4);

    JSONObject getInstrumentLeverRate(final String p0);

    JSONObject changeLeverageOnFixed(final String p0, final String p1, final String p2, final String p3);

    JSONObject changeLeverageOnCross(final String p0, final String p1);

    JSONObject closePositions(final ClosePositions p0);

    JSONObject cancelAll(final CancelAll p0);

    JSONObject changeMarginMode(final ChangeMarginMode p0);

    JSONObject changeLiquiMode(final ChangeLiquiMode p0);

    Holds getHolds(final String p0);

    FuturesOrderResult futuresOrder(@Body final FuturesOrderParam p0);

    CancelFuturesOrdeResult cancelFuturesOrder(@Body final CancelFuturesOrder p0);

    String findFuturesOrder(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6);

    JSONObject getTradeFee();

    JSONObject modifyMargin(final ModifyMarginParam p0);

    JSONObject modifyFixedMargin(final ModifyFixedMargin p0);
}
