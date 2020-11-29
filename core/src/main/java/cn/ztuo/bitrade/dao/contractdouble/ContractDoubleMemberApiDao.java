package cn.ztuo.bitrade.dao.contractdouble;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.contractdouble.*;
import org.springframework.data.repository.query.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

public interface ContractDoubleMemberApiDao extends BaseDao<ContractDoubleMemberApi>
{
    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberApi api SET api.ifDefault=:ifDefault WHERE api.memberId=:memberId")
    int updateApiIfDefault(@Param("memberId") final long p0, @Param("ifDefault") final int p1);

    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberApi api SET api.ifDefault=1 WHERE api.id=:id")
    int updateApiDefault(@Param("id") final Long p0);

    @Transactional
    @Modifying
    @Query("UPDATE ContractDoubleMemberApi api SET api.deleteFlag=1 WHERE api.id=:id")
    int deleteApi(@Param("id") final Long p0);
}
