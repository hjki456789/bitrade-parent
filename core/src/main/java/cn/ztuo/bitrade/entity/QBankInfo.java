package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QBankInfo extends BeanPath<BankInfo>
{
    private static final long serialVersionUID = 1597417069L;
    public static final QBankInfo bankInfo;
    public final StringPath bank;
    public final StringPath branch;
    public final StringPath cardNo;
    
    public QBankInfo(final String variable) {
        super((Class)BankInfo.class, PathMetadataFactory.forVariable(variable));
        this.bank = this.createString("bank");
        this.branch = this.createString("branch");
        this.cardNo = this.createString("cardNo");
    }
    
    public QBankInfo(final Path<? extends BankInfo> path) {
        super(path.getType(), path.getMetadata());
        this.bank = this.createString("bank");
        this.branch = this.createString("branch");
        this.cardNo = this.createString("cardNo");
    }
    
    public QBankInfo(final PathMetadata metadata) {
        super((Class)BankInfo.class, metadata);
        this.bank = this.createString("bank");
        this.branch = this.createString("branch");
        this.cardNo = this.createString("cardNo");
    }
    
    static {
        bankInfo = new QBankInfo("bankInfo");
    }
}
