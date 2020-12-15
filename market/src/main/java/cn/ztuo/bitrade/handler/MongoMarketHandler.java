package cn.ztuo.bitrade.handler;

import cn.ztuo.bitrade.consumer.ExchangeTradeConsumer;
import cn.ztuo.bitrade.entity.CoinThumb;
import cn.ztuo.bitrade.entity.ExchangeTrade;
import cn.ztuo.bitrade.entity.KLine;
import cn.ztuo.bitrade.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MongoMarketHandler implements MarketHandler {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    public void handleTrade(String symbol, ExchangeTrade exchangeTrade, CoinThumb thumb) {
        if(StringUtils.isEmpty(exchangeTrade.getSenderUuid()) || StringUtils.equals(SpringContextUtil.getBean(ExchangeTradeConsumer.class).getUuid(), exchangeTrade.getSenderUuid())) {
            mongoTemplate.insert(exchangeTrade, "exchange_trade_" + symbol);
        }
    }
    @Override
    public void handleKLine(String symbol,KLine kLine) {
        mongoTemplate.insert(kLine,"exchange_kline_"+symbol+"_"+kLine.getPeriod());
    }


    public void handleTrade(final String source, final String symbol, final ExchangeTrade exchangeTrade, final CoinThumb thumb) {
        if (StringUtils.isNotEmpty((CharSequence)source)) {
            this.mongoTemplate.insert((Object)exchangeTrade, source + "_exchange_trade_" + symbol);
        }
        else {
            this.mongoTemplate.insert((Object)exchangeTrade, "exchange_trade_" + symbol);
        }
    }

    public void handleKLine(final String source, final String symbol, final KLine kLine) {
        if (kLine.getOpenPrice().compareTo(BigDecimal.ZERO) > 0) {
            String collectionName = "";
            if (StringUtils.isNotEmpty((CharSequence)source)) {
                collectionName = source + "_exchange_kline_" + symbol + "_" + kLine.getPeriod();
            }
            else {
                collectionName = "exchange_kline_" + symbol + "_" + kLine.getPeriod();
            }
            final Criteria criteria = Criteria.where("time").is((Object)kLine.getTime());
            final Query query = new Query(criteria);
            final List<KLine> kLineList = (List<KLine>)this.mongoTemplate.find(query, (Class)KLine.class, collectionName);
            if (null != kLineList && kLineList.size() > 0) {
                if (kLine.getCount() > 0) {
                    final Update update = new Update().set("openPrice", (Object)kLine.getOpenPrice()).set("highestPrice", (Object)kLine.getHighestPrice()).set("lowestPrice", (Object)kLine.getLowestPrice()).set("closePrice", (Object)kLine.getClosePrice()).set("count", (Object)kLine.getCount()).set("volume", (Object)kLine.getVolume()).set("turnover", (Object)kLine.getTurnover());
                    this.mongoTemplate.updateFirst(query, update, collectionName);
                }
            }
            else {
                this.mongoTemplate.insert((Object)kLine, collectionName);
            }
        }
    }
}
