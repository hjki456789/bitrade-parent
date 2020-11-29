package cn.ztuo.bitrade.entity.unblock;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QUnblockLotteryDraw extends EntityPathBase<UnblockLotteryDraw>
{
    private static final long serialVersionUID = 814434102L;
    public static final QUnblockLotteryDraw unblockLotteryDraw;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> lotteryDrawCount;
    public final NumberPath<Long> lotteryTransCount;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> transCount;
    public final DateTimePath<Date> updateTime;
    
    public QUnblockLotteryDraw(final String variable) {
        super((Class)UnblockLotteryDraw.class, PathMetadataFactory.forVariable(variable));
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryDrawCount = (NumberPath<Long>)this.createNumber("lotteryDrawCount", (Class)Long.class);
        this.lotteryTransCount = (NumberPath<Long>)this.createNumber("lotteryTransCount", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.transCount = (NumberPath<Long>)this.createNumber("transCount", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QUnblockLotteryDraw(final Path<? extends UnblockLotteryDraw> path) {
        super(path.getType(), path.getMetadata());
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryDrawCount = (NumberPath<Long>)this.createNumber("lotteryDrawCount", (Class)Long.class);
        this.lotteryTransCount = (NumberPath<Long>)this.createNumber("lotteryTransCount", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.transCount = (NumberPath<Long>)this.createNumber("transCount", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    public QUnblockLotteryDraw(final PathMetadata metadata) {
        super((Class)UnblockLotteryDraw.class, metadata);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.lotteryDrawCount = (NumberPath<Long>)this.createNumber("lotteryDrawCount", (Class)Long.class);
        this.lotteryTransCount = (NumberPath<Long>)this.createNumber("lotteryTransCount", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.transCount = (NumberPath<Long>)this.createNumber("transCount", (Class)Long.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
    }
    
    static {
        unblockLotteryDraw = new QUnblockLotteryDraw("unblockLotteryDraw");
    }
}
