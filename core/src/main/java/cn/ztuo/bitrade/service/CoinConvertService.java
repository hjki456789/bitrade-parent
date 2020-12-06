package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

import java.util.*;

import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

@Service
public class CoinConvertService extends BaseService {
    @Autowired
    private CoinConvertDao coinConvertDao;

    public CoinConvert findById(final Long id) {
        return this.coinConvertDao.getOne(id);
    }

    public CoinConvert findByBaseAndConvert(final String baseCoin, final String convertCoin) {
        return this.coinConvertDao.findByBaseAndConvert(baseCoin, convertCoin);
    }

    public List<CoinConvert> findByBaseCoin(final String baseCoin) {
        return this.coinConvertDao.findAllByBaseCoin(baseCoin);
    }

    @Override
    public List<CoinConvert> findAll() {
        return (List<CoinConvert>) this.coinConvertDao.findAll();
    }

    public Page<CoinConvert> findAllPage(final Predicate predicate, final Pageable pageable) {
        return (Page<CoinConvert>) this.coinConvertDao.findAll(predicate, pageable);
    }

    public void save(final CoinConvert coinConvert) {
        this.coinConvertDao.save(coinConvert);
    }

    public void deleteById(final Long id) {
        this.coinConvertDao.deleteById(id);
    }
}
