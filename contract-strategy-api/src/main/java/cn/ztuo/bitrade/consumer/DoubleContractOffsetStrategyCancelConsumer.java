package cn.ztuo.bitrade.consumer;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.service.contractdouble.*;

import java.util.*;

import org.apache.kafka.clients.consumer.*;
import cn.ztuo.bitrade.exchanges.huobi.common.api.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.service.swap.impl.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import org.springframework.kafka.annotation.*;
import org.slf4j.*;

@Component
public class DoubleContractOffsetStrategyCancelConsumer {
    private static final Logger log;
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private DoubleContractOffsetStrategyJobService doubleContractOffsetStrategyJobService;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;

    @KafkaListener(topics = {"double-contract-order-offset-strategy-cancel-handler"}, containerFactory = "kafkaListenerContainerFactory")
    public void onCalculateContractOrderOffsetStrategy(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                DoubleContractOffsetStrategyCancelConsumer.log.info("onCalculateContractOrderHedgingStrategy:topic={},orderId={}", (Object) record.topic(), record.key());
                final Long orderId = Long.parseLong((String) record.key());
                final ContractDoubleMemberStrategyOrder strategyOrder = this.contractDoubleMemberStrategyOrderService.findByOrderId((long) orderId);
                if (null == strategyOrder || strategyOrder.getStatus() == 1) {
                    this.doubleContractOffsetStrategyJobService.markOrderRetracement(orderId);
                } else if (strategyOrder.getStatus() == 0 && strategyOrder.getMatchStatus() == 1) {
                    if (strategyOrder.getFromExchangeId() == 1L) {
                        final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                        final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
                        final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey());
                        final String response = huobiApi.futureContractCancel(strategyOrder.getFromExchangeOrderId(), "", strategyOrder.getSymbol());
                        DoubleContractOffsetStrategyCancelConsumer.log.info("用户撤销数据，==>{}，火币撤单返回信息==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) response);
                    } else if (strategyOrder.getFromExchangeId() == 2L) {
                        final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                        final String OKEX_DM_API_URL = exchangeConfig.getApiUrl();
                        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(strategyOrder.getApiId());
                        if (null == api) {
                            return;
                        }
                        final SwapTradeAPIServiceImpl okexApi = new SwapTradeAPIServiceImpl(OKEX_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey(), api.getPassphrase());
                        final String responseOKEX = okexApi.cancelOrderByOrderId(strategyOrder.getSymbol(), strategyOrder.getFromExchangeOrderId());
                        DoubleContractOffsetStrategyCancelConsumer.log.info("用户撤销数据，==>{}，OKEX撤单返回信息==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) responseOKEX);
                    }
                } else {
                    DoubleContractOffsetStrategyCancelConsumer.log.info("用户撤销数据，==>{}，else", (Object) JSON.toJSONString((Object) strategyOrder));
                }
            } catch (Exception e) {
                DoubleContractOffsetStrategyCancelConsumer.log.error("====合约对冲订单撤销处理出错===e={}", (Throwable) e);
            }
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) DoubleContractOffsetStrategyCancelConsumer.class);
    }
}
