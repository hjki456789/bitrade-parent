package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QSysHelp extends EntityPathBase<SysHelp>
{
    private static final long serialVersionUID = 1559418347L;
    public static final QSysHelp sysHelp;
    public final StringPath author;
    public final StringPath content;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final StringPath imgUrl;
    public final StringPath isTop;
    public final NumberPath<Integer> sort;
    public final EnumPath<CommonStatus> status;
    public final EnumPath<SysHelpClassification> sysHelpClassification;
    public final StringPath title;
    
    public QSysHelp(final String variable) {
        super((Class)SysHelp.class, PathMetadataFactory.forVariable(variable));
        this.author = this.createString("author");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.isTop = this.createString("isTop");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.sysHelpClassification = (EnumPath<SysHelpClassification>)this.createEnum("sysHelpClassification", (Class)SysHelpClassification.class);
        this.title = this.createString("title");
    }
    
    public QSysHelp(final Path<? extends SysHelp> path) {
        super(path.getType(), path.getMetadata());
        this.author = this.createString("author");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.isTop = this.createString("isTop");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.sysHelpClassification = (EnumPath<SysHelpClassification>)this.createEnum("sysHelpClassification", (Class)SysHelpClassification.class);
        this.title = this.createString("title");
    }
    
    public QSysHelp(final PathMetadata metadata) {
        super((Class)SysHelp.class, metadata);
        this.author = this.createString("author");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.isTop = this.createString("isTop");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.sysHelpClassification = (EnumPath<SysHelpClassification>)this.createEnum("sysHelpClassification", (Class)SysHelpClassification.class);
        this.title = this.createString("title");
    }
    
    static {
        sysHelp = new QSysHelp("sysHelp");
    }
}
