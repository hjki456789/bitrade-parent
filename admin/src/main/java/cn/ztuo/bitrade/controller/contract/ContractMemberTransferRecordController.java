package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.lang.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract/member-transfer-record" })
public class ContractMemberTransferRecordController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractMemberTransferRecordService contractMemberTransferRecordService;
    @Autowired
    private ContractMemberService contractMemberService;

    @RequiresPermissions({ "contract:member-transfer-record:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找投资者转移记录")
    public MessageResult memberTransferRecordList(final PageModel pageModel, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime, final Long memberId, final String username, final String mobilePhone) {
        final Predicate predicate = this.getPredicate(memberId, username, mobilePhone, startTime, endTime);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractMemberTransferRecord> all = (Page<ContractMemberTransferRecord>)this.contractMemberTransferRecordService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "contract:member-transfer-record:add" })
    @PostMapping({ "add-member-transfer" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "新增投资转移记录")
    public MessageResult addMemberTransfer(@RequestParam("memberId") final Long memberId, @RequestParam("newProxyMemberId") final Long newProxyMemberId) {
        if (memberId == null || newProxyMemberId == null) {
            return MessageResult.error("投资者账号与转入代理都不能为空!");
        }
        if (memberId.equals(newProxyMemberId)) {
            return MessageResult.error("投资者与转入代理不能为同一用户!");
        }
        final ContractMember contractMember = this.contractMemberService.findByMemberId(memberId.toString());
        if (contractMember == null || contractMember.getProxyId() == null) {
            return MessageResult.error("该投资者无相关代理信息!");
        }
        if (contractMember.getProxyId().equals(newProxyMemberId)) {
            return MessageResult.error("该投资者已属于该代理，无需转移!");
        }
        final int result = this.contractMemberService.addMemberTransfer(memberId, newProxyMemberId, contractMember.getProxyId(), Long.valueOf(contractMember.getVersion()));
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    public Predicate getPredicate(final Long memberId, final String username, final String mobilePhone, final Date startTime, final Date endTime) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (memberId != null) {
            booleanExpressions.add(QContractMemberTransferRecord.contractMemberTransferRecord.member.id.eq(memberId));
        }
        if (!StringUtils.isEmpty(username)) {
            booleanExpressions.add(QContractMemberTransferRecord.contractMemberTransferRecord.member.username.eq(username));
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            booleanExpressions.add(QContractMemberTransferRecord.contractMemberTransferRecord.member.mobilePhone.eq(mobilePhone));
        }
        if (startTime != null) {
            booleanExpressions.add(QContractMemberTransferRecord.contractMemberTransferRecord.createTime.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QContractMemberTransferRecord.contractMemberTransferRecord.createTime.lt(endTime));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
