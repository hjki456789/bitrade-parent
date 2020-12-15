package cn.ztuo.bitrade.service;


import cn.ztuo.bitrade.entity.ContractCoin;
import cn.ztuo.bitrade.entity.KLine;
import cn.ztuo.bitrade.entity.ExchangeTrade;
import cn.ztuo.bitrade.entity.TradePlateItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class MarketService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ExchangeOrderService exchangeOrderService;
    @Autowired
    private ContractCoinService contractCoinService;


    public List<KLine> findAllKLine(final String symbol, final String peroid, final String source) {
        final Sort sort = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "time") });
        final Query query = new Query().with(sort).limit(1000);
        String collectionName = "exchange_kline_" + symbol + "_" + peroid;
        if (StringUtils.isNotEmpty(source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        return (List<KLine>)this.mongoTemplate.find(query, (Class)KLine.class, collectionName);
    }

    public List<KLine> findAllKLine(final String symbol, final long fromTime, final long toTime, final String period, final String source) {
        final Criteria criteria = Criteria.where("time").gte((Object)fromTime).andOperator(new Criteria[] { Criteria.where("time").lte((Object)toTime) });
        final Sort sort = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.ASC, "time") });
        final Query query = new Query(criteria).with(sort);
        String collectionName = "exchange_kline_" + symbol + "_" + period;
        if (StringUtils.isNotEmpty(source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        final List<KLine> kLines = (List<KLine>)this.mongoTemplate.find(query, (Class)KLine.class, collectionName);
        return kLines;
    }

    public List<KLine> findAllKLine(String symbol,String peroid){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"time"));
        Query query = new Query().with(sort).limit(1000);

        return mongoTemplate.find(query,KLine.class,"exchange_kline_"+symbol+"_"+peroid);
    }

    public List<KLine> findAllKLine(String symbol,long fromTime,long toTime,String period){
        Criteria criteria = Criteria.where("time").gte(fromTime).andOperator(Criteria.where("time").lte(toTime));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"time"));
        Query query = new Query(criteria).with(sort).limit(2000);
        List<KLine> kLines = mongoTemplate.find(query,KLine.class,"exchange_kline_"+symbol+"_"+ period);
        return kLines;
    }

    public ExchangeTrade findFirstTrade(String symbol,long fromTime,long toTime){
        Criteria criteria = Criteria.where("time").gte(fromTime).andOperator(Criteria.where("time").lte(toTime));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"time"));
        Query query = new Query(criteria).with(sort);
        return mongoTemplate.findOne(query,ExchangeTrade.class,"exchange_trade_"+symbol);
    }

    public ExchangeTrade findLastTrade(String symbol,long fromTime,long toTime){
        Criteria criteria = Criteria.where("time").gte(fromTime).andOperator(Criteria.where("time").lte(toTime));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"time"));
        Query query = new Query(criteria).with(sort);
        return mongoTemplate.findOne(query,ExchangeTrade.class,"exchange_trade_"+symbol);
    }
    public ExchangeTrade findFirstTrade(final String symbol, final long fromTime, final long toTime, final String source) {
        final Criteria criteria = Criteria.where("time").gte((Object)fromTime).andOperator(new Criteria[] { Criteria.where("time").lte((Object)toTime) });
        final Sort sort = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.ASC, "time") });
        final Query query = new Query(criteria).with(sort);
        String collectionName = "exchange_trade_" + symbol;
        if (StringUtils.isNotEmpty((CharSequence)source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        return (ExchangeTrade)this.mongoTemplate.findOne(query, (Class)ExchangeTrade.class, collectionName);
    }

    public ExchangeTrade findLastTrade(final String symbol, final long fromTime, final long toTime, final String source) {
        final Criteria criteria = Criteria.where("time").gte((Object)fromTime).andOperator(new Criteria[] { Criteria.where("time").lte((Object)toTime) });
        final Sort sort = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "time") });
        final Query query = new Query(criteria).with(sort);
        String collectionName = "exchange_trade_" + symbol;
        if (StringUtils.isNotEmpty((CharSequence)source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        return (ExchangeTrade)this.mongoTemplate.findOne(query, (Class)ExchangeTrade.class, collectionName);
    }

    public ExchangeTrade findTrade(String symbol, long fromTime, long toTime, Sort.Order order){
        Criteria criteria = Criteria.where("time").gte(fromTime).andOperator(Criteria.where("time").lte(toTime));
        Sort sort = Sort.by(order);
        Query query = new Query(criteria).with(sort);
        return mongoTemplate.findOne(query,ExchangeTrade.class,"exchange_trade_"+symbol);
    }
    public ExchangeTrade findTrade(final String symbol, final long fromTime, final long toTime, final Sort.Order order, final String source) {
        final Criteria criteria = Criteria.where("time").gte((Object)fromTime).andOperator(new Criteria[] { Criteria.where("time").lte((Object)toTime) });
        final Sort sort = Sort.by(order);
        final Query query = new Query(criteria).with(sort);
        String collectionName = "exchange_trade_" + symbol;
        if (StringUtils.isNotEmpty(source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        return (ExchangeTrade)this.mongoTemplate.findOne(query, (Class)ExchangeTrade.class, collectionName);
    }

    public List<ExchangeTrade> findTradeByTimeRange(String symbol, long timeStart, long timeEnd){
        Criteria criteria = Criteria.where("time").gte(timeStart).andOperator(Criteria.where("time").lt(timeEnd));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"time"));
        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query,ExchangeTrade.class,"exchange_trade_"+symbol);
    }

    public void saveKLine(String symbol,KLine kLine){
//        if(kLine.getVolume().compareTo(BigDecimal.ZERO) > 0) {
        mongoTemplate.insert(kLine, "exchange_kline_" + symbol + "_" + kLine.getPeriod());
//        }
    }

    /**
     * 查找某时间段内的交易量
     * @param symbol
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public BigDecimal findTradeVolume(String symbol, long timeStart, long timeEnd){
        Criteria criteria = Criteria.where("time").gt(timeStart)
                .andOperator(Criteria.where("time").lte(timeEnd));
                //.andOperator(Criteria.where("volume").gt(0));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"time"));
        Query query = new Query(criteria).with(sort);
        List<KLine> kLines =  mongoTemplate.find(query,KLine.class,"exchange_kline_"+symbol+"_1min");
        BigDecimal totalVolume = BigDecimal.ZERO;
        for(KLine kLine:kLines){
            totalVolume = totalVolume.add(kLine.getVolume());
        }
        return totalVolume;
    }

    public List<ExchangeTrade> findTradeByTimeRange(final String symbol, final long timeStart, final long timeEnd, final String source) {
        final Criteria criteria = Criteria.where("time").gte((Object)timeStart).andOperator(new Criteria[] { Criteria.where("time").lt((Object)timeEnd) });
        final Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "time"));
        final Query query = new Query(criteria).with(sort);
        String collectionName = "exchange_trade_" + symbol;
        if (StringUtils.isNotEmpty((CharSequence)source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        return (List<ExchangeTrade>)this.mongoTemplate.find(query, (Class)ExchangeTrade.class, collectionName);
    }

    public void saveKLine(final String symbol, final KLine kLine, final String source) {
        String collectionName = "exchange_kline_" + symbol + "_" + kLine.getPeriod();
        if (StringUtils.isNotEmpty((CharSequence)source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        final Criteria criteria = Criteria.where("time").is((Object)kLine.getTime());
        final Query query = new Query(criteria);
        final List<KLine> kLineList = (List<KLine>)this.mongoTemplate.find(query, (Class)KLine.class, collectionName);
        if (null != kLineList && kLineList.size() > 0) {
            if (kLine.getCount() > 0) {
                Update update = new Update().set("openPrice", kLine.getOpenPrice()).set("highestPrice", (Object)kLine.getHighestPrice()).set("lowestPrice", (Object)kLine.getLowestPrice()).set("closePrice", (Object)kLine.getClosePrice()).set("count", (Object)kLine.getCount()).set("volume", (Object)kLine.getVolume()).set("turnover", (Object)kLine.getTurnover());
                this.mongoTemplate.updateFirst(query, update, collectionName);
            }
        }
        else if (kLine.getVolume().compareTo(BigDecimal.ZERO) > 0) {
            this.mongoTemplate.insert((Object)kLine, collectionName);
        }
    }

    public BigDecimal findTradeVolume(final String symbol, final long timeStart, final long timeEnd, final String source) {
        final Criteria criteria = Criteria.where("time").gt((Object)timeStart).andOperator(new Criteria[] { Criteria.where("time").lte((Object)timeEnd) });
        final Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "time"));
        final Query query = new Query(criteria).with(sort);
        String collectionName = "exchange_kline_" + symbol + "_1min";
        if (StringUtils.isNotEmpty((CharSequence)source) && !source.equals("exchange")) {
            collectionName = source + "_" + collectionName;
        }
        final List<KLine> kLines = (List<KLine>)this.mongoTemplate.find(query, (Class)KLine.class, collectionName);
        BigDecimal totalVolume = BigDecimal.ZERO;
        for (final KLine kLine : kLines) {
            totalVolume = totalVolume.add(kLine.getVolume());
        }
        return totalVolume;
    }

    public Map<String, TradePlateItem> findFirstPlate(final String symbol) {
        return (Map<String, TradePlateItem>)this.exchangeOrderService.getFirstSellAndBuy(symbol);
    }

    public ContractCoin findContractCoinConfigBySymbol(final String symbol) {
        return this.contractCoinService.findBySymbol(symbol);
    }
}
