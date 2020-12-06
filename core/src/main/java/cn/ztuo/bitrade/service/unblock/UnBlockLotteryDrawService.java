package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.unblock.*;
import cn.ztuo.bitrade.enums.*;
import org.springframework.transaction.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

import java.util.*;

import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@Service
public class UnBlockLotteryDrawService {
    private static final Logger log;
    @Autowired
    UnBlockLotteryDrawRepository unBlockLotteryDrawRepository;
    @Autowired
    private UnBlockLotteryRecordService unBlockLotteryRecordService;
    @Autowired
    private UnblockLotteryIncreasedRecordService unblockLotteryIncreasedRecordService;
    @Autowired
    MemberService memberService;

    public UnblockLotteryDraw getUnBlockLotteryDrawByMemberId(final Long memberId) {
        UnblockLotteryDraw unBlockLotteryDraw = this.unBlockLotteryDrawRepository.findByMemberId(memberId);
        if (null == unBlockLotteryDraw) {
            unBlockLotteryDraw = new UnblockLotteryDraw();
            unBlockLotteryDraw.setCreateTime(new Date());
            unBlockLotteryDraw.setMemberId(memberId);
            unBlockLotteryDraw.setLotteryDrawCount(0L);
            unBlockLotteryDraw.setLotteryTransCount(0L);
            unBlockLotteryDraw.setTransCount(0L);
            return (UnblockLotteryDraw) this.unBlockLotteryDrawRepository.save(unBlockLotteryDraw);
        }
        return unBlockLotteryDraw;
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateLotterDrawByMemberId(final Long memberId, final Long lotteryDrawCount) {
        UnblockLotteryDraw draw = this.unBlockLotteryDrawRepository.findByMemberId(memberId);
        int num = 0;
        if (null == draw) {
            draw = new UnblockLotteryDraw();
            draw.setCreateTime(new Date());
            draw.setMemberId(memberId);
            draw.setLotteryDrawCount(0L);
            draw.setLotteryTransCount(0L);
            draw.setTransCount(0L);
            draw = (UnblockLotteryDraw) this.unBlockLotteryDrawRepository.saveAndFlush(draw);
        } else {
            num = this.unBlockLotteryDrawRepository.updateCount(memberId, lotteryDrawCount, new Date());
        }
        if (null != draw || num > 0) {
            final UnblockLotteryIncreasedRecord record = new UnblockLotteryIncreasedRecord();
            record.setMemberId(memberId);
            record.setLotteryAddType(LotteryAddType.WEBADD);
            record.setBeforeCount(draw.getLotteryDrawCount());
            record.setAfterCount(lotteryDrawCount);
            record.setAddCount(lotteryDrawCount - draw.getLotteryDrawCount());
            record.setCreateTime(new Date());
            record.setTime(System.currentTimeMillis());
            this.unblockLotteryIncreasedRecordService.insert(record);
            return 1;
        }
        return 0;
    }

    public void save(final UnblockLotteryDraw draw) {
        this.unBlockLotteryDrawRepository.save(draw);
    }

    public Page<UnblockLotteryDraw> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<UnblockLotteryDraw> page = (Page<UnblockLotteryDraw>) this.unBlockLotteryDrawRepository.findAll(predicate, pageable);
        if (page != null) {
            for (final UnblockLotteryDraw data : page.getContent()) {
                final Member member = this.memberService.findOne(data.getMemberId());
                if (null != member) {
                    data.setUsername(member.getUsername());
                    data.setMobilePhone(member.getMobilePhone());
                    data.setEmail(member.getEmail());
                }
                data.setLotteryOverCount(this.unBlockLotteryRecordService.countByMemberId(data.getMemberId()));
                data.setLotteryBuyCount(this.unblockLotteryIncreasedRecordService.countAddCountByMemberIdAndType(data.getMemberId(), LotteryAddType.BUYADD.getOrdinal()));
            }
        }
        return page;
    }

    public int updateCountInfoById(final Long id, final Long transCount, final Long lotteryTransCount, final Long lotteryDrawCount, final Date updateTime) {
        return this.unBlockLotteryDrawRepository.updateCountInfoById(id, transCount, lotteryTransCount, lotteryDrawCount, updateTime);
    }

    static {
        log = LoggerFactory.getLogger((Class) UnBlockLotteryDrawService.class);
    }
}
