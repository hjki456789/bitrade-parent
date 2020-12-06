package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import javax.transaction.*;

import org.springframework.data.jpa.repository.*;
import cn.ztuo.bitrade.constant.*;

import java.math.*;
import java.util.*;

public interface ContractOrderRepository extends JpaRepository<ContractExchangeOrder, Long>, JpaSpecificationExecutor<ContractExchangeOrder>, QuerydslPredicateExecutor<ContractExchangeOrder> {
    @Transactional
    @Modifying
    @Query("update ContractExchangeOrder set status = :status,version=version+1 where id =:id and version=:version")
    Integer updateOrderStatus(@Param("id") final Long p0, @Param("status") final ContractOrderStatus p1, @Param("version") final Long p2);

    @Transactional
    @Modifying
    @Query("update ContractExchangeOrder set ifStopLoss = :ifStopLoss,stopLossPrice=:stopLossPrice,ifStopProfit=:ifStopProfit,stopProfitPrice=:stopProfitPrice,version=version+1 where id =:id and version=:version")
    int updateStopCondition(@Param("id") final Long p0, @Param("ifStopLoss") final BooleanEnum p1, @Param("stopLossPrice") final BigDecimal p2, @Param("ifStopProfit") final BooleanEnum p3, @Param("stopProfitPrice") final BigDecimal p4, @Param("version") final long p5);

    @Transactional
    @Modifying
    @Query("update ContractExchangeOrder set status=:status,openPrice=:openPrice,version=version+1 where id =:id and version=:version")
    int updateLimitOrderToHolding(@Param("id") final long p0, @Param("status") final ContractOrderStatus p1, @Param("openPrice") final BigDecimal p2, @Param("version") final long p3);

    @Query("select id from ContractExchangeOrder where symbol=:symbol and status=:status")
    List<Long> findAllHoldingOrderIds(@Param("symbol") final String p0, @Param("status") final ContractOrderStatus p1);

    @Query(value = "select member_id from contract_exchange_order where status=:status group by member_id", nativeQuery = true)
    List<BigInteger> findMemberIds(@Param("status") final int p0);

    @Query(value = "select id from contract_exchange_order where member_id=:memberId and coin_id=:coinId and status=:status", nativeQuery = true)
    List<Long> findMemberOrderIds(@Param("memberId") final Long p0, @Param("coinId") final String p1, @Param("status") final int p2);

    @Query(value = "select sum(amount) from contract_exchange_order where member_id = :memberId and symbol=:symbol and status=:status", nativeQuery = true)
    BigDecimal sumHoldingOrdersAmountByMemberId(@Param("memberId") final Long p0, @Param("symbol") final String p1, @Param("status") final int p2);

    @Query(value = "SELECT SUM(a.close_profit_or_loss) FROM contract_exchange_order_close a LEFT JOIN contract_exchange_order b ON a.order_id=b.id WHERE b.leader_member_id=:leaderMemberId AND a.close_profit_or_loss >0 AND a.create_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    BigDecimal sumAllAndCloseOrderByLeaderMeberId(@Param("leaderMemberId") final Long p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);

    @Query(value = "SELECT SUM(capital_amount) FROM contract_exchange_order WHERE member_id=:memberId AND leader_member_id=:leaderMemberId AND create_time BETWEEN :startDate AND :endDate", nativeQuery = true)
    BigDecimal sumAmountOneDay(@Param("memberId") final Long p0, @Param("leaderMemberId") final Long p1, @Param("startDate") final Date p2, @Param("endDate") final Date p3);

    @Query(value = "select sum(capital_amount) from contract_exchange_order where member_id = :memberId and leader_member_id=:leaderMemberId and status=:ordinal", nativeQuery = true)
    BigDecimal sumHoldingOrdersAmountByMemberIdAndLeaderMemberId(@Param("memberId") final Long p0, @Param("leaderMemberId") final Long p1, @Param("ordinal") final int p2);

    @Query(value = "select count(*) from contract_exchange_order where member_id = :memberId and sequence>:sequence and status=:status", nativeQuery = true)
    long countBySequenceAndMemberId(@Param("memberId") final Long p0, @Param("sequence") final Long p1, @Param("status") final int p2);

    @Query(value = "select a.* from contract_exchange_order a left join contract_exchange_order_close b on b.order_id = a.id where a.status >= 3 and a.status <= 9 and a.wallet_type < 2 and b.sequence > :minSequence and b.sequence <:maxSequence", nativeQuery = true)
    List<ContractExchangeOrder> getCloseOrdersBySequence(@Param("minSequence") final Long p0, @Param("maxSequence") final Long p1);
}
