package cn.ztuo.bitrade.service.contractdouble;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.contractdouble.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import java.io.*;

@Service
public class ContractDoubleExchangeConfigService
{
    @Autowired
    private ContractDoubleExchangeConfigDao contractDoubleExchangeConfigDao;

    public List<ContractDoubleExchangeConfig> findAll() {
        return contractDoubleExchangeConfigDao.findAll();
    }

    public ContractDoubleExchangeConfig get(final Long id) {
        return contractDoubleExchangeConfigDao.getOne(id);
    }
}
