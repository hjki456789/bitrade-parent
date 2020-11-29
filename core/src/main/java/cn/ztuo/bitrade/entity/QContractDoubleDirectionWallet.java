package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QContractDoubleDirectionWallet extends EntityPathBase<ContractDoubleDirectionWallet>
{
    private static final long serialVersionUID = 659714258L;
    private static final PathInits INITS;
    public static final QContractDoubleDirectionWallet contractDoubleDirectionWallet;
    public final NumberPath<BigDecimal> balance;
    public final QCoin coin;
    public final NumberPath<BigDecimal> frozenBalance;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isLock;
    public final NumberPath<Long> memberId;
    public final NumberPath<Integer> version;
    
    public QContractDoubleDirectionWallet(final String variable) {
        this(ContractDoubleDirectionWallet.class, PathMetadataFactory.forVariable(variable), QContractDoubleDirectionWallet.INITS);
    }
    
    public QContractDoubleDirectionWallet(final Path<? extends ContractDoubleDirectionWallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractDoubleDirectionWallet.INITS));
    }
    
    public QContractDoubleDirectionWallet(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractDoubleDirectionWallet.INITS));
    }
    
    public QContractDoubleDirectionWallet(final PathMetadata metadata, final PathInits inits) {
        this(ContractDoubleDirectionWallet.class, metadata, inits);
    }
    
    public QContractDoubleDirectionWallet(final Class<? extends ContractDoubleDirectionWallet> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.frozenBalance = (NumberPath<BigDecimal>)this.createNumber("frozenBalance", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isLock = (EnumPath<BooleanEnum>)this.createEnum("isLock", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.version = (NumberPath<Integer>)this.createNumber("version", (Class)Integer.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractDoubleDirectionWallet = new QContractDoubleDirectionWallet("contractDoubleDirectionWallet");
    }
}
