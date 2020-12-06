package cn.ztuo.bitrade.controller.unblock;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.unblock.UnblockExchangeSymbolService;
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
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.unbolck.*;

@RestController
@RequestMapping({ "unblock/exchange-symbol" })
public class UnblockExchangeSymbolController extends BaseAdminController
{
    @Autowired
    private UnblockExchangeSymbolService unblockExchangeSymbolService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "unblock:exchange-symbol:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "分页查找解封交易对配置信息列表")
    public MessageResult pageQuery(final PageModel pageModel, final UnblockExchangeSymbol unblockExchangeSymbol) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(unblockExchangeSymbol);
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
        final Page<UnblockExchangeSymbol> all = (Page<UnblockExchangeSymbol>)this.unblockExchangeSymbolService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "detail" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "解封交易对配置信息详情")
    public MessageResult detail(@RequestParam("symbol") final String symbol) {
        final UnblockExchangeSymbol unblockExchangeSymbol = this.unblockExchangeSymbolService.findBySymbol(symbol);
        if (unblockExchangeSymbol == null) {
            return MessageResult.error("信息不存在!");
        }
        return this.success(unblockExchangeSymbol);
    }

    @RequiresPermissions({ "unblock:exchange-symbol:add" })
    @PostMapping({ "add" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "保存解封交易对配置信息")
    public MessageResult save(final UnblockExchangeSymbol unblockExchangeSymbol) {
        String whitelistMemberId = unblockExchangeSymbol.getWhitelistMemberId();
        if (!StringUtils.isEmpty((CharSequence)whitelistMemberId)) {
            whitelistMemberId = whitelistMemberId.replaceAll("，", ",");
            unblockExchangeSymbol.setWhitelistMemberId(whitelistMemberId);
        }
        final MessageResult validResult = this.valid(unblockExchangeSymbol);
        if (validResult.getCode() != 0) {
            return validResult;
        }
        final UnblockExchangeSymbol unblockSymbol = this.unblockExchangeSymbolService.findBySymbol(unblockExchangeSymbol.getSymbol());
        if (unblockSymbol != null) {
            return MessageResult.error("该交易对配置已存在!");
        }
        this.unblockExchangeSymbolService.insert(unblockExchangeSymbol);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "unblock:exchange-symbol:update" })
    @PostMapping({ "update" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "更新解封交易对配置信息")
    public MessageResult update(final UnblockExchangeSymbol unblockExchangeSymbol) {
        String whitelistMemberId = unblockExchangeSymbol.getWhitelistMemberId();
        if (!StringUtils.isEmpty((CharSequence)whitelistMemberId)) {
            whitelistMemberId = whitelistMemberId.replaceAll("，", ",");
            unblockExchangeSymbol.setWhitelistMemberId(whitelistMemberId);
        }
        final MessageResult validResult = this.valid(unblockExchangeSymbol);
        if (validResult.getCode() != 0) {
            return validResult;
        }
        final UnblockExchangeSymbol unblockSymbol = this.unblockExchangeSymbolService.findBySymbol(unblockExchangeSymbol.getSymbol());
        if (unblockSymbol == null) {
            return MessageResult.error("该交易对配置不存在!");
        }
        unblockExchangeSymbol.setVersion(unblockSymbol.getVersion());
        this.unblockExchangeSymbolService.update(unblockExchangeSymbol);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "unblock:exchange-symbol:delete" })
    @PostMapping({ "delete" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "删除解封交易对配置信息")
    public MessageResult deleteRobotSymbolConfig(@RequestParam("symbol") final String symbol) {
        this.unblockExchangeSymbolService.deleteBySymbol(symbol);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    public MessageResult valid(final UnblockExchangeSymbol unblockExchangeSymbol) {
        final String symbol = unblockExchangeSymbol.getSymbol();
        final BigDecimal singleRoundReleaseRate = unblockExchangeSymbol.getSingleRoundReleaseRate();
        final BigDecimal dayMaxReleaseRate = unblockExchangeSymbol.getDayMaxReleaseRate();
        final BigDecimal dayMaxReleaseAmount = unblockExchangeSymbol.getDayMaxReleaseAmount();
        final BigDecimal startPrice = unblockExchangeSymbol.getStartPrice();
        final BigDecimal priceRiseRule = unblockExchangeSymbol.getPriceRiseRule();
        final BigDecimal roundIntervalPrice = unblockExchangeSymbol.getRoundIntervalPrice();
        final int singleDayRoundMax = unblockExchangeSymbol.getSingleDayRoundMax();
        final String roundLimitKey = unblockExchangeSymbol.getRoundLimitKey();
        final String roundLimitValue = unblockExchangeSymbol.getRoundLimitValue();
        final BigDecimal priceLimit = unblockExchangeSymbol.getPriceLimit();
        if (StringUtils.isEmpty((CharSequence)symbol)) {
            return MessageResult.error("交易对不能为空!");
        }
        if (singleRoundReleaseRate == null || !BigDecimalUtils.compareGt(singleRoundReleaseRate, BigDecimal.ZERO) || BigDecimalUtils.compare(singleRoundReleaseRate, BigDecimal.ONE)) {
            return MessageResult.error("单轮释放比例必须大于0小于1!");
        }
        if (dayMaxReleaseRate == null || !BigDecimalUtils.compareGt(dayMaxReleaseRate, BigDecimal.ZERO) || BigDecimalUtils.compare(dayMaxReleaseRate, BigDecimal.ONE)) {
            return MessageResult.error("单日最高释放比例必须大于0小于1!");
        }
        if (dayMaxReleaseAmount == null || !BigDecimalUtils.compareGt(dayMaxReleaseAmount, BigDecimal.ZERO)) {
            return MessageResult.error("单日最高释放数量必须大于0!");
        }
        if (startPrice == null || !BigDecimalUtils.compareGt(startPrice, BigDecimal.ZERO)) {
            return MessageResult.error("初始开仓价格必须大于0!");
        }
        if (priceRiseRule == null || !BigDecimalUtils.compareGt(priceRiseRule, BigDecimal.ZERO)) {
            return MessageResult.error("上浮价格规则必须大于0!");
        }
        if (roundIntervalPrice == null || !BigDecimalUtils.compareGt(roundIntervalPrice, BigDecimal.ZERO)) {
            return MessageResult.error("卖单和当前价的价格差值必须大于0!");
        }
        if (singleDayRoundMax <= 0) {
            return MessageResult.error("单日最大交易轮次必须大于0!");
        }
        if (StringUtils.isEmpty((CharSequence)roundLimitKey)) {
            return MessageResult.error("轮次配置不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)roundLimitValue)) {
            return MessageResult.error("轮次对应的交易限额不能为空!");
        }
        if (priceLimit == null || !BigDecimalUtils.compare(priceLimit, BigDecimal.ZERO)) {
            return MessageResult.error("价格限制必须大于等于0!");
        }
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    private List<BooleanExpression> getBooleanExpressions(final UnblockExchangeSymbol unblockExchangeSymbol) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)unblockExchangeSymbol.getSymbol())) {
            booleanExpressions.add(QUnblockExchangeSymbol.unblockExchangeSymbol.symbol.eq(unblockExchangeSymbol.getSymbol()));
        }
        return booleanExpressions;
    }
}
