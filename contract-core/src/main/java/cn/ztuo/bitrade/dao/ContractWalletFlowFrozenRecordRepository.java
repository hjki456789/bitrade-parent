package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface ContractWalletFlowFrozenRecordRepository extends JpaRepository<ContractWalletFrozenFlowRecord, String>, JpaSpecificationExecutor<ContractWalletFrozenFlowRecord>, QueryDslPredicateExecutor<ContractWalletFrozenFlowRecord>
{
}