package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QTransferRecord extends EntityPathBase<TransferRecord>
{
    private static final long serialVersionUID = -1132223681L;
    private static final PathInits INITS;
    public static final QTransferRecord transferRecord;
    public final StringPath address;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> fee;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath orderSn;
    public final StringPath remark;
    
    public QTransferRecord(final String variable) {
        this(TransferRecord.class, PathMetadataFactory.forVariable(variable), QTransferRecord.INITS);
    }
    
    public QTransferRecord(final Path<? extends TransferRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QTransferRecord.INITS));
    }
    
    public QTransferRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QTransferRecord.INITS));
    }
    
    public QTransferRecord(final PathMetadata metadata, final PathInits inits) {
        this(TransferRecord.class, metadata, inits);
    }
    
    public QTransferRecord(final Class<? extends TransferRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.orderSn = this.createString("orderSn");
        this.remark = this.createString("remark");
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        transferRecord = new QTransferRecord("transferRecord");
    }
}
