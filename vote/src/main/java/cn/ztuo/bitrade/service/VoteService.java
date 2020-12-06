package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

import java.io.*;

import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.constant.*;

@Service
public class VoteService {
    @Autowired
    private VoteDao voteDao;

    public Vote findById(Long id) {
        return this.voteDao.getOne(id);
    }

    public Vote findVote() {
        return this.voteDao.findFirstByOrderByIdDesc();
    }

    public Vote save(final Vote vote) {
        return voteDao.save(vote);
    }

    public Page<Vote> findAll(final Predicate predicate, final Pageable pageable) {
        return voteDao.findAll(predicate, pageable);
    }

    public void turnOffAllVote() {
        voteDao.turnOffAllVote(BooleanEnum.IS_FALSE, BooleanEnum.IS_TRUE);
    }
}

