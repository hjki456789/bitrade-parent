package cn.ztuo.bitrade.exchanges.okex.service.spot;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import com.alibaba.fastjson.*;

public interface SpotProductAPIService {
    Ticker getTickerByProductId(final String p0);

    String getTickers();

    List<Ticker> getTickers1();

    Book bookProductsByProductId(final String p0, final String p1, final String p2);

    List<Product> getProducts();

    List<Trade> getTrades(final String p0, final String p1);

    JSONArray getCandles(final String p0, final String p1, final String p2, final String p3);

    JSONArray getHistoryCandles(final String p0, final String p1, final String p2, final String p3);
}
