package cn.ztuo.bitrade.controller.unblock;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.dto.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.unblock.*;

@RestController
@RequestMapping({ "unblock/robot-ticket" })
public class UnblockRobotTicketController extends BaseAdminController
{
    @Autowired
    private UnblockRobotTicketService unblockRobotTicketService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "unblock:robot-ticket:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "分页查找解封机器码信息列表")
    public MessageResult pageQuery(final PageModel pageModel, final UnblockRobotTicketBO req) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(req);
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
        final Page<UnblockRobotTicket> all = (Page<UnblockRobotTicket>)this.unblockRobotTicketService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "unblock:robot-ticket:add" })
    @PostMapping({ "add" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "保存解封机器码信息")
    public MessageResult save(@RequestParam("num") final int num, @RequestParam("effectiveDay") final int effectiveDay) {
        if (num <= 0 || num >= 100) {
            return MessageResult.error("新增条数必须大于0小于100!");
        }
        if (effectiveDay <= 0) {
            return MessageResult.error("生效天数必须大于0!");
        }
        this.unblockRobotTicketService.insertUnblockRobotTicket(num, effectiveDay);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    private List<BooleanExpression> getBooleanExpressions(final UnblockRobotTicketBO req) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)req.getId())) {
            booleanExpressions.add(QUnblockRobotTicket.unblockRobotTicket.id.eq(req.getId()));
        }
        if (null != req.getStatus()) {
            booleanExpressions.add(QUnblockRobotTicket.unblockRobotTicket.status.eq(req.getStatus()));
        }
        if (null != req.getMemberId()) {
            booleanExpressions.add(QUnblockRobotTicket.unblockRobotTicket.memberId.eq(req.getMemberId()));
        }
        return booleanExpressions;
    }
}
