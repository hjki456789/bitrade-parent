package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import java.io.*;
import java.math.*;

@Service
public class CommunityMemberFollowInfoService
{
    @Autowired
    private CommunityMemberFollowInfoRepository communityMemberFollowInfoRepository;

    public Long findFollowPeopleCount(final Long memberId) {
        final Criteria<CommunityMemberFollowInfo> specification = (Criteria<CommunityMemberFollowInfo>)new Criteria();
        specification.add((Criterion)Restrictions.eq("leaderMemberId", memberId, true));
        return this.communityMemberFollowInfoRepository.count((Specification)specification);
    }

    public List<CommunityMemberFollowInfo> findAllByLeaderMemberId(final Long memberId) {
        final Criteria<CommunityMemberFollowInfo> specification = (Criteria<CommunityMemberFollowInfo>)new Criteria();
        specification.add((Criterion)Restrictions.eq("leaderMemberId", memberId, true));
        return (List<CommunityMemberFollowInfo>)this.communityMemberFollowInfoRepository.findAll((Specification)specification);
    }

    public Page<CommunityMemberFollowInfo> getLeaderFollowMemberList(final Long memberId, final int pageNo, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "totalProfit") });
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<CommunityMemberFollowInfo> specification = (Criteria<CommunityMemberFollowInfo>)new Criteria();
        specification.add((Criterion)Restrictions.eq("leaderMemberId", memberId, true));
        return (Page<CommunityMemberFollowInfo>)this.communityMemberFollowInfoRepository.findAll((Specification)specification, (Pageable)pageRequest);
    }

    public CommunityMemberFollowInfo findByMemberIdAndCoinIdAndFollowedMemberId(final Long memberId, final String coinId, final Long followedMemberId) {
        return this.communityMemberFollowInfoRepository.findByMemberIdAndCoinIdAndLeaderMemberId(memberId, coinId, followedMemberId);
    }

    @Transactional
    public CommunityMemberFollowInfo saveAndFlush(final CommunityMemberFollowInfo info) {
        return this.communityMemberFollowInfoRepository.saveAndFlush(info);
    }

    public Page<CommunityMemberFollowInfo> getLeadersByMemberId(final Long memberId, final int pageNo, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "sequence") });
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<CommunityMemberFollowInfo> specification = (Criteria<CommunityMemberFollowInfo>)new Criteria();
        specification.add((Criterion)Restrictions.eq("memberId", memberId, true));
        specification.add((Criterion)Restrictions.eq("status", CommunityMemberFollowInfoStatus.FOLLOWING, true));
        return (Page<CommunityMemberFollowInfo>)this.communityMemberFollowInfoRepository.findAll((Specification)specification, (Pageable)pageRequest);
    }

    public CommunityMemberFollowInfo get(final Long id) {
        return this.communityMemberFollowInfoRepository.getOne(id);
    }

    public BigDecimal getMemberFollowTotalAmountByCoin(final Long memberId, final String coinId) {
        return this.communityMemberFollowInfoRepository.sumMemberFollowTotalAmountByCoin(memberId, coinId);
    }

    public BigDecimal getMemberFollowTotalProfitByCoin(final Long memberId, final String coinId) {
        return this.communityMemberFollowInfoRepository.sumMemberFollowTotalProfitByCoin(memberId, coinId);
    }

    public int cancelFollow(final Long id, final CommunityMemberFollowInfoStatus status, final Long version) {
        return this.communityMemberFollowInfoRepository.updateFollowStatus(id, status, version);
    }
}
