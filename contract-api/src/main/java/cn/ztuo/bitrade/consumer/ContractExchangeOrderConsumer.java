package cn.ztuo.bitrade.consumer;

import org.springframework.stereotype.*;
import org.springframework.kafka.core.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.vendor.provider.*;
import cn.ztuo.bitrade.service.*;
import org.apache.kafka.clients.consumer.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import java.math.*;

import cn.ztuo.bitrade.constant.*;
import org.springframework.kafka.annotation.*;
import com.alibaba.fastjson.*;

import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@Component
public class ContractExchangeOrderConsumer {
    private static final Logger log;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractExchangeOrderCalculateService contractExchangeOrderCalculateService;
    @Autowired
    private ContractExchangeService contractExchangeService;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractConfigService contractConfigService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;
    @Autowired
    private ContractExchangeOrderFeeService contractExchangeOrderFeeService;
    @Autowired
    private CommunityMemberFollowInfoService communityMemberFollowInfoService;
    @Autowired
    private ContractCoinService contractCoinService;
    @Autowired
    private ContractMemberService contractMemberService;
    @Autowired
    private MemberPromotionService memberPromotionService;
    @Autowired
    private SMSProvider smsProvider;
    @Autowired
    private MemberService memberService;
    @Autowired
    private RedisUtil redisUtil;

