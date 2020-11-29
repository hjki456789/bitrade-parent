package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QPlatformTransaction extends EntityPathBase<PlatformTransaction>
{
    private static final long serialVersionUID = 864283432L;
    public static final QPlatformTransaction platformTransaction;
    public final NumberPath<BigDecimal> amount;
    public final StringPath bizOrderId;
    public final StringPath day;
    public final NumberPath<Integer> direction;
    public final NumberPath<Long> id;
    public final DateTimePath<Date> time;
    public final NumberPath<Integer> type;
    
    public QPlatformTransaction(final String variable) {
        super((Class)PlatformTransaction.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.bizOrderId = this.createString("bizOrderId");
        this.day = this.createString("day");
        this.direction = (NumberPath<Integer>)this.createNumber("direction", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    public QPlatformTransaction(final Path<? extends PlatformTransaction> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.bizOrderId = this.createString("bizOrderId");
        this.day = this.createString("day");
        this.direction = (NumberPath<Integer>)this.createNumber("direction", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    public QPlatformTransaction(final PathMetadata metadata) {
        super((Class)PlatformTransaction.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.bizOrderId = this.createString("bizOrderId");
        this.day = this.createString("day");
        this.direction = (NumberPath<Integer>)this.createNumber("direction", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    static {
        platformTransaction = new QPlatformTransaction("platformTransaction");
    }
}
