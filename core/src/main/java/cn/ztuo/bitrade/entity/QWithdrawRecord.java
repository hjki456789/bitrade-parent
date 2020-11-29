package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QWithdrawRecord extends EntityPathBase<WithdrawRecord>
{
    private static final long serialVersionUID = 540467998L;
    private static final PathInits INITS;
    public static final QWithdrawRecord withdrawRecord;
    public final StringPath address;
    public final QAdmin admin;
    public final NumberPath<BigDecimal> arrivedAmount;
    public final EnumPath<BooleanEnum> canAutoWithdraw;
    public final QCoin coin;
    public final DateTimePath<Date> createTime;
    public final DateTimePath<Date> dealTime;
    public final NumberPath<BigDecimal> fee;
    public final NumberPath<Long> id;
    public final EnumPath<BooleanEnum> isAuto;
    public final NumberPath<Long> memberId;
    public final StringPath remark;
    public final EnumPath<WithdrawStatus> status;
    public final NumberPath<BigDecimal> totalAmount;
    public final StringPath transactionNumber;
    public final StringPath txHash;
    
    public QWithdrawRecord(final String variable) {
        this(WithdrawRecord.class, PathMetadataFactory.forVariable(variable), QWithdrawRecord.INITS);
    }
    
    public QWithdrawRecord(final Path<? extends WithdrawRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QWithdrawRecord.INITS));
    }
    
    public QWithdrawRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QWithdrawRecord.INITS));
    }
    
    public QWithdrawRecord(final PathMetadata metadata, final PathInits inits) {
        this(WithdrawRecord.class, metadata, inits);
    }
    
    public QWithdrawRecord(final Class<? extends WithdrawRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.address = this.createString("address");
        this.arrivedAmount = (NumberPath<BigDecimal>)this.createNumber("arrivedAmount", (Class)BigDecimal.class);
        this.canAutoWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canAutoWithdraw", (Class)BooleanEnum.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.dealTime = (DateTimePath<Date>)this.createDateTime("dealTime", (Class)Date.class);
        this.fee = (NumberPath<BigDecimal>)this.createNumber("fee", (Class)BigDecimal.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isAuto = (EnumPath<BooleanEnum>)this.createEnum("isAuto", (Class)BooleanEnum.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.remark = this.createString("remark");
        this.status = (EnumPath<WithdrawStatus>)this.createEnum("status", (Class)WithdrawStatus.class);
        this.totalAmount = (NumberPath<BigDecimal>)this.createNumber("totalAmount", (Class)BigDecimal.class);
        this.transactionNumber = this.createString("transactionNumber");
        this.txHash = this.createString("txHash");
        this.admin = (inits.isInitialized("admin") ? new QAdmin(this.forProperty("admin"), inits.get("admin")) : null);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        withdrawRecord = new QWithdrawRecord("withdrawRecord");
    }
}
