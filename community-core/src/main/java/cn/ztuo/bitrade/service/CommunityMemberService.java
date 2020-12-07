package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import cn.ztuo.bitrade.pagination.*;
import org.apache.commons.lang3.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.*;

@Service
@Transactional
public class CommunityMemberService
{
    @Autowired
    private CommunityMemberRepository communityMemberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommunitySecondCategoryConfigRepository communitySecondCategoryConfigRepository;

    public Page<CommunityMember> getLeaderList(final CommunityMemberType type, final String username, final String labels, final String totalProfitRate, final String threeWeekCallbackRate, final String transDays, final int pageNo, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "totalProfitRate") });
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<CommunityMember> specification = (Criteria<CommunityMember>)new Criteria();
        if (null != type) {
            specification.add((Criterion)Restrictions.eq("type", type, true));
        }
        if (StringUtils.isNotEmpty((CharSequence)username)) {
            specification.add((Criterion)Restrictions.like("username", username, true));
        }
        if (StringUtils.isNotEmpty((CharSequence)labels)) {
            final List<String> labelList = Arrays.asList(labels.split(","));
            final Criterion[] list = new Criterion[labelList.size()];
            for (int i = 0; i < labelList.size(); ++i) {
                final Criterion ex = (Criterion)Restrictions.like("labels", (String)labelList.get(i), true);
                list[i] = ex;
            }
            specification.add((Criterion)Restrictions.or(list));
        }
        if (StringUtils.isNotEmpty((CharSequence)totalProfitRate)) {
            final CommunitySecondCategoryConfig secondCategoryConfig = (CommunitySecondCategoryConfig)this.communitySecondCategoryConfigRepository.getOne(totalProfitRate);
            if (null != secondCategoryConfig) {
                if (secondCategoryConfig.getMin().compareTo(secondCategoryConfig.getMax()) > 0) {
                    specification.add((Criterion)Restrictions.gt("totalProfitRate", secondCategoryConfig.getMin(), true));
                }
                else {
                    specification.add((Criterion)Restrictions.gt("totalProfitRate", secondCategoryConfig.getMin(), true));
                    specification.add((Criterion)Restrictions.lte("totalProfitRate", secondCategoryConfig.getMax(), true));
                }
            }
        }
        if (StringUtils.isNotEmpty((CharSequence)threeWeekCallbackRate)) {
            final CommunitySecondCategoryConfig secondCategoryConfig = (CommunitySecondCategoryConfig)this.communitySecondCategoryConfigRepository.getOne(threeWeekCallbackRate);
            if (null != secondCategoryConfig) {
                if (secondCategoryConfig.getMin().compareTo(secondCategoryConfig.getMax()) > 0) {
                    specification.add((Criterion)Restrictions.gt("threeWeekCallbackRate", secondCategoryConfig.getMin(), true));
                }
                else {
                    specification.add((Criterion)Restrictions.gt("threeWeekCallbackRate", secondCategoryConfig.getMin(), true));
                    specification.add((Criterion)Restrictions.lte("threeWeekCallbackRate", secondCategoryConfig.getMax(), true));
                }
            }
        }
        if (StringUtils.isNotEmpty((CharSequence)transDays)) {
            final CommunitySecondCategoryConfig secondCategoryConfig = (CommunitySecondCategoryConfig)this.communitySecondCategoryConfigRepository.getOne(transDays);
            if (null != secondCategoryConfig) {
                if (secondCategoryConfig.getMin().compareTo(secondCategoryConfig.getMax()) > 0) {
                    specification.add((Criterion)Restrictions.gte("transactionDays", secondCategoryConfig.getMin(), true));
                }
                else {
                    specification.add((Criterion)Restrictions.gt("transactionDays", secondCategoryConfig.getMin(), true));
                    specification.add((Criterion)Restrictions.lte("transactionDays", secondCategoryConfig.getMax(), true));
                }
            }
        }
        specification.add((Criterion)Restrictions.eq("status", CommunityMemberStatus.OPEN_LEAD, true));
        return (Page<CommunityMember>)this.communityMemberRepository.findAll((Specification)specification, (Pageable)pageRequest);
    }

    @Transactional(readOnly = false)
    public CommunityMember getMemberById(final Long memberId) {
        CommunityMember member = (CommunityMember)this.communityMemberRepository.getOne(memberId);
        if (null == member) {
            member = new CommunityMember();
            final Member old = this.memberService.findOne(memberId);
            if (null != old) {
                member.setMemberId(memberId);
                member.setUsername(old.getUsername());
                member.setAreaName("未知位置");
                member.setPersonalIntroduction("这个人很懒，什么也没有留下");
                member.setSex(CommunityMemberSex.UNKONWN);
                member.setLabels("");
                member.setType(CommunityMemberType.NORMAL);
                member.setStatus(CommunityMemberStatus.CLOSE_LEAD);
                member.setTotalProfitRate(BigDecimal.ZERO);
                member.setThreeWeekWinRate(BigDecimal.ZERO);
                member.setThreeWeekCallbackRate(BigDecimal.ZERO);
                member.setFollowExtractRate(BigDecimal.ZERO);
                member.setCreateTime(new Date());
                member.setSequence(System.currentTimeMillis());
                member.setVersion(0L);
                member.setActualTimeCount(0);
                final int days = DateUtils.getDaysBetweenDate(old.getRegistrationTime(), new Date());
                member.setTransactionDays(days + 1);
                member = (CommunityMember)this.communityMemberRepository.saveAndFlush(member);
            }
        }
        return member;
    }

    @Transactional(readOnly = false)
    public void updateMemberSex(final Long memberId, final CommunityMemberSex sex) {
        this.communityMemberRepository.updateMemberSex(memberId, sex);
    }

    @Transactional(readOnly = false)
    public void updateMemberPersonalIntroduction(final Long memberId, final String personalIntroduction) {
        this.communityMemberRepository.updateMemberPersonalIntroduction(memberId, personalIntroduction);
    }

    @Transactional(readOnly = false)
    public void updateMemberAreaName(final Long memberId, final String areaName) {
        this.communityMemberRepository.updateMemberAreaName(memberId, areaName);
    }

    @Transactional(readOnly = false)
    public void updateMemberLabels(final Long memberId, final String labels) {
        this.communityMemberRepository.updateMemberLabels(memberId, labels);
    }

    @Transactional(readOnly = false)
    public void updateMemberStatus(final Long memberId, final CommunityMemberStatus status) {
        this.communityMemberRepository.updateMemberStatus(memberId, status);
    }

    public BigDecimal getYesterdayProfit(final Long memberId, final String belongDay) {
        return this.communityMemberRepository.getYesterdayProfit(memberId, belongDay);
    }

    public BigDecimal getTotalProfit(final Long memberId) {
        return this.communityMemberRepository.getTotalProfit(memberId);
    }

    public int updateMemberInfo(final Long memberId, final BigDecimal totalProfitRate, final BigDecimal threeWeekWinRate, final BigDecimal threeWeekCallBackRate) {
        return this.communityMemberRepository.updateMemberInfo(memberId, totalProfitRate, threeWeekWinRate, threeWeekCallBackRate);
    }

    public List<CommunityMember> findAll() {
        return (List<CommunityMember>)this.communityMemberRepository.findAll();
    }
}
