package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;
import java.math.*;

@Service
public class LeaderMemberService
{
    @Autowired
    private CommunityTraderProfitRecordRepository communityTraderProfitRecordRepository;

    public Page<CommunityTraderProfitRecord> getLeaderMemberProfitDetail(final Long memberId, final int pageNo, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "sequence") });
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<CommunityTraderProfitRecord> specification = (Criteria<CommunityTraderProfitRecord>)new Criteria();
        specification.add((Criterion)Restrictions.eq("memberId", memberId, true));
        return (Page<CommunityTraderProfitRecord>)this.communityTraderProfitRecordRepository.findAll((Specification)specification, (Pageable)pageRequest);
    }

    public void save(final CommunityTraderProfitRecord communityTraderProfitRecord) {
        this.communityTraderProfitRecordRepository.saveAndFlush(communityTraderProfitRecord);
    }

    public BigDecimal sumLeaderProfit(final Long id) {
        return this.communityTraderProfitRecordRepository.sumLeaderProfit(id);
    }
}
