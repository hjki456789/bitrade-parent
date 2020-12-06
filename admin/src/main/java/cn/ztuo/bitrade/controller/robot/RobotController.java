package cn.ztuo.bitrade.controller.robot;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.kafka.core.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.*;
import java.math.*;
import com.alibaba.fastjson.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "robot" })
public class RobotController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private RobotSymbolConfigService robotSymbolConfigService;
    @Autowired
    private RobotFromExchageConfigService robotFromExchageConfigService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping({ "from-exchage-config/list" })
    @AccessLog(module = AdminModule.ROBOT, operation = "基本配置列表")
    public MessageResult robotFromExchageConfigList() {
        final List<RobotFromExchageConfig> list = (List<RobotFromExchageConfig>)this.robotFromExchageConfigService.findList();
        return this.success(list);
    }

    @RequiresPermissions({ "robot:symbol-config:page-query" })
    @PostMapping({ "symbol-config/page-query" })
    @AccessLog(module = AdminModule.ROBOT, operation = "分页查找机器人配置列表")
    public MessageResult symbolConfigPageList(final PageModel pageModel, final RobotSymbolConfig robotSymbolConfig) {
        final Predicate predicate = this.getPredicate(robotSymbolConfig);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("createTime");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<RobotSymbolConfig> all = (Page<RobotSymbolConfig>)this.robotSymbolConfigService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "symbol-config/detail" })
    @AccessLog(module = AdminModule.ROBOT, operation = "机器人配置详情")
    public MessageResult robotSymbolConfigDetail(@RequestParam("symbol") final String symbol) {
        final RobotSymbolConfig robotSymbolConfig = this.robotSymbolConfigService.findOne(symbol);
        if (robotSymbolConfig == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        return this.success(robotSymbolConfig);
    }

    @RequiresPermissions({ "robot:symbol-config:add" })
    @PostMapping({ "symbol-config/add" })
    @AccessLog(module = AdminModule.ROBOT, operation = "新增机器人配置信息")
    public MessageResult addRobotSymbolConfig(final RobotSymbolConfig robotSymbolConfig) {
        final String symbol = robotSymbolConfig.getSymbol();
        final String fromExchangeSymbol = robotSymbolConfig.getFromExchangeSymbol();
        if (StringUtils.isEmpty((CharSequence)symbol) || StringUtils.isEmpty((CharSequence)fromExchangeSymbol)) {
            return MessageResult.error("交易对以及来源交易所交易对都不能为空!");
        }
        if (robotSymbolConfig.getFromExchangeId() == null) {
            return MessageResult.error("来源交易所不能为空!");
        }
        if (robotSymbolConfig.getMemberId() == null) {
            return MessageResult.error("下单账户不能为空!");
        }
        if (this.robotSymbolConfigService.findOne(symbol) != null) {
            return MessageResult.error("该交易对的机器人配置信息已存在!");
        }
        if (robotSymbolConfig.getBaseCoinBalance() == null) {
            robotSymbolConfig.setBaseCoinBalance(new BigDecimal(0));
        }
        if (robotSymbolConfig.getTradeCoinBalance() == null) {
            robotSymbolConfig.setTradeCoinBalance(new BigDecimal(0));
        }
        if (robotSymbolConfig.getRobotOrderMax() == null) {
            robotSymbolConfig.setRobotOrderMax(new BigDecimal(0));
        }
        if (robotSymbolConfig.getDepthAmountDownRate() == null) {
            robotSymbolConfig.setDepthAmountDownRate(new BigDecimal(0));
        }
        if (robotSymbolConfig.getTradeAmountUpRate() == null) {
            robotSymbolConfig.setTradeAmountUpRate(new BigDecimal(0));
        }
        if (robotSymbolConfig.getBuyPriceDownRate() == null) {
            robotSymbolConfig.setBuyPriceDownRate(new BigDecimal(0));
        }
        if (robotSymbolConfig.getSellPriceUpRate() == null) {
            robotSymbolConfig.setSellPriceUpRate(new BigDecimal(0));
        }
        if (robotSymbolConfig.getFee() == null) {
            robotSymbolConfig.setFee(new BigDecimal(0));
        }
        if (robotSymbolConfig.getStatus() == null) {
            robotSymbolConfig.setStatus(Integer.valueOf(1));
        }
        if (robotSymbolConfig.getArbitrageStatus() == null) {
            robotSymbolConfig.setArbitrageStatus(Integer.valueOf(1));
        }
        this.robotSymbolConfigService.insert(robotSymbolConfig);
        try {
            final List<RobotSymbolConfig> list = new ArrayList<RobotSymbolConfig>();
            list.add(robotSymbolConfig);
            this.kafkaTemplate.send("exchange-start-robot",JSONObject.toJSONString(list));
        }
        catch (Exception ex) {}
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "robot:symbol-config:update" })
    @PostMapping({ "symbol-config/update" })
    @AccessLog(module = AdminModule.ROBOT, operation = "更新机器人配置信息")
    public MessageResult updateRobotSymbolConfig(final RobotSymbolConfig robotSymbolConfig) {
        final String symbol = robotSymbolConfig.getSymbol();
        final RobotSymbolConfig symbolConfig = this.robotSymbolConfigService.findOne(symbol);
        if (symbolConfig == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        if (robotSymbolConfig.getBaseCoinBalance() != null) {
            symbolConfig.setBaseCoinBalance(robotSymbolConfig.getBaseCoinBalance());
        }
        if (robotSymbolConfig.getTradeCoinBalance() != null) {
            symbolConfig.setTradeCoinBalance(robotSymbolConfig.getTradeCoinBalance());
        }
        if (robotSymbolConfig.getRobotOrderMax() != null) {
            symbolConfig.setRobotOrderMax(robotSymbolConfig.getRobotOrderMax());
        }
        if (robotSymbolConfig.getDepthAmountDownRate() != null) {
            symbolConfig.setDepthAmountDownRate(robotSymbolConfig.getDepthAmountDownRate());
        }
        if (robotSymbolConfig.getTradeAmountUpRate() != null) {
            symbolConfig.setTradeAmountUpRate(robotSymbolConfig.getTradeAmountUpRate());
        }
        if (robotSymbolConfig.getBuyPriceDownRate() != null) {
            symbolConfig.setBuyPriceDownRate(robotSymbolConfig.getBuyPriceDownRate());
        }
        if (robotSymbolConfig.getSellPriceUpRate() != null) {
            symbolConfig.setSellPriceUpRate(robotSymbolConfig.getSellPriceUpRate());
        }
        if (robotSymbolConfig.getFee() != null) {
            symbolConfig.setFee(robotSymbolConfig.getFee());
        }
        if (robotSymbolConfig.getStatus() != null) {
            symbolConfig.setStatus(robotSymbolConfig.getStatus());
        }
        if (robotSymbolConfig.getArbitrageStatus() != null) {
            symbolConfig.setArbitrageStatus(robotSymbolConfig.getArbitrageStatus());
        }
        this.robotSymbolConfigService.update(symbolConfig);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "robot:symbol-config:delete" })
    @PostMapping({ "symbol-config/delete" })
    @AccessLog(module = AdminModule.ROBOT, operation = "删除机器人配置信息")
    public MessageResult deleteRobotSymbolConfig(@RequestParam("symbol") final String symbol) {
        this.robotSymbolConfigService.deleteRobotSymbolConfig(symbol);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    public Predicate getPredicate(final RobotSymbolConfig robotSymbolConfig) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)robotSymbolConfig.getSymbol())) {
            booleanExpressions.add(QRobotSymbolConfig.robotSymbolConfig.symbol.like("%" + robotSymbolConfig.getSymbol() + "%"));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
