package cn.ztuo.bitrade.controller.otc;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.web.bind.annotation.*;
import java.math.*;
import cn.ztuo.bitrade.enums.*;

@RestController
@RequestMapping({ "otc/otc-wallet" })
public class OtcWalletController extends BaseAdminController
{
    @Autowired
    private OtcWalletService otcWalletService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({ "otc:otc-wallet:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.OTC, operation = "分页查找法币钱包列表")
    public MessageResult pageQuery(final Long memberId, final String username, final String mobilePhone, final String email, final String coinId, final Integer status, final PageModel pageModel) {
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("id");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<OtcWallet> all = (Page<OtcWallet>)this.otcWalletService.findPage(memberId, username, mobilePhone, email, coinId, status, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "change-balance" })
    @AccessLog(module = AdminModule.OTC, operation = "法币账户充币")
    public MessageResult changeBalance(@SessionAttribute("ADMIN_MEMBER") final Admin admin, @RequestParam("id") final Long id, @RequestParam("changeBalance") final BigDecimal changeBalance) {
        final String adminId = (admin != null) ? admin.getId().toString() : "";
        if (id == null) {
            return MessageResult.error("参数错误!");
        }
        if (changeBalance == null || changeBalance.equals(BigDecimal.ZERO)) {
            return MessageResult.error("调整余额不可为0!");
        }
        final OtcWallet otcWallet = this.otcWalletService.findOne(id);
        if (otcWallet == null) {
            return MessageResult.error("钱包信息不存在!");
        }
        if (otcWallet.getCoin() == null) {
            return MessageResult.error("币种信息不存在!");
        }
        final int result = this.otcWalletService.changeBalance(otcWallet, changeBalance, OtcWalletOperationType.CHANGE_BALANCE, "法币账户后台充币", adminId);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @PostMapping({ "alter-isLock" })
    @AccessLog(module = AdminModule.OTC, operation = "修改锁定状态")
    public MessageResult alterIsLock(@RequestParam("id") final Long id, @RequestParam("isLock") final Integer isLock) {
        if (id == null || isLock == null) {
            return MessageResult.error("参数错误!");
        }
        final int result = this.otcWalletService.updateIsLock(id, isLock);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }
}
