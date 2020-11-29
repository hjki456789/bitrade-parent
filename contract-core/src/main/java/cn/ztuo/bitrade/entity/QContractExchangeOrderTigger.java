package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderTigger extends EntityPathBase<ContractExchangeOrderTigger>
{
    private static final long serialVersionUID = -303493224L;
    public static final QContractExchangeOrderTigger contractExchangeOrderTigger;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> openPrice;
    public final NumberPath<Long> orderId;
    public final NumberPath<Long> sequence;
    public final NumberPath<Long> version;
    
    public QContractExchangeOrderTigger(final String variable) {
        super((Class)ContractExchangeOrderTigger.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.openPrice = (NumberPath<BigDecimal>)this.createNumber("openPrice", (Class)BigDecimal.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderTigger(final Path<? extends ContractExchangeOrderTigger> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.openPrice = (NumberPath<BigDecimal>)this.createNumber("openPrice", (Class)BigDecimal.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderTigger(final PathMetadata metadata) {
        super((Class)ContractExchangeOrderTigger.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.openPrice = (NumberPath<BigDecimal>)this.createNumber("openPrice", (Class)BigDecimal.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        contractExchangeOrderTigger = new QContractExchangeOrderTigger("contractExchangeOrderTigger");
    }
}
