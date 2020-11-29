package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import java.math.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface ContractWalletRepository extends JpaRepository<ContractWallet, String>, JpaSpecificationExecutor<ContractWallet>, QueryDslPredicateExecutor<ContractWallet>
{
    @Query(value = "select * from contract_wallet where id = :id limit 1", nativeQuery = true)
    ContractWallet findById(@Param("id") final Long p0);
    
    @Query(value = "select * from contract_wallet where member_id = :memberId limit 1", nativeQuery = true)
    ContractWallet findByMemberId(@Param("memberId") final Long p0);
    
    @Query(value = "select * from contract_wallet where member_id = :memberId and coin_id = :coinId limit 1", nativeQuery = true)
    ContractWallet findByMemberIdAndCoin(@Param("memberId") final Long p0, @Param("coinId") final String p1);
    
    @Transactional
    @Modifying
    @Query("update ContractWallet set balance = :balance,frozenBalance=:frozenBalance,  virtualRechargeFrozenBalance=:virtualRechargeFrozenBalance,version=version+1  where id =:id and version=:version")
    Integer updateContractWalletBalance(@Param("balance") final BigDecimal p0, @Param("frozenBalance") final BigDecimal p1, @Param("virtualRechargeFrozenBalance") final BigDecimal p2, @Param("id") final Long p3, @Param("version") final Long p4);
    
    @Transactional
    @Modifying
    @Query("update ContractWallet set is_lock = :isLock where id =:id")
    int updateIsLock(@Param("id") final Long p0, @Param("isLock") final Integer p1);
}
