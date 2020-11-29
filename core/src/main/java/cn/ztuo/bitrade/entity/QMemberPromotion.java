package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QMemberPromotion extends EntityPathBase<MemberPromotion>
{
    private static final long serialVersionUID = -365472666L;
    private static final PathInits INITS;
    public static final QMemberPromotion memberPromotion;
    public final NumberPath<Long> id;
    public final NumberPath<Long> inviteesId;
    public final NumberPath<Long> inviterId;
    public final EnumPath<PromotionLevel> level;
    public final QMember member;
    
    public QMemberPromotion(final String variable) {
        this(MemberPromotion.class, PathMetadataFactory.forVariable(variable), QMemberPromotion.INITS);
    }
    
    public QMemberPromotion(final Path<? extends MemberPromotion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberPromotion.INITS));
    }
    
    public QMemberPromotion(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberPromotion.INITS));
    }
    
    public QMemberPromotion(final PathMetadata metadata, final PathInits inits) {
        this(MemberPromotion.class, metadata, inits);
    }
    
    public QMemberPromotion(final Class<? extends MemberPromotion> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.inviteesId = (NumberPath<Long>)this.createNumber("inviteesId", (Class)Long.class);
        this.inviterId = (NumberPath<Long>)this.createNumber("inviterId", (Class)Long.class);
        this.level = (EnumPath<PromotionLevel>)this.createEnum("level", (Class)PromotionLevel.class);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberPromotion = new QMemberPromotion("memberPromotion");
    }
}
