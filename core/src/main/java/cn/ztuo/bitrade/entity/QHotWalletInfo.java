package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QHotWalletInfo extends EntityPathBase<HotWalletInfo>
{
    private static final long serialVersionUID = 1693211889L;
    public static final QHotWalletInfo hotWalletInfo;
    public final StringPath address;
    public final NumberPath<Integer> chainType;
    public final NumberPath<Long> id;
    public final StringPath privateKey;
    
    public QHotWalletInfo(final String variable) {
        super((Class)HotWalletInfo.class, PathMetadataFactory.forVariable(variable));
        this.address = this.createString("address");
        this.chainType = (NumberPath<Integer>)this.createNumber("chainType", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.privateKey = this.createString("privateKey");
    }
    
    public QHotWalletInfo(final Path<? extends HotWalletInfo> path) {
        super(path.getType(), path.getMetadata());
        this.address = this.createString("address");
        this.chainType = (NumberPath<Integer>)this.createNumber("chainType", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.privateKey = this.createString("privateKey");
    }
    
    public QHotWalletInfo(final PathMetadata metadata) {
        super((Class)HotWalletInfo.class, metadata);
        this.address = this.createString("address");
        this.chainType = (NumberPath<Integer>)this.createNumber("chainType", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.privateKey = this.createString("privateKey");
    }
    
    static {
        hotWalletInfo = new QHotWalletInfo("hotWalletInfo");
    }
}
