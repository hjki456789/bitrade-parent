package cn.ztuo.bitrade.service.contractstrategy;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.contractstrategy.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.contractstrategy.*;
import java.io.*;
import cn.ztuo.bitrade.constant.*;

@Service
public class ContractStrategyConfigService
{
    @Autowired
    private ContractStrategyConfigRepository contractStrategyConfigRepository;

    public Page<ContractStrategyConfig> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<ContractStrategyConfig> page = (Page<ContractStrategyConfig>)this.contractStrategyConfigRepository.findAll(predicate, pageable);
        return page;
    }

    public ContractStrategyConfig findOne(final Long id) {
        return (ContractStrategyConfig)this.contractStrategyConfigRepository.getOne(id);
    }

    public ContractStrategyConfig save(final ContractStrategyConfig config) {
        return (ContractStrategyConfig)this.contractStrategyConfigRepository.saveAndFlush(config);
    }

    public int deleteById(final Long id) {
        return this.contractStrategyConfigRepository.deleteById(id);
    }

    public ContractStrategyConfig findBySymbolAndStatus(final String symbol, final CommonStatus status) {
        return this.contractStrategyConfigRepository.findBySymbolAndStatus(symbol, status.getOrdinal());
    }
}
