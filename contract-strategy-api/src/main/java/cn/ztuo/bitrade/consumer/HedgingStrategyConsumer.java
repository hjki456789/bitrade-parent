package cn.ztuo.bitrade.consumer;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractstrategy.*;
import org.apache.kafka.clients.consumer.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.kafka.annotation.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import cn.ztuo.bitrade.exchanges.huobi.common.api.*;
import org.apache.commons.lang3.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;

import java.lang.reflect.Type;
import java.math.*;

import cn.ztuo.bitrade.exchanges.huobi.common.entity.*;

import java.util.*;

import cn.ztuo.bitrade.entity.contractstrategy.*;
import org.slf4j.*;

@Component
public class HedgingStrategyConsumer {
    private static final Logger log;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractStrategyConfigService contractStrategyConfigService;
    @Autowired
    private ContractStrategyExchangeConfigService contractStrategyExchangeConfigService;
    @Autowired
    private ContractStrategyHedgingOrderService contractStrategyHedgingOrderService;

    @KafkaListener(topics = {"contract-order-hedging-strategy-handler"}, containerFactory = "kafkaListenerContainerFactory")
    public void onCalculateContractOrderHedgingStrategy(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                HedgingStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:topic={},orderId={}", (Object) record.topic(), record.key());
                final Long orderId = Long.parseLong((String) record.key());
                final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
                if (null != order && ContractOrderStatus.HOLD_PROCESSING.equals((Object) order.getStatus())) {
                    final ContractStrategyConfig config = this.contractStrategyConfigService.findBySymbolAndStatus(order.getSymbol(), CommonStatus.NORMAL);
                    if (null != config && CommonStatus.NORMAL.equals((Object) config.getStatus())) {
                        if (config.getFromExchangeId() == 1L) {
                            HedgingStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:handleHuoBiHedgingStrategy");
                            this.handleHuoBiHedgingStrategy(order, config);
                        }
                    } else {
                        HedgingStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:无可用对冲配置信息");
                    }
                } else {
                    HedgingStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:合约订单不存在或状态已发生变化");
                }
            } catch (Exception e) {
                HedgingStrategyConsumer.log.error("====合约订单对冲处理出错===e={}", (Throwable) e);
            }
        }
    }

    private void handleHuoBiHedgingStrategy(final ContractExchangeOrder order, final ContractStrategyConfig config) throws Exception {
        final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(config.getFromExchangeId());
        final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
        String huobiSymbol = order.getSymbol().split("\\/")[0];
        huobiSymbol += "-USD";
        BigDecimal huobiPrice = order.getOpenPrice();
        String huobiDirection;
        if (ContractOrderSide.BUY_UP.equals((Object) order.getSide())) {
            huobiPrice = huobiPrice.multiply(config.getPriceFallRate());
            huobiDirection = "buy";
        } else {
            if (!ContractOrderSide.BUY_DOWN.equals((Object) order.getSide())) {
                return;
            }
            huobiPrice = huobiPrice.multiply(config.getPriceRiseRate());
            huobiDirection = "sell";
        }
        final HbdmswapRestApiV1 huobiGet = new HbdmswapRestApiV1(HUOBI_DM_API_URL);
        final String contractInfo = huobiGet.futureContractInfo(huobiSymbol);
        if (StringUtils.isEmpty((CharSequence) contractInfo)) {
            return;
        }
        final HuoBiCommonResponse<List<HuoBiContractSymbol>> commonResponse = (HuoBiCommonResponse<List<HuoBiContractSymbol>>) JSON.parseObject(contractInfo, (Type) HedgingStrategyConsumer.class, new Feature[0]);
        if (!commonResponse.getStatus().equals("ok") || null == commonResponse.getData() || ((List) commonResponse.getData()).size() <= 0) {
            return;
        }
        final HuoBiContractSymbol huoBiContractSymbol = commonResponse.getData().get(0);
        final int priceAccuracy = (int) Math.abs(Math.log10(huoBiContractSymbol.getPrice_tick().doubleValue()));
        huobiPrice = huobiPrice.setScale(priceAccuracy, 1);
        if (huobiPrice.compareTo(BigDecimal.ZERO) <= 0) {
            HedgingStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:huobiPrice={}", (Object) huobiPrice);
            return;
        }
        final BigDecimal totalAmount = order.getAmount().multiply(order.getOpenPrice());
        final Long huobiAmount = totalAmount.divide(new BigDecimal("10").multiply(huoBiContractSymbol.getContract_size()), 0, 1).longValue();
        final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, config.getApiKey(), config.getSecretKey());
        final String orderResponse = huobiApi.futureContractOrder(huobiSymbol, "", huobiPrice.toPlainString(), huobiAmount.toString(), huobiDirection, "open", "10", "limit");
        final HuoBiCommonResponse<HuoBiOrderResponse> orderIdResp = (HuoBiCommonResponse<HuoBiOrderResponse>) JSON.parseObject(orderResponse, (Type) HedgingStrategyConsumer.class, new Feature[0]);
        if (null != orderIdResp && orderIdResp.getStatus().equals("ok")) {
            final String huobiContractOrderId = ((HuoBiOrderResponse) orderIdResp.getData()).getOrder_id_str();
            final ContractStrategyHedgingOrder contractStrategyHedgingOrder = new ContractStrategyHedgingOrder();
            contractStrategyHedgingOrder.setMemberId(config.getMemberId());
            contractStrategyHedgingOrder.setFromExchangeId(config.getFromExchangeId());
            contractStrategyHedgingOrder.setApiKey(config.getApiKey());
            contractStrategyHedgingOrder.setFromExchangeOrderId(huobiContractOrderId);
            contractStrategyHedgingOrder.setSymbol(huobiSymbol);
            contractStrategyHedgingOrder.setPrice(huobiPrice);
            contractStrategyHedgingOrder.setAmount(huobiAmount);
            contractStrategyHedgingOrder.setContractSize(huoBiContractSymbol.getContract_size());
            contractStrategyHedgingOrder.setDirection(huobiDirection);
            contractStrategyHedgingOrder.setOffset("open");
            contractStrategyHedgingOrder.setLeverRate(Integer.valueOf(10));
            contractStrategyHedgingOrder.setOrderPriceType("limit");
            contractStrategyHedgingOrder.setCreateTime(new Date());
            this.contractStrategyHedgingOrderService.save(contractStrategyHedgingOrder);
        } else {
            HedgingStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) JSON.toJSONString((Object) orderIdResp));
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) HedgingStrategyConsumer.class);
    }
}
