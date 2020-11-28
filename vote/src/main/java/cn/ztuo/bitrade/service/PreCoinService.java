package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class PreCoinService
{
    @Autowired
    private PreCoinDao preCoinDao;

    public PreCoin findById(Long id) {
        return preCoinDao.getOne(id);
    }

    public PreCoin save(PreCoin preCoin) {
        return preCoinDao.saveAndFlush(preCoin);
    }
}
