package cn.ztuo.bitrade.entity.contractstrategy;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractStrategyExchangeConfig extends EntityPathBase<ContractStrategyExchangeConfig>
{
    private static final long serialVersionUID = -556711088L;
    public static final QContractStrategyExchangeConfig contractStrategyExchangeConfig;
    public final StringPath apiUrl;
    public final StringPath cnName;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final StringPath name;
    
    public QContractStrategyExchangeConfig(final String variable) {
        super((Class)ContractStrategyExchangeConfig.class, PathMetadataFactory.forVariable(variable));
        this.apiUrl = this.createString("apiUrl");
        this.cnName = this.createString("cnName");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
    }
    
    public QContractStrategyExchangeConfig(final Path<? extends ContractStrategyExchangeConfig> path) {
        super(path.getType(), path.getMetadata());
        this.apiUrl = this.createString("apiUrl");
        this.cnName = this.createString("cnName");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
    }
    
    public QContractStrategyExchangeConfig(final PathMetadata metadata) {
        super((Class)ContractStrategyExchangeConfig.class, metadata);
        this.apiUrl = this.createString("apiUrl");
        this.cnName = this.createString("cnName");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
    }
    
    static {
        contractStrategyExchangeConfig = new QContractStrategyExchangeConfig("contractStrategyExchangeConfig");
    }
}
