package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface ContractWalletExplodeRecordRepository extends JpaRepository<ContractWalletExplodeRecord, Long>, JpaSpecificationExecutor<ContractWalletExplodeRecord>, QueryDslPredicateExecutor<ContractWalletExplodeRecord>
{
}
