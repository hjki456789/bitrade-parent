package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import java.math.*;
import org.springframework.data.domain.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.pagination.*;
import org.apache.commons.lang3.*;
import org.springframework.data.jpa.domain.*;
import java.util.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.constant.*;
import org.slf4j.*;

@Service
public class ContractDoubleDirectionWalletService extends BaseService
{
    private static final Logger log;
    @Autowired
    private ContractDoubleDirectionWalletDao contractDoubleDirectionWalletDao;
    @Autowired
    private CoinDao coinDao;
    @Autowired
    private MemberTransactionService transactionService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberAccountOperateRecordService memberAccountOperateRecordService;
    @Autowired
    private MemberWalletService memberWalletService;
    public static final Integer limit;
    private static Logger logger;

    public ContractDoubleDirectionWallet save(final ContractDoubleDirectionWallet wallet) {
        return (ContractDoubleDirectionWallet)this.contractDoubleDirectionWalletDao.saveAndFlush(wallet);
    }

    public ContractDoubleDirectionWallet getContractDoubleDirectionWalletByMemberIdAndCoin(final Long memberId, final String coinName) {
        return this.contractDoubleDirectionWalletDao.findCoinIdAndMemberId(coinName, memberId);
    }

    public int updateBalance(final ContractDoubleDirectionWallet wallet) {
        return this.contractDoubleDirectionWalletDao.updateBalance(wallet.getId(), wallet.getBalance(), wallet.getFrozenBalance());
    }

    public int updateWalletBalance(final Long id, final BigDecimal balance, final BigDecimal frozenBalance, final int version) {
        return this.contractDoubleDirectionWalletDao.updateWalletBalance(id, balance, frozenBalance, version);
    }

    public List<ContractDoubleDirectionWallet> queryByMemberId(final Long memberId) {
        return this.contractDoubleDirectionWalletDao.queryByMemberId(memberId);
    }

    public Page<ContractDoubleDirectionWallet> findPage(final List<Long> memberIds, final String coinId, final Integer isLock, final Pageable pageable) {
        final Criteria<ContractDoubleDirectionWallet> specification = new Criteria<ContractDoubleDirectionWallet>();
        if (!CollectionUtils.isEmpty((Collection)memberIds)) {
            specification.add(Restrictions.in("memberId", memberIds, true));
        }
        if (StringUtils.isNotEmpty((CharSequence)coinId)) {
            specification.add(Restrictions.eq("coin.name", coinId, true));
        }
        if (null != isLock) {
            specification.add(Restrictions.eq("isLock", isLock, true));
        }
        final Page<ContractDoubleDirectionWallet> page = (Page<ContractDoubleDirectionWallet>)this.contractDoubleDirectionWalletDao.findAll((Specification)specification, pageable);
        for (final ContractDoubleDirectionWallet wallet : page.getContent()) {
            final Member member = this.memberService.findOne(wallet.getMemberId());
            wallet.setUsername(member.getUsername());
            wallet.setMobilePhone(member.getMobilePhone());
            wallet.setRealName(member.getRealName());
            wallet.setEmail(member.getEmail());
        }
        return page;
    }

    @Transactional(rollbackFor = { Exception.class })
    public int changeBalance(final ContractDoubleDirectionWallet contractDoubleDirectionWallet, final BigDecimal changeBalance, final String adminId) {
        contractDoubleDirectionWallet.setBalance(contractDoubleDirectionWallet.getBalance().add(changeBalance));
        final Long memberId = contractDoubleDirectionWallet.getMemberId();
        final String coinId = contractDoubleDirectionWallet.getCoin().getName();
        this.updateBalance(contractDoubleDirectionWallet);
        final MemberAccountOperateRecord record = new MemberAccountOperateRecord();
        record.setMemberId(memberId);
        record.setChangeAmount(changeBalance);
        record.setCoin(coinId);
        record.setBalanceType(BalanceTypeEnum.BALANCE);
        record.setAccountType(AccountTypeEnum.DOUBLE_DIRECTION);
        record.setAdminUserId(adminId);
        record.setSequence(System.currentTimeMillis());
        record.setCreateTime(new Date());
        this.memberAccountOperateRecordService.save(record);
        return 1;
    }

