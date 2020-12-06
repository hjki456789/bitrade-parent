package cn.ztuo.bitrade.controller.finance;

import cn.ztuo.bitrade.annotation.AccessLog;
import cn.ztuo.bitrade.annotation.MultiDataSource;
import cn.ztuo.bitrade.constant.AdminModule;
import cn.ztuo.bitrade.constant.PageModel;
import cn.ztuo.bitrade.constant.TransactionType;
import cn.ztuo.bitrade.controller.common.BaseAdminController;
import cn.ztuo.bitrade.entity.Admin;
import cn.ztuo.bitrade.entity.MemberTransaction;
import cn.ztuo.bitrade.entity.QMember;
import cn.ztuo.bitrade.entity.QMemberTransaction;
import cn.ztuo.bitrade.model.screen.MemberTransactionScreen;
import cn.ztuo.bitrade.service.LocaleMessageSourceService;
import cn.ztuo.bitrade.service.MemberPromotionService;
import cn.ztuo.bitrade.service.MemberTransactionService;
import cn.ztuo.bitrade.util.DateUtil;
import cn.ztuo.bitrade.util.FileUtil;
import cn.ztuo.bitrade.util.MessageResult;
import cn.ztuo.bitrade.util.PredicateUtils;
import cn.ztuo.bitrade.vo.MemberTransactionVO;
import cn.ztuo.bitrade.vo.TransactionTypeVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.util.Assert.notNull;

/**
 * @author MrGao
 * @description 交易记录
 * @date 2018/1/17 17:07
 */
@RestController
@RequestMapping("/finance/member-transaction")
@Api(tags = "交易记录")
public class MemberTransactionController extends BaseAdminController {
    @Autowired
    private LocaleMessageSourceService messageSource;

    @Autowired
    private MemberTransactionService memberTransactionService;

    @Autowired
    private MemberPromotionService memberPromotionService;

    @RequiresPermissions("finance:member-transaction:page-query")
    @PostMapping("/all")
    //@AccessLog(module = AdminModule.FINANCE, operation = "所有交易记录MemberTransaction")
    @ApiOperation(value = "所有交易记录")
    @MultiDataSource(name = "second")
    public MessageResult all() {
        List<MemberTransaction> memberTransactionList = memberTransactionService.findAll();
        if (memberTransactionList != null && memberTransactionList.size() > 0)
            return success(memberTransactionList);
        return error(messageSource.getMessage("NO_DATA"));
    }

    @RequiresPermissions("finance:member-transaction:page-query")
    @PostMapping("detail")
    //@AccessLog(module = AdminModule.FINANCE, operation = "交易记录MemberTransaction 详情")
    @ApiOperation(value = "交易记录详情")
    @MultiDataSource(name = "second")
    public MessageResult detail(@RequestParam(value = "id") Long id) {
        MemberTransaction memberTransaction = memberTransactionService.findOne(id);
        notNull(memberTransaction, "validate id!");
        return success(memberTransaction);
    }

    @RequiresPermissions(value = {"finance:member-transaction:page-query", "finance:member-transaction:page-query:recharge",
            "finance:member-transaction:page-query:check", "finance:member-transaction:page-query:fee"}, logical = Logical.OR)
    @PostMapping("page-query")
    //@AccessLog(module = AdminModule.FINANCE, operation = "分页查找交易记录MemberTransaction")
    @ApiOperation(value = "分页查找交易记录")
    @MultiDataSource(name = "second")
    public MessageResult pageQuery(
            PageModel pageModel,
            MemberTransactionScreen screen) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(QMemberTransaction.memberTransaction.memberId.eq(QMember.member.id));
        if(screen.getMemberId()!=null)
            predicates.add((QMember.member.id.eq(screen.getMemberId())));
        if (!StringUtils.isEmpty(screen.getAccount()))
            predicates.add(QMember.member.username.like("%"+screen.getAccount()+"%")
                        .or(QMember.member.realName.like("%"+screen.getAccount()+"%")));
        if (screen.getStartTime() != null)
            predicates.add(QMemberTransaction.memberTransaction.createTime.goe(screen.getStartTime()));
        if (screen.getEndTime() != null){
            predicates.add(QMemberTransaction.memberTransaction.createTime.lt(DateUtil.dateAddDay(screen.getEndTime(),1)));
        }
        if (screen.getType() != null)
            predicates.add(QMemberTransaction.memberTransaction.type.eq(screen.getType()));

        if(screen.getMinMoney()!=null)
            predicates.add(QMemberTransaction.memberTransaction.amount.goe(screen.getMinMoney()));

