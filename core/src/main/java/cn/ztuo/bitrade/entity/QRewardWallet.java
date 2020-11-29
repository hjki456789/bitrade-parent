package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QRewardWallet extends EntityPathBase<RewardWallet>
{
    private static final long serialVersionUID = 267389931L;
    public static final QRewardWallet rewardWallet;
    public final NumberPath<BigDecimal> balance;
    public final StringPath coinUnit;
    public final NumberPath<BigDecimal> frozenBalance;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isLock;
    public final NumberPath<Long> memberId;
    public final NumberPath<Integer> version;
    
    public QRewardWallet(final String variable) {
        super((Class)RewardWallet.class, PathMetadataFactory.forVariable(variable));
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinUnit = this.createString("coinUnit");
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (EnumPath<BooleanEnum>)this.createEnum("isLock", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
    }
    
    public QRewardWallet(final Path<? extends RewardWallet> path) {
        super(path.getType(), path.getMetadata());
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinUnit = this.createString("coinUnit");
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (EnumPath<BooleanEnum>)this.createEnum("isLock", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
    }
    
    public QRewardWallet(final PathMetadata metadata) {
        super((Class)RewardWallet.class, metadata);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinUnit = this.createString("coinUnit");
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (EnumPath<BooleanEnum>)this.createEnum("isLock", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
    }
    
    static {
        rewardWallet = new QRewardWallet("rewardWallet");
    }
}
