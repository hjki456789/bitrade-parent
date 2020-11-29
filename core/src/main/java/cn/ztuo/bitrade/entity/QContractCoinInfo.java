package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QContractCoinInfo extends EntityPathBase<ContractCoinInfo>
{
    private static final long serialVersionUID = 99699828L;
    public static final QContractCoinInfo contractCoinInfo;
    public final NumberPath<Integer> coinScale;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isPlatformCoin;
    public final NumberPath<BigDecimal> jyRate;
    public final NumberPath<Integer> maxVolume;
    public final StringPath name;
    public final StringPath nameCn;
    public final NumberPath<Integer> sort;
    public final EnumPath<CommonStatus> status;
    public final StringPath unit;
    
    public QContractCoinInfo(final String variable) {
        super((Class)ContractCoinInfo.class, PathMetadataFactory.forVariable(variable));
        this.coinScale = (NumberPath<Integer>)this.createNumber("coinScale", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.jyRate = (NumberPath<BigDecimal>)this.createNumber("jyRate", (Class)BigDecimal.class);
        this.maxVolume = (NumberPath<Integer>)this.createNumber("maxVolume", (Class)Integer.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
    }
    
    public QContractCoinInfo(final Path<? extends ContractCoinInfo> path) {
        super(path.getType(), path.getMetadata());
        this.coinScale = (NumberPath<Integer>)this.createNumber("coinScale", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.jyRate = (NumberPath<BigDecimal>)this.createNumber("jyRate", (Class)BigDecimal.class);
        this.maxVolume = (NumberPath<Integer>)this.createNumber("maxVolume", (Class)Integer.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
    }
    
    public QContractCoinInfo(final PathMetadata metadata) {
        super((Class)ContractCoinInfo.class, metadata);
        this.coinScale = (NumberPath<Integer>)this.createNumber("coinScale", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.jyRate = (NumberPath<BigDecimal>)this.createNumber("jyRate", (Class)BigDecimal.class);
        this.maxVolume = (NumberPath<Integer>)this.createNumber("maxVolume", (Class)Integer.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
    }
    
    static {
        contractCoinInfo = new QContractCoinInfo("contractCoinInfo");
    }
}
