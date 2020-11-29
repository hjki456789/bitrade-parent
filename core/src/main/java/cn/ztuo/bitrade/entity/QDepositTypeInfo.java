package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QDepositTypeInfo extends EntityPathBase<DepositTypeInfo>
{
    private static final long serialVersionUID = 1102359811L;
    public static final QDepositTypeInfo depositTypeInfo;
    public final NumberPath<BigDecimal> breakRate;
    public final NumberPath<Integer> cycle;
    public final NumberPath<BigDecimal> dayProfitRate;
    public final NumberPath<Long> id;
    public final StringPath investCoin;
    public final NumberPath<BigDecimal> investmentMax;
    public final NumberPath<BigDecimal> investmentMin;
    public final StringPath name;
    public final StringPath profitCoin;
    public final StringPath remark;
    public final NumberPath<Integer> status;
    
    public QDepositTypeInfo(final String variable) {
        super((Class)DepositTypeInfo.class, PathMetadataFactory.forVariable(variable));
        this.breakRate = (NumberPath<BigDecimal>)this.createNumber("breakRate", (Class)BigDecimal.class);
        this.cycle = (NumberPath<Integer>)this.createNumber("cycle", (Class)Integer.class);
        this.dayProfitRate = (NumberPath<BigDecimal>)this.createNumber("dayProfitRate", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.investCoin = this.createString("investCoin");
        this.investmentMax = (NumberPath<BigDecimal>)this.createNumber("investmentMax", (Class)BigDecimal.class);
        this.investmentMin = (NumberPath<BigDecimal>)this.createNumber("investmentMin", (Class)BigDecimal.class);
        this.name = this.createString("name");
        this.profitCoin = this.createString("profitCoin");
        this.remark = this.createString("remark");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    public QDepositTypeInfo(final Path<? extends DepositTypeInfo> path) {
        super(path.getType(), path.getMetadata());
        this.breakRate = (NumberPath<BigDecimal>)this.createNumber("breakRate", (Class)BigDecimal.class);
        this.cycle = (NumberPath<Integer>)this.createNumber("cycle", (Class)Integer.class);
        this.dayProfitRate = (NumberPath<BigDecimal>)this.createNumber("dayProfitRate", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.investCoin = this.createString("investCoin");
        this.investmentMax = (NumberPath<BigDecimal>)this.createNumber("investmentMax", (Class)BigDecimal.class);
        this.investmentMin = (NumberPath<BigDecimal>)this.createNumber("investmentMin", (Class)BigDecimal.class);
        this.name = this.createString("name");
        this.profitCoin = this.createString("profitCoin");
        this.remark = this.createString("remark");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    public QDepositTypeInfo(final PathMetadata metadata) {
        super((Class)DepositTypeInfo.class, metadata);
        this.breakRate = (NumberPath<BigDecimal>)this.createNumber("breakRate", (Class)BigDecimal.class);
        this.cycle = (NumberPath<Integer>)this.createNumber("cycle", (Class)Integer.class);
        this.dayProfitRate = (NumberPath<BigDecimal>)this.createNumber("dayProfitRate", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.investCoin = this.createString("investCoin");
        this.investmentMax = (NumberPath<BigDecimal>)this.createNumber("investmentMax", (Class)BigDecimal.class);
        this.investmentMin = (NumberPath<BigDecimal>)this.createNumber("investmentMin", (Class)BigDecimal.class);
        this.name = this.createString("name");
        this.profitCoin = this.createString("profitCoin");
        this.remark = this.createString("remark");
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    static {
        depositTypeInfo = new QDepositTypeInfo("depositTypeInfo");
    }
}
