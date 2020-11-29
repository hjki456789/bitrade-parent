package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.unblock.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;

@Service
public class UnblockLotteryIncreasedRecordService
{
    @Autowired
    private UnblockLotteryIncreasedRecordRepository unblockLotteryIncreasedRecordRepository;

    public UnblockLotteryIncreasedRecord insert(final UnblockLotteryIncreasedRecord record) {
        return (UnblockLotteryIncreasedRecord)this.unblockLotteryIncreasedRecordRepository.saveAndFlush(record);
    }

    public Page<UnblockLotteryIncreasedRecord> findAllByMemberId(final Long memberId, final int pageNo, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "time") });
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<UnblockLotteryIncreasedRecord> specification = new Criteria<UnblockLotteryIncreasedRecord>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        return (Page<UnblockLotteryIncreasedRecord>)this.unblockLotteryIncreasedRecordRepository.findAll((Specification)specification, (Pageable)pageRequest);
    }

    public long countAddCountByMemberIdAndType(final Long memberId, final int lotteryAddType) {
        final Long count = this.unblockLotteryIncreasedRecordRepository.countAddCountByMemberIdAndType(memberId, lotteryAddType);
        return (count == null) ? 0L : count;
    }
}
