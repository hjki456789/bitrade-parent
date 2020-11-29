package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.querydsl.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.query.*;
import java.util.*;
import java.math.*;
import javax.transaction.*;
import org.springframework.data.jpa.repository.*;

@Repository
public interface ContractRobotSymbolConfigDao extends BaseDao<ContractRobotSymbolConfig>
{
    @Transactional
    @Modifying
    @Query(value = "insert into contract_robot_symbol_config(symbol,from_exchange_id,from_exchange_symbol,status,create_time,sequence,version,amount_multiple) values(:symbol,:fromExchangeId,:fromExchangeSymbol,:status,:createTime,:sequence,:version,:amountMultiple)", nativeQuery = true)
    int insert(@Param("symbol") final String p0, @Param("fromExchangeId") final Long p1, @Param("fromExchangeSymbol") final String p2, @Param("status") final Integer p3, @Param("createTime") final Date p4, @Param("sequence") final Long p5, @Param("version") final Long p6, @Param("amountMultiple") final BigDecimal p7);

    @Transactional
    @Modifying
    @Query(value = "delete from contract_robot_symbol_config where symbol = :symbol", nativeQuery = true)
    int deleteRobotSymbolConfig(@Param("symbol") final String p0);
}
