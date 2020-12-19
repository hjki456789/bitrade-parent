package cn.ztuo.bitrade.exchanges.okex.service.swap;

public interface SwapMarketAPIService {
    String getContractsApi();

    String getDepthApi(final String p0, final String p1, final String p2);

    String getTickersApi();

    String getTickerApi(final String p0);

    String getTradesApi(final String p0, final String p1, final String p2, final String p3);

    String getCandlesApi(final String p0, final String p1, final String p2, final String p3);

    String getHistoryCandlesApi(final String p0, final String p1, final String p2, final String p3);

    String getIndexApi(final String p0);

    String getRateApi();

    String getOpenInterestApi(final String p0);

    String getPriceLimitApi(final String p0);

    String getLiquidationApi(final String p0, final String p1, final String p2, final String p3, final String p4);

    String getFundingTimeApi(final String p0);

    String getHistoricalFundingRateApi(final String p0, final String p1);

    String getMarkPriceApi(final String p0);
}
