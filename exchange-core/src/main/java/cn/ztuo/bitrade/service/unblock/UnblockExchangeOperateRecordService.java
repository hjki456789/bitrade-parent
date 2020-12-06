package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.unbolck.*;
import java.math.*;

@Service
public class UnblockExchangeOperateRecordService
{
    @Autowired
    private UnblockExchangeOperateRecordRepository unblockExchangeOperateRecordRepository;

    public UnblockMemberOperateRecord save(final UnblockMemberOperateRecord record) {
        return (UnblockMemberOperateRecord)this.unblockExchangeOperateRecordRepository.saveAndFlush(record);
    }

    public int countMemberRound(final Long memberId, final String symbol) {
        return this.unblockExchangeOperateRecordRepository.countMemberRound(memberId, symbol);
    }

    public int countMemberRoundBySequence(final Long memberId, final String symbol, final Long sequence) {
        return this.unblockExchangeOperateRecordRepository.countMemberRoundBySequence(memberId, symbol, sequence);
    }

    public BigDecimal sumMemberTradedBySequence(final Long memberId, final String symbol, final Long sequence) {
        return this.unblockExchangeOperateRecordRepository.sumMemberTradedBySequence(memberId, symbol, sequence);
    }
}
