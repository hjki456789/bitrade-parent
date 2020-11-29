package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

@Repository
public interface ChainLastBlockDao extends BaseDao<ChainLastBlock>
{
    ChainLastBlock getOne(final Long id);

    @Query("select a from ChainLastBlock a where a.assetsName = :assetsName")
    ChainLastBlock findByAssetsName(@Param("assetsName") final String p0);

    @Modifying
    @Query("update ChainLastBlock s set s.lastBlockNum = :lastBlockNum where s.id = :id")
    int updateLastBlockNum(@Param("id") final Long p0, @Param("lastBlockNum") final Long p1);
}
