package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import com.querydsl.core.types.*;

public class QVirtualRechargeFrozenFlow extends EntityPathBase<VirtualRechargeFrozenFlow>
{
    private static final long serialVersionUID = -2127115939L;
    public static final QVirtualRechargeFrozenFlow virtualRechargeFrozenFlow;
    public final NumberPath<BigDecimal> afterBalance;
    public final NumberPath<BigDecimal> balance;
    public final StringPath coinId;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final EnumPath<VirtualRechargeFrozenFlowOperationType> operationType;
    public final StringPath remark;
    public final NumberPath<Long> sequence;
    
    public QVirtualRechargeFrozenFlow(final String variable) {
        super((Class)VirtualRechargeFrozenFlow.class, PathMetadataFactory.forVariable(variable));
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.operationType = (EnumPath<VirtualRechargeFrozenFlowOperationType>)this.createEnum("operationType", (Class)VirtualRechargeFrozenFlowOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QVirtualRechargeFrozenFlow(final Path<? extends VirtualRechargeFrozenFlow> path) {
        super(path.getType(), path.getMetadata());
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.operationType = (EnumPath<VirtualRechargeFrozenFlowOperationType>)this.createEnum("operationType", (Class)VirtualRechargeFrozenFlowOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QVirtualRechargeFrozenFlow(final PathMetadata metadata) {
        super((Class)VirtualRechargeFrozenFlow.class, metadata);
        this.afterBalance = (NumberPath<BigDecimal>)this.createNumber("afterBalance", (Class)BigDecimal.class);
        this.balance = (NumberPath<BigDecimal>)this.createNumber("balance", (Class)BigDecimal.class);
        this.coinId = this.createString("coinId");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.operationType = (EnumPath<VirtualRechargeFrozenFlowOperationType>)this.createEnum("operationType", (Class)VirtualRechargeFrozenFlowOperationType.class);
        this.remark = this.createString("remark");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    static {
        virtualRechargeFrozenFlow = new QVirtualRechargeFrozenFlow("virtualRechargeFrozenFlow");
    }
}
