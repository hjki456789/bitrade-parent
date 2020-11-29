package cn.ztuo.bitrade.entity.unblock;

import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QUnblockCommonConfig extends EntityPathBase<UnblockCommonConfig>
{
    private static final long serialVersionUID = -1054407900L;
    public static final QUnblockCommonConfig unblockCommonConfig;
    public final NumberPath<Long> id;
    public final StringPath name;
    public final NumberPath<Integer> orderId;
    public final StringPath remark;
    public final DateTimePath<Date> updateTime;
    public final StringPath value;
    public final NumberPath<Long> version;
    
    public QUnblockCommonConfig(final String variable) {
        super((Class)UnblockCommonConfig.class, PathMetadataFactory.forVariable(variable));
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.orderId = (NumberPath<Integer>)this.createNumber("orderId", (Class)Integer.class);
        this.remark = this.createString("remark");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.value = this.createString("value");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QUnblockCommonConfig(final Path<? extends UnblockCommonConfig> path) {
        super(path.getType(), path.getMetadata());
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.orderId = (NumberPath<Integer>)this.createNumber("orderId", (Class)Integer.class);
        this.remark = this.createString("remark");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.value = this.createString("value");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QUnblockCommonConfig(final PathMetadata metadata) {
        super((Class)UnblockCommonConfig.class, metadata);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.orderId = (NumberPath<Integer>)this.createNumber("orderId", (Class)Integer.class);
        this.remark = this.createString("remark");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.value = this.createString("value");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        unblockCommonConfig = new QUnblockCommonConfig("unblockCommonConfig");
    }
}
