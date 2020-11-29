package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.unblock.*;
import java.io.*;
import java.util.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.slf4j.*;

@Service
public class UnblockMemberRobotService
{
    private static final Logger log;
    @Autowired
    private UnblockMemberRobotRepository unblockMemberRobotRepository;

    public Page<UnblockMemberRobot> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<UnblockMemberRobot>)this.unblockMemberRobotRepository.findAll(predicate, pageable);
    }

    public UnblockMemberRobot findById(final Long id) {
        return (UnblockMemberRobot)this.unblockMemberRobotRepository.getOne(id);
    }

    public int updateStatus(final Long id, final int status, final Long version, final Date updateTime) {
        return this.unblockMemberRobotRepository.updateStatus(id, status, version, updateTime);
    }

    public int updateMemberRobotExpireTime(final long id, final Date expireTime, final Date updateTime, final Long version) {
        return this.unblockMemberRobotRepository.updateMemberRobotExpireTime(id, expireTime, updateTime, version);
    }

    public List<UnblockMemberRobot> findAllByStatus(final int status) {
        final Criteria<UnblockMemberRobot> specification = new Criteria<UnblockMemberRobot>();
        specification.add(Restrictions.eq("status", status, true));
        return (List<UnblockMemberRobot>)this.unblockMemberRobotRepository.findAll((Specification)specification);
    }

    public UnblockMemberRobot findByMemberIdAndSymbol(final long memberId, final String symbol) {
        UnblockMemberRobot robot = this.unblockMemberRobotRepository.findByMemberIdAndSymbol(memberId, symbol);
        if (null == robot) {
            robot = new UnblockMemberRobot();
            robot.setMemberId(memberId);
            robot.setSymbol(symbol);
            robot.setExpireTime(null);
            robot.setStatus(0);
            robot.setUpdateTime(new Date());
            robot.setVersion(0L);
            robot = (UnblockMemberRobot)this.unblockMemberRobotRepository.saveAndFlush(robot);
        }
        return robot;
    }

    static {
        log = LoggerFactory.getLogger((Class)UnblockMemberRobotService.class);
    }
}
