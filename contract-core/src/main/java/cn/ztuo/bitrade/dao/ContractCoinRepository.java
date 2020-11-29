package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface ContractCoinRepository extends JpaRepository<ContractCoin, String>, JpaSpecificationExecutor<ContractCoin>, QueryDslPredicateExecutor<ContractCoin>
{
    @Transactional
    @Modifying
    @Query("update ContractCoin set enable = :enable where id =:id")
    int updateEnable(@Param("id") final String p0, @Param("enable") final Integer p1);
    
    @Query(value = "select * from contract_coin where symbol = :symbol limit 1", nativeQuery = true)
    ContractCoin findBySymbol(@Param("symbol") final String p0);
    
    @Query(value = "select a.symbol from contract_coin a order by a.sort ASC", nativeQuery = true)
    List<String> findAllSymbol();
}
