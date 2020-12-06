package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.kafka.core.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.*;
import com.alibaba.fastjson.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract-robot" })
public class ContractRobotController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractRobotSymbolConfigService contractRobotSymbolConfigService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequiresPermissions({ "contract-robot:symbol-config:page-query" })
    @PostMapping({ "symbol-config/page-query" })
    @AccessLog(module = AdminModule.ROBOT, operation = "分页查找机器人配置列表")
    public MessageResult symbolConfigPageList(final PageModel pageModel, final ContractRobotSymbolConfig robotSymbolConfig) {
        final Predicate predicate = this.getPredicate(robotSymbolConfig);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("createTime");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractRobotSymbolConfig> all = (Page<ContractRobotSymbolConfig>)this.contractRobotSymbolConfigService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "symbol-config/detail" })
    @AccessLog(module = AdminModule.ROBOT, operation = "机器人配置详情")
    public MessageResult robotSymbolConfigDetail(@RequestParam("symbol") final String symbol) {
        final ContractRobotSymbolConfig robotSymbolConfig = this.contractRobotSymbolConfigService.findOne(symbol);
        if (robotSymbolConfig == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        return this.success(robotSymbolConfig);
    }

    @RequiresPermissions({ "contract-robot:symbol-config:add" })
    @PostMapping({ "symbol-config/add" })
    @AccessLog(module = AdminModule.ROBOT, operation = "新增机器人配置信息")
    public MessageResult addRobotSymbolConfig(final ContractRobotSymbolConfig robotSymbolConfig) {
        final String symbol = robotSymbolConfig.getSymbol();
        final String fromExchangeSymbol = robotSymbolConfig.getFromExchangeSymbol();
        if (StringUtils.isEmpty((CharSequence)symbol) || StringUtils.isEmpty((CharSequence)fromExchangeSymbol)) {
            return MessageResult.error("交易对以及来源交易所交易对都不能为空!");
        }
        if (robotSymbolConfig.getFromExchangeId() == null) {
            return MessageResult.error("来源交易所不能为空!");
        }
        if (this.contractRobotSymbolConfigService.findOne(symbol) != null) {
            return MessageResult.error("该交易对的机器人配置信息已存在!");
        }
        if (robotSymbolConfig.getStatus() == null) {
            robotSymbolConfig.setStatus(Integer.valueOf(1));
        }
        this.contractRobotSymbolConfigService.insert(robotSymbolConfig);
        try {
            final List<ContractRobotSymbolConfig> list = new ArrayList<ContractRobotSymbolConfig>();
            list.add(robotSymbolConfig);
            this.kafkaTemplate.send("contract-exchange-start-robot", JSONObject.toJSONString(list));
        }
        catch (Exception ex) {}
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract-robot:symbol-config:update" })
    @PostMapping({ "symbol-config/update" })
    @AccessLog(module = AdminModule.ROBOT, operation = "更新机器人配置信息")
    public MessageResult updateRobotSymbolConfig(final ContractRobotSymbolConfig robotSymbolConfig) {
        final String symbol = robotSymbolConfig.getSymbol();
        final ContractRobotSymbolConfig symbolConfig = this.contractRobotSymbolConfigService.findOne(symbol);
        if (symbolConfig == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        symbolConfig.setFromExchangeId(robotSymbolConfig.getFromExchangeId());
        symbolConfig.setFromExchangeSymbol(robotSymbolConfig.getFromExchangeSymbol());
        symbolConfig.setAmountMultiple(robotSymbolConfig.getAmountMultiple());
        if (robotSymbolConfig.getStatus() != null) {
            symbolConfig.setStatus(robotSymbolConfig.getStatus());
        }
        this.contractRobotSymbolConfigService.update(symbolConfig);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract-robot:symbol-config:delete" })
    @PostMapping({ "symbol-config/delete" })
    @AccessLog(module = AdminModule.ROBOT, operation = "删除机器人配置信息")
    public MessageResult deleteRobotSymbolConfig(@RequestParam("symbol") final String symbol) {
        this.contractRobotSymbolConfigService.deleteRobotSymbolConfig(symbol);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    public Predicate getPredicate(final ContractRobotSymbolConfig robotSymbolConfig) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)robotSymbolConfig.getSymbol())) {
            booleanExpressions.add(QContractRobotSymbolConfig.contractRobotSymbolConfig.symbol.like("%" + robotSymbolConfig.getSymbol() + "%"));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
