package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderClose extends EntityPathBase<ContractExchangeOrderClose>
{
    private static final long serialVersionUID = 1775722786L;
    public static final QContractExchangeOrderClose contractExchangeOrderClose;
    public final NumberPath<BigDecimal> closePrice;
    public final NumberPath<BigDecimal> closeProfitOrLoss;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> orderId;
    public final NumberPath<BigDecimal> profitOrLoss;
    public final NumberPath<Long> sequence;
    public final EnumPath<ContractExchangeOrderCloseType> type;
    public final NumberPath<Long> version;
    
    public QContractExchangeOrderClose(final String variable) {
        super((Class)ContractExchangeOrderClose.class, PathMetadataFactory.forVariable(variable));
        this.closePrice = (NumberPath<BigDecimal>)this.createNumber("closePrice", (Class)BigDecimal.class);
        this.closeProfitOrLoss = (NumberPath<BigDecimal>)this.createNumber("closeProfitOrLoss", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profitOrLoss = (NumberPath<BigDecimal>)this.createNumber("profitOrLoss", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<ContractExchangeOrderCloseType>)this.createEnum("type", (Class)ContractExchangeOrderCloseType.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderClose(final Path<? extends ContractExchangeOrderClose> path) {
        super(path.getType(), path.getMetadata());
        this.closePrice = (NumberPath<BigDecimal>)this.createNumber("closePrice", (Class)BigDecimal.class);
        this.closeProfitOrLoss = (NumberPath<BigDecimal>)this.createNumber("closeProfitOrLoss", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profitOrLoss = (NumberPath<BigDecimal>)this.createNumber("profitOrLoss", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<ContractExchangeOrderCloseType>)this.createEnum("type", (Class)ContractExchangeOrderCloseType.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    public QContractExchangeOrderClose(final PathMetadata metadata) {
        super((Class)ContractExchangeOrderClose.class, metadata);
        this.closePrice = (NumberPath<BigDecimal>)this.createNumber("closePrice", (Class)BigDecimal.class);
        this.closeProfitOrLoss = (NumberPath<BigDecimal>)this.createNumber("closeProfitOrLoss", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profitOrLoss = (NumberPath<BigDecimal>)this.createNumber("profitOrLoss", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<ContractExchangeOrderCloseType>)this.createEnum("type", (Class)ContractExchangeOrderCloseType.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
    }
    
    static {
        contractExchangeOrderClose = new QContractExchangeOrderClose("contractExchangeOrderClose");
    }
}
