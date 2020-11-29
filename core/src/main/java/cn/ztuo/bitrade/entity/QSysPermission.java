package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QSysPermission extends EntityPathBase<SysPermission>
{
    private static final long serialVersionUID = -1431395079L;
    public static final QSysPermission sysPermission;
    public final StringPath description;
    public final NumberPath<Long> id;
    public final StringPath name;
    public final NumberPath<Long> parentId;
    public final ListPath<SysRole, QSysRole> roles;
    public final NumberPath<Integer> sort;
    public final StringPath title;
    
    public QSysPermission(final String variable) {
        super((Class)SysPermission.class, PathMetadataFactory.forVariable(variable));
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.parentId = (NumberPath<Long>)this.createNumber("parentId", (Class)Long.class);
        this.roles = (ListPath<SysRole, QSysRole>)this.createList("roles", (Class)SysRole.class, (Class)QSysRole.class, PathInits.DIRECT2);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.title = this.createString("title");
    }
    
    public QSysPermission(final Path<? extends SysPermission> path) {
        super(path.getType(), path.getMetadata());
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.parentId = (NumberPath<Long>)this.createNumber("parentId", (Class)Long.class);
        this.roles = (ListPath<SysRole, QSysRole>)this.createList("roles", (Class)SysRole.class, (Class)QSysRole.class, PathInits.DIRECT2);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.title = this.createString("title");
    }
    
    public QSysPermission(final PathMetadata metadata) {
        super((Class)SysPermission.class, metadata);
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.parentId = (NumberPath<Long>)this.createNumber("parentId", (Class)Long.class);
        this.roles = (ListPath<SysRole, QSysRole>)this.createList("roles", (Class)SysRole.class, (Class)QSysRole.class, PathInits.DIRECT2);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.title = this.createString("title");
    }
    
    static {
        sysPermission = new QSysPermission("sysPermission");
    }
}
