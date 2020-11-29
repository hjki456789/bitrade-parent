package cn.ztuo.bitrade.dao.contractdouble;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.contractdouble.ContractDoubleExchangeConfig;
import cn.ztuo.bitrade.entity.contractstrategy.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ContractDoubleExchangeConfigDao extends JpaRepository<ContractDoubleExchangeConfig, Long>, JpaSpecificationExecutor<ContractDoubleExchangeConfig>, QuerydslPredicateExecutor<ContractStrategyConfig>
{
}
