package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;

public interface ContractConfigRepository extends JpaRepository<ContractConfig, String>, JpaSpecificationExecutor<ContractConfig>, QueryDslPredicateExecutor<ContractConfig>
{
}
