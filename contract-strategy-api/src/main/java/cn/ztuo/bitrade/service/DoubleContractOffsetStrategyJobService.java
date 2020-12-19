package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import cn.ztuo.bitrade.exception.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;
import java.math.*;

import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@Service
public class DoubleContractOffsetStrategyJobService {
    private static final Logger log;
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractDoubleDirectionWalletService contractDoubleDirectionWalletService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;
    @Autowired
    private DepositWalletService depositWalletService;

    @Transactional(rollbackFor = {Exception.class})
    public void markOrderComplete(final ContractDoubleMemberStrategyOrder strategyOrder, final int matchStatus) throws Exception {
        final ContractExchangeOrder contractExchangeOrder = this.contractOrderService.findOne(strategyOrder.getContractOrderId());
        if (null == contractExchangeOrder) {
            DoubleContractOffsetStrategyJobService.log.info("Find ContractExchangeOrder->ERROR");
            return;
        }
        int num = this.contractDoubleMemberStrategyOrderService.updateMatchStatus(strategyOrder.getId(), matchStatus);
        if (num <= 0) {
            DoubleContractOffsetStrategyJobService.log.info("updateMatchStatus->ERROR");
            throw new InformationExpiredException("Information Expired");
        }
        num = this.contractOrderService.updateOrderStatus(contractExchangeOrder.getId(), ContractOrderStatus.HOLD_PROCESSING, contractExchangeOrder.getVersion());
        if (num <= 0) {
            DoubleContractOffsetStrategyJobService.log.info("updateOrderStatus->ERROR");
            throw new InformationExpiredException("Information Expired");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void markOrderRetracement(final ContractDoubleMemberStrategyOrder strategyOrder, final int matchStatus) throws Exception {
        final int num = this.contractDoubleMemberStrategyOrderService.updateMatchStatus(strategyOrder.getId(), matchStatus);
        if (num <= 0) {
            throw new InformationExpiredException("Information Expired");
        }
        this.markOrderRetracement(strategyOrder.getContractOrderId());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void markOrderRetracement(final Long orderId) throws Exception {
        final ContractExchangeOrder contractExchangeOrder = this.contractOrderService.findOne(orderId);
        if (null == contractExchangeOrder) {
            return;
        }
        if (!contractExchangeOrder.getStatus().equals((Object) ContractOrderStatus.ONE_CLICK_NO_CONFIRM) && !contractExchangeOrder.getStatus().equals(ContractOrderStatus.ONE_CLICK_CANCEL_APPLY)) {
            return;
        }
        int num = this.contractOrderService.updateOrderStatus(contractExchangeOrder.getId(), ContractOrderStatus.ONE_CLICK_CANCEL, contractExchangeOrder.getVersion());
        if (num <= 0) {
            throw new InformationExpiredException("Information Expired");
        }
        if (contractExchangeOrder.getWalletType() == 1) {
            final ContractDoubleDirectionWallet wallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(contractExchangeOrder.getMember().getId(), contractExchangeOrder.getCoinId());
            final BigDecimal balance = wallet.getBalance().add(contractExchangeOrder.getCapitalAmount());
            final BigDecimal frozenBalance = wallet.getFrozenBalance().subtract(contractExchangeOrder.getCapitalAmount());
            num = this.contractDoubleDirectionWalletService.updateWalletBalance(wallet.getId(), balance, frozenBalance, wallet.getVersion());
            if (num <= 0) {
                throw new InformationExpiredException("Information Expired");
            }
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(contractExchangeOrder.getMember());
            contractWalletFlowRecord.setCoin(new Coin(contractExchangeOrder.getCoinId()));
            contractWalletFlowRecord.setAmount(contractExchangeOrder.getCapitalAmount());
            contractWalletFlowRecord.setAfterBalance(balance);
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.DOUBLE_CONTRACT_ORDER_FAIL_RETURN);
            contractWalletFlowRecord.setRelationDetailId((long) wallet.getId());
            contractWalletFlowRecord.setRemark("双仓合约订单外部下单失败返还");
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            final MessageResult mr = this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
            if (mr.getCode() != 0) {
                throw new InformationExpiredException("Information Expired");
            }
        } else if (contractExchangeOrder.getWalletType() == 2) {
            final DepositWallet wallet2 = this.depositWalletService.getDepositWalletByMemberIdAndCoin(contractExchangeOrder.getMember().getId(), contractExchangeOrder.getCoinId());
            final BigDecimal balance = wallet2.getBalance().add(contractExchangeOrder.getCapitalAmount());
            final BigDecimal frozenBalance = wallet2.getFrozenBalance().subtract(contractExchangeOrder.getCapitalAmount());
            num = this.depositWalletService.updateWalletBalance(wallet2.getId(), balance, frozenBalance, wallet2.getVersion());
            if (num <= 0) {
                throw new InformationExpiredException("Information Expired");
            }
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(contractExchangeOrder.getMember());
            contractWalletFlowRecord.setCoin(new Coin(contractExchangeOrder.getCoinId()));
            contractWalletFlowRecord.setAmount(contractExchangeOrder.getCapitalAmount());
            contractWalletFlowRecord.setAfterBalance(balance);
            contractWalletFlowRecord.setOperationType(ContractWalletOperationType.DEPOSIT_CONTRACT_ORDER_FAIL_RETURN);
            contractWalletFlowRecord.setRelationDetailId((long) wallet2.getId());
            contractWalletFlowRecord.setRemark("托管合约订单外部下单失败返还");
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            final MessageResult mr = this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
            if (mr.getCode() != 0) {
                throw new InformationExpiredException("Information Expired");
            }
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) DoubleContractOffsetStrategyJobService.class);
    }
}
