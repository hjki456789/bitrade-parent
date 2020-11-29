package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QMemberTeam extends EntityPathBase<MemberTeam>
{
    private static final long serialVersionUID = 677305530L;
    private static final PathInits INITS;
    public static final QMemberTeam memberTeam;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<Integer> generation;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> isValidMember;
    public final QMember lowerMember;
    public final NumberPath<Long> lowerMemberId;
    public final NumberPath<Long> memberId;
    
    public QMemberTeam(final String variable) {
        this(MemberTeam.class, PathMetadataFactory.forVariable(variable), QMemberTeam.INITS);
    }
    
    public QMemberTeam(final Path<? extends MemberTeam> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberTeam.INITS));
    }
    
    public QMemberTeam(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberTeam.INITS));
    }
    
    public QMemberTeam(final PathMetadata metadata, final PathInits inits) {
        this(MemberTeam.class, metadata, inits);
    }
    
    public QMemberTeam(final Class<? extends MemberTeam> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.generation = (NumberPath<Integer>)this.createNumber("generation", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isValidMember = (NumberPath<Integer>)this.createNumber("isValidMember", (Class)Integer.class);
        this.lowerMemberId = (NumberPath<Long>)this.createNumber("lowerMemberId", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.lowerMember = (inits.isInitialized("lowerMember") ? new QMember(this.forProperty("lowerMember"), inits.get("lowerMember")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberTeam = new QMemberTeam("memberTeam");
    }
}
