package cn.ztuo.bitrade.entity.unblock;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QUnblockLotteryRecord extends EntityPathBase<UnblockLotteryRecord>
{
    private static final long serialVersionUID = 1375982019L;
    public static final QUnblockLotteryRecord unblockLotteryRecord;
    public final NumberPath<BigDecimal> amount;
    public final StringPath coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Integer> isWinne;
    public final NumberPath<Long> lotteryId;
    public final StringPath lotteryInstruction;
    public final StringPath lotteryLevel;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> sequence;
    
    public QUnblockLotteryRecord(final String variable) {
        super((Class)UnblockLotteryRecord.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isWinne = (NumberPath<Integer>)this.createNumber("isWinne", (Class)Integer.class);
        this.lotteryId = (NumberPath<Long>)this.createNumber("lotteryId", (Class)Long.class);
        this.lotteryInstruction = this.createString("lotteryInstruction");
        this.lotteryLevel = this.createString("lotteryLevel");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QUnblockLotteryRecord(final Path<? extends UnblockLotteryRecord> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isWinne = (NumberPath<Integer>)this.createNumber("isWinne", (Class)Integer.class);
        this.lotteryId = (NumberPath<Long>)this.createNumber("lotteryId", (Class)Long.class);
        this.lotteryInstruction = this.createString("lotteryInstruction");
        this.lotteryLevel = this.createString("lotteryLevel");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QUnblockLotteryRecord(final PathMetadata metadata) {
        super((Class)UnblockLotteryRecord.class, metadata);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isWinne = (NumberPath<Integer>)this.createNumber("isWinne", (Class)Integer.class);
        this.lotteryId = (NumberPath<Long>)this.createNumber("lotteryId", (Class)Long.class);
        this.lotteryInstruction = this.createString("lotteryInstruction");
        this.lotteryLevel = this.createString("lotteryLevel");
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    static {
        unblockLotteryRecord = new QUnblockLotteryRecord("unblockLotteryRecord");
    }
}
