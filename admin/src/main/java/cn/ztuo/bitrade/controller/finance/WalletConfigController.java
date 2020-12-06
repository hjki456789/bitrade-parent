package cn.ztuo.bitrade.controller.finance;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "/finance/wallet-config" })
public class WalletConfigController extends BaseAdminController
{
    @Autowired
    private WalletConfigService walletConfigService;

    @RequiresPermissions({ "finance:wallet-config:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.FINANCE, operation = "分页获取冷热钱包配置列表")
    public MessageResult symbolConfigPageList(final PageModel pageModel, final WalletConfig walletConfig) {
        final Predicate predicate = this.getPredicate(walletConfig);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("id");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.ASC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<WalletConfig> all = (Page<WalletConfig>)this.walletConfigService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    public Predicate getPredicate(final WalletConfig walletConfig) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (walletConfig.getType() != null) {
            booleanExpressions.add(QWalletConfig.walletConfig.type.eq(walletConfig.getType()));
        }
        if (!StringUtils.isEmpty((CharSequence)walletConfig.getCoinName())) {
            booleanExpressions.add(QWalletConfig.walletConfig.coinName.like("%" + walletConfig.getCoinName() + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)walletConfig.getAddress())) {
            booleanExpressions.add(QWalletConfig.walletConfig.address.like("%" + walletConfig.getAddress() + "%"));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
