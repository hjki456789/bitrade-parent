package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QAdmin extends EntityPathBase<Admin>
{
    private static final long serialVersionUID = 1056993484L;
    private static final PathInits INITS;
    public static final QAdmin admin;
    public final StringPath areaCode;
    public final StringPath avatar;
    public final QDepartment department;
    public final StringPath email;
    public final EnumPath<CommonStatus> enable;
    public final DateTimePath<Date> googleDate;
    public final StringPath googleKey;
    public final NumberPath<Integer> googleState;
    public final NumberPath<Long> id;
    public final StringPath lastLoginIp;
    public final DateTimePath<Date> lastLoginTime;
    public final StringPath mobilePhone;
    public final StringPath password;
    public final NumberPath<Long> proxyId;
    public final StringPath qq;
    public final StringPath realName;
    public final NumberPath<Long> roleId;
    public final EnumPath<CommonStatus> status;
    public final StringPath username;
    
    public QAdmin(final String variable) {
        this(Admin.class, PathMetadataFactory.forVariable(variable), QAdmin.INITS);
    }
    
    public QAdmin(final Path<? extends Admin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QAdmin.INITS));
    }
    
    public QAdmin(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QAdmin.INITS));
    }
    
    public QAdmin(final PathMetadata metadata, final PathInits inits) {
        this(Admin.class, metadata, inits);
    }
    
    public QAdmin(final Class<? extends Admin> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.areaCode = this.createString("areaCode");
        this.avatar = this.createString("avatar");
        this.email = this.createString("email");
        this.enable = (EnumPath<CommonStatus>)this.createEnum("enable", (Class)CommonStatus.class);
        this.googleDate = (DateTimePath<Date>)this.createDateTime("googleDate", (Class)Date.class);
        this.googleKey = this.createString("googleKey");
        this.googleState = (NumberPath<Integer>)this.createNumber("googleState", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lastLoginIp = this.createString("lastLoginIp");
        this.lastLoginTime = (DateTimePath<Date>)this.createDateTime("lastLoginTime", (Class)Date.class);
        this.mobilePhone = this.createString("mobilePhone");
        this.password = this.createString("password");
        this.proxyId = (NumberPath<Long>)this.createNumber("proxyId", (Class)Long.class);
        this.qq = this.createString("qq");
        this.realName = this.createString("realName");
        this.roleId = (NumberPath<Long>)this.createNumber("roleId", (Class)Long.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.username = this.createString("username");
        this.department = (inits.isInitialized("department") ? new QDepartment(this.forProperty("department")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        admin = new QAdmin("admin");
    }
}
