package cn.ztuo.bitrade.entity.contractstrategy;

import java.util.*;
import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QContractStrategyConfig extends EntityPathBase<ContractStrategyConfig>
{
    private static final long serialVersionUID = 388065869L;
    public static final QContractStrategyConfig contractStrategyConfig;
    public final NumberPath<Long> adminId;
    public final StringPath apiKey;
    public final DateTimePath<Date> createTime;
    public final StringPath expireDate;
    public final NumberPath<Long> fromExchangeId;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> priceFallRate;
    public final NumberPath<BigDecimal> priceRiseRate;
    public final StringPath remark;
    public final StringPath secretKey;
    public final EnumPath<CommonStatus> status;
    public final StringPath symbol;
    public final DateTimePath<Date> updateTime;
    
    public QContractStrategyConfig(final String variable) {
        super((Class)ContractStrategyConfig.class, PathMetadataFactory.forVariable(variable));
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.expireDate = this.createString("expireDate");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.priceFallRate = (NumberPath<BigDecimal>)this.createNumber("priceFallRate", (Class)BigDecimal.class);
        this.priceRiseRate = (NumberPath<BigDecimal>)this.createNumber("priceRiseRate", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QContractStrategyConfig(final Path<? extends ContractStrategyConfig> path) {
        super(path.getType(), path.getMetadata());
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.expireDate = this.createString("expireDate");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.priceFallRate = (NumberPath<BigDecimal>)this.createNumber("priceFallRate", (Class)BigDecimal.class);
        this.priceRiseRate = (NumberPath<BigDecimal>)this.createNumber("priceRiseRate", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QContractStrategyConfig(final PathMetadata metadata) {
        super((Class)ContractStrategyConfig.class, metadata);
        this.adminId = (NumberPath<Long>)this.createNumber("adminId", (Class)Long.class);
        this.apiKey = this.createString("apiKey");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.expireDate = this.createString("expireDate");
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.priceFallRate = (NumberPath<BigDecimal>)this.createNumber("priceFallRate", (Class)BigDecimal.class);
        this.priceRiseRate = (NumberPath<BigDecimal>)this.createNumber("priceRiseRate", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.symbol = this.createString("symbol");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        contractStrategyConfig = new QContractStrategyConfig("contractStrategyConfig");
    }
}
