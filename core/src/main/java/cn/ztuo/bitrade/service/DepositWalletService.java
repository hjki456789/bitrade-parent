package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.data.domain.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.pagination.*;
import org.apache.commons.lang3.*;
import org.springframework.data.jpa.domain.*;

import java.math.*;

import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.constant.*;

import java.util.*;

import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Service
public class DepositWalletService extends BaseService {
    @Autowired
    private DepositWalletDao depositWalletDao;
    @Autowired
    private CoinDao coinDao;
    @Autowired
    private MemberTransactionService transactionService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberAccountOperateRecordService memberAccountOperateRecordService;
    public static final Integer limit;
    private static Logger logger;

    public DepositWallet save(final DepositWallet wallet) {
        return (DepositWallet) this.depositWalletDao.saveAndFlush(wallet);
    }

    public DepositWallet getDepositWalletByMemberIdAndCoin(final Long memberId, final String coinName) {
        DepositWallet depositWallet = this.depositWalletDao.findCoinIdAndMemberId(coinName, memberId);
        if (depositWallet == null) {
            depositWallet = new DepositWallet();
            depositWallet.setMemberId(memberId);
            Coin coin = this.coinDao.findByUnit(coinName);
            if (coin == null) {
                coin = new Coin();
                coin.setName(coinName);
                coin.setUnit(coinName);
                coin = (Coin) this.coinDao.saveAndFlush(coin);
            }
            depositWallet.setCoin(coin);
            depositWallet = (DepositWallet) this.depositWalletDao.saveAndFlush(depositWallet);
        }
        return depositWallet;
    }

    public int updateBalance(final DepositWallet wallet) {
        return this.depositWalletDao.updateBalance(wallet.getId(), wallet.getBalance(), wallet.getFrozenBalance(), wallet.getTotalInBalance());
    }

    public List<DepositWallet> queryByMemberId(final Long memberId) {
        return this.depositWalletDao.queryByMemberId(memberId);
    }

    public Page<DepositWallet> findPage(final List<Long> memberIds, final String coinId, final Integer isLock, final Pageable pageable) {
        final Criteria<DepositWallet> specification = new Criteria<DepositWallet>();
        if (!CollectionUtils.isEmpty((Collection) memberIds)) {
            specification.add(Restrictions.in("memberId", memberIds, true));
        }
        if (StringUtils.isNotEmpty((CharSequence) coinId)) {
            specification.add(Restrictions.eq("coin.name", coinId, true));
        }
        if (null != isLock) {
            specification.add(Restrictions.eq("isLock", isLock, true));
        }
        final Page<DepositWallet> page = (Page<DepositWallet>) this.depositWalletDao.findAll((Specification) specification, pageable);
        for (final DepositWallet wallet : page.getContent()) {
            final Member member = this.memberService.findOne(wallet.getMemberId());
            wallet.setUsername(member.getUsername());
            wallet.setMobilePhone(member.getMobilePhone());
            wallet.setRealName(member.getRealName());
            wallet.setEmail(member.getEmail());
        }
        return page;
    }

    @Transactional(rollbackFor = {Exception.class})
    public int changeBalance(final DepositWallet depositWallet, final BigDecimal changeBalance, final String adminId) {
        depositWallet.setBalance(depositWallet.getBalance().add(changeBalance));
        final Long memberId = depositWallet.getMemberId();
        final String coinId = depositWallet.getCoin().getName();
        this.updateBalance(depositWallet);
        final MemberAccountOperateRecord record = new MemberAccountOperateRecord();
        record.setMemberId(memberId);
        record.setChangeAmount(changeBalance);
        record.setCoin(coinId);
        record.setBalanceType(BalanceTypeEnum.BALANCE);
        record.setAccountType(AccountTypeEnum.DEPOSIT);
        record.setAdminUserId(adminId);
        record.setSequence(System.currentTimeMillis());
        record.setCreateTime(new Date());
        this.memberAccountOperateRecordService.save(record);
        return 1;
    }

    public DepositWallet findOne(final Long id) {
        return this.depositWalletDao.getOne(id);
    }

    public int updateIsLock(final Long id, final Integer isLock) {
        return this.depositWalletDao.updateIsLock(id, isLock);
    }

    public int updateWalletBalance(final Long id, final BigDecimal balance, final BigDecimal frozenBalance, final int version) {
        return this.depositWalletDao.updateWalletBalance(id, balance, frozenBalance, version);
    }

    static {
        limit = 1000;
        DepositWalletService.logger = LoggerFactory.getLogger((Class) DepositWalletService.class);
    }
}
