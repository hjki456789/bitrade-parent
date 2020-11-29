package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface ContractMemberStatusRecordRepository extends JpaRepository<ContractMemberStatusRecord, Long>, JpaSpecificationExecutor<ContractMemberStatusRecord>, QueryDslPredicateExecutor<ContractMemberStatusRecord>
{
}
