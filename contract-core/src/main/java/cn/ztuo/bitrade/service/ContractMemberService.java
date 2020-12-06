package cn.ztuo.bitrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.data.domain.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.*;
import com.querydsl.core.types.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.util.*;

import java.math.*;
import java.util.*;

import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.constant.*;

@Service
public class ContractMemberService {
    @Autowired
    private ContractMemberRepository contractMemberRepository;
    @Autowired
    private ContractMemberStatusRecordRepository contractMemberStatusRecordRepository;
    @Autowired
    private MemberPromotionService memberPromotionService;
    @Autowired
    private ContractNodeRepository contractNodeRepository;
    @Autowired
    private ContractMemberTransferRecordService contractMemberTransferRecordService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDepositService memberDepositService;
    @Autowired
    private ContractMemberProfitLossRepository contractMemberProfitLossRepository;
    @Autowired
    private ContractExchangeOrderCloseService contractExchangeOrderCloseService;
    @Autowired
    private ContractExchangeOrderFeeService contractExchangeOrderFeeService;
    @Autowired
    protected JPAQueryFactory queryFactory;

    public Page<Member> findAllMemberPromotions(final Predicate predicate, PageModel pageable) {
        final List<Member> memberList = new ArrayList<Member>();
        final Page<MemberPromotion> memberPromotionsPage = (Page<MemberPromotion>) this.memberPromotionService.findAll(predicate, pageable);
        if (memberPromotionsPage != null) {
            for (final MemberPromotion memberPromotion : memberPromotionsPage.getContent()) {
                Member member = memberPromotion.getMember();
                if (member != null) {
                    member.setProxyId(memberPromotion.getInviterId());
                    member = this.getMemberInfo(member);
                    memberList.add(member);
                }
            }
        }
        return (Page<Member>) new PageImpl((List) memberList);
    }

    public Page<Member> findAllContractMember(final Predicate predicate, final Pageable pageable) {
        final Page<Member> memberPage = (Page<Member>) this.memberService.findAll(predicate, pageable);
        if (memberPage == null) {
            return null;
        }
        for (Member member : memberPage.getContent()) {
            member = this.getMemberInfo(member);
        }
        return memberPage;
    }

