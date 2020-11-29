package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QFeeStastic extends EntityPathBase<FeeStastic>
{
    private static final long serialVersionUID = 982341528L;
    public static final QFeeStastic feeStastic;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<BigDecimal> bonusAmount;
    public final DateTimePath<Date> date;
    public final NumberPath<Long> id;
    public final NumberPath<Long> sequence;
    public final StringPath symbol;
    public final StringPath unit;
    
    public QFeeStastic(final String variable) {
        super((Class)FeeStastic.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.bonusAmount = (NumberPath<BigDecimal>)this.createNumber("bonusAmount", (Class)BigDecimal.class);
        this.date = (DateTimePath<Date>)this.createDateTime("date", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.unit = this.createString("unit");
    }
    
    public QFeeStastic(final Path<? extends FeeStastic> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.bonusAmount = (NumberPath<BigDecimal>)this.createNumber("bonusAmount", (Class)BigDecimal.class);
        this.date = (DateTimePath<Date>)this.createDateTime("date", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.unit = this.createString("unit");
    }
    
    public QFeeStastic(final PathMetadata metadata) {
        super((Class)FeeStastic.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.bonusAmount = (NumberPath<BigDecimal>)this.createNumber("bonusAmount", (Class)BigDecimal.class);
        this.date = (DateTimePath<Date>)this.createDateTime("date", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.unit = this.createString("unit");
    }
    
    static {
        feeStastic = new QFeeStastic("feeStastic");
    }
}
