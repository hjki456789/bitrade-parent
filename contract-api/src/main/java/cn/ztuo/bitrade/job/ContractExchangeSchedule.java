package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.scheduling.annotation.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.util.*;

import java.math.*;

import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

import java.util.*;

@Component
public class ContractExchangeSchedule {
    private static final Logger log;
    @Autowired
    private ContractExchangeService contractExchangeService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private ContractCoinService contractCoinService;
    @Autowired
    private ContractCoinInfoService contractCoinInfoService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ContractDoubleDirectionProfitInfoService contractDoubleDirectionProfitInfoService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private MemberWalletService memberWalletService;
    private static Map<String, BigDecimal> symbolPriceMap;

    @Scheduled(fixedDelay = 1000L)
    public void contractLimitOrderToHold() {
        ContractExchangeSchedule.log.info("=========================合约限价单处理============================START");
        final List<ContractCoin> coins = this.contractCoinService.findAllEnabled();
        for (final ContractCoin coin : coins) {
            final String symbol = coin.getSymbol();
            BigDecimal closePrice = this.marketService.findNewestPrice(symbol);
            if (null != closePrice) {
                if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                closePrice = ((null != closePrice) ? closePrice : BigDecimal.ZERO);
                if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                final List<ContractExchangeOrder> orderList = this.contractExchangeService.findAllEffectiveLimitOrder(symbol);
                for (final ContractExchangeOrder contractExchangeOrder : orderList) {
                    BigDecimal price = contractExchangeOrder.getPrice();
                    price = ((null != price) ? price : BigDecimal.ZERO);
                    BigDecimal triggerPrice = contractExchangeOrder.getLimitTriggerPrice();
                    triggerPrice = ((null != triggerPrice) ? triggerPrice : BigDecimal.ZERO);
                    BigDecimal limitPrice = contractExchangeOrder.getLimitPrice();
                    limitPrice = ((null != limitPrice) ? limitPrice : BigDecimal.ZERO);
                    if ((price.compareTo(triggerPrice) <= 0 && triggerPrice.compareTo(closePrice) <= 0) || (price.compareTo(triggerPrice) >= 0 && triggerPrice.compareTo(closePrice) >= 0)) {
                        this.contractExchangeService.changeLimitOrderToHolding(Long.valueOf(contractExchangeOrder.getId()), triggerPrice);
                    }
                }
            }
        }
        ContractExchangeSchedule.log.info("=========================合约限价单处理============================END");
    }

    @Scheduled(cron = "1 0 0 * * ?")
    public void settlementHoldingOrderInterest() {
        ContractExchangeSchedule.log.info("=========================结算持仓利息============================START");
        final List<ContractCoin> coins = this.contractCoinService.findAllEnabled();
        for (final ContractCoin coin : coins) {
            final String symbol = coin.getSymbol();
            final BigDecimal dayFeeRate = coin.getDayFeeRate();
            final List<ContractExchangeOrder> orderList = this.contractExchangeService.findAllHoldingOrders(symbol);
            for (final ContractExchangeOrder contractExchangeOrder : orderList) {
                final BigDecimal allMoney = contractExchangeOrder.getCapitalAmount().multiply(contractExchangeOrder.getLeverMultiple());
                final BigDecimal dayFee = allMoney.multiply(dayFeeRate);
                this.contractExchangeService.saveHoldingOrderInterest(contractExchangeOrder.getId(), dayFee);
            }
        }
        ContractExchangeSchedule.log.info("=========================结算持仓利息============================END");
    }

