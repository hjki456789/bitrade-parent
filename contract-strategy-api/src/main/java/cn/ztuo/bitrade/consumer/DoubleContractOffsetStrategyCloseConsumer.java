package cn.ztuo.bitrade.consumer;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.service.contractstrategy.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import org.apache.kafka.clients.consumer.*;
import org.springframework.kafka.annotation.*;
import cn.ztuo.bitrade.exchanges.okex.service.swap.impl.*;
import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;
import cn.ztuo.bitrade.exchanges.okex.response_entity.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;

import java.lang.reflect.Type;
import java.util.*;

import cn.ztuo.bitrade.entity.contractstrategy.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import cn.ztuo.bitrade.exchanges.huobi.common.api.*;
import cn.ztuo.bitrade.exchanges.huobi.common.entity.*;
import org.slf4j.*;

@Component
public class DoubleContractOffsetStrategyCloseConsumer {
    private static final Logger log;
    @Autowired
    private ContractStrategyExchangeConfigService contractStrategyExchangeConfigService;
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;

    @KafkaListener(topics = {"double-contract-order-offset-strategy-close-handler"}, containerFactory = "kafkaListenerContainerFactory")
    public void onCalculateContractOrderOffsetStrategyClose(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                DoubleContractOffsetStrategyCloseConsumer.log.info("onCalculateContractOrderOffsetStrategyClose:topic={},orderId={}", (Object) record.topic(), record.key());
                final Long id = Long.parseLong((String) record.key());
                final ContractDoubleMemberStrategyOrder strategyOrder = this.contractDoubleMemberStrategyOrderService.findById(id);
                if (null != strategyOrder) {
                    if (strategyOrder.getFromExchangeId() == 1L) {
                        this.handleHuoBiHedgingStrategy(strategyOrder);
                    }
                    if (strategyOrder.getFromExchangeId() == 2L) {
                        this.handleOKEXHedgingStrategy(strategyOrder);
                    }
                } else {
                    DoubleContractOffsetStrategyCloseConsumer.log.info("无对冲订单");
                }
            } catch (Exception e) {
                DoubleContractOffsetStrategyCloseConsumer.log.error("====合约订单对冲平仓处理出错===e={}", (Throwable) e);
            }
        }
    }

    private void handleOKEXHedgingStrategy(final ContractDoubleMemberStrategyOrder strategyOrder) {
        final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(strategyOrder.getFromExchangeId());
        final String OKEX_DM_API_URL = exchangeConfig.getApiUrl();
        final String DM_Symbol = strategyOrder.getSymbol();
        final Long amount = strategyOrder.getAmount();
        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(strategyOrder.getApiId());
        if (null == api) {
            return;
        }
        final SwapTradeAPIServiceImpl okexApi = new SwapTradeAPIServiceImpl(OKEX_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey(), api.getPassphrase());
        try {
            final PpOrder orderParam = new PpOrder();
            orderParam.setSize(amount.toString());
            String type;
            if ("sell".equals(strategyOrder.getDirection())) {
                type = "4";
            } else {
                if (!"buy".equals(strategyOrder.getDirection())) {
                    DoubleContractOffsetStrategyCloseConsumer.log.info("Direction获取异常");
                    return;
                }
                type = "3";
            }
            orderParam.setType(type);
            orderParam.setOrder_type("0");
            orderParam.setMatch_price("1");
            orderParam.setInstrument_id(DM_Symbol);
            final String orderResponse = okexApi.order(orderParam);
            final OkexSwapOrderResponse orderIdResp = JSON.parseObject(orderResponse, (Type) DoubleContractOffsetStrategyCloseConsumer.class, new Feature[0]);
            if (null != orderIdResp && orderIdResp.getError_code().equals("0")) {
                final String otherContractOrderId = orderIdResp.getOrder_id();
                this.contractDoubleMemberStrategyOrderService.updateCloseInfo(strategyOrder.getId(), otherContractOrderId, 1, new Date());
            } else {
                DoubleContractOffsetStrategyCloseConsumer.log.error("====合约订单平仓下单异常===e={}", (Object) JSON.toJSONString((Object) orderIdResp));
            }
        } catch (Exception e) {
            DoubleContractOffsetStrategyCloseConsumer.log.error("====合约订单对冲平仓下单异常===e={}", (Object) e.getMessage());
        }
    }

    private void handleHuoBiHedgingStrategy(final ContractDoubleMemberStrategyOrder strategyOrder) {
        final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(strategyOrder.getFromExchangeId());
        final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
        final String huobiSymbol = strategyOrder.getSymbol();
        final String huobiDirection = strategyOrder.getDirection();
        final Long huobiAmount = strategyOrder.getAmount();
        final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey());
        final String orderPriceType = "opponent";
        final Integer leverRate = strategyOrder.getLeverRate();
        try {
            final String orderResponse = huobiApi.futureContractOrder(huobiSymbol, "", "", huobiAmount.toString(), huobiDirection, "close", leverRate.toString(), orderPriceType);
            final HuoBiCommonResponse<HuoBiOrderResponse> orderIdResp = JSON.parseObject(orderResponse, (Type) DoubleContractOffsetStrategyCloseConsumer.class, new Feature[0]);
            if (null != orderIdResp && orderIdResp.getStatus().equals("ok")) {
                final String huobiContractOrderId = ((HuoBiOrderResponse) orderIdResp.getData()).getOrder_id_str();
                this.contractDoubleMemberStrategyOrderService.updateCloseInfo(strategyOrder.getId(), huobiContractOrderId, 1, new Date());
            } else {
                DoubleContractOffsetStrategyCloseConsumer.log.error("====合约订单平仓下单异常===e={}", (Object) JSON.toJSONString((Object) orderIdResp));
            }
        } catch (Exception e) {
            DoubleContractOffsetStrategyCloseConsumer.log.error("====合约订单对冲平仓下单异常===e={}", (Object) e.getMessage());
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) DoubleContractOffsetStrategyCloseConsumer.class);
    }
}
