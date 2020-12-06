package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

import java.util.*;
import java.io.*;

import cn.ztuo.bitrade.constant.*;
import org.springframework.transaction.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

@Service
public class ContractCoinInfoService extends BaseService {
    @Autowired
    private ContractCoinInfoDao contractCoinInfoDao;

    public ContractCoinInfo save(final ContractCoinInfo contractCoinInfo) {
        return (ContractCoinInfo) this.contractCoinInfoDao.save(contractCoinInfo);
    }

    @Override
    public List<ContractCoinInfo> findAll() {
        return (List<ContractCoinInfo>) this.contractCoinInfoDao.findAll();
    }

    public ContractCoinInfo findOne(final Long id) {
        return (ContractCoinInfo) this.contractCoinInfoDao.getOne(id);
    }

    public ContractCoinInfo findByUnit(final String unit) {
        return this.contractCoinInfoDao.findContractCoinInfoByUnit(unit);
    }

    public ContractCoinInfo findByName(final String name) {
        return this.contractCoinInfoDao.findContractCoinInfoByName(name);
    }

    public List<ContractCoinInfo> getNormalCoin() {
        return this.contractCoinInfoDao.findAllByStatus(CommonStatus.NORMAL);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deletes(final Long[] ids) {
        for (final long id : ids) {
            this.contractCoinInfoDao.deleteById(id);
        }
    }

    public Page<ContractCoinInfo> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<ContractCoinInfo>) this.contractCoinInfoDao.findAll(predicate, pageable);
    }

    public List<String> findAllUnits() {
        final List<String> list = this.contractCoinInfoDao.findAllUnits();
        return list;
    }

    public ContractCoinInfo findOtcCoinByUnitAndStatus(final String coinUnit, final CommonStatus commonStatus) {
        return this.contractCoinInfoDao.findContractCoinInfoByUnitAndStatus(coinUnit, commonStatus);
    }
}
