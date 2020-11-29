package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface DepositTypeInfoDao extends BaseDao<DepositTypeInfo>
{
    @Query("select a from DepositTypeInfo a where a.status = 1 ")
    List<DepositTypeInfo> getAllEnableList();

    @Transactional
    @Modifying
    @Query(value = "update deposit_type_info set status = :status where id = :id", nativeQuery = true)
    int updateStatus(@Param("id") final Long p0, @Param("status") final Integer p1);

    @Transactional
    @Modifying
    @Query(value = "delete from deposit_type_info where id = :id", nativeQuery = true)
    void deleteById(@Param("id") final Long p0);
}
