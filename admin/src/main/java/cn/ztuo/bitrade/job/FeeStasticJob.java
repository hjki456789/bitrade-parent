package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.unblock.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.time.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.entity.*;
import java.math.*;
import java.util.*;
import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Component
public class FeeStasticJob
{
    private static final Logger log;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private ExchangeCoinService exchangeCoinService;
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
    private FeeStasticService feeStasticService;
    private static Logger logger;

    @Transactional(rollbackFor = { Exception.class })
    public void memberFeeStatictis() {
        FeeStasticJob.logger.info("---------------\u624b\u7eed\u8d39\u7edf\u8ba1\u5f00\u59cb--------------------");
        final List<ExchangeCoin> exchangeCoinList = (List<ExchangeCoin>)this.exchangeCoinService.findAll();
        if (this.exchangeCoinService == null) {
            FeeStasticJob.logger.info("---------------\u4ea4\u6613\u5bf9\u83b7\u53d6\u5931\u8d25--------------------");
            return;
        }
        FeeStasticJob.logger.info("---------------\u4ea4\u6613\u5bf9\u6570\u91cf--------------------" + exchangeCoinList.size());
        final long todaySequence = Common.getTodaySequence();
        final long yesterdaySequence = todaySequence - 86400000L;
        FeeStasticJob.logger.info("---------------\u7edf\u8ba1\u65e5\u671f--------------------" + DateFormatUtils.format(todaySequence, "yyyy-MM-dd"));
        for (final ExchangeCoin exchangeCoin : exchangeCoinList) {
            final String symbol = exchangeCoin.getSymbol();
            final Map<String, Double> feeMap = new HashMap<String, Double>();
            FeeStasticJob.logger.info("---------------\u7edf\u8ba1\u4ea4\u6613\u5bf9\u624b\u7eed\u8d39--------------------" + symbol);
            final List<ExchangeOrder> exchangeOrders = (List<ExchangeOrder>)this.exchangeOrderService.findOrdersBySymbol(symbol, Long.valueOf(yesterdaySequence), Long.valueOf(todaySequence));
            if (CollectionUtils.isEmpty((Collection)exchangeOrders)) {
                FeeStasticJob.logger.info("---------------\u4ea4\u6613\u5bf9\u8ba2\u5355\u6570\u91cf\u4e3a\u7a7a--------------------");
            }
            else {
                FeeStasticJob.logger.info("---------------\u4ea4\u6613\u5bf9\u8ba2\u5355\u6570\u91cf--------------------" + exchangeOrders.size());
                for (final ExchangeOrder exchangeOrder : exchangeOrders) {
                    final List<ExchangeOrderDetail> exchangeOrderDetailList = (List<ExchangeOrderDetail>)this.exchangeOrderDetailService.findAllByOrderIdAndTime(exchangeOrder.getOrderId(), Long.valueOf(yesterdaySequence), Long.valueOf(todaySequence));
                    if (CollectionUtils.isEmpty((Collection)exchangeOrderDetailList)) {
                        continue;
                    }
                    FeeStasticJob.logger.info("---------------\u8ba2\u5355\u8be6\u7ec6\u4fe1\u606f\u83b7\u53d6\u6210\u529f--------------------" + exchangeOrderDetailList.size());
                    double fee = 0.0;
                    for (final ExchangeOrderDetail exchangeOrderDetail : exchangeOrderDetailList) {
                        fee += exchangeOrderDetail.getFee().doubleValue();
                    }
                    FeeStasticJob.logger.info("---------------\u8ba2\u5355\u624b\u7eed\u8d39\u4e3a--------------------" + fee);
                    if (fee <= 0.0) {
                        continue;
                    }
                    String unit = null;
                    if (exchangeOrder.getDirection().equals((Object)ExchangeOrderDirection.BUY)) {
                        unit = exchangeOrder.getCoinSymbol();
                    }
                    else {
                        unit = exchangeOrder.getBaseSymbol();
                    }
                    if (feeMap.containsKey(unit)) {
                        fee += feeMap.get(unit);
                        feeMap.put(unit, fee);
                    }
                    else {
                        feeMap.put(unit, fee);
                    }
                }
                final String yesterdayDate = DateFormatUtils.format(yesterdaySequence, "yyyy-MM-dd");
                for (final String key : feeMap.keySet()) {
                    final FeeStastic feeStastic = new FeeStastic();
                    feeStastic.setAmount(new BigDecimal(feeMap.get(key)));
                    feeStastic.setDate(new Date());
                    feeStastic.setSequence(Long.valueOf(System.currentTimeMillis()));
                    feeStastic.setSymbol(symbol);
                    feeStastic.setUnit(key);
                    double bonusAmount = 0.0;
                    if (symbol.equalsIgnoreCase("WCG/USDT")) {
                        bonusAmount = feeMap.get(key) * 0.2;
                    }
                    else {
                        bonusAmount = feeMap.get(key) * 0.0;
                    }
                    feeStastic.setBonusAmount(new BigDecimal(bonusAmount));
                    this.feeStasticService.save(feeStastic);
                    FeeStasticJob.logger.info("---------------\u8ba2\u5355\u624b\u7eed\u8d39\u4fdd\u5b58\u6210\u529f--------------------" + feeStastic);
                }
            }
        }
        FeeStasticJob.logger.info("---------------\u624b\u7eed\u8d39\u7edf\u8ba1\u7ed3\u675f--------------------");
    }

    static {
        log = LoggerFactory.getLogger((Class)FeeStasticJob.class);
        FeeStasticJob.logger = LoggerFactory.getLogger((Class)FeeStasticJob.class);
    }
}
