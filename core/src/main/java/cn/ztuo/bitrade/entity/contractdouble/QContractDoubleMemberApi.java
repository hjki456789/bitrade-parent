package cn.ztuo.bitrade.entity.contractdouble;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractDoubleMemberApi extends EntityPathBase<ContractDoubleMemberApi>
{
    private static final long serialVersionUID = 1578927413L;
    public static final QContractDoubleMemberApi contractDoubleMemberApi;
    public final StringPath apiKey;
    public final NumberPath<Integer> deleteFlag;
    public final NumberPath<Long> fromExchangeId;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> ifDefault;
    public final NumberPath<Long> memberId;
    public final StringPath secretKey;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    
    public QContractDoubleMemberApi(final String variable) {
        super((Class)ContractDoubleMemberApi.class, PathMetadataFactory.forVariable(variable));
        this.apiKey = this.createString("apiKey");
        this.deleteFlag = (NumberPath<Integer>)this.createNumber("deleteFlag", (Class)Integer.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ifDefault = (NumberPath<Integer>)this.createNumber("ifDefault", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.secretKey = this.createString("secretKey");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    public QContractDoubleMemberApi(final Path<? extends ContractDoubleMemberApi> path) {
        super(path.getType(), path.getMetadata());
        this.apiKey = this.createString("apiKey");
        this.deleteFlag = (NumberPath<Integer>)this.createNumber("deleteFlag", (Class)Integer.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ifDefault = (NumberPath<Integer>)this.createNumber("ifDefault", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.secretKey = this.createString("secretKey");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    public QContractDoubleMemberApi(final PathMetadata metadata) {
        super((Class)ContractDoubleMemberApi.class, metadata);
        this.apiKey = this.createString("apiKey");
        this.deleteFlag = (NumberPath<Integer>)this.createNumber("deleteFlag", (Class)Integer.class);
        this.fromExchangeId = (NumberPath<Long>)this.createNumber("fromExchangeId", (Class)Long.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ifDefault = (NumberPath<Integer>)this.createNumber("ifDefault", (Class)Integer.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.secretKey = this.createString("secretKey");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
    }
    
    static {
        contractDoubleMemberApi = new QContractDoubleMemberApi("contractDoubleMemberApi");
    }
}
