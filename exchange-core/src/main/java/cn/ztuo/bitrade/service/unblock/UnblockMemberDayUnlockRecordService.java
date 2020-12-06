package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.unbolck.*;
import java.util.*;
import java.io.*;

@Service
public class UnblockMemberDayUnlockRecordService
{
    @Autowired
    private UnblockMemberDayUnlockRecordRepository unblockMemberDayUnlockRecordRepository;

    public UnblockMemberDayUnlockRecord save(final UnblockMemberDayUnlockRecord record) {
        return (UnblockMemberDayUnlockRecord)this.unblockMemberDayUnlockRecordRepository.saveAndFlush(record);
    }

    public List<UnblockMemberDayUnlockRecord> findListByTimeAndLimit(final Long sequence) {
        return this.unblockMemberDayUnlockRecordRepository.findListByTimeAndLimit(sequence);
    }

    public int updateStatus(final Long id, final int status, final Date releaseTime) {
        return this.unblockMemberDayUnlockRecordRepository.updateStatus(id, status, releaseTime);
    }

    public UnblockMemberDayUnlockRecord findOne(final Long unlockId) {
        return (UnblockMemberDayUnlockRecord)this.unblockMemberDayUnlockRecordRepository.getOne(unlockId);
    }
}
