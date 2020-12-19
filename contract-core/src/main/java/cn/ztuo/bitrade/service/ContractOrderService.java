package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import org.apache.commons.collections.*;

import java.io.*;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.*;
import org.springframework.data.domain.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.pagination.*;

import java.util.*;

import cn.ztuo.bitrade.entity.enumConstants.*;
import org.springframework.data.jpa.domain.*;
import org.hibernate.transform.*;
import cn.ztuo.bitrade.constant.*;

import java.math.*;

import cn.ztuo.bitrade.entity.*;

@Service
public class ContractOrderService extends BaseService<ContractExchangeOrder> {
    @Autowired
    private ContractOrderRepository contractOrderRepository;
    @Autowired
    private ContractExchangeOrderCalculateRepository contractExchangeOrderCalculateRepository;
    @Autowired
    private ContractExchangeOrderInterestRepository contractExchangeOrderInterestRepository;
    @Autowired
    private ContractExchangeOrderTiggerRepository contractExchangeOrderTiggerRepository;
    @Autowired
    ContractExchangeOrderCloseRepository contractExchangeOrderCloseRepository;

    public Page<ContractExchangeOrder> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<ContractExchangeOrder> page = (Page<ContractExchangeOrder>) this.contractOrderRepository.findAll(predicate, pageable);
        if (page != null && CollectionUtils.isNotEmpty((Collection) page.getContent())) {
            for (ContractExchangeOrder order : page.getContent()) {
                order = this.getOrderInfo(order);
            }
        }
        return page;
    }

    private ContractExchangeOrder getOrderInfo(final ContractExchangeOrder order) {
        if (order.getOrderFee() != null) {
            order.setFee(order.getOrderFee().getOpenFee().add(order.getOrderFee().getCloseFee()));
        }
        final ContractExchangeOrderCalculate contractExchangeOrderCalculate = (ContractExchangeOrderCalculate) this.contractExchangeOrderCalculateRepository.getOne(order.getId());
        if (contractExchangeOrderCalculate != null) {
            order.setCalculateProfit(contractExchangeOrderCalculate.getProfit());
        }
        final BigDecimal sumInterest = this.contractExchangeOrderInterestRepository.sumInterestBalanceByOrderId(order.getId());
        if (sumInterest != null) {
            order.setHoldInterest(sumInterest);
        }
        if (order.getContractMember() != null && order.getContractMember().getContractNode() != null) {
            order.setNodeName(order.getContractMember().getContractNode().getNodeName());
        }
        if (ContractOrderType.MARKET_PRICE.equals(order.getType())) {
            order.setBuyTime(order.getCreateTime());
        } else if (ContractOrderType.LIMIT_PRICE.equals(order.getType())) {
            final ContractExchangeOrderTigger contractExchangeOrderTigger = (ContractExchangeOrderTigger) this.contractExchangeOrderTiggerRepository.getOne(order.getId());
            if (contractExchangeOrderTigger != null) {
                order.setBuyTime(contractExchangeOrderTigger.getCreateTime());
            }
        }
        return order;
    }

    public BigDecimal getSumAmountOneDay(final Long memberId, final Long leaderMemberId, final Date startDate, final Date endDate) {
        final BigDecimal bigDecimal = this.contractOrderRepository.sumAmountOneDay(memberId, leaderMemberId, startDate, endDate);
        return (bigDecimal == null) ? BigDecimal.ZERO : bigDecimal;
    }

    public PageResult<ContractExchangeOrder> outExcel(final List<BooleanExpression> booleanExpressions, final PageModel pageModel) {
        final Integer pageNo = pageModel.getPageNo();
        final Integer pageSize = pageModel.getPageSize();
        final JPAQuery<ContractExchangeOrder> jpaQuery = (JPAQuery<ContractExchangeOrder>) this.queryFactory.selectFrom((EntityPath) QContractExchangeOrder.contractExchangeOrder);
        if (booleanExpressions != null) {
            jpaQuery.where((Predicate[]) booleanExpressions.toArray((Predicate[]) new BooleanExpression[booleanExpressions.size()]));
        }
        List<ContractExchangeOrder> list;
        if (pageNo != null && pageSize != null) {
            list = (List<ContractExchangeOrder>) ((JPAQuery) ((JPAQuery) jpaQuery.offset((long) ((pageNo - 1) * pageSize))).limit((long) pageSize)).fetch();
        } else {
            list = (List<ContractExchangeOrder>) jpaQuery.fetch();
        }
        return (PageResult<ContractExchangeOrder>) new PageResult((List) list, Long.valueOf(jpaQuery.fetchCount()));
    }

    public ContractExchangeOrder saveOrder(final ContractExchangeOrder exchangeOrder) {
        return (ContractExchangeOrder) this.contractOrderRepository.saveAndFlush(exchangeOrder);
    }

    public Page<ContractExchangeOrder> getHoldingOrders(final Long memberId, final String symbol, final int pageNum, final int pageSize, final Integer contractType, final Integer walletType) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, orders);
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        if (contractType != null) {
            specification.add((Criterion) Restrictions.eq("contractType", contractType, true));
        }
        if (walletType != null) {
            specification.add((Criterion) Restrictions.eq("walletType", walletType, true));
        }
        specification.add((Criterion) Restrictions.eq("member", memberId, true));
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.HOLD_PROCESSING);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        return (Page<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public Page<ContractExchangeOrder> getOrdersResultHistory(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        if (contractType != null) {
            specification.add((Criterion) Restrictions.eq("contractType", contractType, true));
        }
        if (walletType != null) {
            specification.add((Criterion) Restrictions.eq("walletType", walletType, true));
        }
        specification.add((Criterion) Restrictions.eq("member", memberId, true));
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.SELF_CLOSE);
        list.add(ContractOrderStatus.FORCE_CLOSE);
        list.add(ContractOrderStatus.EXPOLDE_WAREHOUSE);
        list.add(ContractOrderStatus.THROUGH_WAREHOUSE);
        list.add(ContractOrderStatus.STOP_PROFIT);
        list.add(ContractOrderStatus.STOP_LOSS);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        return (Page<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public Page<ContractExchangeOrder> getLimitOrders(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        if (contractType != null) {
            specification.add((Criterion) Restrictions.eq("contractType", contractType, true));
        }
        if (walletType != null) {
            specification.add((Criterion) Restrictions.eq("walletType", walletType, true));
        }
        specification.add((Criterion) Restrictions.eq("member", memberId, true));
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.HANG_PROCESSING);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        specification.add((Criterion) Restrictions.eq("type", ContractOrderType.LIMIT_PRICE, true));
        return (Page<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public Page getLimitProfitOrdersResult(final int pageNo, final int pageSize) {
        final PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(pageNo));
        pageModel.setPageSize(Integer.valueOf(pageSize));
        final StringBuilder countSql = new StringBuilder("SELECT count(*)");
        final StringBuilder headSql = new StringBuilder("SELECT  a.id,a.member_id,a.side,a.symbol,a.open_price,a.create_time,a.lever_multiple,b.order_id,b.close_price,b.profit_or_loss,b.close_profit_or_loss,b.type,b.sequence,b.version,b.create_time as time,c.avatar,c.username");
        final StringBuilder endSql = new StringBuilder(" FROM ( contract_exchange_order_close b LEFT JOIN contract_exchange_order a ON  a.id=b.order_id ) LEFT JOIN member c  ON c.id=a.member_id WHERE b.close_profit_or_loss>0");
        final Page page = this.createNativePageQuery(countSql.append((CharSequence) endSql), headSql.append((CharSequence) endSql.append(" ORDER BY time DESC")), pageModel, (ResultTransformer) Transformers.ALIAS_TO_ENTITY_MAP);
        return page;
    }

    public Page<ContractExchangeOrder> getLimitOrdersResultHistory(final Long memberId, final String symbol, final int pageNo, final int pageSize, final Integer contractType, final Integer walletType) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        if (contractType != null) {
            specification.add((Criterion) Restrictions.eq("contractType", contractType, true));
        }
        if (walletType != null) {
            specification.add((Criterion) Restrictions.eq("walletType", walletType, true));
        }
        specification.add((Criterion) Restrictions.eq("member", memberId, true));
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.CANCEL);
        list.add(ContractOrderStatus.SELF_CLOSE);
        list.add(ContractOrderStatus.FORCE_CLOSE);
        list.add(ContractOrderStatus.EXPOLDE_WAREHOUSE);
        list.add(ContractOrderStatus.THROUGH_WAREHOUSE);
        list.add(ContractOrderStatus.STOP_PROFIT);
        list.add(ContractOrderStatus.STOP_LOSS);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        specification.add((Criterion) Restrictions.eq("type", ContractOrderType.LIMIT_PRICE, true));
        return (Page<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public ContractExchangeOrder findOne(final Long orderId) {
        return (ContractExchangeOrder) this.contractOrderRepository.getOne(orderId);
    }

    public int updateOrderStatus(final long id, final ContractOrderStatus cancel, final long version) {
        return this.contractOrderRepository.updateOrderStatus(id, cancel, version);
    }

    public int updateStopCondition(final long orderId, final BooleanEnum ifStopLoss, final BigDecimal stopLossPrice, final BooleanEnum ifStopProfit, final BigDecimal stopProfitPrice, final long version) {
        return this.contractOrderRepository.updateStopCondition(orderId, ifStopLoss, stopLossPrice, ifStopProfit, stopProfitPrice, version);
    }

    public List<ContractExchangeOrder> findAllEffectiveLimitOrder(final String symbol) {
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.HANG_PROCESSING);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        specification.add((Criterion) Restrictions.eq("type", ContractOrderType.LIMIT_PRICE, true));
        specification.sort(new String[]{"sequence", "DESC"});
        return (List<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification);
    }

    public int updateLimitOrderToHolding(final long id, final ContractOrderStatus holdProcessing, final BigDecimal closePrice, final long version) {
        return this.contractOrderRepository.updateLimitOrderToHolding(id, holdProcessing, closePrice, version);
    }

    public List<ContractExchangeOrder> findAllHoldingOrders(final String symbol) {
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.HOLD_PROCESSING);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        specification.sort(new String[]{"sequence", "DESC"});
        return (List<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification);
    }

    public List<Long> findAllHoldingOrderIds(final String symbol) {
        return this.contractOrderRepository.findAllHoldingOrderIds(symbol, ContractOrderStatus.HOLD_PROCESSING);
    }

    public List<BigInteger> findMemberIds(final int status) {
        return this.contractOrderRepository.findMemberIds(status);
    }

    public List<Long> findMemberOrderIds(final Long memberId, final String coin, final int status) {
        return this.contractOrderRepository.findMemberOrderIds(memberId, coin, status);
    }

    public BigDecimal sumInterestBalanceByOrderId(final long orderId) {
        BigDecimal sumInterest = this.contractExchangeOrderInterestRepository.sumInterestBalanceByOrderId(orderId);
        sumInterest = ((null != sumInterest) ? sumInterest : BigDecimal.ZERO);
        return sumInterest;
    }

    public BigDecimal sumHoldingOrdersAmountByMemberId(final Long memberId, final String symbol) {
        return this.contractOrderRepository.sumHoldingOrdersAmountByMemberId(memberId, symbol, ContractOrderStatus.HOLD_PROCESSING.getOrdinal());
    }

    public BigDecimal sumHoldingOrdersAmountByMemberIdAndLeaderMemberId(final Long memberId, final Long leaderMemberId) {
        final BigDecimal bigDecimal = this.contractOrderRepository.sumHoldingOrdersAmountByMemberIdAndLeaderMemberId(memberId, leaderMemberId, ContractOrderStatus.HOLD_PROCESSING.getOrdinal());
        return (bigDecimal == null) ? BigDecimal.ZERO : bigDecimal;
    }

    public Long findAllHistoryOrderCount(final Long memberId) {
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        specification.add((Criterion) Restrictions.eq("member", memberId, true));
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.SELF_CLOSE);
        list.add(ContractOrderStatus.FORCE_CLOSE);
        list.add(ContractOrderStatus.EXPOLDE_WAREHOUSE);
        list.add(ContractOrderStatus.THROUGH_WAREHOUSE);
        list.add(ContractOrderStatus.STOP_PROFIT);
        list.add(ContractOrderStatus.STOP_LOSS);
        list.add(ContractOrderStatus.FOLLOW_CLOSE);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        return this.contractOrderRepository.count((Specification) specification);
    }

    public BigDecimal sumAllAndCloseOrderByLeaderMeberId(final Long leaderMemberId, final Date startDate, final Date endDate) {
        final BigDecimal bigDecimal = this.contractOrderRepository.sumAllAndCloseOrderByLeaderMeberId(leaderMemberId, startDate, endDate);
        return (bigDecimal == null) ? BigDecimal.ZERO : bigDecimal;
    }

    public List<ContractExchangeOrder> findMemberHoldingOrders(final Long memberId, final String coin) {
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (null != memberId) {
            specification.add((Criterion) Restrictions.eq("member", new Member(memberId), true));
        }
        if (StringUtils.isNotEmpty((CharSequence) coin)) {
            specification.add((Criterion) Restrictions.eq("coinId", coin, true));
        }
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.HOLD_PROCESSING);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        specification.sort(new String[]{"sequence", "DESC"});
        return (List<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification);
    }

    public long countMemberHoldingOrders(final Long memberId) {
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (null != memberId) {
            specification.add((Criterion) Restrictions.eq("member", new Member(memberId), true));
        }
        final List<ContractOrderStatus> list = new ArrayList<ContractOrderStatus>();
        list.add(ContractOrderStatus.HOLD_PROCESSING);
        specification.add((Criterion) Restrictions.in("status", (Collection) list, false));
        specification.sort(new String[]{"sequence", "DESC"});
        return this.contractOrderRepository.count((Specification) specification);
    }

    public long countBySequenceAndMemberId(final Long memberId, final Long sequence, final int status) {
        return this.contractOrderRepository.countBySequenceAndMemberId(memberId, sequence, status);
    }

    public List<ContractExchangeOrder> getCloseOrdersBySequence(final Long minSequence, final Long maxSequence) {
        return this.contractOrderRepository.getCloseOrdersBySequence(minSequence, maxSequence);
    }

    public Page<ContractExchangeOrder> getOneClickOrders(final Long memberId, final String symbol, final int pageNum, final int pageSize, final Integer contractType, final Integer walletType) {
        final Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, orders);
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>) new Criteria();
        if (StringUtils.isNotEmpty((CharSequence) symbol)) {
            specification.add((Criterion) Restrictions.eq("symbol", symbol, true));
        }
        if (contractType != null) {
            specification.add((Criterion) Restrictions.eq("contractType", contractType, true));
        }
        if (walletType != null) {
            specification.add((Criterion) Restrictions.eq("walletType", walletType, true));
        }
        specification.add((Criterion) Restrictions.eq("isOneClickOrder", 1, true));
        specification.add((Criterion) Restrictions.eq("member", memberId, true));
        return (Page<ContractExchangeOrder>) this.contractOrderRepository.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public List<ContractExchangeOrder> findOneClickOrdersByStatus(final ContractOrderStatus status) {
        final Criteria<ContractExchangeOrder> specification = (Criteria<ContractExchangeOrder>)new Criteria();
        if (null != status) {
            specification.add((Criterion)Restrictions.eq("status", (Object)status, true));
        }
        specification.add((Criterion)Restrictions.eq("isOneClickOrder", (Object)1, true));
        return (List<ContractExchangeOrder>)this.contractOrderRepository.findAll((Specification)specification);
    }
}
