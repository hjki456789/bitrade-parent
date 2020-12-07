package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;

public interface CommunityTopCategoryConfigRepository extends JpaRepository<CommunityTopCategoryConfig, String>, JpaSpecificationExecutor<CommunityTopCategoryConfig>, QuerydslPredicateExecutor<CommunityTopCategoryConfig>
{
}
