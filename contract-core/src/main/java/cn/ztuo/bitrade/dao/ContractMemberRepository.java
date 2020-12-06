package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;

import javax.transaction.*;

import org.springframework.data.jpa.repository.*;

import java.math.*;

public interface ContractMemberRepository extends JpaRepository<ContractMember, String>, JpaSpecificationExecutor<ContractMember>, QuerydslPredicateExecutor<ContractMember> {
    @Query(value = "select * from contract_member where member_id =:memberId", nativeQuery = true)
    ContractMember findByMemberId(@Param("memberId") final String p0);

    @Transactional
    @Modifying
    @Query("update ContractMember set if_proxy = :ifProxy,version = version+1 where member_id =:memberId and version = :version")
    int updateIfProxy(@Param("memberId") final String p0, @Param("ifProxy") final Integer p1, @Param("version") final Long p2);

    @Transactional
    @Modifying
    @Query("update ContractMember set contract_rebate_rate = :contractRebateRate,spot_rebate_rate = :spotRebateRate,version = version+1 where member_id =:memberId and version = :version")
    int updateRebateRate(@Param("memberId") final String p0, @Param("contractRebateRate") final BigDecimal p1, @Param("spotRebateRate") final BigDecimal p2, @Param("version") final Long p3);

    @Transactional
    @Modifying
    @Query("update ContractMember set proxy_id = :proxyId,version = version+1 where member_id =:memberId and version = :version")
    int updateProxyId(@Param("memberId") final Long p0, @Param("proxyId") final Long p1, @Param("version") final Long p2);

    @Transactional
    @Modifying
    @Query("update ContractMember set status = :status,version = version+1 where member_id =:memberId and version = :version")
    int updateStatus(@Param("memberId") final Long p0, @Param("status") final Integer p1, @Param("version") final Long p2);
}
