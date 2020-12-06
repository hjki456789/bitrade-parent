package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import java.math.*;

public interface FeeStasticRepository extends BaseDao<FeeStastic> {
    @Query(value = "select sum(bonus_amount) as 'amount',unit from fee_stastic  where sequence >= :startSeq and sequence <= :endSeq group by unit", nativeQuery = true)
    List<Object[]> sumAmountBySeq(@Param("startSeq") final long p0, @Param("endSeq") final long p1);

    @Query(value = "select sum(bonus_amount) as 'amount',unit from fee_stastic  where unit = :unit and sequence >= :startSeq and sequence <= :endSeq group by unit", nativeQuery = true)
    List<Object[]> sumAmountByUnitAndSeq(@Param("unit") final String p0, @Param("startSeq") final long p1, @Param("endSeq") final long p2);

    @Query(value = "select sum(a.bonus_amount)  FROM fee_stastic a  WHERE a.unit = \"USDT\"  AND a.symbol IN (:symbols)", nativeQuery = true)
    BigDecimal sumBonusAmount(@Param("symbols") final List<String> p0);
}
