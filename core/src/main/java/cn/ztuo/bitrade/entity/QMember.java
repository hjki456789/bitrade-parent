package cn.ztuo.bitrade.entity;

import java.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QMember extends EntityPathBase<Member>
{
    private static final long serialVersionUID = -1248473923L;
    private static final PathInits INITS;
    public static final QMember member;
    public final QAlipay alipay;
    public final NumberPath<Integer> appealSuccessTimes;
    public final NumberPath<Integer> appealTimes;
    public final DateTimePath<Date> applicationTime;
    public final StringPath avatar;
    public final QBankInfo bankInfo;
    public final DateTimePath<Date> certifiedBusinessApplyTime;
    public final DateTimePath<Date> certifiedBusinessCheckTime;
    public final EnumPath<CertifiedBusinessStatus> certifiedBusinessStatus;
    public final NumberPath<Long> channelId;
    public final QCountry country;
    public final StringPath email;
    public final NumberPath<Integer> firstLevel;
    public final NumberPath<Long> generalizeTotal;
    public final DateTimePath<Date> googleDate;
    public final StringPath googleKey;
    public final NumberPath<Integer> googleState;
    public final NumberPath<Long> id;
    public final StringPath idNumber;
    public final NumberPath<Integer> ifFixMemberGrade;
    public final EnumPath<IfNodeType> ifNode;
    public final NumberPath<Long> integration;
    public final NumberPath<Long> inviterId;
    public final NumberPath<Long> inviterParentId;
    public final EnumPath<BooleanEnum> isChannel;
    public final StringPath jyPassword;
    public final NumberPath<Integer> kycStatus;
    public final DateTimePath<Date> lastLoginTime;
    public final QLocation location;
    public final NumberPath<Integer> loginCount;
    public final EnumPath<BooleanEnum> loginLock;
    public final StringPath margin;
    public final NumberPath<Long> memberGradeId;
    public final EnumPath<MemberLevelEnum> memberLevel;
    public final StringPath mobilePhone;
    public final StringPath password;
    public final NumberPath<Integer> platType;
    public final StringPath promotionCode;
    public final EnumPath<BooleanEnum> publishAdvertise;
    public final StringPath realName;
    public final EnumPath<RealNameStatus> realNameStatus;
    public final DateTimePath<Date> registrationTime;
    public final StringPath salt;
    public final NumberPath<Integer> secondLevel;
    public final BooleanPath signInAbility;
    public final EnumPath<CommonStatus> status;
    public final NumberPath<Integer> thirdLevel;
    public final StringPath token;
    public final DateTimePath<Date> tokenExpireTime;
    public final NumberPath<Integer> transactions;
    public final EnumPath<BooleanEnum> transactionStatus;
    public final DateTimePath<Date> transactionTime;
    public final StringPath username;
    public final EnumPath<UserType> userType;
    public final QWechatPay wechatPay;
    
    public QMember(final String variable) {
        this(Member.class, PathMetadataFactory.forVariable(variable), QMember.INITS);
    }
    
    public QMember(final Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMember.INITS));
    }
    
    public QMember(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMember.INITS));
    }
    
    public QMember(final PathMetadata metadata, final PathInits inits) {
        this(Member.class, metadata, inits);
    }
    
    public QMember(final Class<? extends Member> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.appealSuccessTimes = (NumberPath<Integer>)this.createNumber("appealSuccessTimes", (Class)Integer.class);
        this.appealTimes = (NumberPath<Integer>)this.createNumber("appealTimes", (Class)Integer.class);
        this.applicationTime = (DateTimePath<Date>)this.createDateTime("applicationTime", (Class)Date.class);
        this.avatar = this.createString("avatar");
        this.certifiedBusinessApplyTime = (DateTimePath<Date>)this.createDateTime("certifiedBusinessApplyTime", (Class)Date.class);
        this.certifiedBusinessCheckTime = (DateTimePath<Date>)this.createDateTime("certifiedBusinessCheckTime", (Class)Date.class);
        this.certifiedBusinessStatus = (EnumPath<CertifiedBusinessStatus>)this.createEnum("certifiedBusinessStatus", (Class)CertifiedBusinessStatus.class);
        this.channelId = (NumberPath<Long>)this.createNumber("channelId", (Class)Long.class);
        this.email = this.createString("email");
        this.firstLevel = (NumberPath<Integer>)this.createNumber("firstLevel", (Class)Integer.class);
        this.generalizeTotal = (NumberPath<Long>)this.createNumber("generalizeTotal", (Class)Long.class);
        this.googleDate = (DateTimePath<Date>)this.createDateTime("googleDate", (Class)Date.class);
        this.googleKey = this.createString("googleKey");
        this.googleState = (NumberPath<Integer>)this.createNumber("googleState", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.idNumber = this.createString("idNumber");
        this.ifFixMemberGrade = (NumberPath<Integer>)this.createNumber("ifFixMemberGrade", (Class)Integer.class);
        this.ifNode = (EnumPath<IfNodeType>)this.createEnum("ifNode", (Class)IfNodeType.class);
        this.integration = (NumberPath<Long>)this.createNumber("integration", (Class)Long.class);
        this.inviterId = (NumberPath<Long>)this.createNumber("inviterId", (Class)Long.class);
        this.inviterParentId = (NumberPath<Long>)this.createNumber("inviterParentId", (Class)Long.class);
        this.isChannel = (EnumPath<BooleanEnum>)this.createEnum("isChannel", (Class)BooleanEnum.class);
        this.jyPassword = this.createString("jyPassword");
        this.kycStatus = (NumberPath<Integer>)this.createNumber("kycStatus", (Class)Integer.class);
        this.lastLoginTime = (DateTimePath<Date>)this.createDateTime("lastLoginTime", (Class)Date.class);
        this.loginCount = (NumberPath<Integer>)this.createNumber("loginCount", (Class)Integer.class);
        this.loginLock = (EnumPath<BooleanEnum>)this.createEnum("loginLock", (Class)BooleanEnum.class);
        this.margin = this.createString("margin");
        this.memberGradeId = (NumberPath<Long>)this.createNumber("memberGradeId", (Class)Long.class);
        this.memberLevel = (EnumPath<MemberLevelEnum>)this.createEnum("memberLevel", (Class)MemberLevelEnum.class);
        this.mobilePhone = this.createString("mobilePhone");
        this.password = this.createString("password");
        this.platType = (NumberPath<Integer>)this.createNumber("platType", (Class)Integer.class);
        this.promotionCode = this.createString("promotionCode");
        this.publishAdvertise = (EnumPath<BooleanEnum>)this.createEnum("publishAdvertise", (Class)BooleanEnum.class);
        this.realName = this.createString("realName");
        this.realNameStatus = (EnumPath<RealNameStatus>)this.createEnum("realNameStatus", (Class)RealNameStatus.class);
        this.registrationTime = (DateTimePath<Date>)this.createDateTime("registrationTime", (Class)Date.class);
        this.salt = this.createString("salt");
        this.secondLevel = (NumberPath<Integer>)this.createNumber("secondLevel", (Class)Integer.class);
        this.signInAbility = this.createBoolean("signInAbility");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.thirdLevel = (NumberPath<Integer>)this.createNumber("thirdLevel", (Class)Integer.class);
        this.token = this.createString("token");
        this.tokenExpireTime = (DateTimePath<Date>)this.createDateTime("tokenExpireTime", (Class)Date.class);
        this.transactions = (NumberPath<Integer>)this.createNumber("transactions", (Class)Integer.class);
        this.transactionStatus = (EnumPath<BooleanEnum>)this.createEnum("transactionStatus", (Class)BooleanEnum.class);
        this.transactionTime = (DateTimePath<Date>)this.createDateTime("transactionTime", (Class)Date.class);
        this.username = this.createString("username");
        this.userType = (EnumPath<UserType>)this.createEnum("userType", (Class)UserType.class);
        this.alipay = (inits.isInitialized("alipay") ? new QAlipay(this.forProperty("alipay")) : null);
        this.bankInfo = (inits.isInitialized("bankInfo") ? new QBankInfo(this.forProperty("bankInfo")) : null);
        this.country = (inits.isInitialized("country") ? new QCountry(this.forProperty("country")) : null);
        this.location = (inits.isInitialized("location") ? new QLocation(this.forProperty("location")) : null);
        this.wechatPay = (inits.isInitialized("wechatPay") ? new QWechatPay(this.forProperty("wechatPay")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        member = new QMember("member1");
    }
}
