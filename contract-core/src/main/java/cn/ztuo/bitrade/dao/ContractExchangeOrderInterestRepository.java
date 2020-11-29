package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import org.springframework.data.jpa.repository.*;

public interface ContractExchangeOrderInterestRepository extends JpaRepository<ContractExchangeOrderInterest, Long>, JpaSpecificationExecutor<ContractExchangeOrderInterest>, QueryDslPredicateExecutor<ContractExchangeOrderInterest>
{
    @Query(value = "select sum(balance) from contract_exchange_order_interest where order_id = :orderId", nativeQuery = true)
    BigDecimal sumInterestBalanceByOrderId(@Param("orderId") final Long p0);
    
    @Query(value = "SELECT sum(a.balance) from contract_exchange_order_interest a left join contract_exchange_order b on a.order_id = b.id WHERE b.member_id=:memberId AND b.wallet_type =:walletType AND b.status=:status ", nativeQuery = true)
    BigDecimal sumInterestFeeByMemberId(@Param("memberId") final long p0, @Param("status") final int p1, @Param("walletType") final int p2);
}
