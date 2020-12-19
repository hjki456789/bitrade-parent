package cn.ztuo.bitrade.dao.contractstrategy;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.contractstrategy.*;

import javax.transaction.*;

import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

public interface ContractStrategyConfigRepository extends BaseDao<ContractStrategyConfig> {
    @Transactional
    void deleteById(final Long p0);

    @Query(value = "select a.* from contract_strategy_config a where a.status=:status and a.symbol = :symbol limit 1", nativeQuery = true)
    ContractStrategyConfig findBySymbolAndStatus(@Param("symbol") final String p0, @Param("status") final int p1);
}
