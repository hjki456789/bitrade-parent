package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.enums.*;
import com.querydsl.core.types.*;

public class QContractRobotPinStrategy extends EntityPathBase<ContractRobotPinStrategy>
{
    private static final long serialVersionUID = -1930306573L;
    public static final QContractRobotPinStrategy contractRobotPinStrategy;
    public final NumberPath<BigDecimal> amountRangeMax;
    public final NumberPath<BigDecimal> amountRangeMin;
    public final NumberPath<BigDecimal> antiDirectionMultiple;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Integer> frequency;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> priceRangeMax;
    public final NumberPath<BigDecimal> priceRangeMin;
    public final StringPath remark;
    public final EnumPath<ContractRobotPinStrategySide> side;
    public final NumberPath<Long> startEffectTime;
    public final EnumPath<ContractRobotPinStrategyStatus> status;
    public final StringPath symbol;
    public final NumberPath<BigDecimal> targetPrice;
    public final DateTimePath<Date> updateTime;
    
    public QContractRobotPinStrategy(final String variable) {
        super((Class)ContractRobotPinStrategy.class, PathMetadataFactory.forVariable(variable));
        this.amountRangeMax = (NumberPath<BigDecimal>)this.createNumber("amountRangeMax", (Class)BigDecimal.class);
        this.amountRangeMin = (NumberPath<BigDecimal>)this.createNumber("amountRangeMin", (Class)BigDecimal.class);
        this.antiDirectionMultiple = (NumberPath<BigDecimal>)this.createNumber("antiDirectionMultiple", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.frequency = (NumberPath<Integer>)this.createNumber("frequency", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.priceRangeMax = (NumberPath<BigDecimal>)this.createNumber("priceRangeMax", (Class)BigDecimal.class);
        this.priceRangeMin = (NumberPath<BigDecimal>)this.createNumber("priceRangeMin", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.side = (EnumPath<ContractRobotPinStrategySide>)this.createEnum("side", (Class)ContractRobotPinStrategySide.class);
        this.startEffectTime = (NumberPath<Long>)this.createNumber("startEffectTime", (Class)Long.class);
        this.status = (EnumPath<ContractRobotPinStrategyStatus>)this.createEnum("status", (Class)ContractRobotPinStrategyStatus.class);
        this.symbol = this.createString("symbol");
        this.targetPrice = (NumberPath<BigDecimal>)this.createNumber("targetPrice", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QContractRobotPinStrategy(final Path<? extends ContractRobotPinStrategy> path) {
        super(path.getType(), path.getMetadata());
        this.amountRangeMax = (NumberPath<BigDecimal>)this.createNumber("amountRangeMax", (Class)BigDecimal.class);
        this.amountRangeMin = (NumberPath<BigDecimal>)this.createNumber("amountRangeMin", (Class)BigDecimal.class);
        this.antiDirectionMultiple = (NumberPath<BigDecimal>)this.createNumber("antiDirectionMultiple", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.frequency = (NumberPath<Integer>)this.createNumber("frequency", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.priceRangeMax = (NumberPath<BigDecimal>)this.createNumber("priceRangeMax", (Class)BigDecimal.class);
        this.priceRangeMin = (NumberPath<BigDecimal>)this.createNumber("priceRangeMin", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.side = (EnumPath<ContractRobotPinStrategySide>)this.createEnum("side", (Class)ContractRobotPinStrategySide.class);
        this.startEffectTime = (NumberPath<Long>)this.createNumber("startEffectTime", (Class)Long.class);
        this.status = (EnumPath<ContractRobotPinStrategyStatus>)this.createEnum("status", (Class)ContractRobotPinStrategyStatus.class);
        this.symbol = this.createString("symbol");
        this.targetPrice = (NumberPath<BigDecimal>)this.createNumber("targetPrice", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QContractRobotPinStrategy(final PathMetadata metadata) {
        super((Class)ContractRobotPinStrategy.class, metadata);
        this.amountRangeMax = (NumberPath<BigDecimal>)this.createNumber("amountRangeMax", (Class)BigDecimal.class);
        this.amountRangeMin = (NumberPath<BigDecimal>)this.createNumber("amountRangeMin", (Class)BigDecimal.class);
        this.antiDirectionMultiple = (NumberPath<BigDecimal>)this.createNumber("antiDirectionMultiple", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.frequency = (NumberPath<Integer>)this.createNumber("frequency", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.priceRangeMax = (NumberPath<BigDecimal>)this.createNumber("priceRangeMax", (Class)BigDecimal.class);
        this.priceRangeMin = (NumberPath<BigDecimal>)this.createNumber("priceRangeMin", (Class)BigDecimal.class);
        this.remark = this.createString("remark");
        this.side = (EnumPath<ContractRobotPinStrategySide>)this.createEnum("side", (Class)ContractRobotPinStrategySide.class);
        this.startEffectTime = (NumberPath<Long>)this.createNumber("startEffectTime", (Class)Long.class);
        this.status = (EnumPath<ContractRobotPinStrategyStatus>)this.createEnum("status", (Class)ContractRobotPinStrategyStatus.class);
        this.symbol = this.createString("symbol");
        this.targetPrice = (NumberPath<BigDecimal>)this.createNumber("targetPrice", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        contractRobotPinStrategy = new QContractRobotPinStrategy("contractRobotPinStrategy");
    }
}
