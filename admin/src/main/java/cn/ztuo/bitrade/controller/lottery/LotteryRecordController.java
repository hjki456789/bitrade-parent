package cn.ztuo.bitrade.controller.lottery;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.collections.*;
import java.util.*;
import cn.ztuo.bitrade.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.unblock.*;

@RestController
@RequestMapping({ "/lottery/lottery-record" })
public class LotteryRecordController extends BaseAdminController
{
    @Autowired
    private UnBlockLotteryRecordService unBlockLotteryRecordService;
    @Autowired
    private MemberService memberService;

    @RequiresPermissions({ "lottery:lottery-record::page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "用户抽奖记录分页列表")
    public MessageResult lotteryConfigList(final PageModel pageModel, final UnblockLotteryRecord unBlockLotteryRecord, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        List<Long> memberIds = null;
        if (null != unBlockLotteryRecord.getMemberId() || !StringUtils.isEmpty((CharSequence)unBlockLotteryRecord.getUsername()) || !StringUtils.isEmpty((CharSequence)unBlockLotteryRecord.getMobilePhone()) || !StringUtils.isEmpty((CharSequence)unBlockLotteryRecord.getEmail())) {
            final Member member = new Member();
            member.setId(unBlockLotteryRecord.getMemberId());
            member.setUsername(unBlockLotteryRecord.getUsername());
            member.setMobilePhone(unBlockLotteryRecord.getMobilePhone());
            member.setEmail(unBlockLotteryRecord.getEmail());
            memberIds = (List<Long>)this.memberService.queryMemberIds(member);
            if (CollectionUtils.isEmpty((Collection)memberIds)) {
                return this.success(new PageImpl((List)new ArrayList()));
            }
        }
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(memberIds, unBlockLotteryRecord.getIsWinne(), unBlockLotteryRecord.getLotteryLevel(), startTime, endTime);
        if (!CollectionUtils.isEmpty((Collection)booleanExpressions)) {
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
        final Page<UnblockLotteryRecord> all = (Page<UnblockLotteryRecord>)this.unBlockLotteryRecordService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    private List<BooleanExpression> getBooleanExpressions(final List<Long> memberIds, final Integer isWinne, final String lotteryLevel, final Date startTime, final Date endTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!CollectionUtils.isEmpty((Collection)memberIds)) {
            booleanExpressions.add(QUnblockLotteryRecord.unblockLotteryRecord.memberId.in((Collection)memberIds));
        }
        if (!StringUtils.isEmpty((CharSequence)lotteryLevel)) {
            booleanExpressions.add(QUnblockLotteryRecord.unblockLotteryRecord.lotteryLevel.eq(lotteryLevel));
        }
        if (isWinne != null) {
            booleanExpressions.add(QUnblockLotteryRecord.unblockLotteryRecord.isWinne.eq(isWinne));
        }
        if (startTime != null) {
            booleanExpressions.add(QUnblockLotteryRecord.unblockLotteryRecord.createTime.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QUnblockLotteryRecord.unblockLotteryRecord.createTime.lt(endTime));
        }
        return booleanExpressions;
    }
}
