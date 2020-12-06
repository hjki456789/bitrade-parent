package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.shiro.util.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import javax.servlet.http.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "contract/contract-member" })
public class ContractMemberController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractMemberService contractMemberService;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberPromotionService memberPromotionService;

    @RequiresPermissions({ "contract:contract-member:page-query" })
    @PostMapping({ "member-promotions-page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约用户列表")
    public MessageResult memberPromotionsPageList(final PageModel pageModel, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final Member member, final Long memberId, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerEndTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginEndTime) {
        List<Long> memberIdList = null;
        if (admin != null && admin.getProxyId() != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, admin.getProxyId(), true, false);
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
        final List<BooleanExpression> booleanExpressions = this.getContractMemberPredicate(member, memberIdList, registerStartTime, registerEndTime, lastLoginStartTime, lastLoginEndTime);
        final Predicate predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("id");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<Member> all = (Page<Member>)this.contractMemberService.findAllContractMember(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @GetMapping({ "member-promotions-out-excel" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "合约用户列表导出")
    public MessageResult contractMemberExcel(final PageModel pageModel, final HttpServletRequest request, final HttpServletResponse response, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final Member member, final Long memberId, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerEndTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginEndTime) throws Exception {
        List<Long> memberIdList = null;
        if (admin != null && admin.getProxyId() != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, admin.getProxyId(), true, false);
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
        final List<BooleanExpression> booleanExpressions = this.getContractMemberPredicate(member, memberIdList, registerStartTime, registerEndTime, lastLoginStartTime, lastLoginEndTime);
        final List list = this.contractMemberService.contractMemberExcel((List)booleanExpressions, (Integer)null, (Integer)null);
        return new FileUtil().exportExcel(request, response, list, "contract_member");
    }

    @RequiresPermissions({ "contract:contract-member:market-manager-page-query" })
    @PostMapping({ "market-manager-page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找市场管理员用户列表")
    public MessageResult marketManagerPageList(final PageModel pageModel, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final Member member, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerEndTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginEndTime) {
        List<Long> memberIdList = null;
        if (admin != null && admin.getProxyId() != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, admin.getProxyId(), true, false);
        }
        member.setIfNode(IfNodeType.MARKET);
        final List<BooleanExpression> booleanExpressions = this.getContractMemberPredicate(member, memberIdList, registerStartTime, registerEndTime, lastLoginStartTime, lastLoginEndTime);
        final Predicate predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        final Page<Member> all = (Page<Member>)this.contractMemberService.findAllContractMember(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "alter-userType" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "修改带单用户状态")
    public MessageResult alterUserType(@RequestParam("memberId") final Long memberId, @RequestParam("userType") final Integer userType) {
        if (memberId == null || userType == null) {
            return MessageResult.error("参数错误!");
        }
        this.memberService.alterUserType(memberId, userType);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-member:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约用户列表")
    public MessageResult contractNodePageList(final PageModel pageModel, final String mobilePhone, final String email, final RealNameStatus realNameStatus, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date registerEndTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginStartTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date lastLoginEndTime, final ContractMember contractMember) {
        final Predicate predicate = this.getPredicate(contractMember, mobilePhone, email, realNameStatus, registerStartTime, registerEndTime, lastLoginStartTime, lastLoginEndTime);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractMember> all = (Page<ContractMember>)this.contractMemberService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "contract:contract-member:detail-member" })
    @PostMapping({ "detail-member" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "合约用户详情")
    public MessageResult contractNodeDetail(@RequestParam("memberId") final String memberId) {
        final ContractMember contractMember = this.contractMemberService.findByMemberId(memberId);
        if (contractMember == null) {
            return MessageResult.error("该合约用户不存在!");
        }
        return this.success(contractMember);
    }

    @RequiresPermissions({ "contract:contract-member:update-rebateRate" })
    @PostMapping({ "update-rebateRate" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "编辑返佣比例")
    public MessageResult updateRebateRate(final ContractMember contractMember) {
        final Long memberId = contractMember.getMemberId();
        final BigDecimal contractRebateRate = contractMember.getContractRebateRate();
        final BigDecimal spotRebateRate = contractMember.getSpotRebateRate();
        if (memberId == null) {
            return MessageResult.error("参数错误!");
        }
        final ContractMember contractMemberOld = this.contractMemberService.findByMemberId(memberId.toString());
        if (contractMemberOld == null) {
            return MessageResult.error("该合约用户不存在!");
        }
        if (contractRebateRate == null) {
            contractMember.setContractRebateRate(contractMemberOld.getContractRebateRate());
        }
        if (spotRebateRate == null) {
            contractMember.setSpotRebateRate(contractMemberOld.getSpotRebateRate());
        }
        contractMember.setVersion(contractMemberOld.getVersion());
        final int result = this.contractMemberService.updateRebateRate(contractMember);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @RequiresPermissions({ "contract:contract-member:alter-ifProxy" })
    @PostMapping({ "alter-ifProxy" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "启用禁用代理")
    public MessageResult alterIfProxy(@RequestParam("memberIds") final String[] memberIds, @RequestParam("ifProxy") final Integer ifProxy) {
        if (memberIds == null || memberIds.length == 0 || ifProxy == null) {
            return MessageResult.error("参数错误!");
        }
        this.contractMemberService.updateIfProxy(memberIds, ifProxy);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-member:update-balance" })
    @PostMapping({ "update-balance" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "管理员用户更改合约账户余额")
    public MessageResult updateBalance(@SessionAttribute("ADMIN_MEMBER") final Admin admin, @RequestParam("memberId") final Long memberId, @RequestParam("coinId") final String coinId, @RequestParam("changeBalance") final BigDecimal changeBalance, @RequestParam("remark") final String remark) {
        final String adminId = (admin != null) ? admin.getId().toString() : "";
        final ContractWallet contractWallet = this.contractWalletService.findByMemberIdAndCoin((long)memberId, coinId);
        if (contractWallet == null) {
            return MessageResult.error("暂无该币种的资产信息!");
        }
        if (changeBalance == null || changeBalance.equals(new BigDecimal(0))) {
            return MessageResult.error("调整余额不可为0!");
        }
        final int result = this.contractWalletService.changeBalance(contractWallet, changeBalance, ContractWalletOperationType.OPERATE_BALANCE, remark, adminId);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    public Predicate getMemberPromotionsPredicate(final Member member, final Date registerStartTime, final Date registerEndTime, final Date lastLoginStartTime, final Date lastLoginEndTime) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (member.getProxyId() != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.inviterId.eq(member.getProxyId()));
        }
        if (member.getId() != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.id.eq(member.getId()));
        }
        if (member.getIfNode() != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.ifNode.eq(member.getIfNode()));
        }
        if (!StringUtils.isEmpty((CharSequence)member.getMobilePhone())) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.mobilePhone.like("%" + member.getMobilePhone() + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)member.getEmail())) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.email.like("%" + member.getEmail() + "%"));
        }
        if (registerStartTime != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.registrationTime.goe(registerStartTime));
        }
        if (registerEndTime != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.registrationTime.lt(registerEndTime));
        }
        if (lastLoginStartTime != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.lastLoginTime.goe(lastLoginStartTime));
        }
        if (lastLoginEndTime != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.lastLoginTime.lt(lastLoginEndTime));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }

    public List<BooleanExpression> getContractMemberPredicate(final Member member, final List<Long> memberIds, final Date registerStartTime, final Date registerEndTime, final Date lastLoginStartTime, final Date lastLoginEndTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (!CollectionUtils.isEmpty((Collection)memberIds)) {
            booleanExpressions.add(QMember.member.id.in((Collection)memberIds));
        }
        if (member.getIfNode() != null) {
            booleanExpressions.add(QMember.member.ifNode.eq(member.getIfNode()));
        }
        if (!StringUtils.isEmpty((CharSequence)member.getMobilePhone())) {
            booleanExpressions.add(QMember.member.mobilePhone.like("%" + member.getMobilePhone() + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)member.getEmail())) {
            booleanExpressions.add(QMember.member.email.like("%" + member.getEmail() + "%"));
        }
        if (registerStartTime != null) {
            booleanExpressions.add(QMember.member.registrationTime.goe(registerStartTime));
        }
        if (registerEndTime != null) {
            booleanExpressions.add(QMember.member.registrationTime.lt(registerEndTime));
        }
        if (lastLoginStartTime != null) {
            booleanExpressions.add(QMember.member.lastLoginTime.goe(lastLoginStartTime));
        }
        if (lastLoginEndTime != null) {
            booleanExpressions.add(QMember.member.lastLoginTime.lt(lastLoginEndTime));
        }
        return booleanExpressions;
    }

    public Predicate getPredicate(final ContractMember contractMember, final String mobilePhone, final String email, final RealNameStatus realNameStatus, final Date registerStartTime, final Date registerEndTime, final Date lastLoginStartTime, final Date lastLoginEndTime) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (contractMember.getProxyId() != null) {
            booleanExpressions.add(QContractMember.contractMember.proxyId.eq(contractMember.getProxyId()));
        }
        if (contractMember.getMemberId() != null) {
            booleanExpressions.add(QContractMember.contractMember.memberId.eq(contractMember.getMemberId()));
        }
        if (contractMember.getIfProxy() != null) {
            booleanExpressions.add(QContractMember.contractMember.ifProxy.eq(contractMember.getIfProxy()));
        }
        if (!StringUtils.isEmpty((CharSequence)contractMember.getInvitationCode())) {
            booleanExpressions.add(QContractMember.contractMember.invitationCode.eq(contractMember.getInvitationCode()));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QContractMember.contractMember.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)email)) {
            booleanExpressions.add(QContractMember.contractMember.member.email.like("%" + email + "%"));
        }
        if (realNameStatus != null) {
            booleanExpressions.add(QContractMember.contractMember.member.realNameStatus.eq(realNameStatus));
        }
        if (registerStartTime != null) {
            booleanExpressions.add(QContractMember.contractMember.member.registrationTime.goe(registerStartTime));
        }
        if (registerEndTime != null) {
            booleanExpressions.add(QContractMember.contractMember.member.registrationTime.lt(registerEndTime));
        }
        if (lastLoginStartTime != null) {
            booleanExpressions.add(QContractMember.contractMember.member.lastLoginTime.goe(lastLoginStartTime));
        }
        if (lastLoginEndTime != null) {
            booleanExpressions.add(QContractMember.contractMember.member.lastLoginTime.lt(lastLoginEndTime));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
