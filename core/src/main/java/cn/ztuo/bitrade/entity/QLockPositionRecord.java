package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QLockPositionRecord extends EntityPathBase<LockPositionRecord>
{
    private static final long serialVersionUID = 1166573192L;
    private static final PathInits INITS;
    public static final QLockPositionRecord lockPositionRecord;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath memberName;
    public final StringPath reason;
    public final EnumPath<CommonStatus> status;
    public final DateTimePath<Date> unlockTime;
    public final NumberPath<Long> walletId;
    
    public QLockPositionRecord(final String variable) {
        this(LockPositionRecord.class, PathMetadataFactory.forVariable(variable), QLockPositionRecord.INITS);
    }
    
    public QLockPositionRecord(final Path<? extends LockPositionRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QLockPositionRecord.INITS));
    }
    
    public QLockPositionRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QLockPositionRecord.INITS));
    }
    
    public QLockPositionRecord(final PathMetadata metadata, final PathInits inits) {
        this(LockPositionRecord.class, metadata, inits);
    }
    
    public QLockPositionRecord(final Class<? extends LockPositionRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberName = this.createString("memberName");
        this.reason = this.createString("reason");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unlockTime = (DateTimePath<Date>)this.createDateTime("unlockTime", (Class)Date.class);
        this.walletId = (NumberPath<Long>)this.createNumber("walletId", (Class)Long.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        lockPositionRecord = new QLockPositionRecord("lockPositionRecord");
    }
}
