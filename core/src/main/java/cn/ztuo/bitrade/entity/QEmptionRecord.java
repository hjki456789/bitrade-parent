package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QEmptionRecord extends EntityPathBase<EmptionRecord>
{
    private static final long serialVersionUID = 85908426L;
    public static final QEmptionRecord emptionRecord;
    public final DateTimePath<Date> createTime;
    public final DateTimePath<Date> endTime;
    public final DateTimePath<Date> expectTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> ieoId;
    public final StringPath ieoName;
    public final NumberPath<BigDecimal> payAmount;
    public final StringPath picView;
    public final StringPath raiseCoin;
    public final NumberPath<BigDecimal> ratio;
    public final NumberPath<BigDecimal> receiveAmount;
    public final NumberPath<BigDecimal> saleAmount;
    public final StringPath saleCoin;
    public final DateTimePath<Date> startTime;
    public final StringPath status;
    public final NumberPath<Long> userId;
    public final StringPath userMobile;
    public final StringPath userName;
    
    public QEmptionRecord(final String variable) {
        super((Class)EmptionRecord.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.expectTime = (DateTimePath<Date>)this.createDateTime("expectTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ieoId = (NumberPath<Long>)this.createNumber("ieoId", (Class)Long.class);
        this.ieoName = this.createString("ieoName");
        this.payAmount = (NumberPath<BigDecimal>)this.createNumber("payAmount", (Class)BigDecimal.class);
        this.picView = this.createString("picView");
        this.raiseCoin = this.createString("raiseCoin");
        this.ratio = (NumberPath<BigDecimal>)this.createNumber("ratio", (Class)BigDecimal.class);
        this.receiveAmount = (NumberPath<BigDecimal>)this.createNumber("receiveAmount", (Class)BigDecimal.class);
        this.saleAmount = (NumberPath<BigDecimal>)this.createNumber("saleAmount", (Class)BigDecimal.class);
        this.saleCoin = this.createString("saleCoin");
        this.startTime = (DateTimePath<Date>)this.createDateTime("startTime", (Class)Date.class);
        this.status = this.createString("status");
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.userMobile = this.createString("userMobile");
        this.userName = this.createString("userName");
    }
    
    public QEmptionRecord(final Path<? extends EmptionRecord> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.expectTime = (DateTimePath<Date>)this.createDateTime("expectTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ieoId = (NumberPath<Long>)this.createNumber("ieoId", (Class)Long.class);
        this.ieoName = this.createString("ieoName");
        this.payAmount = (NumberPath<BigDecimal>)this.createNumber("payAmount", (Class)BigDecimal.class);
        this.picView = this.createString("picView");
        this.raiseCoin = this.createString("raiseCoin");
        this.ratio = (NumberPath<BigDecimal>)this.createNumber("ratio", (Class)BigDecimal.class);
        this.receiveAmount = (NumberPath<BigDecimal>)this.createNumber("receiveAmount", (Class)BigDecimal.class);
        this.saleAmount = (NumberPath<BigDecimal>)this.createNumber("saleAmount", (Class)BigDecimal.class);
        this.saleCoin = this.createString("saleCoin");
        this.startTime = (DateTimePath<Date>)this.createDateTime("startTime", (Class)Date.class);
        this.status = this.createString("status");
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.userMobile = this.createString("userMobile");
        this.userName = this.createString("userName");
    }
    
    public QEmptionRecord(final PathMetadata metadata) {
        super((Class)EmptionRecord.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.expectTime = (DateTimePath<Date>)this.createDateTime("expectTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ieoId = (NumberPath<Long>)this.createNumber("ieoId", (Class)Long.class);
        this.ieoName = this.createString("ieoName");
        this.payAmount = (NumberPath<BigDecimal>)this.createNumber("payAmount", (Class)BigDecimal.class);
        this.picView = this.createString("picView");
        this.raiseCoin = this.createString("raiseCoin");
        this.ratio = (NumberPath<BigDecimal>)this.createNumber("ratio", (Class)BigDecimal.class);
        this.receiveAmount = (NumberPath<BigDecimal>)this.createNumber("receiveAmount", (Class)BigDecimal.class);
        this.saleAmount = (NumberPath<BigDecimal>)this.createNumber("saleAmount", (Class)BigDecimal.class);
        this.saleCoin = this.createString("saleCoin");
        this.startTime = (DateTimePath<Date>)this.createDateTime("startTime", (Class)Date.class);
        this.status = this.createString("status");
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.userMobile = this.createString("userMobile");
        this.userName = this.createString("userName");
    }
    
    static {
        emptionRecord = new QEmptionRecord("emptionRecord");
    }
}
