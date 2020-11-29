package cn.ztuo.bitrade.entity.unblock;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QUnblockLotteryConfig extends EntityPathBase<UnblockLotteryConfig>
{
    private static final long serialVersionUID = 956098740L;
    public static final QUnblockLotteryConfig unblockLotteryConfig;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<BigDecimal> buyPrice;
    public final StringPath coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Integer> dayWinnerNumLimit;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> insuranceRate;
    public final StringPath lotteryInstruction;
    public final StringPath lotteryLevel;
    public final NumberPath<BigDecimal> lotteryRate;
    public final StringPath lotteryRule;
    
    public QUnblockLotteryConfig(final String variable) {
        super((Class)UnblockLotteryConfig.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.buyPrice = (NumberPath<BigDecimal>)this.createNumber("buyPrice", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dayWinnerNumLimit = (NumberPath<Integer>)this.createNumber("dayWinnerNumLimit", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.insuranceRate = (NumberPath<BigDecimal>)this.createNumber("insuranceRate", (Class)BigDecimal.class);
        this.lotteryInstruction = this.createString("lotteryInstruction");
        this.lotteryLevel = this.createString("lotteryLevel");
        this.lotteryRate = (NumberPath<BigDecimal>)this.createNumber("lotteryRate", (Class)BigDecimal.class);
        this.lotteryRule = this.createString("lotteryRule");
    }
    
    public QUnblockLotteryConfig(final Path<? extends UnblockLotteryConfig> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.buyPrice = (NumberPath<BigDecimal>)this.createNumber("buyPrice", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dayWinnerNumLimit = (NumberPath<Integer>)this.createNumber("dayWinnerNumLimit", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.insuranceRate = (NumberPath<BigDecimal>)this.createNumber("insuranceRate", (Class)BigDecimal.class);
        this.lotteryInstruction = this.createString("lotteryInstruction");
        this.lotteryLevel = this.createString("lotteryLevel");
        this.lotteryRate = (NumberPath<BigDecimal>)this.createNumber("lotteryRate", (Class)BigDecimal.class);
        this.lotteryRule = this.createString("lotteryRule");
    }
    
    public QUnblockLotteryConfig(final PathMetadata metadata) {
        super((Class)UnblockLotteryConfig.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.buyPrice = (NumberPath<BigDecimal>)this.createNumber("buyPrice", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dayWinnerNumLimit = (NumberPath<Integer>)this.createNumber("dayWinnerNumLimit", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.insuranceRate = (NumberPath<BigDecimal>)this.createNumber("insuranceRate", (Class)BigDecimal.class);
        this.lotteryInstruction = this.createString("lotteryInstruction");
        this.lotteryLevel = this.createString("lotteryLevel");
        this.lotteryRate = (NumberPath<BigDecimal>)this.createNumber("lotteryRate", (Class)BigDecimal.class);
        this.lotteryRule = this.createString("lotteryRule");
    }
    
    static {
        unblockLotteryConfig = new QUnblockLotteryConfig("unblockLotteryConfig");
    }
}