    public List<ContractMemberExcel> contractMemberExcel(final List<BooleanExpression> booleanExpressionList, final Integer pageNo, final Integer pageSize) {
        final List<ContractMemberExcel> resultList = new ArrayList<ContractMemberExcel>();
        try {
            final JPAQuery<Member> jpaQuery = (JPAQuery<Member>) this.queryFactory.selectFrom((EntityPath) QMember.member);
            final OrderSpecifier<Long> orderSpecifier = (OrderSpecifier<Long>) QMember.member.id.desc();
            if (booleanExpressionList != null) {
                jpaQuery.where((Predicate[]) booleanExpressionList.toArray((Predicate[]) new BooleanExpression[booleanExpressionList.size()]));
            }
            List<Member> list;
            if (pageNo != null && pageSize != null) {
                list = (List<Member>) ((JPAQuery) ((JPAQuery) ((JPAQuery) jpaQuery.orderBy((OrderSpecifier) orderSpecifier)).offset((long) ((pageNo - 1) * pageSize))).limit((long) pageSize)).fetch();
            } else {
                list = (List<Member>) ((JPAQuery) jpaQuery.orderBy((OrderSpecifier) orderSpecifier)).fetch();
            }
            if (!CollectionUtils.isEmpty((Collection) list)) {
                for (final Member m : list) {
                    this.getMemberInfo(m);
                    final UserType userType = (m.getUserType() == null) ? UserType.COMMON : m.getUserType();
                    final IfNodeType ifNode = (m.getIfNode() == null) ? IfNodeType.COMMON : m.getIfNode();
                    final ContractMemberExcel excelDto = new ContractMemberExcel();
                    excelDto.setId(m.getId());
                    excelDto.setUsername(m.getUsername());
                    excelDto.setMobilePhone(m.getMobilePhone());
                    excelDto.setEmail(m.getEmail());
                    excelDto.setUserTypeName(userType.getCnName());
                    excelDto.setIfNodeName(ifNode.getCnName());
                    excelDto.setProxyId(m.getProxyId());
                    excelDto.setRegistrationTime(DateUtil.getFormatTime(DateUtil.YYYY_MM_DD_MM_HH_SS, m.getRegistrationTime()));
                    excelDto.setMemberYesterdayProfitLossAmount(m.getMemberYesterdayProfitLossAmount());
                    excelDto.setMemberTotalProfitLossAmount(m.getMemberTotalProfitLossAmount());
                    excelDto.setTeamYesterdayProfitLossAmount(m.getTeamYesterdayProfitLossAmount());
                    excelDto.setTeamTotalProfitLossAmount(m.getTeamTotalProfitLossAmount());
                    excelDto.setYesterdayFee(m.getYesterdayFee());
                    excelDto.setTotalFee(m.getTotalFee());
                    excelDto.setTeamYesterdayFee(m.getTeamYesterdayFee());
                    excelDto.setTeamTotalFee(m.getTeamTotalFee());
                    resultList.add(excelDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private Member getMemberInfo(final Member member) {
        final IfNodeType ifNode = (member.getIfNode() == null) ? IfNodeType.COMMON : member.getIfNode();
        if (IfNodeType.COMMON.getOrdinal() != ifNode.getOrdinal()) {
            final ContractNode contractNode = this.contractNodeRepository.findByMemberId(member.getId());
            member.setContractNode(contractNode);
        }
        if (member.getProxyId() != null) {
            final ContractNode proxyContractNode = this.contractNodeRepository.findByMemberId(member.getProxyId());
            if (proxyContractNode != null) {
                final IfNodeType memberStatus = proxyContractNode.getMemberStatus();
                if (IfNodeType.NODE.getOrdinal() == memberStatus.getOrdinal() || IfNodeType.PROXY.getOrdinal() == memberStatus.getOrdinal()) {
                    if (IfNodeType.COMMON.getOrdinal() == ifNode.getOrdinal()) {
                        member.setOperate(OperateType.ADD_PROXY);
                    } else if (IfNodeType.PROXY.getOrdinal() == ifNode.getOrdinal()) {
                        member.setOperate(OperateType.CANCEL_PROXY);
                    }
                } else if (IfNodeType.MARKET.getOrdinal() == memberStatus.getOrdinal()) {
                    if (IfNodeType.COMMON.getOrdinal() == ifNode.getOrdinal()) {
                        member.setOperate(OperateType.ADD_NODE);
                    } else if (IfNodeType.NODE.getOrdinal() == ifNode.getOrdinal()) {
                        member.setOperate(OperateType.CANCEL_NODE);
                    }
                }
            }
        }
        final MemberPromotion memberPromotion = this.memberPromotionService.findByInviteesId(member.getId());
        if (memberPromotion != null) {
            member.setProxyId(memberPromotion.getInviterId());
        }
        String thisWeekMondayTime = null;
        try {
            final Date zeroDate = DateUtil.getZeroDate(new Date());
            final Date thisWeekMondayDate = DateUtil.getThisWeekMonday(zeroDate);
            thisWeekMondayTime = DateUtil.getFormatTime(DateUtil.YYYY_MM_DD, thisWeekMondayDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ContractMemberProfitLoss> profitLossList = this.contractMemberProfitLossRepository.getWeekProfitLossAmount(member.getId(), thisWeekMondayTime);
        profitLossList = (CollectionUtils.isEmpty((Collection) profitLossList) ? new ArrayList<ContractMemberProfitLoss>() : profitLossList);
        final List<ContractMemberProfitLoss> teamProfitLossList = this.contractMemberProfitLossRepository.getTotalProfitLossAmount(member.getId());
        if (!CollectionUtils.isEmpty((Collection) teamProfitLossList)) {
            profitLossList.addAll(teamProfitLossList);
        }
        if (!CollectionUtils.isEmpty((Collection) profitLossList)) {
            for (final ContractMemberProfitLoss profitLoss : profitLossList) {
                final ProfitLossType type = profitLoss.getType();
                final BigDecimal profitLossAmount = profitLoss.getProfitLossAmount();
                if (ProfitLossType.MEMBER_WEEK.getOrdinal() == type.getOrdinal()) {
                    member.setMemberWeekProfitLossAmount(profitLossAmount);
                } else if (ProfitLossType.TEAM_WEEK.getOrdinal() == type.getOrdinal()) {
                    member.setTeamWeekProfitLossAmount(profitLossAmount);
                } else if (ProfitLossType.MEMBER_TOTAL.getOrdinal() == type.getOrdinal()) {
                    member.setMemberTotalProfitLossAmount(profitLossAmount);
                } else {
                    if (ProfitLossType.TEAM_TOTAL.getOrdinal() != type.getOrdinal()) {
                        continue;
                    }
                    member.setTeamTotalProfitLossAmount(profitLossAmount);
                }
            }
        }
        final UserType userType = (member.getUserType() == null) ? UserType.COMMON : member.getUserType();
        final Date yesterdayZeroDate = DateUtil.getYesterdayZeroDate(new Date());
        final Date todayZeroDate = DateUtil.getZeroDate(new Date());
        if (UserType.LEAD_ORDER.getOrdinal() != userType.getOrdinal()) {
            final List<Long> memberIds = new ArrayList<Long>();
            memberIds.add(member.getId());
            final BigDecimal memberYesterdayCalculateProfit = this.contractExchangeOrderCloseService.sumMembersProfitLoss(memberIds, yesterdayZeroDate, todayZeroDate);
            final BigDecimal memberTotalProfitLoss = this.contractExchangeOrderCloseService.sumMembersTotalProfitLoss(memberIds);
            final BigDecimal yesterdayFee = this.contractExchangeOrderFeeService.sumMembersFee(memberIds, yesterdayZeroDate, todayZeroDate);
            final BigDecimal totalFee = this.contractExchangeOrderFeeService.sumMembersTotalFee(memberIds);
            member.setMemberYesterdayProfitLossAmount(memberYesterdayCalculateProfit);
            member.setMemberTotalProfitLossAmount(memberTotalProfitLoss);
            member.setYesterdayFee(yesterdayFee);
            member.setTotalFee(totalFee);
        }
        final List<Long> teamMemberList = (List<Long>) this.memberPromotionService.findAllTeamMember((List) null, member.getId(), true, false);
        final BigDecimal teamYesterdayProfitLossAmount = this.contractExchangeOrderCloseService.sumMembersProfitLoss(teamMemberList, yesterdayZeroDate, todayZeroDate);
        final BigDecimal teamTotalProfitLossAmount = this.contractExchangeOrderCloseService.sumMembersTotalProfitLoss(teamMemberList);
        final BigDecimal teamYesterdayFee = this.contractExchangeOrderFeeService.sumMembersFee(teamMemberList, yesterdayZeroDate, todayZeroDate);
        final BigDecimal teamTotalFee = this.contractExchangeOrderFeeService.sumMembersTotalFee(teamMemberList);
        member.setTeamYesterdayProfitLossAmount(teamYesterdayProfitLossAmount);
        member.setTeamTotalProfitLossAmount(teamTotalProfitLossAmount);
        member.setTeamYesterdayFee(teamYesterdayFee);
        member.setTeamTotalFee(teamTotalFee);
        final Map<String, BigDecimal> chargeAmountMap = (Map<String, BigDecimal>) this.memberDepositService.sumMemberDeposit(member.getId());
        if (chargeAmountMap != null) {
            member.setEthChargeAmount((chargeAmountMap.get("ETH") == null) ? BigDecimal.ZERO : chargeAmountMap.get("ETH"));
            member.setBtcChargeAmount((chargeAmountMap.get("BTC") == null) ? BigDecimal.ZERO : chargeAmountMap.get("BTC"));
            member.setUsdtChargeAmount((chargeAmountMap.get("USDT") == null) ? BigDecimal.ZERO : chargeAmountMap.get("USDT"));
        }
        return member;
    }

    public Page<ContractMember> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<ContractMember>) this.contractMemberRepository.findAll(predicate, pageable);
    }

    public ContractMember findByMemberId(final String memberId) {
        return this.contractMemberRepository.findByMemberId(memberId);
    }

    public int updateRebateRate(final ContractMember contractMember) {
        return this.contractMemberRepository.updateRebateRate(contractMember.getMemberId().toString(), contractMember.getContractRebateRate(), contractMember.getSpotRebateRate(), contractMember.getVersion());
    }

    public void updateIfProxy(final String[] memberIds, final Integer ifProxy) {
        for (final String memberId : memberIds) {
            final ContractMember contractMember = this.contractMemberRepository.findByMemberId(memberId);
            if (contractMember != null) {
                this.contractMemberRepository.updateIfProxy(memberId, ifProxy, contractMember.getVersion());
            }
        }
    }

    public int updateProxyId(final Long memberId, final Long proxyId, final Long version) {
        return this.contractMemberRepository.updateProxyId(memberId, proxyId, version);
    }

    @Transactional
    public int addMemberTransfer(final Long memberId, final Long newProxyMemberId, final Long oldProxyMemberId, final Long contractMemberVersion) {
        final ContractMemberTransferRecord contractMemberTransferRecord = new ContractMemberTransferRecord();
        final Member member = new Member(memberId);
        final Member orginalProxyMember = new Member(oldProxyMemberId);
        final Member newProxyMember = new Member(newProxyMemberId);
        contractMemberTransferRecord.setMember(member);
        contractMemberTransferRecord.setOrginalProxyMember(orginalProxyMember);
        contractMemberTransferRecord.setNewProxyMember(newProxyMember);
        this.contractMemberTransferRecordService.save(contractMemberTransferRecord);
        final int result = this.updateProxyId(memberId, newProxyMemberId, contractMemberVersion);
        return result;
    }

    public ContractMember insert(final ContractMember contractMember) {
        return (ContractMember) this.contractMemberRepository.saveAndFlush(contractMember);
    }

    public void updateStatuses(final Long nodeMemberId, final List<Long> memberIds, final Integer status) {
        for (final Long memberId : memberIds) {
            ContractMember contractMember = this.contractMemberRepository.findByMemberId(memberId.toString());
            if (contractMember != null) {
                if (status.equals(contractMember.getStatus())) {
                    continue;
                }
                this.contractMemberRepository.updateStatus(memberId, status, contractMember.getVersion());
                final ContractMemberStatusRecord record = new ContractMemberStatusRecord();
                record.setMemberId(memberId);
                record.setNodeMemberId(nodeMemberId);
                record.setMemberIds(memberIds.toString());
                record.setStatus(status);
                record.setSequence(System.currentTimeMillis());
                record.setTime(new Date());
                this.contractMemberStatusRecordRepository.saveAndFlush(record);
            } else {
                contractMember = new ContractMember();
                final MemberPromotion memberPromotion = this.memberPromotionService.findByInviteesId(memberId);
                if (null != memberPromotion) {
                    final Long inviterId = memberPromotion.getInviterId();
                    final Member inviterMember = this.memberService.findOne(inviterId);
                    if (null != inviterMember) {
                        final String promotionCode = inviterMember.getPromotionCode();
                        contractMember.setInvitationCode(promotionCode);
                    }
                }
                contractMember.setMemberId(memberId);
                contractMember.setIfProxy(BooleanEnum.IS_FALSE);
                contractMember.setProxyId(0L);
                contractMember.setContractRebateRate(BigDecimal.ZERO);
                contractMember.setSpotRebateRate(BigDecimal.ZERO);
                contractMember.setStatus(0);
                contractMember.setVersion(0L);
                contractMember.setCreateTime(new Date());
                contractMember.setSequence(System.currentTimeMillis());
                this.insert(contractMember);
            }
        }
    }
}
