package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.enums.*;
import java.util.*;
import org.apache.commons.lang.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract/robot-pin-strategy" })
public class ContractRobotPinStrategyController extends BaseAdminController
{
    @Autowired
    private ContractRobotPinStrategyService contractRobotPinStrategyService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "contract:robot-pin-strategy:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约插针策略列表")
    public MessageResult pageQuery(final String symbol, final Integer status, final Integer side, final PageModel pageModel) {
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("createTime");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractRobotPinStrategy> all = (Page<ContractRobotPinStrategy>)this.contractRobotPinStrategyService.findPage(symbol, status, side, pageModel.getPageable());
        return this.success((Object)all);
    }

    @RequiresPermissions({ "contract:robot-pin-strategy:edit" })
    @PostMapping({ "save" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "保存合约插针策略信息")
    public MessageResult update(final ContractRobotPinStrategy contractRobotPinStrategy) {
        if (contractRobotPinStrategy.getId() != null) {
            final ContractRobotPinStrategy info = this.contractRobotPinStrategyService.findById(contractRobotPinStrategy.getId());
            if (info == null) {
                return MessageResult.error("信息不存在!");
            }
            contractRobotPinStrategy.setStatus(info.getStatus());
            contractRobotPinStrategy.setCreateTime(info.getCreateTime());
        }
        else {
            contractRobotPinStrategy.setStatus(ContractRobotPinStrategyStatus.RUNNING);
            contractRobotPinStrategy.setCreateTime(new Date());
        }
        contractRobotPinStrategy.setUpdateTime(new Date());
        if (StringUtils.isEmpty(contractRobotPinStrategy.getSymbol())) {
            return MessageResult.error("交易对不能为空!");
        }
        if (contractRobotPinStrategy.getSide() == null) {
            return MessageResult.error("插针方向不能为空!");
        }
        if (contractRobotPinStrategy.getPriceRangeMin() == null || !BigDecimalUtils.compareGt(contractRobotPinStrategy.getPriceRangeMin(), BigDecimal.ZERO)) {
            return MessageResult.error("价格波动最小值必须大于等于0!");
        }
        if (contractRobotPinStrategy.getPriceRangeMax() == null || !BigDecimalUtils.compareGt(contractRobotPinStrategy.getPriceRangeMax(), BigDecimal.ZERO)) {
            return MessageResult.error("价格波动最大值必须大于等于0!");
        }
        if (contractRobotPinStrategy.getAmountRangeMin() == null || !BigDecimalUtils.compareGt(contractRobotPinStrategy.getAmountRangeMin(), BigDecimal.ZERO)) {
            return MessageResult.error("数量波动最小值必须大于等于0!");
        }
        if (contractRobotPinStrategy.getAmountRangeMax() == null || !BigDecimalUtils.compareGt(contractRobotPinStrategy.getAmountRangeMax(), BigDecimal.ZERO)) {
            return MessageResult.error("数量波动最大值必须大于等于0!");
        }
        if (BigDecimalUtils.compare(contractRobotPinStrategy.getPriceRangeMin(), contractRobotPinStrategy.getPriceRangeMax())) {
            return MessageResult.error("价格波动最小值必须小于价格波动最大值!");
        }
        if (BigDecimalUtils.compare(contractRobotPinStrategy.getAmountRangeMin(), contractRobotPinStrategy.getAmountRangeMax())) {
            return MessageResult.error("数量波动最小值必须小于数量波动最大值!");
        }
        if (contractRobotPinStrategy.getTargetPrice() == null || !BigDecimalUtils.compareGt(contractRobotPinStrategy.getTargetPrice(), BigDecimal.ZERO)) {
            return MessageResult.error("目标价格必须大于等于0!");
        }
        if (contractRobotPinStrategy.getAntiDirectionMultiple() == null || !BigDecimalUtils.compareGt(contractRobotPinStrategy.getAntiDirectionMultiple(), BigDecimal.ZERO)) {
            return MessageResult.error("反方向倍数必须大于等于0!");
        }
        if (contractRobotPinStrategy.getFrequency() == null || contractRobotPinStrategy.getFrequency() < 0) {
            return MessageResult.error("间隔时间大于等于0!");
        }
        if (contractRobotPinStrategy.getStartEffectTime() == null) {
            contractRobotPinStrategy.setStartEffectTime(Long.valueOf(System.currentTimeMillis()));
        }
        this.contractRobotPinStrategyService.save(contractRobotPinStrategy);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:robot-pin-strategy:edit" })
    @PostMapping({ "updateStatus" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "更新状态")
    public MessageResult updateStatus(final Long id, final Integer status) {
        if (id == null || status == null) {
            return MessageResult.error("参数错误!");
        }
        if (status == ContractRobotPinStrategyStatus.RUNNING.getOrdinal()) {
            return MessageResult.error("停止运行状态下不可修改运行状态!");
        }
        final int num = this.contractRobotPinStrategyService.updateStatus(id, ContractRobotPinStrategyStatus.STOP);
        if (num > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("更新失败!");
    }
}
