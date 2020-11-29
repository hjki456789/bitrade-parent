package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QLocation extends BeanPath<Location>
{
    private static final long serialVersionUID = 1062921496L;
    public static final QLocation location;
    public final StringPath city;
    public final StringPath country;
    public final StringPath district;
    public final StringPath province;
    
    public QLocation(final String variable) {
        super((Class)Location.class, PathMetadataFactory.forVariable(variable));
        this.city = this.createString("city");
        this.country = this.createString("country");
        this.district = this.createString("district");
        this.province = this.createString("province");
    }
    
    public QLocation(final Path<? extends Location> path) {
        super(path.getType(), path.getMetadata());
        this.city = this.createString("city");
        this.country = this.createString("country");
        this.district = this.createString("district");
        this.province = this.createString("province");
    }
    
    public QLocation(final PathMetadata metadata) {
        super((Class)Location.class, metadata);
        this.city = this.createString("city");
        this.country = this.createString("country");
        this.district = this.createString("district");
        this.province = this.createString("province");
    }
    
    static {
        location = new QLocation("location");
    }
}
