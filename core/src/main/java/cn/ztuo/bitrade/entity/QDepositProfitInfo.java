package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QDepositProfitInfo extends EntityPathBase<DepositProfitInfo>
{
    private static final long serialVersionUID = -870304691L;
    public static final QDepositProfitInfo depositProfitInfo;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coinName;
    public final NumberPath<BigDecimal> depositAmount;
    public final NumberPath<Long> depositId;
    public final NumberPath<BigDecimal> fundAmount;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> sequence;
    public final DateTimePath<Date> time;
    
    public QDepositProfitInfo(final String variable) {
        super((Class)DepositProfitInfo.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositId = (NumberPath<Long>)this.createNumber("depositId", (Class)Long.class);
        this.fundAmount = (NumberPath<BigDecimal>)this.createNumber("fundAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    public QDepositProfitInfo(final Path<? extends DepositProfitInfo> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositId = (NumberPath<Long>)this.createNumber("depositId", (Class)Long.class);
        this.fundAmount = (NumberPath<BigDecimal>)this.createNumber("fundAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    public QDepositProfitInfo(final PathMetadata metadata) {
        super((Class)DepositProfitInfo.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositId = (NumberPath<Long>)this.createNumber("depositId", (Class)Long.class);
        this.fundAmount = (NumberPath<BigDecimal>)this.createNumber("fundAmount", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    static {
        depositProfitInfo = new QDepositProfitInfo("depositProfitInfo");
    }
}
