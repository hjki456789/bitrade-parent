package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.transaction.annotation.*;

@Service
public class ChainLastBlockService extends BaseService {
    @Autowired
    private ChainLastBlockDao chainLastBlockDao;

    public ChainLastBlock save(final ChainLastBlock chainLastBlock) {
        return (ChainLastBlock) this.chainLastBlockDao.saveAndFlush(chainLastBlock);
    }

    public ChainLastBlock findById(final Long id) {
        return this.chainLastBlockDao.getOne(id);
    }

    public ChainLastBlock findByAssetsName(final String assetsName) {
        return this.chainLastBlockDao.findByAssetsName(assetsName);
    }

    @Transactional
    public int updateLastBlockNum(final Long id, final Long lastBlockNum) {
        return this.chainLastBlockDao.updateLastBlockNum(id, lastBlockNum);
    }
}
