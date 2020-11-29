package cn.ztuo.bitrade.dao.insurance;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.insurance.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface WholeNetInsuranceFundWalletRecordRepository extends BaseDao<WholeNetInsuranceFundWalletRecord>
{
    @Query(value = "select sum(amount) as 'amount',coin from whole_net_insurance_fund_wallet_record  where sequence >= :startSeq and sequence <= :endSeq group by coin", nativeQuery = true)
    List<Object[]> sumAmountBySeq(@Param("startSeq") final long p0, @Param("endSeq") final long p1);

    @Query(value = "select sum(amount) as 'amount',coin from whole_net_insurance_fund_wallet_record  where coin = :coin and sequence >= :startSeq and sequence <= :endSeq group by coin", nativeQuery = true)
    List<Object[]> sumAmountByCoinAndSeq(@Param("coin") final String p0, @Param("startSeq") final long p1, @Param("endSeq") final long p2);
}
