package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QDividendStartRecord extends EntityPathBase<DividendStartRecord>
{
    private static final long serialVersionUID = -1218070879L;
    private static final PathInits INITS;
    public static final QDividendStartRecord dividendStartRecord;
    public final QAdmin admin;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> date;
    public final NumberPath<Long> end;
    public final DateTimePath<Date> endDate;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> rate;
    public final NumberPath<Long> start;
    public final DateTimePath<Date> startDate;
    public final StringPath unit;
    
    public QDividendStartRecord(final String variable) {
        this(DividendStartRecord.class, PathMetadataFactory.forVariable(variable), QDividendStartRecord.INITS);
    }
    
    public QDividendStartRecord(final Path<? extends DividendStartRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QDividendStartRecord.INITS));
    }
    
    public QDividendStartRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QDividendStartRecord.INITS));
    }
    
    public QDividendStartRecord(final PathMetadata metadata, final PathInits inits) {
        this(DividendStartRecord.class, metadata, inits);
    }
    
    public QDividendStartRecord(final Class<? extends DividendStartRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.date = (DateTimePath<Date>)this.createDateTime("date", (Class)Date.class);
        this.end = (NumberPath<Long>)this.createNumber("end", (Class)Long.class);
        this.endDate = (DateTimePath<Date>)this.createDateTime("endDate", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.rate = (NumberPath<BigDecimal>)this.createNumber("rate", (Class)BigDecimal.class);
        this.start = (NumberPath<Long>)this.createNumber("start", (Class)Long.class);
        this.startDate = (DateTimePath<Date>)this.createDateTime("startDate", (Class)Date.class);
        this.unit = this.createString("unit");
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        dividendStartRecord = new QDividendStartRecord("dividendStartRecord");
    }
}
