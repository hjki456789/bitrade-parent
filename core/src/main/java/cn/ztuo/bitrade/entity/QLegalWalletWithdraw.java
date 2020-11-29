package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QLegalWalletWithdraw extends EntityPathBase<LegalWalletWithdraw>
{
    private static final long serialVersionUID = 473284601L;
    private static final PathInits INITS;
    public static final QLegalWalletWithdraw legalWalletWithdraw;
    public final StringPath account;
    public final QAdmin admin;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final DateTimePath<Date> dealTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final StringPath paymentInstrument;
    public final EnumPath<PayMode> payMode;
    public final StringPath remark;
    public final DateTimePath<Date> remitTime;
    public final EnumPath<WithdrawStatus> status;
    
    public QLegalWalletWithdraw(final String variable) {
        this(LegalWalletWithdraw.class, PathMetadataFactory.forVariable(variable), QLegalWalletWithdraw.INITS);
    }
    
    public QLegalWalletWithdraw(final Path<? extends LegalWalletWithdraw> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QLegalWalletWithdraw.INITS));
    }
    
    public QLegalWalletWithdraw(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QLegalWalletWithdraw.INITS));
    }
    
    public QLegalWalletWithdraw(final PathMetadata metadata, final PathInits inits) {
        this(LegalWalletWithdraw.class, metadata, inits);
    }
    
    public QLegalWalletWithdraw(final Class<? extends LegalWalletWithdraw> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.account = this.createString("account");
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dealTime = (DateTimePath<Date>)this.createDateTime("dealTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.paymentInstrument = this.createString("paymentInstrument");
        this.payMode = (EnumPath<PayMode>)this.createEnum("payMode", (Class)PayMode.class);
        this.remark = this.createString("remark");
        this.remitTime = (DateTimePath<Date>)this.createDateTime("remitTime", (Class)Date.class);
        this.status = (EnumPath<WithdrawStatus>)this.createEnum("status", (Class)WithdrawStatus.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        legalWalletWithdraw = new QLegalWalletWithdraw("legalWalletWithdraw");
    }
}
