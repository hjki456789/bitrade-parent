package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class ContractExchangeOrderCancelService
{
    @Autowired
    private ContractExchangeOrderCancelRepository contractExchangeOrderCancelRepository;

    public ContractExchangeOrderCancel saveContractExchangeOrderCancel(final ContractExchangeOrderCancel cancel) {
        return (ContractExchangeOrderCancel)this.contractExchangeOrderCancelRepository.saveAndFlush(cancel);
    }
}
