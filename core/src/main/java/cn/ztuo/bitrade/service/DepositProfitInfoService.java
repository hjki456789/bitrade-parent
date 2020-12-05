package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import java.math.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class DepositProfitInfoService extends BaseService
{
    @Autowired
    private DepositProfitInfoDao depositProfitInfoDao;
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

    public DepositProfitInfo save(final DepositProfitInfo depositProfitInfo) {
        return (DepositProfitInfo)this.depositProfitInfoDao.save(depositProfitInfo);
    }

    public List<DepositProfitInfo> findListByMemberId(final Long memberId, final Long lastSequence) {
        return this.depositProfitInfoDao.getDepositProfitInfoListByMemberId(memberId, lastSequence);
    }

    public List<DepositProfitInfo> findListByDepositId(final Long depositId, final Long lastSequence) {
        return this.depositProfitInfoDao.getDepositProfitInfoListByDepositInforId(depositId, lastSequence);
    }

    public BigDecimal sumProfitBymemberId(final Long memberId) {
        return this.depositProfitInfoDao.sumProfitByMemberId(memberId);
    }

    public BigDecimal sumProfitByMemberIdAndDepositId(final Long depositId) {
        return this.depositProfitInfoDao.sumProfitByMemberIdAndDepositId(depositId);
    }

    public Page<DepositProfitInfo> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<DepositProfitInfo> page = (Page<DepositProfitInfo>)this.depositProfitInfoDao.findAll(predicate, pageable);
        if (page != null) {
            for (final DepositProfitInfo data : page.getContent()) {
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

    public List<DepositProfitInfo> getListByDepositInfoIdAndSequence(final Long depositId, final Long sequence) {
        return this.depositProfitInfoDao.getListByDepositInfoIdAndSequence(depositId, sequence);
    }

    public List<DepositProfitInfo> getInfosBySequnce(final Long sequence) {
        return this.depositProfitInfoDao.getInfosBySequence(sequence);
    }
}
