package cn.ztuo.bitrade.vo;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QImportXmlVO extends BeanPath<ImportXmlVO> {
    private static final long serialVersionUID = 1840674302L;
    public static final QImportXmlVO importXmlVO;
    public final NumberPath<Double> amount;
    public final StringPath coinName;
    public final StringPath coinUnit;
    public final NumberPath<Long> memberId;
    public final StringPath memberName;
    public final StringPath phone;

    public QImportXmlVO(final String variable) {
        super((Class) ImportXmlVO.class, PathMetadataFactory.forVariable(variable));
        this.amount = (NumberPath<Double>) this.createNumber("amount", (Class) Double.class);
        this.coinName = this.createString("coinName");
        this.coinUnit = this.createString("coinUnit");
        this.memberId = (NumberPath<Long>) this.createNumber("memberId", (Class) Long.class);
        this.memberName = this.createString("memberName");
        this.phone = this.createString("phone");
    }

    public QImportXmlVO(final Path<? extends ImportXmlVO> path) {
        super(path.getType(), path.getMetadata());
        this.amount = (NumberPath<Double>) this.createNumber("amount", (Class) Double.class);
        this.coinName = this.createString("coinName");
        this.coinUnit = this.createString("coinUnit");
        this.memberId = (NumberPath<Long>) this.createNumber("memberId", (Class) Long.class);
        this.memberName = this.createString("memberName");
        this.phone = this.createString("phone");
    }

    public QImportXmlVO(final PathMetadata metadata) {
        super((Class) ImportXmlVO.class, metadata);
        this.amount = (NumberPath<Double>) this.createNumber("amount", (Class) Double.class);
        this.coinName = this.createString("coinName");
        this.coinUnit = this.createString("coinUnit");
        this.memberId = (NumberPath<Long>) this.createNumber("memberId", (Class) Long.class);
        this.memberName = this.createString("memberName");
        this.phone = this.createString("phone");
    }

    static {
        importXmlVO = new QImportXmlVO("importXmlVO");
    }
}
