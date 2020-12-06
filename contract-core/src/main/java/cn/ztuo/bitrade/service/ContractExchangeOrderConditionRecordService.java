package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class ContractExchangeOrderConditionRecordService {
    @Autowired
    private ContractExchangeOrderConditionRecordRepository recordRepository;

    public ContractExchangeOrderConditionRecord save(final ContractExchangeOrderConditionRecord record) {
        return (ContractExchangeOrderConditionRecord) this.recordRepository.saveAndFlush(record);
    }
}
