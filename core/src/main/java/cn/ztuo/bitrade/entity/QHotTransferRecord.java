package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QHotTransferRecord extends EntityPathBase<HotTransferRecord>
{
    private static final long serialVersionUID = -2132499514L;
    public static final QHotTransferRecord hotTransferRecord;
    public final NumberPath<Long> adminId;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<BigDecimal> balance;
    public final StringPath coldAddress;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> minerFee;
    public final StringPath transactionNumber;
    public final DateTimePath<Date> transferTime;
    public final StringPath unit;
    
    public QHotTransferRecord(final String variable) {
        super((Class)HotTransferRecord.class, PathMetadataFactory.forVariable(variable));
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coldAddress = this.createString("coldAddress");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.minerFee = (NumberPath<BigDecimal>)this.createNumber("minerFee", (Class)BigDecimal.class);
        this.transactionNumber = this.createString("transactionNumber");
        this.transferTime = (DateTimePath<Date>)this.createDateTime("transferTime", (Class)Date.class);
        this.unit = this.createString("unit");
    }
    
    public QHotTransferRecord(final Path<? extends HotTransferRecord> path) {
        super(path.getType(), path.getMetadata());
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coldAddress = this.createString("coldAddress");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.minerFee = (NumberPath<BigDecimal>)this.createNumber("minerFee", (Class)BigDecimal.class);
        this.transactionNumber = this.createString("transactionNumber");
        this.transferTime = (DateTimePath<Date>)this.createDateTime("transferTime", (Class)Date.class);
        this.unit = this.createString("unit");
    }
    
    public QHotTransferRecord(final PathMetadata metadata) {
        super((Class)HotTransferRecord.class, metadata);
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coldAddress = this.createString("coldAddress");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.minerFee = (NumberPath<BigDecimal>)this.createNumber("minerFee", (Class)BigDecimal.class);
        this.transactionNumber = this.createString("transactionNumber");
        this.transferTime = (DateTimePath<Date>)this.createDateTime("transferTime", (Class)Date.class);
        this.unit = this.createString("unit");
    }
    
    static {
        hotTransferRecord = new QHotTransferRecord("hotTransferRecord");
    }
}
