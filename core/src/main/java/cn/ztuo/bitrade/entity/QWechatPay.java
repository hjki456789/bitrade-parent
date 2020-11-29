package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QWechatPay extends BeanPath<WechatPay>
{
    private static final long serialVersionUID = 118586015L;
    public static final QWechatPay wechatPay;
    public final StringPath qrWeCodeUrl;
    public final StringPath wechat;
    
    public QWechatPay(final String variable) {
        super((Class)WechatPay.class, PathMetadataFactory.forVariable(variable));
        this.qrWeCodeUrl = this.createString("qrWeCodeUrl");
        this.wechat = this.createString("wechat");
    }
    
    public QWechatPay(final Path<? extends WechatPay> path) {
        super(path.getType(), path.getMetadata());
        this.qrWeCodeUrl = this.createString("qrWeCodeUrl");
        this.wechat = this.createString("wechat");
    }
    
    public QWechatPay(final PathMetadata metadata) {
        super((Class)WechatPay.class, metadata);
        this.qrWeCodeUrl = this.createString("qrWeCodeUrl");
        this.wechat = this.createString("wechat");
    }
    
    static {
        wechatPay = new QWechatPay("wechatPay");
    }
}
