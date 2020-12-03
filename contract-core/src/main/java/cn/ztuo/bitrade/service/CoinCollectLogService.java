package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class CoinCollectLogService
{
    @Autowired
    private CoinCollectLogRepository coinCollectLogRepository;

    public Page<CoinCollectLog> findAll(final Predicate predicate, final Pageable pageable) {
        return this.coinCollectLogRepository.findAll(predicate, pageable);
    }
}
