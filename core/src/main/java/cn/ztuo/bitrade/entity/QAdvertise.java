package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import java.math.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QAdvertise extends EntityPathBase<Advertise>
{
    private static final long serialVersionUID = -902394076L;
    private static final PathInits INITS;
    public static final QAdvertise advertise;
    public final EnumPath<AdvertiseType> advertiseType;
    public final EnumPath<BooleanEnum> auto;
    public final StringPath autoword;
    public final QOtcCoin coin;
    public final StringPath coinUnit;
    public final QCountry country;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> dealAmount;
    public final NumberPath<Long> id;
    public final EnumPath<AdvertiseLevel> level;
    public final StringPath limitMoney;
    public final NumberPath<BigDecimal> maxLimit;
    public final QMember member;
    public final NumberPath<BigDecimal> minLimit;
    public final NumberPath<BigDecimal> number;
    public final StringPath payMode;
    public final NumberPath<Integer> platType;
    public final NumberPath<BigDecimal> premiseRate;
    public final NumberPath<BigDecimal> price;
    public final EnumPath<PriceType> priceType;
    public final NumberPath<BigDecimal> remainAmount;
    public final StringPath remark;
    public final EnumPath<AdvertiseControlStatus> status;
    public final NumberPath<Integer> timeLimit;
    public final DateTimePath<Date> updateTime;
    public final StringPath username;
    public final NumberPath<Long> version;
    
    public QAdvertise(final String variable) {
        this(Advertise.class, PathMetadataFactory.forVariable(variable), QAdvertise.INITS);
    }
    
    public QAdvertise(final Path<? extends Advertise> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QAdvertise.INITS));
    }
    
    public QAdvertise(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QAdvertise.INITS));
    }
    
    public QAdvertise(final PathMetadata metadata, final PathInits inits) {
        this(Advertise.class, metadata, inits);
    }
    
    public QAdvertise(final Class<? extends Advertise> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.advertiseType = (EnumPath<AdvertiseType>)this.createEnum("advertiseType", (Class)AdvertiseType.class);
        this.auto = (EnumPath<BooleanEnum>)this.createEnum("auto", (Class)BooleanEnum.class);
        this.autoword = this.createString("autoword");
        this.coinUnit = this.createString("coinUnit");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dealAmount = (NumberPath<BigDecimal>)this.createNumber("dealAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.level = (EnumPath<AdvertiseLevel>)this.createEnum("level", (Class)AdvertiseLevel.class);
        this.limitMoney = this.createString("limitMoney");
        this.maxLimit = (NumberPath<BigDecimal>)this.createNumber("maxLimit", (Class)BigDecimal.class);
        this.minLimit = (NumberPath<BigDecimal>)this.createNumber("minLimit", (Class)BigDecimal.class);
        this.number = (NumberPath<BigDecimal>)this.createNumber("number", (Class)BigDecimal.class);
        this.payMode = this.createString("payMode");
        this.platType = (NumberPath<Integer>)this.createNumber("platType", (Class)Integer.class);
        this.premiseRate = (NumberPath<BigDecimal>)this.createNumber("premiseRate", (Class)BigDecimal.class);
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.priceType = (EnumPath<PriceType>)this.createEnum("priceType", (Class)PriceType.class);
        this.remainAmount = (NumberPath<BigDecimal>)this.createNumber("remainAmount", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.status = (EnumPath<AdvertiseControlStatus>)this.createEnum("status", (Class)AdvertiseControlStatus.class);
        this.timeLimit = (NumberPath<Integer>)this.createNumber("timeLimit", (Class)Integer.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.username = this.createString("username");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.coin = (inits.isInitialized("coin") ? new QOtcCoin(this.forProperty("coin")) : null);
        this.country = (inits.isInitialized("country") ? new QCountry(this.forProperty("country")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        advertise = new QAdvertise("advertise");
    }
}
