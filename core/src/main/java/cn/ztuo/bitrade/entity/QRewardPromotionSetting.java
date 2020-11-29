package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QRewardPromotionSetting extends EntityPathBase<RewardPromotionSetting>
{
    private static final long serialVersionUID = 190098847L;
    private static final PathInits INITS;
    public static final QRewardPromotionSetting rewardPromotionSetting;
    public final QAdmin admin;
    public final QCoin coin;
    public final NumberPath<Integer> effectiveTime;
    public final NumberPath<Long> id;
    public final StringPath info;
    public final EnumPath<BooleanEnum> status;
    public final EnumPath<PromotionRewardType> type;
    public final DateTimePath<Date> updateTime;
    
    public QRewardPromotionSetting(final String variable) {
        this(RewardPromotionSetting.class, PathMetadataFactory.forVariable(variable), QRewardPromotionSetting.INITS);
    }
    
    public QRewardPromotionSetting(final Path<? extends RewardPromotionSetting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QRewardPromotionSetting.INITS));
    }
    
    public QRewardPromotionSetting(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QRewardPromotionSetting.INITS));
    }
    
    public QRewardPromotionSetting(final PathMetadata metadata, final PathInits inits) {
        this(RewardPromotionSetting.class, metadata, inits);
    }
    
    public QRewardPromotionSetting(final Class<? extends RewardPromotionSetting> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.effectiveTime = (NumberPath<Integer>)this.createNumber("effectiveTime", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.info = this.createString("info");
        this.status = (EnumPath<BooleanEnum>)this.createEnum("status", (Class)BooleanEnum.class);
        this.type = (EnumPath<PromotionRewardType>)this.createEnum("type", (Class)PromotionRewardType.class);
        this.updateTime = (DateTimePath<Date>)this.createDateTime("updateTime", (Class)Date.class);
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        rewardPromotionSetting = new QRewardPromotionSetting("rewardPromotionSetting");
    }
}
