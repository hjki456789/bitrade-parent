package cn.ztuo.bitrade.exchanges.okex.service.futures;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;
import com.alibaba.fastjson.*;

public interface FuturesMarketAPIService {
    List<Instruments> getInstruments();

    List<Currencies> getCurrencies();

    Book getInstrumentBook(final String p0, final String p1, final String p2);

    Ticker getInstrumentTicker(final String p0);

    List<Ticker> getAllInstrumentTicker();

    List<Trades> getInstrumentTrades(final String p0, final String p1, final String p2, final String p3);

    JSONArray getInstrumentCandles(final String p0, final String p1, final String p2, final String p3);

    JSONArray getHistoryCandels(final String p0, final String p1, final String p2, final String p3, final String p4);

    Index getInstrumentIndex(final String p0);

    ExchangeRate getExchangeRate();

    EstimatedPrice getInstrumentEstimatedPrice(final String p0);

    Holds getInstrumentHolds(final String p0);

    PriceLimit getInstrumentPriceLimit(final String p0);

    List<Liquidation> getInstrumentLiquidation(final String p0, final String p1, final String p2, final String p3, final String p4);

    JSONObject getMarkPrice(final String p0);

    JSONArray getSettlementHistory(final String p0, final String p1, final String p2, final String p3, final String p4);
}
