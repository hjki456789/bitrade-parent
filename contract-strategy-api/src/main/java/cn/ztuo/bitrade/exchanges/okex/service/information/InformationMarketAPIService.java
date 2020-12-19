package cn.ztuo.bitrade.exchanges.okex.service.information;

import com.alibaba.fastjson.*;

public interface InformationMarketAPIService {
    JSONArray getLongShortRatio(final String p0, final String p1, final String p2, final String p3);

    JSONArray getVolume(final String p0, final String p1, final String p2, final String p3);

    JSONArray getTaker(final String p0, final String p1, final String p2, final String p3);

    JSONArray getSentiment(final String p0, final String p1, final String p2, final String p3);

    JSONArray getMargin(final String p0, final String p1, final String p2, final String p3);
}
