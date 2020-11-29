package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QMemberAddress extends EntityPathBase<MemberAddress>
{
    private static final long serialVersionUID = 144377975L;
    private static final PathInits INITS;
    public static final QMemberAddress memberAddress;
    public final StringPath address;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final DateTimePath<Date> deleteTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath remark;
    public final EnumPath<CommonStatus> status;
    
    public QMemberAddress(final String variable) {
        this(MemberAddress.class, PathMetadataFactory.forVariable(variable), QMemberAddress.INITS);
    }
    
    public QMemberAddress(final Path<? extends MemberAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberAddress.INITS));
    }
    
    public QMemberAddress(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberAddress.INITS));
    }
    
    public QMemberAddress(final PathMetadata metadata, final PathInits inits) {
        this(MemberAddress.class, metadata, inits);
    }
    
    public QMemberAddress(final Class<? extends MemberAddress> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.deleteTime = (DateTimePath<Date>)this.createDateTime("deleteTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberAddress = new QMemberAddress("memberAddress");
    }
}
