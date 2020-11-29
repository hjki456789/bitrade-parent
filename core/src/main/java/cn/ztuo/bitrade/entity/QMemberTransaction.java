package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QMemberTransaction extends EntityPathBase<MemberTransaction>
{
    private static final long serialVersionUID = -1941682719L;
    private static final PathInits INITS;
    public static final QMemberTransaction memberTransaction;
    public final StringPath address;
    public final NumberPath<BigDecimal> afterBalance;
    public final NumberPath<Long> airdropId;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> fee;
    public final NumberPath<Integer> flag;
    public final NumberPath<Long> fromMemberId;
    public final NumberPath<Long> id;
    public final NumberPath<Long> lotteryCount;
    public final QMember member;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> sequence;
    public final StringPath symbol;
    public final EnumPath<TransactionType> type;
    
    public QMemberTransaction(final String variable) {
        this(MemberTransaction.class, PathMetadataFactory.forVariable(variable), QMemberTransaction.INITS);
    }
    
    public QMemberTransaction(final Path<? extends MemberTransaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberTransaction.INITS));
    }
    
    public QMemberTransaction(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberTransaction.INITS));
    }
    
    public QMemberTransaction(final PathMetadata metadata, final PathInits inits) {
        this(MemberTransaction.class, metadata, inits);
    }
    
    public QMemberTransaction(final Class<? extends MemberTransaction> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.airdropId = (NumberPath<Long>)this.createNumber("airdropId", (Class)Long.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.flag = (NumberPath<Integer>)this.createNumber("flag", (Class)Integer.class);
        this.fromMemberId = (NumberPath<Long>)this.createNumber("fromMemberId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryCount = (NumberPath<Long>)this.createNumber("lotteryCount", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.type = (EnumPath<TransactionType>)this.createEnum("type", (Class)TransactionType.class);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberTransaction = new QMemberTransaction("memberTransaction");
    }
}
