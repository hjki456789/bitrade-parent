package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractCoinNode extends EntityPathBase<ContractCoinNode>
{
    private static final long serialVersionUID = 99849672L;
    private static final PathInits INITS;
    public static final QContractCoinNode contractCoinNode;
    public final QContractCoin contractCoin;
    public final QContractNode contractNode;
    public final NumberPath<Integer> enable;
    public final StringPath id;
    public final NumberPath<Long> sequence;
    
    public QContractCoinNode(final String variable) {
        this(ContractCoinNode.class, PathMetadataFactory.forVariable(variable), QContractCoinNode.INITS);
    }
    
    public QContractCoinNode(final Path<? extends ContractCoinNode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractCoinNode.INITS));
    }
    
    public QContractCoinNode(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractCoinNode.INITS));
    }
    
    public QContractCoinNode(final PathMetadata metadata, final PathInits inits) {
        this(ContractCoinNode.class, metadata, inits);
    }
    
    public QContractCoinNode(final Class<? extends ContractCoinNode> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.id = this.createString("id");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.contractCoin = (inits.isInitialized("contractCoin") ? new QContractCoin(this.forProperty("contractCoin")) : null);
        this.contractNode = (inits.isInitialized("contractNode") ? new QContractNode(this.forProperty("contractNode")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractCoinNode = new QContractCoinNode("contractCoinNode");
    }
}
