package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.unblock.*;
import com.querydsl.core.types.*;
import cn.ztuo.bitrade.entity.unblock.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.domain.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;

import java.util.*;

@Service
public class UnblockMemberRewardService {
    @Autowired
    private MemberWalletDao memberWalletDao;
    @Autowired
    private UnblockMemberRewardRepository unblockMemberRewardRepository;

    public Page<UnblockMemberReward> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<UnblockMemberReward> page = (Page<UnblockMemberReward>) this.unblockMemberRewardRepository.findAll(predicate, pageable);
        if (page != null) {
            for (UnblockMemberReward data : page.getContent()) {
                MemberWallet memberWallet = this.memberWalletDao.findByCoinAndMemberId(new Coin(data.getCoin()), data.getMemberId());
                if (memberWallet != null) {
                    data.setBalance(memberWallet.getBalance());
                }
            }
        }
        return page;
    }

    public Page<UnblockMemberReward> getRewardRecordList(final long memberId, final Long uid, final String coin, final int pageNum, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, orders);
        final Criteria<UnblockMemberReward> specification = new Criteria<UnblockMemberReward>();
        if (StringUtils.isNotEmpty((CharSequence) coin)) {
            specification.add(Restrictions.eq("coin", coin, true));
        }
        if (null != uid && uid > 0L) {
            specification.add(Restrictions.eq("originMemberId", uid, true));
        }
        specification.add(Restrictions.eq("memberId", memberId, true));
        return (Page<UnblockMemberReward>) this.unblockMemberRewardRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public List<Object[]> statisticsMemberRewards(final long memberId, final Long uid) {
        if (null != uid && uid > 0L) {
            return this.unblockMemberRewardRepository.statisticsMemberRewardsAndUid(memberId, uid);
        }
        return this.unblockMemberRewardRepository.statisticsMemberRewards(memberId);
    }

    public void save(final UnblockMemberReward unblockMemberReward) {
        this.unblockMemberRewardRepository.save(unblockMemberReward);
    }
}
