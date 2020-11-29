package cn.ztuo.bitrade.service.contractstrategy;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.contractstrategy.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.contractstrategy.*;
import java.io.*;
import java.util.*;

@Service
public class ContractStrategyExchangeConfigService
{
    @Autowired
    private ContractStrategyExchangeConfigRepository contractStrategyExchangeConfigRepository;

    public ContractStrategyExchangeConfig findById(final Long id) {
        return this.contractStrategyExchangeConfigRepository.getOne(id);
    }

    public List<ContractStrategyExchangeConfig> findAll() {
        return this.contractStrategyExchangeConfigRepository.findAll();
    }
}
