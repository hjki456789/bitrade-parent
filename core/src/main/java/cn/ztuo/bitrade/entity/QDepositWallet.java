package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QDepositWallet extends EntityPathBase<DepositWallet>
{
    private static final long serialVersionUID = -1262614348L;
    private static final PathInits INITS;
    public static final QDepositWallet depositWallet;
    public final NumberPath<BigDecimal> balance;
    public final QCoin coin;
    public final NumberPath<BigDecimal> frozenBalance;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isLock;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> totalInBalance;
    public final NumberPath<Integer> version;
    
    public QDepositWallet(final String variable) {
        this(DepositWallet.class, PathMetadataFactory.forVariable(variable), QDepositWallet.INITS);
    }
    
    public QDepositWallet(final Path<? extends DepositWallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QDepositWallet.INITS));
    }
    
    public QDepositWallet(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QDepositWallet.INITS));
    }
    
    public QDepositWallet(final PathMetadata metadata, final PathInits inits) {
        this(DepositWallet.class, metadata, inits);
    }
    
    public QDepositWallet(final Class<? extends DepositWallet> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (EnumPath<BooleanEnum>)this.createEnum("isLock", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.totalInBalance = (NumberPath<BigDecimal>)this.createNumber("totalInBalance", (Class)BigDecimal.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        depositWallet = new QDepositWallet("depositWallet");
    }
}
