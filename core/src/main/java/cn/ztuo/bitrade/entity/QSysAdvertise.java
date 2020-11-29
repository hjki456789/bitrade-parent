package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QSysAdvertise extends EntityPathBase<SysAdvertise>
{
    private static final long serialVersionUID = -2105361475L;
    public static final QSysAdvertise sysAdvertise;
    public final StringPath author;
    public final StringPath content;
    public final DateTimePath<Date> createTime;
    public final StringPath endTime;
    public final StringPath linkUrl;
    public final StringPath name;
    public final StringPath remark;
    public final StringPath serialNumber;
    public final NumberPath<Integer> sort;
    public final StringPath startTime;
    public final EnumPath<CommonStatus> status;
    public final EnumPath<SysAdvertiseLocation> sysAdvertiseLocation;
    public final StringPath url;
    
    public QSysAdvertise(final String variable) {
        super((Class)SysAdvertise.class, PathMetadataFactory.forVariable(variable));
        this.author = this.createString("author");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.endTime = this.createString("endTime");
        this.linkUrl = this.createString("linkUrl");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.serialNumber = this.createString("serialNumber");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.startTime = this.createString("startTime");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.sysAdvertiseLocation = (EnumPath<SysAdvertiseLocation>)this.createEnum("sysAdvertiseLocation", (Class)SysAdvertiseLocation.class);
        this.url = this.createString("url");
    }
    
    public QSysAdvertise(final Path<? extends SysAdvertise> path) {
        super(path.getType(), path.getMetadata());
        this.author = this.createString("author");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.endTime = this.createString("endTime");
        this.linkUrl = this.createString("linkUrl");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.serialNumber = this.createString("serialNumber");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.startTime = this.createString("startTime");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.sysAdvertiseLocation = (EnumPath<SysAdvertiseLocation>)this.createEnum("sysAdvertiseLocation", (Class)SysAdvertiseLocation.class);
        this.url = this.createString("url");
    }
    
    public QSysAdvertise(final PathMetadata metadata) {
        super((Class)SysAdvertise.class, metadata);
        this.author = this.createString("author");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.endTime = this.createString("endTime");
        this.linkUrl = this.createString("linkUrl");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.serialNumber = this.createString("serialNumber");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.startTime = this.createString("startTime");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.sysAdvertiseLocation = (EnumPath<SysAdvertiseLocation>)this.createEnum("sysAdvertiseLocation", (Class)SysAdvertiseLocation.class);
        this.url = this.createString("url");
    }
    
    static {
        sysAdvertise = new QSysAdvertise("sysAdvertise");
    }
}
