package cn.ztuo.bitrade.dto;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.*;

public class QSmsDTO extends EntityPathBase<SmsDTO>
{
    private static final long serialVersionUID = 390406717L;
    public static final QSmsDTO smsDTO;
    public final NumberPath<Long> id;
    public final StringPath keyId;
    public final StringPath keySecret;
    public final StringPath signId;
    public final StringPath smsName;
    public final StringPath smsStatus;
    public final StringPath templateId;
    
    public QSmsDTO(final String variable) {
        super((Class)SmsDTO.class, PathMetadataFactory.forVariable(variable));
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.keyId = this.createString("keyId");
        this.keySecret = this.createString("keySecret");
        this.signId = this.createString("signId");
        this.smsName = this.createString("smsName");
        this.smsStatus = this.createString("smsStatus");
        this.templateId = this.createString("templateId");
    }
    
    public QSmsDTO(final Path<? extends SmsDTO> path) {
        super(path.getType(), path.getMetadata());
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.keyId = this.createString("keyId");
        this.keySecret = this.createString("keySecret");
        this.signId = this.createString("signId");
        this.smsName = this.createString("smsName");
        this.smsStatus = this.createString("smsStatus");
        this.templateId = this.createString("templateId");
    }
    
    public QSmsDTO(final PathMetadata metadata) {
        super((Class)SmsDTO.class, metadata);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.keyId = this.createString("keyId");
        this.keySecret = this.createString("keySecret");
        this.signId = this.createString("signId");
        this.smsName = this.createString("smsName");
        this.smsStatus = this.createString("smsStatus");
        this.templateId = this.createString("templateId");
    }
    
    static {
        smsDTO = new QSmsDTO("smsDTO");
    }
}
