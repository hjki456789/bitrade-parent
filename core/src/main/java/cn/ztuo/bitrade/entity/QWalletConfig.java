package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QWalletConfig extends EntityPathBase<WalletConfig>
{
    private static final long serialVersionUID = -1455947650L;
    public static final QWalletConfig walletConfig;
    public final StringPath address;
    public final NumberPath<BigDecimal> balance;
    public final StringPath coinName;
    public final NumberPath<BigDecimal> collectRate;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> type;
    
    public QWalletConfig(final String variable) {
        super((Class)WalletConfig.class, PathMetadataFactory.forVariable(variable));
        this.address = this.createString("address");
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.collectRate = (NumberPath<BigDecimal>)this.createNumber("collectRate", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    public QWalletConfig(final Path<? extends WalletConfig> path) {
        super(path.getType(), path.getMetadata());
        this.address = this.createString("address");
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.collectRate = (NumberPath<BigDecimal>)this.createNumber("collectRate", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    public QWalletConfig(final PathMetadata metadata) {
        super((Class)WalletConfig.class, metadata);
        this.address = this.createString("address");
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.collectRate = (NumberPath<BigDecimal>)this.createNumber("collectRate", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
    }
    
    static {
        walletConfig = new QWalletConfig("walletConfig");
    }
}
