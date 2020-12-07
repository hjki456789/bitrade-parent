package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import org.springframework.data.jpa.repository.*;

public interface CommunityMemberTraderApplyRepository extends JpaRepository<CommunityMemberTraderApply, String>, JpaSpecificationExecutor<CommunityMemberTraderApply>, QuerydslPredicateExecutor<CommunityMemberTraderApply>
{
    @Modifying
    @Query("update CommunityMemberTraderApply set realName=:realName,cardId=:cardId,phone=:phone, status=:status where memberId=:id")
    int update(@Param("id") final Long p0, @Param("realName") final String p1, @Param("cardId") final String p2, @Param("phone") final String p3, @Param("status") final CommunityMemberTraderApplyStatus p4);
}
