package cn.ztuo.bitrade.controller.member;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.dto.*;
import org.springframework.util.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "member/member-team" })
public class MemberTeamController extends BaseAdminController
{
    @Autowired
    private MemberTeamService memberTeamService;

    @RequiresPermissions({ "member:member-team:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.MEMBER, operation = "分页查找团队成员列表")
    public MessageResult pageQuery(final PageModel pageModel, final MemberTeam memberTeam) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(memberTeam);
        if (!CollectionUtils.isEmpty((Collection)booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("memberId");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.ASC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<MemberTeam> all = (Page<MemberTeam>)this.memberTeamService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "member:member-team:memberTeamList" })
    @PostMapping({ "memberTeamList" })
    @AccessLog(module = AdminModule.MEMBER, operation = "分页查找用户团队情况")
    public MessageResult memberTeamList(final PageModel pageModel, final MemberTeamDto memberTeamDto) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        if (memberTeamDto.getMemberId() != null) {
            predicates.add((Predicate)QMember.member.id.eq(memberTeamDto.getMemberId()));
        }
        if (!StringUtils.isEmpty(memberTeamDto.getUsername())) {
            predicates.add((Predicate)QMember.member.username.eq(memberTeamDto.getUsername()));
        }
        if (!StringUtils.isEmpty(memberTeamDto.getMobilePhone())) {
            predicates.add((Predicate)QMember.member.mobilePhone.like("%" + memberTeamDto.getMobilePhone() + "%"));
        }
        if (!StringUtils.isEmpty(memberTeamDto.getEmail())) {
            predicates.add((Predicate)QMember.member.email.like("%" + memberTeamDto.getEmail() + "%"));
        }
        final Page<MemberTeamDto> all = (Page<MemberTeamDto>)this.memberTeamService.findMemberTeamList((List)predicates, pageModel);
        return this.success(all);
    }

    private List<BooleanExpression> getBooleanExpressions(final MemberTeam memberTeam) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (memberTeam.getMemberId() != null) {
            booleanExpressions.add(QMemberTeam.memberTeam.memberId.eq(memberTeam.getMemberId()));
        }
        if (memberTeam.getGeneration() != null) {
            booleanExpressions.add(QMemberTeam.memberTeam.generation.eq(memberTeam.getGeneration()));
        }
        return booleanExpressions;
    }
}
