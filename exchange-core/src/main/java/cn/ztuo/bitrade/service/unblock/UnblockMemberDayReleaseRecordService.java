package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.unbolck.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;

@Service
public class UnblockMemberDayReleaseRecordService {
    @Autowired
    private UnblockMemberDayReleaseRecordRepository unblockMemberDayReleaseRecordRepository;

    public UnblockMemberDayReleaseRecord save(final UnblockMemberDayReleaseRecord releaseRecord) {
        return (UnblockMemberDayReleaseRecord) this.unblockMemberDayReleaseRecordRepository.saveAndFlush(releaseRecord);
    }

    public Page<UnblockMemberDayReleaseRecord> getReleaseList(final Long memberId, final String coin, final int pageNum, final int pageSize) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, orders);
        final Criteria<UnblockMemberDayReleaseRecord> specification = (Criteria<UnblockMemberDayReleaseRecord>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) coin)) {
            specification.add((Criterion) Restrictions.eq("coin", coin, true));
        }
        specification.add((Criterion) Restrictions.eq("memberId", memberId, true));
        return (Page<UnblockMemberDayReleaseRecord>) this.unblockMemberDayReleaseRecordRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }
}
