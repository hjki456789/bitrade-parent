package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import java.util.*;

import org.springframework.data.jpa.repository.*;

public interface UnblockMemberRewardRepository extends BaseDao<UnblockMemberReward> {
    @Query(value = "SELECT a.coin AS \"coin\",IFNULL(SUM(a.amount),0) AS \"amount\"  FROM unblock_member_reward a  WHERE a.member_id=:memberId  AND a.coin=\"USDT\"  GROUP BY a.coin ", nativeQuery = true)
    List<Object[]> statisticsMemberRewards(@Param("memberId") final long p0);

    @Query(value = "SELECT a.coin AS \"coin\",IFNULL(SUM(a.amount),0) AS \"amount\"  FROM unblock_member_reward a  WHERE a.member_id=:memberId  AND a.coin=\"USDT\"  AND a.origin_member_id=:uid GROUP BY a.coin ", nativeQuery = true)
    List<Object[]> statisticsMemberRewardsAndUid(@Param("memberId") final long p0, @Param("uid") final Long p1);
}
