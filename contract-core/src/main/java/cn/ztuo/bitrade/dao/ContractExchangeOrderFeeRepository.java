package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import java.math.*;
import org.springframework.data.jpa.repository.*;

public interface ContractExchangeOrderFeeRepository extends JpaRepository<ContractExchangeOrderFee, Long>, JpaSpecificationExecutor<ContractExchangeOrderFee>, QueryDslPredicateExecutor<ContractExchangeOrderFee>
{
    @Query(value = "select sum(a.open_fee+a.close_fee) from contract_exchange_order_fee a left join contract_exchange_order b on a.order_id = b.id where b.member_id in (:memberIds) and a.create_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal sumMembersFee(@Param("memberIds") final List<Long> p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);
    
    @Query(value = "select sum(a.open_fee+a.close_fee) from contract_exchange_order_fee a left join contract_exchange_order b on a.order_id = b.id where b.member_id in (:memberIds) ", nativeQuery = true)
    BigDecimal sumMembersTotalFee(@Param("memberIds") final List<Long> p0);
    
    @Query(value = "SELECT sum(a.open_fee) from contract_exchange_order_fee a left join contract_exchange_order b on a.order_id = b.id WHERE b.member_id=:memberId AND b.wallet_type =:walletType AND b.status=:status ", nativeQuery = true)
    BigDecimal sumOpenFeeByMemberId(@Param("memberId") final long p0, @Param("status") final int p1, @Param("walletType") final int p2);
    
    @Query(value = "SELECT sum(a.close_fee) from contract_exchange_order_fee a left join contract_exchange_order b on a.order_id = b.id WHERE b.member_id=:memberId AND b.wallet_type =:walletType AND b.status=:status ", nativeQuery = true)
    BigDecimal sumCloseFeeByMemberId(@Param("memberId") final long p0, @Param("status") final int p1, @Param("walletType") final int p2);
    
    @Query(value = "SELECT a.* from contract_exchange_order_fee a WHERE a.order_id=:orderId ", nativeQuery = true)
    ContractExchangeOrderFee findByOrderId(@Param("orderId") final long p0);
}
