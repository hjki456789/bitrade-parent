package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.*;

public interface VoteDao extends BaseDao<Vote> {
    Vote findVoteByStatus(final BooleanEnum p0);

    Vote findFirstByOrderByIdDesc();

    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("update Vote vote set vote.status = :status where vote.status = :lastStatus")
    int turnOffAllVote(@Param("status") final BooleanEnum p0, @Param("lastStatus") final BooleanEnum p1);
}
