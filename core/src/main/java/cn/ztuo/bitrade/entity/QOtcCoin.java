package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QOtcCoin extends EntityPathBase<OtcCoin>
{
    private static final long serialVersionUID = 2146309388L;
    public static final QOtcCoin otcCoin;
    public final NumberPath<BigDecimal> buyMinAmount;
    public final NumberPath<Integer> coinScale;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isPlatformCoin;
    public final NumberPath<BigDecimal> jyRate;
    public final NumberPath<Integer> maxTradingTime;
    public final NumberPath<Integer> maxVolume;
    public final StringPath name;
    public final StringPath nameCn;
    public final NumberPath<BigDecimal> sellMinAmount;
    public final NumberPath<Integer> sort;
    public final EnumPath<CommonStatus> status;
    public final StringPath unit;
    
    public QOtcCoin(final String variable) {
        super((Class)OtcCoin.class, PathMetadataFactory.forVariable(variable));
        this.buyMinAmount = (NumberPath<BigDecimal>)this.createNumber("buyMinAmount", (Class)BigDecimal.class);
        this.coinScale = (NumberPath<Integer>)this.createNumber("coinScale", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.jyRate = (NumberPath<BigDecimal>)this.createNumber("jyRate", (Class)BigDecimal.class);
        this.maxTradingTime = (NumberPath<Integer>)this.createNumber("maxTradingTime", (Class)Integer.class);
        this.maxVolume = (NumberPath<Integer>)this.createNumber("maxVolume", (Class)Integer.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sellMinAmount = (NumberPath<BigDecimal>)this.createNumber("sellMinAmount", (Class)BigDecimal.class);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
    }
    
    public QOtcCoin(final Path<? extends OtcCoin> path) {
        super(path.getType(), path.getMetadata());
        this.buyMinAmount = (NumberPath<BigDecimal>)this.createNumber("buyMinAmount", (Class)BigDecimal.class);
        this.coinScale = (NumberPath<Integer>)this.createNumber("coinScale", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.jyRate = (NumberPath<BigDecimal>)this.createNumber("jyRate", (Class)BigDecimal.class);
        this.maxTradingTime = (NumberPath<Integer>)this.createNumber("maxTradingTime", (Class)Integer.class);
        this.maxVolume = (NumberPath<Integer>)this.createNumber("maxVolume", (Class)Integer.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sellMinAmount = (NumberPath<BigDecimal>)this.createNumber("sellMinAmount", (Class)BigDecimal.class);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
    }
    
    public QOtcCoin(final PathMetadata metadata) {
        super((Class)OtcCoin.class, metadata);
        this.buyMinAmount = (NumberPath<BigDecimal>)this.createNumber("buyMinAmount", (Class)BigDecimal.class);
        this.coinScale = (NumberPath<Integer>)this.createNumber("coinScale", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.jyRate = (NumberPath<BigDecimal>)this.createNumber("jyRate", (Class)BigDecimal.class);
        this.maxTradingTime = (NumberPath<Integer>)this.createNumber("maxTradingTime", (Class)Integer.class);
        this.maxVolume = (NumberPath<Integer>)this.createNumber("maxVolume", (Class)Integer.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sellMinAmount = (NumberPath<BigDecimal>)this.createNumber("sellMinAmount", (Class)BigDecimal.class);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
    }
    
    static {
        otcCoin = new QOtcCoin("otcCoin");
    }
}
