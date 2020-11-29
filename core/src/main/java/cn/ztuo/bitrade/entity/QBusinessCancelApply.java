package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QBusinessCancelApply extends EntityPathBase<BusinessCancelApply>
{
    private static final long serialVersionUID = 300449777L;
    private static final PathInits INITS;
    public static final QBusinessCancelApply businessCancelApply;
    public final DateTimePath<Date> cancelApplyTime;
    public final StringPath depositRecordId;
    public final StringPath detail;
    public final DateTimePath<Date> handleTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final StringPath reason;
    public final EnumPath<CertifiedBusinessStatus> status;
    
    public QBusinessCancelApply(final String variable) {
        this(BusinessCancelApply.class, PathMetadataFactory.forVariable(variable), QBusinessCancelApply.INITS);
    }
    
    public QBusinessCancelApply(final Path<? extends BusinessCancelApply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QBusinessCancelApply.INITS));
    }
    
    public QBusinessCancelApply(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QBusinessCancelApply.INITS));
    }
    
    public QBusinessCancelApply(final PathMetadata metadata, final PathInits inits) {
        this(BusinessCancelApply.class, metadata, inits);
    }
    
    public QBusinessCancelApply(final Class<? extends BusinessCancelApply> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.cancelApplyTime = (DateTimePath<Date>)this.createDateTime("cancelApplyTime", (Class)Date.class);
        this.depositRecordId = this.createString("depositRecordId");
        this.detail = this.createString("detail");
        this.handleTime = (DateTimePath<Date>)this.createDateTime("handleTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.reason = this.createString("reason");
        this.status = (EnumPath<CertifiedBusinessStatus>)this.createEnum("status", (Class)CertifiedBusinessStatus.class);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        businessCancelApply = new QBusinessCancelApply("businessCancelApply");
    }
}