        if(screen.getMaxMoney()!=null)
            predicates.add(QMemberTransaction.memberTransaction.amount.loe(screen.getMaxMoney()));

        if(screen.getMinFee()!=null)
            predicates.add(QMemberTransaction.memberTransaction.fee.goe(screen.getMinFee()));

        if(screen.getMaxFee()!=null)
            predicates.add(QMemberTransaction.memberTransaction.fee.loe(screen.getMaxFee()));

        if(screen.getSymbol()!=null&&!screen.getSymbol().equalsIgnoreCase("")){
            predicates.add(QMemberTransaction.memberTransaction.symbol.eq(screen.getSymbol()));
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        if (!StringUtils.isEmpty(screen.getKeyWords())&&pattern.matcher(screen.getKeyWords()).matches()) {
            predicates.add(QMember.member.mobilePhone.like("%" + screen.getKeyWords() + "%")
                    .or(QMember.member.id.eq(Long.valueOf(screen.getKeyWords())))
                    .or(QMember.member.email.like("%" + screen.getKeyWords() + "%")));
        }else if(!StringUtils.isEmpty(screen.getKeyWords())){
            predicates.add(QMember.member.email.like("%" + screen.getKeyWords() + "%"));
        }
        Page<MemberTransactionVO> results = memberTransactionService.joinFind(predicates, pageModel);

        return success(results);
    }

    @RequiresPermissions(value = {"finance:member-transaction:page-query", "finance:member-transaction:page-query:recharge",
            "finance:member-transaction:page-query:check", "finance:member-transaction:page-query:fee"}, logical = Logical.OR)
    @PostMapping("transaction/page-query")
    //@AccessLog(module = AdminModule.FINANCE, operation = "分页查找交易记录MemberTransaction")
    @ApiOperation(value = "分页查找划转记录")
    @MultiDataSource(name = "second")
    public MessageResult transactionPageQuery(
            PageModel pageModel,
            MemberTransactionScreen screen) {
        List<Predicate> predicates = new ArrayList<>();
        if (screen.getStartTime() != null)
            predicates.add(QMemberTransaction.memberTransaction.createTime.goe(screen.getStartTime()));
        if (screen.getEndTime() != null){
            predicates.add(QMemberTransaction.memberTransaction.createTime.lt(DateUtil.dateAddDay(screen.getEndTime(),1)));
        }
        predicates.add(QMemberTransaction.memberTransaction.type.in(TransactionType.TRANSFER_ACCOUNTS,TransactionType.COIN_TWO_OTC,TransactionType.OTC_TWO_COIN));

        if(screen.getSymbol()!=null&&!screen.getSymbol().equalsIgnoreCase("")){
            predicates.add(QMemberTransaction.memberTransaction.symbol.eq(screen.getSymbol()));
        }
        if(!StringUtils.isEmpty(screen.getKeyWords())){
            if(screen.getMemberId()!=null)
                predicates.add((QMember.member.id.eq(screen.getMemberId())));
            Pattern pattern = Pattern.compile("[0-9]*");
            if (pattern.matcher(screen.getKeyWords()).matches()) {
                predicates.add(QMember.member.mobilePhone.like("%" + screen.getKeyWords() + "%")
                        .or(QMember.member.id.eq(Long.valueOf(screen.getKeyWords())))
                        .or(QMember.member.email.like("%" + screen.getKeyWords() + "%")));
            }else{
                predicates.add(QMember.member.email.like("%" + screen.getKeyWords() + "%"));
            }
            return success(memberTransactionService.joinFind(predicates, pageModel));
        }
        return success(memberTransactionService.queryWhereOrPage(predicates,pageModel));
    }


    @PostMapping({ "transactionTypeList" })
    public MessageResult transactionTypeList() {
        final List<TransactionTypeVO> resultList = new ArrayList<TransactionTypeVO>();
        final TransactionType[] arr = TransactionType.values();
        final List<TransactionType> list = Arrays.asList(arr);
        for (final TransactionType type : list) {
            final TransactionTypeVO vo = new TransactionTypeVO();
            vo.setCode(Integer.valueOf(type.getOrdinal()));
            vo.setName(type.getCnName());
            resultList.add(vo);
        }
        return this.success(resultList);
    }

