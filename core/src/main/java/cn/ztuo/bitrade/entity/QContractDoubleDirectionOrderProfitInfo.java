package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QContractDoubleDirectionOrderProfitInfo extends EntityPathBase<ContractDoubleDirectionOrderProfitInfo>
{
    private static final long serialVersionUID = 359183943L;
    public static final QContractDoubleDirectionOrderProfitInfo contractDoubleDirectionOrderProfitInfo;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Integer> days;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> orderId;
    public final NumberPath<BigDecimal> profit;
    public final NumberPath<Integer> remainDays;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    
    public QContractDoubleDirectionOrderProfitInfo(final String variable) {
        super((Class)ContractDoubleDirectionOrderProfitInfo.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.days = (NumberPath<Integer>)this.createNumber("days", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.remainDays = (NumberPath<Integer>)this.createNumber("remainDays", (Class)Integer.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    public QContractDoubleDirectionOrderProfitInfo(final Path<? extends ContractDoubleDirectionOrderProfitInfo> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.days = (NumberPath<Integer>)this.createNumber("days", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.remainDays = (NumberPath<Integer>)this.createNumber("remainDays", (Class)Integer.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    public QContractDoubleDirectionOrderProfitInfo(final PathMetadata metadata) {
        super((Class)ContractDoubleDirectionOrderProfitInfo.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.days = (NumberPath<Integer>)this.createNumber("days", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.orderId = (NumberPath<Long>)this.createNumber("orderId", (Class)Long.class);
        this.profit = (NumberPath<BigDecimal>)this.createNumber("profit", (Class)BigDecimal.class);
        this.remainDays = (NumberPath<Integer>)this.createNumber("remainDays", (Class)Integer.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    static {
        contractDoubleDirectionOrderProfitInfo = new QContractDoubleDirectionOrderProfitInfo("contractDoubleDirectionOrderProfitInfo");
    }
}