    public ContractDoubleDirectionWallet findOne(final Long id) {
        return this.contractDoubleDirectionWalletDao.getOne(id);
    }

    public int updateIsLock(final Long id, final Integer isLock) {
        return this.contractDoubleDirectionWalletDao.updateIsLock(id, isLock);
    }

    @Transactional(rollbackFor = { Exception.class })
    public Integer coin2DoubleDirection(final MemberWallet memberWallet, final ContractDoubleDirectionWallet contractDoubleDirectionWallet, final BigDecimal amount) {
        this.memberWalletService.decreaseBalance(memberWallet.getId(), amount, memberWallet.getVersion());
        contractDoubleDirectionWallet.setBalance(contractDoubleDirectionWallet.getBalance().add(amount));
        final Integer addResult = this.contractDoubleDirectionWalletDao.updateBalance(contractDoubleDirectionWallet.getId(), contractDoubleDirectionWallet.getBalance(), contractDoubleDirectionWallet.getFrozenBalance());
        if (addResult == 0) {
            ContractDoubleDirectionWalletService.log.error("\u53cc\u4ed3\u5408\u7ea6\u8d26\u6237\u66f4\u65b0\u5931\u8d25\uff1a\u5408\u7ea6\u8d26\u6237ID:" + contractDoubleDirectionWallet.getId() + " \u91d1\u989d\uff1a" + amount);
            return 0;
        }
        final MemberTransaction transaction = new MemberTransaction();
        transaction.setAmount(amount);
        transaction.setSymbol(memberWallet.getCoin().getName());
        transaction.setAddress("");
        transaction.setMemberId(memberWallet.getMemberId());
        transaction.setType(TransactionType.COIN_TWO_DOUBLEDIRECTION);
        transaction.setFee(BigDecimal.ZERO);
        transaction.setCreateTime(new Date());
        transaction.setSequence(System.currentTimeMillis());
        this.transactionService.save(transaction);
        return 1;
    }

    @Transactional(rollbackFor = { Exception.class })
    public Integer doubleDirection2Coin(final MemberWallet memberWallet, final ContractDoubleDirectionWallet contractDoubleDirectionWallet, final BigDecimal amount) {
        this.memberWalletService.increaseBalance(memberWallet.getId(), amount, memberWallet.getVersion());
        contractDoubleDirectionWallet.setBalance(contractDoubleDirectionWallet.getBalance().subtract(amount));
        final Integer addResult = this.contractDoubleDirectionWalletDao.updateBalance(contractDoubleDirectionWallet.getId(), contractDoubleDirectionWallet.getBalance(), contractDoubleDirectionWallet.getFrozenBalance());
        if (addResult == 0) {
            ContractDoubleDirectionWalletService.log.error("\u53cc\u4ed3\u5408\u7ea6\u8d26\u6237\u51cf\u5c11\u5931\u8d25\uff1a\u5408\u7ea6\u8d26\u6237ID:" + contractDoubleDirectionWallet.getId() + " \u91d1\u989d\uff1a" + amount);
            return 0;
        }
        final MemberTransaction transaction = new MemberTransaction();
        transaction.setAmount(amount);
        transaction.setSymbol(memberWallet.getCoin().getName());
        transaction.setAddress("");
        transaction.setMemberId(memberWallet.getMemberId());
        transaction.setType(TransactionType.DOUBLEDIRECTION_TWO_COIN);
        transaction.setFee(BigDecimal.ZERO);
        transaction.setCreateTime(new Date());
        transaction.setSequence(System.currentTimeMillis());
        this.transactionService.save(transaction);
        return 1;
    }

    static {
        log = LoggerFactory.getLogger((Class)ContractDoubleDirectionWalletService.class);
        limit = 1000;
        ContractDoubleDirectionWalletService.logger = LoggerFactory.getLogger((Class)ContractDoubleDirectionWalletService.class);
    }
}
