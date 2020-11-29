package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QMemberDepositInfo extends EntityPathBase<MemberDepositInfo>
{
    private static final long serialVersionUID = -56302737L;
    private static final PathInits INITS;
    public static final QMemberDepositInfo memberDepositInfo;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<Long> depositTypeId;
    public final QDepositTypeInfo depositTypeInfo;
    public final DateTimePath<Date> endTime;
    public final NumberPath<Long> id;
    public final DateTimePath<Date> investTime;
    public final NumberPath<Long> memberId;
    public final DateTimePath<Date> preEndTime;
    public final NumberPath<BigDecimal> profit;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    
    public QMemberDepositInfo(final String variable) {
        this(MemberDepositInfo.class, PathMetadataFactory.forVariable(variable), QMemberDepositInfo.INITS);
    }
    
    public QMemberDepositInfo(final Path<? extends MemberDepositInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberDepositInfo.INITS));
    }
    
    public QMemberDepositInfo(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberDepositInfo.INITS));
    }
    
    public QMemberDepositInfo(final PathMetadata metadata, final PathInits inits) {
        this(MemberDepositInfo.class, metadata, inits);
    }
    
    public QMemberDepositInfo(final Class<? extends MemberDepositInfo> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.depositTypeId = (NumberPath<Long>)this.createNumber("depositTypeId", (Class)Long.class);
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.investTime = (DateTimePath<Date>)this.createDateTime("investTime", (Class)Date.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.preEndTime = (DateTimePath<Date>)this.createDateTime("preEndTime", (Class)Date.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.depositTypeInfo = (inits.isInitialized("depositTypeInfo") ? new QDepositTypeInfo(this.forProperty("depositTypeInfo")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberDepositInfo = new QMemberDepositInfo("memberDepositInfo");
    }
}
