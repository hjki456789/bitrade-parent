package cn.ztuo.bitrade.exchanges.okex.service.ett;

import java.math.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

public interface EttOrderAPIService {
    EttCreateOrderResult createOrder(final String p0, final Integer p1, final BigDecimal p2, final BigDecimal p3, final String p4);

    EttCancelOrderResult cancelOrder(final String p0);

    CursorPager<EttOrder> getOrder(final String p0, final Integer p1, final Integer p2, final String p3, final String p4, final int p5);

    EttOrder getOrder(final String p0);
}
