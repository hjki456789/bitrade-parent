package cn.ztuo.bitrade.entity;

import java.math.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractMemberProfitLoss extends EntityPathBase<ContractMemberProfitLoss>
{
    private static final long serialVersionUID = 1827354198L;
    public static final QContractMemberProfitLoss contractMemberProfitLoss;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<BigDecimal> profitLossAmount;
    public final EnumPath<ProfitLossType> type;
    public final StringPath weekEndTime;
    public final StringPath weekStartTime;
    
    public QContractMemberProfitLoss(final String variable) {
        super((Class)ContractMemberProfitLoss.class, PathMetadataFactory.forVariable(variable));
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.profitLossAmount = (NumberPath<BigDecimal>)this.createNumber("profitLossAmount", (Class)BigDecimal.class);
        this.type = (EnumPath<ProfitLossType>)this.createEnum("type", (Class)ProfitLossType.class);
        this.weekEndTime = this.createString("weekEndTime");
        this.weekStartTime = this.createString("weekStartTime");
    }
    
    public QContractMemberProfitLoss(final Path<? extends ContractMemberProfitLoss> path) {
        super(path.getType(), path.getMetadata());
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.profitLossAmount = (NumberPath<BigDecimal>)this.createNumber("profitLossAmount", (Class)BigDecimal.class);
        this.type = (EnumPath<ProfitLossType>)this.createEnum("type", (Class)ProfitLossType.class);
        this.weekEndTime = this.createString("weekEndTime");
        this.weekStartTime = this.createString("weekStartTime");
    }
    
    public QContractMemberProfitLoss(final PathMetadata metadata) {
        super((Class)ContractMemberProfitLoss.class, metadata);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.profitLossAmount = (NumberPath<BigDecimal>)this.createNumber("profitLossAmount", (Class)BigDecimal.class);
        this.type = (EnumPath<ProfitLossType>)this.createEnum("type", (Class)ProfitLossType.class);
        this.weekEndTime = this.createString("weekEndTime");
        this.weekStartTime = this.createString("weekStartTime");
    }
    
    static {
        contractMemberProfitLoss = new QContractMemberProfitLoss("contractMemberProfitLoss");
    }
}
