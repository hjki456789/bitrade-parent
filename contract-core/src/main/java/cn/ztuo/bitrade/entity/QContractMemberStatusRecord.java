package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QContractMemberStatusRecord extends EntityPathBase<ContractMemberStatusRecord>
{
    private static final long serialVersionUID = -1830817294L;
    public static final QContractMemberStatusRecord contractMemberStatusRecord;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath memberIds;
    public final NumberPath<Long> nodeMemberId;
    public final NumberPath<Long> sequence;
    public final NumberPath<Integer> status;
    public final DateTimePath<Date> time;
    
    public QContractMemberStatusRecord(final String variable) {
        super((Class)ContractMemberStatusRecord.class, PathMetadataFactory.forVariable(variable));
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberIds = this.createString("memberIds");
        this.nodeMemberId = (NumberPath<Long>)this.createNumber("nodeMemberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    public QContractMemberStatusRecord(final Path<? extends ContractMemberStatusRecord> path) {
        super(path.getType(), path.getMetadata());
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberIds = this.createString("memberIds");
        this.nodeMemberId = (NumberPath<Long>)this.createNumber("nodeMemberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    public QContractMemberStatusRecord(final PathMetadata metadata) {
        super((Class)ContractMemberStatusRecord.class, metadata);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.memberIds = this.createString("memberIds");
        this.nodeMemberId = (NumberPath<Long>)this.createNumber("nodeMemberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
        this.status = (NumberPath<Integer>)this.createNumber("status", (Class)Integer.class);
        this.time = (DateTimePath<Date>)this.createDateTime("time", (Class)Date.class);
    }
    
    static {
        contractMemberStatusRecord = new QContractMemberStatusRecord("contractMemberStatusRecord");
    }
}
