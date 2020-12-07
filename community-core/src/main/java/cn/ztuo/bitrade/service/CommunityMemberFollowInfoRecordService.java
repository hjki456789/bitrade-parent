package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class CommunityMemberFollowInfoRecordService
{
    @Autowired
    private CommunityMemberFollowInfoRecordRepository communityMemberFollowInfoRecordRepository;

    public void save(final CommunityMemberFollowInfoRecord record) {
        this.communityMemberFollowInfoRecordRepository.save(record);
    }
}
