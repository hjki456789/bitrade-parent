package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QAdminAccessLog extends EntityPathBase<AdminAccessLog>
{
    private static final long serialVersionUID = 1717870548L;
    public static final QAdminAccessLog adminAccessLog;
    public final StringPath accessIp;
    public final StringPath accessMethod;
    public final DateTimePath<Date> accessTime;
    public final NumberPath<Long> adminId;
    public final NumberPath<Long> id;
    public final EnumPath<AdminModule> module;
    public final StringPath operation;
    public final StringPath uri;
    
    public QAdminAccessLog(final String variable) {
        super((Class)AdminAccessLog.class, PathMetadataFactory.forVariable(variable));
        this.accessIp = this.createString("accessIp");
        this.accessMethod = this.createString("accessMethod");
        this.accessTime = (DateTimePath<Date>)this.createDateTime("accessTime", (Class)Date.class);
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.module = (EnumPath<AdminModule>)this.createEnum("module", (Class)AdminModule.class);
        this.operation = this.createString("operation");
        this.uri = this.createString("uri");
    }
    
    public QAdminAccessLog(final Path<? extends AdminAccessLog> path) {
        super(path.getType(), path.getMetadata());
        this.accessIp = this.createString("accessIp");
        this.accessMethod = this.createString("accessMethod");
        this.accessTime = (DateTimePath<Date>)this.createDateTime("accessTime", (Class)Date.class);
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.module = (EnumPath<AdminModule>)this.createEnum("module", (Class)AdminModule.class);
        this.operation = this.createString("operation");
        this.uri = this.createString("uri");
    }
    
    public QAdminAccessLog(final PathMetadata metadata) {
        super((Class)AdminAccessLog.class, metadata);
        this.accessIp = this.createString("accessIp");
        this.accessMethod = this.createString("accessMethod");
        this.accessTime = (DateTimePath<Date>)this.createDateTime("accessTime", (Class)Date.class);
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.module = (EnumPath<AdminModule>)this.createEnum("module", (Class)AdminModule.class);
        this.operation = this.createString("operation");
        this.uri = this.createString("uri");
    }
    
    static {
        adminAccessLog = new QAdminAccessLog("adminAccessLog");
    }
}
