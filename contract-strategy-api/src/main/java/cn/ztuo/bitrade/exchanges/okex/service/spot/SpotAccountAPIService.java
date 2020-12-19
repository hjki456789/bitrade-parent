package cn.ztuo.bitrade.exchanges.okex.service.spot;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import com.alibaba.fastjson.*;

public interface SpotAccountAPIService {
    Map<String, Object> getMiningData();

    List<Account> getAccounts();

    JSONArray getLedgersByCurrency(final String p0, final String p1, final String p2, final String p3, final String p4);

    Account getAccountByCurrency(final String p0);

    JSONObject getTradeFee();
}
