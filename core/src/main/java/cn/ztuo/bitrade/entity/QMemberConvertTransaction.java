package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QMemberConvertTransaction extends EntityPathBase<MemberConvertTransaction>
{
    private static final long serialVersionUID = 1829606088L;
    public static final QMemberConvertTransaction memberConvertTransaction;
    public final NumberPath<Double> amount;
    public final StringPath baseCoin;
    public final NumberPath<Double> convertAmount;
    public final StringPath convertCoin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Double> feeAmount;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Double> rate;
    public final NumberPath<Long> sequence;
    
    public QMemberConvertTransaction(final String variable) {
        super((Class)MemberConvertTransaction.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<Double>)this.createNumber("amount", (Class)Double.class);
        this.baseCoin = this.createString("baseCoin");
        this.convertAmount = (NumberPath<Double>)this.createNumber("convertAmount", (Class)Double.class);
        this.convertCoin = this.createString("convertCoin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.feeAmount = (NumberPath<Double>)this.createNumber("feeAmount", (Class)Double.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.rate = (NumberPath<Double>)this.createNumber("rate", (Class)Double.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QMemberConvertTransaction(final Path<? extends MemberConvertTransaction> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<Double>)this.createNumber("amount", (Class)Double.class);
        this.baseCoin = this.createString("baseCoin");
        this.convertAmount = (NumberPath<Double>)this.createNumber("convertAmount", (Class)Double.class);
        this.convertCoin = this.createString("convertCoin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.feeAmount = (NumberPath<Double>)this.createNumber("feeAmount", (Class)Double.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.rate = (NumberPath<Double>)this.createNumber("rate", (Class)Double.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QMemberConvertTransaction(final PathMetadata metadata) {
        super((Class)MemberConvertTransaction.class, metadata);
        this.amount = (NumberPath<Double>)this.createNumber("amount", (Class)Double.class);
        this.baseCoin = this.createString("baseCoin");
        this.convertAmount = (NumberPath<Double>)this.createNumber("convertAmount", (Class)Double.class);
        this.convertCoin = this.createString("convertCoin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.feeAmount = (NumberPath<Double>)this.createNumber("feeAmount", (Class)Double.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.rate = (NumberPath<Double>)this.createNumber("rate", (Class)Double.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    static {
        memberConvertTransaction = new QMemberConvertTransaction("memberConvertTransaction");
    }
}
