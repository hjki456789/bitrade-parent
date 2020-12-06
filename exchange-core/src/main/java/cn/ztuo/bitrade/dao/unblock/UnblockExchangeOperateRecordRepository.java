package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.entity.unbolck.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

import java.math.*;

public interface UnblockExchangeOperateRecordRepository extends JpaRepository<UnblockMemberOperateRecord, Long>, JpaSpecificationExecutor<UnblockMemberOperateRecord>, QuerydslPredicateExecutor<UnblockMemberOperateRecord> {
    @Query(value = "select count(0) from  unblock_member_operate_record a  left join exchange_order b on a.order_id=b.order_id where  b.status!=2 and a.member_id=:memberId and a.symbol=:symbol", nativeQuery = true)
    int countMemberRound(@Param("memberId") final Long p0, @Param("symbol") final String p1);

    @Query(value = "select count(0) from  unblock_member_operate_record a  left join exchange_order b on a.order_id=b.order_id where  b.status!=2 and a.member_id=:memberId and a.symbol=:symbol and a.sequence>=:sequence", nativeQuery = true)
    int countMemberRoundBySequence(@Param("memberId") final Long p0, @Param("symbol") final String p1, @Param("sequence") final Long p2);

    @Query(value = "select SUM(price*amount) from unblock_member_operate_record where member_id=:memberId and symbol=:symbol and sequence>=:sequence", nativeQuery = true)
    BigDecimal sumMemberTradedBySequence(@Param("memberId") final Long p0, @Param("symbol") final String p1, @Param("sequence") final Long p2);
}
