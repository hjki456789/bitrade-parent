package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import java.io.*;

@Service
public class DepositTypeInfoService extends BaseService
{
    @Autowired
    private DepositTypeInfoDao depositTypeInfoDao;

    public DepositTypeInfo save(final DepositTypeInfo depositTypeInfo) {
        return (DepositTypeInfo)this.depositTypeInfoDao.save(depositTypeInfo);
    }

    public List<DepositTypeInfo> findAllByEnableList() {
        return this.depositTypeInfoDao.getAllEnableList();
    }

    @Override
    public List<DepositTypeInfo> findAll() {
        return (List<DepositTypeInfo>)this.depositTypeInfoDao.findAll();
    }

    public Page<DepositTypeInfo> findAllPage(final Predicate predicate, final Pageable pageable) {
        return (Page<DepositTypeInfo>)this.depositTypeInfoDao.findAll(predicate, pageable);
    }

    public DepositTypeInfo findById(final Long id) {
        return (DepositTypeInfo)this.depositTypeInfoDao.getOne(id);
    }

    public int updateStatus(final Long id, final Integer status) {
        return this.depositTypeInfoDao.updateStatus(id, status);
    }

    public void deleteById(final Long id) {
        this.depositTypeInfoDao.deleteById(id);
    }
}
