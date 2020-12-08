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
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract-double-member-api" })
public class ContractDoubleMemberApiManageController extends BaseAdminController
{
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;
    @Autowired
    private MemberService memberService;

    @RequiresPermissions({ "contract:contract-double-member-api:page-query" })
    @PostMapping({ "config/page-query" })
    @AccessLog(module = AdminModule.CONTRACTSTRATEGY, operation = "分页查找双仓合约API配置列表")
    public MessageResult pageList(final PageModel pageModel, final ContractDoubleMemberApi config) {
        final Predicate predicate = this.getPredicate(config);
        final Page<ContractDoubleMemberApi> page = (Page<ContractDoubleMemberApi>)this.contractDoubleMemberApiService.findAll(predicate, pageModel.getPageable());
        if (null != page && null != page.getContent() && page.getContent().size() > 0) {
            final Map<Long, ContractDoubleExchangeConfig> exchangeMap = new HashMap<>();
            final List<ContractDoubleExchangeConfig> exchangeList = this.contractDoubleExchangeConfigService.findAll();
            exchangeList.forEach(model -> exchangeMap.put(model.getId(), model));
            Map<Long, Member> map=new HashMap<>();
            page.getContent().forEach(model -> {
                Member member=null;
                model.setFromExchangeName(exchangeMap.containsKey(model.getFromExchangeId()) ? exchangeMap.get(model.getFromExchangeId()).getName() : "");
                if (map.containsKey(model.getMemberId())) {
                    member = map.get(model.getMemberId());
                }
                else {
                    member = memberService.findOne(model.getMemberId());
                }
                if (null != member) {
                    model.setMember(member);
                    map.put(member.getId(),member);
                }
                return;
            });
        }
        return this.success(page);
    }

    public Predicate getPredicate(final ContractDoubleMemberApi config) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (null != config.getMemberId()) {
            booleanExpressions.add(QContractDoubleMemberApi.contractDoubleMemberApi.memberId.eq(config.getMemberId()));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
