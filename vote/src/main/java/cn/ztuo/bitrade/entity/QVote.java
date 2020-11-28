package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QVote extends EntityPathBase<Vote>
{
    private static final long serialVersionUID = 1697300941L;
    public static final QVote vote;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final ListPath<PreCoin, QPreCoin> preCoins;
    public final EnumPath<BooleanEnum> status;
    public final NumberPath<Integer> voteLimit;

    public QVote(final String variable) {
        super((Class)Vote.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.preCoins = (ListPath<PreCoin, QPreCoin>)this.createList("preCoins", (Class)PreCoin.class, (Class)QPreCoin.class, PathInits.DIRECT2);
        this.status = (EnumPath<BooleanEnum>)this.createEnum("status", (Class)BooleanEnum.class);
        this.voteLimit = (NumberPath<Integer>)this.createNumber("voteLimit", (Class)Integer.class);
    }

    public QVote(final Path<? extends Vote> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.preCoins = (ListPath<PreCoin, QPreCoin>)this.createList("preCoins", (Class)PreCoin.class, (Class)QPreCoin.class, PathInits.DIRECT2);
        this.status = (EnumPath<BooleanEnum>)this.createEnum("status", (Class)BooleanEnum.class);
        this.voteLimit = (NumberPath<Integer>)this.createNumber("voteLimit", (Class)Integer.class);
    }

    public QVote(final PathMetadata metadata) {
        super((Class)Vote.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.preCoins = (ListPath<PreCoin, QPreCoin>)this.createList("preCoins", (Class)PreCoin.class, (Class)QPreCoin.class, PathInits.DIRECT2);
        this.status = (EnumPath<BooleanEnum>)this.createEnum("status", (Class)BooleanEnum.class);
        this.voteLimit = (NumberPath<Integer>)this.createNumber("voteLimit", (Class)Integer.class);
    }

    static {
        vote = new QVote("vote");
    }
}
