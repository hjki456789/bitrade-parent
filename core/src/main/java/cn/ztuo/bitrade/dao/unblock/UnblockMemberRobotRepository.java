package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import java.util.*;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.*;

public interface UnblockMemberRobotRepository extends BaseDao<UnblockMemberRobot> {
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("UPDATE  UnblockMemberRobot robot set robot.status=:status,robot.updateTime=:updateTime,robot.version=robot.version+1  where  robot.id=:id  and robot.version=:version")
    int updateStatus(@Param("id") final Long p0, @Param("status") final int p1, @Param("version") final Long p2, @Param("updateTime") final Date p3);

    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("UPDATE  UnblockMemberRobot robot set robot.expireTime=:expireTime,robot.updateTime=:updateTime,robot.version=robot.version+1  where  robot.id=:id  and robot.version=:version")
    int updateMemberRobotExpireTime(@Param("id") final long p0, @Param("expireTime") final Date p1, @Param("updateTime") final Date p2, @Param("version") final Long p3);

    @Query(value = "SELECT a.* FROM unblock_member_robot a WHERE  a.member_id=:memberId AND a.symbol=:symbol LIMIT 1", nativeQuery = true)
    UnblockMemberRobot findByMemberIdAndSymbol(@Param("memberId") final long p0, @Param("symbol") final String p1);
}
