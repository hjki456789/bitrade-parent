package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QOtcWallet extends EntityPathBase<OtcWallet>
{
    private static final long serialVersionUID = 1578764212L;
    private static final PathInits INITS;
    public static final QOtcWallet otcWallet;
    public final NumberPath<BigDecimal> balance;
    public final QCoin coin;
    public final NumberPath<BigDecimal> frozenBalance;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> isLock;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> releaseBalance;
    public final NumberPath<Integer> version;
    
    public QOtcWallet(final String variable) {
        this(OtcWallet.class, PathMetadataFactory.forVariable(variable), QOtcWallet.INITS);
    }
    
    public QOtcWallet(final Path<? extends OtcWallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QOtcWallet.INITS));
    }
    
    public QOtcWallet(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QOtcWallet.INITS));
    }
    
    public QOtcWallet(final PathMetadata metadata, final PathInits inits) {
        this(OtcWallet.class, metadata, inits);
    }
    
    public QOtcWallet(final Class<? extends OtcWallet> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (NumberPath<Integer>)this.createNumber("isLock", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.releaseBalance = (NumberPath<BigDecimal>)this.createNumber("releaseBalance", (Class)BigDecimal.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        otcWallet = new QOtcWallet("otcWallet");
    }
}
