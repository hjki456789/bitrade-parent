package cn.ztuo.bitrade.exchanges.okex.service.option.impl;

import cn.ztuo.bitrade.exchanges.okex.service.option.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import com.alibaba.fastjson.*;

public class OptionMarketAPIServiceImpl implements OptionMarketAPIService {
    private APIClient client;
    private OptionMarketAPI api;

    public OptionMarketAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(OptionMarketAPI.class);
    }

    public JSONObject getDepthData(final String instrument_id, final String size) {
        return this.client.executeSync(this.api.getDepthData(instrument_id, size));
    }

    public JSONArray getInstruments(final String underlying, final String delivery, final String instrument_id) {
        return this.client.executeSync(this.api.getInstruments(underlying, delivery, instrument_id));
    }

    public JSONArray getTradeList(final String instrument_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getTradeList(instrument_id, before, after, limit));
    }

    public JSONArray getAllSummary(final String underlying, final String delivery) {
        return this.client.executeSync(this.api.getAllSummary(underlying, delivery));
    }

    public JSONObject getDetailPrice(final String underlying, final String instrument_id) {
        return this.client.executeSync(this.api.getDetailPrice(underlying, instrument_id));
    }

    public JSONArray getCandles(final String instrument_id, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getCandles(instrument_id, start, end, granularity));
    }

    public JSONObject getTicker(final String instrument_id) {
        return this.client.executeSync(this.api.getTicker(instrument_id));
    }

    public JSONArray getUnderlying() {
        return this.client.executeSync(this.api.getUnderlying());
    }

    public JSONArray getHistorySettlement(final String underlying) {
        return this.client.executeSync(this.api.getHistorySettlement(underlying));
    }
}
