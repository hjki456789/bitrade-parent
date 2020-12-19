package cn.ztuo.bitrade.controller;

import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.transform.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import java.math.*;
import cn.ztuo.bitrade.entity.*;

@RestController
@RequestMapping({ "/invest" })
public class DepositController extends BaseController
{
    @Autowired
    private DepositTypeInfoService depositTypeInfoService;
    @Autowired
    private MemberDepositInfoService memberDepositInfoService;
    @Autowired
    private DepositProfitInfoService depositProfitInfoService;
    @Autowired
    private DepositWalletService depositWalletService;

    @RequestMapping({ "/getDepositTypes" })
    public MessageResult getCoinBaseInfo() {
        final List<DepositTypeInfo> results = (List<DepositTypeInfo>)this.depositTypeInfoService.findAllByEnableList();
        return MessageResult.success("成功获取托管理财类型", (Object)results);
    }

    @RequestMapping({ "/getMemberDepositInfos" })
    public MessageResult getMemberDepositInfos(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "lastSequence", required = false) Long lastSequence) {
        if (lastSequence == null || lastSequence == 0L) {
            lastSequence = System.currentTimeMillis();
        }
        final List<MemberDepositInfo> results = (List<MemberDepositInfo>)this.memberDepositInfoService.findListByMemberId(Long.valueOf(authMember.getId()), lastSequence);
        if (results != null) {
            for (final MemberDepositInfo memberDepositInfo : results) {
                long remainDays = DateUtil.getDays(memberDepositInfo.getInvestTime().getTime(), System.currentTimeMillis());
                remainDays = memberDepositInfo.getDepositTypeInfo().getCycle() - remainDays;
                memberDepositInfo.setRemainDays(remainDays);
            }
        }
        return MessageResult.success("成功获取个人理财信息", (Object)results);
    }

    @RequestMapping({ "/add" })
    public MessageResult add(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "amount", required = true) final BigDecimal amount, @RequestParam(value = "depositTypeId", required = true) final long depositTypeId, @RequestParam(value = "fundPassword", required = true) final String fundPassword) {
        return this.memberDepositInfoService.addDeposit(Long.valueOf(authMember.getId()), amount, Long.valueOf(depositTypeId), fundPassword);
    }

    @RequestMapping({ "/getMemberDepositBalanceInfo" })
    public MessageResult getMemberDepositBalanceInfo(@SessionAttribute("API_MEMBER") final AuthMember authMember) {
        return this.memberDepositInfoService.getDepositBalanceInfo(Long.valueOf(authMember.getId()));
    }

    @RequestMapping({ "/getDepositProfitInfos" })
    public MessageResult getDepositProfitInfos(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "memberDepositId", required = true) final Long memberDepositId, @RequestParam(value = "lastSequence", required = false) Long lastSequence) {
        if (lastSequence == null || lastSequence == 0L) {
            lastSequence = System.currentTimeMillis();
        }
        final List<DepositProfitInfo> results = (List<DepositProfitInfo>)this.depositProfitInfoService.findListByDepositId(memberDepositId, lastSequence);
        return MessageResult.success("success", (Object)results);
    }

    @RequestMapping({ "/breakDeposit" })
    public MessageResult breakDeposit(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "memberDepositId", required = true) final Long memberDepositId, @RequestParam(value = "fundPassword", required = true) final String fundPassword) {
        return this.memberDepositInfoService.breakDeposit(Long.valueOf(authMember.getId()), memberDepositId, fundPassword);
    }

    @RequestMapping({ "/getMemberDepositWallet" })
    public MessageResult getMemberDepositWallet(@SessionAttribute("API_MEMBER") final AuthMember authMember) {
        final DepositWallet depositWallet = this.depositWalletService.getDepositWalletByMemberIdAndCoin(Long.valueOf(authMember.getId()), "USDT");
        return MessageResult.success("success", (Object)depositWallet);
    }
}
