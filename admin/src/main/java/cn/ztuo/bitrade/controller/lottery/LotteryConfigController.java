package cn.ztuo.bitrade.controller.lottery;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.apache.commons.collections.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.unblock.*;

@RestController
@RequestMapping({ "/lottery/lottery-config" })
public class LotteryConfigController extends BaseAdminController
{
    @Autowired
    UnBlockLotteryConfigService unBlockLotteryConfigService;

    @PostMapping({ "lotteryConfigList" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "奖项列表")
    public MessageResult lotteryConfigList() {
        final List<UnblockLotteryConfig> list = (List<UnblockLotteryConfig>)this.unBlockLotteryConfigService.getAllLotteryConfig();
        return this.success(list);
    }

    @RequiresPermissions({ "lottery:lottery-config:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "奖项分页列表")
    public MessageResult lotteryConfigList(final PageModel pageModel, final UnblockLotteryConfig unblockLotteryConfig) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(unblockLotteryConfig);
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
        final Page<UnblockLotteryConfig> all = (Page<UnblockLotteryConfig>)this.unBlockLotteryConfigService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "detail" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "奖项配置信息详情")
    public MessageResult detail(@RequestParam("id") final Long id) {
        final UnblockLotteryConfig unblockLotteryConfig = this.unBlockLotteryConfigService.findById(id);
        if (unblockLotteryConfig == null) {
            return MessageResult.error("信息不存在!");
        }
        return this.success(unblockLotteryConfig);
    }

    @RequiresPermissions({ "lottery:lottery-config:lotteryConfigAdd" })
    @PostMapping({ "lotteryConfigAdd" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "奖项新增")
    public MessageResult lotteryConfigAdd(final UnblockLotteryConfig config) {
        final MessageResult validResult = this.valid(config);
        if (validResult.getCode() != 0) {
            return validResult;
        }
        final UnblockLotteryConfig configInfo = this.unBlockLotteryConfigService.getOne();
        if (null == configInfo) {
            return MessageResult.error("基本奖项配置信息不存在");
        }
        config.setBuyPrice(configInfo.getBuyPrice());
        config.setLotteryRule(configInfo.getLotteryRule());
        config.setCreateTime(new Date());
        final UnblockLotteryConfig config2 = this.unBlockLotteryConfigService.save(config);
        if (null == config2) {
            return MessageResult.error("FAIL!");
        }
        return MessageResult.success("SUCCESS");
    }

    @RequiresPermissions({ "lottery:lottery-config:update" })
    @PostMapping({ "lotteryConfigUpdate" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "奖项更新")
    public MessageResult lotteryConfigUpdate(final UnblockLotteryConfig config) {
        if (config.getId() == null) {
            return MessageResult.error("ID不能为空");
        }
        final UnblockLotteryConfig configInfo = this.unBlockLotteryConfigService.findById(config.getId());
        if (null == configInfo) {
            return MessageResult.error("未找到该奖项配置");
        }
        final MessageResult validResult = this.valid(config);
        if (validResult.getCode() != 0) {
            return validResult;
        }
        final int a = this.unBlockLotteryConfigService.update(config);
        if (a == 0) {
            return MessageResult.error("FAIL!");
        }
        return MessageResult.success("SUCCESS");
    }

    @RequiresPermissions({ "lottery:lottery-config:lotteryConfigDel" })
    @PostMapping({ "lotteryConfigDel" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "奖项删除")
    public MessageResult lotteryConfigDel(@RequestParam("id") final Long id) {
        this.unBlockLotteryConfigService.delete(id);
        return MessageResult.success("SUCCESS");
    }

    @PostMapping({ "basicConfigDetail" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "基本奖项配置详情")
    public MessageResult basicConfigDetail() {
        final UnblockLotteryConfig unblockLotteryConfig = this.unBlockLotteryConfigService.getOne();
        if (unblockLotteryConfig == null) {
            return MessageResult.error("信息不存在!");
        }
        return this.success(unblockLotteryConfig);
    }

    @RequiresPermissions({ "lottery:lottery-config:update" })
    @PostMapping({ "updateBasicConfig" })
    @AccessLog(module = AdminModule.LOTTERY, operation = "更新基本奖项配置(抽奖规则与购买抽奖次数单价)")
    public MessageResult updateBasicConfig(@RequestParam("lotteryRule") final String lotteryRule, @RequestParam("buyPrice") final BigDecimal buyPrice) {
        if (null == buyPrice || !BigDecimalUtils.compareGt(buyPrice, BigDecimal.ZERO)) {
            return MessageResult.error("抽奖次数购买单价必须大于0!");
        }
        if (StringUtils.isEmpty((CharSequence)lotteryRule)) {
            return MessageResult.error("抽奖规则不能为空!");
        }
        final int a = this.unBlockLotteryConfigService.updateRuleAndPrice(lotteryRule, buyPrice);
        if (a == 0) {
            return MessageResult.error("FAIL!");
        }
        return MessageResult.success("SUCCESS");
    }

    private List<BooleanExpression> getBooleanExpressions(final UnblockLotteryConfig unblockLotteryConfig) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)unblockLotteryConfig.getLotteryLevel())) {
            booleanExpressions.add(QUnblockLotteryConfig.unblockLotteryConfig.lotteryLevel.like("%" + unblockLotteryConfig.getLotteryLevel() + "%"));
        }
        return booleanExpressions;
    }

    public MessageResult valid(final UnblockLotteryConfig config) {
        final String lotteryLevel = config.getLotteryLevel();
        final BigDecimal lotteryRate = config.getLotteryRate();
        final String lotteryInstruction = config.getLotteryInstruction();
        final String coin = config.getCoin();
        final BigDecimal amount = config.getAmount();
        final BigDecimal insuranceRate = config.getInsuranceRate();
        if (null == lotteryRate || !BigDecimalUtils.compare(lotteryRate, BigDecimal.ZERO) || BigDecimalUtils.compareGt(lotteryRate, BigDecimal.ONE)) {
            return MessageResult.error("中奖几率必须大于等于0小于等于1!");
        }
        if (StringUtils.isEmpty((CharSequence)lotteryLevel)) {
            return MessageResult.error("奖项级别不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)lotteryInstruction)) {
            return MessageResult.error("奖项说明不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)coin)) {
            return MessageResult.error("奖励币种不能为空!");
        }
        if (null == amount || !BigDecimalUtils.compareGt(amount, BigDecimal.ZERO)) {
            return MessageResult.error("奖励数量必须大于0!");
        }
        if (null == insuranceRate || !BigDecimalUtils.compare(insuranceRate, BigDecimal.ZERO) || BigDecimalUtils.compare(insuranceRate, BigDecimal.ONE)) {
            return MessageResult.error("保险基金比例必须大于等于0小于1!");
        }
        return MessageResult.success("SUCCESS");
    }
}
