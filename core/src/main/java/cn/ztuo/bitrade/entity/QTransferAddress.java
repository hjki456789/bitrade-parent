package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QTransferAddress extends EntityPathBase<TransferAddress>
{
    private static final long serialVersionUID = 1325482694L;
    private static final PathInits INITS;
    public static final QTransferAddress transferAddress;
    public final StringPath address;
    public final QCoin coin;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> minAmount;
    public final EnumPath<CommonStatus> status;
    public final NumberPath<BigDecimal> transferFee;
    
    public QTransferAddress(final String variable) {
        this(TransferAddress.class, PathMetadataFactory.forVariable(variable), QTransferAddress.INITS);
    }
    
    public QTransferAddress(final Path<? extends TransferAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QTransferAddress.INITS));
    }
    
    public QTransferAddress(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QTransferAddress.INITS));
    }
    
    public QTransferAddress(final PathMetadata metadata, final PathInits inits) {
        this(TransferAddress.class, metadata, inits);
    }
    
    public QTransferAddress(final Class<? extends TransferAddress> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.minAmount = (NumberPath<BigDecimal>)this.createNumber("minAmount", (Class)BigDecimal.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.transferFee = (NumberPath<BigDecimal>)this.createNumber("transferFee", (Class)BigDecimal.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        transferAddress = new QTransferAddress("transferAddress");
    }
}
