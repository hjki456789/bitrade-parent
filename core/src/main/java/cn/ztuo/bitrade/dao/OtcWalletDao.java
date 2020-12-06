package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.Coin;
import cn.ztuo.bitrade.entity.OtcWallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: Seven
 * @Date: 2019/5/5 3:25 PM
 */

public interface OtcWalletDao extends BaseDao<OtcWallet> {

    List<OtcWallet> findOtcWalletByMemberId(Long memberId);

//    OtcWallet findOtcWalletByMemberIdAndCoinId(Long memberId,String coinId);

    OtcWallet findByMemberIdAndCoin( Long memberId,Coin coin);

    @Modifying
    @Query("update OtcWallet set balance = balance+:amount where id=:id")
    Integer addWallet(@Param("id") Long id, @Param("amount") BigDecimal amount);

    @Modifying
    @Query("update OtcWallet set balance = balance-:amount where id=:id and balance>=:amount")
    Integer subWallet(@Param("id") Long id, @Param("amount") BigDecimal amount);

    /**
     * 解冻钱包余额
     */
    @Modifying
    @Query("update OtcWallet wallet set wallet.balance = wallet.balance + :amount,wallet.frozenBalance=wallet.frozenBalance - :amount where wallet.id = :walletId and wallet.frozenBalance >= :amount")
    Integer thawBalance(@Param("walletId") long walletId, @Param("amount") BigDecimal amount);

    @Modifying
    @Query("update OtcWallet wallet set wallet.frozenBalance=wallet.frozenBalance - :amount where wallet.id = :walletId and wallet.frozenBalance >= :amount")
    Integer decreaseFrozen(@Param("walletId") Long walletId,@Param("amount") BigDecimal amount);

    /**
     * 增加钱包余额
     *
     * @param walletId
     * @param amount
     * @return
     */
    @Modifying
    @Query("update OtcWallet wallet set wallet.balance = wallet.balance + :amount where wallet.id = :walletId")
    Integer increaseBalance(@Param("walletId") long walletId, @Param("amount") BigDecimal amount);

    @Modifying
    @Query("update OtcWallet wallet set wallet.balance = wallet.balance - :amount,wallet.frozenBalance=wallet.frozenBalance + :amount where wallet.id = :walletId and wallet.balance >= :amount")
    Integer freezeBalance(@Param("walletId") Long walletId, @Param("amount") BigDecimal amount);


    @Query(value = "select * from otc_wallet where id = :id limit 1", nativeQuery = true)
    Optional<OtcWallet> findById(@Param("id") final Long p0);

    @Transactional
    @Modifying
    @Query("update OtcWallet set balance = :balance,frozenBalance=:frozenBalance,  releaseBalance=:releaseBalance,version=version+1  where id =:id and version=:version")
    Integer updateOtcWalletBalance(@Param("id") final Long p0, @Param("balance") final BigDecimal p1, @Param("frozenBalance") final BigDecimal p2, @Param("releaseBalance") final BigDecimal p3, @Param("version") final Integer p4);

    @Transactional
    @Modifying
    @Query("update OtcWallet set is_lock = :isLock where id =:id")
    int updateIsLock(@Param("id") final Long p0, @Param("isLock") final Integer p1);

}
