package cn.ztuo.bitrade.exchanges.okex.service.ett;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

public interface EttAccountAPIService {
    List<EttAccount> getAccount();

    EttAccount getAccount(final String p0);

    CursorPager<EttLedger> getLedger(final String p0, final String p1, final String p2, final int p3);
}
