package cn.ztuo.bitrade.controller.unblock;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.unbolck.*;
import cn.ztuo.bitrade.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.unblock.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.*;

@RestController
@RequestMapping({ "unblock/common-config" })
public class UnblockCommonConfigController extends BaseAdminController
{
    @Autowired
    private UnblockCommonConfigService unblockCommonConfigService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "unblock:common-config:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "分页查找解封公共配置信息列表")
    public MessageResult pageQuery(final PageModel pageModel, final UnblockExchangeSymbol unblockExchangeSymbol) {
        final Page<UnblockCommonConfig> all = (Page<UnblockCommonConfig>)this.unblockCommonConfigService.findAll((Predicate)null, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "detail" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "解封公共配置信息详情")
    public MessageResult detail(@RequestParam("id") final Long id) {
        final UnblockCommonConfig unblockCommonConfig = this.unblockCommonConfigService.findById(id);
        if (unblockCommonConfig == null) {
            return MessageResult.error("信息不存在!");
        }
        return this.success(unblockCommonConfig);
    }

    @RequiresPermissions({ "unblock:common-config:update" })
    @PostMapping({ "update" })
    @AccessLog(module = AdminModule.UNBLOCK, operation = "更新解封公共配置信息")
    public MessageResult update(final UnblockCommonConfig unblockCommonConfig) {
        if (null == unblockCommonConfig.getId()) {
            return MessageResult.error("ID不能为空!");
        }
        if (StringUtils.isEmpty((CharSequence)unblockCommonConfig.getValue())) {
            return MessageResult.error("值不能为空!");
        }
        if (null == unblockCommonConfig.getOrderId()) {
            return MessageResult.error("排序不能为空!");
        }
        final UnblockCommonConfig commonConfig = this.unblockCommonConfigService.findById(unblockCommonConfig.getId());
        if (commonConfig == null) {
            return MessageResult.error("信息不存在!");
        }
        this.unblockCommonConfigService.update(unblockCommonConfig);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }
}
