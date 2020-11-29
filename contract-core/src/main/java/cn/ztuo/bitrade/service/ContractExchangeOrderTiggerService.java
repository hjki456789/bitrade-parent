package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class ContractExchangeOrderTiggerService
{
    @Autowired
    private ContractExchangeOrderTiggerRepository contractExchangeOrderTiggerRepository;
    
    public ContractExchangeOrderTigger save(final ContractExchangeOrderTigger tigger) {
        return (ContractExchangeOrderTigger)this.contractExchangeOrderTiggerRepository.saveAndFlush((Object)tigger);
    }
}
