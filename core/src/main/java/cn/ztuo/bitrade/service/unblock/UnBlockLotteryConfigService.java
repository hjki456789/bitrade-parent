package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.unblock.*;
import java.util.*;
import java.io.*;
import java.math.*;
import org.slf4j.*;

@Service
public class UnBlockLotteryConfigService
{
    private static final Logger log;
    @Autowired
    UnBlockLotteryConfigRepository unBlockLotteryConfigRepository;

    public Page<UnblockLotteryConfig> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<UnblockLotteryConfig>)this.unBlockLotteryConfigRepository.findAll(predicate, pageable);
    }

    public List<UnblockLotteryConfig> getAllLotteryConfig() {
        return (List<UnblockLotteryConfig>)this.unBlockLotteryConfigRepository.findAll();
    }

    public UnblockLotteryConfig save(final UnblockLotteryConfig config) {
        return (UnblockLotteryConfig)this.unBlockLotteryConfigRepository.saveAndFlush(config);
    }

    public void delete(final Long id) {
        this.unBlockLotteryConfigRepository.deleteById(id);
    }

    public int update(final UnblockLotteryConfig config) {
        return this.unBlockLotteryConfigRepository.update(config.getId(), config.getLotteryLevel(), config.getLotteryRate(), config.getLotteryInstruction(), config.getCoin(), config.getAmount(), config.getInsuranceRate(), config.getDayWinnerNumLimit());
    }

    public UnblockLotteryConfig findById(final Long id) {
        return (UnblockLotteryConfig)this.unBlockLotteryConfigRepository.getOne(id);
    }

    public UnblockLotteryConfig getOne() {
        return this.unBlockLotteryConfigRepository.getOne();
    }

    public int updateRuleAndPrice(final String lotteryRule, final BigDecimal buyPrice) {
        return this.unBlockLotteryConfigRepository.updateRuleAndPrice(lotteryRule, buyPrice);
    }

    public List<UnblockLotteryConfig> getAllLotteryConfigByRate() {
        return this.unBlockLotteryConfigRepository.getAllLotteryConfigByRate();
    }

    static {
        log = LoggerFactory.getLogger((Class)UnBlockLotteryConfigService.class);
    }
}
