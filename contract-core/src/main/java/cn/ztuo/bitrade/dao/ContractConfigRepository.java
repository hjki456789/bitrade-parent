package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ContractConfigRepository extends JpaRepository<ContractConfig, String>, JpaSpecificationExecutor<ContractConfig>, QuerydslPredicateExecutor<ContractConfig>
{
}
