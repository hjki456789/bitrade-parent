package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract/contract-wallet" })
public class ContractExchangeWalletController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;

    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找合约钱包列表")
    public MessageResult contractWalletList(final Long memberId, final String username, final String mobilePhone, final String email, final String coinId, final Integer status, final PageModel pageModel) {
        final Predicate predicate = this.getPredicate(memberId, username, mobilePhone, email, coinId, status);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("id");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractWallet> all = (Page<ContractWallet>)this.contractWalletService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @PostMapping({ "alter-isLock" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "修改锁定状态")
    public MessageResult alterIsLock(@RequestParam("id") final Long id, @RequestParam("isLock") final Integer isLock) {
        if (id == null || isLock == null) {
            return MessageResult.error("参数错误!");
        }
        final int result = this.contractWalletService.updateIsLock(id, isLock);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @PostMapping({ "change-balance" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "合约账户充币")
    public MessageResult changeBalance(@SessionAttribute("ADMIN_MEMBER") final Admin admin, @RequestParam("id") final Long id, @RequestParam("changeBalance") final BigDecimal changeBalance) {
        final String adminId = (admin != null) ? admin.getId().toString() : "";
        if (id == null) {
            return MessageResult.error("参数错误!");
        }
        if (changeBalance == null || changeBalance.equals(new BigDecimal(0))) {
            return MessageResult.error("调整余额不可为0!");
        }
        final ContractWallet contractWallet = this.contractWalletService.findOne(id);
        if (contractWallet == null) {
            return MessageResult.error("钱包信息不存在!");
        }
        if (contractWallet.getMember() == null || contractWallet.getCoin() == null) {
            return MessageResult.error("用户信息或币种信息不存在!");
        }
        final int result = this.contractWalletService.changeBalance(contractWallet, changeBalance, ContractWalletOperationType.CHANGE_BALANCE, "合约账户充币", adminId);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    public Predicate getPredicate(final Long memberId, final String username, final String mobilePhone, final String email, final String coinId, final Integer status) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (memberId != null) {
            booleanExpressions.add(QContractWallet.contractWallet.member.id.eq(memberId));
        }
        if (!StringUtils.isEmpty((CharSequence)username)) {
            booleanExpressions.add(QContractWallet.contractWallet.member.username.like("%" + username + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QContractWallet.contractWallet.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)email)) {
            booleanExpressions.add(QContractWallet.contractWallet.member.email.like("%" + email + "%"));
        }
        if (!StringUtils.isEmpty((CharSequence)coinId)) {
            booleanExpressions.add(QContractWallet.contractWallet.coin.name.eq(coinId));
        }
        if (status != null) {
            booleanExpressions.add(QContractWallet.contractWallet.status.eq(status));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
