package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QBusinessAuthApply extends EntityPathBase<BusinessAuthApply>
{
    private static final long serialVersionUID = -26398525L;
    private static final PathInits INITS;
    public static final QBusinessAuthApply businessAuthApply;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> auditingTime;
    public final StringPath authInfo;
    public final QBusinessAuthDeposit businessAuthDeposit;
    public final EnumPath<CertifiedBusinessStatus> certifiedBusinessStatus;
    public final DateTimePath<Date> createTime;
    public final StringPath depositRecordId;
    public final StringPath detail;
    public final NumberPath<Long> id;
    public final QMember member;
    public final DateTimePath<Date> updateTime;
    
    public QBusinessAuthApply(final String variable) {
        this(BusinessAuthApply.class, PathMetadataFactory.forVariable(variable), QBusinessAuthApply.INITS);
    }
    
    public QBusinessAuthApply(final Path<? extends BusinessAuthApply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QBusinessAuthApply.INITS));
    }
    
    public QBusinessAuthApply(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QBusinessAuthApply.INITS));
    }
    
    public QBusinessAuthApply(final PathMetadata metadata, final PathInits inits) {
        this(BusinessAuthApply.class, metadata, inits);
    }
    
    public QBusinessAuthApply(final Class<? extends BusinessAuthApply> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.auditingTime = (DateTimePath<Date>)this.createDateTime("auditingTime", (Class)Date.class);
        this.authInfo = this.createString("authInfo");
        this.certifiedBusinessStatus = (EnumPath<CertifiedBusinessStatus>)this.createEnum("certifiedBusinessStatus", (Class)CertifiedBusinessStatus.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.depositRecordId = this.createString("depositRecordId");
        this.detail = this.createString("detail");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.businessAuthDeposit = (inits.isInitialized("businessAuthDeposit") ? new QBusinessAuthDeposit(this.forProperty("businessAuthDeposit"), inits.get("businessAuthDeposit")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        businessAuthApply = new QBusinessAuthApply("businessAuthApply");
    }
}
