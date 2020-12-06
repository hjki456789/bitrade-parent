package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.MemberDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface MemberDepositDao extends JpaRepository<MemberDeposit, Long>, QuerydslPredicateExecutor<MemberDeposit> {
    MemberDeposit findByAddressAndTxid(String address, String txid);

    @Query(value = "select unit ,sum(amount) from member_deposit where date_format(create_time,'%Y-%m-%d') = :date group by unit", nativeQuery = true)
    List<Object[]> getDepositStatistics(@Param("date") String date);

    @Query(value = "select unit ,sum(amount) from member_deposit where member_id in (:memberIds) group by unit", nativeQuery = true)
    List<Object[]> sumMembersDeposit(@Param("memberIds") final List<Long> p0);

    @Query(value = "select unit ,sum(amount) from member_deposit where member_id = :memberId group by unit", nativeQuery = true)
    List<Object[]> sumMemberDeposit(@Param("memberId") final Long p0);

    @Query(value = "SELECT  ifnull(SUM(a.amount),0) from member_deposit a  where a.unit=:unit  and a.member_id=:memberId", nativeQuery = true)
    BigDecimal sumMemberDepositByUnit(@Param("memberId") final long p0, @Param("unit") final String p1);

    @Query(value = "SELECT * from member_deposit a where a.txid=:txid ", nativeQuery = true)
    MemberDeposit getByTxid(@Param("txid") final String p0);

    @Transactional
    @Modifying
    @Query("update MemberDeposit set collect_type = :collectType where id =:id")
    int updateCollectType(@Param("id") final Long id, @Param("collectType") final Integer p1);


}
