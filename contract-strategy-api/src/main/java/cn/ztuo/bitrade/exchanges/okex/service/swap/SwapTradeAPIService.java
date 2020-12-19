package cn.ztuo.bitrade.exchanges.okex.service.swap;

import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;

public interface SwapTradeAPIService {
    Object order(final PpOrder p0);

    String orders(final PpOrders p0);

    String cancelOrderByOrderId(final String p0, final String p1);

    String cancelOrderByClientOid(final String p0, final String p1);

    String cancelOrders(final String p0, final PpCancelOrderVO p1);

    String amendOrder(final String p0, final AmendOrder p1);

    String amendOrderByClientOid(final String p0, final AmendOrder p1);

    String amendBatchOrderByOrderId(final String p0, final AmendOrderParam p1);

    String amendBatchOrderByClientOid(final String p0, final AmendOrderParam p1);

    String swapOrderAlgo(final SwapOrderParam p0);

    String cancelOrderAlgo(final CancelOrderAlgo p0);

    String getSwapOrders(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6);

    String closePosition(final ClosePosition p0);

    String CancelAll(final CancelAllParam p0);
}
