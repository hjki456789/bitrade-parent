package cn.ztuo.bitrade.controller.unblock;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.unblock.*;
import org.apache.commons.lang3.*;

@RestController
@RequestMapping({ "unblock/member-reward" })
public class UnblockMemberRewardController extends BaseAdminController
{
    @Autowired
    private UnblockMemberRewardService unblockMemberRewardService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "unblock:member-reward:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "分页查找用户奖金记录列表")
    public MessageResult pageQuery(final PageModel pageModel, final UnblockMemberReward unblockMemberReward, final String username, final String mobilePhone, final String email, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(unblockMemberReward, username, mobilePhone, email, startTime, endTime);
        if (!CollectionUtils.isEmpty(booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("createTime");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<UnblockMemberReward> all = (Page<UnblockMemberReward>)this.unblockMemberRewardService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    private List<BooleanExpression> getBooleanExpressions(final UnblockMemberReward unblockMemberReward, final String username, final String mobilePhone, final String email, final Date startTime, final Date endTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (null != unblockMemberReward.getMemberId()) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.memberId.eq(unblockMemberReward.getMemberId()));
        }
        if (null != unblockMemberReward.getOriginMemberId()) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.originMemberId.eq(unblockMemberReward.getOriginMemberId()));
        }
        if (null != unblockMemberReward.getGeneration()) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.generation.eq(unblockMemberReward.getGeneration()));
        }
        if (!StringUtils.isEmpty((CharSequence)unblockMemberReward.getCoin())) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.coin.eq(unblockMemberReward.getCoin()));
        }
        if (!StringUtils.isEmpty((CharSequence)username)) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.member.username.like("%" + username + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.member.username.like("%" + mobilePhone + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)email)) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.member.username.like("%" + email + "%"));
        }
        if (startTime != null) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.createTime.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QUnblockMemberReward.unblockMemberReward.createTime.lt(endTime));
        }
        return booleanExpressions;
    }
}
