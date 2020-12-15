package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.exchances.impl.huobi.*;
import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.exchances.*;
import org.springframework.scheduling.annotation.*;

@Component
public class CoinRateJob
{
    @Autowired
    private CoinService coinService;

    @Scheduled(fixedRate = 5000L)
    public void updateCoinRate() {
         List<Coin> coins = this.coinService.findAll();
        for ( Coin coin : coins) {
             String name = coin.getName();
             String symbol = name.toLowerCase() + "usdt";
             BaseExchangeService apiService = new HuoBiApi("https://api.huobi.de.com");
             BigDecimal price = apiService.getSymbolPrice(symbol);
            if (price.compareTo(BigDecimal.ZERO) > 0) {
                this.coinService.updateCoinUsdRate(name, price);
            }
        }
    }

}
