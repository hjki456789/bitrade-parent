package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface ContractCoinNodeRepository extends JpaRepository<ContractCoinNode, String>, JpaSpecificationExecutor<ContractCoinNode>, QuerydslPredicateExecutor<ContractCoinNode>
{
    @Query(value = "select * from contract_coin_node where coin_id = :coinId and node_id = :nodeId limit 1", nativeQuery = true)
    ContractCoinNode getByCoinIdAndNodeId(@Param("coinId") final String p0, @Param("nodeId") final String p1);

    @Transactional
    @Modifying
    @Query("update ContractCoinNode set enable = :enable where id =:id")
    int updateEnable(@Param("id") final String p0, @Param("enable") final Integer p1);

    @Transactional
    @Modifying
    @Query(value = "delete from contract_coin_node where coin_id =:coinId", nativeQuery = true)
    int deteleByCoinId(@Param("coinId") final String p0);

    @Transactional
    @Modifying
    @Query(value = "delete from contract_coin_node where node_id =:nodeId", nativeQuery = true)
    int deteleByNodeId(@Param("nodeId") final String p0);
}
