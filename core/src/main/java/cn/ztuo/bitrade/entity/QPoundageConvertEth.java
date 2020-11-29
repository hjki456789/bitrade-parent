package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QPoundageConvertEth extends EntityPathBase<PoundageConvertEth>
{
    private static final long serialVersionUID = 1139653596L;
    public static final QPoundageConvertEth poundageConvertEth;
    public final StringPath coinId;
    public final StringPath direction;
    public final StringPath ethUsdtRate;
    public final StringPath exchangeOrderId;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> mineAmount;
    public final NumberPath<BigDecimal> poundageAmount;
    public final NumberPath<BigDecimal> poundageAmountEth;
    public final StringPath symbol;
    public final DateTimePath<Date> transactionTime;
    public final StringPath type;
    public final StringPath usdtRate;
    
    public QPoundageConvertEth(final String variable) {
        super((Class)PoundageConvertEth.class, PathMetadataFactory.forVariable(variable));
        this.coinId = this.createString("coinId");
        this.direction = this.createString("direction");
        this.ethUsdtRate = this.createString("ethUsdtRate");
        this.exchangeOrderId = this.createString("exchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.mineAmount = (NumberPath<BigDecimal>)this.createNumber("mineAmount", (Class)BigDecimal.class);
        this.poundageAmount = (NumberPath<BigDecimal>)this.createNumber("poundageAmount", (Class)BigDecimal.class);
        this.poundageAmountEth = (NumberPath<BigDecimal>)this.createNumber("poundageAmountEth", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
        this.transactionTime = (DateTimePath<Date>)this.createDateTime("transactionTime", (Class)Date.class);
        this.type = this.createString("type");
        this.usdtRate = this.createString("usdtRate");
    }
    
    public QPoundageConvertEth(final Path<? extends PoundageConvertEth> path) {
        super(path.getType(), path.getMetadata());
        this.coinId = this.createString("coinId");
        this.direction = this.createString("direction");
        this.ethUsdtRate = this.createString("ethUsdtRate");
        this.exchangeOrderId = this.createString("exchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.mineAmount = (NumberPath<BigDecimal>)this.createNumber("mineAmount", (Class)BigDecimal.class);
        this.poundageAmount = (NumberPath<BigDecimal>)this.createNumber("poundageAmount", (Class)BigDecimal.class);
        this.poundageAmountEth = (NumberPath<BigDecimal>)this.createNumber("poundageAmountEth", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
        this.transactionTime = (DateTimePath<Date>)this.createDateTime("transactionTime", (Class)Date.class);
        this.type = this.createString("type");
        this.usdtRate = this.createString("usdtRate");
    }
    
    public QPoundageConvertEth(final PathMetadata metadata) {
        super((Class)PoundageConvertEth.class, metadata);
        this.coinId = this.createString("coinId");
        this.direction = this.createString("direction");
        this.ethUsdtRate = this.createString("ethUsdtRate");
        this.exchangeOrderId = this.createString("exchangeOrderId");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.mineAmount = (NumberPath<BigDecimal>)this.createNumber("mineAmount", (Class)BigDecimal.class);
        this.poundageAmount = (NumberPath<BigDecimal>)this.createNumber("poundageAmount", (Class)BigDecimal.class);
        this.poundageAmountEth = (NumberPath<BigDecimal>)this.createNumber("poundageAmountEth", (Class)BigDecimal.class);
        this.symbol = this.createString("symbol");
        this.transactionTime = (DateTimePath<Date>)this.createDateTime("transactionTime", (Class)Date.class);
        this.type = this.createString("type");
        this.usdtRate = this.createString("usdtRate");
    }
    
    static {
        poundageConvertEth = new QPoundageConvertEth("poundageConvertEth");
    }
}
