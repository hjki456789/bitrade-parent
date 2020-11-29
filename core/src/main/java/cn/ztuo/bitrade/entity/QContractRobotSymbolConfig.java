package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractRobotSymbolConfig extends EntityPathBase<ContractRobotSymbolConfig>
{
    private static final long serialVersionUID = -1172061041L;
    public static final QContractRobotSymbolConfig contractRobotSymbolConfig;
    public final NumberPath<BigDecimal> amountMultiple;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> fromExchangeId;
    public final StringPath fromExchangeSymbol;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    public final StringPath symbol;
    public final NumberPath<Long> version;
    
    public QContractRobotSymbolConfig(final String variable) {
        super((Class)ContractRobotSymbolConfig.class, PathMetadataFactory.forVariable(variable));
        this.amountMultiple = (NumberPath<BigDecimal>)this.createNumber("amountMultiple", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeSymbol = this.createString("fromExchangeSymbol");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractRobotSymbolConfig(final Path<? extends ContractRobotSymbolConfig> path) {
        super(path.getType(), path.getMetadata());
        this.amountMultiple = (NumberPath<BigDecimal>)this.createNumber("amountMultiple", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeSymbol = this.createString("fromExchangeSymbol");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractRobotSymbolConfig(final PathMetadata metadata) {
        super((Class)ContractRobotSymbolConfig.class, metadata);
        this.amountMultiple = (NumberPath<BigDecimal>)this.createNumber("amountMultiple", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.fromExchangeSymbol = this.createString("fromExchangeSymbol");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.symbol = this.createString("symbol");
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        contractRobotSymbolConfig = new QContractRobotSymbolConfig("contractRobotSymbolConfig");
    }
}
