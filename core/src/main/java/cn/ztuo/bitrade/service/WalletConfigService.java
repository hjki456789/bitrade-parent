package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class WalletConfigService extends BaseService {
    @Autowired
    private WalletConfigDao walletConfigDao;

    public Page<WalletConfig> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<WalletConfig> page = (Page<WalletConfig>) this.walletConfigDao.findAll(predicate, pageable);
        return page;
    }
}
