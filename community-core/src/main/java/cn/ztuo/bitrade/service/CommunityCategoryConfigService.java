package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;

@Service
public class CommunityCategoryConfigService
{
    @Autowired
    private CommunitySecondCategoryConfigRepository communitySecondCategoryConfigRepository;
    @Autowired
    private CommunityTopCategoryConfigRepository communityTopCategoryConfigRepository;

    public List<CommunityTopCategoryConfig> getCommunityTopCategoryConfigList() {
        return (List<CommunityTopCategoryConfig>)this.communityTopCategoryConfigRepository.findAll();
    }

    public List<CommunitySecondCategoryConfig> getCommunitySecondCategoryConfigListByTopId(final String topId) {
        final Criteria<CommunitySecondCategoryConfig> specification = (Criteria<CommunitySecondCategoryConfig>)new Criteria();
        if (StringUtils.isNotEmpty((CharSequence)topId)) {
            specification.add((Criterion)Restrictions.eq("topId", topId, true));
        }
        return (List<CommunitySecondCategoryConfig>)this.communitySecondCategoryConfigRepository.findAll((Specification)specification);
    }
}
