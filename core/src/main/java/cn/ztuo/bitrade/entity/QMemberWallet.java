package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QMemberWallet extends EntityPathBase<MemberWallet>
{
    private static final long serialVersionUID = -1861891306L;
    private static final PathInits INITS;
    public static final QMemberWallet memberWallet;
    public final StringPath address;
    public final NumberPath<BigDecimal> balance;
    public final NumberPath<BigDecimal> blockBalance;
    public final QCoin coin;
    public final NumberPath<BigDecimal> frozenBalance;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isLock;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> releaseBalance;
    public final NumberPath<Integer> version;
    
    public QMemberWallet(final String variable) {
        this(MemberWallet.class, PathMetadataFactory.forVariable(variable), QMemberWallet.INITS);
    }
    
    public QMemberWallet(final Path<? extends MemberWallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMemberWallet.INITS));
    }
    
    public QMemberWallet(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMemberWallet.INITS));
    }
    
    public QMemberWallet(final PathMetadata metadata, final PathInits inits) {
        this(MemberWallet.class, metadata, inits);
    }
    
    public QMemberWallet(final Class<? extends MemberWallet> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.blockBalance = (NumberPath<BigDecimal>)this.createNumber("blockBalance", (Class)BigDecimal.class);
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (EnumPath<BooleanEnum>)this.createEnum("isLock", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.releaseBalance = (NumberPath<BigDecimal>)this.createNumber("releaseBalance", (Class)BigDecimal.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        memberWallet = new QMemberWallet("memberWallet");
    }
}
