package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.repository.query.*;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import java.math.*;

public interface DepositProfitInfoDao extends BaseDao<DepositProfitInfo> {
    @Query(value = "select * from deposit_profit_info a where a.member_id =:memberId and a.sequence < :lastSequence order by a.sequence desc limit 10", nativeQuery = true)
    List<DepositProfitInfo> getDepositProfitInfoListByMemberId(@Param("memberId") final Long p0, @Param("lastSequence") final Long p1);

    @Query(value = "select * from deposit_profit_info a where a.deposit_id =:depositId and a.sequence < :lastSequence order by a.sequence desc limit 10", nativeQuery = true)
    List<DepositProfitInfo> getDepositProfitInfoListByDepositInforId(@Param("depositId") final Long p0, @Param("lastSequence") final Long p1);

    @Query(value = "select ifnull(sum(a.amount),0) from deposit_profit_info a where a.member_id =:memberId", nativeQuery = true)
    BigDecimal sumProfitByMemberId(@Param("memberId") final Long p0);

    @Query(value = "select * from deposit_profit_info a where a.deposit_id =:depositId and a.sequence > :sequence", nativeQuery = true)
    List<DepositProfitInfo> getListByDepositInfoIdAndSequence(@Param("depositId") final Long p0, @Param("sequence") final Long p1);

    @Query(value = "select ifnull(sum(a.amount),0) from deposit_profit_info a where a.deposit_id =:depositId", nativeQuery = true)
    BigDecimal sumProfitByMemberIdAndDepositId(@Param("depositId") final Long p0);

    @Query(value = "select * from deposit_profit_info a where a.sequence > :sequence", nativeQuery = true)
    List<DepositProfitInfo> getInfosBySequence(@Param("sequence") final Long p0);
}
