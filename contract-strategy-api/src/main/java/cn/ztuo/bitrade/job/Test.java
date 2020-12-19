package cn.ztuo.bitrade.job;

import cn.ztuo.bitrade.exchanges.okex.config.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;
import cn.ztuo.bitrade.exchanges.okex.service.swap.impl.*;

public class Test {
    public static void main(final String[] args) {
        final APIConfiguration config = new APIConfiguration();
        config.setEndpoint("https://www.okex.com/");
        config.setApiKey("");
        config.setSecretKey("");
        config.setPassphrase("");
        config.setPrint(false);
        config.setI18n(I18nEnum.ENGLISH);
        final SwapMarketAPIServiceImpl imp = new SwapMarketAPIServiceImpl(config);
        System.out.println(imp.getContractsApi());
    }
}
