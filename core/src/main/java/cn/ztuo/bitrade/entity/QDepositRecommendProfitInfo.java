package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QDepositRecommendProfitInfo extends EntityPathBase<DepositRecommendProfitInfo>
{
    private static final long serialVersionUID = 1939184147L;
    public static final QDepositRecommendProfitInfo depositRecommendProfitInfo;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coinName;
    public final NumberPath<BigDecimal> depositAmount;
    public final NumberPath<Long> depositId;
    public final NumberPath<Long> fromMemberId;
    public final NumberPath<BigDecimal> fundAmount;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> sequence;
    public final DateTimePath<Date> time;
    
    public QDepositRecommendProfitInfo(final String variable) {
        super((Class)DepositRecommendProfitInfo.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositId = (NumberPath<Long>)this.createNumber("depositId", (Class)Long.class);
        this.fromMemberId = (NumberPath<Long>)this.createNumber("fromMemberId", (Class)Long.class);
        this.fundAmount = (NumberPath<BigDecimal>)this.createNumber("fundAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    public QDepositRecommendProfitInfo(final Path<? extends DepositRecommendProfitInfo> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositId = (NumberPath<Long>)this.createNumber("depositId", (Class)Long.class);
        this.fromMemberId = (NumberPath<Long>)this.createNumber("fromMemberId", (Class)Long.class);
        this.fundAmount = (NumberPath<BigDecimal>)this.createNumber("fundAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    public QDepositRecommendProfitInfo(final PathMetadata metadata) {
        super((Class)DepositRecommendProfitInfo.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositId = (NumberPath<Long>)this.createNumber("depositId", (Class)Long.class);
        this.fromMemberId = (NumberPath<Long>)this.createNumber("fromMemberId", (Class)Long.class);
        this.fundAmount = (NumberPath<BigDecimal>)this.createNumber("fundAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    static {
        depositRecommendProfitInfo = new QDepositRecommendProfitInfo("depositRecommendProfitInfo");
    }
}
