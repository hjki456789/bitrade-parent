package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import org.springframework.data.jpa.repository.*;

public interface CommunityMemberFollowInfoRepository extends JpaRepository<CommunityMemberFollowInfo, Long>, JpaSpecificationExecutor<CommunityMemberFollowInfo>, QuerydslPredicateExecutor<CommunityMemberFollowInfo>
{
    CommunityMemberFollowInfo findByMemberIdAndCoinIdAndLeaderMemberId(final Long p0, final String p1, final Long p2);

    @Query(value = "select sum(total_amount) from community_member_follow_info where member_id = :memberId and coin_id=:coinId", nativeQuery = true)
    BigDecimal sumMemberFollowTotalAmountByCoin(@Param("memberId") final Long p0, @Param("coinId") final String p1);

    @Query(value = "select sum(total_profit) from community_member_follow_info where member_id = :memberId and coin_id=:coinId", nativeQuery = true)
    BigDecimal sumMemberFollowTotalProfitByCoin(@Param("memberId") final Long p0, @Param("coinId") final String p1);

    @Modifying
    @Query("update CommunityMemberFollowInfo set status=:status,version=version+1 where id=:id and version=:version")
    int updateFollowStatus(@Param("id") final Long p0, @Param("status") final CommunityMemberFollowInfoStatus p1, @Param("version") final Long p2);
}
