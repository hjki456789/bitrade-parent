package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderInterest extends EntityPathBase<ContractExchangeOrderInterest>
{
    private static final long serialVersionUID = -1076995136L;
    private static final PathInits INITS;
    public static final QContractExchangeOrderInterest contractExchangeOrderInterest;
    public final NumberPath<BigDecimal> balance;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QContractExchangeOrder order;
    public final NumberPath<Long> sequence;
    public final NumberPath<Long> version;
    
    public QContractExchangeOrderInterest(final String variable) {
        this(ContractExchangeOrderInterest.class, PathMetadataFactory.forVariable(variable), QContractExchangeOrderInterest.INITS);
    }
    
    public QContractExchangeOrderInterest(final Path<? extends ContractExchangeOrderInterest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractExchangeOrderInterest.INITS));
    }
    
    public QContractExchangeOrderInterest(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractExchangeOrderInterest.INITS));
    }
    
    public QContractExchangeOrderInterest(final PathMetadata metadata, final PathInits inits) {
        this(ContractExchangeOrderInterest.class, metadata, inits);
    }
    
    public QContractExchangeOrderInterest(final Class<? extends ContractExchangeOrderInterest> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.order = (inits.isInitialized("order") ? new QContractExchangeOrder(this.forProperty("order"), inits.get("order")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractExchangeOrderInterest = new QContractExchangeOrderInterest("contractExchangeOrderInterest");
    }
}
