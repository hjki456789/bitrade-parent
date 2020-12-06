package cn.ztuo.bitrade.controller.coin;

import cn.ztuo.bitrade.controller.common.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "finance/coin-collection-log" })
public class CoinCollectLogController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private CoinCollectLogService coinCollectLogService;

    @RequiresPermissions({ "finance:coin-collection-log:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找钱包归集记录")
    @ApiOperation(value = "分页查找钱包归集记录")
    public MessageResult memberPromotionsPageList(final PageModel pageModel, final CoinCollectLog coinCollectLog, final Long memberId, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        final Predicate predicate = this.getPredicate(coinCollectLog, memberId, startTime, endTime);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("time");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<CoinCollectLog> all = this.coinCollectLogService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    public Predicate getPredicate(final CoinCollectLog coinCollectLog, final Long memberId, final Date startTime, final Date endTime) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (coinCollectLog != null) {
            final String coinName = coinCollectLog.getCoinName();
            final String address = coinCollectLog.getAddress();
            if (StringUtils.isNotEmpty((CharSequence)coinName)) {
                booleanExpressions.add(QCoinCollectLog.coinCollectLog.coinName.eq(coinName));
            }
            if (StringUtils.isNotEmpty((CharSequence)address)) {
                booleanExpressions.add(QCoinCollectLog.coinCollectLog.address.eq(address));
            }
        }
        if (memberId != null) {
            booleanExpressions.add(QCoinCollectLog.coinCollectLog.member.id.eq(memberId));
        }
        if (startTime != null) {
            booleanExpressions.add(QCoinCollectLog.coinCollectLog.time.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QCoinCollectLog.coinCollectLog.time.lt(endTime));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
