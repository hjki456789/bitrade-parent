package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QCoinConvert extends EntityPathBase<CoinConvert>
{
    private static final long serialVersionUID = 548162975L;
    public static final QCoinConvert coinConvert;
    public final StringPath baseCoin;
    public final StringPath convertCoin;
    public final NumberPath<Double> fee;
    public final NumberPath<Long> id;
    
    public QCoinConvert(final String variable) {
        super((Class)CoinConvert.class, PathMetadataFactory.forVariable(variable));
        this.baseCoin = this.createString("baseCoin");
        this.convertCoin = this.createString("convertCoin");
        this.fee = (NumberPath<Double>)this.createNumber("fee", (Class)Double.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    public QCoinConvert(final Path<? extends CoinConvert> path) {
        super(path.getType(), path.getMetadata());
        this.baseCoin = this.createString("baseCoin");
        this.convertCoin = this.createString("convertCoin");
        this.fee = (NumberPath<Double>)this.createNumber("fee", (Class)Double.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    public QCoinConvert(final PathMetadata metadata) {
        super((Class)CoinConvert.class, metadata);
        this.baseCoin = this.createString("baseCoin");
        this.convertCoin = this.createString("convertCoin");
        this.fee = (NumberPath<Double>)this.createNumber("fee", (Class)Double.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    static {
        coinConvert = new QCoinConvert("coinConvert");
    }
}
