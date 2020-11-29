package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractWalletExplodeRecord extends EntityPathBase<ContractWalletExplodeRecord>
{
    private static final long serialVersionUID = 1777264740L;
    public static final QContractWalletExplodeRecord contractWalletExplodeRecord;
    public final NumberPath<BigDecimal> afterBalance;
    public final NumberPath<BigDecimal> beforeBalance;
    public final StringPath coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> frozenAmount;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath remark;
    public final NumberPath<BigDecimal> riskRate;
    public final NumberPath<Long> sequence;
    public final NumberPath<Long> version;
    
    public QContractWalletExplodeRecord(final String variable) {
        super((Class)ContractWalletExplodeRecord.class, PathMetadataFactory.forVariable(variable));
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.beforeBalance = (NumberPath<BigDecimal>)this.createNumber("beforeBalance", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.frozenAmount = (NumberPath<BigDecimal>)this.createNumber("frozenAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.riskRate = (NumberPath<BigDecimal>)this.createNumber("riskRate", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractWalletExplodeRecord(final Path<? extends ContractWalletExplodeRecord> path) {
        super(path.getType(), path.getMetadata());
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.beforeBalance = (NumberPath<BigDecimal>)this.createNumber("beforeBalance", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.frozenAmount = (NumberPath<BigDecimal>)this.createNumber("frozenAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.riskRate = (NumberPath<BigDecimal>)this.createNumber("riskRate", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractWalletExplodeRecord(final PathMetadata metadata) {
        super((Class)ContractWalletExplodeRecord.class, metadata);
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.beforeBalance = (NumberPath<BigDecimal>)this.createNumber("beforeBalance", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.frozenAmount = (NumberPath<BigDecimal>)this.createNumber("frozenAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.riskRate = (NumberPath<BigDecimal>)this.createNumber("riskRate", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        contractWalletExplodeRecord = new QContractWalletExplodeRecord("contractWalletExplodeRecord");
    }
}
