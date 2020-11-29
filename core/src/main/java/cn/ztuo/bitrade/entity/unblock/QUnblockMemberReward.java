package cn.ztuo.bitrade.entity.unblock;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import com.querydsl.core.types.*;

public class QUnblockMemberReward extends EntityPathBase<UnblockMemberReward>
{
    private static final long serialVersionUID = -1139494784L;
    private static final PathInits INITS;
    public static final QUnblockMemberReward unblockMemberReward;
    public final NumberPath<BigDecimal> afterBalance;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Integer> generation;
    public final NumberPath<Long> id;
    public final QMember member;
    public final NumberPath<Long> memberId;
    public final QMember originMember;
    public final NumberPath<Long> originMemberId;
    public final NumberPath<Long> sequence;
    
    public QUnblockMemberReward(final String variable) {
        this(UnblockMemberReward.class, PathMetadataFactory.forVariable(variable), QUnblockMemberReward.INITS);
    }
    
    public QUnblockMemberReward(final Path<? extends UnblockMemberReward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QUnblockMemberReward.INITS));
    }
    
    public QUnblockMemberReward(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QUnblockMemberReward.INITS));
    }
    
    public QUnblockMemberReward(final PathMetadata metadata, final PathInits inits) {
        this(UnblockMemberReward.class, metadata, inits);
    }
    
    public QUnblockMemberReward(final Class<? extends UnblockMemberReward> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.generation = (NumberPath<Integer>)this.createNumber("generation", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.originMemberId = (NumberPath<Long>)this.createNumber("originMemberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
        this.originMember = (inits.isInitialized("originMember") ? new QMember(this.forProperty("originMember"), inits.get("originMember")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        unblockMemberReward = new QUnblockMemberReward("unblockMemberReward");
    }
}
