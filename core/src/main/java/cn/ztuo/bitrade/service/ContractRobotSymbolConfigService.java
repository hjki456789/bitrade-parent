package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.domain.*;
import java.io.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import java.util.*;
import javax.persistence.criteria.*;

@Service
public class ContractRobotSymbolConfigService extends BaseService
{
    @Autowired
    private ContractRobotSymbolConfigDao contractRobotSymbolConfigDao;
    @Autowired
    private MemberDao memberDao;

    public List<ContractRobotSymbolConfig> findAllEnabled() {
        final Specification<ContractRobotSymbolConfig> specification = (Specification<ContractRobotSymbolConfig>)((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.where(criteriaBuilder.equal(root.get("status"), 1));
            return null;
        });
        return (List<ContractRobotSymbolConfig>)this.contractRobotSymbolConfigDao.findAll((Specification)specification);
    }

    public ContractRobotSymbolConfig findOne(final String symbol) {
        return (ContractRobotSymbolConfig)this.contractRobotSymbolConfigDao.getOne(symbol);
    }

    public Page<ContractRobotSymbolConfig> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<ContractRobotSymbolConfig> page = (Page<ContractRobotSymbolConfig>)this.contractRobotSymbolConfigDao.findAll(predicate, pageable);
        return page;
    }

    public int insert(final ContractRobotSymbolConfig robotSymbolConfig) {
        final Date date = new Date();
        return this.contractRobotSymbolConfigDao.insert(robotSymbolConfig.getSymbol(), robotSymbolConfig.getFromExchangeId(), robotSymbolConfig.getFromExchangeSymbol(), robotSymbolConfig.getStatus(), date, date.getTime(), robotSymbolConfig.getVersion(), robotSymbolConfig.getAmountMultiple());
    }

    public ContractRobotSymbolConfig update(final ContractRobotSymbolConfig robotSymbolConfig) {
        return (ContractRobotSymbolConfig)this.contractRobotSymbolConfigDao.save(robotSymbolConfig);
    }

    public int deleteRobotSymbolConfig(final String symbol) {
        return this.contractRobotSymbolConfigDao.deleteRobotSymbolConfig(symbol);
    }
}
