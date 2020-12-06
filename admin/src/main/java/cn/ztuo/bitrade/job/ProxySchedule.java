package cn.ztuo.bitrade.job;

import freemarker.template.Configuration;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.vendor.provider.*;
import cn.ztuo.bitrade.dao.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.collections.*;
import java.math.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;
import org.apache.commons.lang3.*;
import java.util.concurrent.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.mail.javamail.*;
import org.springframework.ui.freemarker.*;

import javax.annotation.Resource;
import javax.mail.internet.*;
import java.util.*;
import freemarker.template.*;
import org.slf4j.*;

@Component
public class ProxySchedule
{
    private static Logger logger;
    @Resource
    private JavaMailSender javaMailSender;
    @Autowired
    private SMSProvider smsProvider;
    @Autowired
    private ContractExchangeOrderCloseService contractExchangeOrderCloseService;
    @Autowired
    private ContractNodeService contractNodeService;
    @Autowired
    private MemberPromotionService memberPromotionService;
    @Autowired
    private ContractMemberService contractMemberService;
    @Autowired
    private ContractCommissionService contractCommissionService;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private VirtualRechargeFrozenFlowRepository virtualRechargeFrozenFlowRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ContractMemberProfitLossService contractMemberProfitLossService;
    @Autowired
    private ContractExchangeOrderFeeService contractExchangeOrderFeeService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${bdtop.system.host}")
    private String host;
    @Value("${bdtop.system.name}")
    private String company;

