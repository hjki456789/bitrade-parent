package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QBusinessAuthDeposit extends EntityPathBase<BusinessAuthDeposit>
{
    private static final long serialVersionUID = -1546468685L;
    private static final PathInits INITS;
    public static final QBusinessAuthDeposit businessAuthDeposit;
    public final QAdmin admin;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final EnumPath<CommonStatus> status;
    
    public QBusinessAuthDeposit(final String variable) {
        this(BusinessAuthDeposit.class, PathMetadataFactory.forVariable(variable), QBusinessAuthDeposit.INITS);
    }
    
    public QBusinessAuthDeposit(final Path<? extends BusinessAuthDeposit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QBusinessAuthDeposit.INITS));
    }
    
    public QBusinessAuthDeposit(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QBusinessAuthDeposit.INITS));
    }
    
    public QBusinessAuthDeposit(final PathMetadata metadata, final PathInits inits) {
        this(BusinessAuthDeposit.class, metadata, inits);
    }
    
    public QBusinessAuthDeposit(final Class<? extends BusinessAuthDeposit> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        businessAuthDeposit = new QBusinessAuthDeposit("businessAuthDeposit");
    }
}
