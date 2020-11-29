package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.enums.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QOtcWalletFlowRecord extends EntityPathBase<OtcWalletFlowRecord>
{
    private static final long serialVersionUID = -2087579149L;
    private static final PathInits INITS;
    public static final QOtcWalletFlowRecord otcWalletFlowRecord;
    public final NumberPath<BigDecimal> afterBalance;
    public final NumberPath<BigDecimal> amount;
    public final EnumPath<OtcWalletBalanceType> balanceType;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final EnumPath<OtcWalletOperationType> operationType;
    public final StringPath remark;
    public final NumberPath<Long> sequence;
    
    public QOtcWalletFlowRecord(final String variable) {
        this(OtcWalletFlowRecord.class, PathMetadataFactory.forVariable(variable), QOtcWalletFlowRecord.INITS);
    }
    
    public QOtcWalletFlowRecord(final Path<? extends OtcWalletFlowRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QOtcWalletFlowRecord.INITS));
    }
    
    public QOtcWalletFlowRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QOtcWalletFlowRecord.INITS));
    }
    
    public QOtcWalletFlowRecord(final PathMetadata metadata, final PathInits inits) {
        this(OtcWalletFlowRecord.class, metadata, inits);
    }
    
    public QOtcWalletFlowRecord(final Class<? extends OtcWalletFlowRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.balanceType = (EnumPath<OtcWalletBalanceType>)this.createEnum("balanceType", (Class)OtcWalletBalanceType.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.operationType = (EnumPath<OtcWalletOperationType>)this.createEnum("operationType", (Class)OtcWalletOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        otcWalletFlowRecord = new QOtcWalletFlowRecord("otcWalletFlowRecord");
    }
}
