package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.collections.*;
import java.math.*;
import cn.ztuo.bitrade.constant.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Component
public class MemberTeamRewardJob
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
    private MemberDepositService memberDepositService;
    @Autowired
    private WithdrawRecordService withdrawRecordService;
    @Autowired
    private DepositProfitInfoService depositProfitInfoService;
    @Autowired
    private DepositWalletService depositWalletService;
    @Autowired
    private DepositRecommendProfitInfoService depositRecommendProfitInfoService;
    private static Logger logger;
    
    @Scheduled(cron = "0 30 6 * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void memberRewardStatictis() {
        MemberTeamRewardJob.logger.info("---------------\u7528\u6237\u8d28\u62bc\u6536\u76ca\u8fd4\u4f63\u8ba1\u7b97\u5f00\u59cb---------------");
        final long todaySequence = DateUtil.getTodaySequence();
        final List<DepositProfitInfo> depositProfitInfoList = (List<DepositProfitInfo>)this.depositProfitInfoService.getInfosBySequnce(Long.valueOf(todaySequence));
        if (CollectionUtils.isEmpty((Collection)depositProfitInfoList)) {
            return;
        }
        final List<MemberGrade> grades = (List<MemberGrade>)this.memberGradeService.findAll();
        if (CollectionUtils.isEmpty((Collection)grades)) {
            return;
        }
        MemberTeamRewardJob.logger.info("---------------\u7528\u6237\u7b49\u7ea7\u6570\u91cf---------------" + grades.size());
        final Map<String, MemberGrade> gradeMap = new HashMap<String, MemberGrade>();
        for (final MemberGrade grade : grades) {
            gradeMap.put(grade.getGradeCode() + "", grade);
        }
        for (final DepositProfitInfo depositProfitInfo : depositProfitInfoList) {
            final List<MemberTeam> memberTeamList = (List<MemberTeam>)this.memberTeamService.getTeamByLowerId((long)depositProfitInfo.getMemberId());
            if (CollectionUtils.isEmpty((Collection)memberTeamList)) {
                continue;
            }
            final Map<String, MemberTeam> memberTeamMap = new HashMap<String, MemberTeam>();
            for (final MemberTeam memberTeam : memberTeamList) {
                memberTeamMap.put(memberTeam.getGeneration() + "", memberTeam);
            }
            BigDecimal lastProfit = BigDecimal.ZERO;
            BigDecimal lastRate = BigDecimal.ZERO;
            String lastMemberId = "";
            final Map<String, BigDecimal> profitMap = new HashMap<String, BigDecimal>();
            for (int i = 1; i < memberTeamList.size() + 1; ++i) {
                final MemberTeam memberTeam2 = memberTeamMap.get(i + "");
                if (memberTeam2 != null) {
                    final Member upMember = this.memberService.findOne(memberTeam2.getMemberId());
                    if (upMember != null) {
                        final MemberGrade upMemberGrade = gradeMap.get(upMember.getMemberGradeId() + "");
                        if (upMemberGrade != null) {
                            BigDecimal profit = BigDecimal.ZERO;
                            if (i == 1) {
                                profit = (lastProfit = depositProfitInfo.getAmount().multiply(upMemberGrade.getCommissionRate()));
                                lastRate = upMemberGrade.getCommissionRate();
                                lastMemberId = upMember.getId() + "";
                                profitMap.put(lastMemberId, new BigDecimal(lastProfit.doubleValue()));
                            }
                            else if (lastRate.doubleValue() < upMemberGrade.getCommissionRate().doubleValue()) {
                                profit = (lastProfit = depositProfitInfo.getAmount().multiply(upMemberGrade.getCommissionRate().subtract(lastRate)));
                                lastRate = upMemberGrade.getCommissionRate();
                                lastMemberId = upMember.getId() + "";
                                profitMap.put(lastMemberId, new BigDecimal(lastProfit.doubleValue()));
                            }
                            else if (lastRate.doubleValue() == upMemberGrade.getCommissionRate().doubleValue() && upMemberGrade.getId() > 1L) {
                                profit = lastProfit.multiply(new BigDecimal(0.2));
                                profitMap.put(lastMemberId, lastProfit.subtract(profit));
                                lastProfit = profit;
                                lastMemberId = upMember.getId() + "";
                                profitMap.put(lastMemberId, new BigDecimal(lastProfit.doubleValue()));
                            }
                        }
                    }
                }
            }
            for (final String key : profitMap.keySet()) {
                long rewardMemberId = 0L;
                try {
                    rewardMemberId = Long.parseLong(key);
                }
                catch (Exception e) {
                    continue;
                }
                final BigDecimal recommedProfit = profitMap.get(key);
                if (recommedProfit.doubleValue() > 0.0) {
                    final BigDecimal fundAmount = recommedProfit;
                    MemberWallet upMemberWallet = this.memberWalletService.findByCoinUnitAndMemberId("USDT", Long.valueOf(rewardMemberId));
                    if (upMemberWallet == null) {
                        upMemberWallet = new MemberWallet();
                        final Coin coin = new Coin();
                        coin.setUnit("USDT");
                        coin.setName("USDT");
                        upMemberWallet.setCoin(coin);
                        upMemberWallet.setMemberId(Long.valueOf(rewardMemberId));
                        upMemberWallet = this.memberWalletService.save(upMemberWallet);
                    }
                    this.memberWalletService.increaseBalanceWithoutVersion(upMemberWallet.getId(), fundAmount);
                    final DepositRecommendProfitInfo depositRecommendProfitInfo = new DepositRecommendProfitInfo();
                    depositRecommendProfitInfo.setAmount(recommedProfit);
                    depositRecommendProfitInfo.setCoinName(depositProfitInfo.getCoinName());
                    depositRecommendProfitInfo.setDepositAmount(BigDecimal.ZERO);
                    depositRecommendProfitInfo.setDepositId(depositProfitInfo.getDepositId());
                    depositRecommendProfitInfo.setFromMemberId(depositProfitInfo.getMemberId());
                    depositRecommendProfitInfo.setFundAmount(fundAmount);
                    depositRecommendProfitInfo.setMemberId(Long.valueOf(rewardMemberId));
                    depositRecommendProfitInfo.setSequence(Long.valueOf(System.currentTimeMillis()));
                    depositRecommendProfitInfo.setTime(new Date());
                    this.depositRecommendProfitInfoService.save(depositRecommendProfitInfo);
                    final MemberTransaction memberTransaction = new MemberTransaction();
                    memberTransaction.setAmount(fundAmount);
                    memberTransaction.setFee(new BigDecimal(0));
                    memberTransaction.setMemberId(Long.valueOf(rewardMemberId));
                    memberTransaction.setSymbol("USDT");
                    memberTransaction.setType(TransactionType.DEPOSIT_PROFIT_RECOMMEND);
                    memberTransaction.setFromMemberId(depositProfitInfo.getMemberId());
                    memberTransaction.setSequence(Long.valueOf(System.currentTimeMillis()));
                    memberTransaction.setAfterBalance(upMemberWallet.getBalance());
                    this.memberTransactionService.save(memberTransaction);
                }
            }
        }
        MemberTeamRewardJob.logger.info("---------------\u7528\u6237\u8d28\u62bc\u6536\u76ca\u8fd4\u4f63\u8ba1\u7b97\u7ed3\u675f---------------");
    }
    
    private Map<String, Double> getReward(final MemberGrade memberGrade, final MemberTeam memberTeam, final Map<String, Double> feeMap) {
        final Map<String, Double> feeRewardMap = new HashMap<String, Double>();
        if (feeMap == null || memberGrade == null || memberTeam == null) {
            return feeRewardMap;
        }
        double rate = 0.0;
        if (memberTeam.getGeneration() == 1) {
            rate = memberGrade.getOneGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 2) {
            rate = memberGrade.getTwoGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 3) {
            rate = memberGrade.getThreeGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 4) {
            rate = memberGrade.getFourGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 5) {
            rate = memberGrade.getFiveGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 6) {
            rate = memberGrade.getSixGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 7) {
            rate = memberGrade.getSevenGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 8) {
            rate = memberGrade.getEightGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 9) {
            rate = memberGrade.getNineGenerationRate().doubleValue();
        }
        else if (memberTeam.getGeneration() == 10) {
            rate = memberGrade.getTenGenerationRate().doubleValue();
        }
        for (final String key : feeMap.keySet()) {
            feeRewardMap.put(key, feeMap.get(key) * rate * 0.8);
        }
        return feeRewardMap;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)MemberTeamRewardJob.class);
        MemberTeamRewardJob.logger = LoggerFactory.getLogger((Class)MemberTeamRewardJob.class);
    }
}
