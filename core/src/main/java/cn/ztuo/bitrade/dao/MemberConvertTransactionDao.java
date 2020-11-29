package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface MemberConvertTransactionDao extends BaseDao<MemberConvertTransaction>
{
    @Query(value = "SELECT\n\t*\nFROM\n\tmember_convert_transaction\nWHERE\n\tcreate_time BETWEEN :beginDate\nAND :endDate\nAND fee > 0", nativeQuery = true)
    List<MemberConvertTransaction> findAllByCreateTime(@Param("beginDate") final String p0, @Param("endDate") final String p1);
    
    @Query(value = "select * from member_convert_transaction t where t.sequence < :sequence and t.member_id = :memberId order by t.sequence desc limit 10", nativeQuery = true)
    List<MemberConvertTransaction> findListBySequence(@Param("memberId") final Long p0, @Param("sequence") final Long p1);
}
