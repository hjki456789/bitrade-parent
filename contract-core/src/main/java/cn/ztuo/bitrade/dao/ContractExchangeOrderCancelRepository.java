package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface ContractExchangeOrderCancelRepository extends JpaRepository<ContractExchangeOrderCancel, String>, JpaSpecificationExecutor<ContractExchangeOrderCancel>, QueryDslPredicateExecutor<ContractExchangeOrderCancel>
{
}
