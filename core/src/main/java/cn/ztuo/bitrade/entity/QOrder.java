package cn.ztuo.bitrade.entity;

import java.util.*;
import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QOrder extends EntityPathBase<Order>
{
    private static final long serialVersionUID = 1070331083L;
    private static final PathInits INITS;
    public static final QOrder order;
    public final NumberPath<Long> advertiseId;
    public final EnumPath<AdvertiseType> advertiseType;
    public final QAlipay alipay;
    public final QBankInfo bankInfo;
    public final DateTimePath<Date> cancelTime;
    public final QOtcCoin coin;
    public final NumberPath<BigDecimal> commission;
    public final StringPath country;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> customerId;
    public final StringPath customerName;
    public final StringPath customerRealName;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> maxLimit;
    public final NumberPath<Long> memberId;
    public final StringPath memberName;
    public final StringPath memberRealName;
    public final NumberPath<BigDecimal> minLimit;
    public final NumberPath<BigDecimal> money;
    public final NumberPath<BigDecimal> number;
    public final StringPath orderSn;
    public final StringPath payMode;
    public final DateTimePath<Date> payTime;
    public final StringPath payUrl;
    public final NumberPath<Integer> platType;
    public final NumberPath<BigDecimal> price;
    public final StringPath referenceNumber;
    public final DateTimePath<Date> releaseTime;
    public final StringPath remark;
    public final EnumPath<OrderStatus> status;
    public final NumberPath<Integer> timeLimit;
    public final NumberPath<Long> version;
    public final QWechatPay wechatPay;
    
    public QOrder(final String variable) {
        this(Order.class, PathMetadataFactory.forVariable(variable), QOrder.INITS);
    }
    
    public QOrder(final Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QOrder.INITS));
    }
    
    public QOrder(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QOrder.INITS));
    }
    
    public QOrder(final PathMetadata metadata, final PathInits inits) {
        this(Order.class, metadata, inits);
    }
    
    public QOrder(final Class<? extends Order> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.advertiseId = (NumberPath<Long>)this.createNumber("advertiseId", (Class)Long.class);
        this.advertiseType = (EnumPath<AdvertiseType>)this.createEnum("advertiseType", (Class)AdvertiseType.class);
        this.cancelTime = (DateTimePath<Date>)this.createDateTime("cancelTime", (Class)Date.class);
        this.commission = (NumberPath<BigDecimal>)this.createNumber("commission", (Class)BigDecimal.class);
        this.country = this.createString("country");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.customerId = (NumberPath<Long>)this.createNumber("customerId", (Class)Long.class);
        this.customerName = this.createString("customerName");
        this.customerRealName = this.createString("customerRealName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.maxLimit = (NumberPath<BigDecimal>)this.createNumber("maxLimit", (Class)BigDecimal.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberName = this.createString("memberName");
        this.memberRealName = this.createString("memberRealName");
        this.minLimit = (NumberPath<BigDecimal>)this.createNumber("minLimit", (Class)BigDecimal.class);
        this.money = (NumberPath<BigDecimal>)this.createNumber("money", (Class)BigDecimal.class);
        this.number = (NumberPath<BigDecimal>)this.createNumber("number", (Class)BigDecimal.class);
        this.orderSn = this.createString("orderSn");
        this.payMode = this.createString("payMode");
        this.payTime = (DateTimePath<Date>)this.createDateTime("payTime", (Class)Date.class);
        this.payUrl = this.createString("payUrl");
        this.platType = (NumberPath<Integer>)this.createNumber("platType", (Class)Integer.class);
        this.price = (NumberPath<BigDecimal>)this.createNumber("price", (Class)BigDecimal.class);
        this.referenceNumber = this.createString("referenceNumber");
        this.releaseTime = (DateTimePath<Date>)this.createDateTime("releaseTime", (Class)Date.class);
        this.remark = this.createString("remark");
        this.status = (EnumPath<OrderStatus>)this.createEnum("status", (Class)OrderStatus.class);
        this.timeLimit = (NumberPath<Integer>)this.createNumber("timeLimit", (Class)Integer.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.alipay = (inits.isInitialized("alipay") ? new QAlipay(this.forProperty("alipay")) : null);
        this.bankInfo = (inits.isInitialized("bankInfo") ? new QBankInfo(this.forProperty("bankInfo")) : null);
        this.coin = (inits.isInitialized("coin") ? new QOtcCoin(this.forProperty("coin")) : null);
        this.wechatPay = (inits.isInitialized("wechatPay") ? new QWechatPay(this.forProperty("wechatPay")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        order = new QOrder("order1");
    }
}
