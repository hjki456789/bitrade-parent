package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import java.io.*;
import org.slf4j.*;

@Service
public class MemberConvertTransactionService extends BaseService
{
    @Autowired
    private MemberConvertTransactionDao convertTransactionDao;

    public MemberConvertTransaction save(final MemberConvertTransaction transaction) {
        return this.convertTransactionDao.saveAndFlush(transaction);
    }

    @Override
    public List<MemberConvertTransaction> findAll() {
        return this.convertTransactionDao.findAll();
    }

    public MemberConvertTransaction findOne(final Long id) {
        return this.convertTransactionDao.getOne(id);
    }

    public void save(final List<MemberConvertTransaction> list) {
        this.convertTransactionDao.saveAll(list);
    }

    public List<MemberConvertTransaction> findAllByCreateTime(final String beginDate, final String endDate) {
        return this.convertTransactionDao.findAllByCreateTime(beginDate, endDate);
    }

    public List<MemberConvertTransaction> findListBySequence(final Long memberId, Long sequence) {
        if (sequence == null || sequence == 0L) {
            sequence = System.currentTimeMillis();
        }
        return this.convertTransactionDao.findListBySequence(memberId, sequence);
    }
}
