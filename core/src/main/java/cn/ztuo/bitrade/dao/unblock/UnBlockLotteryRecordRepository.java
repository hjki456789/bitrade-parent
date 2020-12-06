package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

public interface UnBlockLotteryRecordRepository extends BaseDao<UnblockLotteryRecord> {
    @Query(value = "select count(*) from unblock_lottery_record where member_id =:memberId ", nativeQuery = true)
    Long countByMemberId(@Param("memberId") final Long p0);

    @Query(value = "select count(0) from unblock_lottery_record  where lottery_id =:lotteryId  AND sequence>=:startSequence", nativeQuery = true)
    long countByLotteryIdAndStartTime(@Param("lotteryId") final Long p0, @Param("startSequence") final Long p1);
}
