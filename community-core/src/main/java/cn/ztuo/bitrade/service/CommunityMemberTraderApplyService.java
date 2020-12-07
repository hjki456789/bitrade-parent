package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import cn.ztuo.bitrade.entity.enumconstants.*;

@Service
@Transactional
public class CommunityMemberTraderApplyService
{
    @Autowired
    private CommunityMemberTraderApplyRepository communityMemberTraderApplyRepository;

    public CommunityMemberTraderApply save(final CommunityMemberTraderApply communityMemberTraderApply) {
        return (CommunityMemberTraderApply)this.communityMemberTraderApplyRepository.saveAndFlush(communityMemberTraderApply);
    }

    public CommunityMemberTraderApply findByMemberId(final Long memberId) {
        final Criteria<CommunityMemberTraderApply> specification = (Criteria<CommunityMemberTraderApply>)new Criteria();
        specification.add((Criterion)Restrictions.eq("memberId", memberId, true));
        return (CommunityMemberTraderApply)this.communityMemberTraderApplyRepository.findOne(specification).get();
    }

    public int update(final long id, final String realName, final String cardId, final String phone, final CommunityMemberTraderApplyStatus status) {
        return this.communityMemberTraderApplyRepository.update(id, realName, cardId, phone, status);
    }
}
