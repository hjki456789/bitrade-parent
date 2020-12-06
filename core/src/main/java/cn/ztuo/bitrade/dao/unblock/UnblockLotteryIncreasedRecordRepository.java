package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

public interface UnblockLotteryIncreasedRecordRepository extends BaseDao<UnblockLotteryIncreasedRecord> {
    @Query(value = "select sum(add_count) from unblock_lottery_increased_record  where member_id =:memberId and lottery_add_type = :lotteryAddType", nativeQuery = true)
    Long countAddCountByMemberIdAndType(@Param("memberId") final Long p0, @Param("lotteryAddType") final int p1);
}
