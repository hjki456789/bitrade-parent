package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface ContractNodeRepository extends JpaRepository<ContractNode, String>, JpaSpecificationExecutor<ContractNode>, QueryDslPredicateExecutor<ContractNode>
{
    @Transactional
    @Modifying
    @Query("update ContractNode set enable = :enable where id =:id")
    int updateEnable(@Param("id") final String p0, @Param("enable") final Integer p1);
    
    @Query(value = "select * from contract_node where member_id = :memberId limit 1", nativeQuery = true)
    ContractNode findByMemberId(@Param("memberId") final Long p0);
    
    @Query(value = "select * from contract_node where member_status in (:memberStatuses)", nativeQuery = true)
    List<ContractNode> findListByMemberStatuses(@Param("memberStatuses") final List<Integer> p0);
    
    @Transactional
    @Modifying
    @Query(value = "delete from contract_node where member_id =:memberId", nativeQuery = true)
    int deleteByMemberId(@Param("memberId") final Long p0);
}
