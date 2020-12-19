package cn.ztuo.bitrade.exchanges.okex.service.futures.impl;

import cn.ztuo.bitrade.exchanges.okex.service.futures.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;
import com.alibaba.fastjson.*;

public class FuturesMarketAPIServiceImpl implements FuturesMarketAPIService {
    private APIClient client;
    private FuturesMarketAPI api;

    public FuturesMarketAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(FuturesMarketAPI.class);
    }

    @Override
    public List<Instruments> getInstruments() {
        return this.client.executeSync(this.api.getInstruments());
    }

    @Override
    public List<Currencies> getCurrencies() {
        return this.client.executeSync(this.api.getCurrencies());
    }

    @Override
    public Book getInstrumentBook(final String instrument_id, final String size, final String depth) {
        return this.client.executeSync(this.api.getInstrumentBook(instrument_id, size, depth));
    }

    @Override
    public Ticker getInstrumentTicker(final String instrument_id) {
        return this.client.executeSync(this.api.getInstrumentTicker(instrument_id));
    }

    @Override
    public List<Ticker> getAllInstrumentTicker() {
        return this.client.executeSync(this.api.getAllInstrumentTicker());
    }

    @Override
    public List<Trades> getInstrumentTrades(final String instrument_id, final String after, final String before, final String limit) {
        return this.client.executeSync(this.api.getInstrumentTrades(instrument_id, after, before, limit));
    }

    @Override
    public JSONArray getInstrumentCandles(final String instrument_id, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getInstrumentCandles(instrument_id, start, end, granularity));
    }

    @Override
    public JSONArray getHistoryCandels(final String instrument_id, final String start, final String end, final String granularity, final String limit) {
        return this.client.executeSync(this.api.getHistoryCandels(instrument_id, start, end, granularity, limit));
    }

    @Override
    public Index getInstrumentIndex(final String instrument_id) {
        return this.client.executeSync(this.api.getInstrumentIndex(instrument_id));
    }

    @Override
    public ExchangeRate getExchangeRate() {
        return this.client.executeSync(this.api.getExchangeRate());
    }

    @Override
    public EstimatedPrice getInstrumentEstimatedPrice(final String instrument_id) {
        return this.client.executeSync(this.api.getInstrumentEstimatedPrice(instrument_id));
    }

    @Override
    public Holds getInstrumentHolds(final String instrument_id) {
        return this.client.executeSync(this.api.getInstrumentHolds(instrument_id));
    }

    @Override
    public PriceLimit getInstrumentPriceLimit(final String instrument_id) {
        return this.client.executeSync(this.api.getInstrumentPriceLimit(instrument_id));
    }

    @Override
    public List<Liquidation> getInstrumentLiquidation(final String instrument_id, final String status, final String from, final String to, final String limit) {
        return this.client.executeSync(this.api.getInstrumentLiquidation(instrument_id, status, from, to, limit));
    }

    @Override
    public JSONObject getMarkPrice(final String instrumentId) {
        return this.client.executeSync(this.api.getMarkPrice(instrumentId));
    }

    @Override
    public JSONArray getSettlementHistory(final String instrument_id, final String underlying, final String start, final String limit, final String end) {
        return this.client.executeSync(this.api.getSettlementHistory(instrument_id, underlying, start, limit, end));
    }
}
