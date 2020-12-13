package cn.ztuo.bitrade.dao;


import cn.ztuo.bitrade.constant.TransactionType;
import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.MemberTransaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Date;

public interface MemberTransactionDao extends BaseDao<MemberTransaction> {

    @Query("select t.symbol as symbol,sum(t.amount) as amount from MemberTransaction t where  t.amount > 0 and t.memberId = :memberId and t.type in :types GROUP BY t.symbol")
    List<Map<String, Object>> findTransactionSum(@Param("memberId") Long memberId, @Param("types") List<TransactionType> types);

    @Query("select sum(t.amount)  as amount from MemberTransaction t where t.flag = 0  and t.memberId = :memberId and t.symbol = :symbol and t.type = :type and t.createTime >= :startTime and t.createTime <= :endTime")
    Map<String, Object> findMatchTransactionSum(@Param("memberId") Long memberId, @Param("symbol") String symbol, @Param("type") TransactionType type, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Query("select sum(t.amount)  as amount from MemberTransaction t where t.flag = 0  and t.symbol = :symbol and t.type = :type and t.createTime >= :startTime and t.createTime <= :endTime")
    Map<String, Object> findMatchTransactionSum(@Param("symbol") String symbol, @Param("type") TransactionType type, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 根据时间预估分红的数量
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    @Query(value = "SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tmember_transaction\n" +
            "WHERE\n" +
            "\tcreate_time BETWEEN :beginDate\n" +
            "AND :endDate\n" +
            "AND fee > 0", nativeQuery = true)
    List<MemberTransaction> findAllByCreateTime(@Param("beginDate") String beginDate, @Param("endDate") String endDate);



    @Query(value = "select * from member_transaction t where t.sequence < :sequence and t.member_id = :memberId and (t.type = 28 or t.type = 29 or t.type = 14 or t.type = 15 or t.type = 40 or t.type = 41) order by t.sequence desc limit 10", nativeQuery = true)
    List<MemberTransaction> findListBySequence(@Param("memberId") final Long p0, @Param("sequence") final Long p1);


    @Transactional
    @Modifying
    @Query(value = "delete from  member_transaction where member_id=:memberId", nativeQuery = true)
    int deleteByMemberId(@Param("memberId") final Long p0);

    @Query(value = "SELECT SUM(amount) FROM member_transaction WHERE member_id=:id and type=28", nativeQuery = true)
    BigDecimal findAndSumIntoContractById(@Param("id") final Long p0);

    @Query(value = "SELECT IFNULL(SUM(a.amount),0)  FROM member_transaction a  WHERE a.type=:type  AND a.symbol=:symbol AND a.member_id=:memberId", nativeQuery = true)
    BigDecimal sumByTransactionType(@Param("memberId") final long p0, @Param("symbol") final String p1, @Param("type") final int p2);

    @Query(value = "SELECT IFNULL(SUM(a.amount),0)  FROM member_transaction a  WHERE (a.type = 0 or a.type= 10 or a.type= 4)  AND a.symbol=:symbol AND a.member_id=:memberId", nativeQuery = true)
    BigDecimal sumRecharge(@Param("memberId") final long p0, @Param("symbol") final String p1);
}
