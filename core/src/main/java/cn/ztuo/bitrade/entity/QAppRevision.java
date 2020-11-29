package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QAppRevision extends EntityPathBase<AppRevision>
{
    private static final long serialVersionUID = -1364688679L;
    public static final QAppRevision appRevision;
    public final StringPath downloadUrl;
    public final NumberPath<Long> id;
    public final EnumPath<Platform> platform;
    public final DateTimePath<Date> publishTime;
    public final StringPath remark;
    public final StringPath version;
    
    public QAppRevision(final String variable) {
        super((Class)AppRevision.class, PathMetadataFactory.forVariable(variable));
        this.downloadUrl = this.createString("downloadUrl");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.platform = (EnumPath<Platform>)this.createEnum("platform", (Class)Platform.class);
        this.publishTime = (DateTimePath<Date>)this.createDateTime("publishTime", (Class)Date.class);
        this.remark = this.createString("remark");
        this.version = this.createString("version");
    }
    
    public QAppRevision(final Path<? extends AppRevision> path) {
        super(path.getType(), path.getMetadata());
        this.downloadUrl = this.createString("downloadUrl");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.platform = (EnumPath<Platform>)this.createEnum("platform", (Class)Platform.class);
        this.publishTime = (DateTimePath<Date>)this.createDateTime("publishTime", (Class)Date.class);
        this.remark = this.createString("remark");
        this.version = this.createString("version");
    }
    
    public QAppRevision(final PathMetadata metadata) {
        super((Class)AppRevision.class, metadata);
        this.downloadUrl = this.createString("downloadUrl");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.platform = (EnumPath<Platform>)this.createEnum("platform", (Class)Platform.class);
        this.publishTime = (DateTimePath<Date>)this.createDateTime("publishTime", (Class)Date.class);
        this.remark = this.createString("remark");
        this.version = this.createString("version");
    }
    
    static {
        appRevision = new QAppRevision("appRevision");
    }
}
