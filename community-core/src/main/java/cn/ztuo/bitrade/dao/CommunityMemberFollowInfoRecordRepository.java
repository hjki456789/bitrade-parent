package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface CommunityMemberFollowInfoRecordRepository extends JpaRepository<CommunityMemberFollowInfoRecord, Long>, JpaSpecificationExecutor<CommunityMemberFollowInfoRecord>, QuerydslPredicateExecutor<CommunityMemberFollowInfoRecord>
{
}