    @Scheduled(cron = "0 5/5 * * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void calculateProfitStatistics() {
        ProxySchedule.logger.info("--- \u7edf\u8ba1\u7528\u6237\u5468\u76c8\u4e8f\u3001\u603b\u4f53\u76c8\u4e8f\uff0c\u56e2\u961f\u5468\u76c8\u4e8f\u3001\u603b\u4f53\u76c8\u4e8f ---");
        try {
            final Date zeroDate = DateUtil.getZeroDate(new Date());
            final Date thisWeekMondayDate = DateUtil.getThisWeekMonday(zeroDate);
            final String thisWeekMondayTime = DateUtil.getFormatTime(DateUtil.YYYY_MM_DD, thisWeekMondayDate);
            final Date nextWeekMondayDate = DateUtil.getNextWeekMonday(zeroDate);
            final String nextWeekMondayTime = DateUtil.getFormatTime(DateUtil.YYYY_MM_DD, nextWeekMondayDate);
            final List<Member> memberList = (List<Member>)this.memberService.findAll();
            if (CollectionUtils.isNotEmpty((Collection)memberList)) {
                for (final Member member : memberList) {
                    final Long memberId = member.getId();
                    final UserType userType = (member.getUserType() == null) ? UserType.COMMON : member.getUserType();
                    if (UserType.LEAD_ORDER.getOrdinal() == userType.getOrdinal()) {
                        continue;
                    }
                    final BigDecimal memberWeekCalculateProfit = this.contractExchangeOrderCloseService.getMemberProfitLoss(memberId, thisWeekMondayDate, nextWeekMondayDate);
                    this.contractMemberProfitLossService.updateProfitLoss(memberId, memberWeekCalculateProfit, ProfitLossType.MEMBER_WEEK, thisWeekMondayTime, nextWeekMondayTime);
                    final BigDecimal memberTotalProfitLoss = this.contractExchangeOrderCloseService.sumMemberTotalProfitLoss(memberId);
                    this.contractMemberProfitLossService.updateTotalProfitLoss(memberId, memberTotalProfitLoss, ProfitLossType.MEMBER_TOTAL);
                }
            }
            final List<ContractNode> nodeList = (List<ContractNode>)this.contractNodeService.findList(new ContractNode());
            if (CollectionUtils.isNotEmpty((Collection)nodeList)) {
                for (final ContractNode node : nodeList) {
                    final Long nodeMemberId = node.getMemberId();
                    final List<Long> teamMemberList = (List<Long>)this.memberPromotionService.findAllTeamMember((List)null, nodeMemberId, true, false);
                    if (CollectionUtils.isEmpty((Collection)teamMemberList)) {
                        continue;
                    }
                    final BigDecimal teamWeekCalculateProfit = this.contractExchangeOrderCloseService.sumMembersProfitLoss((List)teamMemberList, thisWeekMondayDate, nextWeekMondayDate);
                    this.contractMemberProfitLossService.updateProfitLoss(nodeMemberId, teamWeekCalculateProfit, ProfitLossType.TEAM_WEEK, thisWeekMondayTime, nextWeekMondayTime);
                    final List<Long> memberIds = (List<Long>)this.memberPromotionService.findAllTeamMember((List)null, nodeMemberId, true, false);
                    final BigDecimal teamTotalProfitLoss = this.contractExchangeOrderCloseService.sumMembersTotalProfitLoss((List)memberIds);
                    this.contractMemberProfitLossService.updateTotalProfitLoss(nodeMemberId, teamTotalProfitLoss, ProfitLossType.TEAM_TOTAL);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ProxySchedule.logger.error("----- calculateProfitStatistics ------ \u5b9a\u65f6\u4efb\u52a1\u5f02\u5e38", (Throwable)e);
        }
    }

    @Scheduled(cron = "0 5/5 * * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void sendWarningMessageAndAlterContractStatus() {
        try {
            final Date zeroDate = DateUtil.getZeroDate(new Date());
            final Date thisWeekMondayDate = DateUtil.getThisWeekMonday(zeroDate);
            final Date nextWeekMondayDate = DateUtil.getNextWeekMonday(zeroDate);
            final List<ContractNode> nodeList = (List<ContractNode>)this.contractNodeService.findList(new ContractNode(IfNodeType.NODE));
            if (CollectionUtils.isEmpty((Collection)nodeList)) {
                return;
            }
            for (final ContractNode node : nodeList) {
                final Long nodeMemberId = node.getMemberId();
                final BigDecimal virtualRechargeAmount = (node.getVirtualRechargeAmount() == null) ? new BigDecimal(0) : node.getVirtualRechargeAmount();
                final BigDecimal warningRate = (node.getWarningRate() == null) ? new BigDecimal(0) : node.getWarningRate();
                final BigDecimal warningAmount = virtualRechargeAmount.multiply(warningRate);
                final List<Long> teamMemberList = (List<Long>)this.memberPromotionService.findAllTeamMember((List)null, nodeMemberId, true, false);
                if (CollectionUtils.isEmpty((Collection)teamMemberList)) {
                    continue;
                }
                final BigDecimal teamWeekCalculateProfit = this.contractExchangeOrderCloseService.sumMembersProfitLoss((List)teamMemberList, thisWeekMondayDate, nextWeekMondayDate);
                if (teamWeekCalculateProfit.compareTo(virtualRechargeAmount) > -1) {
                    this.contractMemberService.updateStatuses(nodeMemberId, (List)teamMemberList, Integer.valueOf(1));
                }
                else {
                    this.contractMemberService.updateStatuses(nodeMemberId, (List)teamMemberList, Integer.valueOf(0));
                }
                if (teamWeekCalculateProfit.compareTo(warningAmount) <= -1) {
                    continue;
                }
                final Member member = this.memberService.findOne(nodeMemberId);
                if (member == null) {
                    continue;
                }
                final String mobilePhone = member.getMobilePhone();
                final String email = member.getEmail();
                if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
                    final String currentKey = "Admin_No_Security_" + mobilePhone;
                    final Object object = this.redisUtil.get(currentKey);
                    if (object != null) {
                        continue;
                    }
                    final Country country = member.getCountry();
                    if (country.getAreaCode().equals("86")) {
                        final String templateId = "178526";
                        this.smsProvider.sendTemplateMessage(mobilePhone, templateId);
                    }
                    else {
                        this.smsProvider.sendInternationalMessage("", mobilePhone, new String[] { "3330" });
                    }
                    this.redisUtil.set(currentKey, System.currentTimeMillis(), 6L, TimeUnit.HOURS);
                }
                else {
                    if (StringUtils.isEmpty((CharSequence)email)) {
                        continue;
                    }
                    final String currentKey = "Admin_No_Security_" + email;
                    final Object object = this.redisUtil.get(currentKey);
                    if (object != null) {
                        continue;
                    }
                    this.sendDepositWarningEmail(email);
                    this.redisUtil.set(currentKey, System.currentTimeMillis(), 6L, TimeUnit.HOURS);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ProxySchedule.logger.error("----- sendWarningMessageAndAlterContractStatus ------ \u5b9a\u65f6\u4efb\u52a1\u5f02\u5e38", (Throwable)e);
        }
    }

    @Scheduled(cron = "5 0 0 ? * MON")
    @Transactional(rollbackFor = { Exception.class })
    public void nodeAndProxyReturnCommission() {
        try {
            final Date date = new Date();
            final Long current = System.currentTimeMillis();
            final String time = DateUtil.getFormatTime(DateUtil.YYYY_MM_DD_MM_HH_SS, date);
            final String coinId = "USDT";
            final Date zeroDate = DateUtil.getZeroDate(date);
            final Date lastWeekMondayDate = DateUtil.getLastWeekMonday(zeroDate);
            final Date thisWeekMondayDate = DateUtil.getThisWeekMonday(zeroDate);
            final Integer[] memberStatusesArr = { IfNodeType.NODE.getOrdinal(), IfNodeType.PROXY.getOrdinal() };
            final List<ContractNode> nodeList = (List<ContractNode>)this.contractNodeService.findListByMemberStatuses((List)Arrays.asList(memberStatusesArr));
            if (CollectionUtils.isEmpty((Collection)nodeList)) {
                return;
            }
            for (final ContractNode node : nodeList) {
                final Long nodeMemberId = node.getMemberId();
                final IfNodeType memberStatus = node.getMemberStatus();
                final BigDecimal profitLossReturnRate = node.getProfitLossReturnRate();
                final BigDecimal feeReturnRate = node.getFeeReturnRate();
                final List<Long> teamMemberList = (List<Long>)this.memberPromotionService.findAllTeamMember((List)null, nodeMemberId, true, false);
                if (CollectionUtils.isEmpty((Collection)teamMemberList)) {
                    continue;
                }
                final BigDecimal teamWeekCalculateProfit = this.contractExchangeOrderCloseService.sumMembersProfitLoss((List)teamMemberList, lastWeekMondayDate, thisWeekMondayDate);
                if (teamWeekCalculateProfit.compareTo(new BigDecimal(0)) == -1) {
                    final BigDecimal commissionAmount = teamWeekCalculateProfit.negate().multiply(profitLossReturnRate);
                    if (commissionAmount.compareTo(new BigDecimal(0)) == 1) {
                        final Long proxyId = 0L;
                        final ContractCommissionInfo contractCommissionInfo = new ContractCommissionInfo(nodeMemberId, proxyId, commissionAmount, coinId, Integer.valueOf(0), time, time, current, memberStatus, Integer.valueOf(CommissionType.PROFIT_LOSS.getOrdinal()));
                        this.contractCommissionService.save(contractCommissionInfo);
                    }
                }
                else if (teamWeekCalculateProfit.compareTo(BigDecimal.ZERO) == 1 && IfNodeType.NODE.getOrdinal() == memberStatus.getOrdinal()) {
                    ContractWallet contractWallet = this.contractWalletService.findByMemberId((long)nodeMemberId);
                    if (contractWallet == null) {
                        contractWallet = this.contractWalletService.insertContractWallet(nodeMemberId, coinId);
                    }
                    final BigDecimal virtualRechargeFrozenBalance = contractWallet.getVirtualRechargeFrozenBalance();
                    if (virtualRechargeFrozenBalance.compareTo(teamWeekCalculateProfit) > -1) {
                        contractWallet.setVirtualRechargeFrozenBalance(virtualRechargeFrozenBalance.subtract(teamWeekCalculateProfit));
                        final int result = this.contractWalletService.updateContractWalletBalance(contractWallet);
                        if (result > 0) {
                            this.contractNodeService.updateVirtualRechargeAmount(nodeMemberId, contractWallet.getVirtualRechargeFrozenBalance());
                            final VirtualRechargeFrozenFlow virtualRechargeFrozenFlow = new VirtualRechargeFrozenFlow(nodeMemberId, coinId, teamWeekCalculateProfit.negate(), contractWallet.getVirtualRechargeFrozenBalance(), VirtualRechargeFrozenFlowOperationType.PLAT_LOSS);
                            this.virtualRechargeFrozenFlowRepository.save(virtualRechargeFrozenFlow);
                        }
                    }
                    else {
                        ProxySchedule.logger.error("----- \u672c\u5468 \u8282\u70b9\u7528\u6237\u51bb\u7ed3\u91d1\u989d \u5c11\u4e8e \u8282\u70b9\u7528\u6237\u76c8\u4e8f\u91d1\u989d\uff0c\u65e0\u6cd5\u51cf\u5c11\u51bb\u7ed3\u8d44\u4ea7 ------");
                    }
                }
                final BigDecimal teamWeekFee = this.contractExchangeOrderFeeService.sumMembersFee((List)teamMemberList, lastWeekMondayDate, thisWeekMondayDate);
                if (teamWeekFee.compareTo(BigDecimal.ZERO) != 1) {
                    continue;
                }
                final BigDecimal commissionAmount2 = teamWeekFee.multiply(feeReturnRate);
                if (commissionAmount2.compareTo(BigDecimal.ZERO) != 1) {
                    continue;
                }
                Long proxyId2 = 0L;
                if (IfNodeType.PROXY.getOrdinal() == memberStatus.getOrdinal()) {
                    proxyId2 = this.memberPromotionService.getSuperNodeId(nodeMemberId);
                    if (nodeMemberId.equals(proxyId2)) {
                        proxyId2 = 0L;
                    }
                }
                final ContractCommissionInfo contractCommissionInfo2 = new ContractCommissionInfo(nodeMemberId, proxyId2, commissionAmount2, coinId, Integer.valueOf(0), time, time, current, memberStatus, Integer.valueOf(CommissionType.FEE.getOrdinal()));
                this.contractCommissionService.save(contractCommissionInfo2);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ProxySchedule.logger.error("----- nodeAndProxyReturnCommission ------ \u5b9a\u65f6\u4efb\u52a1\u5f02\u5e38", (Throwable)e);
        }
    }

    @Scheduled(cron = "0 5 0 15 * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void marketReturnCommission() {
        try {
            final Date date = new Date();
            final Long current = System.currentTimeMillis();
            final String time = DateUtil.getFormatTime(DateUtil.YYYY_MM_DD_MM_HH_SS, date);
            final String coinId = "USDT";
            final Date zeroDate = DateUtil.getZeroDate(date);
            final Date lastMonthFirstDayDate = DateUtil.getLastMonthFirstDay(zeroDate);
            final Date thisMonthFirstDayDate = DateUtil.getThisMonthFirstDay(zeroDate);
            final List<ContractNode> marketList = (List<ContractNode>)this.contractNodeService.findList(new ContractNode(IfNodeType.MARKET));
            if (CollectionUtils.isEmpty((Collection)marketList)) {
                return;
            }
            for (final ContractNode market : marketList) {
                final Long marketMemberId = market.getMemberId();
                final IfNodeType memberStatus = market.getMemberStatus();
                final BigDecimal marketProfitLossReturnRate = market.getProfitLossReturnRate();
                final BigDecimal marketFeeReturnRate = market.getFeeReturnRate();
                BigDecimal profitLoss = BigDecimal.ZERO;
                BigDecimal teamWeekFee = BigDecimal.ZERO;
                final List<MemberPromotion> inviteesList = (List<MemberPromotion>)this.memberPromotionService.findByInviterId(marketMemberId);
                if (CollectionUtils.isEmpty((Collection)inviteesList)) {
                    continue;
                }
                for (final MemberPromotion member : inviteesList) {
                    List<Long> teamMemberList = new ArrayList<Long>();
                    final Long nodeMemberId = member.getId();
                    final ContractNode node = this.contractNodeService.findByMemberId(nodeMemberId);
                    final int nodeMemberStatus = (node == null || node.getMemberStatus() == null) ? 0 : node.getMemberStatus().getOrdinal();
                    if (IfNodeType.NODE.getOrdinal() == nodeMemberStatus) {
                        teamMemberList = (List<Long>)this.memberPromotionService.findAllTeamMember((List)null, nodeMemberId, true, false);
                    }
                    else {
                        teamMemberList.add(member.getId());
                    }
                    if (CollectionUtils.isEmpty((Collection)teamMemberList)) {
                        continue;
                    }
                    final BigDecimal teamMongthCalculateProfit = this.contractExchangeOrderCloseService.sumMembersProfitLoss((List)teamMemberList, lastMonthFirstDayDate, thisMonthFirstDayDate);
                    final BigDecimal teamMongthFee = this.contractExchangeOrderFeeService.sumMembersFee((List)teamMemberList, lastMonthFirstDayDate, thisMonthFirstDayDate);
                    profitLoss = profitLoss.add(teamMongthCalculateProfit);
                    teamWeekFee = teamWeekFee.add(teamMongthFee);
                }
                if (profitLoss.compareTo(BigDecimal.ZERO) == -1) {
                    final BigDecimal commissionAmount = profitLoss.negate().multiply(marketProfitLossReturnRate);
                    if (commissionAmount.compareTo(BigDecimal.ZERO) == 1) {
                        final ContractCommissionInfo contractCommissionInfo = new ContractCommissionInfo(marketMemberId, Long.valueOf(0L), commissionAmount, coinId, Integer.valueOf(0), time, time, current, memberStatus, Integer.valueOf(CommissionType.PROFIT_LOSS.getOrdinal()));
                        this.contractCommissionService.save(contractCommissionInfo);
                    }
                }
                if (teamWeekFee.compareTo(BigDecimal.ZERO) != -1) {
                    continue;
                }
                final BigDecimal commissionAmount = teamWeekFee.multiply(marketFeeReturnRate);
                if (commissionAmount.compareTo(new BigDecimal(0)) != 1) {
                    continue;
                }
                final ContractCommissionInfo contractCommissionInfo = new ContractCommissionInfo(marketMemberId, Long.valueOf(0L), commissionAmount, coinId, Integer.valueOf(0), time, time, current, memberStatus, Integer.valueOf(CommissionType.FEE.getOrdinal()));
                this.contractCommissionService.save(contractCommissionInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ProxySchedule.logger.error("----- marketReturnCommission ------ \u5b9a\u65f6\u4efb\u52a1\u5f02\u5e38", (Throwable)e);
        }
    }

    public void sendDepositWarningEmail(final String email) throws Exception {
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(this.from);
        helper.setTo(email);
        helper.setSubject(this.company);
        final Map<String, Object> model = new HashMap<String, Object>(16);
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading((Class)this.getClass(), "/templates");
        final Template template = cfg.getTemplate("depositWarning.ftl");
        final String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
        this.javaMailSender.send(mimeMessage);
        ProxySchedule.logger.info("send email for {},content:{}", email, html);
    }

    static {
        ProxySchedule.logger = LoggerFactory.getLogger((Class)ProxySchedule.class);
    }
}
