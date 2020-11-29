package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractMemberTransferRecord extends EntityPathBase<ContractMemberTransferRecord>
{
    private static final long serialVersionUID = -1175735445L;
    private static final PathInits INITS;
    public static final QContractMemberTransferRecord contractMemberTransferRecord;
    public final DateTimePath<Date> createTime;
    public final StringPath id;
    public final QMember member;
    public final QMember newProxyMember;
    public final QMember orginalProxyMember;
    public final NumberPath<Long> sequence;
    
    public QContractMemberTransferRecord(final String variable) {
        this(ContractMemberTransferRecord.class, PathMetadataFactory.forVariable(variable), QContractMemberTransferRecord.INITS);
    }
    
    public QContractMemberTransferRecord(final Path<? extends ContractMemberTransferRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QContractMemberTransferRecord.INITS));
    }
    
    public QContractMemberTransferRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QContractMemberTransferRecord.INITS));
    }
    
    public QContractMemberTransferRecord(final PathMetadata metadata, final PathInits inits) {
        this(ContractMemberTransferRecord.class, metadata, inits);
    }
    
    public QContractMemberTransferRecord(final Class<? extends ContractMemberTransferRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = this.createString("id");
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
        this.newProxyMember = (inits.isInitialized("newProxyMember") ? new QMember(this.forProperty("newProxyMember"), inits.get("newProxyMember")) : null);
        this.orginalProxyMember = (inits.isInitialized("orginalProxyMember") ? new QMember(this.forProperty("orginalProxyMember"), inits.get("orginalProxyMember")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        contractMemberTransferRecord = new QContractMemberTransferRecord("contractMemberTransferRecord");
    }
}