    @KafkaListener(topics = {"contract-exchange-symbol-price-calculate"}, containerFactory = "kafkaListenerContainerFactory")
    public void onCalculateContractExchangeOrder(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                ContractExchangeOrderConsumer.log.info("onCalculateSymbolPrice:topic={},accessKey={}", record.topic(), record.key());
                final BigDecimal coinPrice = new BigDecimal((String) record.key());
                final String symbol = (String) record.value();
                final List<Long> orderIdList = (List<Long>) this.contractExchangeService.findAllHoldingOrderIds(symbol);
                for (final Long orderId : orderIdList) {
                    try {
                        final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
                        if (null == order || !order.getStatus().equals(ContractOrderStatus.HOLD_PROCESSING)) {
                            continue;
                        }
                        BigDecimal openPrice = order.getOpenPrice();
                        openPrice = ((null != openPrice) ? openPrice : BigDecimal.ZERO);
                        final BigDecimal capitalAmount = order.getCapitalAmount();
                        final BigDecimal leverMultiple = order.getLeverMultiple();
                        final BigDecimal allMoney = capitalAmount.multiply(leverMultiple);
                        BigDecimal rate = BigDecimal.ZERO;
                        if (ContractOrderSide.BUY_UP.equals(order.getSide())) {
                            rate = coinPrice.divide(openPrice, 8, RoundingMode.HALF_UP).subtract(BigDecimal.ONE);
                        } else if (ContractOrderSide.BUY_DOWN.equals(order.getSide())) {
                            rate = BigDecimal.ONE.subtract(coinPrice.divide(openPrice, 8, RoundingMode.HALF_UP));
                        }
                        final BigDecimal currentProfit = allMoney.multiply(rate);
                        final BigDecimal lastRate = currentProfit.divide(capitalAmount, 8, RoundingMode.HALF_UP);
                        ContractExchangeOrderCalculate calculate = this.contractExchangeOrderCalculateService.findByOrderId(orderId);
                        if (null != calculate) {
                            calculate.setProfit(currentProfit);
                            calculate.setRate(lastRate);
                            calculate.setSymbolPrice(coinPrice);
                            calculate.setUpdateTime(new Date());
                            this.contractExchangeOrderCalculateService.update(calculate);
                        } else {
                            calculate = new ContractExchangeOrderCalculate();
                            calculate.setOrderId((long) orderId);
                            calculate.setProfit(currentProfit);
                            calculate.setRate(lastRate);
                            calculate.setSymbolPrice(coinPrice);
                            calculate.setUpdateTime(new Date());
                            calculate.setVersion(Long.valueOf(0L));
                            this.contractExchangeOrderCalculateService.save(calculate);
                        }
                        if (order.getIfStopProfit().equals(BooleanEnum.IS_TRUE) && ((ContractOrderSide.BUY_UP.equals(order.getSide()) && coinPrice.compareTo(order.getStopProfitPrice()) >= 0) || (ContractOrderSide.BUY_DOWN.equals(order.getSide()) && coinPrice.compareTo(order.getStopProfitPrice()) <= 0))) {
                            this.kafkaTemplate.send("contract-exchange-order-stop", "STOP_PROFIT", orderId.toString());
                        } else {
                            if (!order.getIfStopLoss().equals(BooleanEnum.IS_TRUE) || ((ContractOrderSide.BUY_UP.equals(order.getSide()) || coinPrice.compareTo(order.getStopLossPrice()) > 0) && (!ContractOrderSide.BUY_DOWN.equals(order.getSide()) || coinPrice.compareTo(order.getStopLossPrice()) < 0))) {
                                continue;
                            }
                            this.kafkaTemplate.send("contract-exchange-order-stop", "STOP_LOSS", orderId.toString());
                        }
                    } catch (Exception e) {
                        ContractExchangeOrderConsumer.log.error("====订单处理出错===e={}", (Throwable) e);
                    }
                }
            } catch (Exception e2) {
                ContractExchangeOrderConsumer.log.error("====订单处理出错===e={}", (Throwable) e2);
            }
        }
    }

    @KafkaListener(topics = {"contract-exchange-order-status-deal"}, containerFactory = "kafkaListenerContainerFactory")
    public void onDealContractExchangeOrder(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                ContractExchangeOrderConsumer.log.info("onDealContractExchangeOrder:topic={},accessKey={},record={}", new Object[]{record.topic(), record.key(), record.value()});
                final String coin = (String) record.key();
                Long memberId = 0L;
                final List<Long> orderIds = (List<Long>) JSON.parseArray((String) record.value(), (Class) Long.class);
                BigDecimal totalProfit = BigDecimal.ZERO;
                BigDecimal totalFee = BigDecimal.ZERO;
                BigDecimal totalInterest = BigDecimal.ZERO;
                BigDecimal maxLoss = BigDecimal.ZERO;
                ContractExchangeOrder lossMaxOrder = null;
                ContractExchangeOrderCalculate lossMaxCalculate = null;
                for (final Long orderId : orderIds) {
                    final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
                    if (null != order) {
                        if (!order.getStatus().equals(ContractOrderStatus.HOLD_PROCESSING)) {
                            continue;
                        }
                        final ContractExchangeOrderCalculate calculate = this.contractExchangeOrderCalculateService.findByOrderId(orderId);
                        if (null == calculate) {
                            continue;
                        }
                        memberId = order.getMember().getId();
                        final BigDecimal coinPrice = calculate.getSymbolPrice();
                        if (order.getIfStopProfit().equals(BooleanEnum.IS_TRUE) && ((ContractOrderSide.BUY_UP.equals(order.getSide()) && coinPrice.compareTo(order.getStopProfitPrice()) >= 0) || (ContractOrderSide.BUY_DOWN.equals(order.getSide()) && coinPrice.compareTo(order.getStopProfitPrice()) <= 0))) {
                            this.kafkaTemplate.send("contract-exchange-order-stop", "STOP_PROFIT", orderId.toString());
                        } else if (order.getIfStopLoss().equals(BooleanEnum.IS_TRUE) && ((ContractOrderSide.BUY_UP.equals(order.getSide()) && coinPrice.compareTo(order.getStopLossPrice()) <= 0) || (ContractOrderSide.BUY_DOWN.equals(order.getSide()) && coinPrice.compareTo(order.getStopLossPrice()) >= 0))) {
                            this.kafkaTemplate.send("contract-exchange-order-stop", "STOP_LOSS", orderId.toString());
                        } else {
                            totalProfit = totalProfit.add(calculate.getProfit());
                            BigDecimal orderFee = BigDecimal.ZERO;
                            if (null != order.getOrderFee()) {
                                orderFee = orderFee.add(order.getOrderFee().getOpenFee());
                                orderFee = orderFee.add(order.getOrderFee().getCloseFee());
                            }
                            totalFee = totalFee.add(orderFee);
                            BigDecimal interestFee = this.contractExchangeService.getInterestBalanceByOrderId(order.getId());
                            interestFee = ((null != interestFee) ? interestFee : BigDecimal.ZERO);
                            totalInterest = totalInterest.add(interestFee);
                            final BigDecimal currentLeft = calculate.getProfit().subtract(interestFee).subtract(orderFee);
                            final BigDecimal loss = currentLeft.abs();
                            if (maxLoss.compareTo(loss) >= 0) {
                                continue;
                            }
                            maxLoss = loss;
                            lossMaxOrder = order;
                            lossMaxCalculate = calculate;
                        }
                    }
                }
                if (memberId > 0L) {
                    final ContractConfig logicConfig = this.contractConfigService.findByConfigKey("explode_logic_select");
                    if ("1".equals(logicConfig.getConfigValue())) {
                        final MessageResult mr = this.contractExchangeService.getMemberAccountRights((long) memberId, true, 0);
                        Label_0908:
                        {
                            if (mr.getCode() == 0) {
                                final Map<String, Object> map = (Map<String, Object>) mr.getData();
                                final BigDecimal riskRate = new BigDecimal(map.get("riskRate").toString());
                                final BigDecimal frozenBalance = new BigDecimal(map.get("frozenBalance").toString());
                                List<ContractExchangeOrderCalculate> calculateList = (List<ContractExchangeOrderCalculate>) map.get("calculateList");
                                if (frozenBalance.compareTo(BigDecimal.ZERO) == 0) {
                                    if (calculateList == null) {
                                        break Label_0908;
                                    }
                                    if (calculateList.size() == 0) {
                                        break Label_0908;
                                    }
                                }
                                final ContractConfig sendMsgConfig = this.contractConfigService.findByConfigKey("send_msg_risk_rate_limit");
                                final BigDecimal sendMsgRiskRateLimit = new BigDecimal(sendMsgConfig.getConfigValue());
                                if (riskRate.compareTo(sendMsgRiskRateLimit) <= 0) {
                                    try {
                                        final Member member = this.memberService.findOne(memberId);
                                        final String currentKey = "Contract_No_Balance_" + member.getMobilePhone();
                                        final Object object = this.redisUtil.get(currentKey);
                                        if (object == null && !"15385197777".equals(member.getMobilePhone())) {
                                            final Country country = member.getCountry();
                                            if (country.getAreaCode().equals("86")) {
                                            }
                                            this.redisUtil.set(currentKey, System.currentTimeMillis(), 6L, TimeUnit.HOURS);
                                        }
                                    } catch (Exception e) {
                                        ContractExchangeOrderConsumer.log.error("====短信发送异常===e={}", (Throwable) e);
                                    }
                                }
                                final ContractConfig explodeConfig = this.contractConfigService.findByConfigKey("explode_risk_rate_limit");
                                final BigDecimal explodeRiskRateLimit = new BigDecimal(explodeConfig.getConfigValue());
                                if (riskRate.compareTo(explodeRiskRateLimit) <= 0) {
                                    this.contractExchangeService.explodeAllOrder(memberId, riskRate, (List) calculateList, 0);
                                }
                            }
                        }
                        final MessageResult mrShuangCang = this.contractExchangeService.getMemberAccountRights((long) memberId, true, 1);
                        Label_1075:
                        {
                            if (mrShuangCang.getCode() == 0) {
                                final Map<String, Object> map2 = (Map<String, Object>) mrShuangCang.getData();
                                final BigDecimal riskRate2 = new BigDecimal(map2.get("riskRate").toString());
                                final BigDecimal frozenBalance2 = new BigDecimal(map2.get("frozenBalance").toString());
                                final List<ContractExchangeOrderCalculate> calculateList2 = (List<ContractExchangeOrderCalculate>) map2.get("calculateList");
                                if (frozenBalance2.doubleValue() == 0.0) {
                                    if (calculateList2 == null) {
                                        break Label_1075;
                                    }
                                    if (calculateList2.size() == 0) {
                                        break Label_1075;
                                    }
                                }
                                final ContractConfig explodeConfig2 = this.contractConfigService.findByConfigKey("explode_risk_rate_limit");
                                final BigDecimal explodeRiskRateLimit2 = new BigDecimal(explodeConfig2.getConfigValue());
                                if (riskRate2.compareTo(explodeRiskRateLimit2) <= 0) {
                                    this.contractExchangeService.explodeAllOrder(memberId, riskRate2, (List) calculateList2, 1);
                                }
                            }
                        }
                        final MessageResult mrTuoGuan = this.contractExchangeService.getMemberAccountRights((long) memberId, true, 2);
                        Label_1242:
                        {
                            if (mrShuangCang.getCode() == 0) {
                                final Map<String, Object> map3 = (Map<String, Object>) mrTuoGuan.getData();
                                final BigDecimal riskRate3 = new BigDecimal(map3.get("riskRate").toString());
                                final BigDecimal frozenBalance3 = new BigDecimal(map3.get("frozenBalance").toString());
                                final List<ContractExchangeOrderCalculate> calculateList3 = (List<ContractExchangeOrderCalculate>) map3.get("calculateList");
                                if (frozenBalance3.doubleValue() == 0.0) {
                                    if (calculateList3 == null) {
                                        break Label_1242;
                                    }
                                    if (calculateList3.size() == 0) {
                                        break Label_1242;
                                    }
                                }
                                final ContractConfig explodeConfig = this.contractConfigService.findByConfigKey("explode_risk_rate_limit");
                                final BigDecimal explodeRiskRateLimit = new BigDecimal(explodeConfig.getConfigValue());
                                if (riskRate3.compareTo(explodeRiskRateLimit) <= 0) {
                                    this.contractExchangeService.explodeAllOrder(memberId, riskRate3, (List) calculateList3, 2);
                                }
                            }
                        }
                    } else {
                        BigDecimal left = totalProfit.subtract(totalFee).subtract(totalInterest);
                        final ContractWallet contractWallet = this.contractWalletService.findByMemberIdAndCoin((long) memberId, coin);
                        final BigDecimal recognizance = contractWallet.getBalance().add(contractWallet.getFrozenBalance());
                        if (left.compareTo(BigDecimal.ZERO) <= 0) {
                            left = left.abs();
                            final ContractConfig configMsg = this.contractConfigService.findByConfigKey("send_msg_balance_limit");
                            final BigDecimal limitPercentMsg = new BigDecimal(configMsg.getConfigValue());
                            final BigDecimal rightMsg = recognizance.multiply(limitPercentMsg);
                            if (left.compareTo(rightMsg) >= 0) {
                                final Member member2 = this.memberService.findOne(memberId);
                                if (null != member2 && StringUtils.isNotEmpty((CharSequence) member2.getMobilePhone())) {
                                    try {
                                        final String currentKey2 = "Contract_No_Balance_" + member2.getMobilePhone();
                                        final Object object2 = this.redisUtil.get(currentKey2);
                                        if (object2 == null) {
                                            final Country country2 = member2.getCountry();
                                            if (country2.getAreaCode().equals("86")) {
                                                this.smsProvider.sendTemplateMessage(member2.getMobilePhone(), "178525");
                                            } else {
                                                this.smsProvider.sendInternationalMessage("", member2.getMobilePhone(), new String[]{"3329"});
                                            }
                                            this.redisUtil.set(currentKey2, System.currentTimeMillis(), 6L, TimeUnit.HOURS);
                                        }
                                    } catch (Exception e) {
                                        ContractExchangeOrderConsumer.log.error("====短信发送异常===e={}", (Throwable) e);
                                    }
                                }
                            }
                            final ContractConfig config = this.contractConfigService.findByConfigKey("explode_balance_limit");
                            final BigDecimal limitPercent = new BigDecimal(config.getConfigValue());
                            final BigDecimal right = recognizance.multiply(limitPercent);
                            if (left.compareTo(right) >= 0 && null != lossMaxOrder && null != lossMaxCalculate) {
                                this.contractExchangeService.closeOrder(lossMaxOrder, lossMaxCalculate, ContractOrderStatus.EXPOLDE_WAREHOUSE);
                                return;
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                ContractExchangeOrderConsumer.log.error("====订单处理出错===e={}", (Throwable) e2);
            }
        }
    }

    @KafkaListener(topics = {"contract-exchange-order-stop"}, containerFactory = "kafkaListenerContainerFactory")
    public void onStopContractExchangeOrder(final List<ConsumerRecord<String, String>> records) {
        for (int i = 0; i < records.size(); ++i) {
            try {
                final ConsumerRecord<String, String> record = records.get(i);
                ContractExchangeOrderConsumer.log.info("onStopContractExchangeOrder:topic={},accessKey={}", record.topic(), record.key());
                final String type = (String) record.key();
                final Long orderId = Long.parseLong((String) record.value());
                final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
                if (null != order && order.getStatus().equals(ContractOrderStatus.HOLD_PROCESSING)) {
                    ContractOrderStatus status = null;
                    final String s = type;
                    switch (s) {
                        case "STOP_PROFIT": {
                            status = ContractOrderStatus.STOP_PROFIT;
                            break;
                        }
                        case "STOP_LOSS": {
                            status = ContractOrderStatus.STOP_LOSS;
                            break;
                        }
                    }
                    if (null != status) {
                        this.contractExchangeService.closeOrderByStop(order, status);
                    }
                }
            } catch (Exception e) {
                ContractExchangeOrderConsumer.log.error("====止盈止损订单处理出错===e={}", (Throwable) e);
            }
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) ContractExchangeOrderConsumer.class);
    }
}
