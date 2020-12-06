package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.util.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import javax.servlet.http.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "contract/contract-commission" })
public class ContractCommissionController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractCommissionService contractCommissionService;
    @Autowired
    private MemberPromotionService memberPromotionService;

    @RequiresPermissions({ "contract:contract-commission:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约用户返佣信息列表")
    public MessageResult contractNodePageList(Long proxyId, final Long memberId, final String username, final String mobilePhone, final String email, final Integer status, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final PageModel pageModel) {
        List<Long> memberIdList = null;
        if (admin != null && admin.getProxyId() != null) {
            proxyId = admin.getProxyId();
        }
        if (proxyId != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, proxyId, true, false);
        }
        if (!CollectionUtils.isEmpty((Collection)memberIdList)) {
            if (memberId != null) {
                if (!memberIdList.contains(memberId)) {
                    return this.success((Object)new PageImpl((List)new ArrayList()));
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
        final List<BooleanExpression> booleanExpressions = this.getPredicate(memberIdList, username, mobilePhone, email, status);
        final Predicate predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractCommissionInfo> all = (Page<ContractCommissionInfo>)this.contractCommissionService.findAll(predicate, pageModel.getPageable());
        return this.success((Object)all);
    }

    @GetMapping({ "out-excel" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "合约用户返佣列表导出")
    public MessageResult contractNodePageList(Long proxyId, final Long memberId, final String username, final String mobilePhone, final String email, final Integer status, final Long nodeId, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final PageModel pageModel, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        List<ContractCommissionExcel> resultList = new ArrayList<ContractCommissionExcel>();
        List<Long> memberIdList = null;
        if (admin != null && admin.getProxyId() != null) {
            proxyId = admin.getProxyId();
        }
        if (proxyId != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, proxyId, true, false);
        }
        if (!CollectionUtils.isEmpty((Collection)memberIdList)) {
            if (memberId != null) {
                if (!memberIdList.contains(memberId)) {
                    return this.success((Object)new PageImpl((List)new ArrayList()));
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
        final List<BooleanExpression> booleanExpressionList = this.getPredicate(memberIdList, username, mobilePhone, email, status);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        resultList = (List<ContractCommissionExcel>)this.contractCommissionService.queryWhereOrPage((List)booleanExpressionList, (Integer)null, (Integer)null).getContent();
        return new FileUtil().exportExcel(request, response, (List)resultList, "contract_commission");
    }

    @PostMapping({ "grant-commission-verify" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "用户返佣审核")
    public MessageResult grantCommissionVerify(@RequestParam("id") final String id, @RequestParam("status") final Integer status, @SessionAttribute("ADMIN_MEMBER") final Admin admin) {
        if (id == null || status == null) {
            return MessageResult.error("参数错误!");
        }
        final ContractCommissionInfo contractCommissionInfo = this.contractCommissionService.findOne(id);
        if (contractCommissionInfo == null) {
            return MessageResult.error("信息不存在!");
        }
        if (contractCommissionInfo.getStatus() != 0) {
            return MessageResult.error("该佣金发放已处理!");
        }
        if (contractCommissionInfo.getMember() == null || contractCommissionInfo.getMember().getId() == null) {
            return MessageResult.error("该用户信息不存在!");
        }
        String adminId = "";
        if (admin != null) {
            adminId = admin.getId().toString();
        }
        if (status == 2) {
            this.contractCommissionService.updateStatus(id, Integer.valueOf(2), adminId);
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        contractCommissionInfo.setSysUser(adminId);
        return this.contractCommissionService.grantCommissionPass(contractCommissionInfo);
    }

    public List<BooleanExpression> getPredicate(final List<Long> memberIds, final String username, final String mobilePhone, final String email, final Integer status) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!CollectionUtils.isEmpty((Collection)memberIds)) {
            booleanExpressions.add(QContractCommissionInfo.contractCommissionInfo.member.id.in((Collection)memberIds));
        }
        if (!StringUtils.isEmpty((CharSequence)username)) {
            booleanExpressions.add(QContractCommissionInfo.contractCommissionInfo.member.username.like("%" + username + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QContractCommissionInfo.contractCommissionInfo.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)email)) {
            booleanExpressions.add(QContractCommissionInfo.contractCommissionInfo.member.email.like("%" + email + "%"));
        }
        if (status != null) {
            booleanExpressions.add(QContractCommissionInfo.contractCommissionInfo.status.eq(status));
        }
        return booleanExpressions;
    }
}
