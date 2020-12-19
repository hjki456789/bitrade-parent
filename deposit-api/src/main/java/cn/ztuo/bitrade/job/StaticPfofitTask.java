package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import java.math.*;
import cn.ztuo.bitrade.constant.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Component
public class StaticPfofitTask
{
    private static final Logger log;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private DepositWalletService depositWalletService;
    @Autowired
    private MemberDepositInfoService memberDepositInfoService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private DepositProfitInfoService depositProfitInfoService;
    @Autowired
    private DepositTypeInfoService depositTypeInfoService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    
    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = { Exception.class })
    public void staticProfitCompute() {
        StaticPfofitTask.log.info("=========\u5f00\u59cb\u53d1\u653e\u9759\u6001\u6536\u76ca===========");
        final Long todaySequence = DateUtil.getTodaySequence();
        final List<MemberDepositInfo> memberDepositInfos = (List<MemberDepositInfo>)this.memberDepositInfoService.getUnFinishDepositInfoBySequence(todaySequence);
        if (memberDepositInfos != null) {
            for (final MemberDepositInfo memberDepositInfo : memberDepositInfos) {
                final List<DepositProfitInfo> results = (List<DepositProfitInfo>)this.depositProfitInfoService.getListByDepositInfoIdAndSequence(memberDepositInfo.getId(), todaySequence);
                if (results == null || results.size() <= 0) {
                    DepositTypeInfo depositTypeInfo = memberDepositInfo.getDepositTypeInfo();
                    if (depositTypeInfo == null) {
                        depositTypeInfo = this.depositTypeInfoService.findById(memberDepositInfo.getDepositTypeId());
                        if (depositTypeInfo == null) {
                            continue;
                        }
                    }
                    final BigDecimal profit = memberDepositInfo.getAmount().multiply(depositTypeInfo.getDayProfitRate());
                    final BigDecimal fundProfit = profit.multiply(new BigDecimal(0.5));
                    final BigDecimal depositProfit = profit.multiply(new BigDecimal(0.5));
                    MemberWallet memberWallet = this.memberWalletService.findOneByCoinNameAndMemberId(memberDepositInfo.getDepositTypeInfo().getProfitCoin(), (long)memberDepositInfo.getMemberId());
                    if (memberWallet == null) {
                        memberWallet = new MemberWallet();
                        memberWallet.setMemberId(memberDepositInfo.getMemberId());
                        Coin coin = this.coinService.findByUnit(memberDepositInfo.getDepositTypeInfo().getProfitCoin());
                        if (coin == null) {
                            coin = new Coin();
                            coin.setUnit("USDT");
                            coin.setName("USDT");
                        }
                        memberWallet.setCoin(coin);
                        memberWallet = this.memberWalletService.save(memberWallet);
                    }
                    this.memberWalletService.increaseBalanceWithoutVersion(memberWallet.getId(), fundProfit);
                    final MemberTransaction transactionFund = new MemberTransaction();
                    transactionFund.setAmount(fundProfit);
                    transactionFund.setSymbol(memberWallet.getCoin().getName());
                    transactionFund.setAddress("");
                    transactionFund.setMemberId(memberWallet.getMemberId());
                    transactionFund.setType(TransactionType.DEPOSIT_PROFIT_STATIC);
                    transactionFund.setFee(BigDecimal.ZERO);
                    transactionFund.setCreateTime(new Date());
                    transactionFund.setSequence(Long.valueOf(System.currentTimeMillis()));
                    transactionFund.setAfterBalance(memberWallet.getBalance().subtract(fundProfit));
                    this.memberTransactionService.save(transactionFund);
                    DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(memberDepositInfo.getMemberId(), memberDepositInfo.getDepositTypeInfo().getProfitCoin());
                    if (depositWallet == null) {
                        depositWallet = new DepositWallet();
                        Coin coin2 = this.coinService.findByUnit(memberDepositInfo.getDepositTypeInfo().getProfitCoin());
                        if (coin2 == null) {
                            coin2 = new Coin();
                            coin2.setUnit("USDT");
                            coin2.setName("USDT");
                        }
                        depositWallet.setMemberId(memberDepositInfo.getMemberId());
                        depositWallet.setCoin(coin2);
                        depositWallet = this.depositWalletService.save(depositWallet);
                    }
                    depositWallet.setBalance(depositWallet.getBalance().add(depositProfit));
                    depositWallet.setTotalInBalance(depositWallet.getTotalInBalance().add(depositProfit));
                    this.depositWalletService.updateBalance(depositWallet);
                    final MemberTransaction transactionDeposit = new MemberTransaction();
                    transactionDeposit.setAmount(depositProfit);
                    transactionDeposit.setSymbol(depositWallet.getCoin().getName());
                    transactionDeposit.setAddress("");
                    transactionDeposit.setMemberId(depositWallet.getMemberId());
                    transactionDeposit.setType(TransactionType.DEPOSIT_PROFIT_STATIC);
                    transactionDeposit.setFee(BigDecimal.ZERO);
                    transactionDeposit.setCreateTime(new Date());
                    transactionDeposit.setSequence(Long.valueOf(System.currentTimeMillis()));
                    transactionDeposit.setAfterBalance(depositWallet.getBalance());
                    this.memberTransactionService.save(transactionFund);
                    final DepositProfitInfo depositProfitInfo = new DepositProfitInfo();
                    depositProfitInfo.setAmount(profit);
                    depositProfitInfo.setMemberId(memberDepositInfo.getMemberId());
                    depositProfitInfo.setCoinName(memberDepositInfo.getDepositTypeInfo().getProfitCoin());
                    depositProfitInfo.setDepositId(memberDepositInfo.getId());
                    depositProfitInfo.setDepositAmount(depositProfit);
                    depositProfitInfo.setSequence(Long.valueOf(System.currentTimeMillis()));
                    depositProfitInfo.setFundAmount(fundProfit);
                    depositProfitInfo.setTime(new Date());
                    this.depositProfitInfoService.save(depositProfitInfo);
                }
                final BigDecimal profit2 = this.depositProfitInfoService.sumProfitByMemberIdAndDepositId(memberDepositInfo.getId());
                this.memberDepositInfoService.updateProfit(memberDepositInfo.getId(), profit2);
            }
        }
        StaticPfofitTask.log.info("=========\u9759\u6001\u6536\u76ca\u53d1\u653e\u7ed3\u675f===========");
    }
    
    static {
        log = LoggerFactory.getLogger((Class)StaticPfofitTask.class);
    }
}
