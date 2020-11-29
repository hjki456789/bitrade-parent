package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QMemberDeposit extends EntityPathBase<MemberDeposit>
{
    private static final long serialVersionUID = -1448443103L;
    public static final QMemberDeposit memberDeposit;
    public final StringPath address;
    public final NumberPath<BigDecimal> amount;
    public final NumberPath<Integer> collectType;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath txid;
    public final StringPath unit;
    
    public QMemberDeposit(final String variable) {
        super((Class)MemberDeposit.class, PathMetadataFactory.forVariable(variable));
        this.address = this.createString("address");
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.collectType = (NumberPath<Integer>)this.createNumber("collectType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.txid = this.createString("txid");
        this.unit = this.createString("unit");
    }
    
    public QMemberDeposit(final Path<? extends MemberDeposit> path) {
        super(path.getType(), path.getMetadata());
        this.address = this.createString("address");
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.collectType = (NumberPath<Integer>)this.createNumber("collectType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.txid = this.createString("txid");
        this.unit = this.createString("unit");
    }
    
    public QMemberDeposit(final PathMetadata metadata) {
        super((Class)MemberDeposit.class, metadata);
        this.address = this.createString("address");
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.collectType = (NumberPath<Integer>)this.createNumber("collectType", (Class)Integer.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.txid = this.createString("txid");
        this.unit = this.createString("unit");
    }
    
    static {
        memberDeposit = new QMemberDeposit("memberDeposit");
    }
}
