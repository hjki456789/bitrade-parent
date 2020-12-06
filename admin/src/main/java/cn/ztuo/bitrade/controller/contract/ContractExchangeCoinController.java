package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.kafka.core.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import com.alibaba.fastjson.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract/contract-coin" })
public class ContractExchangeCoinController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractCoinService contractCoinService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequiresPermissions({ "contract:contract-coin:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约交易币种列表")
    public MessageResult contractCoinPageList(final ContractCoin contractCoin, final PageModel pageModel) {
        final Predicate predicate = this.getPredicate(contractCoin);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sort");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.ASC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractCoin> all = (Page<ContractCoin>)this.contractCoinService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "list" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "有效合约币种列表")
    public MessageResult contractCoinList() {
        final List<ContractCoin> list = (List<ContractCoin>)this.contractCoinService.findAllEnabled();
        return this.success(list);
    }

    @RequiresPermissions({ "contract:contract-coin:add-coin" })
    @PostMapping({ "add-coin" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "新增币种信息")
    public MessageResult addContractCoin(final ContractCoin contractCoin) {
        if (StringUtils.isEmpty((CharSequence)contractCoin.getBaseSymbol())) {
            return MessageResult.error("计价币种不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)contractCoin.getCoinSymbol())) {
            return MessageResult.error("交易币种不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)contractCoin.getCoinSymbolName())) {
            return MessageResult.error("交易币种名称不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)contractCoin.getLeverMultiple())) {
            return MessageResult.error("杠杆倍数不得为空!");
        }
        contractCoin.setSymbol(contractCoin.getCoinSymbol() + "/" + contractCoin.getBaseSymbol());
        final ContractCoin contractCoinSymbol = this.contractCoinService.findBySymbol(contractCoin.getSymbol());
        if (contractCoinSymbol != null) {
            return MessageResult.error("该交易对已存在!");
        }
        if (contractCoin.getCoinSymbol().equals(contractCoin.getBaseSymbol())) {
            return MessageResult.error("该交易对不合法!");
        }
        if (contractCoin.getOpenFee() == null) {
            contractCoin.setOpenFee(new BigDecimal(0));
        }
        if (contractCoin.getCloseFee() == null) {
            contractCoin.setCloseFee(new BigDecimal(0));
        }
        if (contractCoin.getDayFeeRate() == null) {
            contractCoin.setDayFeeRate(new BigDecimal(0));
        }
        if (contractCoin.getMinLimit() == null) {
            contractCoin.setMinLimit(new BigDecimal(0));
        }
        if (contractCoin.getMaxLimit() == null) {
            contractCoin.setMaxLimit(new BigDecimal(0));
        }
        if (contractCoin.getMaxHoldLimit() == null) {
            contractCoin.setMaxHoldLimit(new BigDecimal(0));
        }
        if (contractCoin.getRiseInterval() == null) {
            contractCoin.setRiseInterval(new BigDecimal(0));
        }
        if (contractCoin.getFallInterval() == null) {
            contractCoin.setFallInterval(new BigDecimal(0));
        }
        if (contractCoin.getSort() == null) {
            contractCoin.setSort(Integer.valueOf(0));
        }
        if (contractCoin.getCoinSymbolPrecision() == null) {
            contractCoin.setCoinSymbolPrecision(Integer.valueOf(2));
        }
        if (contractCoin.getEnable() == null) {
            contractCoin.setEnable(Integer.valueOf(1));
        }
        this.contractCoinService.save(contractCoin);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-coin:update-coin" })
    @PostMapping({ "update-coin" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "更新币种信息")
    public MessageResult updateContractCoin(final ContractCoin contractCoin) {
        final ContractCoin contractCoinOld = this.contractCoinService.findOne(contractCoin.getId());
        if (contractCoinOld == null) {
            return MessageResult.error("无效合约币种!");
        }
        if (contractCoin.getCoinSymbol().equals(contractCoin.getBaseSymbol())) {
            return MessageResult.error("该交易对不合法!");
        }
        if (StringUtils.isNotEmpty((CharSequence)contractCoin.getBaseSymbol())) {
            contractCoinOld.setBaseSymbol(contractCoin.getBaseSymbol());
        }
        if (StringUtils.isNotEmpty((CharSequence)contractCoin.getCoinSymbol())) {
            contractCoinOld.setCoinSymbol(contractCoin.getCoinSymbol());
        }
        if (StringUtils.isNotEmpty((CharSequence)contractCoin.getCoinSymbolName())) {
            contractCoinOld.setCoinSymbolName(contractCoin.getCoinSymbolName());
        }
        if (StringUtils.isNotEmpty((CharSequence)contractCoin.getCoinSymbolImageUrl())) {
            contractCoinOld.setCoinSymbolImageUrl(contractCoin.getCoinSymbolImageUrl());
        }
        if (StringUtils.isNotEmpty((CharSequence)contractCoin.getLeverMultiple())) {
            contractCoinOld.setLeverMultiple(contractCoin.getLeverMultiple());
        }
        if (contractCoin.getOpenFee() != null) {
            contractCoinOld.setOpenFee(contractCoin.getOpenFee());
        }
        if (contractCoin.getCloseFee() != null) {
            contractCoinOld.setCloseFee(contractCoin.getCloseFee());
        }
        if (contractCoin.getDayFeeRate() != null) {
            contractCoinOld.setDayFeeRate(contractCoin.getDayFeeRate());
        }
        if (contractCoin.getMinLimit() != null) {
            contractCoinOld.setMinLimit(contractCoin.getMinLimit());
        }
        if (contractCoin.getMaxLimit() != null) {
            contractCoinOld.setMaxLimit(contractCoin.getMaxLimit());
        }
        if (contractCoin.getMaxHoldLimit() != null) {
            contractCoinOld.setMaxHoldLimit(contractCoin.getMaxHoldLimit());
        }
        if (contractCoin.getRiseInterval() != null) {
            contractCoinOld.setRiseInterval(contractCoin.getRiseInterval());
        }
        if (contractCoin.getFallInterval() != null) {
            contractCoinOld.setFallInterval(contractCoin.getFallInterval());
        }
        if (contractCoin.getSort() != null) {
            contractCoinOld.setSort(contractCoin.getSort());
        }
        if (contractCoin.getCoinSymbolPrecision() != null) {
            contractCoinOld.setCoinSymbolPrecision(contractCoin.getCoinSymbolPrecision());
        }
        contractCoinOld.setSymbol(contractCoinOld.getCoinSymbol() + "/" + contractCoinOld.getBaseSymbol());
        contractCoinOld.setContractType(contractCoin.getContractType());
        contractCoinOld.setCanBuyDown(contractCoin.getCanBuyDown());
        contractCoinOld.setCanBuyUp(contractCoin.getCanBuyUp());
        this.contractCoinService.save(contractCoinOld);
        try {
            final List<ContractCoin> list = new ArrayList<ContractCoin>();
            list.add(contractCoinOld);
            this.kafkaTemplate.send("contract-market-symbol", JSONObject.toJSONString(contractCoinOld));
        }
        catch (Exception ex) {}
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-coin:detail-coin" })
    @PostMapping({ "detail-coin" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "币种信息详情")
    public MessageResult contractCoinDetail(@RequestParam("id") final String id) {
        final ContractCoin contractCoin = this.contractCoinService.findOne(id);
        Assert.notNull(contractCoin, "validate symbol!");
        return this.success(contractCoin);
    }

    @RequiresPermissions({ "contract:contract-coin:delete-coin" })
    @PostMapping({ "delete-coin" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "删除币种信息")
    public MessageResult DeleteContractCoin(@RequestParam("ids") final String[] ids) {
        this.contractCoinService.delete(ids);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-coin:alter-enable" })
    @PostMapping({ "alter-enable" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "修改禁用状态")
    public MessageResult alterEnable(@RequestParam("id") final String id, @RequestParam("enable") final Integer enable) {
        Assert.notNull(enable, "validate enable!");
        final int result = this.contractCoinService.updateEnable(id, enable);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    private Predicate getPredicate(final ContractCoin contractCoin) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)contractCoin.getBaseSymbol())) {
            booleanExpressions.add(QContractCoin.contractCoin.baseSymbol.eq(contractCoin.getBaseSymbol()));
        }
        if (!StringUtils.isEmpty((CharSequence)contractCoin.getCoinSymbol())) {
            booleanExpressions.add(QContractCoin.contractCoin.coinSymbol.eq(contractCoin.getCoinSymbol()));
        }
        if (!StringUtils.isEmpty((CharSequence)contractCoin.getCoinSymbolName())) {
            booleanExpressions.add(QContractCoin.contractCoin.coinSymbolName.eq(contractCoin.getCoinSymbolName()));
        }
        if (!StringUtils.isEmpty((CharSequence)contractCoin.getSymbol())) {
            booleanExpressions.add(QContractCoin.contractCoin.symbol.eq(contractCoin.getSymbol()));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
