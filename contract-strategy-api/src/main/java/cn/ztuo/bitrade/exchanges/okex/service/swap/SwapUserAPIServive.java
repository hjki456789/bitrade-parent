package cn.ztuo.bitrade.exchanges.okex.service.swap;

import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;

public interface SwapUserAPIServive {
    String getPositions();

    String getPosition(final String p0);

    String getAccounts();

    String selectAccount(final String p0);

    String selectContractSettings(final String p0);

    String updateLevelRate(final String p0, final LevelRateParam p1);

    String selectOrders(final String p0, final String p1, final String p2, final String p3, final String p4);

    String selectOrderByOrderId(final String p0, final String p1);

    String selectOrderByClientOid(final String p0, final String p1);

    String selectDealDetail(final String p0, final String p1, final String p2, final String p3, final String p4);

    String getLedger(final String p0, final String p1, final String p2, final String p3, final String p4);

    String getHolds(final String p0);

    String getTradeFee();
}
