package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QAppeal extends EntityPathBase<Appeal>
{
    private static final long serialVersionUID = -1581772878L;
    private static final PathInits INITS;
    public static final QAppeal appeal;
    public final QAdmin admin;
    public final NumberPath<Long> associateId;
    public final DateTimePath<Date> createTime;
    public final DateTimePath<Date> dealWithTime;
    public final NumberPath<Long> id;
    public final StringPath imgUrls;
    public final NumberPath<Long> initiatorId;
    public final EnumPath<BooleanEnum> isSuccess;
    public final QOrder order;
    public final StringPath remark;
    public final EnumPath<AppealStatus> status;
    
    public QAppeal(final String variable) {
        this(Appeal.class, PathMetadataFactory.forVariable(variable), QAppeal.INITS);
    }
    
    public QAppeal(final Path<? extends Appeal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QAppeal.INITS));
    }
    
    public QAppeal(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QAppeal.INITS));
    }
    
    public QAppeal(final PathMetadata metadata, final PathInits inits) {
        this(Appeal.class, metadata, inits);
    }
    
    public QAppeal(final Class<? extends Appeal> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.associateId = (NumberPath<Long>)this.createNumber("associateId", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dealWithTime = (DateTimePath<Date>)this.createDateTime("dealWithTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrls = this.createString("imgUrls");
        this.initiatorId = (NumberPath<Long>)this.createNumber("initiatorId", (Class)Long.class);
        this.isSuccess = (EnumPath<BooleanEnum>)this.createEnum("isSuccess", (Class)BooleanEnum.class);
        this.remark = this.createString("remark");
        this.status = (EnumPath<AppealStatus>)this.createEnum("status", (Class)AppealStatus.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.order = (inits.isInitialized("order") ? new QOrder(this.forProperty("order"), inits.get("order")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        appeal = new QAppeal("appeal");
    }
}
