package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class ContractWalletExplodeRecordService
{
    @Autowired
    private ContractWalletExplodeRecordRepository contractWalletExplodeRecordRepository;

    public ContractWalletExplodeRecord saveRecord(final ContractWalletExplodeRecord explodeRecord) {
        return (ContractWalletExplodeRecord)this.contractWalletExplodeRecordRepository.saveAndFlush(explodeRecord);
    }
}
