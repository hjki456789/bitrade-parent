package cn.ztuo.bitrade.processor;

import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.component.*;
import org.slf4j.*;
import java.math.*;
import java.util.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.handler.*;
import java.text.*;
import cn.ztuo.bitrade.entity.*;

public class ContractCoinProcessor implements CoinProcessor
{
    private Logger logger;
    private String symbol;
    private String baseCoin;
    private KLine currentKLine;
    private List<MarketHandler> handlers;
    private CoinThumb coinThumb;
    private MarketService service;
    private CoinExchangeRate coinExchangeRate;
    private Boolean isHalt;
    private int baseScale;
    private int coinScale;
    
    public ContractCoinProcessor(final String symbol, final String baseCoin) {
        this.logger = LoggerFactory.getLogger((Class)ContractCoinProcessor.class);
        this.isHalt = true;
        this.handlers = new ArrayList<MarketHandler>();
        this.createNewKLine();
        this.baseCoin = baseCoin;
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    @Override
    public void initializeThumb() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(13, 0);
        calendar.set(14, 0);
        final long nowTime = calendar.getTimeInMillis();
        calendar.set(12, 0);
        calendar.set(11, 0);
        final long firstTimeOfToday = calendar.getTimeInMillis();
        final String period = "1min";
        this.logger.info("initializeThumb from {} to {}", (Object)firstTimeOfToday, (Object)nowTime);
        final List<KLine> lines = (List<KLine>)this.service.findAllKLine(this.symbol, firstTimeOfToday, nowTime, period, "contract");
        synchronized (this.coinThumb = new CoinThumb()) {
            this.coinThumb.setSymbol(this.symbol);
            for (final KLine kline : lines) {
                if (kline.getOpenPrice().compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                if (this.coinThumb.getOpen().compareTo(BigDecimal.ZERO) == 0) {
                    this.coinThumb.setOpen(kline.getOpenPrice());
                }
                if (this.coinThumb.getHigh().compareTo(kline.getHighestPrice()) < 0) {
                    this.coinThumb.setHigh(kline.getHighestPrice());
                }
                if (kline.getLowestPrice().compareTo(BigDecimal.ZERO) > 0 && this.coinThumb.getLow().compareTo(kline.getLowestPrice()) > 0) {
                    this.coinThumb.setLow(kline.getLowestPrice());
                }
                if (kline.getClosePrice().compareTo(BigDecimal.ZERO) > 0) {
                    this.coinThumb.setClose(kline.getClosePrice());
                }
                this.coinThumb.setVolume(this.coinThumb.getVolume().add(kline.getVolume()));
                this.coinThumb.setTurnover(this.coinThumb.getTurnover().add(kline.getTurnover()));
            }
            this.coinThumb.setChange(this.coinThumb.getClose().subtract(this.coinThumb.getOpen()));
            if (this.coinThumb.getOpen().compareTo(BigDecimal.ZERO) > 0) {
                this.coinThumb.setChg(this.coinThumb.getChange().divide(this.coinThumb.getOpen(), 4, RoundingMode.UP));
            }
            this.coinThumb.setCloseStr(this.coinThumb.getClose().setScale(this.baseScale, RoundingMode.DOWN).toPlainString());
            try {
                this.coinThumb.setCnyPrice(this.coinThumb.getClose().multiply(this.coinThumb.getBaseUsdRate()).multiply(BigDecimal.valueOf(6.8)).setScale(2, 5).toString());
            }
            catch (Exception e) {
                this.logger.info("\u521d\u59cb\u5316\u4ef7\u683c\u4e3a\u7a7a");
            }
        }
    }
    
    public void createNewKLine() {
        synchronized (this.currentKLine = new KLine()) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(13, 0);
            calendar.set(14, 0);
            if (this.currentKLine.getOpenPrice().compareTo(BigDecimal.ZERO) == 0 && null != this.coinThumb) {
                this.currentKLine.setOpenPrice(this.coinThumb.getClose());
                this.currentKLine.setLowestPrice(this.coinThumb.getClose());
                this.currentKLine.setHighestPrice(this.coinThumb.getClose());
                this.currentKLine.setClosePrice(this.coinThumb.getClose());
            }
            this.currentKLine.setTime(calendar.getTimeInMillis());
            this.currentKLine.setPeriod("1min");
            this.currentKLine.setCount(0);
        }
        if (this.currentKLine.getOpenPrice().compareTo(BigDecimal.ZERO) > 0) {
            this.handleKLineStorage(this.currentKLine);
        }
    }
    
