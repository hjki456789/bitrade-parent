package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderFee extends EntityPathBase<ContractExchangeOrderFee>
{
    private static final long serialVersionUID = 1043190064L;
    public static final QContractExchangeOrderFee contractExchangeOrderFee;
    public final NumberPath<BigDecimal> closeFee;
    public final NumberPath<Integer> contractType;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> openFee;
    public final NumberPath<Long> orderId;
    public final NumberPath<Long> sequence;
    public final NumberPath<Long> version;
    
    public QContractExchangeOrderFee(final String variable) {
        super((Class)ContractExchangeOrderFee.class, PathMetadataFactory.forVariable(variable));
        this.closeFee = (NumberPath<BigDecimal>)this.createNumber("closeFee", (Class)BigDecimal.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.openFee = (NumberPath<BigDecimal>)this.createNumber("openFee", (Class)BigDecimal.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderFee(final Path<? extends ContractExchangeOrderFee> path) {
        super(path.getType(), path.getMetadata());
        this.closeFee = (NumberPath<BigDecimal>)this.createNumber("closeFee", (Class)BigDecimal.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.openFee = (NumberPath<BigDecimal>)this.createNumber("openFee", (Class)BigDecimal.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderFee(final PathMetadata metadata) {
        super((Class)ContractExchangeOrderFee.class, metadata);
        this.closeFee = (NumberPath<BigDecimal>)this.createNumber("closeFee", (Class)BigDecimal.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.openFee = (NumberPath<BigDecimal>)this.createNumber("openFee", (Class)BigDecimal.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        contractExchangeOrderFee = new QContractExchangeOrderFee("contractExchangeOrderFee");
    }
}
