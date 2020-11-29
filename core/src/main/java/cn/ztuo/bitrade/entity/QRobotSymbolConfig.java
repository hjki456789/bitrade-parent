package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QRobotSymbolConfig extends EntityPathBase<RobotSymbolConfig>
{
    private static final long serialVersionUID = -1388979071L;
    public static final QRobotSymbolConfig robotSymbolConfig;
    public final NumberPath<Integer> arbitrageStatus;
    public final NumberPath<BigDecimal> baseCoinBalance;
    public final NumberPath<BigDecimal> buyPriceDownRate;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> depthAmountDownRate;
    public final NumberPath<BigDecimal> depthIntervalMax;
    public final NumberPath<BigDecimal> depthIntervalMin;
    public final NumberPath<BigDecimal> fee;
    public final NumberPath<Long> fromExchangeId;
    public final StringPath fromExchangeSymbol;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> robotOrderMax;
    public final NumberPath<BigDecimal> sellPriceUpRate;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    public final StringPath symbol;
    public final NumberPath<BigDecimal> tradeAmountUpRate;
    public final NumberPath<BigDecimal> tradeCoinBalance;
    public final NumberPath<Long> version;
    
    public QRobotSymbolConfig(final String variable) {
        super((Class)RobotSymbolConfig.class, PathMetadataFactory.forVariable(variable));
        this.arbitrageStatus = (NumberPath<Integer>)this.createNumber("arbitrageStatus", (Class)Integer.class);
        this.baseCoinBalance = (NumberPath<BigDecimal>)this.createNumber("baseCoinBalance", (Class)BigDecimal.class);
        this.buyPriceDownRate = (NumberPath<BigDecimal>)this.createNumber("buyPriceDownRate", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.depthAmountDownRate = (NumberPath<BigDecimal>)this.createNumber("depthAmountDownRate", (Class)BigDecimal.class);
        this.depthIntervalMax = (NumberPath<BigDecimal>)this.createNumber("depthIntervalMax", (Class)BigDecimal.class);
        this.depthIntervalMin = (NumberPath<BigDecimal>)this.createNumber("depthIntervalMin", (Class)BigDecimal.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeSymbol = this.createString("fromExchangeSymbol");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.robotOrderMax = (NumberPath<BigDecimal>)this.createNumber("robotOrderMax", (Class)BigDecimal.class);
        this.sellPriceUpRate = (NumberPath<BigDecimal>)this.createNumber("sellPriceUpRate", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.tradeAmountUpRate = (NumberPath<BigDecimal>)this.createNumber("tradeAmountUpRate", (Class)BigDecimal.class);
        this.tradeCoinBalance = (NumberPath<BigDecimal>)this.createNumber("tradeCoinBalance", (Class)BigDecimal.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QRobotSymbolConfig(final Path<? extends RobotSymbolConfig> path) {
        super(path.getType(), path.getMetadata());
        this.arbitrageStatus = (NumberPath<Integer>)this.createNumber("arbitrageStatus", (Class)Integer.class);
        this.baseCoinBalance = (NumberPath<BigDecimal>)this.createNumber("baseCoinBalance", (Class)BigDecimal.class);
        this.buyPriceDownRate = (NumberPath<BigDecimal>)this.createNumber("buyPriceDownRate", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.depthAmountDownRate = (NumberPath<BigDecimal>)this.createNumber("depthAmountDownRate", (Class)BigDecimal.class);
        this.depthIntervalMax = (NumberPath<BigDecimal>)this.createNumber("depthIntervalMax", (Class)BigDecimal.class);
        this.depthIntervalMin = (NumberPath<BigDecimal>)this.createNumber("depthIntervalMin", (Class)BigDecimal.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeSymbol = this.createString("fromExchangeSymbol");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.robotOrderMax = (NumberPath<BigDecimal>)this.createNumber("robotOrderMax", (Class)BigDecimal.class);
        this.sellPriceUpRate = (NumberPath<BigDecimal>)this.createNumber("sellPriceUpRate", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.tradeAmountUpRate = (NumberPath<BigDecimal>)this.createNumber("tradeAmountUpRate", (Class)BigDecimal.class);
        this.tradeCoinBalance = (NumberPath<BigDecimal>)this.createNumber("tradeCoinBalance", (Class)BigDecimal.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QRobotSymbolConfig(final PathMetadata metadata) {
        super((Class)RobotSymbolConfig.class, metadata);
        this.arbitrageStatus = (NumberPath<Integer>)this.createNumber("arbitrageStatus", (Class)Integer.class);
        this.baseCoinBalance = (NumberPath<BigDecimal>)this.createNumber("baseCoinBalance", (Class)BigDecimal.class);
        this.buyPriceDownRate = (NumberPath<BigDecimal>)this.createNumber("buyPriceDownRate", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.depthAmountDownRate = (NumberPath<BigDecimal>)this.createNumber("depthAmountDownRate", (Class)BigDecimal.class);
        this.depthIntervalMax = (NumberPath<BigDecimal>)this.createNumber("depthIntervalMax", (Class)BigDecimal.class);
        this.depthIntervalMin = (NumberPath<BigDecimal>)this.createNumber("depthIntervalMin", (Class)BigDecimal.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeSymbol = this.createString("fromExchangeSymbol");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.robotOrderMax = (NumberPath<BigDecimal>)this.createNumber("robotOrderMax", (Class)BigDecimal.class);
        this.sellPriceUpRate = (NumberPath<BigDecimal>)this.createNumber("sellPriceUpRate", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.tradeAmountUpRate = (NumberPath<BigDecimal>)this.createNumber("tradeAmountUpRate", (Class)BigDecimal.class);
        this.tradeCoinBalance = (NumberPath<BigDecimal>)this.createNumber("tradeCoinBalance", (Class)BigDecimal.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        robotSymbolConfig = new QRobotSymbolConfig("robotSymbolConfig");
    }
}
