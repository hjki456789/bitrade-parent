package cn.ztuo.bitrade.service;

import org.checkerframework.checker.units.qual.K;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.*;
import com.alibaba.fastjson.*;
import org.springframework.transaction.annotation.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.entity.contractdouble.*;

@Service
public class ContractExchangeService {
    @Autowired
    private ContractCoinService contractCoinService;
    @Autowired
    private LocaleMessageSourceService msService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;
    @Autowired
    private ContractExchangeOrderCancelService contractExchangeOrderCancelService;
    @Autowired
    private ContractExchangeOrderConditionRecordService contractExchangeOrderConditionRecordService;
    @Autowired
    private ContractExchangeOrderFeeService contractExchangeOrderFeeService;
    @Autowired
    private ContractExchangeOrderCloseService contractExchangeOrderCloseService;
    @Autowired
    private ContractExchangeOrderInterestService contractExchangeOrderInterestService;
    @Autowired
    private ContractExchangeOrderTiggerService contractExchangeOrderTiggerService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private ContractMemberService contractMemberService;
    @Autowired
    private MemberPromotionService memberPromotionService;
    @Autowired
    private ContractExchangeOrderCalculateService contractExchangeOrderCalculateService;
    @Autowired
    private CommunityMemberFollowInfoService communityMemberFollowInfoService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ContractDoubleDirectionWalletService contractDoubleDirectionWalletService;
    @Autowired
    private ContractWalletExplodeRecordService contractWalletExplodeRecordService;
    @Autowired
    private DepositWalletService depositWalletService;
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private ContractDoubleDirectionProfitInfoService contractDoubleDirectionProfitInfoService;
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult addContractOrder(final Long memberId, final String symbol, final ContractOrderSide side, final ContractOrderType type, BigDecimal price, final BigDecimal limitTriggerPrice, final BigDecimal limitPrice, final BigDecimal amount, BigDecimal capitalAmount, final BigDecimal leverMultiple, final BooleanEnum ifStopLoss, final BigDecimal stopLossPrice, final BooleanEnum ifStopProfit, final BigDecimal stopProfitPrice, final ContractOrderOrigin origin, final String originIp, final int walletType, final int isOneClickOrder) {
        final ContractCoin contractCoin = this.contractCoinService.findBySymbol(symbol);
        if (null == contractCoin || StringUtils.isEmpty((CharSequence) contractCoin.getSymbol())) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_SYMBOL_NOT_EXIST"));
        }
        if (contractCoin.getCanBuyUp() == 0 && ContractOrderSide.BUY_UP.equals(side)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_SYMBOL_CANNOT_BUY"));
        }
        if (contractCoin.getCanBuyDown() == 0 && ContractOrderSide.BUY_DOWN.equals(side)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_SYMBOL_CANNOT_BUY"));
        }
        if (walletType != 0 && isOneClickOrder == 1) {
            final List<ContractDoubleMemberApi> datas = (List<ContractDoubleMemberApi>) this.contractDoubleMemberApiService.findMemberApiByMemberId((long) memberId);
            if (datas == null || datas.size() == 0) {
                return MessageResult.error(500, "您不能一键下单，一键下单需要到个人中心绑定外部apikey!");
            }
            if (BooleanEnum.IS_TRUE.equals(ifStopProfit) || BooleanEnum.IS_TRUE.equals(ifStopLoss)) {
                return MessageResult.error(500, "一键下单暂不支持止盈止损设置！");
            }
        }
        final CoinThumb symbolThumb = this.marketService.getSymbolThumb(symbol);
        if (null == symbolThumb) {
            return MessageResult.error(500, this.msService.getMessage("SYSTEM_ERROR"));
        }
        final BigDecimal closePrice = symbolThumb.getClose();
        if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
            return MessageResult.error(500, this.msService.getMessage("SYSTEM_ERROR"));
        }
        price = closePrice;
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return MessageResult.error(500, "本金数量需大于0");
        }
        if (contractCoin.getMinLimit().compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(contractCoin.getMinLimit()) < 0) {
            return MessageResult.error(500, "单笔最小数量需大于" + contractCoin.getMinLimit());
        }
        if (contractCoin.getMaxLimit().compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(contractCoin.getMaxLimit()) > 0) {
            return MessageResult.error(500, "单笔最大数量需小于" + contractCoin.getMaxLimit());
        }
        capitalAmount = price.multiply(amount).divide(leverMultiple, 8, RoundingMode.DOWN);
        BigDecimal currentHoldAmount = this.contractOrderService.sumHoldingOrdersAmountByMemberId(memberId, symbol);
        currentHoldAmount = ((null != currentHoldAmount) ? currentHoldAmount : BigDecimal.ZERO);
        currentHoldAmount = currentHoldAmount.add(amount);
        if (currentHoldAmount.compareTo(contractCoin.getMaxHoldLimit()) > 0) {
            return MessageResult.error(500, "超过最大持仓量，无法下单");
        }
        if (ContractOrderSide.BUY_UP.equals(side)) {
            if (BooleanEnum.IS_TRUE.equals(ifStopProfit) && price.doubleValue() > stopProfitPrice.doubleValue()) {
                return MessageResult.error(500, "买涨止盈价小于当前价，无法下单");
            }
            if (BooleanEnum.IS_TRUE.equals(ifStopLoss) && price.doubleValue() < stopLossPrice.doubleValue()) {
                return MessageResult.error(500, "买涨止损价大于当前价，无法下单");
            }
        } else {
            if (BooleanEnum.IS_TRUE.equals(ifStopProfit) && price.doubleValue() < stopProfitPrice.doubleValue()) {
                return MessageResult.error(500, "买跌止盈价大于当前价，无法下单");
            }
            if (BooleanEnum.IS_TRUE.equals(ifStopLoss) && price.doubleValue() > stopLossPrice.doubleValue()) {
                return MessageResult.error(500, "买跌止损价小于当前价，无法下单");
            }
        }
        ContractMember contractMember = this.contractMemberService.findByMemberId(memberId.toString());
        if (contractMember == null) {
            contractMember = new ContractMember();
            final MemberPromotion memberPromotion = this.memberPromotionService.findByInviteesId(memberId);
            if (null != memberPromotion) {
                final Long inviterId = memberPromotion.getInviterId();
                final Member inviterMember = this.memberService.findOne(inviterId);
                if (null != inviterMember) {
                    final String promotionCode = inviterMember.getPromotionCode();
                    contractMember.setInvitationCode(promotionCode);
                }
            }
            contractMember.setMemberId(memberId);
            contractMember.setIfProxy(BooleanEnum.IS_FALSE);
            contractMember.setProxyId(Long.valueOf(0L));
            contractMember.setContractRebateRate(BigDecimal.ZERO);
            contractMember.setSpotRebateRate(BigDecimal.ZERO);
            contractMember.setStatus(0);
            contractMember.setVersion(0L);
            contractMember.setCreateTime(new Date());
            contractMember.setSequence(System.currentTimeMillis());
            this.contractMemberService.insert(contractMember);
        } else if (0 != contractMember.getStatus()) {
            return MessageResult.error(500, this.msService.getMessage("ORDER_LIMIT"));
        }
        final Member member = this.memberService.findOne(memberId);
        ContractExchangeOrder exchangeOrder = new ContractExchangeOrder();
        exchangeOrder.setMember(member);
        exchangeOrder.setSide(side);
        exchangeOrder.setType(type);
        exchangeOrder.setSymbol(symbol);
        exchangeOrder.setPrice(price);
        exchangeOrder.setLimitTriggerPrice(limitTriggerPrice);
        exchangeOrder.setLimitPrice(limitPrice);
        exchangeOrder.setCapitalAmount(capitalAmount);
        exchangeOrder.setAmount(amount);
        exchangeOrder.setLeverMultiple(leverMultiple);
        exchangeOrder.setIfStopLoss(ifStopLoss);
        exchangeOrder.setStopLossPrice(stopLossPrice);
        exchangeOrder.setIfStopProfit(ifStopProfit);
        exchangeOrder.setStopProfitPrice(stopProfitPrice);
        exchangeOrder.setContractType(Integer.valueOf(contractCoin.getContractType()));
        exchangeOrder.setOriginIp(originIp);
        exchangeOrder.setOrigin(origin);
        exchangeOrder.setVersion(0L);
        exchangeOrder.setCreateTime(new Date());
        exchangeOrder.setWalletType(Integer.valueOf(walletType));
        exchangeOrder.setIsOneClickOrder(Integer.valueOf(isOneClickOrder));
        exchangeOrder.setSequence(System.currentTimeMillis());
        if (type.equals(ContractOrderType.MARKET_PRICE)) {
            exchangeOrder.setOpenPrice(price);
            exchangeOrder.setStatus(ContractOrderStatus.HOLD_PROCESSING);
            if (isOneClickOrder == 1) {
                exchangeOrder.setStatus(ContractOrderStatus.ONE_CLICK_NO_CONFIRM);
            }
        } else if (type.equals(ContractOrderType.LIMIT_PRICE)) {
            if (isOneClickOrder == 1) {
                return MessageResult.error(500, "限价单不支持一键下单");
            }
            exchangeOrder.setStatus(ContractOrderStatus.HANG_PROCESSING);
            if (null == limitTriggerPrice || limitTriggerPrice.compareTo(BigDecimal.ZERO) <= 0) {
                return MessageResult.error(500, this.msService.getMessage("LIMIT_ORDER_TRIGGER_PRICE_NOT_RIGHT"));
            }
        }
        if (type.equals(ContractOrderType.MARKET_PRICE)) {
            if (contractCoin.getContractType() == 0) {
                final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) memberId);
                if (null == contractWallet || null == contractWallet.getBalance() || contractWallet.getBalance().compareTo(capitalAmount) < 0) {
                    return MessageResult.error(500, this.msService.getMessage("MEMBER_WALLET_NOT_ENOUGH"));
                }
                exchangeOrder = this.contractOrderService.saveOrder(exchangeOrder);
                if (exchangeOrder == null) {
                    throw new RuntimeException();
                }
                contractWallet.setBalance(contractWallet.getBalance().subtract(capitalAmount));
                contractWallet.setFrozenBalance(contractWallet.getFrozenBalance().add(capitalAmount));
                final int num = this.contractWalletService.updateContractWalletBalance(contractWallet);
                if (num <= 0) {
                    throw new RuntimeException();
                }
                final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
                contractWalletFlowRecord.setMember(member);
                contractWalletFlowRecord.setCoin(contractWallet.getCoin());
                contractWalletFlowRecord.setAmount(capitalAmount.multiply(new BigDecimal("-1")));
                contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
                contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_PLACE_ORDER);
                contractWalletFlowRecord.setRelationDetailId(exchangeOrder.getId());
                contractWalletFlowRecord.setVersion(0L);
                contractWalletFlowRecord.setCreateTime(new Date());
                contractWalletFlowRecord.setSequence(System.currentTimeMillis());
                contractWalletFlowRecord.setContractType(contractCoin.getContractType());
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
                ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
                final BigDecimal allMoney = exchangeOrder.getCapitalAmount().multiply(exchangeOrder.getLeverMultiple());
                final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
                final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
                orderFee.setOrderId(exchangeOrder.getId());
                orderFee.setOpenFee(openFee);
                orderFee.setCloseFee(closeFee);
                orderFee.setCreateTime(new Date());
                orderFee.setSequence(System.currentTimeMillis());
                orderFee.setSequence(0L);
                orderFee.setContractType(contractCoin.getContractType());
                orderFee = this.contractExchangeOrderFeeService.save(orderFee);
                if (null == orderFee) {
                    throw new RuntimeException();
                }
            } else if (walletType == 1) {
                final ContractDoubleDirectionWallet contractWallet2 = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(memberId, "USDT");
                if (null == contractWallet2 || null == contractWallet2.getBalance() || contractWallet2.getBalance().compareTo(capitalAmount) < 0) {
                    return MessageResult.error(500, this.msService.getMessage("MEMBER_WALLET_NOT_ENOUGH"));
                }
                exchangeOrder = this.contractOrderService.saveOrder(exchangeOrder);
                if (exchangeOrder == null) {
                    throw new RuntimeException();
                }
                contractWallet2.setBalance(contractWallet2.getBalance().subtract(capitalAmount));
                contractWallet2.setFrozenBalance(contractWallet2.getFrozenBalance().add(capitalAmount));
                final int num = this.contractDoubleDirectionWalletService.updateBalance(contractWallet2);
                if (num <= 0) {
                    throw new RuntimeException();
                }
                final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
                contractWalletFlowRecord.setMember(member);
                contractWalletFlowRecord.setCoin(contractWallet2.getCoin());
                contractWalletFlowRecord.setAmount(capitalAmount.multiply(new BigDecimal("-1")));
                contractWalletFlowRecord.setAfterBalance(contractWallet2.getBalance());
                contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_PLACE_ORDER);
                contractWalletFlowRecord.setRelationDetailId(exchangeOrder.getId());
                contractWalletFlowRecord.setVersion(0L);
                contractWalletFlowRecord.setCreateTime(new Date());
                contractWalletFlowRecord.setSequence(System.currentTimeMillis());
                contractWalletFlowRecord.setContractType(contractCoin.getContractType());
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
                ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
                final BigDecimal allMoney = exchangeOrder.getCapitalAmount().multiply(exchangeOrder.getLeverMultiple());
                final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
                final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
                orderFee.setOrderId(exchangeOrder.getId());
                orderFee.setOpenFee(openFee);
                orderFee.setCloseFee(closeFee);
                orderFee.setCreateTime(new Date());
                orderFee.setSequence(System.currentTimeMillis());
                orderFee.setSequence(0L);
                orderFee.setContractType(contractCoin.getContractType());
                orderFee = this.contractExchangeOrderFeeService.save(orderFee);
                if (null == orderFee) {
                    throw new RuntimeException();
                }
                if (isOneClickOrder == 1) {
                    this.kafkaTemplate.send("double-contract-order-offset-strategy-handler", String.valueOf(exchangeOrder.getId()), JSONObject.toJSONString(exchangeOrder));
                }
            } else {
                final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(memberId, "USDT");
                if (null == depositWallet || null == depositWallet.getBalance() || depositWallet.getBalance().compareTo(capitalAmount) < 0) {
                    return MessageResult.error(500, this.msService.getMessage("MEMBER_WALLET_NOT_ENOUGH"));
                }
                exchangeOrder = this.contractOrderService.saveOrder(exchangeOrder);
                if (exchangeOrder == null) {
                    throw new RuntimeException();
                }
                depositWallet.setBalance(depositWallet.getBalance().subtract(capitalAmount));
                depositWallet.setFrozenBalance(depositWallet.getFrozenBalance().add(capitalAmount));
                final int num = this.depositWalletService.updateBalance(depositWallet);
                if (num <= 0) {
                    throw new RuntimeException();
                }
                final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
                contractWalletFlowRecord.setMember(member);
                contractWalletFlowRecord.setCoin(depositWallet.getCoin());
                contractWalletFlowRecord.setAmount(capitalAmount.multiply(new BigDecimal("-1")));
                contractWalletFlowRecord.setAfterBalance(depositWallet.getBalance());
                contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_PLACE_ORDER);
                contractWalletFlowRecord.setRelationDetailId(exchangeOrder.getId());
                contractWalletFlowRecord.setVersion(0L);
                contractWalletFlowRecord.setCreateTime(new Date());
                contractWalletFlowRecord.setSequence(System.currentTimeMillis());
                contractWalletFlowRecord.setContractType(2);
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
                ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
                final BigDecimal allMoney = exchangeOrder.getCapitalAmount().multiply(exchangeOrder.getLeverMultiple());
                final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
                final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
                orderFee.setOrderId(exchangeOrder.getId());
                orderFee.setOpenFee(openFee);
                orderFee.setCloseFee(closeFee);
                orderFee.setCreateTime(new Date());
                orderFee.setSequence(System.currentTimeMillis());
                orderFee.setSequence(0L);
                orderFee.setContractType(contractCoin.getContractType());
                orderFee = this.contractExchangeOrderFeeService.save(orderFee);
                if (null == orderFee) {
                    throw new RuntimeException();
                }
                if (isOneClickOrder == 1) {
                    this.kafkaTemplate.send("double-contract-order-offset-strategy-handler", String.valueOf(exchangeOrder.getId()), JSONObject.toJSONString(exchangeOrder));
                }
            }
        } else {
            exchangeOrder = this.contractOrderService.saveOrder(exchangeOrder);
            if (exchangeOrder == null) {
                throw new RuntimeException();
            }
        }
        return MessageResult.success("success");
    }

    public MessageResult getHoldingOrders(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Page<ContractExchangeOrder> list = (Page<ContractExchangeOrder>) this.contractOrderService.getHoldingOrders(memberId, symbol, pageNo, pageSize, contractType, walletType);
        list.getContent().forEach(model -> {
            model.setSymbolCurrentPrice(this.marketService.findNewestPrice(model.getSymbol()));
            return;
        });
        return MessageResult.success("成功获取持仓订单数据", list);
    }

    public MessageResult getOrdersResultHistory(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Page<ContractExchangeOrder> list = (Page<ContractExchangeOrder>) this.contractOrderService.getOrdersResultHistory(memberId, symbol, pageNo, pageSize, contractType, walletType);
        list.getContent().forEach(model -> {
            model.setHoldInterest(this.contractOrderService.sumInterestBalanceByOrderId(model.getId()));
            return;
        });
        return MessageResult.success("成功获取历史订单数据", list);
    }

    public MessageResult getLimitOrders(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Page<ContractExchangeOrder> list = (Page<ContractExchangeOrder>) this.contractOrderService.getLimitOrders(memberId, symbol, pageNo, pageSize, contractType, walletType);
        list.getContent().forEach(model -> {
            model.setSymbolCurrentPrice(this.marketService.findNewestPrice(model.getSymbol()));
            return;
        });
        return MessageResult.success("成功获取限价订单数据", list);
    }

    public MessageResult getLimitOrdersResultHistory(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Page<ContractExchangeOrder> list = (Page<ContractExchangeOrder>) this.contractOrderService.getLimitOrdersResultHistory(memberId, symbol, pageNo, pageSize, contractType, walletType);
        return MessageResult.success("成功获取限价订单历史数据", list);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult cancelLimitOrder(final Long memberId, final Long orderId) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
        if (null == order) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_EXIST"));
        }
        if (!order.getMember().getId().equals(memberId)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_MATCH_MEMBER"));
        }
        if (!order.getStatus().equals(ContractOrderStatus.HANG_PROCESSING)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_STATUS_ALREADY_CHANGE"));
        }
        final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.CANCEL, order.getVersion());
        if (num <= 0) {
            throw new RuntimeException();
        }
        ContractExchangeOrderCancel cancel = new ContractExchangeOrderCancel();
        cancel.setOrderId(order.getId());
        cancel.setType(ContractExchangeOrderCancelType.SELF_OPERATION);
        cancel.setReasonMsg("");
        cancel.setCreateTime(new Date());
        cancel.setSequence(System.currentTimeMillis());
        cancel = this.contractExchangeOrderCancelService.saveContractExchangeOrderCancel(cancel);
        if (cancel == null) {
            throw new RuntimeException();
        }
        return MessageResult.success();
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult updateStopCondition(final Long memberId, final Long orderId, final BooleanEnum ifStopLoss, final BigDecimal stopLossPrice, final BooleanEnum ifStopProfit, final BigDecimal stopProfitPrice) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
        if (null == order) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_EXIST"));
        }
        if (!order.getMember().getId().equals(memberId)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_MATCH_MEMBER"));
        }
        if (!order.getStatus().equals(ContractOrderStatus.HOLD_PROCESSING)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_STATUS_ALREADY_CHANGE"));
        }
        if (order.getIsOneClickOrder() == 1 && (BooleanEnum.IS_TRUE.equals(ifStopProfit) || BooleanEnum.IS_TRUE.equals(ifStopLoss))) {
            return MessageResult.error(500, "一键下单暂不支持止盈止损设置！");
        }
        final CoinThumb symbolThumb = this.marketService.getSymbolThumb(order.getSymbol());
        if (null == symbolThumb) {
            return MessageResult.error(500, this.msService.getMessage("SYSTEM_ERROR"));
        }
        final BigDecimal closePrice = symbolThumb.getClose();
        if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
            return MessageResult.error(500, this.msService.getMessage("SYSTEM_ERROR"));
        }
        final BigDecimal price = closePrice;
        if (ContractOrderSide.BUY_UP.equals(order.getSide())) {
            if (BooleanEnum.IS_TRUE.equals(ifStopProfit) && (price.doubleValue() > stopProfitPrice.doubleValue() || order.getOpenPrice().doubleValue() > stopProfitPrice.doubleValue())) {
                return MessageResult.error(500, "买涨止盈价小于当前价或开仓价，无法更改");
            }
            if (BooleanEnum.IS_TRUE.equals(ifStopLoss) && (price.doubleValue() < stopLossPrice.doubleValue() || order.getOpenPrice().doubleValue() < stopLossPrice.doubleValue())) {
                return MessageResult.error(500, "买涨止损价大于当前价或开仓价，无法更改");
            }
        } else {
            if (BooleanEnum.IS_TRUE.equals(ifStopProfit) && (price.doubleValue() < stopProfitPrice.doubleValue() || order.getOpenPrice().doubleValue() < stopProfitPrice.doubleValue())) {
                return MessageResult.error(500, "买跌止盈价大于当前价或开仓价，无法更改");
            }
            if (BooleanEnum.IS_TRUE.equals(ifStopLoss) && (price.doubleValue() > stopLossPrice.doubleValue() || order.getOpenPrice().doubleValue() > stopLossPrice.doubleValue())) {
                return MessageResult.error(500, "买跌止损价小于当前价或开仓价，无法更改");
            }
        }
        final int num = this.contractOrderService.updateStopCondition(order.getId(), ifStopLoss, stopLossPrice, ifStopProfit, stopProfitPrice, order.getVersion());
        if (num <= 0) {
            throw new RuntimeException();
        }
        ContractExchangeOrderConditionRecord record = new ContractExchangeOrderConditionRecord();
        record.setOrder(order);
        record.setIfStopLoss(order.getIfStopLoss());
        record.setIfStopProfit(order.getIfStopProfit());
        record.setStopLossPrice(order.getStopLossPrice());
        record.setStopProfitPrice(order.getStopProfitPrice());
        record.setCreateTime(new Date());
        record.setSequence(System.currentTimeMillis());
        record = this.contractExchangeOrderConditionRecordService.save(record);
        if (record == null) {
            throw new RuntimeException();
        }
        return MessageResult.success();
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult closeContractOrder(final Long memberId, final Long orderId, BigDecimal closePrice) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
        if (null == order) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_EXIST"));
        }
        if (!order.getMember().getId().equals(memberId)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_MATCH_MEMBER"));
        }
        if (!order.getStatus().equals(ContractOrderStatus.HOLD_PROCESSING)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_STATUS_ALREADY_CHANGE"));
        }
        final CoinThumb symbolThumb = this.marketService.getSymbolThumb(order.getSymbol());
        if (null == symbolThumb) {
            return MessageResult.error(500, this.msService.getMessage("SYSTEM_ERROR"));
        }
        closePrice = symbolThumb.getClose();
        if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
            return MessageResult.error(500, this.msService.getMessage("SYSTEM_ERROR"));
        }
        final ContractCoin contractCoin = this.contractCoinService.findBySymbol(order.getSymbol());
        ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
        final BigDecimal allMoney = order.getLeverMultiple().multiply(order.getCapitalAmount());
        if (null != order.getOrderFee()) {
            orderFee = order.getOrderFee();
        } else {
            final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
            final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
            orderFee.setOrderId((long) orderId);
            orderFee.setOpenFee(openFee);
            orderFee.setCloseFee(closeFee);
            orderFee.setCreateTime(new Date());
            orderFee.setSequence(System.currentTimeMillis());
            orderFee.setSequence(0L);
            orderFee.setContractType(contractCoin.getContractType());
            orderFee = this.contractExchangeOrderFeeService.save(orderFee);
            if (null == orderFee) {
                throw new RuntimeException();
            }
        }
        BigDecimal fee = BigDecimal.ZERO;
        fee = fee.add(orderFee.getOpenFee());
        fee = fee.add(orderFee.getCloseFee());
        BigDecimal profitRate = BigDecimal.ZERO;
        if (order.getSide().equals(ContractOrderSide.BUY_UP)) {
            profitRate = closePrice.divide(order.getOpenPrice(), 8, 4).subtract(BigDecimal.ONE);
        } else if (order.getSide().equals(ContractOrderSide.BUY_DOWN)) {
            profitRate = closePrice.divide(order.getOpenPrice(), 8, 4);
            profitRate = BigDecimal.ONE.subtract(profitRate);
        }
        final BigDecimal profit = allMoney.multiply(profitRate);
        BigDecimal newBalance = profit.add(order.getCapitalAmount());
        BigDecimal interestBalance = this.contractExchangeOrderInterestService.sumInterestBalanceByOrderId(orderId);
        interestBalance = ((null != interestBalance) ? interestBalance : BigDecimal.ZERO);
        newBalance = newBalance.subtract(fee);
        newBalance = newBalance.subtract(interestBalance);
        if (contractCoin.getContractType() == 0) {
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) memberId);
            contractWallet.setBalance(contractWallet.getBalance().add(newBalance));
            contractWallet.setFrozenBalance(contractWallet.getFrozenBalance().subtract(order.getCapitalAmount()));
            if (contractWallet.getBalance().doubleValue() < 0.0) {
                contractWallet.setBalance(BigDecimal.ZERO);
            }
            if (contractWallet.getFrozenBalance().doubleValue() < 0.0) {
                contractWallet.setFrozenBalance(BigDecimal.ZERO);
            }
            int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.SELF_CLOSE, order.getVersion());
            if (num <= 0) {
                throw new RuntimeException();
            }
            num = this.contractWalletService.updateContractWalletBalance(contractWallet);
            if (num <= 0) {
                throw new RuntimeException();
            }
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(order.getMember());
            contractWalletFlowRecord.setCoin(contractWallet.getCoin());
            contractWalletFlowRecord.setAmount(newBalance);
            contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_CLOSE_ORDER);
            contractWalletFlowRecord.setRelationDetailId(order.getId());
            contractWalletFlowRecord.setVersion(0L);
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord.setContractType(contractCoin.getContractType());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        } else {
            BigDecimal frozenProfit = BigDecimal.ZERO;
            if (order.getWalletType() == 1) {
                BigDecimal rewordAmount = BigDecimal.ZERO;
                if (profit.doubleValue() < 0.0) {
                    rewordAmount = profit.abs().multiply(new BigDecimal(0.3));
                } else if (profit.doubleValue() > 0.0) {
                    frozenProfit = profit;
                }
                final ContractDoubleDirectionWallet contractWallet2 = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(memberId, "USDT");
                contractWallet2.setBalance(contractWallet2.getBalance().add(newBalance).add(rewordAmount).subtract(frozenProfit));
                contractWallet2.setFrozenBalance(contractWallet2.getFrozenBalance().subtract(order.getCapitalAmount()));
                if (contractWallet2.getBalance().doubleValue() < 0.0) {
                    contractWallet2.setBalance(BigDecimal.ZERO);
                }
                if (contractWallet2.getFrozenBalance().doubleValue() < 0.0) {
                    contractWallet2.setFrozenBalance(BigDecimal.ZERO);
                }
                int num2 = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.SELF_CLOSE, order.getVersion());
                if (num2 <= 0) {
                    throw new RuntimeException();
                }
                num2 = this.contractDoubleDirectionWalletService.updateBalance(contractWallet2);
                if (num2 <= 0) {
                    throw new RuntimeException();
                }
                final ContractWalletFlowRecord contractWalletFlowRecord2 = new ContractWalletFlowRecord();
                contractWalletFlowRecord2.setMember(order.getMember());
                contractWalletFlowRecord2.setCoin(contractWallet2.getCoin());
                contractWalletFlowRecord2.setAmount(newBalance);
                contractWalletFlowRecord2.setAfterBalance(contractWallet2.getBalance().subtract(rewordAmount));
                contractWalletFlowRecord2.setOperationType(ContractWalletOperationType.CONTRACT_CLOSE_ORDER);
                contractWalletFlowRecord2.setRelationDetailId(order.getId());
                contractWalletFlowRecord2.setVersion(0L);
                contractWalletFlowRecord2.setCreateTime(new Date());
                contractWalletFlowRecord2.setSequence(System.currentTimeMillis());
                contractWalletFlowRecord2.setContractType(contractCoin.getContractType());
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord2);
                if (profit.doubleValue() < 0.0) {
                    final ContractWalletFlowRecord contractWalletFlowRecord3 = new ContractWalletFlowRecord();
                    contractWalletFlowRecord3.setMember(order.getMember());
                    contractWalletFlowRecord3.setCoin(contractWallet2.getCoin());
                    contractWalletFlowRecord3.setAmount(rewordAmount);
                    contractWalletFlowRecord3.setAfterBalance(contractWallet2.getBalance());
                    contractWalletFlowRecord3.setOperationType(ContractWalletOperationType.CONTRACT_LOSS_REWARD);
                    contractWalletFlowRecord3.setRelationDetailId(order.getId());
                    contractWalletFlowRecord3.setVersion(0L);
                    contractWalletFlowRecord3.setCreateTime(new Date());
                    contractWalletFlowRecord3.setSequence(System.currentTimeMillis());
                    contractWalletFlowRecord3.setContractType(contractCoin.getContractType());
                    this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord3);
                    final MemberTransaction transaction = new MemberTransaction();
                    transaction.setAmount(rewordAmount);
                    transaction.setSymbol(contractWallet2.getCoin().getName());
                    transaction.setAddress("");
                    transaction.setMemberId(memberId);
                    transaction.setType(TransactionType.CONTRACT_LOSS_RECOMMEND);
                    transaction.setFee(BigDecimal.ZERO);
                    transaction.setCreateTime(new Date());
                    transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
                    transaction.setAfterBalance(contractWallet2.getBalance());
                    this.memberTransactionService.save(transaction);
                }
            } else {
                BigDecimal rewordAmount = BigDecimal.ZERO;
                if (profit.doubleValue() < 0.0) {
                    rewordAmount = profit.abs().multiply(new BigDecimal(0.3));
                } else if (profit.doubleValue() > 0.0) {
                    frozenProfit = profit;
                }
                final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(memberId, "USDT");
                depositWallet.setBalance(depositWallet.getBalance().add(newBalance).add(rewordAmount).subtract(frozenProfit));
                depositWallet.setFrozenBalance(depositWallet.getFrozenBalance().subtract(order.getCapitalAmount()));
                if (depositWallet.getBalance().doubleValue() < 0.0) {
                    depositWallet.setBalance(BigDecimal.ZERO);
                }
                if (depositWallet.getFrozenBalance().doubleValue() < 0.0) {
                    depositWallet.setFrozenBalance(BigDecimal.ZERO);
                }
                int num2 = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.SELF_CLOSE, order.getVersion());
                if (num2 <= 0) {
                    throw new RuntimeException();
                }
                num2 = this.depositWalletService.updateBalance(depositWallet);
                if (num2 <= 0) {
                    throw new RuntimeException();
                }
                final ContractWalletFlowRecord contractWalletFlowRecord2 = new ContractWalletFlowRecord();
                contractWalletFlowRecord2.setMember(order.getMember());
                contractWalletFlowRecord2.setCoin(depositWallet.getCoin());
                contractWalletFlowRecord2.setAmount(newBalance);
                contractWalletFlowRecord2.setAfterBalance(depositWallet.getBalance().subtract(rewordAmount));
                contractWalletFlowRecord2.setOperationType(ContractWalletOperationType.CONTRACT_CLOSE_ORDER);
                contractWalletFlowRecord2.setRelationDetailId(order.getId());
                contractWalletFlowRecord2.setVersion(0L);
                contractWalletFlowRecord2.setCreateTime(new Date());
                contractWalletFlowRecord2.setSequence(System.currentTimeMillis());
                contractWalletFlowRecord2.setContractType(2);
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord2);
                if (profit.doubleValue() < 0.0) {
                    final ContractWalletFlowRecord contractWalletFlowRecord3 = new ContractWalletFlowRecord();
                    contractWalletFlowRecord3.setMember(order.getMember());
                    contractWalletFlowRecord3.setCoin(depositWallet.getCoin());
                    contractWalletFlowRecord3.setAmount(rewordAmount);
                    contractWalletFlowRecord3.setAfterBalance(depositWallet.getBalance());
                    contractWalletFlowRecord3.setOperationType(ContractWalletOperationType.CONTRACT_LOSS_REWARD);
                    contractWalletFlowRecord3.setRelationDetailId(order.getId());
                    contractWalletFlowRecord3.setVersion(0L);
                    contractWalletFlowRecord3.setCreateTime(new Date());
                    contractWalletFlowRecord3.setSequence(System.currentTimeMillis());
                    contractWalletFlowRecord3.setContractType(2);
                    this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord3);
                    final MemberTransaction transaction = new MemberTransaction();
                    transaction.setAmount(rewordAmount);
                    transaction.setSymbol(depositWallet.getCoin().getName());
                    transaction.setAddress("");
                    transaction.setMemberId(memberId);
                    transaction.setType(TransactionType.CONTRACT_LOSS_RECOMMEND);
                    transaction.setFee(BigDecimal.ZERO);
                    transaction.setCreateTime(new Date());
                    transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
                    transaction.setAfterBalance(depositWallet.getBalance());
                    this.memberTransactionService.save(transaction);
                }
            }
            if (frozenProfit.doubleValue() > 0.0) {
                MemberWallet memberWallet = this.memberWalletService.findOneByCoinNameAndMemberId("USDT", (long) memberId);
                if (memberWallet == null) {
                    memberWallet = new MemberWallet();
                    memberWallet.setMemberId(memberId);
                    final Coin coin = new Coin();
                    coin.setName("USDT");
                    coin.setUnit("USDT");
                    memberWallet.setCoin(coin);
                    memberWallet = this.memberWalletService.save(memberWallet);
                }
                memberWallet.setFrozenBalance(memberWallet.getFrozenBalance().add(frozenProfit));
                this.memberWalletService.updateFrozenBalance(memberWallet.getId(), memberWallet.getFrozenBalance());
                final ContractDoubleDirectionOrderProfitInfo profitInfo = new ContractDoubleDirectionOrderProfitInfo();
                profitInfo.setMemberId(memberId);
                profitInfo.setOrderId(orderId);
                profitInfo.setProfit(frozenProfit);
                profitInfo.setDays(10);
                profitInfo.setRemainDays(10);
                profitInfo.setCreateTime(new Date());
                profitInfo.setSequence(Long.valueOf(System.currentTimeMillis()));
                profitInfo.setStatus(0);
                this.contractDoubleDirectionProfitInfoService.save(profitInfo);
            }
        }
        ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
        orderClose.setOrderId((long) orderId);
        orderClose.setClosePrice(closePrice);
        orderClose.setProfitOrLoss(profitRate);
        orderClose.setCloseProfitOrLoss(profit);
        orderClose.setType(ContractExchangeOrderCloseType.CLOSE_SELF);
        orderClose.setCreateTime(new Date());
        orderClose.setSequence(System.currentTimeMillis());
        orderClose.setVersion(0L);
        orderClose = this.contractExchangeOrderCloseService.save(orderClose);
        if (null == orderClose) {
            throw new RuntimeException();
        }
        return MessageResult.success();
    }

    public List<ContractExchangeOrder> findAllEffectiveLimitOrder(final String symbol) {
        return (List<ContractExchangeOrder>) this.contractOrderService.findAllEffectiveLimitOrder(symbol);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void changeLimitOrderToHolding(final Long orderId, final BigDecimal openPrice) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
        if (null == order) {
            return;
        }
        if (!order.getStatus().equals(ContractOrderStatus.HANG_PROCESSING)) {
            return;
        }
        final BigDecimal capitalAmount = order.getCapitalAmount();
        if (order.getContractType() == 0) {
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) order.getMember().getId());
            if (null == contractWallet || null == contractWallet.getBalance() || contractWallet.getBalance().compareTo(capitalAmount) < 0) {
                final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.CANCEL, order.getVersion());
                if (num > 0) {
                    final ContractExchangeOrderCancel cancel = new ContractExchangeOrderCancel();
                    cancel.setOrderId(order.getId());
                    cancel.setType(ContractExchangeOrderCancelType.SYSTEM_OPERATION);
                    cancel.setReasonMsg("合约帐号余额不足，系统自动取消限价订单");
                    cancel.setCreateTime(new Date());
                    cancel.setSequence(System.currentTimeMillis());
                    this.contractExchangeOrderCancelService.saveContractExchangeOrderCancel(cancel);
                }
                final ContractExchangeOrderTigger tigger = new ContractExchangeOrderTigger();
                tigger.setOpenPrice(openPrice);
                tigger.setOrderId((long) orderId);
                tigger.setVersion(0L);
                tigger.setCreateTime(new Date());
                tigger.setSequence(System.currentTimeMillis());
                this.contractExchangeOrderTiggerService.save(tigger);
                return;
            }
            contractWallet.setBalance(contractWallet.getBalance().subtract(capitalAmount));
            contractWallet.setFrozenBalance(contractWallet.getFrozenBalance().add(capitalAmount));
            final int num = this.contractWalletService.updateContractWalletBalance(contractWallet);
            if (num <= 0) {
                throw new RuntimeException();
            }
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(order.getMember());
            contractWalletFlowRecord.setCoin(contractWallet.getCoin());
            contractWalletFlowRecord.setAmount(capitalAmount.multiply(new BigDecimal("-1")));
            contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.LIMIT_ORDER_TO_HOLD);
            contractWalletFlowRecord.setRelationDetailId((long) orderId);
            contractWalletFlowRecord.setVersion(0L);
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        } else if (order.getWalletType() == 1) {
            final ContractDoubleDirectionWallet contractWallet2 = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(order.getMember().getId(), "USDT");
            if (null == contractWallet2 || null == contractWallet2.getBalance() || contractWallet2.getBalance().compareTo(capitalAmount) < 0) {
                final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.CANCEL, order.getVersion());
                if (num > 0) {
                    final ContractExchangeOrderCancel cancel = new ContractExchangeOrderCancel();
                    cancel.setOrderId(order.getId());
                    cancel.setType(ContractExchangeOrderCancelType.SYSTEM_OPERATION);
                    cancel.setReasonMsg("双仓合约帐号余额不足，系统自动取消限价订单");
                    cancel.setCreateTime(new Date());
                    cancel.setSequence(System.currentTimeMillis());
                    this.contractExchangeOrderCancelService.saveContractExchangeOrderCancel(cancel);
                }
                final ContractExchangeOrderTigger tigger = new ContractExchangeOrderTigger();
                tigger.setOpenPrice(openPrice);
                tigger.setOrderId((long) orderId);
                tigger.setVersion(0L);
                tigger.setCreateTime(new Date());
                tigger.setSequence(System.currentTimeMillis());
                this.contractExchangeOrderTiggerService.save(tigger);
                return;
            }
            contractWallet2.setBalance(contractWallet2.getBalance().subtract(capitalAmount));
            contractWallet2.setFrozenBalance(contractWallet2.getFrozenBalance().add(capitalAmount));
            final int num = this.contractDoubleDirectionWalletService.updateBalance(contractWallet2);
            if (num <= 0) {
                throw new RuntimeException();
            }
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(order.getMember());
            contractWalletFlowRecord.setCoin(contractWallet2.getCoin());
            contractWalletFlowRecord.setAmount(capitalAmount.multiply(new BigDecimal("-1")));
            contractWalletFlowRecord.setAfterBalance(contractWallet2.getBalance());
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.LIMIT_ORDER_TO_HOLD);
            contractWalletFlowRecord.setContractType((int) order.getContractType());
            contractWalletFlowRecord.setRelationDetailId((long) orderId);
            contractWalletFlowRecord.setVersion(0L);
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        } else {
            final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(order.getMember().getId(), "USDT");
            if (null == depositWallet || null == depositWallet.getBalance() || depositWallet.getBalance().compareTo(capitalAmount) < 0) {
                final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.CANCEL, order.getVersion());
                if (num > 0) {
                    final ContractExchangeOrderCancel cancel = new ContractExchangeOrderCancel();
                    cancel.setOrderId(order.getId());
                    cancel.setType(ContractExchangeOrderCancelType.SYSTEM_OPERATION);
                    cancel.setReasonMsg("合约帐号余额不足，系统自动取消限价订单");
                    cancel.setCreateTime(new Date());
                    cancel.setSequence(System.currentTimeMillis());
                    this.contractExchangeOrderCancelService.saveContractExchangeOrderCancel(cancel);
                }
                final ContractExchangeOrderTigger tigger = new ContractExchangeOrderTigger();
                tigger.setOpenPrice(openPrice);
                tigger.setOrderId((long) orderId);
                tigger.setVersion(0L);
                tigger.setCreateTime(new Date());
                tigger.setSequence(System.currentTimeMillis());
                this.contractExchangeOrderTiggerService.save(tigger);
                return;
            }
            depositWallet.setBalance(depositWallet.getBalance().subtract(capitalAmount));
            depositWallet.setFrozenBalance(depositWallet.getFrozenBalance().add(capitalAmount));
            final int num = this.depositWalletService.updateBalance(depositWallet);
            if (num <= 0) {
                throw new RuntimeException();
            }
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(order.getMember());
            contractWalletFlowRecord.setCoin(depositWallet.getCoin());
            contractWalletFlowRecord.setAmount(capitalAmount.multiply(new BigDecimal("-1")));
            contractWalletFlowRecord.setAfterBalance(depositWallet.getBalance());
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.LIMIT_ORDER_TO_HOLD);
            contractWalletFlowRecord.setContractType(2);
            contractWalletFlowRecord.setRelationDetailId((long) orderId);
            contractWalletFlowRecord.setVersion(0L);
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        }
        final int num2 = this.contractOrderService.updateLimitOrderToHolding(order.getId(), ContractOrderStatus.HOLD_PROCESSING, openPrice, order.getVersion());
        if (num2 <= 0) {
            throw new RuntimeException();
        }
        ContractExchangeOrderTigger tigger2 = new ContractExchangeOrderTigger();
        tigger2.setOpenPrice(openPrice);
        tigger2.setOrderId((long) orderId);
        tigger2.setVersion(0L);
        tigger2.setCreateTime(new Date());
        tigger2.setSequence(System.currentTimeMillis());
        ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
        final ContractCoin contractCoin = this.contractCoinService.findBySymbol(order.getSymbol());
        final BigDecimal allMoney = order.getCapitalAmount().multiply(order.getLeverMultiple());
        final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
        final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
        orderFee.setOrderId(order.getId());
        orderFee.setOpenFee(openFee);
        orderFee.setCloseFee(closeFee);
        orderFee.setCreateTime(new Date());
        orderFee.setSequence(System.currentTimeMillis());
        orderFee.setSequence(0L);
        orderFee = this.contractExchangeOrderFeeService.save(orderFee);
        if (null == orderFee) {
            throw new RuntimeException();
        }
        tigger2 = this.contractExchangeOrderTiggerService.save(tigger2);
        if (null == tigger2) {
            throw new RuntimeException();
        }
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void cancelLimitOrderById(final long orderId, final BigDecimal openPrice) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(Long.valueOf(orderId));
        if (null == order) {
            return;
        }
        if (!order.getStatus().equals(ContractOrderStatus.HANG_PROCESSING)) {
            return;
        }
        final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.CANCEL, order.getVersion());
        if (num <= 0) {
            return;
        }
        ContractExchangeOrderCancel cancel = new ContractExchangeOrderCancel();
        cancel.setOrderId(order.getId());
        cancel.setType(ContractExchangeOrderCancelType.SYSTEM_OPERATION);
        cancel.setReasonMsg("限价订单触发后不服务委托价格，系统自动取消限价订单");
        cancel.setCreateTime(new Date());
        cancel.setSequence(System.currentTimeMillis());
        cancel = this.contractExchangeOrderCancelService.saveContractExchangeOrderCancel(cancel);
        if (null == cancel) {
            throw new RuntimeException();
        }
        ContractExchangeOrderTigger tigger = new ContractExchangeOrderTigger();
        tigger.setOpenPrice(openPrice);
        tigger.setOrderId(orderId);
        tigger.setVersion(0L);
        tigger.setCreateTime(new Date());
        tigger.setSequence(System.currentTimeMillis());
        tigger = this.contractExchangeOrderTiggerService.save(tigger);
        if (null == tigger) {
            throw new RuntimeException();
        }
    }

    public List<ContractExchangeOrder> findAllHoldingOrders(final String symbol) {
        return (List<ContractExchangeOrder>) this.contractOrderService.findAllHoldingOrders(symbol);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void saveHoldingOrderInterest(final long orderId, final BigDecimal dayFee) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(Long.valueOf(orderId));
        if (null == order) {
            return;
        }
        if (!order.getStatus().equals(ContractOrderStatus.HOLD_PROCESSING)) {
            return;
        }
        final ContractExchangeOrderInterest interest = new ContractExchangeOrderInterest();
        interest.setOrder(order);
        interest.setBalance(dayFee);
        interest.setVersion(0L);
        interest.setCreateTime(new Date());
        interest.setSequence(System.currentTimeMillis());
        this.contractExchangeOrderInterestService.save(interest);
    }

    public BigDecimal getInterestBalanceByOrderId(final long orderId) {
        return this.contractExchangeOrderInterestService.sumInterestBalanceByOrderId(Long.valueOf(orderId));
    }

    public List<Long> findAllHoldingOrderIds(final String symbol) {
        return (List<Long>) this.contractOrderService.findAllHoldingOrderIds(symbol);
    }

    public List<BigInteger> findMemberIds(final int status) {
        return (List<BigInteger>) this.contractOrderService.findMemberIds(status);
    }

    public List<Long> findMemberOrderIds(final Long memberId, final String coin, final int status) {
        return (List<Long>) this.contractOrderService.findMemberOrderIds(memberId, coin, status);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult closeOrder(final ContractExchangeOrder order, final ContractExchangeOrderCalculate calculate, final ContractOrderStatus status) {
        int num = this.contractOrderService.updateOrderStatus(order.getId(), status, order.getVersion());
        if (num <= 0) {
            return MessageResult.error(this.msService.getMessage("ERROR"));
        }
        ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
        final BigDecimal allMoney = order.getLeverMultiple().multiply(order.getCapitalAmount());
        if (null != order.getOrderFee()) {
            orderFee = order.getOrderFee();
        } else {
            final ContractCoin contractCoin = this.contractCoinService.findBySymbol(order.getSymbol());
            final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
            final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
            orderFee.setOrderId(order.getId());
            orderFee.setOpenFee(openFee);
            orderFee.setCloseFee(closeFee);
            orderFee.setCreateTime(new Date());
            orderFee.setSequence(System.currentTimeMillis());
            orderFee.setSequence(0L);
            orderFee = this.contractExchangeOrderFeeService.save(orderFee);
            if (null == orderFee) {
                throw new RuntimeException();
            }
        }
        BigDecimal fee = BigDecimal.ZERO;
        fee = fee.add(orderFee.getOpenFee());
        fee = fee.add(orderFee.getCloseFee());
        final BigDecimal profit = calculate.getProfit();
        BigDecimal newBalance = profit.add(order.getCapitalAmount());
        BigDecimal interestBalance = this.contractExchangeOrderInterestService.sumInterestBalanceByOrderId(Long.valueOf(order.getId()));
        interestBalance = ((null != interestBalance) ? interestBalance : BigDecimal.ZERO);
        newBalance = newBalance.subtract(fee);
        newBalance = newBalance.subtract(interestBalance);
        final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) order.getMember().getId());
        contractWallet.setBalance(contractWallet.getBalance().add(newBalance));
        contractWallet.setFrozenBalance(contractWallet.getFrozenBalance().subtract(order.getCapitalAmount()));
        num = this.contractWalletService.updateContractWalletBalance(contractWallet);
        if (num <= 0) {
            throw new RuntimeException();
        }
        final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
        contractWalletFlowRecord.setMember(order.getMember());
        contractWalletFlowRecord.setCoin(contractWallet.getCoin());
        contractWalletFlowRecord.setAmount(newBalance);
        contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
        contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_CLOSE_ORDER);
        contractWalletFlowRecord.setRelationDetailId(order.getId());
        contractWalletFlowRecord.setVersion(0L);
        contractWalletFlowRecord.setCreateTime(new Date());
        contractWalletFlowRecord.setSequence(System.currentTimeMillis());
        this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
        orderClose.setOrderId(order.getId());
        orderClose.setClosePrice(calculate.getSymbolPrice());
        orderClose.setProfitOrLoss(calculate.getRate());
        orderClose.setCloseProfitOrLoss(calculate.getProfit());
        orderClose.setType(ContractExchangeOrderCloseType.CLOSE_AUTO);
        orderClose.setCreateTime(new Date());
        orderClose.setSequence(System.currentTimeMillis());
        orderClose.setVersion(0L);
        orderClose = this.contractExchangeOrderCloseService.save(orderClose);
        if (null == orderClose) {
            throw new RuntimeException();
        }
        return MessageResult.success();
    }

    public MessageResult getCurrentExplodeInfo(final long memberId) {
        final Map<String, Object> map = new HashMap<String, Object>();
        BigDecimal totalProfit = BigDecimal.ZERO;
        BigDecimal totalFee = BigDecimal.ZERO;
        final List<Long> orderIds = this.findMemberOrderIds(memberId, "USDT", ContractOrderStatus.HOLD_PROCESSING.getOrdinal());
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
                final BigDecimal coinPrice = calculate.getSymbolPrice();
                totalProfit = totalProfit.add(calculate.getProfit());
                BigDecimal orderFee = BigDecimal.ZERO;
                if (null != order.getOrderFee()) {
                    orderFee = orderFee.add(order.getOrderFee().getOpenFee());
                    orderFee = orderFee.add(order.getOrderFee().getCloseFee());
                }
                totalFee = totalFee.add(orderFee);
            }
        }
        final BigDecimal totalInterest = BigDecimal.ZERO;
        final BigDecimal explodeRate = BigDecimal.ZERO;
        map.put("explodeRate", explodeRate);
        return MessageResult.success("操作成功", map);
    }

    public MessageResult getMemberAccountRights(final long memberId, final int contractType) {
        final Map<String, Object> map = new HashMap<String, Object>();
        BigDecimal totalProfit = this.contractExchangeOrderCalculateService.getMemberCalculateProfit(Long.valueOf(memberId), "USDT", ContractOrderStatus.HOLD_PROCESSING.getOrdinal(), contractType);
        totalProfit = ((null != totalProfit) ? totalProfit : BigDecimal.ZERO);
        BigDecimal balance = BigDecimal.ZERO;
        BigDecimal frozenBalance = BigDecimal.ZERO;
        if (contractType == 0) {
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId(memberId);
            if (contractWallet != null) {
                balance = contractWallet.getBalance();
                frozenBalance = contractWallet.getFrozenBalance();
            }
        } else {
            final ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(Long.valueOf(memberId), "USDT");
            if (contractDoubleDirectionWallet != null) {
                balance = contractDoubleDirectionWallet.getBalance();
                frozenBalance = contractDoubleDirectionWallet.getFrozenBalance();
            }
        }
        BigDecimal accountRight = totalProfit.add(balance).add(frozenBalance);
        accountRight = ((null != accountRight) ? accountRight : BigDecimal.ZERO);
        map.put("accountRight", accountRight);
        BigDecimal riskRate = BigDecimal.ZERO;
        if (frozenBalance.compareTo(BigDecimal.ZERO) > 0) {
            riskRate = accountRight.divide(frozenBalance, 8, RoundingMode.HALF_UP);
            riskRate = riskRate.multiply(new BigDecimal("100"));
            riskRate = riskRate.setScale(4, RoundingMode.HALF_UP);
        }
        map.put("riskRate", riskRate);
        return MessageResult.success("操作成功", map);
    }

    public MessageResult getMemberAccountRights(final long memberId, final boolean needCalculateInfo, final int walletType) {
        final Map<String, Object> map = new HashMap<String, Object>();
        BigDecimal totalProfit = BigDecimal.ZERO;
        if (needCalculateInfo) {
            final List<ContractExchangeOrderCalculate> calculateList = (List<ContractExchangeOrderCalculate>) this.contractExchangeOrderCalculateService.findAllByMemberId(memberId, "USDT", ContractOrderStatus.HOLD_PROCESSING.getOrdinal(), walletType);
            for (final ContractExchangeOrderCalculate calculate : calculateList) {
                totalProfit = totalProfit.add(calculate.getProfit());
            }
            map.put("calculateList", calculateList);
        } else {
            totalProfit = this.contractExchangeOrderCalculateService.getMemberCalculateProfit(Long.valueOf(memberId), "USDT", ContractOrderStatus.HOLD_PROCESSING.getOrdinal(), walletType);
        }
        totalProfit = ((null != totalProfit) ? totalProfit : BigDecimal.ZERO);
        BigDecimal interestFee = this.contractExchangeOrderInterestService.sumInterestFeeByMemberId(memberId, ContractOrderStatus.HOLD_PROCESSING.getOrdinal(), walletType);
        interestFee = ((null != interestFee) ? interestFee : BigDecimal.ZERO);
        BigDecimal openFee = this.contractExchangeOrderFeeService.sumOpenFeeByMemberId(memberId, ContractOrderStatus.HOLD_PROCESSING.getOrdinal(), walletType);
        openFee = ((null != openFee) ? openFee : BigDecimal.ZERO);
        BigDecimal closeFee = this.contractExchangeOrderFeeService.sumCloseFeeByMemberId(memberId, ContractOrderStatus.HOLD_PROCESSING.getOrdinal(), walletType);
        closeFee = ((null != closeFee) ? closeFee : BigDecimal.ZERO);
        BigDecimal balance = BigDecimal.ZERO;
        BigDecimal frozenBalance = BigDecimal.ZERO;
        if (walletType == 0) {
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId(memberId);
            if (contractWallet != null) {
                balance = contractWallet.getBalance();
                frozenBalance = contractWallet.getFrozenBalance();
            }
        } else if (walletType == 1) {
            final ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(Long.valueOf(memberId), "USDT");
            if (contractDoubleDirectionWallet != null) {
                balance = contractDoubleDirectionWallet.getBalance();
                frozenBalance = contractDoubleDirectionWallet.getFrozenBalance();
            }
        } else {
            final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(Long.valueOf(memberId), "USDT");
            if (depositWallet != null) {
                balance = depositWallet.getBalance();
                frozenBalance = depositWallet.getFrozenBalance();
            }
        }
        BigDecimal accountRight = totalProfit.add(balance).add(frozenBalance);
        accountRight = accountRight.subtract(interestFee).subtract(openFee).subtract(closeFee);
        accountRight = ((null != accountRight) ? accountRight : BigDecimal.ZERO);
        map.put("accountRight", accountRight);
        BigDecimal riskRate = BigDecimal.ZERO;
        if (frozenBalance.compareTo(BigDecimal.ZERO) > 0) {
            riskRate = accountRight.divide(frozenBalance, 8, RoundingMode.HALF_UP);
            riskRate = riskRate.multiply(new BigDecimal("100"));
            riskRate = riskRate.setScale(4, RoundingMode.HALF_UP);
        }
        map.put("riskRate", riskRate);
        map.put("frozenBalance", frozenBalance);
        return MessageResult.success(this.msService.getMessage("SUCCESS"), map);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void explodeAllOrder(final Long memberId) throws Exception {
        final List<ContractExchangeOrder> orderIds = this.findMemberHoldingOrders(memberId, "USDT");
        for (final ContractExchangeOrder order : orderIds) {
            final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.EXPOLDE_WAREHOUSE, order.getVersion());
            if (num > 0) {
                final ContractExchangeOrderCalculate calculate = this.contractExchangeOrderCalculateService.findByOrderId(Long.valueOf(order.getId()));
                ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
                orderClose.setOrderId(order.getId());
                orderClose.setClosePrice(calculate.getSymbolPrice());
                orderClose.setProfitOrLoss(calculate.getRate());
                orderClose.setCloseProfitOrLoss(calculate.getProfit());
                orderClose.setType(ContractExchangeOrderCloseType.CLOSE_AUTO);
                orderClose.setCreateTime(new Date());
                orderClose.setSequence(System.currentTimeMillis());
                orderClose.setVersion(0L);
                orderClose = this.contractExchangeOrderCloseService.save(orderClose);
            }
        }
        final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) memberId);
        final BigDecimal newBalance = contractWallet.getFrozenBalance().multiply(new BigDecimal("0.5"));
        contractWallet.setBalance(newBalance);
        contractWallet.setFrozenBalance(BigDecimal.ZERO);
        final int num = this.contractWalletService.updateContractWalletBalance(contractWallet);
        if (num <= 0) {
            throw new RuntimeException();
        }
        final Member member = this.memberService.findOne(memberId);
        final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
        contractWalletFlowRecord.setMember(member);
        contractWalletFlowRecord.setCoin(contractWallet.getCoin());
        contractWalletFlowRecord.setAmount(newBalance);
        contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
        contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_CLOSE_ORDER);
        contractWalletFlowRecord.setRelationDetailId(-1L);
        contractWalletFlowRecord.setVersion(0L);
        contractWalletFlowRecord.setCreateTime(new Date());
        contractWalletFlowRecord.setSequence(System.currentTimeMillis());
        this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void explodeAllOrder(final Long memberId, BigDecimal riskRate, final List<ContractExchangeOrderCalculate> calculateList, final int walletType) throws Exception {
        if (walletType == 0) {
            riskRate = riskRate.multiply(new BigDecimal("0.01"));
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) memberId);
            final Map<Long, ContractExchangeOrderCalculate> calculateMap = new HashMap<Long, ContractExchangeOrderCalculate>();
            for (final ContractExchangeOrderCalculate calculate : calculateList) {
                calculateMap.put(calculate.getOrderId(), calculate);
            }
            final List<ContractExchangeOrder> orderIds = this.findMemberHoldingOrders(memberId, "USDT");
            for (final ContractExchangeOrder order : orderIds) {
                if (order.getWalletType() != walletType) {
                    continue;
                }
                final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.EXPOLDE_WAREHOUSE, order.getVersion());
                if (num <= 0) {
                    continue;
                }
                final ContractExchangeOrderCalculate calculate2 = calculateMap.get(order.getId());
                ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
                orderClose.setOrderId(order.getId());
                orderClose.setType(ContractExchangeOrderCloseType.CLOSE_AUTO);
                orderClose.setCreateTime(new Date());
                orderClose.setSequence(System.currentTimeMillis());
                orderClose.setVersion(0L);
                if (null != calculate2) {
                    orderClose.setClosePrice(calculate2.getSymbolPrice());
                    orderClose.setProfitOrLoss(calculate2.getRate());
                    orderClose.setCloseProfitOrLoss(calculate2.getProfit());
                } else {
                    orderClose.setClosePrice(order.getOpenPrice());
                    orderClose.setProfitOrLoss(BigDecimal.ZERO);
                    orderClose.setCloseProfitOrLoss(BigDecimal.ZERO);
                }
                orderClose = this.contractExchangeOrderCloseService.save(orderClose);
            }
            BigDecimal frozenAmount = contractWallet.getFrozenBalance();
            if (frozenAmount.doubleValue() < 0.0) {
                frozenAmount = BigDecimal.ZERO;
            }
            final BigDecimal beforeBalance = contractWallet.getBalance();
            final BigDecimal newBalance = frozenAmount.multiply(riskRate);
            contractWallet.setBalance(newBalance);
            if (contractWallet.getBalance().doubleValue() < 0.0) {
                contractWallet.setBalance(BigDecimal.ZERO);
            }
            contractWallet.setFrozenBalance(BigDecimal.ZERO);
            final int num2 = this.contractWalletService.updateContractWalletBalance(contractWallet);
            if (num2 <= 0) {
                throw new RuntimeException();
            }
            final Member member = this.memberService.findOne(memberId);
            ContractWalletExplodeRecord explodeRecord = new ContractWalletExplodeRecord();
            explodeRecord.setMemberId((long) memberId);
            explodeRecord.setCoin("USDT");
            explodeRecord.setVersion(0L);
            explodeRecord.setCreateTime(new Date());
            explodeRecord.setSequence(System.currentTimeMillis());
            explodeRecord.setRemark("帐号合约爆仓");
            explodeRecord.setRiskRate(riskRate);
            explodeRecord.setFrozenAmount(frozenAmount);
            explodeRecord.setBeforeBalance(beforeBalance);
            explodeRecord.setAfterBalance(newBalance);
            explodeRecord = this.contractWalletExplodeRecordService.saveRecord(explodeRecord);
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(member);
            contractWalletFlowRecord.setCoin(contractWallet.getCoin());
            contractWalletFlowRecord.setAmount(newBalance);
            contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_EXPLODE_ORDER);
            contractWalletFlowRecord.setVersion(0L);
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord.setRelationDetailId(explodeRecord.getId());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        } else if (walletType == 1) {
            riskRate = riskRate.multiply(new BigDecimal("0.01"));
            final ContractDoubleDirectionWallet contractWallet2 = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(memberId, "USDT");
            final Map<Long, ContractExchangeOrderCalculate> calculateMap = new HashMap<Long, ContractExchangeOrderCalculate>();
            for (final ContractExchangeOrderCalculate calculate : calculateList) {
                calculateMap.put(calculate.getOrderId(), calculate);
            }
            final List<ContractExchangeOrder> orderIds = this.findMemberHoldingOrders(memberId, "USDT");
            for (final ContractExchangeOrder order : orderIds) {
                if (order.getWalletType() != walletType) {
                    continue;
                }
                final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.EXPOLDE_WAREHOUSE, order.getVersion());
                if (num <= 0) {
                    continue;
                }
                final ContractExchangeOrderCalculate calculate2 = calculateMap.get(order.getId());
                ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
                orderClose.setOrderId(order.getId());
                orderClose.setType(ContractExchangeOrderCloseType.CLOSE_AUTO);
                orderClose.setCreateTime(new Date());
                orderClose.setSequence(System.currentTimeMillis());
                orderClose.setVersion(0L);
                if (null != calculate2) {
                    orderClose.setClosePrice(calculate2.getSymbolPrice());
                    orderClose.setProfitOrLoss(calculate2.getRate());
                    orderClose.setCloseProfitOrLoss(calculate2.getProfit());
                } else {
                    orderClose.setClosePrice(order.getOpenPrice());
                    orderClose.setProfitOrLoss(BigDecimal.ZERO);
                    orderClose.setCloseProfitOrLoss(BigDecimal.ZERO);
                }
                orderClose = this.contractExchangeOrderCloseService.save(orderClose);
            }
            BigDecimal reward = BigDecimal.ZERO;
            BigDecimal frozenAmount2 = contractWallet2.getFrozenBalance();
            if (frozenAmount2.doubleValue() < 0.0) {
                frozenAmount2 = BigDecimal.ZERO;
            }
            final BigDecimal beforeBalance2 = contractWallet2.getBalance();
            BigDecimal newBalance2 = frozenAmount2.multiply(riskRate);
            if (newBalance2.doubleValue() < 0.0) {
                newBalance2 = BigDecimal.ZERO;
            }
            reward = frozenAmount2.add(beforeBalance2).subtract(newBalance2).multiply(new BigDecimal(0.3));
            contractWallet2.setBalance(newBalance2.add(reward));
            contractWallet2.setFrozenBalance(BigDecimal.ZERO);
            final int num3 = this.contractDoubleDirectionWalletService.updateBalance(contractWallet2);
            if (num3 <= 0) {
                throw new RuntimeException();
            }
            final Member member2 = this.memberService.findOne(memberId);
            ContractWalletExplodeRecord explodeRecord2 = new ContractWalletExplodeRecord();
            explodeRecord2.setMemberId((long) memberId);
            explodeRecord2.setCoin("USDT");
            explodeRecord2.setVersion(0L);
            explodeRecord2.setCreateTime(new Date());
            explodeRecord2.setSequence(System.currentTimeMillis());
            explodeRecord2.setRemark("帐号合约爆仓");
            explodeRecord2.setRiskRate(riskRate);
            explodeRecord2.setFrozenAmount(frozenAmount2);
            explodeRecord2.setBeforeBalance(beforeBalance2);
            explodeRecord2.setAfterBalance(newBalance2);
            explodeRecord2 = this.contractWalletExplodeRecordService.saveRecord(explodeRecord2);
            final ContractWalletFlowRecord contractWalletFlowRecord2 = new ContractWalletFlowRecord();
            contractWalletFlowRecord2.setMember(member2);
            contractWalletFlowRecord2.setCoin(contractWallet2.getCoin());
            contractWalletFlowRecord2.setAmount(newBalance2);
            contractWalletFlowRecord2.setAfterBalance(contractWallet2.getBalance().subtract(reward));
            contractWalletFlowRecord2.setOperationType(ContractWalletOperationType.CONTRACT_EXPLODE_ORDER);
            contractWalletFlowRecord2.setVersion(0L);
            contractWalletFlowRecord2.setCreateTime(new Date());
            contractWalletFlowRecord2.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord2.setRelationDetailId(explodeRecord2.getId());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord2);
            final ContractWalletFlowRecord contractWalletFlowRecord3 = new ContractWalletFlowRecord();
            contractWalletFlowRecord3.setMember(member2);
            contractWalletFlowRecord3.setCoin(contractWallet2.getCoin());
            contractWalletFlowRecord3.setAmount(reward);
            contractWalletFlowRecord3.setAfterBalance(contractWallet2.getBalance());
            contractWalletFlowRecord3.setOperationType(ContractWalletOperationType.CONTRACT_LOSS_REWARD);
            contractWalletFlowRecord3.setVersion(0L);
            contractWalletFlowRecord3.setCreateTime(new Date());
            contractWalletFlowRecord3.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord3.setRelationDetailId(explodeRecord2.getId());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord3);
            final MemberTransaction transaction = new MemberTransaction();
            transaction.setAmount(reward);
            transaction.setSymbol(contractWallet2.getCoin().getName());
            transaction.setAddress("");
            transaction.setMemberId(member2.getId());
            transaction.setType(TransactionType.CONTRACT_LOSS_RECOMMEND);
            transaction.setFee(BigDecimal.ZERO);
            transaction.setCreateTime(new Date());
            transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
            transaction.setAfterBalance(contractWallet2.getBalance());
            this.memberTransactionService.save(transaction);
        } else if (walletType == 2) {
            riskRate = riskRate.multiply(new BigDecimal("0.01"));
            final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(memberId, "USDT");
            final Map<Long, ContractExchangeOrderCalculate> calculateMap = new HashMap<Long, ContractExchangeOrderCalculate>();
            for (final ContractExchangeOrderCalculate calculate : calculateList) {
                calculateMap.put(calculate.getOrderId(), calculate);
            }
            final List<ContractExchangeOrder> orderIds = this.findMemberHoldingOrders(memberId, "USDT");
            for (final ContractExchangeOrder order : orderIds) {
                if (order.getWalletType() != walletType) {
                    continue;
                }
                final int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.EXPOLDE_WAREHOUSE, order.getVersion());
                if (num <= 0) {
                    continue;
                }
                final ContractExchangeOrderCalculate calculate2 = calculateMap.get(order.getId());
                ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
                orderClose.setOrderId(order.getId());
                orderClose.setType(ContractExchangeOrderCloseType.CLOSE_AUTO);
                orderClose.setCreateTime(new Date());
                orderClose.setSequence(System.currentTimeMillis());
                orderClose.setVersion(0L);
                if (null != calculate2) {
                    orderClose.setClosePrice(calculate2.getSymbolPrice());
                    orderClose.setProfitOrLoss(calculate2.getRate());
                    orderClose.setCloseProfitOrLoss(calculate2.getProfit());
                } else {
                    orderClose.setClosePrice(order.getOpenPrice());
                    orderClose.setProfitOrLoss(BigDecimal.ZERO);
                    orderClose.setCloseProfitOrLoss(BigDecimal.ZERO);
                }
                orderClose = this.contractExchangeOrderCloseService.save(orderClose);
            }
            BigDecimal reward = BigDecimal.ZERO;
            BigDecimal frozenAmount2 = depositWallet.getFrozenBalance();
            if (frozenAmount2.doubleValue() < 0.0) {
                frozenAmount2 = BigDecimal.ZERO;
            }
            final BigDecimal beforeBalance2 = depositWallet.getBalance();
            BigDecimal newBalance2 = frozenAmount2.multiply(riskRate);
            if (newBalance2.doubleValue() < 0.0) {
                newBalance2 = BigDecimal.ZERO;
            }
            reward = frozenAmount2.add(beforeBalance2).subtract(newBalance2).multiply(new BigDecimal(0.3));
            depositWallet.setBalance(newBalance2.add(reward));
            depositWallet.setFrozenBalance(BigDecimal.ZERO);
            final int num3 = this.depositWalletService.updateBalance(depositWallet);
            if (num3 <= 0) {
                throw new RuntimeException();
            }
            final Member member2 = this.memberService.findOne(memberId);
            ContractWalletExplodeRecord explodeRecord2 = new ContractWalletExplodeRecord();
            explodeRecord2.setMemberId((long) memberId);
            explodeRecord2.setCoin("USDT");
            explodeRecord2.setVersion(0L);
            explodeRecord2.setCreateTime(new Date());
            explodeRecord2.setSequence(System.currentTimeMillis());
            explodeRecord2.setRemark("帐号合约爆仓");
            explodeRecord2.setRiskRate(riskRate);
            explodeRecord2.setFrozenAmount(frozenAmount2);
            explodeRecord2.setBeforeBalance(beforeBalance2);
            explodeRecord2.setAfterBalance(newBalance2);
            explodeRecord2 = this.contractWalletExplodeRecordService.saveRecord(explodeRecord2);
            final ContractWalletFlowRecord contractWalletFlowRecord2 = new ContractWalletFlowRecord();
            contractWalletFlowRecord2.setMember(member2);
            contractWalletFlowRecord2.setCoin(depositWallet.getCoin());
            contractWalletFlowRecord2.setAmount(newBalance2);
            contractWalletFlowRecord2.setAfterBalance(depositWallet.getBalance().subtract(reward));
            contractWalletFlowRecord2.setOperationType(ContractWalletOperationType.CONTRACT_EXPLODE_ORDER);
            contractWalletFlowRecord2.setVersion(0L);
            contractWalletFlowRecord2.setCreateTime(new Date());
            contractWalletFlowRecord2.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord2.setRelationDetailId(explodeRecord2.getId());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord2);
            final ContractWalletFlowRecord contractWalletFlowRecord3 = new ContractWalletFlowRecord();
            contractWalletFlowRecord3.setMember(member2);
            contractWalletFlowRecord3.setCoin(depositWallet.getCoin());
            contractWalletFlowRecord3.setAmount(reward);
            contractWalletFlowRecord3.setAfterBalance(depositWallet.getBalance());
            contractWalletFlowRecord3.setOperationType(ContractWalletOperationType.CONTRACT_LOSS_REWARD);
            contractWalletFlowRecord3.setVersion(0L);
            contractWalletFlowRecord3.setCreateTime(new Date());
            contractWalletFlowRecord3.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord3.setRelationDetailId(explodeRecord2.getId());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord3);
            final MemberTransaction transaction = new MemberTransaction();
            transaction.setAmount(reward);
            transaction.setSymbol(depositWallet.getCoin().getName());
            transaction.setAddress("");
            transaction.setMemberId(member2.getId());
            transaction.setType(TransactionType.CONTRACT_LOSS_RECOMMEND);
            transaction.setFee(BigDecimal.ZERO);
            transaction.setCreateTime(new Date());
            transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
            transaction.setAfterBalance(depositWallet.getBalance());
            this.memberTransactionService.save(transaction);
        }
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult closeOrderByStop(final ContractExchangeOrder order, final ContractOrderStatus status) {
        ContractExchangeOrderFee orderFee = new ContractExchangeOrderFee();
        final BigDecimal allMoney = order.getLeverMultiple().multiply(order.getCapitalAmount());
        if (null != order.getOrderFee()) {
            orderFee = order.getOrderFee();
        } else {
            final ContractCoin contractCoin = this.contractCoinService.findBySymbol(order.getSymbol());
            final BigDecimal openFee = allMoney.multiply(contractCoin.getOpenFee());
            final BigDecimal closeFee = allMoney.multiply(contractCoin.getCloseFee());
            orderFee.setOrderId(order.getId());
            orderFee.setOpenFee(openFee);
            orderFee.setCloseFee(closeFee);
            orderFee.setCreateTime(new Date());
            orderFee.setSequence(System.currentTimeMillis());
            orderFee.setSequence(0L);
            orderFee.setContractType((int) order.getContractType());
            orderFee = this.contractExchangeOrderFeeService.save(orderFee);
            if (null == orderFee) {
                throw new RuntimeException();
            }
        }
        BigDecimal fee = BigDecimal.ZERO;
        fee = fee.add(orderFee.getOpenFee());
        fee = fee.add(orderFee.getCloseFee());
        BigDecimal closePrice = BigDecimal.ZERO;
        if (ContractOrderStatus.STOP_PROFIT.equals(status)) {
            closePrice = order.getStopProfitPrice();
        } else {
            if (!ContractOrderStatus.STOP_LOSS.equals(status)) {
                throw new RuntimeException();
            }
            closePrice = order.getStopLossPrice();
        }
        BigDecimal profitRate = BigDecimal.ZERO;
        if (order.getSide().equals(ContractOrderSide.BUY_UP)) {
            profitRate = closePrice.divide(order.getOpenPrice(), 8, 4).subtract(BigDecimal.ONE);
        } else if (order.getSide().equals(ContractOrderSide.BUY_DOWN)) {
            profitRate = closePrice.divide(order.getOpenPrice(), 8, 4);
            profitRate = BigDecimal.ONE.subtract(profitRate);
        }
        final BigDecimal profit = allMoney.multiply(profitRate);
        final BigDecimal lastRate = profit.divide(order.getCapitalAmount(), 8, RoundingMode.HALF_UP);
        BigDecimal newBalance = profit.add(order.getCapitalAmount());
        BigDecimal interestBalance = this.contractExchangeOrderInterestService.sumInterestBalanceByOrderId(Long.valueOf(order.getId()));
        interestBalance = ((null != interestBalance) ? interestBalance : BigDecimal.ZERO);
        newBalance = newBalance.subtract(fee);
        newBalance = newBalance.subtract(interestBalance);
        int num = 0;
        Coin coin = null;
        BigDecimal balance = BigDecimal.ZERO;
        BigDecimal reward = BigDecimal.ZERO;
        if (order.getContractType() == 0) {
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long) order.getMember().getId());
            contractWallet.setBalance(contractWallet.getBalance().add(newBalance));
            contractWallet.setFrozenBalance(contractWallet.getFrozenBalance().subtract(order.getCapitalAmount()));
            if (contractWallet.getBalance().doubleValue() < 0.0) {
                contractWallet.setBalance(BigDecimal.ZERO);
            }
            if (contractWallet.getFrozenBalance().doubleValue() < 0.0) {
                contractWallet.setFrozenBalance(BigDecimal.ZERO);
            }
            num = this.contractWalletService.updateContractWalletBalance(contractWallet);
            if (num <= 0) {
                throw new RuntimeException();
            }
            coin = contractWallet.getCoin();
            balance = contractWallet.getBalance();
        } else {
            BigDecimal frozenProfit = BigDecimal.ZERO;
            if (order.getWalletType() == 1) {
                if (profit.doubleValue() < 0.0) {
                    reward = profit.abs().multiply(new BigDecimal(0.3));
                } else if (profit.doubleValue() > 0.0) {
                    frozenProfit = profit;
                }
                final ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(order.getMember().getId(), "USDT");
                contractDoubleDirectionWallet.setBalance(contractDoubleDirectionWallet.getBalance().add(newBalance).add(reward).subtract(frozenProfit));
                contractDoubleDirectionWallet.setFrozenBalance(contractDoubleDirectionWallet.getFrozenBalance().subtract(order.getCapitalAmount()));
                if (contractDoubleDirectionWallet.getBalance().doubleValue() < 0.0) {
                    contractDoubleDirectionWallet.setBalance(BigDecimal.ZERO);
                }
                if (contractDoubleDirectionWallet.getFrozenBalance().doubleValue() < 0.0) {
                    contractDoubleDirectionWallet.setFrozenBalance(BigDecimal.ZERO);
                }
                num = this.contractDoubleDirectionWalletService.updateBalance(contractDoubleDirectionWallet);
                if (num <= 0) {
                    throw new RuntimeException();
                }
                coin = contractDoubleDirectionWallet.getCoin();
                balance = contractDoubleDirectionWallet.getBalance();
            } else {
                if (profit.doubleValue() < 0.0) {
                    reward = profit.abs().multiply(new BigDecimal(0.3));
                } else if (profit.doubleValue() > 0.0) {
                    frozenProfit = profit;
                }
                final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(order.getMember().getId(), "USDT");
                depositWallet.setBalance(depositWallet.getBalance().add(newBalance).add(reward).subtract(frozenProfit));
                depositWallet.setFrozenBalance(depositWallet.getFrozenBalance().subtract(order.getCapitalAmount()));
                if (depositWallet.getBalance().doubleValue() < 0.0) {
                    depositWallet.setBalance(BigDecimal.ZERO);
                }
                if (depositWallet.getFrozenBalance().doubleValue() < 0.0) {
                    depositWallet.setFrozenBalance(BigDecimal.ZERO);
                }
                num = this.depositWalletService.updateBalance(depositWallet);
                if (num <= 0) {
                    throw new RuntimeException();
                }
                coin = depositWallet.getCoin();
                balance = depositWallet.getBalance();
            }
            if (frozenProfit.doubleValue() > 0.0) {
                MemberWallet memberWallet = this.memberWalletService.findOneByCoinNameAndMemberId("USDT", (long) order.getMember().getId());
                if (memberWallet == null) {
                    memberWallet = new MemberWallet();
                    memberWallet.setMemberId(order.getMember().getId());
                    memberWallet.setCoin(coin);
                    memberWallet = this.memberWalletService.save(memberWallet);
                }
                memberWallet.setFrozenBalance(memberWallet.getFrozenBalance().add(frozenProfit));
                this.memberWalletService.updateFrozenBalance(memberWallet.getId(), memberWallet.getFrozenBalance());
                final ContractDoubleDirectionOrderProfitInfo profitInfo = new ContractDoubleDirectionOrderProfitInfo();
                profitInfo.setMemberId(order.getMember().getId());
                profitInfo.setOrderId(Long.valueOf(order.getId()));
                profitInfo.setProfit(frozenProfit);
                profitInfo.setDays(10);
                profitInfo.setRemainDays(10);
                profitInfo.setCreateTime(new Date());
                profitInfo.setSequence(Long.valueOf(System.currentTimeMillis()));
                profitInfo.setStatus(0);
                this.contractDoubleDirectionProfitInfoService.save(profitInfo);
            }
        }
        final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
        contractWalletFlowRecord.setMember(order.getMember());
        contractWalletFlowRecord.setCoin(coin);
        contractWalletFlowRecord.setAmount(newBalance);
        contractWalletFlowRecord.setAfterBalance(balance.subtract(reward));
        contractWalletFlowRecord.setOperationType(ContractWalletOperationType.CONTRACT_CLOSE_ORDER);
        contractWalletFlowRecord.setRelationDetailId(order.getId());
        contractWalletFlowRecord.setVersion(0L);
        contractWalletFlowRecord.setCreateTime(new Date());
        contractWalletFlowRecord.setSequence(System.currentTimeMillis());
        contractWalletFlowRecord.setContractType((int) order.getWalletType());
        this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
        if (reward.doubleValue() > 0.0) {
            final ContractWalletFlowRecord contractWalletFlowRecord2 = new ContractWalletFlowRecord();
            contractWalletFlowRecord2.setMember(order.getMember());
            contractWalletFlowRecord2.setCoin(coin);
            contractWalletFlowRecord2.setAmount(reward);
            contractWalletFlowRecord2.setAfterBalance(balance);
            contractWalletFlowRecord2.setOperationType(ContractWalletOperationType.CONTRACT_LOSS_REWARD);
            contractWalletFlowRecord2.setRelationDetailId(order.getId());
            contractWalletFlowRecord2.setVersion(0L);
            contractWalletFlowRecord2.setCreateTime(new Date());
            contractWalletFlowRecord2.setSequence(System.currentTimeMillis());
            contractWalletFlowRecord2.setContractType((int) order.getWalletType());
            this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord2);
            final MemberTransaction transaction = new MemberTransaction();
            transaction.setAmount(reward);
            transaction.setSymbol(coin.getName());
            transaction.setAddress("");
            transaction.setMemberId(order.getMember().getId());
            transaction.setType(TransactionType.CONTRACT_LOSS_RECOMMEND);
            transaction.setFee(BigDecimal.ZERO);
            transaction.setCreateTime(new Date());
            transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
            transaction.setAfterBalance(balance);
            this.memberTransactionService.save(transaction);
        }
        ContractExchangeOrderClose orderClose = new ContractExchangeOrderClose();
        orderClose.setOrderId(order.getId());
        orderClose.setClosePrice(closePrice);
        orderClose.setProfitOrLoss(lastRate);
        orderClose.setCloseProfitOrLoss(profit);
        orderClose.setType(ContractExchangeOrderCloseType.CLOSE_AUTO);
        orderClose.setCreateTime(new Date());
        orderClose.setSequence(System.currentTimeMillis());
        orderClose.setVersion(0L);
        orderClose = this.contractExchangeOrderCloseService.save(orderClose);
        if (null == orderClose) {
            throw new RuntimeException();
        }
        num = this.contractOrderService.updateOrderStatus(order.getId(), status, order.getVersion());
        if (num <= 0) {
            return MessageResult.error(this.msService.getMessage("ERROR"));
        }
        return MessageResult.success();
    }

    private List<ContractExchangeOrder> findMemberHoldingOrders(final Long memberId, final String coin) {
        return (List<ContractExchangeOrder>) this.contractOrderService.findMemberHoldingOrders(memberId, coin);
    }

    public MessageResult getExternalExchangeOrders(final long memberId, final int pageNo, final int pageSize, final Integer fromExchangeId, final Integer status) {
        if (status == 1) {
            Page<ContractDoubleMemberStrategyOrder> list = this.contractDoubleMemberStrategyOrderService.getExternalOpenExchangeOrders(Long.valueOf(memberId), pageNo, pageSize, fromExchangeId);
            Map<Long, ContractDoubleExchangeConfig> exchangeMap = new HashMap<Long, ContractDoubleExchangeConfig>();
            if (null != list && null != list.getContent() && list.getContent().size() > 0) {
                List<ContractDoubleExchangeConfig> exchangeList = this.contractDoubleExchangeConfigService.findAll();
                exchangeList.forEach(model -> exchangeMap.put(model.getId(), model));
                list.getContent().forEach(model -> model.setFromExchangeName(exchangeMap.containsKey(model.getFromExchangeId()) ? exchangeMap.get(model.getFromExchangeId()).getName() : ""));
            }
            return MessageResult.success("成功", list);
        } else if (status == 2) {
            Map<Long, ContractDoubleExchangeConfig> exchangeMap = new HashMap<Long, ContractDoubleExchangeConfig>();
            Page<ContractDoubleMemberStrategyOrder> list = this.contractDoubleMemberStrategyOrderService.getExternalHistoryExchangeOrders(memberId, pageNo, pageSize, fromExchangeId);
            List<ContractDoubleExchangeConfig> exchangeList = this.contractDoubleExchangeConfigService.findAll();
            exchangeList.forEach(model -> exchangeMap.put(model.getId(), model));
            list.getContent().forEach(model -> model.setFromExchangeName(exchangeMap.containsKey(model.getFromExchangeId()) ? exchangeMap.get(model.getFromExchangeId()).getName() : ""));
            return MessageResult.success("成功", list);
        }
        return MessageResult.error("参数异常");
    }

    public MessageResult cancelExternalExchangeOrder(final Long memberId, final Long id) {
        final ContractDoubleMemberStrategyOrder strategyOrder = this.contractDoubleMemberStrategyOrderService.findById(id);
        if (null == strategyOrder) {
            return MessageResult.error("外部订单不存在");
        }
        if (!memberId.equals(strategyOrder.getMemberId())) {
            return MessageResult.error("只能操作自己的外部订单");
        }
        this.kafkaTemplate.send("double-contract-order-offset-strategy-close-handler", String.valueOf(id), String.valueOf(id));
        return MessageResult.success("平仓申请提交成功");
    }

    public MessageResult getOneClickOrders(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Page<ContractExchangeOrder> list = (Page<ContractExchangeOrder>) this.contractOrderService.getOneClickOrders(memberId, symbol, pageNo, pageSize, contractType, walletType);
        list.getContent().forEach(model -> {
            model.setSymbolCurrentPrice(this.marketService.findNewestPrice(model.getSymbol()));
            return;
        });
        return MessageResult.success("成功获取一键订单数据", list);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public MessageResult cancelOneClickOrder(final Long memberId, final Long orderId) {
        final ContractExchangeOrder order = this.contractOrderService.findOne(orderId);
        if (null == order) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_EXIST"));
        }
        if (!order.getMember().getId().equals(memberId)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_NOT_MATCH_MEMBER"));
        }
        if (!order.getStatus().equals(ContractOrderStatus.ONE_CLICK_NO_CONFIRM)) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_ORDER_STATUS_ALREADY_CHANGE"));
        }
        if (order.getSequence() + 30000L > System.currentTimeMillis()) {
            return MessageResult.error(500, "下单30秒内不允许撤单");
        }
        int num = this.contractOrderService.updateOrderStatus(order.getId(), ContractOrderStatus.ONE_CLICK_CANCEL, order.getVersion());
        if (num <= 0) {
            throw new RuntimeException();
        }
        this.kafkaTemplate.send("double-contract-order-offset-strategy-cancel-handler", String.valueOf(orderId), String.valueOf(orderId));
        return MessageResult.success("撤单申请成功");
    }
}
