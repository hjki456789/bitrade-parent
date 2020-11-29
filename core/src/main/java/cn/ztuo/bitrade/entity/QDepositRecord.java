package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QDepositRecord extends EntityPathBase<DepositRecord>
{
    private static final long serialVersionUID = -1402330868L;
    private static final PathInits INITS;
    public static final QDepositRecord depositRecord;
    public final NumberPath<BigDecimal> amount;
    public final QCoin coin;
    public final StringPath id;
    public final QMember member;
    public final EnumPath<DepositStatusEnum> status;
    
    public QDepositRecord(final String variable) {
        this(DepositRecord.class, PathMetadataFactory.forVariable(variable), QDepositRecord.INITS);
    }
    
    public QDepositRecord(final Path<? extends DepositRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QDepositRecord.INITS));
    }
    
    public QDepositRecord(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QDepositRecord.INITS));
    }
    
    public QDepositRecord(final PathMetadata metadata, final PathInits inits) {
        this(DepositRecord.class, metadata, inits);
    }
    
    public QDepositRecord(final Class<? extends DepositRecord> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.status = (EnumPath<DepositStatusEnum>)this.createEnum("status", (Class)DepositStatusEnum.class);
        this.coin = (inits.isInitialized("coin") ? new QCoin(this.forProperty("coin")) : null);
        this.member = (inits.isInitialized("member") ? new QMember(this.forProperty("member"), inits.get("member")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        depositRecord = new QDepositRecord("depositRecord");
    }
}
