package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QAnnouncement extends EntityPathBase<Announcement>
{
    private static final long serialVersionUID = -1414780630L;
    public static final QAnnouncement announcement;
    public final StringPath content;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final StringPath imgUrl;
    public final BooleanPath isShow;
    public final StringPath isTop;
    public final NumberPath<Integer> sort;
    public final StringPath title;
    
    public QAnnouncement(final String variable) {
        super((Class)Announcement.class, PathMetadataFactory.forVariable(variable));
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.isShow = this.createBoolean("isShow");
        this.isTop = this.createString("isTop");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.title = this.createString("title");
    }
    
    public QAnnouncement(final Path<? extends Announcement> path) {
        super(path.getType(), path.getMetadata());
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.isShow = this.createBoolean("isShow");
        this.isTop = this.createString("isTop");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.title = this.createString("title");
    }
    
    public QAnnouncement(final PathMetadata metadata) {
        super((Class)Announcement.class, metadata);
        this.content = this.createString("content");
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.imgUrl = this.createString("imgUrl");
        this.isShow = this.createBoolean("isShow");
        this.isTop = this.createString("isTop");
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.title = this.createString("title");
    }
    
    static {
        announcement = new QAnnouncement("announcement");
    }
}
