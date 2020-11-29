package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface UnblockRobotTicketRepository extends JpaRepository<UnblockRobotTicket, String>, JpaSpecificationExecutor<UnblockRobotTicket>, QuerydslPredicateExecutor<UnblockRobotTicket>
{
    @Transactional
    @Modifying
    @Query(value = "insert into unblock_robot_ticket(id,status,effective_day,create_time)  values(:id,:status,:effectiveDay,:createTime)", nativeQuery = true)
    int insert(@Param("id") final String p0, @Param("status") final int p1, @Param("effectiveDay") final int p2, @Param("createTime") final Date p3);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE UnblockRobotTicket ticket SET  ticket.memberId=:memberId, ticket.useTime=:useDate, ticket.status=1 WHERE ticket.id=:ticketId AND ticket.status=:status")
    int updateTicketUsed(@Param("ticketId") final String p0, @Param("status") final int p1, @Param("memberId") final long p2, @Param("useDate") final Date p3);
}
