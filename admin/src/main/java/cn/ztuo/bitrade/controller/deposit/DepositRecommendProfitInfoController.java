package cn.ztuo.bitrade.controller.deposit;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "deposit/recommend-profit" })
public class DepositRecommendProfitInfoController extends BaseAdminController
{
    @Autowired
    private DepositRecommendProfitInfoService depositRecommendProfitInfoService;
    @Autowired
    private MemberService memberService;

    @RequiresPermissions({ "deposit:recommend-profit:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "分页查找用户托管理财动态收益记录列表")
    public MessageResult pageQuery(final PageModel pageModel, final Long memberId, final String username, final String mobilePhone, final String email, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        final List<Long> memberIds = (List<Long>)this.memberService.getPredicateMemberIds(memberId, username, (String)null, mobilePhone, email);
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(memberIds, startTime, endTime);
        if (!CollectionUtils.isEmpty((Collection)booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<DepositRecommendProfitInfo> all = (Page<DepositRecommendProfitInfo>)this.depositRecommendProfitInfoService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    private List<BooleanExpression> getBooleanExpressions(final List<Long> memberIds, final Date startTime, final Date endTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (CollectionUtils.isNotEmpty((Collection)memberIds)) {
            booleanExpressions.add(QDepositRecommendProfitInfo.depositRecommendProfitInfo.memberId.in((Collection)memberIds));
        }
        if (startTime != null) {
            booleanExpressions.add(QDepositRecommendProfitInfo.depositRecommendProfitInfo.time.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QDepositRecommendProfitInfo.depositRecommendProfitInfo.time.lt(endTime));
        }
        return booleanExpressions;
    }
}
