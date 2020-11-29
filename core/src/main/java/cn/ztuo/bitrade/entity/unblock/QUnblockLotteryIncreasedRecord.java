package cn.ztuo.bitrade.entity.unblock;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.enums.*;
import com.querydsl.core.types.*;

public class QUnblockLotteryIncreasedRecord extends EntityPathBase<UnblockLotteryIncreasedRecord>
{
    private static final long serialVersionUID = -1886884831L;
    public static final QUnblockLotteryIncreasedRecord unblockLotteryIncreasedRecord;
    public final NumberPath<Long> addCount;
    public final NumberPath<Long> afterCount;
    public final NumberPath<Long> beforeCount;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final EnumPath<LotteryAddType> lotteryAddType;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> time;
    public final DateTimePath<Date> updateTime;
    
    public QUnblockLotteryIncreasedRecord(final String variable) {
        super((Class)UnblockLotteryIncreasedRecord.class, PathMetadataFactory.forVariable(variable));
        this.addCount = (NumberPath<Long>)this.createNumber("addCount", (Class)Long.class);
        this.afterCount = (NumberPath<Long>)this.createNumber("afterCount", (Class)Long.class);
        this.beforeCount = (NumberPath<Long>)this.createNumber("beforeCount", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryAddType = (EnumPath<LotteryAddType>)this.createEnum("lotteryAddType", (Class)LotteryAddType.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.time = (NumberPath<Long>)this.createNumber("time", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QUnblockLotteryIncreasedRecord(final Path<? extends UnblockLotteryIncreasedRecord> path) {
        super(path.getType(), path.getMetadata());
        this.addCount = (NumberPath<Long>)this.createNumber("addCount", (Class)Long.class);
        this.afterCount = (NumberPath<Long>)this.createNumber("afterCount", (Class)Long.class);
        this.beforeCount = (NumberPath<Long>)this.createNumber("beforeCount", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryAddType = (EnumPath<LotteryAddType>)this.createEnum("lotteryAddType", (Class)LotteryAddType.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.time = (NumberPath<Long>)this.createNumber("time", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QUnblockLotteryIncreasedRecord(final PathMetadata metadata) {
        super((Class)UnblockLotteryIncreasedRecord.class, metadata);
        this.addCount = (NumberPath<Long>)this.createNumber("addCount", (Class)Long.class);
        this.afterCount = (NumberPath<Long>)this.createNumber("afterCount", (Class)Long.class);
        this.beforeCount = (NumberPath<Long>)this.createNumber("beforeCount", (Class)Long.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryAddType = (EnumPath<LotteryAddType>)this.createEnum("lotteryAddType", (Class)LotteryAddType.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.time = (NumberPath<Long>)this.createNumber("time", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        unblockLotteryIncreasedRecord = new QUnblockLotteryIncreasedRecord("unblockLotteryIncreasedRecord");
    }
}
