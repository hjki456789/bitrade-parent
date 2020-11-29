package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractWalletTransferRecord extends EntityPathBase<ContractWalletTransferRecord>
{
    private static final long serialVersionUID = -1520686454L;
    private static final PathInits INITS;
    public static final QContractWalletTransferRecord contractWalletTransferRecord;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> type;
    public final NumberPath<Long> version;
    
    public QContractWalletTransferRecord(final String variable) {
        this(ContractWalletTransferRecord.class, PathMetadataFactory.forVariable(variable), QContractWalletTransferRecord.INITS);
    }
    
    public QContractWalletTransferRecord(final Path<? extends ContractWalletTransferRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractWalletTransferRecord.INITS));
    }
    
    public QContractWalletTransferRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractWalletTransferRecord.INITS));
    }
    
    public QContractWalletTransferRecord(final PathMetadata metadata, final PathInits inits) {
        this(ContractWalletTransferRecord.class, metadata, inits);
    }
    
    public QContractWalletTransferRecord(final Class<? extends ContractWalletTransferRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractWalletTransferRecord = new QContractWalletTransferRecord("contractWalletTransferRecord");
    }
}
