package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;

import java.math.*;

import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.transaction.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

import java.util.*;

import cn.ztuo.bitrade.entity.*;

@Service
public class MemberDepositInfoService extends BaseService {
    @Autowired
    private MemberDepositInfoDao memberDepositInfoDao;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private DepositTypeInfoDao depositTypeInfoDao;
    @Autowired
    private MemberWalletDao memberWalletDao;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private DepositProfitInfoService depositProfitInfoService;
    @Autowired
    private DepositRecommendProfitInfoService depositRecommendProfitInfoService;

    public MemberDepositInfo save(final MemberDepositInfo memberDepositInfo) {
        return (MemberDepositInfo) this.memberDepositInfoDao.save(memberDepositInfo);
    }

    public List<MemberDepositInfo> findListByMemberId(final Long memberId, final Long lastSequence) {
        return this.memberDepositInfoDao.getDepositListByMemberId(memberId, lastSequence);
    }

    @Transactional(rollbackFor = {Exception.class})
    public MessageResult addDeposit(final Long memberId, final BigDecimal amount, final Long depositTypeId, final String fundPassword) {
        if (amount.doubleValue() <= 0.0) {
            return MessageResult.error("理财金额非法！");
        }
        final Member member = (Member) this.memberDao.getOne(memberId);
        if (member == null) {
            return MessageResult.error("用户不存在！");
        }
        try {
            if (!Md5.md5Digest(fundPassword + member.getSalt()).toLowerCase().equals(member.getJyPassword())) {
                return MessageResult.error("交易所密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResult.error("交易异常！");
        }
        final DepositTypeInfo depositTypeInfo = (DepositTypeInfo) this.depositTypeInfoDao.getOne(depositTypeId);
        if (depositTypeInfo == null) {
            return MessageResult.error("托管理财产品不存在！");
        }
        if (amount.doubleValue() > depositTypeInfo.getInvestmentMax().doubleValue() || amount.doubleValue() < depositTypeInfo.getInvestmentMin().doubleValue()) {
            return MessageResult.error("投资金额不在当前产品范围内");
        }
        final List<MemberDepositInfo> preMemberDepositInfos = this.memberDepositInfoDao.getDepositIngListByMemberId(memberId, depositTypeId);
        if (preMemberDepositInfos != null && preMemberDepositInfos.size() > 0) {
            return MessageResult.error("您已购买购买过当前产品且未释放结束，暂不能再次购买！");
        }
        final MemberWallet memberWallet = this.memberWalletDao.findCoinIdAndMemberId(depositTypeInfo.getInvestCoin(), memberId);
        if (memberWallet == null || memberWallet.getBalance().doubleValue() < amount.doubleValue()) {
            return MessageResult.error("当前余额不足");
        }
        final int updateNum = this.memberWalletDao.decreaseBalance(memberWallet.getId(), amount, memberWallet.getVersion());
        if (updateNum > 0) {
            final MemberDepositInfo memberDepositInfo = new MemberDepositInfo();
            memberDepositInfo.setAmount(amount);
            memberDepositInfo.setDepositTypeId(depositTypeId);
            memberDepositInfo.setInvestTime(new Date());
            memberDepositInfo.setMemberId(memberId);
            memberDepositInfo.setEndTime(null);
            memberDepositInfo.setPreEndTime(new Date(System.currentTimeMillis() + depositTypeInfo.getCycle() * 3600L * 24L * 1000L));
            memberDepositInfo.setProfit(BigDecimal.ZERO);
            memberDepositInfo.setStatus(0);
            memberDepositInfo.setSequence(System.currentTimeMillis());
            this.memberDepositInfoDao.save(memberDepositInfo);
            final MemberTransaction transaction = new MemberTransaction();
            transaction.setAmount(amount.multiply(new BigDecimal("-1")));
            transaction.setSymbol(memberWallet.getCoin().getName());
            transaction.setAddress("");
            transaction.setMemberId(memberWallet.getMemberId());
            transaction.setType(TransactionType.DEPOSIT_ADD);
            transaction.setFee(BigDecimal.ZERO);
            transaction.setCreateTime(new Date());
            transaction.setSequence(System.currentTimeMillis());
            transaction.setAfterBalance(memberWallet.getBalance().subtract(amount));
            this.memberTransactionService.save(transaction);
        }
        return MessageResult.success();
    }

    public Page<MemberDepositInfo> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<MemberDepositInfo> page = (Page<MemberDepositInfo>) this.memberDepositInfoDao.findAll(predicate, pageable);
        if (page != null) {
            for (final MemberDepositInfo data : page.getContent()) {
                final Member member = this.memberService.findOne(data.getMemberId());
                if (member != null) {
                    data.setUsername(member.getUsername());
                    data.setMobilePhone(member.getMobilePhone());
                    data.setEmail(member.getEmail());
                }
            }
        }
        return page;
    }

    public MessageResult getDepositBalanceInfo(final Long memberId) {
        final Member member = (Member) this.memberDao.getOne(memberId);
        if (member == null) {
            return MessageResult.error("用户不存在！");
        }
        final DepositBalanceInfo depositBalanceInfo = new DepositBalanceInfo();
        final MemberWallet memberWallet = this.memberWalletDao.findCoinIdAndMemberId("USDT", memberId);
        if (memberWallet != null) {
            depositBalanceInfo.setBalance(memberWallet.getBalance());
        }
        final BigDecimal staticProfit = this.depositProfitInfoService.sumProfitBymemberId(memberId);
        final BigDecimal recommendProfit = this.depositRecommendProfitInfoService.sumRecommendProfitByMemberId(memberId);
        depositBalanceInfo.setStaticProfit(staticProfit);
        depositBalanceInfo.setRecommendProfit(recommendProfit);
        depositBalanceInfo.setTotalProfit(recommendProfit.add(staticProfit));
        final BigDecimal depositAmount = this.memberDepositInfoDao.sumAmoutByMemberId(memberId);
        depositBalanceInfo.setDepositAmount(depositAmount);
        return MessageResult.success("success", depositBalanceInfo);
    }

    @Transactional(rollbackFor = {Exception.class})
    public MessageResult breakDeposit(final Long memberId, final Long memberDepositId, final String fundPassword) {
        final Member member = (Member) this.memberDao.getOne(memberId);
        if (member == null) {
            return MessageResult.error("用户不存在！");
        }
        try {
            if (!Md5.md5Digest(fundPassword + member.getSalt()).toLowerCase().equals(member.getJyPassword())) {
                return MessageResult.error("交易所密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResult.error("交易密码获取异常！");
        }
        final MemberDepositInfo memberDepositInfo = (MemberDepositInfo) this.memberDepositInfoDao.getOne(memberDepositId);
        if (memberDepositInfo == null) {
            return MessageResult.error("理财记录不存在！");
        }
        if (memberDepositInfo.getStatus() != 0) {
            return MessageResult.error("理财已经结束！");
        }
        if (!memberDepositInfo.getMemberId().equals(memberId)) {
            return MessageResult.error("理财记录不属于当前用户！");
        }
        final int updatDepositInfoNum = this.memberDepositInfoDao.updateStatus(memberDepositId, new Date(), 2);
        if (updatDepositInfoNum > 0) {
            final BigDecimal rate = BigDecimal.ONE.subtract(memberDepositInfo.getDepositTypeInfo().getBreakRate());
            final BigDecimal amount = memberDepositInfo.getAmount().multiply(rate);
            MemberWallet memberWallet = this.memberWalletDao.findCoinIdAndMemberId(memberDepositInfo.getDepositTypeInfo().getInvestCoin(), memberId);
            if (memberWallet == null) {
                memberWallet = new MemberWallet();
                memberWallet.setMemberId(memberId);
                final Coin coin = new Coin();
                coin.setName(memberDepositInfo.getDepositTypeInfo().getInvestCoin());
                coin.setUnit(memberDepositInfo.getDepositTypeInfo().getInvestCoin());
                memberWallet.setCoin(coin);
                memberWallet.setBalance(BigDecimal.ZERO);
                memberWallet.setFrozenBalance(BigDecimal.ZERO);
                memberWallet = (MemberWallet) this.memberWalletDao.saveAndFlush(memberWallet);
            }
            final int updateBalanceNum = this.memberWalletDao.increaseBalance(memberWallet.getId(), amount, memberWallet.getVersion());
            if (updateBalanceNum > 0) {
                final MemberTransaction transaction = new MemberTransaction();
                transaction.setAmount(amount);
                transaction.setSymbol(memberWallet.getCoin().getName());
                transaction.setAddress("");
                transaction.setMemberId(memberWallet.getMemberId());
                transaction.setType(TransactionType.DEPOSIT_BREAK);
                transaction.setFee(memberDepositInfo.getAmount().multiply(memberDepositInfo.getDepositTypeInfo().getBreakRate()));
                transaction.setCreateTime(new Date());
                transaction.setSequence(System.currentTimeMillis());
                transaction.setAfterBalance(memberWallet.getBalance().add(amount));
                this.memberTransactionService.save(transaction);
            }
            return MessageResult.success();
        }
        return MessageResult.error("理财记录状态更新失败！");
    }

    public List<MemberDepositInfo> getUnFinishDepositInfoBySequence(final Long sequence) {
        return this.memberDepositInfoDao.getUnFinishDepositInfoBySequence(sequence);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateProfit(final Long id, final BigDecimal profit) {
        return this.memberDepositInfoDao.updateProfit(id, profit);
    }
}
