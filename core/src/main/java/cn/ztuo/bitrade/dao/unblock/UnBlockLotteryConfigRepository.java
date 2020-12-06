package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import java.math.*;
import javax.transaction.*;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UnBlockLotteryConfigRepository extends BaseDao<UnblockLotteryConfig> {
    @Transactional
    @Modifying
    @Query("update UnblockLotteryConfig set lotteryLevel = :lotteryLevel, lotteryRate=:lotteryRate,  lotteryInstruction=:lotteryInstruction, coin=:coin,  amount=:amount, insuranceRate=:insuranceRate,  dayWinnerNumLimit=:dayWinnerNumLimit where id =:id")
    int update(@Param("id") final Long p0, @Param("lotteryLevel") final String p1, @Param("lotteryRate") final BigDecimal p2, @Param("lotteryInstruction") final String p3, @Param("coin") final String p4, @Param("amount") final BigDecimal p5, @Param("insuranceRate") final BigDecimal p6, @Param("dayWinnerNumLimit") final int p7);

    @Query(value = "select * from unblock_lottery_config limit 1", nativeQuery = true)
    UnblockLotteryConfig getOne();

    @Transactional
    @Modifying
    @Query("update UnblockLotteryConfig set lotteryRule = :lotteryRule,buyPrice=:buyPrice ")
    int updateRuleAndPrice(@Param("lotteryRule") final String p0, @Param("buyPrice") final BigDecimal p1);

    @Query(value = "select * from unblock_lottery_config where lottery_rate>0 order by lottery_rate asc", nativeQuery = true)
    List<UnblockLotteryConfig> getAllLotteryConfigByRate();
}
