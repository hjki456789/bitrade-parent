package cn.ztuo.bitrade.controller.coin;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.collections.*;
import java.util.*;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.apache.commons.lang3.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "coin/coin-convert" })
public class CoinConvertController extends BaseAdminController
{
    @Autowired
    private CoinConvertService coinConvertService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "coin:coin-convert:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约交易订单列表")
    public MessageResult pageQuery(final PageModel pageModel, final CoinConvert coinConvert) {
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(coinConvert);
        if (!CollectionUtils.isEmpty((Collection)booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("baseCoin");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.ASC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<CoinConvert> all = (Page<CoinConvert>)this.coinConvertService.findAllPage(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "coin:coin-convert:save" })
    @PostMapping({ "save" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "保存兑币信息")
    public MessageResult saveCoinConvert(final CoinConvert coinConvert) {
        final Long id = coinConvert.getId();
        final String baseCoin = coinConvert.getBaseCoin();
        final String convertCoin = coinConvert.getConvertCoin();
        final Double fee = coinConvert.getFee();
        if (StringUtils.isEmpty((CharSequence)baseCoin)) {
            return MessageResult.error("被转换币不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)convertCoin)) {
            return MessageResult.error("转换币不能为空!");
        }
        if (fee == null) {
            return MessageResult.error("手续费不能为空!");
        }
        if (BigDecimalUtils.compare(BigDecimal.valueOf(fee), BigDecimal.ONE) || !BigDecimalUtils.compareGt(BigDecimal.valueOf(fee), BigDecimal.ZERO)) {
            return MessageResult.error("手续费必须大于0小于1!");
        }
        if (baseCoin.equals(convertCoin)) {
            return MessageResult.error("转换币与被转换币不能相同!");
        }
        final CoinConvert coinConvertBc = this.coinConvertService.findByBaseAndConvert(baseCoin, convertCoin);
        if (id == null) {
            if (coinConvertBc != null) {
                return MessageResult.error("该兑币已存在!");
            }
        }
        else {
            final CoinConvert coinConvertOld = this.coinConvertService.findById(id);
            if (coinConvertOld == null) {
                return MessageResult.error("该兑币信息不存在!");
            }
            if (coinConvertBc != null && !coinConvertBc.getId().equals(coinConvertOld.getId())) {
                return MessageResult.error("该兑币已存在!");
            }
        }
        this.coinConvertService.save(coinConvert);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "coin:coin-convert:delete" })
    @PostMapping({ "delete" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "删除兑币信息")
    public MessageResult delete(final CoinConvert coinConvert) {
        if (coinConvert.getId() == null) {
            return MessageResult.error("ID\u4e0d\u80fd\u4e3a\u7a7a!");
        }
        this.coinConvertService.deleteById(coinConvert.getId());
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    private List<BooleanExpression> getBooleanExpressions(final CoinConvert coinConvert) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!StringUtils.isEmpty((CharSequence)coinConvert.getBaseCoin())) {
            booleanExpressions.add(QCoinConvert.coinConvert.baseCoin.eq(coinConvert.getBaseCoin()));
        }
        if (!StringUtils.isEmpty((CharSequence)coinConvert.getConvertCoin())) {
            booleanExpressions.add(QCoinConvert.coinConvert.convertCoin.eq(coinConvert.getConvertCoin()));
        }
        return booleanExpressions;
    }
}
