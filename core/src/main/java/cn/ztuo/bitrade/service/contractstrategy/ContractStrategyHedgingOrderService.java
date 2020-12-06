package cn.ztuo.bitrade.service.contractstrategy;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.contractstrategy.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.contractstrategy.*;

@Service
public class ContractStrategyHedgingOrderService {
    @Autowired
    private ContractStrategyHedgingOrderRepository contractStrategyHedgingOrderRepository;

    public ContractStrategyHedgingOrder save(ContractStrategyHedgingOrder contractStrategyHedgingOrder) {
        return this.contractStrategyHedgingOrderRepository.saveAndFlush(contractStrategyHedgingOrder);
    }
}
