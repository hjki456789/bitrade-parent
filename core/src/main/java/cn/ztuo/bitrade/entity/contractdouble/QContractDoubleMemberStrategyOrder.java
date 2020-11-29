package cn.ztuo.bitrade.entity.contractdouble;

import com.querydsl.core.types.dsl.*;
import java.util.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QContractDoubleMemberStrategyOrder extends EntityPathBase<ContractDoubleMemberStrategyOrder>
{
    private static final long serialVersionUID = 95570166L;
    public static final QContractDoubleMemberStrategyOrder contractDoubleMemberStrategyOrder;
    public final NumberPath<Long> amount;
    public final NumberPath<Long> apiId;
    public final StringPath apiKey;
    public final NumberPath<Integer> closeMatchStatus;
    public final StringPath closeOrderId;
    public final DateTimePath<Date> closeTime;
    public final NumberPath<Long> contractOrderId;
    public final NumberPath<BigDecimal> contractSize;
    public final DateTimePath<Date> createTime;
    public final StringPath direction;
    public final NumberPath<Long> fromExchangeId;
    public final StringPath fromExchangeOrderId;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> leverRate;
    public final NumberPath<BigDecimal> marginFrozen;
    public final NumberPath<Integer> matchStatus;
    public final NumberPath<Long> memberId;
    public final StringPath offset;
    public final StringPath orderPriceType;
    public final NumberPath<BigDecimal> price;
    public final NumberPath<BigDecimal> profit;
    public final StringPath remark;
    public final StringPath secretKey;
    public final NumberPath<Integer> status;
    public final StringPath symbol;
    public final DateTimePath<Date> updateTime;
    
    public QContractDoubleMemberStrategyOrder(final String variable) {
        super((Class)ContractDoubleMemberStrategyOrder.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.apiId = (NumberPath<Long>)this.createNumber("apiId", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.closeMatchStatus = (NumberPath<Integer>)this.createNumber("closeMatchStatus", (Class)Integer.class);
        this.closeOrderId = this.createString("closeOrderId");
        this.closeTime = (DateTimePath<Date>)this.createDateTime("closeTime", (Class)Date.class);
        this.contractOrderId = (NumberPath<Long>)this.createNumber("contractOrderId", (Class)Long.class);
        this.contractSize = (NumberPath<BigDecimal>)this.createNumber("contractSize", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.direction = this.createString("direction");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeOrderId = this.createString("fromExchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leverRate = (NumberPath<Integer>)this.createNumber("leverRate", (Class)Integer.class);
        this.marginFrozen = (NumberPath<BigDecimal>)this.createNumber("marginFrozen", (Class)BigDecimal.class);
        this.matchStatus = (NumberPath<Integer>)this.createNumber("matchStatus", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.offset = this.createString("offset");
        this.orderPriceType = this.createString("orderPriceType");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QContractDoubleMemberStrategyOrder(final Path<? extends ContractDoubleMemberStrategyOrder> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.apiId = (NumberPath<Long>)this.createNumber("apiId", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.closeMatchStatus = (NumberPath<Integer>)this.createNumber("closeMatchStatus", (Class)Integer.class);
        this.closeOrderId = this.createString("closeOrderId");
        this.closeTime = (DateTimePath<Date>)this.createDateTime("closeTime", (Class)Date.class);
        this.contractOrderId = (NumberPath<Long>)this.createNumber("contractOrderId", (Class)Long.class);
        this.contractSize = (NumberPath<BigDecimal>)this.createNumber("contractSize", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.direction = this.createString("direction");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeOrderId = this.createString("fromExchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leverRate = (NumberPath<Integer>)this.createNumber("leverRate", (Class)Integer.class);
        this.marginFrozen = (NumberPath<BigDecimal>)this.createNumber("marginFrozen", (Class)BigDecimal.class);
        this.matchStatus = (NumberPath<Integer>)this.createNumber("matchStatus", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.offset = this.createString("offset");
        this.orderPriceType = this.createString("orderPriceType");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QContractDoubleMemberStrategyOrder(final PathMetadata metadata) {
        super((Class)ContractDoubleMemberStrategyOrder.class, metadata);
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.apiId = (NumberPath<Long>)this.createNumber("apiId", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.closeMatchStatus = (NumberPath<Integer>)this.createNumber("closeMatchStatus", (Class)Integer.class);
        this.closeOrderId = this.createString("closeOrderId");
        this.closeTime = (DateTimePath<Date>)this.createDateTime("closeTime", (Class)Date.class);
        this.contractOrderId = (NumberPath<Long>)this.createNumber("contractOrderId", (Class)Long.class);
        this.contractSize = (NumberPath<BigDecimal>)this.createNumber("contractSize", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.direction = this.createString("direction");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeOrderId = this.createString("fromExchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leverRate = (NumberPath<Integer>)this.createNumber("leverRate", (Class)Integer.class);
        this.marginFrozen = (NumberPath<BigDecimal>)this.createNumber("marginFrozen", (Class)BigDecimal.class);
        this.matchStatus = (NumberPath<Integer>)this.createNumber("matchStatus", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.offset = this.createString("offset");
        this.orderPriceType = this.createString("orderPriceType");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        contractDoubleMemberStrategyOrder = new QContractDoubleMemberStrategyOrder("contractDoubleMemberStrategyOrder");
    }
}
