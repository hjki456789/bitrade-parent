package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.*;

public interface ContractDoubleDirectionProfitInfoDao extends BaseDao<ContractDoubleDirectionOrderProfitInfo>
{
    @Query(value = "select a.* from contract_double_direction_order_profit_info a where a.status =:status", nativeQuery = true)
    List<ContractDoubleDirectionOrderProfitInfo> getProfitInfoListByStatus(@Param("status") final int p0);
    
    @Transactional(rollbackFor = { Exception.class })
    @Modifying
    @Query("update ContractDoubleDirectionOrderProfitInfo a set a.remainDays=:remainDays,a.status= :status where a.id = :id ")
    int updateStatus(@Param("id") final Long p0, @Param("remainDays") final int p1, @Param("status") final int p2);
}
