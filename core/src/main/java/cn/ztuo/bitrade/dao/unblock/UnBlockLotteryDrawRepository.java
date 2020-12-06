package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import java.util.*;
import javax.transaction.*;

import org.springframework.data.jpa.repository.*;

public interface UnBlockLotteryDrawRepository extends BaseDao<UnblockLotteryDraw> {
    @Query(value = "select * from unblock_lottery_draw where member_id = :memberId ", nativeQuery = true)
    UnblockLotteryDraw findByMemberId(@Param("memberId") final Long p0);

    @Transactional
    @Modifying
    @Query("update UnblockLotteryDraw set lotteryDrawCount = :lotteryDrawCount,updateTime=:updateTime where memberId =:memberId")
    int updateCount(@Param("memberId") final Long p0, @Param("lotteryDrawCount") final Long p1, @Param("updateTime") final Date p2);

    @Transactional
    @Modifying
    @Query("update UnblockLotteryDraw lottery set lottery.transCount = :transCount,lottery.lotteryTransCount = :lotteryTransCount,lottery.lotteryDrawCount = :lotteryDrawCount,lottery.updateTime=:updateTime where lottery.id =:id")
    int updateCountInfoById(@Param("id") final Long p0, @Param("transCount") final Long p1, @Param("lotteryTransCount") final Long p2, @Param("lotteryDrawCount") final Long p3, @Param("updateTime") final Date p4);
}
