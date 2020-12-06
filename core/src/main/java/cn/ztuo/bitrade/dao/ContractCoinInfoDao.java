package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.constant.*;

import java.util.*;

import org.springframework.data.jpa.repository.*;

public interface ContractCoinInfoDao extends BaseDao<ContractCoinInfo> {
    ContractCoinInfo findContractCoinInfoByUnitAndStatus(final String p0, final CommonStatus p1);

    List<ContractCoinInfo> findAllByStatus(final CommonStatus p0);

    ContractCoinInfo findContractCoinInfoByUnit(final String p0);

    ContractCoinInfo findContractCoinInfoByName(final String p0);

    @Query("select distinct a.unit from ContractCoinInfo a where a.status = 0")
    List<String> findAllUnits();
}
