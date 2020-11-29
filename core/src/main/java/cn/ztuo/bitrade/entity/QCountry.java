package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QCountry extends EntityPathBase<Country>
{
    private static final long serialVersionUID = -39036461L;
    public static final QCountry country;
    public final StringPath areaCode;
    public final StringPath enName;
    public final StringPath language;
    public final StringPath localCurrency;
    public final NumberPath<Integer> sort;
    public final StringPath zhName;
    
    public QCountry(final String variable) {
        super((Class)Country.class, PathMetadataFactory.forVariable(variable));
        this.areaCode = this.createString("areaCode");
        this.enName = this.createString("enName");
        this.language = this.createString("language");
        this.localCurrency = this.createString("localCurrency");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.zhName = this.createString("zhName");
    }
    
    public QCountry(final Path<? extends Country> path) {
        super(path.getType(), path.getMetadata());
        this.areaCode = this.createString("areaCode");
        this.enName = this.createString("enName");
        this.language = this.createString("language");
        this.localCurrency = this.createString("localCurrency");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.zhName = this.createString("zhName");
    }
    
    public QCountry(final PathMetadata metadata) {
        super((Class)Country.class, metadata);
        this.areaCode = this.createString("areaCode");
        this.enName = this.createString("enName");
        this.language = this.createString("language");
        this.localCurrency = this.createString("localCurrency");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.zhName = this.createString("zhName");
    }
    
    static {
        country = new QCountry("country");
    }
}
