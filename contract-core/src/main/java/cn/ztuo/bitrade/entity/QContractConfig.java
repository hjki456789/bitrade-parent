package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QContractConfig extends EntityPathBase<ContractConfig>
{
    private static final long serialVersionUID = 2047171095L;
    public static final QContractConfig contractConfig;
    public final StringPath configKey;
    public final StringPath configValue;
    public final StringPath description;
    
    public QContractConfig(final String variable) {
        super((Class)ContractConfig.class, PathMetadataFactory.forVariable(variable));
        this.configKey = this.createString("configKey");
        this.configValue = this.createString("configValue");
        this.description = this.createString("description");
    }
    
    public QContractConfig(final Path<? extends ContractConfig> path) {
        super(path.getType(), path.getMetadata());
        this.configKey = this.createString("configKey");
        this.configValue = this.createString("configValue");
        this.description = this.createString("description");
    }
    
    public QContractConfig(final PathMetadata metadata) {
        super((Class)ContractConfig.class, metadata);
        this.configKey = this.createString("configKey");
        this.configValue = this.createString("configValue");
        this.description = this.createString("description");
    }
    
    static {
        contractConfig = new QContractConfig("contractConfig");
    }
}
