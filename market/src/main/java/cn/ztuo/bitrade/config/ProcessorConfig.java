package cn.ztuo.bitrade.config;

import cn.ztuo.bitrade.component.CoinExchangeRate;
import cn.ztuo.bitrade.entity.ContractCoin;
import cn.ztuo.bitrade.entity.ExchangeCoin;
import cn.ztuo.bitrade.handler.MarketHandler;
import cn.ztuo.bitrade.handler.MongoMarketHandler;
import cn.ztuo.bitrade.handler.NettyHandler;
import cn.ztuo.bitrade.handler.WebsocketMarketHandler;
import cn.ztuo.bitrade.processor.CoinProcessor;
import cn.ztuo.bitrade.processor.CoinProcessorFactory;
import cn.ztuo.bitrade.processor.ContractCoinProcessor;
import cn.ztuo.bitrade.processor.DefaultCoinProcessor;
import cn.ztuo.bitrade.service.ContractCoinService;
import cn.ztuo.bitrade.service.ExchangeCoinService;
import cn.ztuo.bitrade.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class ProcessorConfig {

    @Bean
    public CoinProcessorFactory processorFactory(MongoMarketHandler mongoMarketHandler,
                                                 WebsocketMarketHandler wsHandler,
                                                 NettyHandler nettyHandler,
                                                 MarketService marketService,
                                                 CoinExchangeRate exchangeRate,
                                                 ExchangeCoinService coinService,
                                                 ContractCoinService contractCoinService) {

        log.info("====initialized CoinProcessorFactory start==================================");

        CoinProcessorFactory factory = new CoinProcessorFactory();
        List<ExchangeCoin> coins = coinService.findAllEnabled();
        log.info("exchange-coin result:{}",coins);

        for (ExchangeCoin coin : coins) {
            CoinProcessor processor = new DefaultCoinProcessor(coin.getSymbol(), coin.getBaseSymbol());
            processor.addHandler(mongoMarketHandler);
            processor.addHandler(wsHandler);
            processor.addHandler(nettyHandler);
            processor.setMarketService(marketService);
            processor.setExchangeRate(exchangeRate);
            processor.setScale(coin.getCoinScale(),coin.getBaseCoinScale());
            factory.addProcessor(coin.getSymbol(), processor);
        }

        log.info("====initialized CoinProcessorFactory completed====");
        log.info("CoinProcessorFactory = ", factory);
        exchangeRate.setCoinProcessorFactory(factory);


        List<ContractCoin> contractCoins = (List<ContractCoin>)contractCoinService.findAllEnabled();
        for (ContractCoin coin2 : contractCoins) {
            CoinProcessor processor2 = (CoinProcessor)new ContractCoinProcessor(coin2.getSymbol(), coin2.getBaseSymbol());
            processor2.addHandler(mongoMarketHandler);
            processor2.addHandler(wsHandler);
            processor2.addHandler(nettyHandler);
            processor2.setMarketService(marketService);
            processor2.setExchangeRate(exchangeRate);
            processor2.setScale((int)coin2.getCoinSymbolPrecision(), (int)coin2.getCoinSymbolPrecision());
            factory.addProcessor("contract", coin2.getSymbol(), processor2);
        }
        return factory;
    }


}
