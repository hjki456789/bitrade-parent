package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QRobotTransaction extends EntityPathBase<RobotTransaction>
{
    private static final long serialVersionUID = 96828535L;
    public static final QRobotTransaction robotTransaction;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> createTime;
    public final NumberPath<BigDecimal> fee;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath symbol;
    public final EnumPath<TransactionType> type;
    
    public QRobotTransaction(final String variable) {
        super((Class)RobotTransaction.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.type = (EnumPath<TransactionType>)this.createEnum("type", (Class)TransactionType.class);
    }
    
    public QRobotTransaction(final Path<? extends RobotTransaction> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.type = (EnumPath<TransactionType>)this.createEnum("type", (Class)TransactionType.class);
    }
    
    public QRobotTransaction(final PathMetadata metadata) {
        super((Class)RobotTransaction.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.symbol = this.createString("symbol");
        this.type = (EnumPath<TransactionType>)this.createEnum("type", (Class)TransactionType.class);
    }
    
    static {
        robotTransaction = new QRobotTransaction("robotTransaction");
    }
}
