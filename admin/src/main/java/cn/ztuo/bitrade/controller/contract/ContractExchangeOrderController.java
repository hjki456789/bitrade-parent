package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.collections.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import javax.servlet.http.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.lang3.*;

@RestController
@RequestMapping({ "contract/contract-order" })
public class ContractExchangeOrderController extends BaseAdminController
{
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private MemberPromotionService memberPromotionService;

    @RequiresPermissions({ "contract:contract-order:page-query", "contract:contract-order:page-query-close", "contract:contract-order:page-query-limit", "contract:contract-order:page-query-market" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约交易订单列表")
    public MessageResult ContractOrderList(final PageModel pageModel, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final Integer orderCategory, final Long memberId, final String username, final String mobilePhone, final String email, Long proxyId, final Long orderId, final ContractExchangeOrderCloseType orderCloseType, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date closeStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date closeEndTime, final ContractExchangeOrder contractExchangeOrder) {
        if (admin != null && admin.getProxyId() != null) {
            proxyId = admin.getProxyId();
        }
        List<Long> memberIdList = null;
        if (proxyId != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, proxyId, true, false);
        }
        if (!CollectionUtils.isEmpty((Collection)memberIdList)) {
            if (memberId != null) {
                if (!memberIdList.contains(memberId)) {
                    return this.success(new PageImpl((List)new ArrayList()));
                }
                memberIdList = new ArrayList<Long>();
                memberIdList.add(memberId);
            }
        }
        else {
            memberIdList = new ArrayList<Long>();
            if (memberId != null) {
                memberIdList.add(memberId);
            }
        }
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(orderCategory, memberIdList, username, mobilePhone, email, orderCloseType, startTime, endTime, closeStartTime, closeEndTime, contractExchangeOrder, orderId);
        if (!CollectionUtils.isEmpty((Collection)booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractExchangeOrder> all = (Page<ContractExchangeOrder>)this.contractOrderService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "contract:contract-order:out-excel" })
    @GetMapping({ "out-excel" })
    @AccessLog(module = AdminModule.OTC, operation = "导出合约订单Order Excel")
    public MessageResult outExcel(final PageModel pageModel, final Integer orderCategory, final Long memberId, final String username, final String mobilePhone, final String email, final Long proxyId, final Long orderId, final ContractExchangeOrderCloseType orderCloseType, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date closeStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date closeEndTime, final ContractExchangeOrder contractExchangeOrder, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(orderCategory, null, username, mobilePhone, email, orderCloseType, startTime, endTime, closeStartTime, closeEndTime, contractExchangeOrder, orderId);
        final List<ContractExchangeOrder> list = (List<ContractExchangeOrder>)this.contractOrderService.outExcel((List)booleanExpressions, pageModel).getContent();
        return new FileUtil().exportExcel(request, response, (List)list, "contract_order");
    }

    private List<BooleanExpression> getBooleanExpressions(final Integer orderCategory, final List<Long> memberIdList, final String username, final String mobilePhone, final String email, final ContractExchangeOrderCloseType orderCloseType, final Date startTime, final Date endTime, final Date closeStartTime, final Date closeEndTime, final ContractExchangeOrder contractExchangeOrder, final Long orderId) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        final List<ContractOrderStatus> statusList = new ArrayList<ContractOrderStatus>();
        ContractOrderType type = contractExchangeOrder.getType();
        if (orderCategory != null) {
            if (1 == orderCategory) {
                statusList.add(ContractOrderStatus.HOLD_PROCESSING);
            }
            else if (2 == orderCategory) {
                statusList.add(ContractOrderStatus.SELF_CLOSE);
                statusList.add(ContractOrderStatus.FORCE_CLOSE);
                statusList.add(ContractOrderStatus.EXPOLDE_WAREHOUSE);
                statusList.add(ContractOrderStatus.THROUGH_WAREHOUSE);
                statusList.add(ContractOrderStatus.STOP_PROFIT);
                statusList.add(ContractOrderStatus.STOP_LOSS);
            }
            else if (3 == orderCategory) {
                type = ContractOrderType.MARKET_PRICE;
            }
            else if (4 == orderCategory) {
                type = ContractOrderType.LIMIT_PRICE;
            }
        }
        if (contractExchangeOrder.getStatus() != null) {
            statusList.add(contractExchangeOrder.getStatus());
        }
        if (statusList != null && statusList.size() > 0) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.status.in((Collection)statusList));
        }
        if (orderId != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.id.eq(orderId));
        }
        if (type != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.type.eq(type));
        }
        if (!CollectionUtils.isEmpty((Collection)memberIdList)) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.member.id.in((Collection)memberIdList));
        }
        if (!StringUtils.isEmpty((CharSequence)username)) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.member.username.like("%" + username + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)email)) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.member.email.like("%" + email + "%"));
        }
        if (contractExchangeOrder.getContractType() != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.contractType.eq(contractExchangeOrder.getContractType()));
        }
        if (contractExchangeOrder.getSide() != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.side.eq(contractExchangeOrder.getSide()));
        }
        if (!StringUtils.isEmpty((CharSequence)contractExchangeOrder.getSymbol())) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.symbol.eq(contractExchangeOrder.getSymbol()));
        }
        if (orderCloseType != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.orderClose.type.eq(orderCloseType));
        }
        if (closeStartTime != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.orderClose.createTime.goe(closeStartTime));
        }
        if (closeEndTime != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.orderClose.createTime.lt(closeEndTime));
        }
        if (startTime != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.createTime.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.createTime.lt(endTime));
        }
        if (contractExchangeOrder.getIfStopLoss() != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.ifStopLoss.eq(contractExchangeOrder.getIfStopLoss()));
        }
        if (contractExchangeOrder.getIfStopProfit() != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.ifStopProfit.eq(contractExchangeOrder.getIfStopProfit()));
        }
        if (contractExchangeOrder.getOrigin() != null) {
            booleanExpressions.add(QContractExchangeOrder.contractExchangeOrder.origin.eq(contractExchangeOrder.getOrigin()));
        }
        return booleanExpressions;
    }
}
