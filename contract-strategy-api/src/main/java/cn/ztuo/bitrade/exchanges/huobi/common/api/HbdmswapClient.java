package cn.ztuo.bitrade.exchanges.huobi.common.api;

import org.apache.http.*;

import java.io.*;

import org.slf4j.*;

public class HbdmswapClient {
    private static Logger logger;

    public static void main(final String[] args) throws HttpException, IOException {
        final String api_key = "4dc9f690-edrfhh5h53-22b2f7c0-4c69b";
        final String secret_key = "784c3afa-ae3c5371-d441ac30-d996d";
        final String url_prex = "https://api.btcgateway.pro";
        final IHbdmswapRestApi futureGetV1 = (IHbdmswapRestApi) new HbdmswapRestApiV1(url_prex);
        final IHbdmswapRestApi futurePostV1 = (IHbdmswapRestApi) new HbdmswapRestApiV1(url_prex, api_key, secret_key);
        final String contractInfo = futureGetV1.futureContractInfo("ETH-USD");
        HbdmswapClient.logger.info("\u83b7\u53d6\u5408\u7ea6\u4fe1\u606f" + contractInfo);
    }

    static {
        HbdmswapClient.logger = LoggerFactory.getLogger((Class) HbdmswapClient.class);
    }
}
