package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractMember extends EntityPathBase<ContractMember>
{
    private static final long serialVersionUID = -1970773649L;
    private static final PathInits INITS;
    public static final QContractMember contractMember;
    public final QContractNode contractNode;
    public final NumberPath<BigDecimal> contractRebateRate;
    public final ListPath<ContractWallet, QContractWallet> contractWalletList;
    public final DateTimePath<Date> createTime;
    public final EnumPath<BooleanEnum> ifProxy;
    public final StringPath invitationCode;
    public final QMember member;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> proxyId;
    public final NumberPath<Long> sequence;
    public final NumberPath<BigDecimal> spotRebateRate;
    public final NumberPath<Integer> status;
    public final NumberPath<Long> version;
    
    public QContractMember(final String variable) {
        this(ContractMember.class, PathMetadataFactory.forVariable(variable), QContractMember.INITS);
    }
    
    public QContractMember(final Path<? extends ContractMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractMember.INITS));
    }
    
    public QContractMember(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractMember.INITS));
    }
    
    public QContractMember(final PathMetadata metadata, final PathInits inits) {
        this(ContractMember.class, metadata, inits);
    }
    
    public QContractMember(final Class<? extends ContractMember> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.contractRebateRate = (NumberPath<BigDecimal>)this.createNumber("contractRebateRate", (Class)BigDecimal.class);
        this.contractWalletList = (ListPath<ContractWallet, QContractWallet>)this.createList("contractWalletList", (Class)ContractWallet.class, (Class)QContractWallet.class, PathInits.DIRECT2);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.ifProxy = (EnumPath<BooleanEnum>)this.createEnum("ifProxy", (Class)BooleanEnum.class);
        this.invitationCode = this.createString("invitationCode");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.proxyId = (NumberPath<Long>)this.createNumber("proxyId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.spotRebateRate = (NumberPath<BigDecimal>)this.createNumber("spotRebateRate", (Class)BigDecimal.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.version = (NumberPath<Long>)this.createNumber("version", (Class)Long.class);
        this.contractNode = (inits.isInitialized("contractNode") ? new QContractNode(this.forProperty("contractNode")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractMember = new QContractMember("contractMember");
    }
}
