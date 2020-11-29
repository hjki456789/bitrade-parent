package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QRewardActivitySetting extends EntityPathBase<RewardActivitySetting>
{
    private static final long serialVersionUID = 860556431L;
    private static final PathInits INITS;
    public static final QRewardActivitySetting rewardActivitySetting;
    public final QAdmin admin;
    public final QCoin coin;
    public final NumberPath<Long> id;
    public final StringPath info;
    public final EnumPath<BooleanEnum> status;
    public final EnumPath<ActivityRewardType> type;
    public final DateTimePath<Date> updateTime;
    
    public QRewardActivitySetting(final String variable) {
        this(RewardActivitySetting.class, PathMetadataFactory.forVariable(variable), QRewardActivitySetting.INITS);
    }
    
    public QRewardActivitySetting(final Path<? extends RewardActivitySetting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QRewardActivitySetting.INITS));
    }
    
    public QRewardActivitySetting(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QRewardActivitySetting.INITS));
    }
    
    public QRewardActivitySetting(final PathMetadata metadata, final PathInits inits) {
        this(RewardActivitySetting.class, metadata, inits);
    }
    
    public QRewardActivitySetting(final Class<? extends RewardActivitySetting> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.info = this.createString("info");
        this.status = (EnumPath<BooleanEnum>)this.createEnum("status", (Class)BooleanEnum.class);
        this.type = (EnumPath<ActivityRewardType>)this.createEnum("type", (Class)ActivityRewardType.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        rewardActivitySetting = new QRewardActivitySetting("rewardActivitySetting");
    }
}
