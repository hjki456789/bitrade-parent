package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.entity.unbolck.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface UnblockExchangeSymbolRepository extends JpaRepository<UnblockExchangeSymbol, String>, JpaSpecificationExecutor<UnblockExchangeSymbol>, QuerydslPredicateExecutor<UnblockExchangeSymbol>
{
    @Transactional
    @Modifying
    @Query(value = "insert into unblock_exchange_symbol(symbol,single_round_release_rate,day_max_release_rate,day_max_release_amount,start_price,price_rise_rule,round_interval_price,single_day_round_max,round_limit_key,round_limit_value,price_limit,whitelist_member_id,update_time,create_time,version) values(:symbol,:singleRoundReleaseRate,:dayMaxReleaseRate,:dayMaxReleaseAmount,:startPrice,:priceRiseRule,:roundIntervalPrice,:singleDayRoundMax,:roundLimitKey,:roundLimitValue,:priceLimit,:whitelistMemberId,:updateTime,:createTime,:version)", nativeQuery = true)
    int insert(@Param("symbol") final String p0, @Param("singleRoundReleaseRate") final BigDecimal p1, @Param("dayMaxReleaseRate") final BigDecimal p2, @Param("dayMaxReleaseAmount") final BigDecimal p3, @Param("startPrice") final BigDecimal p4, @Param("priceRiseRule") final BigDecimal p5, @Param("roundIntervalPrice") final BigDecimal p6, @Param("singleDayRoundMax") final int p7, @Param("roundLimitKey") final String p8, @Param("roundLimitValue") final String p9, @Param("priceLimit") final BigDecimal p10, @Param("whitelistMemberId") final String p11, @Param("createTime") final Date p12, @Param("updateTime") final Date p13, @Param("version") final Long p14);

    @Transactional
    @Modifying
    @Query(value = "update unblock_exchange_symbol set single_round_release_rate = :singleRoundReleaseRate,day_max_release_rate = :dayMaxReleaseRate,day_max_release_amount = :dayMaxReleaseAmount,start_price = :startPrice,price_rise_rule = :priceRiseRule,round_interval_price = :roundIntervalPrice,single_day_round_max = :singleDayRoundMax,round_limit_key = :roundLimitKey,round_limit_value = :roundLimitValue,price_limit = :priceLimit,whitelist_member_id = :whitelistMemberId,update_time = :updateTime,version = version + 1 where symbol = :symbol and version = :version ", nativeQuery = true)
    int update(@Param("symbol") final String p0, @Param("singleRoundReleaseRate") final BigDecimal p1, @Param("dayMaxReleaseRate") final BigDecimal p2, @Param("dayMaxReleaseAmount") final BigDecimal p3, @Param("startPrice") final BigDecimal p4, @Param("priceRiseRule") final BigDecimal p5, @Param("roundIntervalPrice") final BigDecimal p6, @Param("singleDayRoundMax") final int p7, @Param("roundLimitKey") final String p8, @Param("roundLimitValue") final String p9, @Param("priceLimit") final BigDecimal p10, @Param("whitelistMemberId") final String p11, @Param("updateTime") final Date p12, @Param("version") final Long p13);

    @Transactional
    @Modifying
    @Query(value = "delete from unblock_exchange_symbol where symbol = :symbol", nativeQuery = true)
    int deleteBySymbol(@Param("symbol") final String p0);
}
