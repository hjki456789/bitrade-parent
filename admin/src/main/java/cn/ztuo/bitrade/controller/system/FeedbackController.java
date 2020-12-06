package cn.ztuo.bitrade.controller.system;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import com.fasterxml.jackson.annotation.*;
import cn.ztuo.bitrade.util.*;

import java.util.*;

import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;

@RestController
@RequestMapping({"system/feedback"})
public class FeedbackController extends BaseAdminController {
    @Autowired
    private FeedbackService feedbackService;

    @RequiresPermissions({"system:feedback:page-query"})
    @PostMapping({"page-query"})
    @AccessLog(module = AdminModule.SYSTEM, operation = "分页查找用户反馈信息列表")
    public MessageResult pageQuery(final Long memberId, final String username, final String mobilePhone, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime, final PageModel pageModel) {
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("createTime");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List) list);
            pageModel.setDirection((List) directions);
        }
        final Page<Feedback> all = (Page<Feedback>) this.feedbackService.findPage(memberId, username, mobilePhone, startTime, endTime, pageModel.getPageable());
        return this.success(all);
    }
}
