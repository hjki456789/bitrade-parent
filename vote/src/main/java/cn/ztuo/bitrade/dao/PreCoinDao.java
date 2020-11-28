package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;

public interface PreCoinDao extends JpaRepository<PreCoin, Long>, JpaSpecificationExecutor<PreCoin>
{
}

