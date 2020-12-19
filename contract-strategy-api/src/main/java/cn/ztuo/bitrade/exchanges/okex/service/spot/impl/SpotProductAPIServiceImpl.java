package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import cn.ztuo.bitrade.exchanges.okex.service.spot.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import com.alibaba.fastjson.*;

public class SpotProductAPIServiceImpl implements SpotProductAPIService {
    private final APIClient client;
    private final SpotProductAPI spotProductAPI;

    public SpotProductAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.spotProductAPI = this.client.createService(SpotProductAPI.class);
    }

    public Ticker getTickerByProductId(final String instrument_id) {
        return this.client.executeSync(this.spotProductAPI.getTickerByProductId(instrument_id));
    }

    public List<Ticker> getTickers1() {
        return this.client.executeSync(this.spotProductAPI.getTickers1());
    }

    public String getTickers() {
        return this.client.executeSync(this.spotProductAPI.getTickers());
    }

    public Book bookProductsByProductId(final String instrument_id, final String size, final String depth) {
        return this.client.executeSync(this.spotProductAPI.bookProductsByProductId(instrument_id, size, depth));
    }

    public List<Product> getProducts() {
        return this.client.executeSync(this.spotProductAPI.getProducts());
    }

    public List<Trade> getTrades(final String instrument_id, final String limit) {
        return this.client.executeSync(this.spotProductAPI.getTrades(instrument_id, limit));
    }

    public JSONArray getHistoryCandles(final String instrument_id, final String granularity, final String start, final String end) {
        return this.client.executeSync(this.spotProductAPI.getHistoryCandles(instrument_id, granularity, start, end));
    }

    public JSONArray getCandles(final String instrument_id, final String granularity, final String start, final String end) {
        return this.client.executeSync(this.spotProductAPI.getCandles(instrument_id, granularity, start, end));
    }
}
