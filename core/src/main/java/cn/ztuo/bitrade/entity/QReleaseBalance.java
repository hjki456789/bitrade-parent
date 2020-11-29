package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.util.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QReleaseBalance extends EntityPathBase<ReleaseBalance>
{
    private static final long serialVersionUID = 2111277048L;
    public static final QReleaseBalance releaseBalance1;
    public final StringPath coinName;
    public final StringPath coinUnit;
    public final StringPath email;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath phone;
    public final DateTimePath<Date> registerTime;
    public final NumberPath<BigDecimal> releaseBalance;
    public final StringPath releaseState;
    public final DateTimePath<Date> releaseTime;
    public final StringPath userName;
    
    public QReleaseBalance(final String variable) {
        super((Class)ReleaseBalance.class, PathMetadataFactory.forVariable(variable));
        this.coinName = this.createString("coinName");
        this.coinUnit = this.createString("coinUnit");
        this.email = this.createString("email");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.phone = this.createString("phone");
        this.registerTime = (DateTimePath<Date>)this.createDateTime("registerTime", (Class)Date.class);
        this.releaseBalance = (NumberPath<BigDecimal>)this.createNumber("releaseBalance", (Class)BigDecimal.class);
        this.releaseState = this.createString("releaseState");
        this.releaseTime = (DateTimePath<Date>)this.createDateTime("releaseTime", (Class)Date.class);
        this.userName = this.createString("userName");
    }
    
    public QReleaseBalance(final Path<? extends ReleaseBalance> path) {
        super(path.getType(), path.getMetadata());
        this.coinName = this.createString("coinName");
        this.coinUnit = this.createString("coinUnit");
        this.email = this.createString("email");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.phone = this.createString("phone");
        this.registerTime = (DateTimePath<Date>)this.createDateTime("registerTime", (Class)Date.class);
        this.releaseBalance = (NumberPath<BigDecimal>)this.createNumber("releaseBalance", (Class)BigDecimal.class);
        this.releaseState = this.createString("releaseState");
        this.releaseTime = (DateTimePath<Date>)this.createDateTime("releaseTime", (Class)Date.class);
        this.userName = this.createString("userName");
    }
    
    public QReleaseBalance(final PathMetadata metadata) {
        super((Class)ReleaseBalance.class, metadata);
        this.coinName = this.createString("coinName");
        this.coinUnit = this.createString("coinUnit");
        this.email = this.createString("email");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.phone = this.createString("phone");
        this.registerTime = (DateTimePath<Date>)this.createDateTime("registerTime", (Class)Date.class);
        this.releaseBalance = (NumberPath<BigDecimal>)this.createNumber("releaseBalance", (Class)BigDecimal.class);
        this.releaseState = this.createString("releaseState");
        this.releaseTime = (DateTimePath<Date>)this.createDateTime("releaseTime", (Class)Date.class);
        this.userName = this.createString("userName");
    }
    
    static {
        releaseBalance1 = new QReleaseBalance("releaseBalance1");
    }
}
