package cn.ztuo.bitrade.exchanges.okex.service.swap.impl;

import cn.ztuo.bitrade.exchanges.okex.service.swap.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;

public class SwapMarketAPIServiceImpl implements SwapMarketAPIService {
    private APIClient client;
    private SwapMarketAPI api;

    public SwapMarketAPIServiceImpl() {
    }

    public SwapMarketAPIServiceImpl(final String url) {
        final APIConfiguration config = new APIConfiguration();
        config.setEndpoint(url);
        config.setApiKey("");
        config.setSecretKey("");
        config.setPassphrase("");
        config.setPrint(false);
        config.setI18n(I18nEnum.ENGLISH);
        this.client = new APIClient(config);
        this.api = this.client.createService(SwapMarketAPI.class);
    }

    public SwapMarketAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(SwapMarketAPI.class);
    }

    public String getContractsApi() {
        return this.client.executeSync(this.api.getContractsApi());
    }

    public String getDepthApi(final String instrument_id, final String size, final String depth) {
        return this.client.executeSync(this.api.getDepthApi(instrument_id, size, depth));
    }

    public String getTickersApi() {
        return this.client.executeSync(this.api.getTickersApi());
    }

    public String getTickerApi(final String instrument_id) {
        return this.client.executeSync(this.api.getTickerApi(instrument_id));
    }

    public String getTradesApi(final String instrument_id, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getTradesApi(instrument_id, before, after, limit));
    }

    public String getCandlesApi(final String instrument_id, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getCandlesApi(instrument_id, start, end, granularity));
    }

    public String getHistoryCandlesApi(final String instrument_id, final String start, final String end, final String granularity) {
        return this.client.executeSync(this.api.getHistoryCandlesApi(instrument_id, start, end, granularity));
    }

    public String getIndexApi(final String instrument_id) {
        return this.client.executeSync(this.api.getIndexApi(instrument_id));
    }

    public String getRateApi() {
        return this.client.executeSync(this.api.getRateApi());
    }

    public String getOpenInterestApi(final String instrument_id) {
        return this.client.executeSync(this.api.getOpenInterestApi(instrument_id));
    }

    public String getPriceLimitApi(final String instrument_id) {
        return this.client.executeSync(this.api.getPriceLimitApi(instrument_id));
    }

    public String getLiquidationApi(final String instrument_id, final String status, final String from, final String to, final String limit) {
        return this.client.executeSync(this.api.getLiquidationApi(instrument_id, status, from, to, limit));
    }

    public String getFundingTimeApi(final String instrument_id) {
        return this.client.executeSync(this.api.getFundingTimeApi(instrument_id));
    }

    public String getHistoricalFundingRateApi(final String instrument_id, final String limit) {
        return this.client.executeSync(this.api.getHistoricalFundingRateApi(instrument_id, limit));
    }

    public String getMarkPriceApi(final String instrument_id) {
        return this.client.executeSync(this.api.getMarkPriceApi(instrument_id));
    }
}
