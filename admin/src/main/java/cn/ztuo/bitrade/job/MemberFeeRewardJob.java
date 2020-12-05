package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.service.*;
import org.apache.commons.lang3.time.*;
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
public class MemberFeeRewardJob
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
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractExchangeOrderFeeService contractExchangeOrderFeeService;
    private static Logger logger;
    
    @Scheduled(cron = "0 5 0 * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void memberRewardStatictis() {
        final String today = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
        while (!today.equalsIgnoreCase(MemberLevelStatisJob.computDate)) {
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MemberFeeRewardJob.logger.info("---------------\u7528\u6237\u624b\u7eed\u8d39\u8fd4\u4f63\u8ba1\u7b97\u5f00\u59cb---------------");
        final long todaySequence = DateUtil.getTodaySequence();
        final long yesterSequence = DateUtil.getYesterdaySequence();
        final List<ContractExchangeOrder> contractExchangeOrderList = (List<ContractExchangeOrder>)this.contractOrderService.getCloseOrdersBySequence(Long.valueOf(yesterSequence), Long.valueOf(todaySequence));
        if (CollectionUtils.isEmpty((Collection)contractExchangeOrderList)) {
            return;
        }
        final List<MemberGrade> grades = (List<MemberGrade>)this.memberGradeService.findAll();
        if (CollectionUtils.isEmpty((Collection)grades)) {
            return;
        }
        MemberFeeRewardJob.logger.info("---------------\u7528\u6237\u7b49\u7ea7\u6570\u91cf---------------" + grades.size());
        final Map<String, MemberGrade> gradeMap = new HashMap<String, MemberGrade>();
        for (final MemberGrade grade : grades) {
            gradeMap.put(grade.getGradeCode() + "", grade);
        }
        for (final ContractExchangeOrder contractExchangeOrder : contractExchangeOrderList) {
            final List<MemberTeam> memberTeamList = (List<MemberTeam>)this.memberTeamService.getTeamByLowerId((long)contractExchangeOrder.getMember().getId());
            if (CollectionUtils.isEmpty((Collection)memberTeamList)) {
                continue;
            }
            final Map<String, MemberTeam> memberTeamMap = new HashMap<String, MemberTeam>();
            for (final MemberTeam memberTeam : memberTeamList) {
                memberTeamMap.put(memberTeam.getGeneration() + "", memberTeam);
            }
            final ContractExchangeOrderFee contractExchangeOrderFee = this.contractExchangeOrderFeeService.findByOrderId(contractExchangeOrder.getId());
            if (contractExchangeOrderFee == null) {
                continue;
            }
            final BigDecimal fee = contractExchangeOrderFee.getCloseFee().add(contractExchangeOrderFee.getOpenFee());
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
                                profit = (lastProfit = fee.multiply(upMemberGrade.getFeeCommissionRate()));
                                lastRate = upMemberGrade.getFeeCommissionRate();
                                lastMemberId = upMember.getId() + "";
                                profitMap.put(lastMemberId, new BigDecimal(lastProfit.doubleValue()));
                            }
                            else if (lastRate.doubleValue() < upMemberGrade.getFeeCommissionRate().doubleValue()) {
                                profit = (lastProfit = fee.multiply(upMemberGrade.getFeeCommissionRate().subtract(lastRate)));
                                lastRate = upMemberGrade.getFeeCommissionRate();
                                lastMemberId = upMember.getId() + "";
                                profitMap.put(lastMemberId, new BigDecimal(lastProfit.doubleValue()));
                            }
                            else if (lastRate.doubleValue() == upMemberGrade.getFeeCommissionRate().doubleValue() && upMemberGrade.getId() > 1L) {
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
                catch (Exception e2) {
                    continue;
                }
                if (profitMap.get(key).doubleValue() > 0.0) {
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
                    this.memberWalletService.increaseBalanceWithoutVersion(upMemberWallet.getId(), (BigDecimal)profitMap.get(key));
                    final MemberTransaction memberTransaction = new MemberTransaction();
                    memberTransaction.setAmount((BigDecimal)profitMap.get(key));
                    memberTransaction.setFee(new BigDecimal(0));
                    memberTransaction.setMemberId(Long.valueOf(rewardMemberId));
                    memberTransaction.setSymbol("USDT");
                    memberTransaction.setType(TransactionType.FEE_PROFIT_RECOMMEND);
                    memberTransaction.setSequence(Long.valueOf(System.currentTimeMillis()));
                    memberTransaction.setAfterBalance(upMemberWallet.getBalance());
                    memberTransaction.setFromMemberId(contractExchangeOrder.getMember().getId());
                    this.memberTransactionService.save(memberTransaction);
                }
            }
        }
        MemberFeeRewardJob.logger.info("---------------\u7528\u6237\u624b\u7eed\u8d39\u8fd4\u4f63\u8ba1\u7b97\u7ed3\u675f---------------");
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
        log = LoggerFactory.getLogger((Class)MemberFeeRewardJob.class);
        MemberFeeRewardJob.logger = LoggerFactory.getLogger((Class)MemberFeeRewardJob.class);
    }
}
