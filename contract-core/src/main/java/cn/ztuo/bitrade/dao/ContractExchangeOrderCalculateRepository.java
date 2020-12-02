package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface ContractExchangeOrderCalculateRepository extends JpaRepository<ContractExchangeOrderCalculate, Long>, JpaSpecificationExecutor<ContractExchangeOrderCalculate>, QuerydslPredicateExecutor<ContractExchangeOrderCalculate>
{
    @Transactional
    @Modifying
    @Query("update ContractExchangeOrderCalculate set symbolPrice=:symbolPrice,profit=:profit,rate=:rate,version=version+1 where orderId =:orderId and version=:version")
    Integer updateProfit(@Param("orderId") final long p0, @Param("symbolPrice") final BigDecimal p1, @Param("profit") final BigDecimal p2, @Param("rate") final BigDecimal p3, @Param("version") final Long p4);

    @Query(value = "select sum(a.profit) from contract_exchange_order_calculate a left join contract_exchange_order b on a.order_id = b.id where b.member_id =:memberId and a.update_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal getCalculateProfit(@Param("memberId") final Long p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);

    @Query(value = "select sum(a.profit) from contract_exchange_order_calculate a left join contract_exchange_order b on a.order_id = b.id where b.member_id in (:memberIds) and a.update_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal sumMembersCalculateProfit(@Param("memberIds") final List<Long> p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);

    @Query(value = "select sum(a.profit) from contract_exchange_order_calculate a left join contract_exchange_order b on a.order_id = b.id where b.member_id =:memberId and b.wallet_type =:walletType and b.coin_id =:coin and b.status=:status", nativeQuery = true)
    BigDecimal getMemberCalculateProfit(@Param("memberId") final Long p0, @Param("coin") final String p1, @Param("status") final int p2, @Param("walletType") final int p3);

    @Query(value = "select a.* from contract_exchange_order_calculate a left join contract_exchange_order b on a.order_id = b.id where b.member_id =:memberId and b.wallet_type =:walletType and b.coin_id =:coin and b.status=:status", nativeQuery = true)
    List<ContractExchangeOrderCalculate> findAllByMemberId(@Param("memberId") final Long p0, @Param("coin") final String p1, @Param("status") final int p2, @Param("walletType") final int p3);
}
