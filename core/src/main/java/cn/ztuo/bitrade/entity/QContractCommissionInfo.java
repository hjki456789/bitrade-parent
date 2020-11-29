package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QContractCommissionInfo extends EntityPathBase<ContractCommissionInfo>
{
    private static final long serialVersionUID = 606476814L;
    private static final PathInits INITS;
    public static final QContractCommissionInfo contractCommissionInfo;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coinName;
    public final StringPath createTime;
    public final StringPath id;
    public final QMember member;
    public final EnumPath<IfNodeType> memberStatus;
    public final NumberPath<Long> proxyId;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    public final StringPath sysUser;
    public final NumberPath<Integer> type;
    public final StringPath updateTime;
    
    public QContractCommissionInfo(final String variable) {
        this(ContractCommissionInfo.class, PathMetadataFactory.forVariable(variable), QContractCommissionInfo.INITS);
    }
    
    public QContractCommissionInfo(final Path<? extends ContractCommissionInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractCommissionInfo.INITS));
    }
    
    public QContractCommissionInfo(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractCommissionInfo.INITS));
    }
    
    public QContractCommissionInfo(final PathMetadata metadata, final PathInits inits) {
        this(ContractCommissionInfo.class, metadata, inits);
    }
    
    public QContractCommissionInfo(final Class<? extends ContractCommissionInfo> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coinName = this.createString("coinName");
        this.createTime = this.createString("createTime");
        this.id = this.createString("id");
        this.memberStatus = (EnumPath<IfNodeType>)this.createEnum("memberStatus", (Class)IfNodeType.class);
        this.proxyId = (NumberPath<Long>)this.createNumber("proxyId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.sysUser = this.createString("sysUser");
        this.type = (NumberPath<Integer>)this.createNumber("type", (Class)Integer.class);
        this.updateTime = this.createString("updateTime");
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractCommissionInfo = new QContractCommissionInfo("contractCommissionInfo");
    }
}
