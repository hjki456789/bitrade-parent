package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.*;
import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QMemberAccountOperateRecord extends EntityPathBase<MemberAccountOperateRecord>
{
    private static final long serialVersionUID = -592394715L;
    public static final QMemberAccountOperateRecord memberAccountOperateRecord;
    public final EnumPath<AccountTypeEnum> accountType;
    public final StringPath adminUserId;
    public final EnumPath<BalanceTypeEnum> balanceType;
    public final NumberPath<BigDecimal> changeAmount;
    public final StringPath coin;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final NumberPath<Long> memberId;
    public final NumberPath<Long> sequence;
    
    public QMemberAccountOperateRecord(final String variable) {
        super((Class)MemberAccountOperateRecord.class, PathMetadataFactory.forVariable(variable));
        this.accountType = (EnumPath<AccountTypeEnum>)this.createEnum("accountType", (Class)AccountTypeEnum.class);
        this.adminUserId = this.createString("adminUserId");
        this.balanceType = (EnumPath<BalanceTypeEnum>)this.createEnum("balanceType", (Class)BalanceTypeEnum.class);
        this.changeAmount = (NumberPath<BigDecimal>)this.createNumber("changeAmount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QMemberAccountOperateRecord(final Path<? extends MemberAccountOperateRecord> path) {
        super(path.getType(), path.getMetadata());
        this.accountType = (EnumPath<AccountTypeEnum>)this.createEnum("accountType", (Class)AccountTypeEnum.class);
        this.adminUserId = this.createString("adminUserId");
        this.balanceType = (EnumPath<BalanceTypeEnum>)this.createEnum("balanceType", (Class)BalanceTypeEnum.class);
        this.changeAmount = (NumberPath<BigDecimal>)this.createNumber("changeAmount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    public QMemberAccountOperateRecord(final PathMetadata metadata) {
        super((Class)MemberAccountOperateRecord.class, metadata);
        this.accountType = (EnumPath<AccountTypeEnum>)this.createEnum("accountType", (Class)AccountTypeEnum.class);
        this.adminUserId = this.createString("adminUserId");
        this.balanceType = (EnumPath<BalanceTypeEnum>)this.createEnum("balanceType", (Class)BalanceTypeEnum.class);
        this.changeAmount = (NumberPath<BigDecimal>)this.createNumber("changeAmount", (Class)BigDecimal.class);
        this.coin = this.createString("coin");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.memberId = (NumberPath<Long>)this.createNumber("memberId", (Class)Long.class);
        this.sequence = (NumberPath<Long>)this.createNumber("sequence", (Class)Long.class);
    }
    
    static {
        memberAccountOperateRecord = new QMemberAccountOperateRecord("memberAccountOperateRecord");
    }
}
