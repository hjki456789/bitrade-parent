package cn.ztuo.bitrade.dao;

import org.springframework.data.jpa.repository.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;

public interface CoinCollectLogDao extends JpaRepository<CoinCollectLog, Long>, QuerydslPredicateExecutor<CoinCollectLog>
{
}
