package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.dao.FeedbackDao;
import cn.ztuo.bitrade.entity.Feedback;
import cn.ztuo.bitrade.pagination.Criteria;
import cn.ztuo.bitrade.pagination.Restrictions;
import cn.ztuo.bitrade.service.Base.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Seven
 * @date 2019年03月19日
 */
@Service
public class FeedbackService extends BaseService {
    @Autowired
    private FeedbackDao feedbackDao;

    public Feedback save(Feedback feedback){
        return feedbackDao.save(feedback);
    }


    public Page<Feedback> findPage(final Long memberId, final String username, final String mobilePhone, final Date startTime, final Date endTime, final Pageable pageable) {
        final Criteria<Feedback> specification = new Criteria<Feedback>();
        if (null != memberId) {
            specification.add(Restrictions.eq("member.id", memberId, true));
        }
        if (StringUtils.isNotEmpty((CharSequence)username)) {
            specification.add(Restrictions.like("member.username", username, true));
        }
        if (StringUtils.isNotEmpty((CharSequence)mobilePhone)) {
            specification.add(Restrictions.like("member.mobilePhone", mobilePhone, true));
        }
        if (null != startTime) {
            specification.add(Restrictions.gte("createTime", startTime, true));
        }
        if (null != endTime) {
            specification.add(Restrictions.lt("createTime", endTime, true));
        }
        return this.feedbackDao.findAll(specification, pageable);
    }
}
