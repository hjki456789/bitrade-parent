package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderConditionRecord extends EntityPathBase<ContractExchangeOrderConditionRecord>
{
    private static final long serialVersionUID = 654728182L;
    private static final PathInits INITS;
    public static final QContractExchangeOrderConditionRecord contractExchangeOrderConditionRecord;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> ifStopLoss;
    public final EnumPath<BooleanEnum> ifStopProfit;
    public final QContractExchangeOrder order;
    public final NumberPath<Long> sequence;
    public final NumberPath<BigDecimal> stopLossPrice;
    public final NumberPath<BigDecimal> stopProfitPrice;
    
    public QContractExchangeOrderConditionRecord(final String variable) {
        this(ContractExchangeOrderConditionRecord.class, PathMetadataFactory.forVariable(variable), QContractExchangeOrderConditionRecord.INITS);
    }
    
    public QContractExchangeOrderConditionRecord(final Path<? extends ContractExchangeOrderConditionRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractExchangeOrderConditionRecord.INITS));
    }
    
    public QContractExchangeOrderConditionRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractExchangeOrderConditionRecord.INITS));
    }
    
    public QContractExchangeOrderConditionRecord(final PathMetadata metadata, final PathInits inits) {
        this(ContractExchangeOrderConditionRecord.class, metadata, inits);
    }
    
    public QContractExchangeOrderConditionRecord(final Class<? extends ContractExchangeOrderConditionRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ifStopLoss = (EnumPath<BooleanEnum>)this.createEnum("ifStopLoss", (Class)BooleanEnum.class);
        this.ifStopProfit = (EnumPath<BooleanEnum>)this.createEnum("ifStopProfit", (Class)BooleanEnum.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.stopLossPrice = (NumberPath<BigDecimal>)this.createNumber("stopLossPrice", (Class)BigDecimal.class);
        this.stopProfitPrice = (NumberPath<BigDecimal>)this.createNumber("stopProfitPrice", (Class)BigDecimal.class);
        this.order = (inits.isInitialized("order") ? new QContractExchangeOrder(this.forProperty("order"), inits.get("order")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractExchangeOrderConditionRecord = new QContractExchangeOrderConditionRecord("contractExchangeOrderConditionRecord");
    }
}
