package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.unblock.*;
import java.io.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import org.slf4j.*;

@Service
public class UnblockRobotTicketService
{
    private static final Logger log;
    @Autowired
    private UnblockRobotTicketRepository unblockRobotTicketRepository;

    public Page<UnblockRobotTicket> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<UnblockRobotTicket>)this.unblockRobotTicketRepository.findAll(predicate, pageable);
    }

    public UnblockRobotTicket findById(final String id) {
        return (UnblockRobotTicket)this.unblockRobotTicketRepository.getOne(id);
    }

    public void insertUnblockRobotTicket(final int num, final int effectiveDay) {
        for (int i = 0; i < num; ++i) {
            final UnblockRobotTicket record = new UnblockRobotTicket();
            record.setId(UUIDUtil.getUUID());
            record.setStatus(0);
            record.setEffectiveDay(effectiveDay);
            record.setCreateTime(new Date());
            this.unblockRobotTicketRepository.insert(record.getId(), record.getStatus(), record.getEffectiveDay(), record.getCreateTime());
        }
    }

    public int updateTicketUsed(final String ticketId, final int status, final long memberId, final Date useDate) {
        return this.unblockRobotTicketRepository.updateTicketUsed(ticketId, status, memberId, useDate);
    }

    static {
        log = LoggerFactory.getLogger((Class)UnblockRobotTicketService.class);
    }
}
