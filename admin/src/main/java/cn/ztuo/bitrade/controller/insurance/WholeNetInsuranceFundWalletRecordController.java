package cn.ztuo.bitrade.controller.insurance;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.insurance.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.collections.*;
import java.util.*;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.insurance.*;

@RestController
@RequestMapping({ "/insurance/insurance-fund-record" })
public class WholeNetInsuranceFundWalletRecordController extends BaseAdminController
{
    @Autowired
    private WholeNetInsuranceFundWalletRecordService wholeNetInsuranceFundWalletRecordService;

    @RequiresPermissions({ "insurance:insurance-fund-record:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.INSURANCE, operation = "保险基金记录分页列表")
    public MessageResult pageQuery(final PageModel pageModel, final String coin, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(coin, startTime, endTime);
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
        final Page<WholeNetInsuranceFundWalletRecord> all = (Page<WholeNetInsuranceFundWalletRecord>)this.wholeNetInsuranceFundWalletRecordService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "statistic" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "保险基金统计")
    public MessageResult statistic(final String coin, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
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
        final List<WholeNetInsuranceFundWalletRecord> list = (List<WholeNetInsuranceFundWalletRecord>)this.wholeNetInsuranceFundWalletRecordService.sumAmount(coin, startSeq, endSeq);
        return this.success(list);
    }

    private List<BooleanExpression> getBooleanExpressions(final String coin, final Date startTime, final Date endTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        try {
            if (!StringUtils.isEmpty((CharSequence)coin)) {
                booleanExpressions.add(QWholeNetInsuranceFundWalletRecord.wholeNetInsuranceFundWalletRecord.coin.eq(coin));
            }
            if (startTime != null) {
                final long startSeq = DateUtil.getTimeCur("yyyy-MM-dd HH:mm:ss", startTime);
                booleanExpressions.add(QWholeNetInsuranceFundWalletRecord.wholeNetInsuranceFundWalletRecord.sequence.goe(startSeq));
            }
            if (endTime != null) {
                final long endSeq = DateUtil.getTimeCur("yyyy-MM-dd HH:mm:ss", endTime);
                booleanExpressions.add(QWholeNetInsuranceFundWalletRecord.wholeNetInsuranceFundWalletRecord.sequence.lt(endSeq));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return booleanExpressions;
    }
}
