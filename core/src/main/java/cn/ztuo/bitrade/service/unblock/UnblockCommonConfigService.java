package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.unblock.*;

import java.io.*;

import org.slf4j.*;

@Service
public class UnblockCommonConfigService {
    private static final Logger log;
    @Autowired
    private UnblockCommonConfigRepository unblockCommonConfigRepository;

    public Page<UnblockCommonConfig> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<UnblockCommonConfig>) this.unblockCommonConfigRepository.findAll(predicate, pageable);
    }

    public UnblockCommonConfig findById(final Long id) {
        return (UnblockCommonConfig) this.unblockCommonConfigRepository.getOne(id);
    }

    public int update(final UnblockCommonConfig unblockCommonConfig) {
        return this.unblockCommonConfigRepository.update(unblockCommonConfig.getId(), unblockCommonConfig.getValue(), unblockCommonConfig.getOrderId());
    }

    static {
        log = LoggerFactory.getLogger((Class) UnblockCommonConfigService.class);
    }
}
