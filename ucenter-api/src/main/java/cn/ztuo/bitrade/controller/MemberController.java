package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.constant.BooleanEnum;
import cn.ztuo.bitrade.constant.CommonStatus;
import cn.ztuo.bitrade.constant.PageModel;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.entity.transform.AuthMember;
import cn.ztuo.bitrade.entity.unblock.UnblockMemberReward;
import cn.ztuo.bitrade.pagination.PageResult;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.service.unblock.UnblockMemberRewardService;
import cn.ztuo.bitrade.util.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.ztuo.bitrade.constant.SysConstant.SESSION_MEMBER;

/**
 * @author MrGao
 * @Description:
 * @date 2018/5/49:30
 */
@RestController
@RequestMapping("member")
@Api(tags = "用户相关")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SignService signService;
    @Autowired
    private MemberWalletService walletService;

    @Autowired
    private MemberLoginRecordService memberLoginRecordService;

    @Autowired
    private MemberVerifyRecordService memberVerifyRecordService;

    @Autowired
    private MemberTeamService memberTeamService;
    @Autowired
    private UnblockMemberRewardService unblockMemberRewardService;



    /**
     * 签到
     * @param user
     * @return
     */
    @PostMapping("sign-in")
    @ApiOperation(value = "签到")
    public MessageResult signIn(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember user) {
        //校验 签到活动 币种 会员 会员钱包
        Assert.notNull(user, "The login timeout!");
        Sign sign = signService.fetchUnderway();
        Assert.notNull(sign, "The check-in activity is over!");

        Coin coin = sign.getCoin();
        Assert.isTrue(coin.getStatus() == CommonStatus.NORMAL, "coin disabled!");

        Member member = memberService.findOne(user.getId());
        Assert.notNull(member, "validate member id!");
        Assert.isTrue(member.getSignInAbility() == true, "Have already signed in!");

        MemberWallet memberWallet = walletService.findByCoinAndMember(coin, member);
        Assert.notNull(memberWallet, "Member wallet does not exist!");
        Assert.isTrue(memberWallet.getIsLock() == BooleanEnum.IS_FALSE, "Wallet locked!");
        //签到事件
        memberService.signInIncident(member, memberWallet, sign);
        return success();
    }

    /**
     * 查询用户登录记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loginRecord",method = RequestMethod.POST)
    @ApiOperation(value = "登录记录")
    public MessageResult getLoginRecord(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember user,
                                                        PageModel pageModel) {
        PageResult<MemberLoginRecord> result = memberLoginRecordService.query(user.getId(),pageModel.getPageNo(),pageModel.getPageSize());
        return success(result);
    }

    /**
     * 法币设置--提醒
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setRemind",method = RequestMethod.POST)
    @ApiOperation(value = "法币设置--提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "way", value = "email/sms", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remind", value = "提醒方式（0：接收、1：不接收）", required = false, dataType = "String"),
    })
    public MessageResult setRemind(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember user,String way,String remind) {
        Member member = memberService.findOne(user.getId());
        if(way.equalsIgnoreCase("email"))
            member.setEmailRemind(remind);
        if(way.equalsIgnoreCase("sms"))
            member.setSmsRemind(remind);
        memberService.saveAndFlush(member);
        return success(member);
    }


    /**
     * 查询用户安全记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "verifyRecord",method = RequestMethod.POST)
    @ApiOperation(value = "安全记录")
    public MessageResult getVerifyRecord(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember user,
                                        PageModel pageModel) {
        PageResult<MemberVerifyRecord> result = memberVerifyRecordService.query(user.getId(),pageModel.getPageNo(),pageModel.getPageSize());
        return success(result);
    }


    @PostMapping({ "/getRewardStatisticsInfo" })
    public MessageResult getRewardStatisticsInfo(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "uid", required = false) final Long uid) {
        final Member member = this.memberService.findOne(Long.valueOf(authMember.getId()));
        Assert.notNull((Object)member, "无效的用户");
        final long memberCount = this.memberTeamService.countTeamNumByMemberId(authMember.getId());
        final List<Object[]> memberRewards = (List<Object[]>)this.unblockMemberRewardService.statisticsMemberRewards(authMember.getId(), uid);
        final List<Map<String, Object>> rewardMap = new ArrayList<Map<String, Object>>();
        if (null != memberRewards) {
            for (final Object[] memberReward : memberRewards) {
                final String coin = memberReward[0].toString();
                final BigDecimal amount = new BigDecimal(memberReward[1].toString());
                final Map<String, Object> coinMap = new HashMap<String, Object>();
                coinMap.put("coin", coin);
                coinMap.put("amount", amount);
                rewardMap.add(coinMap);
            }
        }
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("teamMemberNum", memberCount);
        map.put("memberRewards", rewardMap);
        return MessageResult.success("SUCCESS", (Object)map);
    }

    @PostMapping({ "/getRewardRecordList" })
    public MessageResult getRewardRecordList(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "coin", required = false) String coin, @RequestParam(value = "uid", required = false) final Long uid, final int pageNo, final int pageSize) {
        coin = "USDT";
        final Page<UnblockMemberReward> list = (Page<UnblockMemberReward>)this.unblockMemberRewardService.getRewardRecordList(authMember.getId(), uid, coin, pageNo, pageSize);
        if (null == list) {
            return MessageResult.error("\u65e0\u8bb0\u5f55");
        }
        return MessageResult.success("SUCCESS", (Object)list);
    }
}
