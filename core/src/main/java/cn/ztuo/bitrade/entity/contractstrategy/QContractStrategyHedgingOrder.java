package cn.ztuo.bitrade.entity.contractstrategy;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractStrategyHedgingOrder extends EntityPathBase<ContractStrategyHedgingOrder>
{
    private static final long serialVersionUID = 1035361687L;
    public static final QContractStrategyHedgingOrder contractStrategyHedgingOrder;
    public final NumberPath<Long> amount;
    public final StringPath apiKey;
    public final NumberPath<BigDecimal> contractSize;
    public final DateTimePath<Date> createTime;
    public final StringPath direction;
    public final NumberPath<Long> fromExchangeId;
    public final StringPath fromExchangeOrderId;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> leverRate;
    public final NumberPath<Long> memberId;
    public final StringPath offset;
    public final StringPath orderPriceType;
    public final NumberPath<BigDecimal> price;
    public final StringPath symbol;
    
    public QContractStrategyHedgingOrder(final String variable) {
        super((Class)ContractStrategyHedgingOrder.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.contractSize = (NumberPath<BigDecimal>)this.createNumber("contractSize", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.direction = this.createString("direction");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeOrderId = this.createString("fromExchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leverRate = (NumberPath<Integer>)this.createNumber("leverRate", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.offset = this.createString("offset");
        this.orderPriceType = this.createString("orderPriceType");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
    }
    
    public QContractStrategyHedgingOrder(final Path<? extends ContractStrategyHedgingOrder> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.contractSize = (NumberPath<BigDecimal>)this.createNumber("contractSize", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.direction = this.createString("direction");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeOrderId = this.createString("fromExchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leverRate = (NumberPath<Integer>)this.createNumber("leverRate", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.offset = this.createString("offset");
        this.orderPriceType = this.createString("orderPriceType");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
    }
    
    public QContractStrategyHedgingOrder(final PathMetadata metadata) {
        super((Class)ContractStrategyHedgingOrder.class, metadata);
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.contractSize = (NumberPath<BigDecimal>)this.createNumber("contractSize", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.direction = this.createString("direction");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeOrderId = this.createString("fromExchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leverRate = (NumberPath<Integer>)this.createNumber("leverRate", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.offset = this.createString("offset");
        this.orderPriceType = this.createString("orderPriceType");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
    }
    
    static {
        contractStrategyHedgingOrder = new QContractStrategyHedgingOrder("contractStrategyHedgingOrder");
    }
}
