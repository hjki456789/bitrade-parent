package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QRewardRecord extends EntityPathBase<RewardRecord>
{
    private static final long serialVersionUID = 127673411L;
    private static final PathInits INITS;
    public static final QRewardRecord rewardRecord;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QMember member;
    public final StringPath remark;
    public final EnumPath<RewardRecordType> type;
    
    public QRewardRecord(final String variable) {
        this(RewardRecord.class, PathMetadataFactory.forVariable(variable), QRewardRecord.INITS);
    }
    
    public QRewardRecord(final Path<? extends RewardRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QRewardRecord.INITS));
    }
    
    public QRewardRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QRewardRecord.INITS));
    }
    
    public QRewardRecord(final PathMetadata metadata, final PathInits inits) {
        this(RewardRecord.class, metadata, inits);
    }
    
    public QRewardRecord(final Class<? extends RewardRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.remark = this.createString("remark");
        this.type = (EnumPath<RewardRecordType>)this.createEnum("type", (Class)RewardRecordType.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        rewardRecord = new QRewardRecord("rewardRecord");
    }
}
