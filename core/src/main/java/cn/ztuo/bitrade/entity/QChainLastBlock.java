package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QChainLastBlock extends EntityPathBase<ChainLastBlock>
{
    private static final long serialVersionUID = 2085499129L;
    public static final QChainLastBlock chainLastBlock;
    public final StringPath assetsName;
    public final NumberPath<Long> id;
    public final NumberPath<Long> lastBlockNum;
    public final DateTimePath<Date> updateTime;
    
    public QChainLastBlock(final String variable) {
        super((Class)ChainLastBlock.class, PathMetadataFactory.forVariable(variable));
        this.assetsName = this.createString("assetsName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lastBlockNum = (NumberPath<Long>)this.createNumber("lastBlockNum", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QChainLastBlock(final Path<? extends ChainLastBlock> path) {
        super(path.getType(), path.getMetadata());
        this.assetsName = this.createString("assetsName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lastBlockNum = (NumberPath<Long>)this.createNumber("lastBlockNum", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QChainLastBlock(final PathMetadata metadata) {
        super((Class)ChainLastBlock.class, metadata);
        this.assetsName = this.createString("assetsName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lastBlockNum = (NumberPath<Long>)this.createNumber("lastBlockNum", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        chainLastBlock = new QChainLastBlock("chainLastBlock");
    }
}
