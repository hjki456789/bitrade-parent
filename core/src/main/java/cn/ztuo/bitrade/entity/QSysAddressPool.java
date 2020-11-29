package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QSysAddressPool extends EntityPathBase<SysAddressPool>
{
    private static final long serialVersionUID = 360289350L;
    public static final QSysAddressPool sysAddressPool;
    public final StringPath address;
    public final NumberPath<Integer> status;
    public final NumberPath<Integer> type;
    
    public QSysAddressPool(final String variable) {
        super((Class)SysAddressPool.class, PathMetadataFactory.forVariable(variable));
        this.address = this.createString("address");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    public QSysAddressPool(final Path<? extends SysAddressPool> path) {
        super(path.getType(), path.getMetadata());
        this.address = this.createString("address");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    public QSysAddressPool(final PathMetadata metadata) {
        super((Class)SysAddressPool.class, metadata);
        this.address = this.createString("address");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    static {
        sysAddressPool = new QSysAddressPool("sysAddressPool");
    }
}
