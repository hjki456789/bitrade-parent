package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QSign extends EntityPathBase<Sign>
{
    private static final long serialVersionUID = 1697205408L;
    private static final PathInits INITS;
    public static final QSign sign;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> creationTime;
    public final DatePath<Date> endDate;
    public final NumberPath<Long> id;
    public final EnumPath<SignStatus> status;
    
    public QSign(final String variable) {
        this(Sign.class, PathMetadataFactory.forVariable(variable), QSign.INITS);
    }
    
    public QSign(final Path<? extends Sign> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QSign.INITS));
    }
    
    public QSign(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QSign.INITS));
    }
    
    public QSign(final PathMetadata metadata, final PathInits inits) {
        this(Sign.class, metadata, inits);
    }
    
    public QSign(final Class<? extends Sign> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.creationTime = (DateTimePath<Date>)this.createDateTime("creationTime", (Class)Date.class);
        this.endDate = (DatePath<Date>)this.createDate("endDate", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.status = (EnumPath<SignStatus>)this.createEnum("status", (Class)SignStatus.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        sign = new QSign("sign");
    }
}
