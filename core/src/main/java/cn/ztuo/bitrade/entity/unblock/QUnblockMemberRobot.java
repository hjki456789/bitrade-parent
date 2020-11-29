package cn.ztuo.bitrade.entity.unblock;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QUnblockMemberRobot extends EntityPathBase<UnblockMemberRobot>
{
    private static final long serialVersionUID = -590669063L;
    public static final QUnblockMemberRobot unblockMemberRobot;
    public final DateTimePath<Date> expireTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Integer> status;
    public final StringPath symbol;
    public final DateTimePath<Date> updateTime;
    public final NumberPath<Long> version;
    
    public QUnblockMemberRobot(final String variable) {
        super((Class)UnblockMemberRobot.class, PathMetadataFactory.forVariable(variable));
        this.expireTime = (DateTimePath<Date>)this.createDateTime("expireTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QUnblockMemberRobot(final Path<? extends UnblockMemberRobot> path) {
        super(path.getType(), path.getMetadata());
        this.expireTime = (DateTimePath<Date>)this.createDateTime("expireTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QUnblockMemberRobot(final PathMetadata metadata) {
        super((Class)UnblockMemberRobot.class, metadata);
        this.expireTime = (DateTimePath<Date>)this.createDateTime("expireTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        unblockMemberRobot = new QUnblockMemberRobot("unblockMemberRobot");
    }
}
