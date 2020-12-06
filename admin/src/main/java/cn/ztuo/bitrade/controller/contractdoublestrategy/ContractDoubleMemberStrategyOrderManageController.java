package cn.ztuo.bitrade.controller.contractdoublestrategy;

import cn.ztuo.bitrade.controller.common.*;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract-double-member-order" })
public class ContractDoubleMemberStrategyOrderManageController extends BaseAdminController
{
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;
    @Autowired
    private MemberService memberService;

    @RequiresPermissions({ "contract:contract-double-member-order:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "分页查找双仓合约下单列表")
    public MessageResult pageList(final PageModel pageModel, final ContractDoubleMemberStrategyOrder config) {
        final Predicate predicate = this.getPredicate(config);
        final Page<ContractDoubleMemberStrategyOrder> page = this.contractDoubleMemberStrategyOrderService.findAll(predicate, pageModel.getPageable());
        if (null != page && null != page.getContent() && page.getContent().size() > 0) {
            Map<Long, ContractDoubleExchangeConfig> exchangeMap = new HashMap();
            List<ContractDoubleExchangeConfig> exchangeList = this.contractDoubleExchangeConfigService.findAll();
            exchangeList.forEach(model -> exchangeMap.put(model.getId(), model));
            Map<K, ContractDoubleExchangeConfig> map=new HashMap<>();;
            Map<K, Member> map2=new HashMap<>();;
            page.getContent().forEach(model -> {
                Member member;
                model.setFromExchangeName(map.containsKey(model.getFromExchangeId()) ? map.get(model.getFromExchangeId()).getName() : "");
                if (map2.containsKey(model.getMemberId())) {
                    member = map2.get(model.getMemberId());
                }
                else {
                    member = this.memberService.findOne(model.getMemberId());
                }
                if (null != member) {
                    model.setMember(member);
                }
                return;
            });
        }
        return this.success(page);
    }

    public Predicate getPredicate(final ContractDoubleMemberStrategyOrder config) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (null != config.getMemberId()) {
            booleanExpressions.add(QContractDoubleMemberStrategyOrder.contractDoubleMemberStrategyOrder.memberId.eq(config.getMemberId()));
        }
        if (null != config.getContractOrderId()) {
            booleanExpressions.add(QContractDoubleMemberStrategyOrder.contractDoubleMemberStrategyOrder.contractOrderId.eq(config.getContractOrderId()));
        }
        if (StringUtils.isNotEmpty((CharSequence)config.getFromExchangeOrderId())) {
            booleanExpressions.add(QContractDoubleMemberStrategyOrder.contractDoubleMemberStrategyOrder.fromExchangeOrderId.eq(config.getFromExchangeOrderId()));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
