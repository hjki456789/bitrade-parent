package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QIeoEmption extends EntityPathBase<IeoEmption>
{
    private static final long serialVersionUID = 914353708L;
    public static final QIeoEmption ieoEmption;
    public final DateTimePath<Date> createTime;
    public final StringPath createUser;
    public final DateTimePath<Date> endTime;
    public final DateTimePath<Date> expectTime;
    public final NumberPath<BigDecimal> fee;
    public final NumberPath<BigDecimal> haveAmount;
    public final StringPath haveCoin;
    public final NumberPath<Long> id;
    public final StringPath ieoName;
    public final NumberPath<BigDecimal> limitAmount;
    public final StringPath pic;
    public final StringPath picView;
    public final StringPath raiseCoin;
    public final NumberPath<BigDecimal> ratio;
    public final NumberPath<BigDecimal> saleAmount;
    public final StringPath saleCoin;
    public final StringPath sellDetail;
    public final StringPath sellMode;
    public final DateTimePath<Date> startTime;
    public final NumberPath<BigDecimal> successRatio;
    public final NumberPath<BigDecimal> surplusAmount;
    public final DateTimePath<Date> updateTime;
    public final StringPath updateUser;
    
    public QIeoEmption(final String variable) {
        super((Class)IeoEmption.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.createUser = this.createString("createUser");
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.expectTime = (DateTimePath<Date>)this.createDateTime("expectTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.haveAmount = (NumberPath<BigDecimal>)this.createNumber("haveAmount", (Class)BigDecimal.class);
        this.haveCoin = this.createString("haveCoin");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ieoName = this.createString("ieoName");
        this.limitAmount = (NumberPath<BigDecimal>)this.createNumber("limitAmount", (Class)BigDecimal.class);
        this.pic = this.createString("pic");
        this.picView = this.createString("picView");
        this.raiseCoin = this.createString("raiseCoin");
        this.ratio = (NumberPath<BigDecimal>)this.createNumber("ratio", (Class)BigDecimal.class);
        this.saleAmount = (NumberPath<BigDecimal>)this.createNumber("saleAmount", (Class)BigDecimal.class);
        this.saleCoin = this.createString("saleCoin");
        this.sellDetail = this.createString("sellDetail");
        this.sellMode = this.createString("sellMode");
        this.startTime = (DateTimePath<Date>)this.createDateTime("startTime", (Class)Date.class);
        this.successRatio = (NumberPath<BigDecimal>)this.createNumber("successRatio", (Class)BigDecimal.class);
        this.surplusAmount = (NumberPath<BigDecimal>)this.createNumber("surplusAmount", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.updateUser = this.createString("updateUser");
    }
    
    public QIeoEmption(final Path<? extends IeoEmption> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.createUser = this.createString("createUser");
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.expectTime = (DateTimePath<Date>)this.createDateTime("expectTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.haveAmount = (NumberPath<BigDecimal>)this.createNumber("haveAmount", (Class)BigDecimal.class);
        this.haveCoin = this.createString("haveCoin");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ieoName = this.createString("ieoName");
        this.limitAmount = (NumberPath<BigDecimal>)this.createNumber("limitAmount", (Class)BigDecimal.class);
        this.pic = this.createString("pic");
        this.picView = this.createString("picView");
        this.raiseCoin = this.createString("raiseCoin");
        this.ratio = (NumberPath<BigDecimal>)this.createNumber("ratio", (Class)BigDecimal.class);
        this.saleAmount = (NumberPath<BigDecimal>)this.createNumber("saleAmount", (Class)BigDecimal.class);
        this.saleCoin = this.createString("saleCoin");
        this.sellDetail = this.createString("sellDetail");
        this.sellMode = this.createString("sellMode");
        this.startTime = (DateTimePath<Date>)this.createDateTime("startTime", (Class)Date.class);
        this.successRatio = (NumberPath<BigDecimal>)this.createNumber("successRatio", (Class)BigDecimal.class);
        this.surplusAmount = (NumberPath<BigDecimal>)this.createNumber("surplusAmount", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.updateUser = this.createString("updateUser");
    }
    
    public QIeoEmption(final PathMetadata metadata) {
        super((Class)IeoEmption.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.createUser = this.createString("createUser");
        this.endTime = (DateTimePath<Date>)this.createDateTime("endTime", (Class)Date.class);
        this.expectTime = (DateTimePath<Date>)this.createDateTime("expectTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.haveAmount = (NumberPath<BigDecimal>)this.createNumber("haveAmount", (Class)BigDecimal.class);
        this.haveCoin = this.createString("haveCoin");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ieoName = this.createString("ieoName");
        this.limitAmount = (NumberPath<BigDecimal>)this.createNumber("limitAmount", (Class)BigDecimal.class);
        this.pic = this.createString("pic");
        this.picView = this.createString("picView");
        this.raiseCoin = this.createString("raiseCoin");
        this.ratio = (NumberPath<BigDecimal>)this.createNumber("ratio", (Class)BigDecimal.class);
        this.saleAmount = (NumberPath<BigDecimal>)this.createNumber("saleAmount", (Class)BigDecimal.class);
        this.saleCoin = this.createString("saleCoin");
        this.sellDetail = this.createString("sellDetail");
        this.sellMode = this.createString("sellMode");
        this.startTime = (DateTimePath<Date>)this.createDateTime("startTime", (Class)Date.class);
        this.successRatio = (NumberPath<BigDecimal>)this.createNumber("successRatio", (Class)BigDecimal.class);
        this.surplusAmount = (NumberPath<BigDecimal>)this.createNumber("surplusAmount", (Class)BigDecimal.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.updateUser = this.createString("updateUser");
    }
    
    static {
        ieoEmption = new QIeoEmption("ieoEmption");
    }
}
