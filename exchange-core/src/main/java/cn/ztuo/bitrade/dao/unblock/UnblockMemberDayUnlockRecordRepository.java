package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.entity.unbolck.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface UnblockMemberDayUnlockRecordRepository extends JpaRepository<UnblockMemberDayUnlockRecord, Long>, JpaSpecificationExecutor<UnblockMemberDayUnlockRecord>, QuerydslPredicateExecutor<UnblockMemberDayUnlockRecord>
{
    @Query("select record from UnblockMemberDayUnlockRecord record where record.status=0 and record.sequence>=:sequence")
    List<UnblockMemberDayUnlockRecord> findListByTimeAndLimit(@Param("sequence") final Long p0);

    @Modifying
    @Query("update UnblockMemberDayUnlockRecord set status=:status,releaseTime=:releaseTime where id=:id")
    int updateStatus(@Param("id") final Long p0, @Param("status") final int p1, @Param("releaseTime") final Date p2);
}
