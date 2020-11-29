package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QContractWallet extends EntityPathBase<ContractWallet>
{
    private static final long serialVersionUID = -1688196402L;
    private static final PathInits INITS;
    public static final QContractWallet contractWallet;
    public final NumberPath<BigDecimal> balance;
    public final QCoin coin;
    public final NumberPath<BigDecimal> frozenBalance;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> is_lock;
    public final QMember member;
    public final NumberPath<Integer> status;
    public final NumberPath<Long> version;
    public final NumberPath<BigDecimal> virtualRechargeFrozenBalance;
    
    public QContractWallet(final String variable) {
        this(ContractWallet.class, PathMetadataFactory.forVariable(variable), QContractWallet.INITS);
    }
    
    public QContractWallet(final Path<? extends ContractWallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractWallet.INITS));
    }
    
    public QContractWallet(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractWallet.INITS));
    }
    
    public QContractWallet(final PathMetadata metadata, final PathInits inits) {
        this(ContractWallet.class, metadata, inits);
    }
    
    public QContractWallet(final Class<? extends ContractWallet> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.is_lock = (NumberPath<Integer>)this.createNumber("is_lock", (Class)Integer.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.virtualRechargeFrozenBalance = (NumberPath<BigDecimal>)this.createNumber("virtualRechargeFrozenBalance", (Class)BigDecimal.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractWallet = new QContractWallet("contractWallet");
    }
}
