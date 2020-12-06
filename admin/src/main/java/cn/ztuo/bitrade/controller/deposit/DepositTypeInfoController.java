package cn.ztuo.bitrade.controller.deposit;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.collections.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.apache.commons.lang.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "deposit/deposit-type" })
public class DepositTypeInfoController extends BaseAdminController
{
    @Autowired
    private DepositTypeInfoService depositTypeInfoService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @PostMapping({ "findAll" })
    public MessageResult findAll() {
        final List<DepositTypeInfo> list = (List<DepositTypeInfo>)this.depositTypeInfoService.findAllByEnableList();
        return this.success(list);
    }

    @RequiresPermissions({ "deposit:deposit-type:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "分页查找理财类型列表")
    public MessageResult pageQuery(final PageModel pageModel, final String name, final Integer cycle, final Integer status, final String investCoin, final String profitCoin) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(name, cycle, status, investCoin, profitCoin);
        if (!CollectionUtils.isEmpty((Collection)booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("id");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.ASC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<DepositTypeInfo> all = (Page<DepositTypeInfo>)this.depositTypeInfoService.findAllPage(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "deposit:deposit-type:edit" })
    @PostMapping({ "save" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "保存理财类型信息")
    public MessageResult update(final DepositTypeInfo depositTypeInfo) {
        if (depositTypeInfo.getId() != null) {
            final DepositTypeInfo info = this.depositTypeInfoService.findById(depositTypeInfo.getId());
            if (info == null) {
                return MessageResult.error("信息不存在!");
            }
            depositTypeInfo.setStatus(info.getStatus());
        }
        else {
            depositTypeInfo.setStatus(1);
        }
        if (StringUtils.isEmpty(depositTypeInfo.getName())) {
            return MessageResult.error("套餐名称不能为空!");
        }
        if (depositTypeInfo.getCycle() <= 0) {
            return MessageResult.error("周期必须大于0!");
        }
        if (depositTypeInfo.getDayProfitRate() == null || !BigDecimalUtils.compareGt(depositTypeInfo.getDayProfitRate(), BigDecimal.ZERO)) {
            return MessageResult.error("日收益率必须大于等于0!");
        }
        if (depositTypeInfo.getInvestmentMax() == null || depositTypeInfo.getInvestmentMin() == null || !BigDecimalUtils.compare(depositTypeInfo.getInvestmentMax(), BigDecimal.ZERO) || !BigDecimalUtils.compare(depositTypeInfo.getInvestmentMin(), BigDecimal.ZERO) || BigDecimalUtils.compare(depositTypeInfo.getInvestmentMin(), depositTypeInfo.getInvestmentMax())) {
            return MessageResult.error("投资最大额与投资最小额必须大于0且投资最大额不能小于投资最小额!");
        }
        if (!BigDecimalUtils.compareGt(depositTypeInfo.getBreakRate(), BigDecimal.ZERO)) {
            return MessageResult.error("违约扣除本金比例必须大于等于0!");
        }
        if (StringUtils.isEmpty(depositTypeInfo.getInvestCoin())) {
            depositTypeInfo.setInvestCoin("USDT");
        }
        if (StringUtils.isEmpty(depositTypeInfo.getProfitCoin())) {
            depositTypeInfo.setProfitCoin("USDT");
        }
        this.depositTypeInfoService.save(depositTypeInfo);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "deposit:deposit-type:edit" })
    @PostMapping({ "updateStatus" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "修改理财类型信息可用状态")
    public MessageResult updateStatus(@RequestParam("id") final Long id, @RequestParam("status") final Integer status) {
        final int num = this.depositTypeInfoService.updateStatus(id, status);
        if (num > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @RequiresPermissions({ "deposit:deposit-type:delete" })
    @PostMapping({ "delete" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "删除理财类型信息")
    public MessageResult deleteById(@RequestParam("id") final Long id) {
            this.depositTypeInfoService.deleteById(id);
            return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    private List<BooleanExpression> getBooleanExpressions(final String name, final Integer cycle, final Integer status, final String investCoin, final String profitCoin) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (StringUtils.isNotEmpty(name)) {
            booleanExpressions.add(QDepositTypeInfo.depositTypeInfo.name.like("%" + name + "%"));
        }
        if (cycle != null) {
            booleanExpressions.add(QDepositTypeInfo.depositTypeInfo.cycle.eq(cycle));
        }
        if (status != null) {
            booleanExpressions.add(QDepositTypeInfo.depositTypeInfo.status.eq(status));
        }
        if (StringUtils.isNotEmpty(investCoin)) {
            booleanExpressions.add(QDepositTypeInfo.depositTypeInfo.investCoin.eq(investCoin));
        }
        if (StringUtils.isNotEmpty(profitCoin)) {
            booleanExpressions.add(QDepositTypeInfo.depositTypeInfo.profitCoin.eq(profitCoin));
        }
        return booleanExpressions;
    }
}
