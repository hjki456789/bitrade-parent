package cn.ztuo.bitrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.dto.*;
import com.querydsl.jpa.impl.*;
import cn.ztuo.bitrade.entity.*;
import com.querydsl.core.types.*;
import org.apache.commons.collections.*;
import org.springframework.data.domain.*;
import java.util.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import java.math.*;

@Service
public class MemberTeamService
{
    @Autowired
    protected JPAQueryFactory queryFactory;
    @Autowired
    private MemberTeamDao memberTeamDao;

    public Page<MemberTeam> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<MemberTeam>)this.memberTeamDao.findAll(predicate, pageable);
    }

    public Page<MemberTeamDto> findMemberTeamList(final List<Predicate> predicates, final PageModel pageModel) {
        final List<OrderSpecifier> orderSpecifiers = pageModel.getOrderSpecifiers();
        final JPAQuery<MemberTeamDto> query = (JPAQuery<MemberTeamDto>)((JPAQuery)((JPAQuery)this.queryFactory.select((Expression)Projections.fields((Class)MemberTeamDto.class, new Expression[] { (Expression)QMember.member.id.as("memberId"), (Expression)QMember.member.username.as("username"), (Expression)QMember.member.mobilePhone.as("mobilePhone"), (Expression)QMember.member.email.as("email") })).from((EntityPath)QMember.member)).where((Predicate[])predicates.toArray(new Predicate[predicates.size()]))).orderBy((OrderSpecifier[])orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]));
        final List<MemberTeamDto> list = (List<MemberTeamDto>)((JPAQuery)((JPAQuery)query.offset((long)((pageModel.getPageNo() - 1) * pageModel.getPageSize()))).limit((long)pageModel.getPageSize())).fetch();
        final long total = query.fetchCount();
        if (!CollectionUtils.isEmpty((Collection)list)) {
            for (final MemberTeamDto data : list) {
                final Long directNumber = this.memberTeamDao.countDirectNumber(data.getMemberId(), 1);
                data.setDirectNumber((directNumber == null) ? 0L : directNumber);
                final List<Object[]> objList = this.memberTeamDao.countTeamNumber(data.getMemberId());
                if (!CollectionUtils.isEmpty((Collection)objList)) {
                    data.setGeneration(Integer.parseInt(objList.get(0)[0].toString()));
                    data.setTeamNumber(Long.parseLong(objList.get(0)[1].toString()));
                }
            }
        }
        return (Page<MemberTeamDto>)new PageImpl((List)list, pageModel.getPageable(), total);
    }

    public MemberTeam getByMemberIdAndLowerMemberId(final Long memberId, final Long lowerMemberId) {
        return this.memberTeamDao.getByMemberIdAndLowerMemberId(memberId, lowerMemberId);
    }

    public int save(final MemberTeam memberTeam) {
        this.memberTeamDao.save(memberTeam);
        return 1;
    }

    public long countTeamNumByMemberId(final long memberId) {
        final Criteria<MemberTeam> specification = new Criteria<MemberTeam>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        return this.memberTeamDao.count((Specification)specification);
    }

    public long countValidTeamNumByMemberId(final long memberId) {
        final Criteria<MemberTeam> specification = new Criteria<MemberTeam>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        specification.add(Restrictions.eq("isValidMember", 1, true));
        return this.memberTeamDao.count((Specification)specification);
    }

    public List<MemberTeam> getTeamByMemberId(final long memberId) {
        return this.memberTeamDao.getTeamByMemberId(memberId);
    }

    public List<MemberTeam> getTeamByMemberIdAndGeneration(final long memberId, final int generation) {
        return this.memberTeamDao.getTeamByMemberIdGAndGeneration(memberId, generation);
    }

    public BigDecimal getTeamAmountByMemberId(final Long memberId) {
        return this.memberTeamDao.getTeamAmountByMemberId(memberId);
    }

    public List<MemberTeam> getTeamByLowerId(final long lowerMemberId) {
        return this.memberTeamDao.getTeamByLowerId(lowerMemberId);
    }
}
