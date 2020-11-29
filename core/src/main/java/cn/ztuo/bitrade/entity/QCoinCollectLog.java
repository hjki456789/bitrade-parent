package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QCoinCollectLog extends EntityPathBase<CoinCollectLog>
{
    private static final long serialVersionUID = 1367997006L;
    private static final PathInits INITS;
    public static final QCoinCollectLog coinCollectLog;
    public final StringPath address;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coinName;
    public final NumberPath<Long> id;
    public final QMember member;
    public final DateTimePath<Date> time;
    public final StringPath toAddress;
    public final StringPath txHash;
    
    public QCoinCollectLog(final String variable) {
        this(CoinCollectLog.class, PathMetadataFactory.forVariable(variable), QCoinCollectLog.INITS);
    }
    
    public QCoinCollectLog(final Path<? extends CoinCollectLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QCoinCollectLog.INITS));
    }
    
    public QCoinCollectLog(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QCoinCollectLog.INITS));
    }
    
    public QCoinCollectLog(final PathMetadata metadata, final PathInits inits) {
        this(CoinCollectLog.class, metadata, inits);
    }
    
    public QCoinCollectLog(final Class<? extends CoinCollectLog> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
        this.toAddress = this.createString("toAddress");
        this.txHash = this.createString("txHash");
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        coinCollectLog = new QCoinCollectLog("coinCollectLog");
    }
}
