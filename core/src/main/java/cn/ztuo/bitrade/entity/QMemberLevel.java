package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QMemberLevel extends EntityPathBase<MemberLevel>
{
    private static final long serialVersionUID = -485733177L;
    public static final QMemberLevel memberLevel;
    public final NumberPath<Long> id;
    public final BooleanPath isDefault;
    public final StringPath name;
    public final StringPath remark;
    
    public QMemberLevel(final String variable) {
        super((Class)MemberLevel.class, PathMetadataFactory.forVariable(variable));
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isDefault = this.createBoolean("isDefault");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
    }
    
    public QMemberLevel(final Path<? extends MemberLevel> path) {
        super(path.getType(), path.getMetadata());
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isDefault = this.createBoolean("isDefault");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
    }
    
    public QMemberLevel(final PathMetadata metadata) {
        super((Class)MemberLevel.class, metadata);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.isDefault = this.createBoolean("isDefault");
        this.name = this.createString("name");
        this.remark = this.createString("remark");
    }
    
    static {
        memberLevel = new QMemberLevel("memberLevel");
    }
}
