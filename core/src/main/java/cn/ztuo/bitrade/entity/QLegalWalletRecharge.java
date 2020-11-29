package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QLegalWalletRecharge extends EntityPathBase<LegalWalletRecharge>
{
    private static final long serialVersionUID = 607335318L;
    private static final PathInits INITS;
    public static final QLegalWalletRecharge legalWalletRecharge;
    public final QAdmin admin;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> creationTime;
    public final DateTimePath<Date> dealTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final StringPath paymentInstrument;
    public final EnumPath<PayMode> payMode;
    public final StringPath remark;
    public final EnumPath<LegalWalletState> state;
    public final DateTimePath<Date> updateTime;
    
    public QLegalWalletRecharge(final String variable) {
        this(LegalWalletRecharge.class, PathMetadataFactory.forVariable(variable), QLegalWalletRecharge.INITS);
    }
    
    public QLegalWalletRecharge(final Path<? extends LegalWalletRecharge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QLegalWalletRecharge.INITS));
    }
    
    public QLegalWalletRecharge(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QLegalWalletRecharge.INITS));
    }
    
    public QLegalWalletRecharge(final PathMetadata metadata, final PathInits inits) {
        this(LegalWalletRecharge.class, metadata, inits);
    }
    
    public QLegalWalletRecharge(final Class<? extends LegalWalletRecharge> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.creationTime = (DateTimePath<Date>)this.createDateTime("creationTime", (Class)Date.class);
        this.dealTime = (DateTimePath<Date>)this.createDateTime("dealTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.paymentInstrument = this.createString("paymentInstrument");
        this.payMode = (EnumPath<PayMode>)this.createEnum("payMode", (Class)PayMode.class);
        this.remark = this.createString("remark");
        this.state = (EnumPath<LegalWalletState>)this.createEnum("state", (Class)LegalWalletState.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        legalWalletRecharge = new QLegalWalletRecharge("legalWalletRecharge");
    }
}
