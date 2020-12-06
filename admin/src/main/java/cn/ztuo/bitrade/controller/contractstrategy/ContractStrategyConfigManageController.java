package cn.ztuo.bitrade.controller.contractstrategy;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractstrategy.*;
import javax.servlet.http.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.contractstrategy.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;

@RestController
@RequestMapping({ "contract-strategy" })
public class ContractStrategyConfigManageController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractStrategyConfigService contractStrategyConfigService;
    @Autowired
    private ContractStrategyExchangeConfigService contractStrategyExchangeConfigService;

    @RequiresPermissions({ "robot:contract-strategy:page-query" })
    @PostMapping({ "config/page-query" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "分页查找合约对冲配置列表")
    public MessageResult pageList(final PageModel pageModel, final ContractStrategyConfig config, final HttpServletRequest request) {
        final Predicate predicate = this.getPredicate(config);
        final Page<ContractStrategyConfig> all = (Page<ContractStrategyConfig>)this.contractStrategyConfigService.findAll(predicate, pageModel.getPageable());
        if (null != all && null != all.getContent() && all.getContent().size() > 0) {
            for (final ContractStrategyConfig model : all.getContent()) {
                final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(model.getFromExchangeId());
                model.setExchangeConfig(exchangeConfig);
            }
        }
        return this.success(all);
    }

    @PostMapping({ "config/detail" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "合约对冲配置详情")
    public MessageResult configDetail(@RequestParam("id") final Long id) {
        final ContractStrategyConfig config = this.contractStrategyConfigService.findOne(id);
        final ContractStrategyExchangeConfig exchangeConfig = this.contractStrategyExchangeConfigService.findById(config.getFromExchangeId());
        config.setExchangeConfig(exchangeConfig);
        if (config == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        return this.success(config);
    }

    @RequiresPermissions({ "robot:contract-strategy:add" })
    @PostMapping({ "config/add" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "新增合约对冲配置信息")
    public MessageResult addConfig(ContractStrategyConfig config, final HttpServletRequest request) {
        Assert.notNull(config.getApiKey(), "API_KEY不能为空");
        Assert.notNull(config.getSecretKey(), "SECRET_KEY不能为空");
        Assert.notNull(config.getSymbol(), "合约交易对不能为空");
        Assert.notNull(config.getFromExchangeId(), "对冲交易所不能为空");
        final Admin admin = this.getAdmin(request);
        config.setAdminId(admin.getId());
        config.setMemberId(admin.getProxyId());
        config.setCreateTime(new Date());
        config.setUpdateTime(new Date());
        config.setStatus(CommonStatus.NORMAL);
        config = this.contractStrategyConfigService.save(config);
        if (null != config) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return this.error(this.messageSource.getMessage("REQUEST_FAILED"));
    }

    @RequiresPermissions({ "robot:contract-strategy:update" })
    @PostMapping({ "config/update" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "更新合约对冲配置信息")
    public MessageResult updateConfig(final ContractStrategyConfig configNew, final HttpServletRequest request) {
        Assert.notNull(configNew.getApiKey(), "API_KEY不能为空");
        Assert.notNull(configNew.getSecretKey(), "SECRET_KEY不能为空");
        Assert.notNull(configNew.getSymbol(), "合约交易对不能为空");
        Assert.notNull(configNew.getFromExchangeId(), "对冲交易所不能为空");
        final Admin admin = this.getAdmin(request);
        ContractStrategyConfig configOld = this.contractStrategyConfigService.findOne(configNew.getId());
        if (configOld == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        if (!admin.getId().equals(configOld.getAdminId())) {
            return MessageResult.error("只能修改自己的配置信息!");
        }
        configOld.setApiKey(configNew.getApiKey());
        configOld.setSecretKey(configNew.getSecretKey());
        configOld.setExpireDate(configNew.getExpireDate());
        configOld.setPriceFallRate(configNew.getPriceFallRate());
        configOld.setPriceRiseRate(configNew.getPriceRiseRate());
        configOld.setStatus(configNew.getStatus());
        configOld.setFromExchangeId(configNew.getFromExchangeId());
        configOld.setUpdateTime(new Date());
        configOld = this.contractStrategyConfigService.save(configOld);
        if (null != configOld) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return this.error(this.messageSource.getMessage("REQUEST_FAILED"));
    }

    @RequiresPermissions({ "robot:contract-strategy:update" })
    @PostMapping({ "config/updateStatus" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "更新合约对冲状态配置信息")
    public MessageResult updateStatusConfig(final ContractStrategyConfig configNew, final HttpServletRequest request) {
        final Admin admin = this.getAdmin(request);
        ContractStrategyConfig configOld = this.contractStrategyConfigService.findOne(configNew.getId());
        if (configOld == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        if (!admin.getId().equals(configOld.getAdminId())) {
            return MessageResult.error("只能修改自己的配置信息!");
        }
        configOld.setStatus(configNew.getStatus());
        configOld.setUpdateTime(new Date());
        configOld = this.contractStrategyConfigService.save(configOld);
        if (null != configOld) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return this.error(this.messageSource.getMessage("REQUEST_FAILED"));
    }

    @RequiresPermissions({ "robot:contract-strategy:delete" })
    @PostMapping({ "config/delete" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "删除合约对冲配置信息")
    public MessageResult deleteConfig(@RequestParam("id") final Long id, final HttpServletRequest request) {
        final Admin admin = this.getAdmin(request);
        final ContractStrategyConfig configOld = this.contractStrategyConfigService.findOne(id);
        if (configOld == null) {
            return MessageResult.error("机器人配置信息不存在!");
        }
        if (!admin.getId().equals(configOld.getAdminId())) {
            return MessageResult.error("只能修改自己的配置信息!");
        }
        final int num = this.contractStrategyConfigService.deleteById(id);
        if (num > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return this.error(this.messageSource.getMessage("REQUEST_FAILED"));
    }

    public Predicate getPredicate(final ContractStrategyConfig config) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (null != config.getStatus()) {
            booleanExpressions.add(QContractStrategyConfig.contractStrategyConfig.status.eq(config.getStatus()));
        }
        if (StringUtils.isNotEmpty((CharSequence)config.getApiKey())) {
            booleanExpressions.add(QContractStrategyConfig.contractStrategyConfig.apiKey.like("%" + config.getSymbol() + "%"));
        }
        if (StringUtils.isNotEmpty((CharSequence)config.getSymbol())) {
            booleanExpressions.add(QContractStrategyConfig.contractStrategyConfig.symbol.like("%" + config.getSymbol() + "%"));
        }
        if (null != config.getAdminId()) {
            booleanExpressions.add(QContractStrategyConfig.contractStrategyConfig.adminId.eq(config.getAdminId()));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
