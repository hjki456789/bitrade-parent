package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import org.springframework.data.jpa.repository.*;
import cn.ztuo.bitrade.entity.enumconstants.*;

public interface CommunityMemberRepository extends JpaRepository<CommunityMember, Long>, JpaSpecificationExecutor<CommunityMember>, QuerydslPredicateExecutor<CommunityMember>
{
    @Modifying
    @Query("update  CommunityMember  set totalProfitRate=:totalProfitRate , threeWeekWinRate=:threeWeekWinRate ,threeWeekCallbackRate=:threeWeekCallBackRate  where memberId =:memberId")
    int updateMemberInfo(@Param("memberId") final Long p0, @Param("totalProfitRate") final BigDecimal p1, @Param("threeWeekWinRate") final BigDecimal p2, @Param("threeWeekCallBackRate") final BigDecimal p3);

    @Modifying
    @Query("update CommunityMember set sex = :sex where memberId =:memberId")
    int updateMemberSex(@Param("memberId") final Long p0, @Param("sex") final CommunityMemberSex p1);

    @Modifying
    @Query("update CommunityMember set personalIntroduction = :personalIntroduction where memberId =:memberId")
    int updateMemberPersonalIntroduction(@Param("memberId") final Long p0, @Param("personalIntroduction") final String p1);

    @Modifying
    @Query("update CommunityMember set areaName = :areaName where memberId =:memberId")
    int updateMemberAreaName(@Param("memberId") final Long p0, @Param("areaName") final String p1);

    @Modifying
    @Query("update CommunityMember set labels = :labels where memberId =:memberId")
    int updateMemberLabels(@Param("memberId") final Long p0, @Param("labels") final String p1);

    @Modifying
    @Query("update CommunityMember set status = :status where memberId =:memberId")
    int updateMemberStatus(@Param("memberId") final Long p0, @Param("status") final CommunityMemberStatus p1);

    @Query(value = "SELECT SUM(amount) FROM community_trader_profit_record WHERE member_id = :memberId and belong_day = :belongDay", nativeQuery = true)
    BigDecimal getYesterdayProfit(@Param("memberId") final Long p0, @Param("belongDay") final String p1);

    @Query(value = "SELECT SUM(amount) FROM community_trader_profit_record WHERE member_id = :memberId", nativeQuery = true)
    BigDecimal getTotalProfit(@Param("memberId") final Long p0);
}
