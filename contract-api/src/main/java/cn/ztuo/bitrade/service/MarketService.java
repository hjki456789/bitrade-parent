package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.client.*;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.*;

import java.math.*;

import cn.ztuo.bitrade.entity.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;
import org.springframework.http.*;

import java.util.*;

import org.slf4j.*;

@Service
public class MarketService {
    private static final Logger log;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RestTemplate restTemplate;

    public List<KLine> findKLine(final String symbol, final String peroid, final int limit) {
        final Sort sort = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "time")});
        final Query query = new Query().with(sort).limit(limit);
        return (List<KLine>) this.mongoTemplate.find(query, (Class) KLine.class, "contract_exchange_kline_" + symbol + "_" + peroid);
    }

    public BigDecimal findNewestPrice(final String symbol) {
        BigDecimal closePrice = BigDecimal.ZERO;
        final CoinThumb symbolThumb = this.getSymbolThumb(symbol);
        if (null != symbolThumb && null != symbolThumb.getClose()) {
            closePrice = symbolThumb.getClose();
        }
        closePrice = ((null != closePrice) ? closePrice : BigDecimal.ZERO);
        return closePrice;
    }

    public CoinThumb getSymbolThumb(final String symbol) {
        try {
            final String serviceName = "bitrade-market";
            final String url = "http://" + serviceName + "/market/contract-symbol-thumb";
            final ResponseEntity<List> resultMap = (ResponseEntity<List>) this.restTemplate.getForEntity(url, (Class) List.class, new Object[0]);
            List<CoinThumb> map = (List<CoinThumb>) resultMap.getBody();
            final String mapStr = JSON.toJSONString(map);
            map = (List<CoinThumb>) JSON.parseObject(mapStr, MarketService.class, new Feature[0]);
            for (final CoinThumb thumb : map) {
                if (symbol.equals(thumb.getSymbol())) {
                    return thumb;
                }
            }
        } catch (Exception e) {
            MarketService.log.info(">>>>>>>\u83b7\u53d6\u884c\u60c5\u4fe1\u606f\u5f02\u5e38>>>>>>>", (Throwable) e);
        }
        return null;
    }

    static {
        log = LoggerFactory.getLogger((Class) MarketService.class);
    }
}
