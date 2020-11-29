package cn.ztuo.bitrade.entity.insurance;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.enums.*;
import com.querydsl.core.types.*;

public class QWholeNetInsuranceFundWalletRecord extends EntityPathBase<WholeNetInsuranceFundWalletRecord>
{
    private static final long serialVersionUID = 587565036L;
    public static final QWholeNetInsuranceFundWalletRecord wholeNetInsuranceFundWalletRecord;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final StringPath remark;
    public final NumberPath<Long> sequence;
    public final EnumPath<WholeNetInsuranceFundWalletRecordType> type;
    
    public QWholeNetInsuranceFundWalletRecord(final String variable) {
        super((Class)WholeNetInsuranceFundWalletRecord.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<WholeNetInsuranceFundWalletRecordType>)this.createEnum("type", (Class)WholeNetInsuranceFundWalletRecordType.class);
    }
    
    public QWholeNetInsuranceFundWalletRecord(final Path<? extends WholeNetInsuranceFundWalletRecord> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<WholeNetInsuranceFundWalletRecordType>)this.createEnum("type", (Class)WholeNetInsuranceFundWalletRecordType.class);
    }
    
    public QWholeNetInsuranceFundWalletRecord(final PathMetadata metadata) {
        super((Class)WholeNetInsuranceFundWalletRecord.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<WholeNetInsuranceFundWalletRecordType>)this.createEnum("type", (Class)WholeNetInsuranceFundWalletRecordType.class);
    }
    
    static {
        wholeNetInsuranceFundWalletRecord = new QWholeNetInsuranceFundWalletRecord("wholeNetInsuranceFundWalletRecord");
    }
}