    @Scheduled(fixedDelay = 1000L)
    public void calculateOrder() {
        ContractExchangeSchedule.log.info("=========================更新合约订单============================START");
        final List<ContractCoin> symbols = this.contractCoinService.findAllEnabled();
        for (final ContractCoin symbolModel : symbols) {
            final String symbol = symbolModel.getSymbol();
            BigDecimal closePrice = this.marketService.findNewestPrice(symbol);
            if (null != closePrice) {
                if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                closePrice = ((null != closePrice) ? closePrice : BigDecimal.ZERO);
                if (ContractExchangeSchedule.symbolPriceMap.containsKey(symbol) && ContractExchangeSchedule.symbolPriceMap.get(symbol).compareTo(closePrice) == 0) {
                    continue;
                }
                ContractExchangeSchedule.symbolPriceMap.put(symbol, closePrice);
                if (closePrice.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                ContractExchangeSchedule.log.info("===币种{}===价格：{}", symbol, closePrice);
                this.kafkaTemplate.send("contract-exchange-symbol-price-calculate", closePrice.toString(), symbol);
            }
        }
        ContractExchangeSchedule.log.info("=========================更新合约订单============================END");
    }

    @Scheduled(fixedDelay = 1000L)
    public void explodeBalance() {
        ContractExchangeSchedule.log.info("=========================爆仓-止盈-止损处理============================START");
        final List<BigInteger> memberIds = this.contractExchangeService.findMemberIds(ContractOrderStatus.HOLD_PROCESSING.getOrdinal());
        for (final BigInteger memberId : memberIds) {
            final String coin = "USDT";
            final List<Long> orderIds = this.contractExchangeService.findMemberOrderIds(Long.valueOf(memberId.longValue()), coin, ContractOrderStatus.HOLD_PROCESSING.getOrdinal());
            if (null != orderIds && orderIds.size() > 0) {
                ContractExchangeSchedule.log.info("向Kafka推送消息：会员：" + memberId + "，订单：" + JSON.toJSONString(orderIds));
                this.kafkaTemplate.send("contract-exchange-order-status-deal", coin, JSON.toJSONString(orderIds));
            }
        }
        ContractExchangeSchedule.log.info("=========================爆仓-止盈-止损处理============================END");
    }

    @Scheduled(cron = "0 30 0 * * ?")
    public void contractDoubleDirectionProfitReward() {
        ContractExchangeSchedule.log.info("=========================双仓合约盈利释放开始============================START");
        final List<ContractDoubleDirectionOrderProfitInfo> dataList = this.contractDoubleDirectionProfitInfoService.getProfitInfoListByStatus(0);
        if (dataList != null) {
            for (final ContractDoubleDirectionOrderProfitInfo profitInfo : dataList) {
                final long todaySequence = DateUtil.getTodaySequence();
                if (todaySequence < profitInfo.getSequence()) {
                    continue;
                }
                if (profitInfo.getRemainDays() == 0) {
                    continue;
                }
                final BigDecimal amount = profitInfo.getProfit().divide(new BigDecimal(profitInfo.getDays()), 8, RoundingMode.HALF_UP);
                if (amount.doubleValue() < 0.0) {
                    continue;
                }
                final MemberWallet memberWallet = this.memberWalletService.findOneByCoinNameAndMemberId("USDT", (long) profitInfo.getMemberId());
                if (memberWallet == null) {
                    continue;
                }
                memberWallet.setBalance(memberWallet.getBalance().add(amount));
                memberWallet.setFrozenBalance(memberWallet.getFrozenBalance().subtract(amount));
                if (memberWallet.getFrozenBalance().doubleValue() < 0.0) {
                    memberWallet.setFrozenBalance(BigDecimal.ZERO);
                }
                final int num = this.memberWalletService.updateBalanceAndFrozenBalance(memberWallet.getId(), memberWallet.getBalance(), memberWallet.getFrozenBalance());
                if (num <= 0) {
                    continue;
                }
                final int remainDays = profitInfo.getRemainDays() - 1;
                int status = profitInfo.getStatus();
                if (remainDays == 0) {
                    status = 1;
                }
                this.contractDoubleDirectionProfitInfoService.updateInfo(profitInfo.getId(), status, remainDays);
                final MemberTransaction transaction = new MemberTransaction();
                transaction.setAmount(amount);
                transaction.setSymbol(memberWallet.getCoin().getName());
                transaction.setAddress("");
                transaction.setMemberId(memberWallet.getMemberId());
                transaction.setType(TransactionType.CONTRACT_PROFIT_RECOMMEND);
                transaction.setFee(BigDecimal.ZERO);
                transaction.setCreateTime(new Date());
                transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
                transaction.setAfterBalance(memberWallet.getBalance());
                this.memberTransactionService.save(transaction);
            }
        }
        ContractExchangeSchedule.log.info("=========================双仓合约盈利释放结束============================END");
    }

    static {
        log = LoggerFactory.getLogger((Class) ContractExchangeSchedule.class);
        ContractExchangeSchedule.symbolPriceMap = new HashMap<String, BigDecimal>();
    }
}
