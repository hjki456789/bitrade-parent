package cn.ztuo.bitrade.entity.unblock;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QUnblockRobotTicket extends EntityPathBase<UnblockRobotTicket>
{
    private static final long serialVersionUID = 522872095L;
    public static final QUnblockRobotTicket unblockRobotTicket;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Integer> effectiveDay;
    public final StringPath id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Integer> status;
    public final DateTimePath<Date> useTime;
    
    public QUnblockRobotTicket(final String variable) {
        super((Class)UnblockRobotTicket.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.effectiveDay = (NumberPath<Integer>)this.createNumber("effectiveDay", (Class)Integer.class);
        this.id = this.createString("id");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.useTime = (DateTimePath<Date>)this.createDateTime("useTime", (Class)Date.class);
    }
    
    public QUnblockRobotTicket(final Path<? extends UnblockRobotTicket> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.effectiveDay = (NumberPath<Integer>)this.createNumber("effectiveDay", (Class)Integer.class);
        this.id = this.createString("id");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.useTime = (DateTimePath<Date>)this.createDateTime("useTime", (Class)Date.class);
    }
    
    public QUnblockRobotTicket(final PathMetadata metadata) {
        super((Class)UnblockRobotTicket.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.effectiveDay = (NumberPath<Integer>)this.createNumber("effectiveDay", (Class)Integer.class);
        this.id = this.createString("id");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.useTime = (DateTimePath<Date>)this.createDateTime("useTime", (Class)Date.class);
    }
    
    static {
        unblockRobotTicket = new QUnblockRobotTicket("unblockRobotTicket");
    }
}
