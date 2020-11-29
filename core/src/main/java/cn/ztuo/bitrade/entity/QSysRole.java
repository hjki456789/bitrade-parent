package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QSysRole extends EntityPathBase<SysRole>
{
    private static final long serialVersionUID = 1559725856L;
    public static final QSysRole sysRole;
    public final StringPath description;
    public final NumberPath<Long> id;
    public final ListPath<SysPermission, QSysPermission> permissions;
    public final StringPath role;
    
    public QSysRole(final String variable) {
        super((Class)SysRole.class, PathMetadataFactory.forVariable(variable));
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.permissions = (ListPath<SysPermission, QSysPermission>)this.createList("permissions", (Class)SysPermission.class, (Class)QSysPermission.class, PathInits.DIRECT2);
        this.role = this.createString("role");
    }
    
    public QSysRole(final Path<? extends SysRole> path) {
        super(path.getType(), path.getMetadata());
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.permissions = (ListPath<SysPermission, QSysPermission>)this.createList("permissions", (Class)SysPermission.class, (Class)QSysPermission.class, PathInits.DIRECT2);
        this.role = this.createString("role");
    }
    
    public QSysRole(final PathMetadata metadata) {
        super((Class)SysRole.class, metadata);
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.permissions = (ListPath<SysPermission, QSysPermission>)this.createList("permissions", (Class)SysPermission.class, (Class)QSysPermission.class, PathInits.DIRECT2);
        this.role = this.createString("role");
    }
    
    static {
        sysRole = new QSysRole("sysRole");
    }
}
