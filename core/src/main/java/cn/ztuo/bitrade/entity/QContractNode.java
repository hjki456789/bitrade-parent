package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QContractNode extends EntityPathBase<ContractNode>
{
    private static final long serialVersionUID = 1320891927L;
    public static final QContractNode contractNode;
    public final NumberPath<BigDecimal> closeRate;
    public final NumberPath<BigDecimal> depositAmount;
    public final NumberPath<BigDecimal> depositLimitRate;
    public final NumberPath<Integer> enable;
    public final NumberPath<BigDecimal> feeReturnRate;
    public final NumberPath<BigDecimal> holdFeeReturnRate;
    public final StringPath id;
    public final StringPath lastNodeId;
    public final NumberPath<Long> memberId;
    public final EnumPath<IfNodeType> memberStatus;
    public final NumberPath<Integer> nodeLevel;
    public final StringPath nodeName;
    public final StringPath nodeType;
    public final NumberPath<BigDecimal> profitLossReturnRate;
    public final StringPath superNodeId;
    public final NumberPath<BigDecimal> transferRate;
    public final NumberPath<BigDecimal> virtualRechargeAmount;
    public final NumberPath<BigDecimal> warningRate;
    
    public QContractNode(final String variable) {
        super((Class)ContractNode.class, PathMetadataFactory.forVariable(variable));
        this.closeRate = (NumberPath<BigDecimal>)this.createNumber("closeRate", (Class)BigDecimal.class);
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositLimitRate = (NumberPath<BigDecimal>)this.createNumber("depositLimitRate", (Class)BigDecimal.class);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.feeReturnRate = (NumberPath<BigDecimal>)this.createNumber("feeReturnRate", (Class)BigDecimal.class);
        this.holdFeeReturnRate = (NumberPath<BigDecimal>)this.createNumber("holdFeeReturnRate", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.lastNodeId = this.createString("lastNodeId");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberStatus = (EnumPath<IfNodeType>)this.createEnum("memberStatus", (Class)IfNodeType.class);
        this.nodeLevel = (NumberPath<Integer>)this.createNumber("nodeLevel", (Class)Integer.class);
        this.nodeName = this.createString("nodeName");
        this.nodeType = this.createString("nodeType");
        this.profitLossReturnRate = (NumberPath<BigDecimal>)this.createNumber("profitLossReturnRate", (Class)BigDecimal.class);
        this.superNodeId = this.createString("superNodeId");
        this.transferRate = (NumberPath<BigDecimal>)this.createNumber("transferRate", (Class)BigDecimal.class);
        this.virtualRechargeAmount = (NumberPath<BigDecimal>)this.createNumber("virtualRechargeAmount", (Class)BigDecimal.class);
        this.warningRate = (NumberPath<BigDecimal>)this.createNumber("warningRate", (Class)BigDecimal.class);
    }
    
    public QContractNode(final Path<? extends ContractNode> path) {
        super(path.getType(), path.getMetadata());
        this.closeRate = (NumberPath<BigDecimal>)this.createNumber("closeRate", (Class)BigDecimal.class);
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositLimitRate = (NumberPath<BigDecimal>)this.createNumber("depositLimitRate", (Class)BigDecimal.class);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.feeReturnRate = (NumberPath<BigDecimal>)this.createNumber("feeReturnRate", (Class)BigDecimal.class);
        this.holdFeeReturnRate = (NumberPath<BigDecimal>)this.createNumber("holdFeeReturnRate", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.lastNodeId = this.createString("lastNodeId");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberStatus = (EnumPath<IfNodeType>)this.createEnum("memberStatus", (Class)IfNodeType.class);
        this.nodeLevel = (NumberPath<Integer>)this.createNumber("nodeLevel", (Class)Integer.class);
        this.nodeName = this.createString("nodeName");
        this.nodeType = this.createString("nodeType");
        this.profitLossReturnRate = (NumberPath<BigDecimal>)this.createNumber("profitLossReturnRate", (Class)BigDecimal.class);
        this.superNodeId = this.createString("superNodeId");
        this.transferRate = (NumberPath<BigDecimal>)this.createNumber("transferRate", (Class)BigDecimal.class);
        this.virtualRechargeAmount = (NumberPath<BigDecimal>)this.createNumber("virtualRechargeAmount", (Class)BigDecimal.class);
        this.warningRate = (NumberPath<BigDecimal>)this.createNumber("warningRate", (Class)BigDecimal.class);
    }
    
    public QContractNode(final PathMetadata metadata) {
        super((Class)ContractNode.class, metadata);
        this.closeRate = (NumberPath<BigDecimal>)this.createNumber("closeRate", (Class)BigDecimal.class);
        this.depositAmount = (NumberPath<BigDecimal>)this.createNumber("depositAmount", (Class)BigDecimal.class);
        this.depositLimitRate = (NumberPath<BigDecimal>)this.createNumber("depositLimitRate", (Class)BigDecimal.class);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.feeReturnRate = (NumberPath<BigDecimal>)this.createNumber("feeReturnRate", (Class)BigDecimal.class);
        this.holdFeeReturnRate = (NumberPath<BigDecimal>)this.createNumber("holdFeeReturnRate", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.lastNodeId = this.createString("lastNodeId");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberStatus = (EnumPath<IfNodeType>)this.createEnum("memberStatus", (Class)IfNodeType.class);
        this.nodeLevel = (NumberPath<Integer>)this.createNumber("nodeLevel", (Class)Integer.class);
        this.nodeName = this.createString("nodeName");
        this.nodeType = this.createString("nodeType");
        this.profitLossReturnRate = (NumberPath<BigDecimal>)this.createNumber("profitLossReturnRate", (Class)BigDecimal.class);
        this.superNodeId = this.createString("superNodeId");
        this.transferRate = (NumberPath<BigDecimal>)this.createNumber("transferRate", (Class)BigDecimal.class);
        this.virtualRechargeAmount = (NumberPath<BigDecimal>)this.createNumber("virtualRechargeAmount", (Class)BigDecimal.class);
        this.warningRate = (NumberPath<BigDecimal>)this.createNumber("warningRate", (Class)BigDecimal.class);
    }
    
    static {
        contractNode = new QContractNode("contractNode");
    }
}
