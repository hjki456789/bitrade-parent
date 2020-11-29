package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import java.math.*;

public interface DepositRecommendProfitInfoDao extends BaseDao<DepositRecommendProfitInfo>
{
    @Query(value = "select * from deposit_recommend_profit_info a where a.member_id =:memberId and a.sequence < :lastSequence order by a.sequence desc limit 10", nativeQuery = true)
    List<DepositRecommendProfitInfo> getDepositRecommendProfitInfoListByMemberId(@Param("memberId") final Long p0, @Param("lastSequence") final Long p1);
    
    @Query(value = "select ifnull(sum(a.amount),0) from deposit_recommend_profit_info a where a.member_id =:memberId", nativeQuery = true)
    BigDecimal sumProfitByMemberId(@Param("memberId") final Long p0);
}
