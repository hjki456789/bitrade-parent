package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QPreCoin extends EntityPathBase<PreCoin>
{
    private static final long serialVersionUID = -1316565487L;
    private static final PathInits INITS;
    public static final QPreCoin preCoin;
    public final NumberPath<Integer> amount;
    public final NumberPath<Long> id;
    public final StringPath link;
    public final StringPath name;
    public final StringPath remark;
    public final StringPath unit;
    public final NumberPath<Long> version;
    public final QVote vote;

    public QPreCoin(final String variable) {
        this(PreCoin.class, PathMetadataFactory.forVariable(variable), QPreCoin.INITS);
    }

    public QPreCoin(final Path<? extends PreCoin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QPreCoin.INITS));
    }

    public QPreCoin(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QPreCoin.INITS));
    }

    public QPreCoin(final PathMetadata metadata, final PathInits inits) {
        this(PreCoin.class, metadata, inits);
    }

    public QPreCoin(final Class<? extends PreCoin> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<Integer>)this.createNumber("amount", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.link = this.createString("link");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
        this.unit = this.createString("unit");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.vote = (inits.isInitialized("vote") ? new QVote(this.forProperty("vote")) : null);
    }

    static {
        INITS = PathInits.DIRECT2;
        preCoin = new QPreCoin("preCoin");
    }
}
