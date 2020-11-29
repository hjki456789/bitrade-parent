package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QGiftConfig extends EntityPathBase<GiftConfig>
{
    private static final long serialVersionUID = 1721543989L;
    public static final QGiftConfig giftConfig;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> createTime;
    public final StringPath giftCoin;
    public final StringPath giftName;
    public final NumberPath<BigDecimal> haveAmount;
    public final StringPath haveCoin;
    public final NumberPath<Long> id;
    
    public QGiftConfig(final String variable) {
        super((Class)GiftConfig.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.giftCoin = this.createString("giftCoin");
        this.giftName = this.createString("giftName");
        this.haveAmount = (NumberPath<BigDecimal>)this.createNumber("haveAmount", (Class)BigDecimal.class);
        this.haveCoin = this.createString("haveCoin");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    public QGiftConfig(final Path<? extends GiftConfig> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.giftCoin = this.createString("giftCoin");
        this.giftName = this.createString("giftName");
        this.haveAmount = (NumberPath<BigDecimal>)this.createNumber("haveAmount", (Class)BigDecimal.class);
        this.haveCoin = this.createString("haveCoin");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    public QGiftConfig(final PathMetadata metadata) {
        super((Class)GiftConfig.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.giftCoin = this.createString("giftCoin");
        this.giftName = this.createString("giftName");
        this.haveAmount = (NumberPath<BigDecimal>)this.createNumber("haveAmount", (Class)BigDecimal.class);
        this.haveCoin = this.createString("haveCoin");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    static {
        giftConfig = new QGiftConfig("giftConfig");
    }
}