    @Override
    public void resetThumb() {
        this.logger.info("reset coinThumb");
        synchronized (this.coinThumb) {
            this.coinThumb.setOpen(BigDecimal.ZERO);
            this.coinThumb.setHigh(BigDecimal.ZERO);
            this.coinThumb.setLastDayClose(this.coinThumb.getClose());
            this.coinThumb.setLow(BigDecimal.ZERO);
            this.coinThumb.setChg(BigDecimal.ZERO);
            this.coinThumb.setChange(BigDecimal.ZERO);
        }
    }
    
    @Override
    public void setExchangeRate(final CoinExchangeRate coinExchangeRate) {
        this.coinExchangeRate = coinExchangeRate;
    }
    
    @Override
    public void update24HVolume(final long time) {
        if (this.coinThumb != null) {
            synchronized (this.coinThumb) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                calendar.add(11, -24);
                final long timeStart = calendar.getTimeInMillis();
                final BigDecimal volume = this.service.findTradeVolume(this.symbol, timeStart, time, "contract");
                this.coinThumb.setVolume(volume.setScale(4, RoundingMode.DOWN));
            }
        }
    }
    
    @Override
    public void initializeUsdRate() {
        this.logger.info("symbol = {} ,baseCoin = {}", (Object)this.symbol, (Object)this.baseCoin);
        final BigDecimal baseUsdRate = this.coinExchangeRate.getCoinLegalRate("USD", this.baseCoin);
        this.coinThumb.setBaseUsdRate(baseUsdRate);
        this.logger.info("setBaseUsdRate = ", (Object)baseUsdRate);
        final BigDecimal multiply = this.coinThumb.getClose().multiply(baseUsdRate);
        this.logger.info("setUsdRate = ", (Object)multiply);
        this.coinThumb.setUsdRate(multiply);
    }
    
    @Override
    public String getBaseCoin() {
        return this.baseCoin;
    }
    
    @Override
    public void setScale(final int coinScale, final int baseCoinScale) {
        this.coinScale = coinScale;
        this.baseScale = baseCoinScale;
    }
    
    @Override
    public void autoGenerate() {
        final DateFormat df = new SimpleDateFormat("HH:mm:ss");
        this.logger.info("auto generate 1min kline in {},data={}", (Object)df.format(new Date(this.currentKLine.getTime())), (Object)JSON.toJSONString((Object)this.currentKLine));
        if (this.coinThumb != null) {
            synchronized (this.currentKLine) {
                if (this.currentKLine.getOpenPrice().compareTo(BigDecimal.ZERO) == 0) {
                    this.currentKLine.setOpenPrice(this.coinThumb.getClose());
                    this.currentKLine.setLowestPrice(this.coinThumb.getClose());
                    this.currentKLine.setHighestPrice(this.coinThumb.getClose());
                    this.currentKLine.setClosePrice(this.coinThumb.getClose());
                }
                final Calendar calendar = Calendar.getInstance();
                calendar.set(13, 0);
                calendar.set(14, 0);
                calendar.add(12, -1);
                this.currentKLine.setTime(calendar.getTimeInMillis());
                for (final MarketHandler storage : this.handlers) {
                    if (storage instanceof MongoMarketHandler) {
                        storage.handleKLine("contract", this.symbol, this.currentKLine);
                    }
                }
                this.createNewKLine();
            }
        }
    }
    
    @Override
    public void setIsHalt(final boolean status) {
        this.isHalt = status;
    }
    
    @Override
    public void process(final List<ExchangeTrade> trades) {
        if (!this.isHalt) {
            if (trades == null || trades.size() == 0) {
                return;
            }
            for (final ExchangeTrade exchangeTrade : trades) {
                synchronized (this.currentKLine) {
                    this.processTrade(this.currentKLine, exchangeTrade);
                }
                this.logger.info("\u5904\u7406\u4eca\u65e5\u6982\u51b5\u4fe1\u606f");
                this.handleThumb(exchangeTrade);
                this.handleTradeStorage(exchangeTrade);
            }
        }
    }
    
    public void processTrade(final KLine kLine, final ExchangeTrade exchangeTrade) {
        if (kLine.getClosePrice().compareTo(BigDecimal.ZERO) == 0) {
            kLine.setOpenPrice(exchangeTrade.getPrice());
            kLine.setHighestPrice(exchangeTrade.getPrice());
            kLine.setLowestPrice(exchangeTrade.getPrice());
            kLine.setClosePrice(exchangeTrade.getPrice());
        }
        else {
            kLine.setHighestPrice(exchangeTrade.getPrice().max(kLine.getHighestPrice()));
            kLine.setLowestPrice(exchangeTrade.getPrice().min(kLine.getLowestPrice()));
            kLine.setClosePrice(exchangeTrade.getPrice());
        }
        kLine.setCount(kLine.getCount() + 1);
        kLine.setVolume(kLine.getVolume().add(exchangeTrade.getAmount()));
        final BigDecimal turnover = exchangeTrade.getPrice().multiply(exchangeTrade.getAmount());
        kLine.setTurnover(kLine.getTurnover().add(turnover));
    }
    
    public void handleTradeStorage(final ExchangeTrade exchangeTrade) {
        for (final MarketHandler storage : this.handlers) {
            exchangeTrade.setAmountStr(exchangeTrade.getAmount().setScale(this.coinScale, RoundingMode.DOWN).toPlainString());
            exchangeTrade.setPriceStr(exchangeTrade.getPrice().setScale(this.baseScale, RoundingMode.DOWN).toPlainString());
            storage.handleTrade("contract", this.symbol, exchangeTrade, this.coinThumb);
        }
    }
    
    public void handleKLineStorage(final KLine kLine) {
        for (final MarketHandler storage : this.handlers) {
            storage.handleKLine("contract", this.symbol, kLine);
        }
    }
    
    public void handleThumb(final ExchangeTrade exchangeTrade) {
        this.logger.info("handleThumb symbol = {}", (Object)this.symbol);
        synchronized (this.coinThumb) {
            if (this.coinThumb.getOpen().compareTo(BigDecimal.ZERO) == 0) {
                this.coinThumb.setOpen(exchangeTrade.getPrice());
            }
            this.coinThumb.setHigh(exchangeTrade.getPrice().max(this.coinThumb.getHigh()));
            if (this.coinThumb.getLow().compareTo(BigDecimal.ZERO) == 0) {
                this.coinThumb.setLow(exchangeTrade.getPrice());
            }
            else {
                this.coinThumb.setLow(exchangeTrade.getPrice().min(this.coinThumb.getLow()));
            }
            this.coinThumb.setClose(exchangeTrade.getPrice());
            this.coinThumb.setVolume(this.coinThumb.getVolume().add(exchangeTrade.getAmount()).setScale(4, RoundingMode.UP));
            final BigDecimal turnover = exchangeTrade.getPrice().multiply(exchangeTrade.getAmount()).setScale(4, RoundingMode.UP);
            this.coinThumb.setTurnover(this.coinThumb.getTurnover().add(turnover));
            final BigDecimal change = this.coinThumb.getClose().subtract(this.coinThumb.getOpen());
            this.coinThumb.setChange(change);
            if (this.coinThumb.getOpen().compareTo(BigDecimal.ZERO) > 0) {
                this.coinThumb.setChg(change.divide(this.coinThumb.getOpen(), 4, 0));
            }
            this.coinThumb.setBaseUsdRate(this.coinExchangeRate.getCoinLegalRate("USD", this.baseCoin));
            this.coinThumb.setUsdRate(exchangeTrade.getPrice().multiply(this.coinThumb.getBaseUsdRate()));
            this.coinThumb.setCloseStr(this.coinThumb.getClose().setScale(this.baseScale, RoundingMode.DOWN).toPlainString());
            try {
                this.coinThumb.setCnyPrice(this.coinThumb.getClose().multiply(this.coinThumb.getBaseUsdRate()).multiply(BigDecimal.valueOf(7L)).setScale(2, 5).toString());
            }
            catch (Exception e) {
                this.logger.info("\u521d\u59cb\u5316\u4ef7\u683c\u4e3a\u7a7a");
            }
            final ContractCoin contractCoin = this.service.findContractCoinConfigBySymbol(this.symbol);
            if (null != contractCoin) {
                BigDecimal buy = this.coinThumb.getClose().subtract(contractCoin.getFallInterval());
                buy = ((buy.compareTo(BigDecimal.ZERO) > 0) ? buy : BigDecimal.ZERO);
                buy = buy.setScale(this.baseScale, RoundingMode.HALF_UP);
                this.coinThumb.setBuy(buy);
                BigDecimal sell = this.coinThumb.getClose().add(contractCoin.getRiseInterval());
                sell = ((sell.compareTo(BigDecimal.ZERO) > 0) ? sell : BigDecimal.ZERO);
                sell = sell.setScale(this.baseScale, RoundingMode.HALF_UP);
                this.coinThumb.setSell(sell);
            }
            this.logger.info("thumb = {}\uff0cscale = {}", (Object)this.coinThumb, (Object)this.baseScale);
        }
    }
    
    @Override
    public void addHandler(final MarketHandler storage) {
        this.handlers.add(storage);
    }
    
    @Override
    public CoinThumb getThumb() {
        return this.coinThumb;
    }
    
    @Override
    public void setMarketService(final MarketService service) {
        this.service = service;
    }
    
    @Override
    public void generateKLine(final int range, final int field, final long time) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final long endTick = calendar.getTimeInMillis();
        final String endTime = df.format(calendar.getTime());
        calendar.add(field, -range);
        final String fromTime = df.format(calendar.getTime());
        final long startTick = calendar.getTimeInMillis();
        this.logger.info("time range from " + fromTime + " to " + endTime);
        final List<ExchangeTrade> exchangeTrades = (List<ExchangeTrade>)this.service.findTradeByTimeRange(this.symbol, startTick, endTick, "contract");
        final KLine kLine = new KLine();
        kLine.setTime(startTick);
        String rangeUnit = "";
        if (field == 12) {
            rangeUnit = "min";
        }
        else if (field == 11) {
            rangeUnit = "hour";
        }
        else if (field == 7) {
            rangeUnit = "week";
        }
        else if (field == 6) {
            rangeUnit = "day";
        }
        else if (field == 2) {
            rangeUnit = "month";
        }
        kLine.setPeriod(range + rangeUnit);
        for (final ExchangeTrade exchangeTrade : exchangeTrades) {
            this.processTrade(kLine, exchangeTrade);
        }
        this.service.saveKLine(this.symbol, kLine, "contract");
        this.generateKLineNext(range, field, time, kLine.getClosePrice());
    }
    
    private void generateKLineNext(final int range, final int field, final long time, final BigDecimal closePrice) {
        if (closePrice.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }
        final Calendar calendarNext = Calendar.getInstance();
        calendarNext.setTimeInMillis(time);
        calendarNext.add(field, range);
        final long endTickNext = calendarNext.getTimeInMillis();
        final long startTickNext = time;
        final List<ExchangeTrade> exchangeTrades = (List<ExchangeTrade>)this.service.findTradeByTimeRange(this.symbol, startTickNext, endTickNext, "contract");
        final KLine kLine = new KLine();
        kLine.setTime(startTickNext);
        String rangeUnit = "";
        if (field == 12) {
            rangeUnit = "min";
        }
        else if (field == 11) {
            rangeUnit = "hour";
        }
        else if (field == 7) {
            rangeUnit = "week";
        }
        else if (field == 6) {
            rangeUnit = "day";
        }
        else if (field == 2) {
            rangeUnit = "month";
        }
        kLine.setPeriod(range + rangeUnit);
        for (final ExchangeTrade exchangeTrade : exchangeTrades) {
            this.processTrade(kLine, exchangeTrade);
        }
        if (kLine.getOpenPrice().compareTo(BigDecimal.ZERO) <= 0) {
            kLine.setOpenPrice(closePrice);
            kLine.setHighestPrice(closePrice);
            kLine.setLowestPrice(closePrice);
            kLine.setClosePrice(closePrice);
        }
        for (final MarketHandler storage : this.handlers) {
            storage.handleKLine("contract", this.symbol, kLine);
        }
    }
    
    public static void main(final String[] args) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(13, 0);
        calendar.set(14, 0);
        final long time = calendar.getTimeInMillis();
        System.out.println(time);
    }
    
    @Override
    public KLine getKLine() {
        return this.currentKLine;
    }
    
    @Override
    public String toString() {
        return "ContractCoinProcessor(logger=" + this.logger + ", symbol=" + this.getSymbol() + ", baseCoin=" + this.getBaseCoin() + ", currentKLine=" + this.currentKLine + ", handlers=" + this.handlers + ", coinThumb=" + this.coinThumb + ", service=" + this.service + ", coinExchangeRate=" + this.coinExchangeRate + ", isHalt=" + this.isHalt + ", baseScale=" + this.baseScale + ", coinScale=" + this.coinScale + ")";
    }
}
