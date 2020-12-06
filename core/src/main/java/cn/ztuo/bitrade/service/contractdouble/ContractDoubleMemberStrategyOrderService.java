package cn.ztuo.bitrade.service.contractdouble;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.contractdouble.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import com.querydsl.core.types.*;
import org.springframework.transaction.annotation.*;

import java.io.*;

import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;

import java.util.*;
import java.math.*;

@Service
public class ContractDoubleMemberStrategyOrderService {
    @Autowired
    private ContractDoubleMemberStrategyOrderDao contractDoubleMemberStrategyOrderDao;

    public ContractDoubleMemberStrategyOrder save(final ContractDoubleMemberStrategyOrder contractStrategyHedgingOrder) {
        return this.contractDoubleMemberStrategyOrderDao.saveAndFlush(contractStrategyHedgingOrder);
    }

    public Page<ContractDoubleMemberStrategyOrder> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<ContractDoubleMemberStrategyOrder> page = this.contractDoubleMemberStrategyOrderDao.findAll(predicate, pageable);
        return page;
    }

    public List<ContractDoubleMemberStrategyOrder> findAllNeedCheckOrders() {
        return this.contractDoubleMemberStrategyOrderDao.findAllNeedCheckOrders();
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateMatchStatus(final Long id, final int matchStatus) {
        return this.contractDoubleMemberStrategyOrderDao.updateMatchStatus(id, matchStatus, new Date());
    }

    public ContractDoubleMemberStrategyOrder findById(final Long id) {
        if (null == id) {
            return null;
        }
        return this.contractDoubleMemberStrategyOrderDao.getOne(id);
    }

    public ContractDoubleMemberStrategyOrder findByOrderId(final long contractOrderId) {
        final Criteria<ContractDoubleMemberStrategyOrder> specification = new Criteria<ContractDoubleMemberStrategyOrder>();
        specification.add(Restrictions.eq("contractOrderId", contractOrderId, true));
        return this.contractDoubleMemberStrategyOrderDao.findOne(specification).get();
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateCloseInfo(final Long id, final String closeOrderId, final int closeMatchStatus, final Date closeTime) {
        return this.contractDoubleMemberStrategyOrderDao.updateCloseInfo(id, closeOrderId, closeMatchStatus, closeTime);
    }

    public List<ContractDoubleMemberStrategyOrder> findAllNeedCheckCloseOrders() {
        return this.contractDoubleMemberStrategyOrderDao.findAllNeedCheckCloseOrders();
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateCloseMatchStatus(final Long id, final int closeMatchStatus) {
        return this.contractDoubleMemberStrategyOrderDao.updateCloseMatchStatus(id, closeMatchStatus);
    }

    public Page<ContractDoubleMemberStrategyOrder> getExternalOpenExchangeOrders(final Long memberId, final int pageNo, final int pageSize, final Integer fromExchangeId) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "id")});
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<ContractDoubleMemberStrategyOrder> specification = new Criteria<ContractDoubleMemberStrategyOrder>();
        if (null != memberId) {
            specification.add(Restrictions.eq("memberId", memberId, true));
        }
        specification.add(Restrictions.eq("status", 1, false));
        if (null != fromExchangeId) {
            specification.add(Restrictions.eq("fromExchangeId", fromExchangeId, true));
        }
        specification.add(Restrictions.ne("closeMatchStatus", 5, true));
        specification.add(Restrictions.ne("closeMatchStatus", 6, true));
        specification.add(Restrictions.ne("closeMatchStatus", 7, true));
        return (Page<ContractDoubleMemberStrategyOrder>) this.contractDoubleMemberStrategyOrderDao.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public Page<ContractDoubleMemberStrategyOrder> getExternalHistoryExchangeOrders(final long memberId, final int pageNo, final int pageSize, final Integer fromExchangeId) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "id")});
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<ContractDoubleMemberStrategyOrder> specification = new Criteria<ContractDoubleMemberStrategyOrder>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        specification.add(Restrictions.eq("status", 1, false));
        if (null != fromExchangeId) {
            specification.add(Restrictions.eq("fromExchangeId", fromExchangeId, true));
        }
        final List<Integer> closeMatchStatusList = new ArrayList<Integer>();
        closeMatchStatusList.add(5);
        closeMatchStatusList.add(6);
        closeMatchStatusList.add(7);
        specification.add(Restrictions.in("closeMatchStatus", closeMatchStatusList, true));
        return (Page<ContractDoubleMemberStrategyOrder>) this.contractDoubleMemberStrategyOrderDao.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public int updateBasicInfo(final Long id, final BigDecimal marginFrozen, final BigDecimal profit) {
        return this.contractDoubleMemberStrategyOrderDao.updateBasicInfo(id, marginFrozen, profit);
    }
}
