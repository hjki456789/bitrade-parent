package cn.ztuo.bitrade.exchanges.okex.service.ett;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.ett.result.*;

public interface EttProductAPIService {
    EttConstituentsResult getConstituents(final String p0);

    List<EttSettlementDefinePrice> getDefinePrice(final String p0);
}
