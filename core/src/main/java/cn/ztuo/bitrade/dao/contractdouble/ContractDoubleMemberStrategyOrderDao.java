package cn.ztuo.bitrade.dao.contractdouble;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.contractdouble.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import java.util.*;
import javax.transaction.*;

import org.springframework.data.jpa.repository.*;

import java.math.*;

public interface ContractDoubleMemberStrategyOrderDao extends BaseDao<ContractDoubleMemberStrategyOrder> {
    @Query(value = "select a.* from contract_double_member_strategy_order a  WHERE a.status=0  and a.match_status not in (5,6,7)  LIMIT 1000", nativeQuery = true)
    List<ContractDoubleMemberStrategyOrder> findAllNeedCheckOrders();

    @Query(value = "select a.* from contract_double_member_strategy_order a  WHERE a.status=0  and a.from_exchange_id=:fromExchangeId and a.match_status not in (5,6,7)  LIMIT 1000", nativeQuery = true)
    List<ContractDoubleMemberStrategyOrder> findAllNeedCheckOrders(@Param("fromExchangeId") final Integer p0);


    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberStrategyOrder strategyOrder SET  strategyOrder.matchStatus=:matchStatus, strategyOrder.updateTime=:updateTime  WHERE  strategyOrder.id=:id")
    int updateMatchStatus(@Param("id") final Long p0, @Param("matchStatus") final int p1, @Param("updateTime") final Date p2);

    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberStrategyOrder strategyOrder SET  strategyOrder.closeOrderId=:closeOrderId, strategyOrder.closeMatchStatus=:closeMatchStatus, strategyOrder.closeTime=:closeTime  WHERE  strategyOrder.id=:id")
    int updateCloseInfo(@Param("id") final Long p0, @Param("closeOrderId") final String p1, @Param("closeMatchStatus") final int p2, @Param("closeTime") final Date p3);

    @Query(value = "select a.* from contract_double_member_strategy_order a  WHERE a.status=0  and a.match_status in (5,6)  and a.close_match_status not in (5,6,7)  LIMIT 1000", nativeQuery = true)
    List<ContractDoubleMemberStrategyOrder> findAllNeedCheckCloseOrders();

    @Query(value = "select a.* from contract_double_member_strategy_order a  WHERE a.status=0  and a.from_exchange_id=:fromExchangeId and a.match_status in (5,6)  and a.close_match_status not in (5,6,7)  LIMIT 1000", nativeQuery = true)
    List<ContractDoubleMemberStrategyOrder> findAllNeedCheckCloseOrders(@Param("fromExchangeId") final Integer p0);

    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberStrategyOrder strategyOrder SET  strategyOrder.closeMatchStatus=:closeMatchStatus WHERE  strategyOrder.id=:id")
    int updateCloseMatchStatus(@Param("id") final Long p0, @Param("closeMatchStatus") final int p1);

    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberStrategyOrder strategyOrder SET  strategyOrder.marginFrozen=:marginFrozen, strategyOrder.profit=:profit WHERE  strategyOrder.id=:id")
    int updateBasicInfo(@Param("id") final Long p0, @Param("marginFrozen") final BigDecimal p1, @Param("profit") final BigDecimal p2);
}
