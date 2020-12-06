package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "contract/contract-node" })
public class ContractNodeController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractNodeService contractNodeService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberPromotionService memberPromotionService;

    @RequiresPermissions({ "contract:contract-node:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找节点列表")
    public MessageResult contractNodePageList(final Long memberId, final String userName, final String mobilePhone, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final PageModel pageModel) {
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
        final Predicate predicate = this.getContractNodePredicate(IfNodeType.NODE, memberIdList, userName, mobilePhone);
        final Page<ContractNode> all = (Page<ContractNode>)this.contractNodeService.findAllContractNode(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "list" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "获取节点列表")
    public MessageResult enableContractNodeList(final ContractNode contractNode) {
        contractNode.setEnable(Integer.valueOf(1));
        final List<ContractNode> list = (List<ContractNode>)this.contractNodeService.findList(contractNode);
        return this.success(list);
    }

    @RequiresPermissions({ "contract:contract-node:add-node" })
    @PostMapping({ "add-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "新增节点信息")
    public MessageResult addContractNode(ContractNode contractNode) {
        final Long memberId = contractNode.getMemberId();
        final String nodeName = contractNode.getNodeName();
        final BigDecimal feeReturnRate = contractNode.getFeeReturnRate();
        final BigDecimal profitLossReturnRate = contractNode.getProfitLossReturnRate();
        final BigDecimal holdFeeReturnRate = contractNode.getHoldFeeReturnRate();
        final BigDecimal virtualRechargeAmount = contractNode.getVirtualRechargeAmount();
        final BigDecimal depositAmount = contractNode.getDepositAmount();
        if (memberId == null) {
            return MessageResult.error("用户id不能为空!");
        }
        if (feeReturnRate == null || feeReturnRate.compareTo(BigDecimal.ZERO) == -1 || feeReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("手续费返佣比例必须大于等于0小于等于1!");
        }
        if (profitLossReturnRate == null || profitLossReturnRate.compareTo(BigDecimal.ZERO) == -1 || profitLossReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("盈亏返佣比例必须大于等于0小于等于1!");
        }
        if (holdFeeReturnRate == null || holdFeeReturnRate.compareTo(BigDecimal.ZERO) == -1 || holdFeeReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("持仓费返佣比例必须大于等于0小于等于1!");
        }
        if (virtualRechargeAmount.compareTo(new BigDecimal(0)) < 1) {
            return MessageResult.error("虚充金额必须大于0!");
        }
        if (depositAmount.compareTo(new BigDecimal(0)) == -1) {
            return MessageResult.error("保证金金额必须大于等于0!");
        }
        final Member member = this.memberService.findOne(memberId);
        if (member == null) {
            return MessageResult.error("该用户不存在!");
        }
        final MemberPromotion memberPromotion = this.memberPromotionService.findByInviteesId(memberId);
        if (memberPromotion == null) {
            return MessageResult.error("该用户上级用户非市场管理员，不可成为节点!");
        }
        final Member promotionMember = this.memberService.findOne(memberPromotion.getInviterId());
        if (promotionMember == null || !IfNodeType.MARKET.equals(promotionMember.getIfNode())) {
            return MessageResult.error("该用户上级用户非市场管理员，不可成为节点!");
        }
        final ContractNode info = this.contractNodeService.findByMemberId(memberId);
        if (info != null) {
            return MessageResult.error("该用户已是" + info.getMemberStatus() + "，不可设置成为节点!");
        }
        contractNode = new ContractNode(memberId, nodeName, feeReturnRate, profitLossReturnRate, holdFeeReturnRate, virtualRechargeAmount, depositAmount, new BigDecimal(0.5), new BigDecimal(0), new BigDecimal(0), IfNodeType.NODE, Integer.valueOf(1));
        return this.contractNodeService.addContractNode(contractNode);
    }

    @RequiresPermissions({ "contract:contract-node:update-node" })
    @PostMapping({ "update-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "编辑contract-node信息")
    public MessageResult updateContractNode(final ContractNode contractNode) {
        final String id = contractNode.getId();
        final String nodeName = contractNode.getNodeName();
        final BigDecimal feeReturnRate = contractNode.getFeeReturnRate();
        final BigDecimal profitLossReturnRate = contractNode.getProfitLossReturnRate();
        final BigDecimal holdFeeReturnRate = contractNode.getHoldFeeReturnRate();
        if (id == null) {
            return MessageResult.error("ID不能为空!");
        }
        final ContractNode nodeOld = this.contractNodeService.findOne(id);
        if (nodeOld == null) {
            return MessageResult.error("信息不存在!");
        }
        if (!StringUtils.isEmpty((CharSequence)nodeName)) {
            nodeOld.setNodeName(nodeName);
        }
        if (feeReturnRate != null) {
            nodeOld.setFeeReturnRate(feeReturnRate);
        }
        if (profitLossReturnRate != null) {
            nodeOld.setProfitLossReturnRate(profitLossReturnRate);
        }
        if (holdFeeReturnRate != null) {
            nodeOld.setHoldFeeReturnRate(holdFeeReturnRate);
        }
        if (feeReturnRate == null || feeReturnRate.compareTo(BigDecimal.ZERO) == -1 || feeReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("手续费返佣比例必须大于等于0小于等于1!");
        }
        if (profitLossReturnRate == null || profitLossReturnRate.compareTo(BigDecimal.ZERO) == -1 || profitLossReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("盈亏返佣比例必须大于等于0小于等于1!");
        }
        if (holdFeeReturnRate == null || holdFeeReturnRate.compareTo(BigDecimal.ZERO) == -1 || holdFeeReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("持仓费返佣比例必须大于等于0小于等于1!");
        }
        this.contractNodeService.save(nodeOld);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-node:detail-node" })
    @PostMapping({ "detail-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "节点信息详情")
    public MessageResult contractNodeDetail(@RequestParam("id") final String id) {
        final ContractNode contractNode = this.contractNodeService.findOne(id);
        if (contractNode == null) {
            return MessageResult.error("节点不存在!");
        }
        return this.success(contractNode);
    }

    @RequiresPermissions({ "contract:contract-node:alter-enable" })
    @PostMapping({ "alter-enable" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "修改禁用状态")
    public MessageResult alterEnable(@RequestParam("id") final String id, @RequestParam("enable") final Integer enable) {
        Assert.notNull(enable, "validate enable!");
        final int result = this.contractNodeService.updateEnable(id, enable);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @RequiresPermissions({ "contract:contract-coin:delete-node" })
    @PostMapping({ "delete-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "删除节点信息")
    public MessageResult deleteContractCoin(@RequestParam("ids") final String[] ids) {
        this.contractNodeService.delete(ids);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @PostMapping({ "add-depositAmount" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "增加保证金")
    public MessageResult addDepositAmount(final String id, final BigDecimal depositAmount, final BigDecimal virtualRechargeAmount) {
        if (StringUtils.isEmpty((CharSequence)id) || depositAmount == null || virtualRechargeAmount == null) {
            return MessageResult.error("参数错误!");
        }
        if (virtualRechargeAmount.compareTo(new BigDecimal(0)) < 1) {
            return MessageResult.error("虚充金额必须大于0!");
        }
        if (depositAmount.compareTo(new BigDecimal(0)) == -1) {
            return MessageResult.error("保证金金额必须大于等于0!");
        }
        final ContractNode contractNode = this.contractNodeService.findOne(id);
        if (contractNode == null) {
            return MessageResult.error("信息不存在!");
        }
        return this.contractNodeService.addDepositAmount(contractNode, depositAmount, virtualRechargeAmount);
    }

    @PostMapping({ "cancel-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "取消节点")
    public MessageResult cancelNode(@RequestParam("memberId") final Long memberId) {
        if (memberId == null) {
            return MessageResult.error("用户id不能为空!");
        }
        final Member member = this.memberService.findOne(memberId);
        if (member == null) {
            return MessageResult.error("该用户不存在!");
        }
        final ContractNode contractNode = this.contractNodeService.findByMemberId(memberId);
        if (contractNode == null || !IfNodeType.NODE.equals(contractNode.getMemberStatus())) {
            return MessageResult.error("该用户不是节点，不可取消!");
        }
        this.contractNodeService.cancel(memberId);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @PostMapping({ "add-market-manager" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "增加市场管理员")
    public MessageResult addMarketManager(@RequestParam("memberId") final Long memberId, @RequestParam("nodeName") final String nodeName) {
        if (memberId == null) {
            return MessageResult.error("用户id不能为空!");
        }
        final BigDecimal profitLossReturnRate = new BigDecimal(0.16);
        final Member member = this.memberService.findOne(memberId);
        if (member == null) {
            return MessageResult.error("该用户不存在!");
        }
        final MemberPromotion memberPromotion = this.memberPromotionService.findByInviteesId(memberId);
        if (memberPromotion == null || 57L != memberPromotion.getInviterId()) {
            return MessageResult.error("该用户不符合条件，不可成为市场管理员!");
        }
        final ContractNode contractNode = this.contractNodeService.findByMemberId(memberId);
        if (contractNode != null) {
            return MessageResult.error("该用户已是" + contractNode.getMemberStatus() + "，不可设置成为市场管理员!");
        }
        this.contractNodeService.addMarketManager(memberId, nodeName, profitLossReturnRate);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @PostMapping({ "cancel-market-manager" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "取消市场管理员")
    public MessageResult cancelMarketManager(@RequestParam("memberId") final Long memberId) {
        if (memberId == null) {
            return MessageResult.error("用户id不能为空!");
        }
        final Member member = this.memberService.findOne(memberId);
        if (member == null) {
            return MessageResult.error("该用户不存在!");
        }
        final ContractNode contractNode = this.contractNodeService.findByMemberId(memberId);
        if (contractNode == null || !IfNodeType.MARKET.equals(contractNode.getMemberStatus())) {
            return MessageResult.error("该用户不是市场管理员，不可取消!");
        }
        this.contractNodeService.cancel(memberId);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @PostMapping({ "add-proxy" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "设置代理")
    public MessageResult addProxy(@RequestParam("memberId") final Long memberId, @RequestParam("nodeName") final String nodeName, @RequestParam("feeReturnRate") final BigDecimal feeReturnRate, @RequestParam("profitLossReturnRate") final BigDecimal profitLossReturnRate, @RequestParam("holdFeeReturnRate") final BigDecimal holdFeeReturnRate) {
        if (memberId == null) {
            return MessageResult.error("用户id不能为空!");
        }
        if (feeReturnRate == null || feeReturnRate.compareTo(BigDecimal.ZERO) == -1 || feeReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("手续费返佣比例必须大于等于0小于等于1!");
        }
        if (profitLossReturnRate == null || profitLossReturnRate.compareTo(BigDecimal.ZERO) == -1 || profitLossReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("盈亏返佣比例必须大于等于0小于等于1!");
        }
        if (holdFeeReturnRate == null || holdFeeReturnRate.compareTo(BigDecimal.ZERO) == -1 || holdFeeReturnRate.compareTo(BigDecimal.ONE) == 1) {
            return MessageResult.error("持仓费返佣比例必须大于等于0小于等于1!");
        }
        final Member member = this.memberService.findOne(memberId);
        if (member == null) {
            return MessageResult.error("该用户不存在!");
        }
        final MemberPromotion memberPromotion = this.memberPromotionService.findByInviteesId(memberId);
        if (memberPromotion == null) {
            return MessageResult.error("该用户上级用户非节点或代理，不可设置成为代理!");
        }
        final Member promotionMember = this.memberService.findOne(memberPromotion.getInviterId());
        if (promotionMember == null) {
            return MessageResult.error("该用户上级用户非节点或代理，不可设置成为代理!");
        }
        if (IfNodeType.NODE.getOrdinal() != promotionMember.getIfNode().getOrdinal() && IfNodeType.PROXY.getOrdinal() != promotionMember.getIfNode().getOrdinal()) {
            return MessageResult.error("该用户上级用户非节点或代理，不可设置成为代理!");
        }
        final ContractNode contractNode = this.contractNodeService.findByMemberId(memberId);
        if (contractNode != null) {
            return MessageResult.error("该用户已是" + contractNode.getMemberStatus() + "，不可设置成为代理!");
        }
        this.contractNodeService.addProxy(memberId, nodeName, feeReturnRate, profitLossReturnRate, holdFeeReturnRate);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @PostMapping({ "cancel-proxy" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "取消代理")
    public MessageResult cancelProxy(@RequestParam("memberId") final Long memberId) {
        if (memberId == null) {
            return MessageResult.error("用户id不能为空!");
        }
        final Member member = this.memberService.findOne(memberId);
        if (member == null) {
            return MessageResult.error("该用户不存在!");
        }
        final ContractNode contractNode = this.contractNodeService.findByMemberId(memberId);
        if (contractNode == null || !IfNodeType.PROXY.equals(contractNode.getMemberStatus())) {
            return MessageResult.error("该用户不是代理，不可取消!");
        }
        this.contractNodeService.cancel(memberId);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    public Predicate getContractNodePredicate(final IfNodeType ifNode, final List<Long> memberIds, final String userName, final String mobilePhone) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (ifNode != null) {
            booleanExpressions.add(QMember.member.ifNode.eq(ifNode));
        }
        if (!CollectionUtils.isEmpty((Collection)memberIds)) {
            booleanExpressions.add(QMember.member.id.in((Collection)memberIds));
        }
        if (!StringUtils.isEmpty((CharSequence)userName)) {
            booleanExpressions.add(QMember.member.username.like("%" + userName + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QMember.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }

    public Predicate getPromotionContractNodePredicate(final Long proxyId, final IfNodeType ifNode, final Long memberId, final String userName, final String mobilePhone) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (proxyId != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.inviterId.eq(proxyId));
        }
        if (ifNode != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.ifNode.eq(ifNode));
        }
        if (memberId != null) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.inviteesId.eq(memberId));
        }
        if (!StringUtils.isEmpty((CharSequence)userName)) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.username.like("%" + userName + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QMemberPromotion.memberPromotion.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
