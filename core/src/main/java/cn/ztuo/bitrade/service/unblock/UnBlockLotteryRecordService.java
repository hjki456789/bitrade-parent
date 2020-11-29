package cn.ztuo.bitrade.service.unblock;

import cn.ztuo.bitrade.controller.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.unblock.*;
import cn.ztuo.bitrade.pagination.*;
import org.apache.commons.lang3.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;
import com.querydsl.core.types.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@Service
public class UnBlockLotteryRecordService extends BaseController
{
    private static final Logger log;
    @Autowired
    UnBlockLotteryRecordRepository unBlockLotteryRecordRepository;
    @Autowired
    private MemberService memberService;

    public int save(final UnblockLotteryRecord unBlockLotteryRecord) {
        final UnblockLotteryRecord record = (UnblockLotteryRecord)this.unBlockLotteryRecordRepository.saveAndFlush(unBlockLotteryRecord);
        if (record == null) {
            return 0;
        }
        return 1;
    }

    public Page<UnblockLotteryRecord> getLimitRecord(final Long memberId, final int pageNo, final int pageSize, final Integer isWinne, final String startTime, final String endTime) {
        final Sort orders = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "sequence") });
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<UnblockLotteryRecord> specification = new Criteria<UnblockLotteryRecord>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        if (null != isWinne) {
            specification.add(Restrictions.eq("isWinne", isWinne, true));
        }
        if (StringUtils.isNotEmpty((CharSequence)startTime) && StringUtils.isNotEmpty((CharSequence)endTime) && Long.valueOf(startTime) < Long.valueOf(endTime)) {
            specification.add(Restrictions.gte("sequence", Long.valueOf(startTime), true));
            specification.add(Restrictions.lte("sequence", Long.valueOf(endTime), true));
        }
        return (Page<UnblockLotteryRecord>)this.unBlockLotteryRecordRepository.findAll((Specification)specification, (Pageable)pageRequest);
    }

    public Page<UnblockLotteryRecord> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<UnblockLotteryRecord> page = (Page<UnblockLotteryRecord>)this.unBlockLotteryRecordRepository.findAll(predicate, pageable);
        if (page != null) {
            for (final UnblockLotteryRecord data : page.getContent()) {
                final Member member = this.memberService.findOne(data.getMemberId());
                if (null != member) {
                    data.setUsername(member.getUsername());
                    data.setMobilePhone(member.getMobilePhone());
                    data.setEmail(member.getEmail());
                }
            }
        }
        return page;
    }

    public long countByMemberId(final Long memberId) {
        final Long count = this.unBlockLotteryRecordRepository.countByMemberId(memberId);
        return (count == null) ? 0L : count;
    }

    public long countByLotteryIdAndStartTime(final Long lotteryId, final Long startSequence) {
        return this.unBlockLotteryRecordRepository.countByLotteryIdAndStartTime(lotteryId, startSequence);
    }

    static {
        log = LoggerFactory.getLogger((Class)UnBlockLotteryRecordService.class);
    }
}
