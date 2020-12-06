package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.data.jpa.domain.*;
import java.io.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import javax.persistence.criteria.*;

@Service
public class RobotSymbolConfigService extends BaseService
{
    @Autowired
    private RobotSymbolConfigDao robotSymbolConfigDao;
    @Autowired
    private MemberDao memberDao;

    public List<RobotSymbolConfig> findAllEnabled() {
        final Specification<RobotSymbolConfig> specification = (Specification<RobotSymbolConfig>)((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.where(criteriaBuilder.equal(root.get("status"), 1));
            return null;
        });
        return (List<RobotSymbolConfig>)this.robotSymbolConfigDao.findAll((Specification)specification);
    }

    public RobotSymbolConfig findOne(final String symbol) {
        return (RobotSymbolConfig)this.robotSymbolConfigDao.getOne(symbol);
    }

    public Page<RobotSymbolConfig> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<RobotSymbolConfig> page = (Page<RobotSymbolConfig>)this.robotSymbolConfigDao.findAll(predicate, pageable);
        if (page != null && page.getContent() != null) {
            for (final RobotSymbolConfig robotSymbolConfig : page.getContent()) {
                final Member member = (Member)this.memberDao.getOne(robotSymbolConfig.getMemberId());
                robotSymbolConfig.setMember(member);
            }
        }
        return page;
    }

    public int insert(final RobotSymbolConfig robotSymbolConfig) {
        final Date date = new Date();
        return this.robotSymbolConfigDao.insert(robotSymbolConfig.getSymbol(), robotSymbolConfig.getBaseCoinBalance(), robotSymbolConfig.getTradeCoinBalance(), robotSymbolConfig.getRobotOrderMax(), robotSymbolConfig.getFromExchangeId(), robotSymbolConfig.getFromExchangeSymbol(), robotSymbolConfig.getMemberId(), robotSymbolConfig.getDepthAmountDownRate(), robotSymbolConfig.getTradeAmountUpRate(), robotSymbolConfig.getBuyPriceDownRate(), robotSymbolConfig.getSellPriceUpRate(), robotSymbolConfig.getFee(), robotSymbolConfig.getStatus(), robotSymbolConfig.getArbitrageStatus(), date, date.getTime(), robotSymbolConfig.getVersion());
    }

    public RobotSymbolConfig update(final RobotSymbolConfig robotSymbolConfig) {
        return (RobotSymbolConfig)this.robotSymbolConfigDao.save(robotSymbolConfig);
    }

    public int deleteRobotSymbolConfig(final String symbol) {
        return this.robotSymbolConfigDao.deleteRobotSymbolConfig(symbol);
    }
}
