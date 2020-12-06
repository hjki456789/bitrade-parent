package cn.ztuo.bitrade.controller.lottery;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import cn.ztuo.bitrade.enums.*;
import cn.ztuo.bitrade.entity.unblock.*;

@RestController
@RequestMapping({ "/lottery/lottery-draw" })
public class LotteryDrawController extends BaseAdminController
{
    @Autowired
    UnBlockLotteryDrawService unBlockLotteryDrawService;
    @Autowired
    UnblockLotteryIncreasedRecordService unblockLotteryIncreasedRecordService;
    @Autowired
    MemberService memberService;

    @RequiresPermissions({ "lottery:lottery-draw::page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "用户抽奖配置信息分页列表")
    public MessageResult lotteryConfigList(final PageModel pageModel, final UnblockLotteryDraw unblockLotteryDraw) {
        List<Long> memberIds = null;
        if (null != unblockLotteryDraw.getMemberId() || !StringUtils.isEmpty((CharSequence)unblockLotteryDraw.getUsername()) || !StringUtils.isEmpty((CharSequence)unblockLotteryDraw.getMobilePhone()) || !StringUtils.isEmpty((CharSequence)unblockLotteryDraw.getEmail())) {
            final Member member = new Member();
            member.setId(unblockLotteryDraw.getMemberId());
            member.setUsername(unblockLotteryDraw.getUsername());
            member.setMobilePhone(unblockLotteryDraw.getMobilePhone());
            member.setEmail(unblockLotteryDraw.getEmail());
            memberIds = (List<Long>)this.memberService.queryMemberIds(member);
            if (CollectionUtils.isEmpty((Collection)memberIds)) {
                return this.success(new PageImpl((List)new ArrayList()));
            }
        }
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(memberIds);
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
        final Page<UnblockLotteryDraw> all = (Page<UnblockLotteryDraw>)this.unBlockLotteryDrawService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "lottery:lottery-draw:updateDrawCount" })
    @PostMapping({ "updateDrawCount" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "更新抽奖次数")
    public MessageResult updateDrawCount(@RequestParam("memberId") final Long memberId, @RequestParam("lotteryDrawCount") final Long lotteryDrawCount) {
        if (lotteryDrawCount < 0L) {
            return MessageResult.error("可抽奖次数不可小于0!");
        }
        final int a = this.unBlockLotteryDrawService.updateLotterDrawByMemberId(memberId, lotteryDrawCount);
        if (a == 0) {
            return MessageResult.error("FAIL!");
        }
        return MessageResult.success("SUCCESS");
    }

    @RequiresPermissions({ "lottery:lottery-draw:updateDrawCount" })
    @PostMapping({ "lotteryDrawCount" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "增加抽奖次数")
    public MessageResult lotteryDrawCount(@RequestParam("memberId") final Long memberId) {
        UnblockLotteryDraw draw = this.unBlockLotteryDrawService.getUnBlockLotteryDrawByMemberId(memberId);
        if (null == draw) {
            draw = new UnblockLotteryDraw();
            draw.setCreateTime(new Date());
            draw.setMemberId(memberId);
            draw.setLotteryDrawCount(Long.valueOf(0L));
            draw.setTransCount(Long.valueOf(0L));
            this.unBlockLotteryDrawService.save(draw);
        }
        final int a = this.unBlockLotteryDrawService.updateLotterDrawByMemberId(memberId, Long.valueOf(draw.getLotteryDrawCount() + 1L));
        if (a == 0) {
            return MessageResult.error("FAIL!");
        }
        final UnblockLotteryIncreasedRecord unblockLotteryIncreasedRecord = new UnblockLotteryIncreasedRecord();
        unblockLotteryIncreasedRecord.setAddCount(Long.valueOf(1L));
        unblockLotteryIncreasedRecord.setBeforeCount(Long.valueOf((long)draw.getLotteryDrawCount()));
        unblockLotteryIncreasedRecord.setAfterCount(Long.valueOf(draw.getLotteryDrawCount() + 1L));
        unblockLotteryIncreasedRecord.setCreateTime(new Date());
        unblockLotteryIncreasedRecord.setTime(Long.valueOf(System.currentTimeMillis()));
        unblockLotteryIncreasedRecord.setMemberId(memberId);
        unblockLotteryIncreasedRecord.setLotteryAddType(LotteryAddType.WEBADD);
        this.unblockLotteryIncreasedRecordService.insert(unblockLotteryIncreasedRecord);
        return MessageResult.success("SUCCESS");
    }

    private List<BooleanExpression> getBooleanExpressions(final List<Long> memberIds) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!CollectionUtils.isEmpty((Collection)memberIds)) {
            booleanExpressions.add(QUnblockLotteryDraw.unblockLotteryDraw.memberId.in((Collection)memberIds));
        }
        return booleanExpressions;
    }
}
