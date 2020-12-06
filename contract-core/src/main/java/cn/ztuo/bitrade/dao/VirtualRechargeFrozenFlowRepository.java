package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface VirtualRechargeFrozenFlowRepository extends JpaRepository<VirtualRechargeFrozenFlow, String>, JpaSpecificationExecutor<VirtualRechargeFrozenFlow>, QuerydslPredicateExecutor<VirtualRechargeFrozenFlow> {
}
