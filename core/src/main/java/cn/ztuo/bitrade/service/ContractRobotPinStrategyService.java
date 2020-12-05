package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.enums.*;
import javax.transaction.*;
import org.springframework.data.domain.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.apache.commons.collections.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import java.io.*;

@Service
public class ContractRobotPinStrategyService extends BaseService
{
    @Autowired
    private ContractRobotPinStrategyDao contractRobotPinStrategyDao;

    public ContractRobotPinStrategy getCurrentEffectiveStrategy(final String symbol, final long sequence) {
        return this.contractRobotPinStrategyDao.getCurrentEffectiveStrategy(symbol, sequence);
    }

    @Transactional
    public int updateStatus(final Long id, final ContractRobotPinStrategyStatus status) {
        return this.contractRobotPinStrategyDao.updateStatus(id, status, new Date());
    }

    public Page<ContractRobotPinStrategy> findPage(final String symbol, final Integer status, final Integer side, final Pageable pageable) {
        final Criteria<ContractRobotPinStrategy> specification = new Criteria<ContractRobotPinStrategy>();
        if (StringUtils.isNotEmpty((CharSequence)symbol)) {
            specification.add(Restrictions.eq("symbol", symbol, true));
        }
        if (null != status) {
            specification.add(Restrictions.eq("status", status, true));
        }
        if (null != side) {
            specification.add(Restrictions.eq("side", side, true));
        }
        final Page<ContractRobotPinStrategy> page = (Page<ContractRobotPinStrategy>)this.contractRobotPinStrategyDao.findAll((Specification)specification, pageable);
        if (CollectionUtils.isEmpty((Collection)page.getContent())) {
            return page;
        }
        for (final ContractRobotPinStrategy data : page.getContent()) {
            data.setStartEffectTimeStr(DateUtil.YYYY_MM_DD_MM_HH_SS.format(data.getStartEffectTime()));
        }
        return page;
    }

    public ContractRobotPinStrategy findById(final Long id) {
        return (ContractRobotPinStrategy)this.contractRobotPinStrategyDao.getOne(id);
    }

    public ContractRobotPinStrategy save(final ContractRobotPinStrategy contractRobotPinStrategy) {
        return (ContractRobotPinStrategy)this.contractRobotPinStrategyDao.save(contractRobotPinStrategy);
    }
}
