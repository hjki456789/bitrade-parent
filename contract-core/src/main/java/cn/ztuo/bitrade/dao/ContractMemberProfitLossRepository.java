package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;
import java.math.*;
import java.util.*;

public interface ContractMemberProfitLossRepository extends JpaRepository<ContractMemberProfitLoss, Long>, JpaSpecificationExecutor<ContractMemberProfitLoss>, QuerydslPredicateExecutor<ContractMemberProfitLoss>
{
    @Query(value = "select * from contract_member_profit_loss where member_id = :memberId and type = :type and week_start_time = :weekStartTime and week_end_time = :weekEndTime limit 1", nativeQuery = true)
    ContractMemberProfitLoss getByMemberIdAndTypeAndStartTimeAndEndTime(@Param("memberId") final Long p0, @Param("type") final Integer p1, @Param("weekStartTime") final String p2, @Param("weekEndTime") final String p3);

    @Query(value = "select * from contract_member_profit_loss where member_id = :memberId and type = :type limit 1", nativeQuery = true)
    ContractMemberProfitLoss getByMemberIdAndType(@Param("memberId") final Long p0, @Param("type") final Integer p1);

    @Query(value = "select sum(profit_loss_amount) from contract_member_profit_loss where member_id = :memberId and type = :type limit 1", nativeQuery = true)
    BigDecimal sumProfitLossByMemberIdAndType(@Param("memberId") final Long p0, @Param("type") final Integer p1);

    @Query(value = "SELECT * FROM `contract_member_profit_loss` WHERE member_id = :memberId and type in(0,1) and week_start_time = :weekStartTime order BY week_end_time desc limit 2", nativeQuery = true)
    List<ContractMemberProfitLoss> getWeekProfitLossAmount(@Param("memberId") final Long p0, @Param("weekStartTime") final String p1);

    @Query(value = "SELECT * FROM `contract_member_profit_loss` WHERE member_id = :memberId and type in(2,3) ORDER BY type desc limit 2", nativeQuery = true)
    List<ContractMemberProfitLoss> getTotalProfitLossAmount(@Param("memberId") final Long p0);
}
