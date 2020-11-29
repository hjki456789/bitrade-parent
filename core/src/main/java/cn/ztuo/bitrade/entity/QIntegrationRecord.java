package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QIntegrationRecord extends EntityPathBase<IntegrationRecord>
{
    private static final long serialVersionUID = -1886504638L;
    public static final QIntegrationRecord integrationRecord;
    public final NumberPath<Long> amount;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final EnumPath<IntegrationRecordType> type;
    
    public QIntegrationRecord(final String variable) {
        super((Class)IntegrationRecord.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.type = (EnumPath<IntegrationRecordType>)this.createEnum("type", (Class)IntegrationRecordType.class);
    }
    
    public QIntegrationRecord(final Path<? extends IntegrationRecord> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.type = (EnumPath<IntegrationRecordType>)this.createEnum("type", (Class)IntegrationRecordType.class);
    }
    
    public QIntegrationRecord(final PathMetadata metadata) {
        super((Class)IntegrationRecord.class, metadata);
        this.amount = (NumberPath<Long>)this.createNumber("amount", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.type = (EnumPath<IntegrationRecordType>)this.createEnum("type", (Class)IntegrationRecordType.class);
    }
    
    static {
        integrationRecord = new QIntegrationRecord("integrationRecord");
    }
}
