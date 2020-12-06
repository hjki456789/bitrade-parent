package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

import java.math.*;

import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;

import cn.ztuo.bitrade.util.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.constant.*;
import org.slf4j.*;

@Service
public class ContractWalletService {
    private static final Logger log;
    @Autowired
    private ContractWalletRepository contractWalletRepository;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private MemberTransactionService transactionService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;
    @Autowired
    private MemberAccountOperateRecordService memberAccountOperateRecordService;

    public Page<ContractWallet> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<ContractWallet>) this.contractWalletRepository.findAll(predicate, pageable);
    }

    public int updateIsLock(final Long id, final Integer isLock) {
        return this.contractWalletRepository.updateIsLock(id, isLock);
    }

    public ContractWallet findByMemberId(final long memberId) {
        return this.contractWalletRepository.findByMemberId(memberId);
    }

    public ContractWallet findByMemberIdAndCoin(final long memberId, final String coinId) {
        return this.contractWalletRepository.findByMemberIdAndCoin(memberId, coinId);
    }

    public ContractWallet findOne(final Long id) {
        return this.contractWalletRepository.findById(id);
    }

    public int updateContractWalletBalance(final ContractWallet contractWallet) {
        return this.contractWalletRepository.updateContractWalletBalance(contractWallet.getBalance(), contractWallet.getFrozenBalance(), contractWallet.getVirtualRechargeFrozenBalance(), contractWallet.getId(), contractWallet.getVersion());
    }

    @Transactional(rollbackFor = {Exception.class})
    public int changeBalance(final ContractWallet contractWallet, final BigDecimal changeBalance, final ContractWalletOperationType operationType, final String remark, final String adminId) {
        contractWallet.setBalance(contractWallet.getBalance().add(changeBalance));
        final Long memberId = contractWallet.getMember().getId();
        final String coinId = contractWallet.getCoin().getName();
        final int result = this.updateContractWalletBalance(contractWallet);
        if (result > 0) {
            final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord();
            contractWalletFlowRecord.setMember(new Member(memberId));
            contractWalletFlowRecord.setCoin(new Coin(coinId));
            contractWalletFlowRecord.setAmount(changeBalance);
            contractWalletFlowRecord.setAfterBalance(contractWallet.getBalance());
            contractWalletFlowRecord.setOperationType(operationType);
            contractWalletFlowRecord.setRelationDetailId(contractWallet.getId());
            contractWalletFlowRecord.setRemark(remark);
            contractWalletFlowRecord.setCreateTime(new Date());
            contractWalletFlowRecord.setSequence(System.currentTimeMillis());
            final MessageResult result2 = this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
            if (result2.getCode() == 0) {
                final MemberAccountOperateRecord record = new MemberAccountOperateRecord();
                record.setMemberId(memberId);
                record.setChangeAmount(changeBalance);
                record.setCoin(coinId);
                record.setBalanceType(BalanceTypeEnum.BALANCE);
                record.setAccountType(AccountTypeEnum.CONTRACT);
                record.setAdminUserId(adminId);
                record.setSequence(Long.valueOf(System.currentTimeMillis()));
                record.setCreateTime(new Date());
                this.memberAccountOperateRecordService.save(record);
                return 1;
            }
        }
        return 0;
    }

    public ContractWallet insertContractWallet(final Long memberId, final String coinId) {
        final ContractWallet contractWallet = new ContractWallet();
        contractWallet.setMember(new Member(memberId));
        contractWallet.setCoin(new Coin(coinId));
        contractWallet.setBalance(BigDecimal.ZERO);
        contractWallet.setFrozenBalance(BigDecimal.ZERO);
        contractWallet.setVirtualRechargeFrozenBalance(BigDecimal.ZERO);
        return this.save(contractWallet);
    }

    public ContractWallet save(final ContractWallet contractWallet) {
        return (ContractWallet) this.contractWalletRepository.saveAndFlush(contractWallet);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Integer coin2Contract(final MemberWallet memberWallet, final ContractWallet contractWallet, final BigDecimal amount) {
        this.memberWalletService.decreaseBalance(memberWallet.getId(), amount, memberWallet.getVersion());
        contractWallet.setBalance(contractWallet.getBalance().add(amount));
        final Integer addResult = this.contractWalletRepository.updateContractWalletBalance(contractWallet.getBalance(), contractWallet.getFrozenBalance(), contractWallet.getVirtualRechargeFrozenBalance(), contractWallet.getId(), contractWallet.getVersion());
        if (addResult == 0) {
            ContractWalletService.log.error("\u5408\u7ea6\u8d26\u6237\u66f4\u65b0\u5931\u8d25\uff1a\u5408\u7ea6\u8d26\u6237ID:" + contractWallet.getId() + " \u91d1\u989d\uff1a" + amount);
            return 0;
        }
        final MemberTransaction transaction = new MemberTransaction();
        transaction.setAmount(amount);
        transaction.setSymbol(memberWallet.getCoin().getName());
        transaction.setAddress("");
        transaction.setMemberId(memberWallet.getMemberId());
        transaction.setType(TransactionType.COIN_TWO_CONTRACT);
        transaction.setFee(BigDecimal.ZERO);
        transaction.setCreateTime(new Date());
        transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
        this.transactionService.save(transaction);
        return 1;
    }

    @Transactional(rollbackFor = {Exception.class})
    public Integer Contract2Coin(final MemberWallet memberWallet, final ContractWallet contractWallet, final BigDecimal amount) {
        this.memberWalletService.increaseBalance(memberWallet.getId(), amount, memberWallet.getVersion());
        contractWallet.setBalance(contractWallet.getBalance().subtract(amount));
        final Integer addResult = this.contractWalletRepository.updateContractWalletBalance(contractWallet.getBalance(), contractWallet.getFrozenBalance(), contractWallet.getVirtualRechargeFrozenBalance(), contractWallet.getId(), contractWallet.getVersion());
        if (addResult == 0) {
            ContractWalletService.log.error("\u5408\u7ea6\u8d26\u6237\u51cf\u5c11\u5931\u8d25\uff1a\u5408\u7ea6\u8d26\u6237ID:" + contractWallet.getId() + " \u91d1\u989d\uff1a" + amount);
            return 0;
        }
        final MemberTransaction transaction = new MemberTransaction();
        transaction.setAmount(amount);
        transaction.setSymbol(memberWallet.getCoin().getName());
        transaction.setAddress("");
        transaction.setMemberId(memberWallet.getMemberId());
        transaction.setType(TransactionType.CONTRACT_TWO_CONIN);
        transaction.setFee(BigDecimal.ZERO);
        transaction.setCreateTime(new Date());
        transaction.setSequence(Long.valueOf(System.currentTimeMillis()));
        this.transactionService.save(transaction);
        return 1;
    }

    static {
        log = LoggerFactory.getLogger((Class) ContractWalletService.class);
    }
}
