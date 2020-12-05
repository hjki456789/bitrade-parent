package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.collections.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.time.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@Component
public class MemberTeamJob
{
    private static final Logger log;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberPromotionService memberPromotionService;
    @Autowired
    private MemberTeamService memberTeamService;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractDoubleDirectionWalletService contractDoubleDirectionWalletService;
    @Autowired
    private ContractOrderService contractOrderService;
    
    @Scheduled(cron = "0 0/2 * * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void calculateMemberTeam() {
        final List<Member> memberList = (List<Member>)this.memberService.findAll();
        if (CollectionUtils.isEmpty((Collection)memberList)) {
            return;
        }
        for (final Member member : memberList) {
            final List<MemberTeam> list = this.findTeamMemberAndGeneration(null, member.getId(), 0);
            if (CollectionUtils.isNotEmpty((Collection)list)) {
                this.saveMemberTeam(list, member.getId());
            }
        }
    }
    
    public void saveMemberTeam(final List<MemberTeam> list, final Long memberId) {
        for (final MemberTeam team : list) {
            team.setMemberId(memberId);
            BigDecimal amount = BigDecimal.ZERO;
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long)team.getLowerMemberId());
            if (contractWallet != null) {
                amount = contractWallet.getBalance();
            }
            final ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(team.getLowerMemberId(), "USDT");
            if (contractDoubleDirectionWallet != null) {
                amount = amount.add(contractDoubleDirectionWallet.getBalance());
            }
            team.setAmount(amount);
            long sequence = DateUtil.getTodaySequence();
            final String hour = DateFormatUtils.format(System.currentTimeMillis(), "HH");
            if (hour.equalsIgnoreCase("00") || hour.equalsIgnoreCase("01")) {
                sequence = DateUtil.getYesterdaySequence();
            }
            final long orderNum = this.contractOrderService.countBySequenceAndMemberId(team.getLowerMemberId(), Long.valueOf(sequence), 1);
            if (orderNum > 0L) {
                team.setIsValidMember(1);
            }
            else {
                team.setIsValidMember(0);
            }
            final MemberTeam memberTeam = this.memberTeamService.getByMemberIdAndLowerMemberId(memberId, team.getLowerMemberId());
            if (null == memberTeam) {
                this.memberTeamService.save(team);
            }
            else {
                if (memberTeam.getGeneration().equals(team.getGeneration()) && memberTeam.getAmount().doubleValue() == team.getAmount().doubleValue()) {
                    continue;
                }
                team.setId(memberTeam.getId());
                this.memberTeamService.save(team);
            }
        }
    }
    
    public List<MemberTeam> findTeamMemberAndGeneration(List<MemberTeam> list, final Long memberId, int count) {
        ++count;
        if (list == null) {
            list = new ArrayList<MemberTeam>();
        }
        final List<MemberPromotion> memberPromotionList = (List<MemberPromotion>)this.memberPromotionService.findByInviterId(memberId);
        if (CollectionUtils.isNotEmpty((Collection)memberPromotionList)) {
            for (final MemberPromotion info : memberPromotionList) {
                final MemberTeam team = new MemberTeam();
                team.setLowerMemberId(info.getInviteesId());
                team.setGeneration(Integer.valueOf(count));
                list.add(team);
            }
            for (final MemberPromotion info : memberPromotionList) {
                this.findTeamMemberAndGeneration(list, info.getInviteesId(), count);
            }
        }
        --count;
        return list;
    }
    
    public List<MemberTeam> findTeamMember(List<MemberTeam> list, final Long memberId) {
        if (list == null) {
            list = new ArrayList<MemberTeam>();
        }
        final List<MemberPromotion> memberPromotionList = (List<MemberPromotion>)this.memberPromotionService.findByInviterId(memberId);
        if (CollectionUtils.isNotEmpty((Collection)memberPromotionList)) {
            for (final MemberPromotion info : memberPromotionList) {
                this.findTeamMember(list, info.getInviteesId());
            }
        }
        return list;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)MemberTeamJob.class);
    }
}
