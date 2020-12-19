package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.transaction.annotation.*;

@Service
public class SysAddressPoolService extends BaseService
{
    @Autowired
    private SysAddressPoolDao sysAddressPoolDao;
    
    public SysAddressPool findByType(final int type) {
        return this.sysAddressPoolDao.findByType(type);
    }
    
    @Transactional
    public int updateStatus(final String address) {
        return this.sysAddressPoolDao.updateStatus(address);
    }
}
