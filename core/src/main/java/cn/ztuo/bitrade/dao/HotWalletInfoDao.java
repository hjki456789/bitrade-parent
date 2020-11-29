package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.jpa.repository.*;

@Repository
public interface HotWalletInfoDao extends BaseDao<HotWalletInfo>
{
    HotWalletInfo getOne(final Long p0);

    @Query("select a from HotWalletInfo a where a.chainType = :chainType")
    HotWalletInfo findByChainType(@Param("chainType") final int p0);
}
