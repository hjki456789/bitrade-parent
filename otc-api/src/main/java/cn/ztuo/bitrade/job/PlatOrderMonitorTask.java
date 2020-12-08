package cn.ztuo.bitrade.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.event.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.beans.*;
import cn.ztuo.bitrade.exception.*;
import java.util.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@Component
@Slf4j
public class PlatOrderMonitorTask
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LocaleMessageSourceService msService;
    @Autowired
    private AdvertiseService advertiseService;
    @Autowired
    private OtcWalletService otcWalletService;
    @Autowired
    private DataDictionaryService dictionaryService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private MemberGradeService gradeService;
    @Autowired
    private IntegrationRecordService integrationRecordService;
    @Autowired
    private OrderEvent orderEvent;
    @Autowired
    private OrderDetailAggregationService orderDetailAggregationService;

    @Scheduled(fixedRate = 5000L)
    @Transactional(rollbackFor = { Exception.class })
    public void platOrderMonitor() {
        log.info("=========开始检查平台订单===========");
        final List<Order> list = this.orderService.getPlatOrderList();
        if (list != null && list.size() > 0) {
            list.stream().forEach(x -> {
                try {
                    boolean orderStatus = PayUtils.querryOrderStatus(x.getOrderSn());
                    if (orderStatus) {
                        log.info("===========开始自动更新订单==========" + x.getOrderSn());
                        boolean payOrderStatus = true;
                        if (x.getStatus().equals((Object)OrderStatus.NONPAYMENT)) {
                            payOrderStatus = this.payOrder(x.getOrderSn(), x.getCustomerId());
                        }
                        if (payOrderStatus) {
                            this.confirmRelease(x.getOrderSn(), x.getMemberId());
                        }
                    }
                }
                catch (Exception e) {
                    log.error("订单自动更新失败",e);
                    log.warn("订单编号{}:自动更新失败", (Object)x.getOrderSn());
                }
                return;
            });
        }
        log.info("=========检查平台订单结束===========");
    }

    @Transactional(rollbackFor = { Exception.class })
    public boolean payOrder(final String orderSn, final Long userId) throws InformationExpiredException {
        final Order order = this.orderService.findOneByOrderSn(orderSn);
        Assert.notNull((Object)order, this.msService.getMessage("ORDER_NOT_EXISTS"));
        int ret = 0;
        if (order.getAdvertiseType().equals((Object)AdvertiseType.BUY) && order.getMemberId().equals(userId)) {
            ret = 1;
        }
        else if (order.getAdvertiseType().equals((Object)AdvertiseType.SELL) && order.getCustomerId().equals(userId)) {
            ret = 2;
        }
        Assert.isTrue(ret != 0, this.msService.getMessage("REQUEST_ILLEGAL"));
        Assert.isTrue(order.getStatus().equals((Object)OrderStatus.NONPAYMENT), this.msService.getMessage("ORDER_STATUS_EXPIRED"));
        Assert.isTrue(BigDecimalUtils.compare(new BigDecimal(order.getTimeLimit()), DateUtil.diffMinute(order.getCreateTime())), this.msService.getMessage("ORDER_ALREADY_AUTO_CANCEL"));
        final int is = this.orderService.payForOrder(orderSn);
        if (is > 0) {
            final OrderDetailAggregation aggregation = new OrderDetailAggregation();
            BeanUtils.copyProperties((Object)order, (Object)aggregation);
            aggregation.setUnit(order.getCoin().getUnit());
            aggregation.setOrderId(order.getOrderSn());
            aggregation.setFee(order.getCommission().doubleValue());
            aggregation.setAmount(order.getNumber().doubleValue());
            aggregation.setType(OrderTypeEnum.OTC);
            aggregation.setTime(Calendar.getInstance().getTimeInMillis());
            this.orderDetailAggregationService.save(aggregation);
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = { Exception.class })
    public void confirmRelease(final String orderSn, final Long userId) throws Exception {
        final Member member = this.memberService.findOne(userId);
        final Order order = this.orderService.findOneByOrderSn(orderSn);
        Assert.notNull((Object)order, this.msService.getMessage("ORDER_NOT_EXISTS"));
        int ret = 0;
        if (order.getAdvertiseType().equals((Object)AdvertiseType.BUY) && order.getCustomerId().equals(userId)) {
            ret = 1;
        }
        else if (order.getAdvertiseType().equals((Object)AdvertiseType.SELL) && order.getMemberId().equals(userId)) {
            ret = 2;
        }
        Assert.isTrue(ret != 0, this.msService.getMessage("REQUEST_ILLEGAL"));
        if (ret == 1) {
            if (!this.advertiseService.updateAdvertiseAmountForRelease((long)order.getAdvertiseId(), order.getNumber())) {
                throw new InformationExpiredException("Information Expired");
            }
        }
        else if (!this.advertiseService.updateAdvertiseAmountForRelease((long)order.getAdvertiseId(), BigDecimalUtils.add(order.getNumber(), order.getCommission()))) {
            throw new InformationExpiredException("Information Expired");
        }
        if (this.orderService.releaseOrder(order.getOrderSn()) <= 0) {
            throw new InformationExpiredException("Information Expired");
        }
        this.otcWalletService.transfer(order, ret);
        final DataDictionary dictionary = this.dictionaryService.findByBond("integration_giving_otc_buy_cny_rate");
        if (ret == 1) {
            final MemberTransaction memberTransaction = new MemberTransaction();
            memberTransaction.setSymbol(order.getCoin().getUnit());
            memberTransaction.setType(TransactionType.OTC_SELL);
            memberTransaction.setFee(BigDecimal.ZERO);
            memberTransaction.setFee(order.getCommission());
            memberTransaction.setMemberId(userId);
            memberTransaction.setAmount(BigDecimal.ZERO.subtract(order.getNumber()));
            this.memberTransactionService.save(memberTransaction);
            final MemberTransaction memberTransaction2 = new MemberTransaction();
            memberTransaction2.setAmount(order.getNumber());
            memberTransaction2.setType(TransactionType.OTC_BUY);
            memberTransaction2.setMemberId(order.getMemberId());
            memberTransaction2.setSymbol(order.getCoin().getUnit());
            memberTransaction2.setFee(order.getCommission());
            memberTransaction2.setFee(BigDecimal.ZERO);
            this.memberTransactionService.save(memberTransaction2);
            try {
                final Long merchantsMemberId = order.getMemberId();
                final BigDecimal money = order.getMoney().multiply(new BigDecimal(dictionary.getValue()));
                final Member merchantsMember = this.memberService.findOne(merchantsMemberId);
                final Long integration = money.setScale(0).longValue();
                final Long totalIntegration = integration + merchantsMember.getIntegration();
                merchantsMember.setIntegration(totalIntegration);
                final MemberGrade grade = this.gradeService.findOne(merchantsMember.getMemberGradeId());
                if (grade.getId() != 5L && grade.getId() != 6L && grade.getGradeBound() < totalIntegration) {
                    merchantsMember.setMemberGradeId(Long.valueOf(merchantsMember.getMemberGradeId() + 1L));
                }
                this.memberService.save(merchantsMember);
                final IntegrationRecord integrationRecord = new IntegrationRecord();
                integrationRecord.setAmount(integration);
                integrationRecord.setMemberId(merchantsMember.getId());
                integrationRecord.setCreateTime(new Date());
                integrationRecord.setType(IntegrationRecordType.LEGAL_RECHARGE_GIVING);
                this.integrationRecordService.save(integrationRecord);
            }
            catch (Exception e) {
                log.info("法币充值积分赠送失败,={}", (Throwable)e);
            }
        }
        else {
            final MemberTransaction memberTransaction = new MemberTransaction();
            memberTransaction.setSymbol(order.getCoin().getUnit());
            memberTransaction.setType(TransactionType.OTC_SELL);
            memberTransaction.setFee(order.getCommission());
            memberTransaction.setMemberId(userId);
            memberTransaction.setAmount(BigDecimal.ZERO.subtract(order.getNumber()));
            this.memberTransactionService.save(memberTransaction);
            final MemberTransaction memberTransaction2 = new MemberTransaction();
            memberTransaction2.setAmount(order.getNumber());
            memberTransaction2.setType(TransactionType.OTC_BUY);
            memberTransaction2.setMemberId(order.getCustomerId());
            memberTransaction2.setSymbol(order.getCoin().getUnit());
            memberTransaction2.setFee(BigDecimal.ZERO);
            this.memberTransactionService.save(memberTransaction2);
            try {
                final Long customerMemberId = order.getCustomerId();
                final BigDecimal money = order.getMoney().multiply(new BigDecimal(dictionary.getValue()));
                final Member customersMember = this.memberService.findOne(customerMemberId);
                final Long integration = money.setScale(0).longValue();
                final Long totalIntegration = integration + customersMember.getIntegration();
                customersMember.setIntegration(totalIntegration);
                final MemberGrade grade = this.gradeService.findOne(customersMember.getMemberGradeId());
                if (grade.getId() != 5L && grade.getId() != 6L && grade.getGradeBound() < totalIntegration) {
                    customersMember.setMemberGradeId(Long.valueOf(customersMember.getMemberGradeId() + 1L));
                }
                this.memberService.save(customersMember);
                final IntegrationRecord integrationRecord = new IntegrationRecord();
                integrationRecord.setAmount(integration);
                integrationRecord.setMemberId(customersMember.getId());
                integrationRecord.setCreateTime(new Date());
                integrationRecord.setType(IntegrationRecordType.LEGAL_RECHARGE_GIVING);
                this.integrationRecordService.save(integrationRecord);
            }
            catch (Exception e) {
                log.info("法币充值积分则送失败={}", (Throwable)e);
            }
        }
        this.orderEvent.onOrderCompleted(order);
    }
}
