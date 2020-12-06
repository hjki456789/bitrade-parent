package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import org.springframework.data.repository.query.*;

import java.math.*;

import org.springframework.data.jpa.repository.*;
import cn.ztuo.bitrade.entity.*;

import java.util.*;

import org.springframework.transaction.annotation.*;

public interface ContractDoubleDirectionWalletDao extends BaseDao<ContractDoubleDirectionWallet> {
    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.balance = wallet.balance + :amount,wallet.version= wallet.version+1 where wallet.id = :walletId and wallet.version=:version")
    int increaseBalance(@Param("walletId") final long p0, @Param("amount") final BigDecimal p1, @Param("version") final int p2);

    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.balance = wallet.balance + :amount where wallet.id = :walletId")
    int increaseBalanceWithoutVersion(@Param("walletId") final long p0, @Param("amount") final BigDecimal p1);

    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.balance = wallet.balance - :amount,wallet.version= wallet.version+1 where wallet.id = :walletId and wallet.balance >= :amount and wallet.version=:version")
    int decreaseBalance(@Param("walletId") final long p0, @Param("amount") final BigDecimal p1, @Param("version") final int p2);

    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.balance = wallet.balance + :amount,wallet.frozenBalance=wallet.frozenBalance - :amount where wallet.id = :walletId and wallet.frozenBalance >= :amount")
    int thawBalance(@Param("walletId") final long p0, @Param("amount") final BigDecimal p1);

    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.balance = wallet.balance - :amount,wallet.frozenBalance=wallet.frozenBalance + :amount where wallet.id = :walletId and wallet.balance >= :amount")
    int freezeBalance(@Param("walletId") final long p0, @Param("amount") final BigDecimal p1);

    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.frozenBalance=wallet.frozenBalance -:amount where wallet.id = :walletId")
    int decreaseFrozen(@Param("walletId") final long p0, @Param("amount") final BigDecimal p1);

    ContractDoubleDirectionWallet findByCoinAndMemberId(final Coin p0, final Long p1);

    List<ContractDoubleDirectionWallet> findAllByMemberId(final Long p0);

    List<ContractDoubleDirectionWallet> findAllByCoin(final Coin p0);

    @Query(value = "select ifnull(sum(a.balance),0)+ ifnull(sum(a.frozen_balance),0) as allBalance from contract_double_direction_wallet a where a.coin_id = :coinName", nativeQuery = true)
    BigDecimal getWalletAllBalance(@Param("coinName") final String p0);

    @Query("select m from ContractDoubleDirectionWallet m where m.coin=:coin and m.memberId in (:memberIdList)")
    List<ContractDoubleDirectionWallet> findALLByCoinIdAndMemberIdList(@Param("coin") final Coin p0, @Param("memberIdList") final List<Long> p1);

    @Query(value = "select * from contract_double_direction_wallet  where coin_id=:coinId and member_id=:memberId limit 1", nativeQuery = true)
    ContractDoubleDirectionWallet findCoinIdAndMemberId(@Param("coinId") final String p0, @Param("memberId") final Long p1);

    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set wallet.frozenBalance=:frozenBalance where wallet.id=:id")
    int updateFrozenBalance(@Param("id") final Long p0, @Param("frozenBalance") final BigDecimal p1);

    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set  wallet.balance=:balance,  wallet.frozenBalance=:frozenBalance where wallet.id=:id")
    int updateBalance(@Param("id") final Long p0, @Param("balance") final BigDecimal p1, @Param("frozenBalance") final BigDecimal p2);

    @Query(value = "select * from contract_double_direction_wallet where member_id = :memberId", nativeQuery = true)
    List<ContractDoubleDirectionWallet> queryByMemberId(@Param("memberId") final Long p0);

    @Query(value = "select * from contract_double_direction_wallet where id = :id limit 1", nativeQuery = true)
    ContractDoubleDirectionWallet getOne(@Param("id") final Long p0);

    @Transactional
    @Modifying
    @Query("update ContractDoubleDirectionWallet set is_lock = :isLock where id =:id")
    int updateIsLock(@Param("id") final Long p0, @Param("isLock") final Integer p1);

    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    @Query("update ContractDoubleDirectionWallet wallet set  wallet.balance=:balance,  wallet.frozenBalance=:frozenBalance, wallet.version=wallet.version+1 where  wallet.id=:id and wallet.version=:version")
    int updateWalletBalance(@Param("id") final Long p0, @Param("balance") final BigDecimal p1, @Param("frozenBalance") final BigDecimal p2, @Param("version") final int p3);
}
