package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class VoteDetailService
{
    @Autowired
    private VoteDetailDao voteDetailDao;

    public VoteDetail save(VoteDetail voteDetail) {
        return  voteDetailDao.save(voteDetail);
    }

    public int queryVoted(Long userId,Vote vote) {
        return this.voteDetailDao.findAllByUserIdAndVote(userId, vote).stream().map(x -> x.getVoteAmount()).reduce(Integer::sum).orElse(0);
    }
}
