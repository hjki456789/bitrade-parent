package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QRobotFromExchageConfig extends EntityPathBase<RobotFromExchageConfig>
{
    private static final long serialVersionUID = -1242642664L;
    public static final QRobotFromExchageConfig robotFromExchageConfig;
    public final StringPath baseUrl;
    public final StringPath host;
    public final NumberPath<Long> id;
    public final StringPath name;
    public final StringPath nameCn;
    public final StringPath remark;
    
    public QRobotFromExchageConfig(final String variable) {
        super((Class)RobotFromExchageConfig.class, PathMetadataFactory.forVariable(variable));
        this.baseUrl = this.createString("baseUrl");
        this.host = this.createString("host");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.remark = this.createString("remark");
    }
    
    public QRobotFromExchageConfig(final Path<? extends RobotFromExchageConfig> path) {
        super(path.getType(), path.getMetadata());
        this.baseUrl = this.createString("baseUrl");
        this.host = this.createString("host");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.remark = this.createString("remark");
    }
    
    public QRobotFromExchageConfig(final PathMetadata metadata) {
        super((Class)RobotFromExchageConfig.class, metadata);
        this.baseUrl = this.createString("baseUrl");
        this.host = this.createString("host");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.remark = this.createString("remark");
    }
    
    static {
        robotFromExchageConfig = new QRobotFromExchageConfig("robotFromExchageConfig");
    }
}
