package cn.ztuo.bitrade.exchanges.okex.service.option;

import com.alibaba.fastjson.*;

public interface OptionMarketAPIService {
    JSONObject getDepthData(final String p0, final String p1);

    JSONArray getTradeList(final String p0, final String p1, final String p2, final String p3);

    JSONArray getInstruments(final String p0, final String p1, final String p2);

    JSONArray getAllSummary(final String p0, final String p1);

    JSONObject getDetailPrice(final String p0, final String p1);

    JSONArray getCandles(final String p0, final String p1, final String p2, final String p3);

    JSONObject getTicker(final String p0);

    JSONArray getUnderlying();

    JSONArray getHistorySettlement(final String p0);
}
