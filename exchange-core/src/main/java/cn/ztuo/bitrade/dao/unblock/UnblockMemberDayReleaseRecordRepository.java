package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.entity.unbolck.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface UnblockMemberDayReleaseRecordRepository extends JpaRepository<UnblockMemberDayReleaseRecord, Long>, JpaSpecificationExecutor<UnblockMemberDayReleaseRecord>, QuerydslPredicateExecutor<UnblockMemberDayReleaseRecord>
{
}
