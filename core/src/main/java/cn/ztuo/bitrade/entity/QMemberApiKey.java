package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QMemberApiKey extends EntityPathBase<MemberApiKey>
{
    private static final long serialVersionUID = 1816966402L;
    public static final QMemberApiKey memberApiKey;
    public final StringPath apiKey;
    public final StringPath apiName;
    public final StringPath bindIp;
    public final DateTimePath<Date> createTime;
    public final DateTimePath<Date> expireTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final StringPath remark;
    public final StringPath secretKey;
    
    public QMemberApiKey(final String variable) {
        super((Class)MemberApiKey.class, PathMetadataFactory.forVariable(variable));
        this.apiKey = this.createString("apiKey");
        this.apiName = this.createString("apiName");
        this.bindIp = this.createString("bindIp");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.expireTime = (DateTimePath<Date>)this.createDateTime("expireTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
    }
    
    public QMemberApiKey(final Path<? extends MemberApiKey> path) {
        super(path.getType(), path.getMetadata());
        this.apiKey = this.createString("apiKey");
        this.apiName = this.createString("apiName");
        this.bindIp = this.createString("bindIp");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.expireTime = (DateTimePath<Date>)this.createDateTime("expireTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
    }
    
    public QMemberApiKey(final PathMetadata metadata) {
        super((Class)MemberApiKey.class, metadata);
        this.apiKey = this.createString("apiKey");
        this.apiName = this.createString("apiName");
        this.bindIp = this.createString("bindIp");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.expireTime = (DateTimePath<Date>)this.createDateTime("expireTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.secretKey = this.createString("secretKey");
    }
    
    static {
        memberApiKey = new QMemberApiKey("memberApiKey");
    }
}
