package cn.ztuo.bitrade.entity.contractdouble;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractDoubleExchangeConfig extends EntityPathBase<ContractDoubleExchangeConfig>
{
    private static final long serialVersionUID = 6197328L;
    public static final QContractDoubleExchangeConfig contractDoubleExchangeConfig;
    public final StringPath apiUrl;
    public final StringPath cnName;
    public final StringPath content;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final StringPath name;
    
    public QContractDoubleExchangeConfig(final String variable) {
        super((Class)ContractDoubleExchangeConfig.class, PathMetadataFactory.forVariable(variable));
        this.apiUrl = this.createString("apiUrl");
        this.cnName = this.createString("cnName");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
    }
    
    public QContractDoubleExchangeConfig(final Path<? extends ContractDoubleExchangeConfig> path) {
        super(path.getType(), path.getMetadata());
        this.apiUrl = this.createString("apiUrl");
        this.cnName = this.createString("cnName");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
    }
    
    public QContractDoubleExchangeConfig(final PathMetadata metadata) {
        super((Class)ContractDoubleExchangeConfig.class, metadata);
        this.apiUrl = this.createString("apiUrl");
        this.cnName = this.createString("cnName");
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
    }
    
    static {
        contractDoubleExchangeConfig = new QContractDoubleExchangeConfig("contractDoubleExchangeConfig");
    }
}
