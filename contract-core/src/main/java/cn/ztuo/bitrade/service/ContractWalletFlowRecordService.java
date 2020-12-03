package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@Service
public class ContractWalletFlowRecordService
{
    @Autowired
    private ContractWalletFlowRecordRepository contractWalletFlowRecordRepository;

    public MessageResult saveRecord(ContractWalletFlowRecord contractWalletFlowRecord) {
        contractWalletFlowRecord = (ContractWalletFlowRecord)this.contractWalletFlowRecordRepository.saveAndFlush(contractWalletFlowRecord);
        if (contractWalletFlowRecord != null) {
            return MessageResult.success("success");
        }
        return MessageResult.error(500, "error");
    }
}
