package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QDepartment extends EntityPathBase<Department>
{
    private static final long serialVersionUID = -1428309003L;
    public static final QDepartment department;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> leaderId;
    public final StringPath name;
    public final StringPath remark;
    public final DateTimePath<Date> updateTime;
    
    public QDepartment(final String variable) {
        super((Class)Department.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leaderId = (NumberPath<Long>)this.createNumber("leaderId", (Class)Long.class);
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QDepartment(final Path<? extends Department> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leaderId = (NumberPath<Long>)this.createNumber("leaderId", (Class)Long.class);
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QDepartment(final PathMetadata metadata) {
        super((Class)Department.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.leaderId = (NumberPath<Long>)this.createNumber("leaderId", (Class)Long.class);
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        department = new QDepartment("department");
    }
}
