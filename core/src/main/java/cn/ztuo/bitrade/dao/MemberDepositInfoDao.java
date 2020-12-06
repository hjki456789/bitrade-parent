package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.repository.query.*;

import java.math.*;
import java.util.*;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.*;

public interface MemberDepositInfoDao extends BaseDao<MemberDepositInfo> {
    @Query("select a from MemberDepositInfo a where a.memberId =:memberId and a.sequence < :lastSequence order by a.sequence desc ")
    List<MemberDepositInfo> getDepositListByMemberId(@Param("memberId") final Long p0, @Param("lastSequence") final Long p1);

    @Query(value = "select ifnull(sum(a.amount),0) from member_deposit_info a where a.member_id =:memberId and a.status = 0", nativeQuery = true)
    BigDecimal sumAmoutByMemberId(@Param("memberId") final Long p0);

    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    @Query("update MemberDepositInfo a set a.endTime=:endTime,a.status= :status where a.id = :id ")
    int updateStatus(@Param("id") final Long p0, @Param("endTime") final Date p1, @Param("status") final int p2);

    @Query("select a from MemberDepositInfo a where a.status = 0 and a.sequence < :sequence")
    List<MemberDepositInfo> getUnFinishDepositInfoBySequence(@Param("sequence") final Long p0);

    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    @Query("update MemberDepositInfo a set a.profit= :profit where a.id = :id ")
    int updateProfit(@Param("id") final Long p0, @Param("profit") final BigDecimal p1);

    @Query("select a from MemberDepositInfo a where a.memberId =:memberId and a.depositTypeId  =:depositTypeId and a.status = 0")
    List<MemberDepositInfo> getDepositIngListByMemberId(@Param("memberId") final Long p0, @Param("depositTypeId") final Long p1);
}
