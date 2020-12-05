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
public class DepositRecommendProfitInfoService extends BaseService
{
    @Autowired
    private DepositRecommendProfitInfoDao depositRecommendProfitInfoDao;
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

    public DepositRecommendProfitInfo save(final DepositRecommendProfitInfo depositRecommendProfitInfo) {
        return (DepositRecommendProfitInfo)this.depositRecommendProfitInfoDao.save(depositRecommendProfitInfo);
    }

    public List<DepositRecommendProfitInfo> findListByMemberId(final Long memberId, final Long lastSequence) {
        return this.depositRecommendProfitInfoDao.getDepositRecommendProfitInfoListByMemberId(memberId, lastSequence);
    }

    public BigDecimal sumRecommendProfitByMemberId(final Long memberId) {
        return this.depositRecommendProfitInfoDao.sumProfitByMemberId(memberId);
    }

    public Page<DepositRecommendProfitInfo> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<DepositRecommendProfitInfo> page = (Page<DepositRecommendProfitInfo>)this.depositRecommendProfitInfoDao.findAll(predicate, pageable);
        if (page != null) {
            for (final DepositRecommendProfitInfo data : page.getContent()) {
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
}
