package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.*;

public class QContractExchangeOrder extends EntityPathBase<ContractExchangeOrder>
{
    private static final long serialVersionUID = 2028938870L;
    private static final PathInits INITS;
    public static final QContractExchangeOrder contractExchangeOrder;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<BigDecimal> capitalAmount;
    public final StringPath coinId;
    public final QContractMember contractMember;
    public final NumberPath<Integer> contractType;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> ifStopLoss;
    public final EnumPath<BooleanEnum> ifStopProfit;
    public final NumberPath<Integer> isOneClickOrder;
    public final NumberPath<BigDecimal> leverMultiple;
    public final NumberPath<BigDecimal> limitPrice;
    public final NumberPath<BigDecimal> limitTriggerPrice;
    public final QMember member;
    public final NumberPath<BigDecimal> openPrice;
    public final QContractExchangeOrderCancel orderCancel;
    public final QContractExchangeOrderClose orderClose;
    public final QContractExchangeOrderFee orderFee;
    public final QContractExchangeOrderTigger orderTigger;
    public final EnumPath<ContractOrderOrigin> origin;
    public final StringPath originIp;
    public final NumberPath<BigDecimal> price;
    public final NumberPath<Long> sequence;
    public final EnumPath<ContractOrderSide> side;
    public final EnumPath<ContractOrderStatus> status;
    public final NumberPath<BigDecimal> stopLossPrice;
    public final NumberPath<BigDecimal> stopProfitPrice;
    public final StringPath symbol;
    public final EnumPath<ContractOrderType> type;
    public final NumberPath<Long> version;
    public final NumberPath<Integer> walletType;
    
    public QContractExchangeOrder(final String variable) {
        this(ContractExchangeOrder.class, PathMetadataFactory.forVariable(variable), QContractExchangeOrder.INITS);
    }
    
    public QContractExchangeOrder(final Path<? extends ContractExchangeOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractExchangeOrder.INITS));
    }
    
    public QContractExchangeOrder(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractExchangeOrder.INITS));
    }
    
    public QContractExchangeOrder(final PathMetadata metadata, final PathInits inits) {
        this(ContractExchangeOrder.class, metadata, inits);
    }
    
    public QContractExchangeOrder(final Class<? extends ContractExchangeOrder> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.capitalAmount = (NumberPath<BigDecimal>)this.createNumber("capitalAmount", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ifStopLoss = (EnumPath<BooleanEnum>)this.createEnum("ifStopLoss", (Class)BooleanEnum.class);
        this.ifStopProfit = (EnumPath<BooleanEnum>)this.createEnum("ifStopProfit", (Class)BooleanEnum.class);
        this.isOneClickOrder = (NumberPath<Integer>)this.createNumber("isOneClickOrder", (Class)Integer.class);
        this.leverMultiple = (NumberPath<BigDecimal>)this.createNumber("leverMultiple", (Class)BigDecimal.class);
        this.limitPrice = (NumberPath<BigDecimal>)this.createNumber("limitPrice", (Class)BigDecimal.class);
        this.limitTriggerPrice = (NumberPath<BigDecimal>)this.createNumber("limitTriggerPrice", (Class)BigDecimal.class);
        this.openPrice = (NumberPath<BigDecimal>)this.createNumber("openPrice", (Class)BigDecimal.class);
        this.origin = (EnumPath<ContractOrderOrigin>)this.createEnum("origin", (Class)ContractOrderOrigin.class);
        this.originIp = this.createString("originIp");
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.side = (EnumPath<ContractOrderSide>)this.createEnum("side", (Class)ContractOrderSide.class);
        this.status = (EnumPath<ContractOrderStatus>)this.createEnum("status", (Class)ContractOrderStatus.class);
        this.stopLossPrice = (NumberPath<BigDecimal>)this.createNumber("stopLossPrice", (Class)BigDecimal.class);
        this.stopProfitPrice = (NumberPath<BigDecimal>)this.createNumber("stopProfitPrice", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
        this.type = (EnumPath<ContractOrderType>)this.createEnum("type", (Class)ContractOrderType.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.walletType = (NumberPath<Integer>)this.createNumber("walletType", (Class)Integer.class);
        this.contractMember = (inits.isInitialized("contractMember") ? new QContractMember(this.forProperty("contractMember"), inits.get("contractMember")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
        this.orderCancel = (inits.isInitialized("orderCancel") ? new QContractExchangeOrderCancel(this.forProperty("orderCancel")) : null);
        this.orderClose = (inits.isInitialized("orderClose") ? new QContractExchangeOrderClose(this.forProperty("orderClose")) : null);
        this.orderFee = (inits.isInitialized("orderFee") ? new QContractExchangeOrderFee(this.forProperty("orderFee")) : null);
        this.orderTigger = (inits.isInitialized("orderTigger") ? new QContractExchangeOrderTigger(this.forProperty("orderTigger")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractExchangeOrder = new QContractExchangeOrder("contractExchangeOrder");
    }
}
