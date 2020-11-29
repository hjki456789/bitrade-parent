package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractWalletFlowRecord extends EntityPathBase<ContractWalletFlowRecord>
{
    private static final long serialVersionUID = -989684595L;
    private static final PathInits INITS;
    public static final QContractWalletFlowRecord contractWalletFlowRecord;
    public final NumberPath<BigDecimal> afterBalance;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final NumberPath<Integer> contractType;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final EnumPath<ContractWalletOperationType> operationType;
    public final NumberPath<Long> relationDetailId;
    public final StringPath remark;
    public final NumberPath<Long> sequence;
    public final NumberPath<Long> version;
    
    public QContractWalletFlowRecord(final String variable) {
        this(ContractWalletFlowRecord.class, PathMetadataFactory.forVariable(variable), QContractWalletFlowRecord.INITS);
    }
    
    public QContractWalletFlowRecord(final Path<? extends ContractWalletFlowRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractWalletFlowRecord.INITS));
    }
    
    public QContractWalletFlowRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractWalletFlowRecord.INITS));
    }
    
    public QContractWalletFlowRecord(final PathMetadata metadata, final PathInits inits) {
        this(ContractWalletFlowRecord.class, metadata, inits);
    }
    
    public QContractWalletFlowRecord(final Class<? extends ContractWalletFlowRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.operationType = (EnumPath<ContractWalletOperationType>)this.createEnum("operationType", (Class)ContractWalletOperationType.class);
        this.relationDetailId = (NumberPath<Long>)this.createNumber("relationDetailId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractWalletFlowRecord = new QContractWalletFlowRecord("contractWalletFlowRecord");
    }
}
