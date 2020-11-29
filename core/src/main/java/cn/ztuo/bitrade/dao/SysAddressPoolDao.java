package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

@Repository
public interface SysAddressPoolDao extends BaseDao<SysAddressPool>
{
    @Query(value = "select * from sys_address_pool a where a.type = :type and a.status = 0 limit 1", nativeQuery = true)
    SysAddressPool findByType(@Param("type") final int p0);
    
    @Modifying
    @Query("update SysAddressPool s set s.status = 1 where s.address = :address")
    int updateStatus(@Param("address") final String p0);
}
