package cn.ztuo.bitrade.controller.deposit;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.util.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.web.bind.annotation.*;
import java.math.*;

@RestController
@RequestMapping({ "deposit/wallet" })
public class DepositWalletController extends BaseAdminController
{
    @Autowired
    private DepositWalletService depositWalletService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @PostMapping({ "list" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "用户托管账户列表")
    public MessageResult list(final Long memberId) {
        final List<DepositWallet> all = (List<DepositWallet>)this.depositWalletService.queryByMemberId(memberId);
        return this.success((Object)all);
    }

    @RequiresPermissions({ "deposit:wallet:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "分页查找托管账户列表")
    public MessageResult pageQuery(final Long memberId, final String username, final String mobilePhone, final String email, final String coinId, final Integer isLock, final PageModel pageModel) {
        final List<Long> memberIds = (List<Long>)this.memberService.getPredicateMemberIds(memberId, username, (String)null, mobilePhone, email);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("id");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<DepositWallet> all = (Page<DepositWallet>)this.depositWalletService.findPage((List)memberIds, coinId, isLock, pageModel.getPageable());
        return this.success((Object)all);
    }

    @RequiresPermissions({ "deposit:wallet:edit" })
    @PostMapping({ "change-balance" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "托管账户充币")
    public MessageResult changeBalance(@SessionAttribute("ADMIN_MEMBER") final Admin admin, @RequestParam("id") final Long id, @RequestParam("changeBalance") final BigDecimal changeBalance) {
        final String adminId = (admin != null) ? admin.getId().toString() : "";
        if (id == null) {
            return MessageResult.error("参数错误!");
        }
        if (changeBalance == null || changeBalance.equals(BigDecimal.ZERO)) {
            return MessageResult.error("调整余额不可为0!");
        }
        final DepositWallet depositWallet = this.depositWalletService.findOne(id);
        if (depositWallet == null) {
            return MessageResult.error("钱包信息不存在!");
        }
        if (depositWallet.getCoin() == null) {
            return MessageResult.error("币种信息不存在!");
        }
        final int result = this.depositWalletService.changeBalance(depositWallet, changeBalance, adminId);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @RequiresPermissions({ "deposit:wallet:edit" })
    @PostMapping({ "alter-isLock" })
    @AccessLog(module = AdminModule.DEPOSIT, operation = "修改锁定状态")
    public MessageResult alterIsLock(@RequestParam("id") final Long id, @RequestParam("isLock") final Integer isLock) {
        if (id == null || isLock == null) {
            return MessageResult.error("参数错误!");
        }
        final int result = this.depositWalletService.updateIsLock(id, isLock);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }
}
