package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface ContractExchangeOrderCloseRepository extends JpaRepository<ContractExchangeOrderClose, Long>, JpaSpecificationExecutor<ContractExchangeOrderClose>, QueryDslPredicateExecutor<ContractExchangeOrderClose>
{
    @Query(value = "select sum(a.close_profit_or_loss) from contract_exchange_order_close a left join contract_exchange_order b on a.order_id = b.id where b.member_id =:memberId and a.create_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal getMemberProfitLoss(@Param("memberId") final Long p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);
    
    @Query(value = "select sum(a.close_profit_or_loss) from contract_exchange_order_close a left join contract_exchange_order b on a.order_id = b.id where b.member_id =:memberId", nativeQuery = true)
    BigDecimal sumMemberTotalProfitLoss(@Param("memberId") final Long p0);
    
    @Query(value = "select sum(a.close_profit_or_loss) from contract_exchange_order_close a left join contract_exchange_order b on a.order_id = b.id where b.member_id in (:memberIds) and a.create_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal sumMembersProfitLoss(@Param("memberIds") final List<Long> p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);
    
    @Query(value = "select sum(a.close_profit_or_loss) from contract_exchange_order_close a left join contract_exchange_order b on a.order_id = b.id where b.member_id in (:memberIds)", nativeQuery = true)
    BigDecimal sumMembersTotalProfitLoss(@Param("memberIds") final List<Long> p0);
    
    @Query(value = "select avg(a.profit_or_loss) from contract_exchange_order_close a left join contract_exchange_order b on a.order_id = b.id where b.member_id =:memberId and a.create_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal sumMembersProfitLossRate(@Param("memberId") final Long p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);
    
    @Query(value = "select min(a.profit_or_loss) from contract_exchange_order_close a  left join contract_exchange_order b on a.order_id = b.id  where b.member_id =:memberId and a.create_time between :startDate and :endDate", nativeQuery = true)
    BigDecimal getMinProfitLossRate(@Param("memberId") final Long p0, @Param("startDate") final Date p1, @Param("endDate") final Date p2);
}
