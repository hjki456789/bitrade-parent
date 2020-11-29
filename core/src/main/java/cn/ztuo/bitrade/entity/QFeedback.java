package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QFeedback extends EntityPathBase<Feedback>
{
    private static final long serialVersionUID = -1029623576L;
    private static final PathInits INITS;
    public static final QFeedback feedback;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final StringPath remark;
    
    public QFeedback(final String variable) {
        this(Feedback.class, PathMetadataFactory.forVariable(variable), QFeedback.INITS);
    }
    
    public QFeedback(final Path<? extends Feedback> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QFeedback.INITS));
    }
    
    public QFeedback(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QFeedback.INITS));
    }
    
    public QFeedback(final PathMetadata metadata, final PathInits inits) {
        this(Feedback.class, metadata, inits);
    }
    
    public QFeedback(final Class<? extends Feedback> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.remark = this.createString("remark");
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        feedback = new QFeedback("feedback");
    }
}
