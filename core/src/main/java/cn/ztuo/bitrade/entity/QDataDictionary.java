package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QDataDictionary extends EntityPathBase<DataDictionary>
{
    private static final long serialVersionUID = 1873977091L;
    public static final QDataDictionary dataDictionary;
    public final StringPath bond;
    public final StringPath comment;
    public final DateTimePath<Date> creationTime;
    public final NumberPath<Long> id;
    public final StringPath imgUrl;
    public final DateTimePath<Date> updateTime;
    public final StringPath value;
    
    public QDataDictionary(final String variable) {
        super((Class)DataDictionary.class, PathMetadataFactory.forVariable(variable));
        this.bond = this.createString("bond");
        this.comment = this.createString("comment");
        this.creationTime = (DateTimePath<Date>)this.createDateTime("creationTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.value = this.createString("value");
    }
    
    public QDataDictionary(final Path<? extends DataDictionary> path) {
        super(path.getType(), path.getMetadata());
        this.bond = this.createString("bond");
        this.comment = this.createString("comment");
        this.creationTime = (DateTimePath<Date>)this.createDateTime("creationTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.value = this.createString("value");
    }
    
    public QDataDictionary(final PathMetadata metadata) {
        super((Class)DataDictionary.class, metadata);
        this.bond = this.createString("bond");
        this.comment = this.createString("comment");
        this.creationTime = (DateTimePath<Date>)this.createDateTime("creationTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.value = this.createString("value");
    }
    
    static {
        dataDictionary = new QDataDictionary("dataDictionary");
    }
}
