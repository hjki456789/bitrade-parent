package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QMemberSignRecord extends EntityPathBase<MemberSignRecord>
{
    private static final long serialVersionUID = -961424117L;
    private static final PathInits INITS;
    public static final QMemberSignRecord memberSignRecord;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> creationTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final QSign sign;
    
    public QMemberSignRecord(final String variable) {
        this(MemberSignRecord.class, PathMetadataFactory.forVariable(variable), QMemberSignRecord.INITS);
    }
    
    public QMemberSignRecord(final Path<? extends MemberSignRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberSignRecord.INITS));
    }
    
    public QMemberSignRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberSignRecord.INITS));
    }
    
    public QMemberSignRecord(final PathMetadata metadata, final PathInits inits) {
        this(MemberSignRecord.class, metadata, inits);
    }
    
    public QMemberSignRecord(final Class<? extends MemberSignRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.creationTime = (DateTimePath<Date>)this.createDateTime("creationTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
        this.sign = (inits.isInitialized("sign") ? new QSign(this.forProperty("sign"), inits.get("sign")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberSignRecord = new QMemberSignRecord("memberSignRecord");
    }
}
