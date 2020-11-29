package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface ContractCommissionRepository extends JpaRepository<ContractCommissionInfo, String>, JpaSpecificationExecutor<ContractCommissionInfo>, QueryDslPredicateExecutor<ContractCommissionInfo>
{
    @Query(value = "select * from contract_commission_info where member_id = :memberId limit 1", nativeQuery = true)
    ContractCommissionInfo findByMemberId(@Param("memberId") final String p0);
    
    @Transactional
    @Modifying
    @Query("update ContractCommissionInfo set status = :status,sys_user = :adminId where id =:id")
    Integer updateStatus(@Param("id") final String p0, @Param("status") final Integer p1, @Param("adminId") final String p2);
}
