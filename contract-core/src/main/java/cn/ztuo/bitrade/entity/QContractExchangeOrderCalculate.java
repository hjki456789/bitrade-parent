package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderCalculate extends EntityPathBase<ContractExchangeOrderCalculate>
{
    private static final long serialVersionUID = -813375088L;
    public static final QContractExchangeOrderCalculate contractExchangeOrderCalculate;
    public final NumberPath<Long> orderId;
    public final NumberPath<BigDecimal> profit;
    public final NumberPath<BigDecimal> rate;
    public final NumberPath<BigDecimal> symbolPrice;
    public final DateTimePath<Date> updateTime;
    public final NumberPath<Long> version;
    
    public QContractExchangeOrderCalculate(final String variable) {
        super((Class)ContractExchangeOrderCalculate.class, PathMetadataFactory.forVariable(variable));
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.rate = (NumberPath<BigDecimal>)this.createNumber("rate", (Class)BigDecimal.class);
        this.symbolPrice = (NumberPath<BigDecimal>)this.createNumber("symbolPrice", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderCalculate(final Path<? extends ContractExchangeOrderCalculate> path) {
        super(path.getType(), path.getMetadata());
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.rate = (NumberPath<BigDecimal>)this.createNumber("rate", (Class)BigDecimal.class);
        this.symbolPrice = (NumberPath<BigDecimal>)this.createNumber("symbolPrice", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderCalculate(final PathMetadata metadata) {
        super((Class)ContractExchangeOrderCalculate.class, metadata);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.rate = (NumberPath<BigDecimal>)this.createNumber("rate", (Class)BigDecimal.class);
        this.symbolPrice = (NumberPath<BigDecimal>)this.createNumber("symbolPrice", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        contractExchangeOrderCalculate = new QContractExchangeOrderCalculate("contractExchangeOrderCalculate");
    }
}
