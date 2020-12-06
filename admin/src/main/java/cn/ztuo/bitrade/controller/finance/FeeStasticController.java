package cn.ztuo.bitrade.controller.finance;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
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
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "/finance/fee-stastic" })
public class FeeStasticController extends BaseAdminController
{
    @Autowired
    private FeeStasticService feeStasticService;

    @RequiresPermissions({ "finance:fee-stastic:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.FINANCE, operation = "手续费统计记录分页列表")
    public MessageResult pageQuery(final PageModel pageModel, final String unit, final String symbol, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") Date endTime) {
        Predicate predicate = null;
        if (startTime == null) {
            startTime = DateUtil.getZeroDate(new Date());
        }
        if (endTime == null) {
            endTime = DateUtil.getZeroDate(new Date(DateUtil.getLaterTime(new Date(), Integer.valueOf(1), Integer.valueOf(1))));
        }
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(unit, symbol, startTime, endTime);
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
        final Page<FeeStastic> all = (Page<FeeStastic>)this.feeStasticService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "statistic" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "保险基金统计")
    public MessageResult statistic(final String unit, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        long startSeq = 0L;
        long endSeq = Long.MAX_VALUE;
        try {
            if (startTime != null) {
                startSeq = DateUtil.getTimeCur("yyyy-MM-dd HH:mm:ss", startTime);
            }
            if (endTime != null) {
                endSeq = DateUtil.getTimeCur("yyyy-MM-dd HH:mm:ss", startTime);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final List<FeeStastic> list = (List<FeeStastic>)this.feeStasticService.sumAmount(unit, startSeq, endSeq);
        return this.success(list);
    }

    private List<BooleanExpression> getBooleanExpressions(final String unit, final String symbol, final Date startTime, final Date endTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        try {
            if (!StringUtils.isEmpty((CharSequence)unit)) {
                booleanExpressions.add(QFeeStastic.feeStastic.unit.eq(unit));
            }
            if (!StringUtils.isEmpty((CharSequence)symbol)) {
                booleanExpressions.add(QFeeStastic.feeStastic.symbol.eq(symbol));
            }
            if (startTime != null) {
                final long startSeq = DateUtil.getTimeCur("yyyy-MM-dd HH:mm:ss", startTime);
                booleanExpressions.add(QFeeStastic.feeStastic.sequence.goe(startSeq));
            }
            if (endTime != null) {
                final long endSeq = DateUtil.getTimeCur("yyyy-MM-dd HH:mm:ss", endTime);
                booleanExpressions.add(QFeeStastic.feeStastic.sequence.lt(endSeq));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return booleanExpressions;
    }
}
