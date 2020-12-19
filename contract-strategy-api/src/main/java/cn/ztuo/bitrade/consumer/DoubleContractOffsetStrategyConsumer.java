package cn.ztuo.bitrade.consumer;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractstrategy.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import cn.ztuo.bitrade.service.*;
import org.apache.kafka.clients.consumer.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.kafka.annotation.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import org.apache.commons.lang3.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;

import java.lang.reflect.Type;
import java.math.*;

import cn.ztuo.bitrade.exchanges.okex.service.swap.impl.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import cn.ztuo.bitrade.exchanges.okex.bean.swap.param.*;
import cn.ztuo.bitrade.exchanges.okex.response_entity.*;
import cn.ztuo.bitrade.entity.contractstrategy.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.huobi.common.api.*;
import cn.ztuo.bitrade.exchanges.huobi.common.entity.*;
import org.slf4j.*;

@Component
public class DoubleContractOffsetStrategyConsumer {
    private static final Logger log;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractStrategyExchangeConfigService contractStrategyExchangeConfigService;
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private DoubleContractOffsetStrategyJobService doubleContractOffsetStrategyJobService;

    @KafkaListener(topics = {"double-contract-order-offset-strategy-handler"}, containerFactory = "kafkaListenerContainerFactory")
    public void onCalculateContractOrderOffsetStrategy(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                DoubleContractOffsetStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:topic={},orderId={}", (Object) record.topic(), record.key());
                final Long orderId = Long.parseLong((String) record.key());
                final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
                if (null != order) {
                    final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.findMemberApiByIfDefault((long) order.getMember().getId(), 1);
                    if (null != api) {
                        if (api.getFromExchangeId() == 1L) {
                            DoubleContractOffsetStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:handleHuoBiHedgingStrategy:HuoBi");
                            this.handleHuoBiHedgingStrategy(order, api);
                        } else if (api.getFromExchangeId() == 2L) {
                            DoubleContractOffsetStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:handleHuoBiHedgingStrategy:OKEX");
                            this.handleOKEXHedgingStrategy(order, api);
                        }
                    } else {
                        try {
                            this.doubleContractOffsetStrategyJobService.markOrderRetracement(Long.valueOf(order.getId()));
                        } catch (Exception e) {
                            DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) e.getMessage());
                        }
                        DoubleContractOffsetStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:无可用对冲配置信息");
                    }
                } else {
                    DoubleContractOffsetStrategyConsumer.log.info("onCalculateContractOrderHedgingStrategy:合约订单不存在或状态已发生变化");
                }
            } catch (Exception e2) {
                DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲处理出错===e={}", (Throwable) e2);
            }
        }
    }

    private void handleOKEXHedgingStrategy(final ContractExchangeOrder order, final ContractDoubleMemberApi config) {
        final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(config.getFromExchangeId());
        final String OKEX_DM_API_URL = exchangeConfig.getApiUrl();
        String okexSymbol = order.getSymbol().split("\\/")[0];
        okexSymbol += "-USDT-SWAP";
        String direction;
        String type;
        if (ContractOrderSide.BUY_UP.equals((Object) order.getSide())) {
            direction = "sell";
            type = "2";
        } else {
            if (!ContractOrderSide.BUY_DOWN.equals((Object) order.getSide())) {
                DoubleContractOffsetStrategyConsumer.log.info("Direction获取异常");
                return;
            }
            direction = "buy";
            type = "1";
        }
        final SwapMarketAPIServiceImpl marketAPIService = new SwapMarketAPIServiceImpl(OKEX_DM_API_URL);
        final String contractInfo = marketAPIService.getContractsApi();
        if (StringUtils.isEmpty((CharSequence) contractInfo)) {
            DoubleContractOffsetStrategyConsumer.log.info("contractInfo获取异常");
            return;
        }
        final List<OkexContractInfo> commonResponse = (List<OkexContractInfo>) JSON.parseObject(contractInfo, (Type) DoubleContractOffsetStrategyConsumer.class, new Feature[0]);
        OkexContractInfo contractSymbol = null;
        if (null == commonResponse || commonResponse.size() <= 0) {
            DoubleContractOffsetStrategyConsumer.log.info("OKEX_ContractSymbol获取异常");
            return;
        }
        for (final OkexContractInfo okexContractInfo : commonResponse) {
            if (okexSymbol.equals(okexContractInfo.getInstrument_id())) {
                contractSymbol = okexContractInfo;
                break;
            }
        }
        if (null == contractSymbol) {
            DoubleContractOffsetStrategyConsumer.log.info("OKEX_ContractSymbol获取异常");
            return;
        }
        final BigDecimal totalAmount = order.getAmount().multiply(order.getOpenPrice());
        final int leverRate = order.getLeverMultiple().intValue();
        Long amount = totalAmount.divide(BigDecimal.valueOf(leverRate).multiply(new BigDecimal(contractSymbol.getContract_val())), 0, 1).longValue();
        if (amount <= 0L) {
            amount = 1L;
        }
        final SwapTradeAPIServiceImpl okexApi = new SwapTradeAPIServiceImpl(OKEX_DM_API_URL, config.getApiKey(), config.getSecretKey(), config.getPassphrase());
        final ContractDoubleMemberStrategyOrder contractStrategyHedgingOrder = new ContractDoubleMemberStrategyOrder();
        contractStrategyHedgingOrder.setMemberId(config.getMemberId());
        contractStrategyHedgingOrder.setFromExchangeId(config.getFromExchangeId());
        contractStrategyHedgingOrder.setContractOrderId(Long.valueOf(order.getId()));
        contractStrategyHedgingOrder.setApiId(config.getId());
        contractStrategyHedgingOrder.setApiKey(config.getApiKey());
        contractStrategyHedgingOrder.setSecretKey(config.getSecretKey());
        contractStrategyHedgingOrder.setFromExchangeOrderId("");
        contractStrategyHedgingOrder.setSymbol(okexSymbol);
        contractStrategyHedgingOrder.setPrice(BigDecimal.ZERO);
        contractStrategyHedgingOrder.setAmount(amount);
        contractStrategyHedgingOrder.setContractSize(new BigDecimal(contractSymbol.getContract_val()));
        contractStrategyHedgingOrder.setDirection(direction);
        contractStrategyHedgingOrder.setOffset("open");
        contractStrategyHedgingOrder.setLeverRate(Integer.valueOf(leverRate));
        final String orderPriceType = "opponent";
        contractStrategyHedgingOrder.setOrderPriceType(orderPriceType);
        contractStrategyHedgingOrder.setMatchStatus(1);
        contractStrategyHedgingOrder.setCreateTime(new Date());
        contractStrategyHedgingOrder.setUpdateTime(new Date());
        try {
            final PpOrder orderParam = new PpOrder();
            orderParam.setSize(amount.toString());
            orderParam.setType(type);
            orderParam.setOrder_type("0");
            orderParam.setMatch_price("1");
            orderParam.setInstrument_id(okexSymbol);
            final String orderResponse = okexApi.order(orderParam);
            final OkexSwapOrderResponse orderIdResp = (OkexSwapOrderResponse) JSON.parseObject(orderResponse, (Type) DoubleContractOffsetStrategyConsumer.class, new Feature[0]);
            if (null != orderIdResp && orderIdResp.getError_code().equals("0")) {
                final String otherContractOrderId = orderIdResp.getOrder_id();
                contractStrategyHedgingOrder.setFromExchangeOrderId(otherContractOrderId);
                contractStrategyHedgingOrder.setStatus(0);
                contractStrategyHedgingOrder.setRemark("下单成功");
            } else {
                contractStrategyHedgingOrder.setStatus(1);
                contractStrategyHedgingOrder.setRemark("下单异常，异常信息：" + JSON.toJSONString((Object) orderIdResp));
                DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) JSON.toJSONString((Object) orderIdResp));
            }
        } catch (Exception e) {
            contractStrategyHedgingOrder.setStatus(1);
            contractStrategyHedgingOrder.setRemark("下单异常，异常信息：" + e.getMessage());
            DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) e.getMessage());
        }
        this.contractDoubleMemberStrategyOrderService.save(contractStrategyHedgingOrder);
        if (contractStrategyHedgingOrder.getStatus() == 1) {
            try {
                this.doubleContractOffsetStrategyJobService.markOrderRetracement(contractStrategyHedgingOrder, 1);
            } catch (Exception e) {
                DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) e.getMessage());
            }
        }
    }

    private void handleHuoBiHedgingStrategy(final ContractExchangeOrder order, final ContractDoubleMemberApi config) {
        final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(config.getFromExchangeId());
        final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
        String huobiSymbol = order.getSymbol().split("\\/")[0];
        huobiSymbol += "-USD";
        String huobiDirection;
        if (ContractOrderSide.BUY_UP.equals((Object) order.getSide())) {
            huobiDirection = "sell";
        } else {
            if (!ContractOrderSide.BUY_DOWN.equals((Object) order.getSide())) {
                DoubleContractOffsetStrategyConsumer.log.info("huobiDirection获取异常");
                return;
            }
            huobiDirection = "buy";
        }
        final HbdmswapRestApiV1 huobiGet = new HbdmswapRestApiV1(HUOBI_DM_API_URL);
        final String contractInfo = huobiGet.futureContractInfo(huobiSymbol);
        if (StringUtils.isEmpty((CharSequence) contractInfo)) {
            DoubleContractOffsetStrategyConsumer.log.info("contractInfo获取异常");
            return;
        }
        final HuoBiCommonResponse<List<HuoBiContractSymbol>> commonResponse = (HuoBiCommonResponse<List<HuoBiContractSymbol>>) JSON.parseObject(contractInfo, (Type) DoubleContractOffsetStrategyConsumer.class, new Feature[0]);
        if (commonResponse.getStatus().equals("ok") && null != commonResponse.getData() && ((List) commonResponse.getData()).size() > 0) {
            final HuoBiContractSymbol huoBiContractSymbol = commonResponse.getData().get(0);
            final BigDecimal totalAmount = order.getAmount().multiply(order.getOpenPrice());
            final int leverRate = order.getLeverMultiple().intValue();
            Long huobiAmount = totalAmount.divide(BigDecimal.valueOf(leverRate).multiply(huoBiContractSymbol.getContract_size()), 0, 1).longValue();
            if (huobiAmount <= 0L) {
                huobiAmount = 1L;
            }
            final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, config.getApiKey(), config.getSecretKey());
            final ContractDoubleMemberStrategyOrder contractStrategyHedgingOrder = new ContractDoubleMemberStrategyOrder();
            contractStrategyHedgingOrder.setMemberId(config.getMemberId());
            contractStrategyHedgingOrder.setFromExchangeId(config.getFromExchangeId());
            contractStrategyHedgingOrder.setContractOrderId(Long.valueOf(order.getId()));
            contractStrategyHedgingOrder.setApiId(config.getId());
            contractStrategyHedgingOrder.setApiKey(config.getApiKey());
            contractStrategyHedgingOrder.setSecretKey(config.getSecretKey());
            contractStrategyHedgingOrder.setFromExchangeOrderId("");
            contractStrategyHedgingOrder.setSymbol(huobiSymbol);
            contractStrategyHedgingOrder.setPrice(BigDecimal.ZERO);
            contractStrategyHedgingOrder.setAmount(huobiAmount);
            contractStrategyHedgingOrder.setContractSize(huoBiContractSymbol.getContract_size());
            contractStrategyHedgingOrder.setDirection(huobiDirection);
            contractStrategyHedgingOrder.setOffset("open");
            contractStrategyHedgingOrder.setLeverRate(Integer.valueOf(leverRate));
            final String orderPriceType = "opponent";
            contractStrategyHedgingOrder.setOrderPriceType(orderPriceType);
            contractStrategyHedgingOrder.setMatchStatus(1);
            contractStrategyHedgingOrder.setCreateTime(new Date());
            contractStrategyHedgingOrder.setUpdateTime(new Date());
            try {
                final String orderResponse = huobiApi.futureContractOrder(huobiSymbol, "", "", huobiAmount.toString(), huobiDirection, "open", order.getLeverMultiple().intValue() + "", orderPriceType);
                final HuoBiCommonResponse<HuoBiOrderResponse> orderIdResp = (HuoBiCommonResponse<HuoBiOrderResponse>) JSON.parseObject(orderResponse, (Type) DoubleContractOffsetStrategyConsumer.class, new Feature[0]);
                if (null != orderIdResp && orderIdResp.getStatus().equals("ok")) {
                    final String huobiContractOrderId = ((HuoBiOrderResponse) orderIdResp.getData()).getOrder_id_str();
                    contractStrategyHedgingOrder.setFromExchangeOrderId(huobiContractOrderId);
                    contractStrategyHedgingOrder.setStatus(0);
                    contractStrategyHedgingOrder.setRemark("下单成功");
                } else {
                    contractStrategyHedgingOrder.setStatus(1);
                    contractStrategyHedgingOrder.setRemark("下单异常，异常信息：" + JSON.toJSONString((Object) orderIdResp));
                    DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) JSON.toJSONString((Object) orderIdResp));
                }
            } catch (Exception e) {
                contractStrategyHedgingOrder.setStatus(1);
                contractStrategyHedgingOrder.setRemark("下单异常，异常信息：" + e.getMessage());
                DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) e.getMessage());
            }
            this.contractDoubleMemberStrategyOrderService.save(contractStrategyHedgingOrder);
            if (contractStrategyHedgingOrder.getStatus() == 1) {
                try {
                    this.doubleContractOffsetStrategyJobService.markOrderRetracement(contractStrategyHedgingOrder, 1);
                } catch (Exception e) {
                    DoubleContractOffsetStrategyConsumer.log.error("====合约订单对冲下单异常===e={}", (Object) e.getMessage());
                }
            }
            return;
        }
        DoubleContractOffsetStrategyConsumer.log.info("huoBiContractSymbol获取异常");
    }

    static {
        log = LoggerFactory.getLogger((Class) DoubleContractOffsetStrategyConsumer.class);
    }
}
