package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;

import java.math.*;
import java.util.*;
import javax.transaction.*;

import org.springframework.data.jpa.repository.*;

@Repository
public interface RobotSymbolConfigDao extends JpaRepository<RobotSymbolConfig, String>, JpaSpecificationExecutor<RobotSymbolConfig>, QuerydslPredicateExecutor<RobotSymbolConfig> {
    @Transactional
    @Modifying
    @Query(value = "insert into robot_symbol_config(symbol,base_coin_balance,trade_coin_balance,robot_order_max,from_exchange_id,from_exchange_symbol,member_id,depth_amount_down_rate,trade_amount_up_rate,buy_price_down_rate,sell_price_up_rate,fee,status,arbitrage_status,create_time,sequence,version) values(:symbol,:baseCoinBalance,:tradeCoinBalance,:robotOrderMax,:fromExchangeId,:fromExchangeSymbol,:memberId,:depthAmountDownRate,:tradeAmountUpRate,:buyPriceDownRate,:sellPriceUpRate,:fee,:status,:arbitrageStatus,:createTime,:sequence,:version)", nativeQuery = true)
    int insert(@Param("symbol") final String p0, @Param("baseCoinBalance") final BigDecimal p1, @Param("tradeCoinBalance") final BigDecimal p2, @Param("robotOrderMax") final BigDecimal p3, @Param("fromExchangeId") final Long p4, @Param("fromExchangeSymbol") final String p5, @Param("memberId") final Long p6, @Param("depthAmountDownRate") final BigDecimal p7, @Param("tradeAmountUpRate") final BigDecimal p8, @Param("buyPriceDownRate") final BigDecimal p9, @Param("sellPriceUpRate") final BigDecimal p10, @Param("fee") final BigDecimal p11, @Param("status") final Integer p12, @Param("arbitrageStatus") final Integer p13, @Param("createTime") final Date p14, @Param("sequence") final Long p15, @Param("version") final Long p16);

    @Transactional
    @Modifying
    @Query(value = "delete from robot_symbol_config where symbol = :symbol", nativeQuery = true)
    int deleteRobotSymbolConfig(@Param("symbol") final String p0);
}
