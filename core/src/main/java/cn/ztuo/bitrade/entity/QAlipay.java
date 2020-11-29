package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QAlipay extends BeanPath<Alipay>
{
    private static final long serialVersionUID = -1585664915L;
    public static final QAlipay alipay;
    public final StringPath aliNo;
    public final StringPath qrCodeUrl;
    
    public QAlipay(final String variable) {
        super((Class)Alipay.class, PathMetadataFactory.forVariable(variable));
        this.aliNo = this.createString("aliNo");
        this.qrCodeUrl = this.createString("qrCodeUrl");
    }
    
    public QAlipay(final Path<? extends Alipay> path) {
        super(path.getType(), path.getMetadata());
        this.aliNo = this.createString("aliNo");
        this.qrCodeUrl = this.createString("qrCodeUrl");
    }
    
    public QAlipay(final PathMetadata metadata) {
        super((Class)Alipay.class, metadata);
        this.aliNo = this.createString("aliNo");
        this.qrCodeUrl = this.createString("qrCodeUrl");
    }
    
    static {
        alipay = new QAlipay("alipay");
    }
}
