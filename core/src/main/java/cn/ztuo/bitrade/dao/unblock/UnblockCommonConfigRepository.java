package cn.ztuo.bitrade.dao.unblock;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.unblock.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface UnblockCommonConfigRepository extends BaseDao<UnblockCommonConfig>
{
    @Transactional
    @Modifying
    @Query(value = "update unblock_common_config set value = :value,order_id = :orderId where id = :id ", nativeQuery = true)
    int update(@Param("id") final Long p0, @Param("value") final String p1, @Param("orderId") final Integer p2);
}
