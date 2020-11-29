package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QWebsiteInformation extends EntityPathBase<WebsiteInformation>
{
    private static final long serialVersionUID = -1202486476L;
    public static final QWebsiteInformation websiteInformation;
    public final StringPath addressIcon;
    public final StringPath contact;
    public final StringPath copyright;
    public final StringPath description;
    public final NumberPath<Long> id;
    public final StringPath keywords;
    public final StringPath logo;
    public final StringPath name;
    public final StringPath otherInformation;
    public final StringPath postcode;
    public final StringPath url;
    
    public QWebsiteInformation(final String variable) {
        super((Class)WebsiteInformation.class, PathMetadataFactory.forVariable(variable));
        this.addressIcon = this.createString("addressIcon");
        this.contact = this.createString("contact");
        this.copyright = this.createString("copyright");
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.keywords = this.createString("keywords");
        this.logo = this.createString("logo");
        this.name = this.createString("name");
        this.otherInformation = this.createString("otherInformation");
        this.postcode = this.createString("postcode");
        this.url = this.createString("url");
    }
    
    public QWebsiteInformation(final Path<? extends WebsiteInformation> path) {
        super(path.getType(), path.getMetadata());
        this.addressIcon = this.createString("addressIcon");
        this.contact = this.createString("contact");
        this.copyright = this.createString("copyright");
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.keywords = this.createString("keywords");
        this.logo = this.createString("logo");
        this.name = this.createString("name");
        this.otherInformation = this.createString("otherInformation");
        this.postcode = this.createString("postcode");
        this.url = this.createString("url");
    }
    
    public QWebsiteInformation(final PathMetadata metadata) {
        super((Class)WebsiteInformation.class, metadata);
        this.addressIcon = this.createString("addressIcon");
        this.contact = this.createString("contact");
        this.copyright = this.createString("copyright");
        this.description = this.createString("description");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.keywords = this.createString("keywords");
        this.logo = this.createString("logo");
        this.name = this.createString("name");
        this.otherInformation = this.createString("otherInformation");
        this.postcode = this.createString("postcode");
        this.url = this.createString("url");
    }
    
    static {
        websiteInformation = new QWebsiteInformation("websiteInformation");
    }
}
