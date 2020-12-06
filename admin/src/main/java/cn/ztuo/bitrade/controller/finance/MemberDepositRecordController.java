package cn.ztuo.bitrade.controller.finance;


import cn.ztuo.bitrade.annotation.MultiDataSource;
import cn.ztuo.bitrade.controller.common.BaseAdminController;
import cn.ztuo.bitrade.entity.Member;
import cn.ztuo.bitrade.entity.MemberDeposit;
import cn.ztuo.bitrade.model.screen.MemberDepositScreen;
import cn.ztuo.bitrade.service.MemberService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.types.dsl.BooleanExpression;
import cn.ztuo.bitrade.annotation.AccessLog;
import cn.ztuo.bitrade.constant.AdminModule;
import cn.ztuo.bitrade.constant.PageModel;
import cn.ztuo.bitrade.controller.common.BaseAdminController;
import cn.ztuo.bitrade.entity.QMember;
import cn.ztuo.bitrade.entity.QMemberDeposit;
import cn.ztuo.bitrade.model.screen.MemberDepositScreen;
import cn.ztuo.bitrade.service.LocaleMessageSourceService;
import cn.ztuo.bitrade.service.MemberDepositService;
import cn.ztuo.bitrade.util.MessageResult;
import cn.ztuo.bitrade.vo.MemberDepositVO;
import cn.ztuo.bitrade.controller.common.BaseAdminController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static cn.ztuo.bitrade.entity.QMember.member;

@RestController
@RequestMapping("finance/member-deposit")
@Api(tags = "充币记录")
public class MemberDepositRecordController extends BaseAdminController {
    @Autowired
    private MemberDepositService memberDepositService;
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private MemberService memberService;

    /**
     * 充币记录
     *
     * @param pageModel
     * @param screen
     * @return
     */
    @RequiresPermissions("finance:member-deposit:page-query")
    @PostMapping("page-query")
    //@AccessLog(module = AdminModule.FINANCE, operation = "充币记录")
    @ApiOperation(value = "分页获取充币记录")
    @MultiDataSource(name = "second")
    public MessageResult page(PageModel pageModel, MemberDepositScreen screen) {
        List<BooleanExpression> predicates = new ArrayList<>();
        predicates.add(QMember.member.id.eq(QMemberDeposit.memberDeposit.memberId));
        if (screen.getMemberId() != null)
            predicates.add((QMemberDeposit.memberDeposit.id.eq(screen.getMemberId())));
        if (!StringUtils.isEmpty(screen.getUnit()))
            predicates.add((QMemberDeposit.memberDeposit.unit.equalsIgnoreCase(screen.getUnit())));
        if (!StringUtils.isEmpty(screen.getAddress()))
            predicates.add((QMemberDeposit.memberDeposit.address.like("%"+ screen.getAddress() + "%")));
        if (screen.getStartTime()!=null)
            predicates.add((QMemberDeposit.memberDeposit.createTime.after(screen.getStartTime())));
        if (screen.getEndTime()!=null)
            predicates.add((QMemberDeposit.memberDeposit.createTime.before(DateUtils.addDays(screen.getEndTime(),1))));
        Pattern pattern = Pattern.compile("[0-9]*");
        if (!StringUtils.isEmpty(screen.getKeyWords())&&pattern.matcher(screen.getKeyWords()).matches()) {
            predicates.add(QMember.member.mobilePhone.like("%" + screen.getKeyWords() + "%")
                    .or(QMember.member.id.eq(Long.valueOf(screen.getKeyWords())))
                    .or(QMember.member.email.like("%" + screen.getKeyWords() + "%")));
        }else if(!StringUtils.isEmpty(screen.getKeyWords())){
            predicates.add(QMember.member.email.like("%" + screen.getKeyWords() + "%"));
        }
        Page<MemberDepositVO> page = memberDepositService.page(predicates, pageModel);
        return success(messageSource.getMessage("SUCCESS"), page);
    }


    @RequiresPermissions({ "finance:member-deposit:edit" })
    @PostMapping({ "add" })
    @AccessLog(module = AdminModule.FINANCE, operation = "新增充币记录")
    public MessageResult addMemberTransfer(final MemberDeposit memberDeposit, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date time) {
        if (memberDeposit.getMemberId() == null) {
            return MessageResult.error("用户ID不能为空!");
        }
        if (StringUtils.isEmpty(memberDeposit.getUnit())) {
            return MessageResult.error("币种不能为空!");
        }
        if (StringUtils.isEmpty(memberDeposit.getAddress())) {
            return MessageResult.error("充币地址不能为空!");
        }
        if (memberDeposit.getAmount() == null || memberDeposit.getAmount().compareTo(BigDecimal.ZERO) < 1) {
            return MessageResult.error("充币数量必须大于0!");
        }
        if (StringUtils.isEmpty(memberDeposit.getTxid())) {
            return MessageResult.error("充币哈希值不能为空!");
        }
        if (time == null) {
            return MessageResult.error("到账时间不能为空!");
        }
        memberDeposit.setCollectType(0);
        final Member member = this.memberService.findOne(memberDeposit.getMemberId());
        if (member == null) {
            return MessageResult.error("用户不存在!");
        }
        final MemberDeposit info = this.memberDepositService.getByTxid(memberDeposit.getTxid());
        if (info != null) {
            return MessageResult.error("哈希值不可重复!");
        }
        this.memberDepositService.save(memberDeposit);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "finance:member-deposit:edit" })
    @PostMapping({ "alter-collectType" })
    @AccessLog(module = AdminModule.FINANCE, operation = "修改归集状态")
    public MessageResult alterIsLock(@RequestParam("id") final Long id, @RequestParam("collectType") final Integer collectType) {
        if (id == null || collectType == null) {
            return MessageResult.error("参数错误!");
        }
        final int result = this.memberDepositService.updateCollectType(id, collectType);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }
}
