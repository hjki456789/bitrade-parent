package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QAirdrop extends EntityPathBase<Airdrop>
{
    private static final long serialVersionUID = -1988889226L;
    private static final PathInits INITS;
    public static final QAirdrop airdrop;
    public final QAdmin admin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Integer> errorIndex;
    public final StringPath errorMsg;
    public final StringPath fileName;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> status;
    public final NumberPath<Integer> successCount;
    
    public QAirdrop(final String variable) {
        this(Airdrop.class, PathMetadataFactory.forVariable(variable), QAirdrop.INITS);
    }
    
    public QAirdrop(final Path<? extends Airdrop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QAirdrop.INITS));
    }
    
    public QAirdrop(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QAirdrop.INITS));
    }
    
    public QAirdrop(final PathMetadata metadata, final PathInits inits) {
        this(Airdrop.class, metadata, inits);
    }
    
    public QAirdrop(final Class<? extends Airdrop> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.errorIndex = (NumberPath<Integer>)this.createNumber("errorIndex", (Class)Integer.class);
        this.errorMsg = this.createString("errorMsg");
        this.fileName = this.createString("fileName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.successCount = (NumberPath<Integer>)this.createNumber("successCount", (Class)Integer.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        airdrop = new QAirdrop("airdrop");
    }
}
