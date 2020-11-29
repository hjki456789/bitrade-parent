package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.enums.*;
import com.querydsl.core.types.*;

public class QMemberApplication extends EntityPathBase<MemberApplication>
{
    private static final long serialVersionUID = 1766291539L;
    private static final PathInits INITS;
    public static final QMemberApplication memberApplication;
    public final EnumPath<AuditStatus> auditStatus;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final StringPath idCard;
    public final StringPath identityCardImgFront;
    public final StringPath identityCardImgInHand;
    public final StringPath identityCardImgReverse;
    public final NumberPath<Integer> kycStatus;
    public final QMember member;
    public final StringPath realName;
    public final StringPath rejectReason;
    public final EnumPath<CredentialsType> type;
    public final DateTimePath<Date> updateTime;
    public final StringPath videoRandom;
    public final StringPath videoUrl;
    
    public QMemberApplication(final String variable) {
        this(MemberApplication.class, PathMetadataFactory.forVariable(variable), QMemberApplication.INITS);
    }
    
    public QMemberApplication(final Path<? extends MemberApplication> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberApplication.INITS));
    }
    
    public QMemberApplication(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberApplication.INITS));
    }
    
    public QMemberApplication(final PathMetadata metadata, final PathInits inits) {
        this(MemberApplication.class, metadata, inits);
    }
    
    public QMemberApplication(final Class<? extends MemberApplication> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.auditStatus = (EnumPath<AuditStatus>)this.createEnum("auditStatus", (Class)AuditStatus.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.idCard = this.createString("idCard");
        this.identityCardImgFront = this.createString("identityCardImgFront");
        this.identityCardImgInHand = this.createString("identityCardImgInHand");
        this.identityCardImgReverse = this.createString("identityCardImgReverse");
        this.kycStatus = (NumberPath<Integer>)this.createNumber("kycStatus", (Class)Integer.class);
        this.realName = this.createString("realName");
        this.rejectReason = this.createString("rejectReason");
        this.type = (EnumPath<CredentialsType>)this.createEnum("type", (Class)CredentialsType.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.videoRandom = this.createString("videoRandom");
        this.videoUrl = this.createString("videoUrl");
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberApplication = new QMemberApplication("memberApplication");
    }
}
