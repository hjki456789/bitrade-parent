package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;

@Service
public class ContractMemberTransferRecordService
{
    @Autowired
    private ContractMemberTransferRecordRepository contractMemberTransferRecordRepository;

    public Page<ContractMemberTransferRecord> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<ContractMemberTransferRecord>)this.contractMemberTransferRecordRepository.findAll(predicate, pageable);
    }

    public ContractMemberTransferRecord save(final ContractMemberTransferRecord contractMemberTransferRecord) {
        contractMemberTransferRecord.setCreateTime(new Date());
        contractMemberTransferRecord.setSequence(System.currentTimeMillis());
        return (ContractMemberTransferRecord)this.contractMemberTransferRecordRepository.save(contractMemberTransferRecord);
    }
}
