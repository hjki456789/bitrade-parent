package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;
import cn.ztuo.bitrade.enums.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

@Repository
public interface ContractRobotPinStrategyDao extends BaseDao<ContractRobotPinStrategy>
{
    @Query(value = "SELECT a.* FROM contract_robot_pin_strategy a WHERE  a.symbol=:symbol  AND a.start_effect_time<=:sequence AND a.status=1  ORDER BY start_effect_time ASC  LIMIT 1", nativeQuery = true)
    ContractRobotPinStrategy getCurrentEffectiveStrategy(@Param("symbol") final String p0, @Param("sequence") final long p1);

    @Transactional
    @Modifying
    @Query("update ContractRobotPinStrategy set status=:status,updateTime=:updateTime where id=:id")
    int updateStatus(@Param("id") final Long p0, @Param("status") final ContractRobotPinStrategyStatus p1, @Param("updateTime") final Date p2);
}
