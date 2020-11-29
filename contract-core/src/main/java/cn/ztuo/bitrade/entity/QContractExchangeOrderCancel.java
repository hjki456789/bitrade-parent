package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrderCancel extends EntityPathBase<ContractExchangeOrderCancel>
{
    private static final long serialVersionUID = -797372272L;
    public static final QContractExchangeOrderCancel contractExchangeOrderCancel;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> orderId;
    public final StringPath reasonMsg;
    public final NumberPath<Long> sequence;
    public final EnumPath<ContractExchangeOrderCancelType> type;
    
    public QContractExchangeOrderCancel(final String variable) {
        super((Class)ContractExchangeOrderCancel.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.reasonMsg = this.createString("reasonMsg");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<ContractExchangeOrderCancelType>)this.createEnum("type", (Class)ContractExchangeOrderCancelType.class);
    }
    
    public QContractExchangeOrderCancel(final Path<? extends ContractExchangeOrderCancel> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.reasonMsg = this.createString("reasonMsg");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<ContractExchangeOrderCancelType>)this.createEnum("type", (Class)ContractExchangeOrderCancelType.class);
    }
    
    public QContractExchangeOrderCancel(final PathMetadata metadata) {
        super((Class)ContractExchangeOrderCancel.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.reasonMsg = this.createString("reasonMsg");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.type = (EnumPath<ContractExchangeOrderCancelType>)this.createEnum("type", (Class)ContractExchangeOrderCancelType.class);
    }
    
    static {
        contractExchangeOrderCancel = new QContractExchangeOrderCancel("contractExchangeOrderCancel");
    }
}
