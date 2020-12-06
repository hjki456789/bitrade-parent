package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import java.math.*;

import cn.ztuo.bitrade.enums.*;

import java.util.*;

import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Service
public class UnBlockLotteryService {
    private static final Logger log;
    @Autowired
    private UnblockCommonConfigService unblockCommonConfigService;
    @Autowired
    private UnBlockLotteryDrawService unBlockLotteryDrawService;
    @Autowired
    private UnblockLotteryIncreasedRecordService unblockLotteryIncreasedRecordService;

    @Transactional(rollbackFor = {Exception.class})
    public void memberLottery(final Long memberId, final BigDecimal usdtAmount) throws Exception {
        final UnblockLotteryDraw unblockLotteryDraw = this.unBlockLotteryDrawService.getUnBlockLotteryDrawByMemberId(memberId);
        final UnblockCommonConfig unblockCommonConfig = this.unblockCommonConfigService.findById(1L);
        if (null == unblockCommonConfig) {
            return;
        }
        final UnblockCommonConfig roundUsdtLimitConfig = this.unblockCommonConfigService.findById(6L);
        BigDecimal roundUsdtLimit = BigDecimal.ZERO;
        if (null != unblockCommonConfig) {
            roundUsdtLimit = new BigDecimal(roundUsdtLimitConfig.getValue());
        }
        if (roundUsdtLimit.compareTo(BigDecimal.ZERO) > 0 && roundUsdtLimit.compareTo(usdtAmount) > 0) {
            return;
        }
        final Long limitRound = Long.parseLong(unblockCommonConfig.getValue());
        final Long transCount = unblockLotteryDraw.getTransCount() + 1L;
        Long lotteryTransCount = unblockLotteryDraw.getLotteryTransCount();
        Long lotteryDrawCount = unblockLotteryDraw.getLotteryDrawCount();
        if (transCount > 0L && transCount % limitRound == 0L) {
            ++lotteryTransCount;
            ++lotteryDrawCount;
            final UnblockLotteryIncreasedRecord increasedRecord = new UnblockLotteryIncreasedRecord();
            increasedRecord.setTime(System.currentTimeMillis());
            increasedRecord.setMemberId(memberId);
            increasedRecord.setBeforeCount(unblockLotteryDraw.getLotteryDrawCount());
            increasedRecord.setAfterCount(lotteryDrawCount);
            increasedRecord.setAddCount(1L);
            increasedRecord.setLotteryAddType(LotteryAddType.TRANSADD);
            increasedRecord.setCreateTime(new Date());
            increasedRecord.setUpdateTime(new Date());
            this.unblockLotteryIncreasedRecordService.insert(increasedRecord);
        }
        this.unBlockLotteryDrawService.updateCountInfoById(unblockLotteryDraw.getId(), transCount, lotteryTransCount, lotteryDrawCount, new Date());
    }

    static {
        log = LoggerFactory.getLogger((Class) UnBlockLotteryService.class);
    }
}
