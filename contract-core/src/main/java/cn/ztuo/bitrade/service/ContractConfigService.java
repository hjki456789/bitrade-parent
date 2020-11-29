package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import java.io.*;
import java.util.*;

@Service
public class ContractConfigService
{
    @Autowired
    private ContractConfigRepository contractConfigRepository;
    
    public ContractConfig findByConfigKey(final String configKey) {
        return (ContractConfig)this.contractConfigRepository.findOne((Serializable)configKey);
    }
    
    public List<ContractConfig> findAll() {
        return (List<ContractConfig>)this.contractConfigRepository.findAll();
    }
}
