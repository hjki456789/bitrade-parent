package cn.ztuo.bitrade.entity;

import java.util.*;
import java.math.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QGiftRecord extends EntityPathBase<GiftRecord>
{
    private static final long serialVersionUID = 2141427268L;
    public static final QGiftRecord giftRecord;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> giftAmount;
    public final StringPath giftCoin;
    public final StringPath giftName;
    public final NumberPath<Long> id;
    public final NumberPath<Long> userId;
    public final StringPath userMobile;
    public final StringPath userName;
    
    public QGiftRecord(final String variable) {
        super((Class)GiftRecord.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.giftAmount = (NumberPath<BigDecimal>)this.createNumber("giftAmount", (Class)BigDecimal.class);
        this.giftCoin = this.createString("giftCoin");
        this.giftName = this.createString("giftName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.userMobile = this.createString("userMobile");
        this.userName = this.createString("userName");
    }
    
    public QGiftRecord(final Path<? extends GiftRecord> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.giftAmount = (NumberPath<BigDecimal>)this.createNumber("giftAmount", (Class)BigDecimal.class);
        this.giftCoin = this.createString("giftCoin");
        this.giftName = this.createString("giftName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.userMobile = this.createString("userMobile");
        this.userName = this.createString("userName");
    }
    
    public QGiftRecord(final PathMetadata metadata) {
        super((Class)GiftRecord.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.giftAmount = (NumberPath<BigDecimal>)this.createNumber("giftAmount", (Class)BigDecimal.class);
        this.giftCoin = this.createString("giftCoin");
        this.giftName = this.createString("giftName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.userMobile = this.createString("userMobile");
        this.userName = this.createString("userName");
    }
    
    static {
        giftRecord = new QGiftRecord("giftRecord");
    }
}
