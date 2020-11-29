package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QInitPlate extends EntityPathBase<InitPlate>
{
    private static final long serialVersionUID = -483748125L;
    public static final QInitPlate initPlate;
    public final StringPath finalPrice;
    public final NumberPath<Long> id;
    public final StringPath initPrice;
    public final StringPath interferenceFactor;
    public final StringPath relativeTime;
    public final StringPath symbol;
    
    public QInitPlate(final String variable) {
        super((Class)InitPlate.class, PathMetadataFactory.forVariable(variable));
        this.finalPrice = this.createString("finalPrice");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.initPrice = this.createString("initPrice");
        this.interferenceFactor = this.createString("interferenceFactor");
        this.relativeTime = this.createString("relativeTime");
        this.symbol = this.createString("symbol");
    }
    
    public QInitPlate(final Path<? extends InitPlate> path) {
        super(path.getType(), path.getMetadata());
        this.finalPrice = this.createString("finalPrice");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.initPrice = this.createString("initPrice");
        this.interferenceFactor = this.createString("interferenceFactor");
        this.relativeTime = this.createString("relativeTime");
        this.symbol = this.createString("symbol");
    }
    
    public QInitPlate(final PathMetadata metadata) {
        super((Class)InitPlate.class, metadata);
        this.finalPrice = this.createString("finalPrice");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.initPrice = this.createString("initPrice");
        this.interferenceFactor = this.createString("interferenceFactor");
        this.relativeTime = this.createString("relativeTime");
        this.symbol = this.createString("symbol");
    }
    
    static {
        initPlate = new QInitPlate("initPlate");
    }
}
