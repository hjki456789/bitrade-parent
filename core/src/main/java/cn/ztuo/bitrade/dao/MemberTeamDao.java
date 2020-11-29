package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
import java.math.*;

public interface MemberTeamDao extends BaseDao<MemberTeam>
{
    @Query(value = "select * from member_team where member_id = :memberId and lower_member_id = :lowerMemberId limit 1", nativeQuery = true)
    MemberTeam getByMemberIdAndLowerMemberId(@Param("memberId") final Long p0, @Param("lowerMemberId") final Long p1);
    
    @Query(value = "select * from member_team where member_id = :memberId", nativeQuery = true)
    List<MemberTeam> getTeamByMemberId(@Param("memberId") final Long p0);
    
    @Query(value = "select * from member_team where member_id = :memberId and generation = :generation", nativeQuery = true)
    List<MemberTeam> getTeamByMemberIdGAndGeneration(@Param("memberId") final Long p0, @Param("generation") final int p1);
    
    @Query(value = "SELECT max(generation) as 'generation',count(*) as 'teamNumber' from member_team WHERE member_id = :memberId GROUP BY member_id", nativeQuery = true)
    List<Object[]> countTeamNumber(@Param("memberId") final Long p0);
    
    @Query(value = "SELECT count(*) from member_team WHERE generation = :generation and  member_id = :memberId", nativeQuery = true)
    Long countDirectNumber(@Param("memberId") final Long p0, @Param("generation") final int p1);
    
    @Query(value = "select ifnull(sum(a.amount),0) from member_team a where a.member_id =:memberId", nativeQuery = true)
    BigDecimal getTeamAmountByMemberId(@Param("memberId") final Long p0);
    
    @Query(value = "select * from member_team where lower_member_id = :lowerMemberId", nativeQuery = true)
    List<MemberTeam> getTeamByLowerId(@Param("lowerMemberId") final Long p0);
}
