package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import org.springframework.data.jpa.repository.*;

public interface CommunityTraderProfitRecordRepository extends JpaRepository<CommunityTraderProfitRecord, Long>, JpaSpecificationExecutor<CommunityTraderProfitRecord>, QuerydslPredicateExecutor<CommunityTraderProfitRecord>
{
    @Query(value = "SELECT SUM(amount) FROM community_trader_profit_record WHERE member_id=:id", nativeQuery = true)
    BigDecimal sumLeaderProfit(@Param("id") final Long p0);
}
