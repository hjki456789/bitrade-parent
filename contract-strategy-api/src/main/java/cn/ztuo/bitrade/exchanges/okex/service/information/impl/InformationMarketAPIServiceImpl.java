package cn.ztuo.bitrade.exchanges.okex.service.information.impl;

import cn.ztuo.bitrade.exchanges.okex.service.information.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import com.alibaba.fastjson.*;

public class InformationMarketAPIServiceImpl implements InformationMarketAPIService {
    private APIClient client;
    private InformationMarketAPI api;

    public InformationMarketAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(InformationMarketAPI.class);
    }

    public JSONArray getLongShortRatio(final String currency, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getLongShortRatio(currency, start, end, granularity));
    }

    public JSONArray getVolume(final String currency, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getVolume(currency, start, end, granularity));
    }

    public JSONArray getTaker(final String currency, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getTaker(currency, start, end, granularity));
    }

    public JSONArray getSentiment(final String currency, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getSentiment(currency, start, end, granularity));
    }

    public JSONArray getMargin(final String currency, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getMargin(currency, start, end, granularity));
    }
}
