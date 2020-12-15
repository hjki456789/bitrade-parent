package cn.ztuo.bitrade.exchances.impl.huobi;

import cn.ztuo.bitrade.exchances.*;
import cn.ztuo.bitrade.util.HttpClientUtil;
import org.apache.commons.lang3.*;

import java.lang.reflect.Type;
import java.math.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;
import java.util.*;
import cn.ztuo.bitrade.exchances.entity.*;
import cn.ztuo.bitrade.exchances.impl.huobi.model.*;
import org.slf4j.*;

public class HuoBiApi implements BaseExchangeService
{
    private static final Logger log;
    private static String API_KEY;
    private static String SECRET_KEY;
    private static String BASE_URL;

    public HuoBiApi(final String baseUrl) {
        if (StringUtils.isNotEmpty((CharSequence)baseUrl)) {
            HuoBiApi.BASE_URL = baseUrl;
        }
    }

    public HuoBiApi(final String apiKey, final String secretKey, final String baseUrl) {
        if (StringUtils.isNotEmpty((CharSequence)baseUrl)) {
            HuoBiApi.BASE_URL = baseUrl;
        }
        HuoBiApi.API_KEY = apiKey;
        HuoBiApi.SECRET_KEY = secretKey;
    }

    public BigDecimal getSymbolPrice(final String symbol) {
        BigDecimal price = BigDecimal.ZERO;
        try {
            final String path = "/market/detail/merged";
            final StringBuffer sb = new StringBuffer(HuoBiApi.BASE_URL).append(path).append("?symbol=").append(symbol);
            final String result = HttpClientUtil.get(sb.toString(),null,null);
            if (StringUtils.isNotEmpty((CharSequence)result)) {
                final HuobiTickResponse<HuoBiDetailMerged> re = JSON.parseObject(result, (Type) HuoBiApi.class, new Feature[0]);
                if (re.getStatus().equals("ok") && null != re.getTick()) {
                    price = ((HuoBiDetailMerged)re.getTick()).getClose();
                }
            }
        }
        catch (Exception e) {
            HuoBiApi.log.error("=============\u83b7\u53d6\u706b\u5e01\u4ea4\u6613\u5bf9\u4ef7\u683c\u5f02\u5e38================", (Throwable)e);
        }
        return price;
    }

    public Map<Side, List<Depth>> getSymbolDepth(final String symbol) {
        final List<Depth> buyDepth = new ArrayList<Depth>();
        final List<Depth> sellDepth = new ArrayList<Depth>();
        final Map<Side, List<Depth>> result = new HashMap<Side, List<Depth>>();
        try {
            final String path = "/market/depth";
            final StringBuffer sb = new StringBuffer(HuoBiApi.BASE_URL).append(path).append("?symbol=").append(symbol).append("&depth=").append(20).append("&type=").append("step0");
            final String responseStr = HttpClientUtil.get(sb.toString(),null,null);
            if (StringUtils.isNotEmpty((CharSequence)responseStr)) {
                final HuobiTickResponse<HuoBiDepth> re = JSON.parseObject(responseStr, (Type) HuoBiApi.class, new Feature[0]);
                if (re.getStatus().equals("ok") && null != re.getTick()) {
                    if (null != ((HuoBiDepth)re.getTick()).getAsks() && ((HuoBiDepth)re.getTick()).getAsks().size() > 0) {
                        for (final List<BigDecimal> index : ((HuoBiDepth)re.getTick()).getAsks()) {
                            if (null != index) {
                                final Depth depth = new Depth();
                                depth.setPrice((BigDecimal)index.get(0));
                                depth.setAmount((BigDecimal)index.get(1));
                                sellDepth.add(depth);
                            }
                        }
                    }
                    if (null != ((HuoBiDepth)re.getTick()).getBids() && ((HuoBiDepth)re.getTick()).getBids().size() > 0) {
                        for (final List<BigDecimal> index : ((HuoBiDepth)re.getTick()).getBids()) {
                            if (null != index) {
                                final Depth depth = new Depth();
                                depth.setPrice((BigDecimal)index.get(0));
                                depth.setAmount((BigDecimal)index.get(1));
                                buyDepth.add(depth);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            HuoBiApi.log.error("=============\u83b7\u53d6\u706b\u5e01\u4ea4\u6613\u5bf9\u6df1\u5ea6\u5f02\u5e38================", (Throwable)e);
        }
        result.put(Side.BUY, buyDepth);
        result.put(Side.SELL, sellDepth);
        return result;
    }

    public LastTrade getLastTrade(final String symbol) {
        final LastTrade trade = new LastTrade();
        try {
            final String path = "/market/trade";
            final StringBuffer sb = new StringBuffer(HuoBiApi.BASE_URL).append(path).append("?symbol=").append(symbol);
            final String responseStr = HttpClientUtil.get(sb.toString(),null,null);
            if (StringUtils.isNotEmpty((CharSequence)responseStr)) {
                System.out.println(responseStr);
                final HuobiTickResponse<HuobiTickData<HuoBiLastTrade>> re = (HuobiTickResponse<HuobiTickData<HuoBiLastTrade>>)JSON.parseObject(responseStr, (Type)HuoBiApi.class, new Feature[0]);
                if (re.getStatus().equals("ok") && null != re.getTick() && null != ((HuobiTickData)re.getTick()).getData() && ((HuobiTickData)re.getTick()).getData().size() > 0) {
                    HuoBiLastTrade huoBiTrade = re.getTick().getData().get(0);
                    trade.setPrice(huoBiTrade.getPrice());
                    trade.setAmount(huoBiTrade.getAmount());
                    if ("buy".equals(huoBiTrade.getDirection())) {
                        trade.setSide(Side.BUY);
                    }
                    else if ("sell".equals(huoBiTrade.getDirection())) {
                        trade.setSide(Side.SELL);
                    }
                }
            }
        }
        catch (Exception e) {
            HuoBiApi.log.error("=============\u83b7\u53d6\u706b\u5e01\u4ea4\u6613\u5bf9\u6700\u540e\u4e00\u4e2a\u6210\u4ea4\u8bb0\u5f55\u5f02\u5e38================", (Throwable)e);
        }
        return trade;
    }

    public static void main(final String[] args) throws Exception {
        System.out.println(JSON.toJSONString((Object)new HuoBiApi("https://api.huobi.de.com").getLastTrade("btcusdt")));
    }

    static {
        log = LoggerFactory.getLogger((Class)HuoBiApi.class);
        HuoBiApi.BASE_URL = "https://api.huobi.pro";
    }
}
