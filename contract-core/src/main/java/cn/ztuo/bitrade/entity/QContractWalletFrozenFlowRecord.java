package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.*;

public class QContractWalletFrozenFlowRecord extends EntityPathBase<ContractWalletFrozenFlowRecord>
{
    private static final long serialVersionUID = 2102556877L;
    public static final QContractWalletFrozenFlowRecord contractWalletFrozenFlowRecord;
    public final NumberPath<BigDecimal> afterFrozenBalance;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coinId;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final EnumPath<ContractWalletFrozenOperationType> operationType;
    public final StringPath remark;
    public final NumberPath<Long> sequence;
    
    public QContractWalletFrozenFlowRecord(final String variable) {
        super((Class)ContractWalletFrozenFlowRecord.class, PathMetadataFactory.forVariable(variable));
        this.afterFrozenBalance = (NumberPath<BigDecimal>)this.createNumber("afterFrozenBalance", (Class)BigDecimal.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.operationType = (EnumPath<ContractWalletFrozenOperationType>)this.createEnum("operationType", (Class)ContractWalletFrozenOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QContractWalletFrozenFlowRecord(final Path<? extends ContractWalletFrozenFlowRecord> path) {
        super(path.getType(), path.getMetadata());
        this.afterFrozenBalance = (NumberPath<BigDecimal>)this.createNumber("afterFrozenBalance", (Class)BigDecimal.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.operationType = (EnumPath<ContractWalletFrozenOperationType>)this.createEnum("operationType", (Class)ContractWalletFrozenOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QContractWalletFrozenFlowRecord(final PathMetadata metadata) {
        super((Class)ContractWalletFrozenFlowRecord.class, metadata);
        this.afterFrozenBalance = (NumberPath<BigDecimal>)this.createNumber("afterFrozenBalance", (Class)BigDecimal.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.operationType = (EnumPath<ContractWalletFrozenOperationType>)this.createEnum("operationType", (Class)ContractWalletFrozenOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    static {
        contractWalletFrozenFlowRecord = new QContractWalletFrozenFlowRecord("contractWalletFrozenFlowRecord");
    }
}
