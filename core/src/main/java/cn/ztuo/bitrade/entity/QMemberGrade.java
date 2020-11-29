package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QMemberGrade extends EntityPathBase<MemberGrade>
{
    private static final long serialVersionUID = -489983718L;
    public static final QMemberGrade memberGrade;
    public final NumberPath<BigDecimal> commissionRate;
    public final NumberPath<Integer> dayWithdrawCount;
    public final NumberPath<Long> directNumber;
    public final NumberPath<BigDecimal> eightGenerationRate;
    public final NumberPath<BigDecimal> exchangeFeeRate;
    public final NumberPath<BigDecimal> feeCommissionRate;
    public final NumberPath<BigDecimal> fiveGenerationRate;
    public final NumberPath<BigDecimal> fixInvestAmount;
    public final NumberPath<BigDecimal> fourGenerationRate;
    public final NumberPath<Integer> gradeBound;
    public final StringPath gradeCode;
    public final StringPath gradeName;
    public final NumberPath<Long> id;
    public final NumberPath<BigDecimal> nineGenerationRate;
    public final NumberPath<BigDecimal> oneGenerationRate;
    public final NumberPath<Integer> peersNeed;
    public final NumberPath<BigDecimal> sevenGenerationRate;
    public final NumberPath<BigDecimal> sixGenerationRate;
    public final NumberPath<BigDecimal> teamInvestAmount;
    public final NumberPath<Long> teamNumber;
    public final NumberPath<BigDecimal> tenGenerationRate;
    public final NumberPath<BigDecimal> threeGenerationRate;
    public final NumberPath<BigDecimal> twoGenerationRate;
    public final NumberPath<BigDecimal> withdrawCoinAmount;
    
    public QMemberGrade(final String variable) {
        super((Class)MemberGrade.class, PathMetadataFactory.forVariable(variable));
        this.commissionRate = (NumberPath<BigDecimal>)this.createNumber("commissionRate", (Class)BigDecimal.class);
        this.dayWithdrawCount = (NumberPath<Integer>)this.createNumber("dayWithdrawCount", (Class)Integer.class);
        this.directNumber = (NumberPath<Long>)this.createNumber("directNumber", (Class)Long.class);
        this.eightGenerationRate = (NumberPath<BigDecimal>)this.createNumber("eightGenerationRate", (Class)BigDecimal.class);
        this.exchangeFeeRate = (NumberPath<BigDecimal>)this.createNumber("exchangeFeeRate", (Class)BigDecimal.class);
        this.feeCommissionRate = (NumberPath<BigDecimal>)this.createNumber("feeCommissionRate", (Class)BigDecimal.class);
        this.fiveGenerationRate = (NumberPath<BigDecimal>)this.createNumber("fiveGenerationRate", (Class)BigDecimal.class);
        this.fixInvestAmount = (NumberPath<BigDecimal>)this.createNumber("fixInvestAmount", (Class)BigDecimal.class);
        this.fourGenerationRate = (NumberPath<BigDecimal>)this.createNumber("fourGenerationRate", (Class)BigDecimal.class);
        this.gradeBound = (NumberPath<Integer>)this.createNumber("gradeBound", (Class)Integer.class);
        this.gradeCode = this.createString("gradeCode");
        this.gradeName = this.createString("gradeName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.nineGenerationRate = (NumberPath<BigDecimal>)this.createNumber("nineGenerationRate", (Class)BigDecimal.class);
        this.oneGenerationRate = (NumberPath<BigDecimal>)this.createNumber("oneGenerationRate", (Class)BigDecimal.class);
        this.peersNeed = (NumberPath<Integer>)this.createNumber("peersNeed", (Class)Integer.class);
        this.sevenGenerationRate = (NumberPath<BigDecimal>)this.createNumber("sevenGenerationRate", (Class)BigDecimal.class);
        this.sixGenerationRate = (NumberPath<BigDecimal>)this.createNumber("sixGenerationRate", (Class)BigDecimal.class);
        this.teamInvestAmount = (NumberPath<BigDecimal>)this.createNumber("teamInvestAmount", (Class)BigDecimal.class);
        this.teamNumber = (NumberPath<Long>)this.createNumber("teamNumber", (Class)Long.class);
        this.tenGenerationRate = (NumberPath<BigDecimal>)this.createNumber("tenGenerationRate", (Class)BigDecimal.class);
        this.threeGenerationRate = (NumberPath<BigDecimal>)this.createNumber("threeGenerationRate", (Class)BigDecimal.class);
        this.twoGenerationRate = (NumberPath<BigDecimal>)this.createNumber("twoGenerationRate", (Class)BigDecimal.class);
        this.withdrawCoinAmount = (NumberPath<BigDecimal>)this.createNumber("withdrawCoinAmount", (Class)BigDecimal.class);
    }
    
    public QMemberGrade(final Path<? extends MemberGrade> path) {
        super(path.getType(), path.getMetadata());
        this.commissionRate = (NumberPath<BigDecimal>)this.createNumber("commissionRate", (Class)BigDecimal.class);
        this.dayWithdrawCount = (NumberPath<Integer>)this.createNumber("dayWithdrawCount", (Class)Integer.class);
        this.directNumber = (NumberPath<Long>)this.createNumber("directNumber", (Class)Long.class);
        this.eightGenerationRate = (NumberPath<BigDecimal>)this.createNumber("eightGenerationRate", (Class)BigDecimal.class);
        this.exchangeFeeRate = (NumberPath<BigDecimal>)this.createNumber("exchangeFeeRate", (Class)BigDecimal.class);
        this.feeCommissionRate = (NumberPath<BigDecimal>)this.createNumber("feeCommissionRate", (Class)BigDecimal.class);
        this.fiveGenerationRate = (NumberPath<BigDecimal>)this.createNumber("fiveGenerationRate", (Class)BigDecimal.class);
        this.fixInvestAmount = (NumberPath<BigDecimal>)this.createNumber("fixInvestAmount", (Class)BigDecimal.class);
        this.fourGenerationRate = (NumberPath<BigDecimal>)this.createNumber("fourGenerationRate", (Class)BigDecimal.class);
        this.gradeBound = (NumberPath<Integer>)this.createNumber("gradeBound", (Class)Integer.class);
        this.gradeCode = this.createString("gradeCode");
        this.gradeName = this.createString("gradeName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.nineGenerationRate = (NumberPath<BigDecimal>)this.createNumber("nineGenerationRate", (Class)BigDecimal.class);
        this.oneGenerationRate = (NumberPath<BigDecimal>)this.createNumber("oneGenerationRate", (Class)BigDecimal.class);
        this.peersNeed = (NumberPath<Integer>)this.createNumber("peersNeed", (Class)Integer.class);
        this.sevenGenerationRate = (NumberPath<BigDecimal>)this.createNumber("sevenGenerationRate", (Class)BigDecimal.class);
        this.sixGenerationRate = (NumberPath<BigDecimal>)this.createNumber("sixGenerationRate", (Class)BigDecimal.class);
        this.teamInvestAmount = (NumberPath<BigDecimal>)this.createNumber("teamInvestAmount", (Class)BigDecimal.class);
        this.teamNumber = (NumberPath<Long>)this.createNumber("teamNumber", (Class)Long.class);
        this.tenGenerationRate = (NumberPath<BigDecimal>)this.createNumber("tenGenerationRate", (Class)BigDecimal.class);
        this.threeGenerationRate = (NumberPath<BigDecimal>)this.createNumber("threeGenerationRate", (Class)BigDecimal.class);
        this.twoGenerationRate = (NumberPath<BigDecimal>)this.createNumber("twoGenerationRate", (Class)BigDecimal.class);
        this.withdrawCoinAmount = (NumberPath<BigDecimal>)this.createNumber("withdrawCoinAmount", (Class)BigDecimal.class);
    }
    
    public QMemberGrade(final PathMetadata metadata) {
        super((Class)MemberGrade.class, metadata);
        this.commissionRate = (NumberPath<BigDecimal>)this.createNumber("commissionRate", (Class)BigDecimal.class);
        this.dayWithdrawCount = (NumberPath<Integer>)this.createNumber("dayWithdrawCount", (Class)Integer.class);
        this.directNumber = (NumberPath<Long>)this.createNumber("directNumber", (Class)Long.class);
        this.eightGenerationRate = (NumberPath<BigDecimal>)this.createNumber("eightGenerationRate", (Class)BigDecimal.class);
        this.exchangeFeeRate = (NumberPath<BigDecimal>)this.createNumber("exchangeFeeRate", (Class)BigDecimal.class);
        this.feeCommissionRate = (NumberPath<BigDecimal>)this.createNumber("feeCommissionRate", (Class)BigDecimal.class);
        this.fiveGenerationRate = (NumberPath<BigDecimal>)this.createNumber("fiveGenerationRate", (Class)BigDecimal.class);
        this.fixInvestAmount = (NumberPath<BigDecimal>)this.createNumber("fixInvestAmount", (Class)BigDecimal.class);
        this.fourGenerationRate = (NumberPath<BigDecimal>)this.createNumber("fourGenerationRate", (Class)BigDecimal.class);
        this.gradeBound = (NumberPath<Integer>)this.createNumber("gradeBound", (Class)Integer.class);
        this.gradeCode = this.createString("gradeCode");
        this.gradeName = this.createString("gradeName");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.nineGenerationRate = (NumberPath<BigDecimal>)this.createNumber("nineGenerationRate", (Class)BigDecimal.class);
        this.oneGenerationRate = (NumberPath<BigDecimal>)this.createNumber("oneGenerationRate", (Class)BigDecimal.class);
        this.peersNeed = (NumberPath<Integer>)this.createNumber("peersNeed", (Class)Integer.class);
        this.sevenGenerationRate = (NumberPath<BigDecimal>)this.createNumber("sevenGenerationRate", (Class)BigDecimal.class);
        this.sixGenerationRate = (NumberPath<BigDecimal>)this.createNumber("sixGenerationRate", (Class)BigDecimal.class);
        this.teamInvestAmount = (NumberPath<BigDecimal>)this.createNumber("teamInvestAmount", (Class)BigDecimal.class);
        this.teamNumber = (NumberPath<Long>)this.createNumber("teamNumber", (Class)Long.class);
        this.tenGenerationRate = (NumberPath<BigDecimal>)this.createNumber("tenGenerationRate", (Class)BigDecimal.class);
        this.threeGenerationRate = (NumberPath<BigDecimal>)this.createNumber("threeGenerationRate", (Class)BigDecimal.class);
        this.twoGenerationRate = (NumberPath<BigDecimal>)this.createNumber("twoGenerationRate", (Class)BigDecimal.class);
        this.withdrawCoinAmount = (NumberPath<BigDecimal>)this.createNumber("withdrawCoinAmount", (Class)BigDecimal.class);
    }
    
    static {
        memberGrade = new QMemberGrade("memberGrade");
    }
}
