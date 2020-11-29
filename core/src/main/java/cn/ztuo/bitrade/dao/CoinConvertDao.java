package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

@Repository
public interface CoinConvertDao extends BaseDao<CoinConvert>
{
    CoinConvert getOne(final Long p0);

    @Query("select a from CoinConvert a where a.baseCoin = :baseCoin")
    List<CoinConvert> findAllByBaseCoin(@Param("baseCoin") final String baseCoin);

    @Query("select a from CoinConvert a where a.baseCoin = :baseCoin and a.convertCoin = :convertCoin")
    CoinConvert findByBaseAndConvert(@Param("baseCoin") final String p0, @Param("convertCoin") final String p1);

    @Transactional
    @Modifying
    @Query(value = "delete from coin_convert where id =:id", nativeQuery = true)
    void deleteById(@Param("id") final Long p0);
}
