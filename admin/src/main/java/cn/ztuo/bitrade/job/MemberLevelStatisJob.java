package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.collections.*;
import java.math.*;
import org.apache.commons.lang3.time.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Component
public class MemberLevelStatisJob
{
    private static final Logger log;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private MemberTeamService memberTeamService;
    @Autowired
    private MemberGradeService memberGradeService;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;
    @Autowired
    private ExchangeOrderDetailService exchangeOrderDetailService;
    @Autowired
    private UnblockMemberRewardService unblockMemberRewardService;
    @Autowired
    private WithdrawRecordService withdrawRecordService;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractDoubleDirectionWalletService contractDoubleDirectionWalletService;
    private static Logger logger;
    public static String computDate;
    
    @Scheduled(cron = "0 1 0 * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void memberGradeStatictis() {
        MemberLevelStatisJob.logger.info("----------------\u7528\u6237\u7b49\u7ea7\u8ba1\u7b97\u5f00\u59cb------------------");
        final List<Member> memberList = (List<Member>)this.memberService.findAll();
        if (CollectionUtils.isEmpty((Collection)memberList)) {
            return;
        }
        MemberLevelStatisJob.logger.info("----------------\u7528\u6237\u6570\u91cf------------------" + memberList.size());
        final List<MemberGrade> grades = (List<MemberGrade>)this.memberGradeService.findAll();
        if (CollectionUtils.isEmpty((Collection)grades)) {
            return;
        }
        MemberLevelStatisJob.logger.info("----------------\u7528\u6237\u7b49\u7ea7\u6570\u91cf------------------" + grades.size());
        for (final Member member : memberList) {
            if (member.getIfFixMemberGrade() != null && member.getIfFixMemberGrade() == 1) {
                continue;
            }
            BigDecimal amountHave = BigDecimal.ZERO;
            final ContractWallet contractWallet = this.contractWalletService.findByMemberId((long)member.getId());
            if (contractWallet != null) {
                amountHave = contractWallet.getBalance();
            }
            final ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(member.getId(), "USDT");
            if (contractDoubleDirectionWallet != null) {
                amountHave = amountHave.add(contractDoubleDirectionWallet.getBalance());
            }
            final BigDecimal teamAmount = this.memberTeamService.getTeamAmountByMemberId(member.getId());
            final List<MemberTeam> memberDirectTeams = (List<MemberTeam>)this.memberTeamService.getTeamByMemberIdAndGeneration((long)member.getId(), 1);
            long directNum = 0L;
            if (!CollectionUtils.isEmpty((Collection)memberDirectTeams)) {
                directNum = memberDirectTeams.size();
            }
            final long teamNum = this.memberTeamService.countValidTeamNumByMemberId((long)member.getId());
            long levelId = 1L;
            final long preLevelId = member.getMemberGradeId();
            for (final MemberGrade memberGrade : grades) {
                if (amountHave.doubleValue() < memberGrade.getFixInvestAmount().doubleValue()) {
                    continue;
                }
                if (teamAmount.doubleValue() < memberGrade.getTeamInvestAmount().doubleValue()) {
                    continue;
                }
                if (directNum < memberGrade.getDirectNumber()) {
                    continue;
                }
                if (teamNum < memberGrade.getTeamNumber()) {
                    continue;
                }
                if (memberGrade.getId() <= levelId) {
                    continue;
                }
                levelId = memberGrade.getId();
            }
            if (preLevelId >= 3L && levelId < 3L) {
                levelId = 3L;
            }
            if (preLevelId == 2L && levelId < 2L) {
                levelId = 2L;
            }
            if (levelId == preLevelId) {
                continue;
            }
            this.memberService.updateMemberGradeId(member.getId(), levelId);
            MemberLevelStatisJob.logger.info("----------------\u7528\u6237\u7b49\u7ea7\u5347\u7ea7\u6210\u529f------------------" + member);
        }
        MemberLevelStatisJob.computDate = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
        MemberLevelStatisJob.logger.info("----------------\u7528\u6237\u7b49\u7ea7\u8ba1\u7b97\u7ed3\u675f------------------");
    }
    
    static {
        log = LoggerFactory.getLogger((Class)MemberLevelStatisJob.class);
        MemberLevelStatisJob.logger = LoggerFactory.getLogger((Class)MemberLevelStatisJob.class);
        MemberLevelStatisJob.computDate = "";
    }
}
