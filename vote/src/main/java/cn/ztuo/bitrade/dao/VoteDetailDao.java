package cn.ztuo.bitrade.dao;

import org.springframework.data.jpa.repository.*;
import cn.ztuo.bitrade.entity.*;

import java.util.*;

public interface VoteDetailDao extends JpaRepository<VoteDetail, Long>, JpaSpecificationExecutor<VoteDetail> {
    List<VoteDetail> findAllByUserIdAndVote(final long p0, final Vote p1);
}