    @RequiresPermissions({ "finance:member-transaction:page-query-list" })
    @PostMapping({ "page-query-list" })
    @AccessLog(module = AdminModule.FINANCE, operation = "分页查找出入金报表")
    public MessageResult memberTransactionList(final PageModel pageModel, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final String mobilePhone, final String email, Long proxyId, final MemberTransaction memberTransaction, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime) {
        if (admin != null && admin.getProxyId() != null) {
            proxyId = admin.getProxyId();
        }
        List<Long> memberIdList = null;
        if (proxyId != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findTeamMembers((List)memberIdList, proxyId, true, false);
        }
        if (!CollectionUtils.isEmpty(memberIdList)) {
            if (memberTransaction.getMemberId() != null) {
                if (!memberIdList.contains(memberTransaction.getMemberId())) {
                    return this.success(new PageImpl((List)new ArrayList()));
                }
                memberIdList = new ArrayList<Long>();
                memberIdList.add(memberTransaction.getMemberId());
            }
        }
        else {
            memberIdList = new ArrayList<Long>();
            if (memberTransaction.getMemberId() != null) {
                memberIdList.add(memberTransaction.getMemberId());
            }
        }
        Predicate predicate = null;
        final List<BooleanExpression> booleanExpressions = this.getBooleanExpressions(memberTransaction, memberIdList, mobilePhone, email, startTime, endTime);
        if (!CollectionUtils.isEmpty(booleanExpressions)) {
            predicate = PredicateUtils.getPredicate((List)booleanExpressions);
        }
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<MemberTransaction> all = (Page<MemberTransaction>)this.memberTransactionService.findAll(predicate, pageModel);
        return this.success(all);
    }

    @GetMapping({ "out-excel-list" })
    @AccessLog(module = AdminModule.FINANCE, operation = "导出出入金报表")
    public MessageResult outExcel(final PageModel pageModel, final MemberTransaction memberTransaction, @SessionAttribute("ADMIN_MEMBER") final Admin admin, final String mobilePhone, final String email, Long proxyId, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date startTime, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") final Date endTime, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if (admin != null && admin.getProxyId() != null) {
            proxyId = admin.getProxyId();
        }
        List<Long> memberIdList = null;
        if (proxyId != null) {
            memberIdList = (List<Long>)this.memberPromotionService.findMemberIdByInviterId(proxyId);
        }
        if (!CollectionUtils.isEmpty(memberIdList)) {
            if (memberTransaction.getMemberId() != null) {
                if (!memberIdList.contains(memberTransaction.getMemberId())) {
                    return this.success(new PageImpl(new ArrayList()));
                }
                memberIdList = new ArrayList<Long>();
                memberIdList.add(memberTransaction.getMemberId());
            }
        }
        else {
            memberIdList = new ArrayList<Long>();
            if (memberTransaction.getMemberId() != null) {
                memberIdList.add(memberTransaction.getMemberId());
            }
        }
        final List<BooleanExpression> booleanExpressionList = this.getBooleanExpressions(memberTransaction, memberIdList, mobilePhone, email, startTime, endTime);
        final List list = this.memberTransactionService.queryWhereOrPage(booleanExpressionList, pageModel.getPageNo(), pageModel.getPageSize()).getContent();
        return new FileUtil().exportExcel(request, response, list, "member_transaction");
    }

    private List<BooleanExpression> getBooleanExpressions(final MemberTransaction memberTransaction, final List<Long> memberIdList, final String mobilePhone, final String email, final Date startTime, final Date endTime) {
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        final List<TransactionType> statusList = new ArrayList<TransactionType>();
        if (memberTransaction.getType() != null) {
            statusList.add(memberTransaction.getType());
        }
        else {
            statusList.add(TransactionType.RECHARGE);
            statusList.add(TransactionType.WITHDRAW);
            statusList.add(TransactionType.OTC_BUY);
            statusList.add(TransactionType.OTC_SELL);
        }
        if (statusList != null && statusList.size() > 0) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.type.in(statusList));
        }
        if (!CollectionUtils.isEmpty(memberIdList)) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.member.id.in(memberIdList));
        }
        if (!StringUtils.isEmpty(memberTransaction.getSymbol())) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.symbol.eq(memberTransaction.getSymbol()));
        }
        if (!org.apache.commons.lang3.StringUtils.isEmpty((CharSequence)mobilePhone)) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        if (!org.apache.commons.lang3.StringUtils.isEmpty((CharSequence)email)) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.member.email.like("%" + email + "%"));
        }
        if (startTime != null) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.createTime.goe(startTime));
        }
        if (endTime != null) {
            booleanExpressions.add(QMemberTransaction.memberTransaction.createTime.lt(endTime));
        }
        return booleanExpressions;
    }



}
