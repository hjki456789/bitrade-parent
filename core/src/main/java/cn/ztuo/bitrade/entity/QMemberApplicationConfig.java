package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QMemberApplicationConfig extends EntityPathBase<MemberApplicationConfig>
{
    private static final long serialVersionUID = -1558766219L;
    public static final QMemberApplicationConfig memberApplicationConfig;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> promotionOn;
    public final EnumPath<BooleanEnum> rechargeCoinOn;
    public final EnumPath<BooleanEnum> transactionOn;
    public final EnumPath<BooleanEnum> withdrawCoinOn;
    
    public QMemberApplicationConfig(final String variable) {
        super((Class)MemberApplicationConfig.class, PathMetadataFactory.forVariable(variable));
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.promotionOn = (EnumPath<BooleanEnum>)this.createEnum("promotionOn", (Class)BooleanEnum.class);
        this.rechargeCoinOn = (EnumPath<BooleanEnum>)this.createEnum("rechargeCoinOn", (Class)BooleanEnum.class);
        this.transactionOn = (EnumPath<BooleanEnum>)this.createEnum("transactionOn", (Class)BooleanEnum.class);
        this.withdrawCoinOn = (EnumPath<BooleanEnum>)this.createEnum("withdrawCoinOn", (Class)BooleanEnum.class);
    }
    
    public QMemberApplicationConfig(final Path<? extends MemberApplicationConfig> path) {
        super(path.getType(), path.getMetadata());
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.promotionOn = (EnumPath<BooleanEnum>)this.createEnum("promotionOn", (Class)BooleanEnum.class);
        this.rechargeCoinOn = (EnumPath<BooleanEnum>)this.createEnum("rechargeCoinOn", (Class)BooleanEnum.class);
        this.transactionOn = (EnumPath<BooleanEnum>)this.createEnum("transactionOn", (Class)BooleanEnum.class);
        this.withdrawCoinOn = (EnumPath<BooleanEnum>)this.createEnum("withdrawCoinOn", (Class)BooleanEnum.class);
    }
    
    public QMemberApplicationConfig(final PathMetadata metadata) {
        super((Class)MemberApplicationConfig.class, metadata);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.promotionOn = (EnumPath<BooleanEnum>)this.createEnum("promotionOn", (Class)BooleanEnum.class);
        this.rechargeCoinOn = (EnumPath<BooleanEnum>)this.createEnum("rechargeCoinOn", (Class)BooleanEnum.class);
        this.transactionOn = (EnumPath<BooleanEnum>)this.createEnum("transactionOn", (Class)BooleanEnum.class);
        this.withdrawCoinOn = (EnumPath<BooleanEnum>)this.createEnum("withdrawCoinOn", (Class)BooleanEnum.class);
    }
    
    static {
        memberApplicationConfig = new QMemberApplicationConfig("memberApplicationConfig");
    }
}
