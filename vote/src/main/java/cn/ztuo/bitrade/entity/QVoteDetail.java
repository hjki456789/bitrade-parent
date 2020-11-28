package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import java.util.*;
import com.querydsl.core.types.*;

public class QVoteDetail extends EntityPathBase<VoteDetail>
{
    private static final long serialVersionUID = -964528002L;
    private static final PathInits INITS;
    public static final QVoteDetail voteDetail;
    public final NumberPath<BigDecimal> amount;
    public final DateTimePath<Date> createTime;
    public final NumberPath<Long> id;
    public final QPreCoin preCoin;
    public final NumberPath<Long> userId;
    public final QVote vote;
    public final NumberPath<Integer> voteAmount;

    public QVoteDetail(final String variable) {
        this(VoteDetail.class, PathMetadataFactory.forVariable(variable), QVoteDetail.INITS);
    }

    public QVoteDetail(final Path<? extends VoteDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QVoteDetail.INITS));
    }

    public QVoteDetail(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QVoteDetail.INITS));
    }

    public QVoteDetail(final PathMetadata metadata, final PathInits inits) {
        this(VoteDetail.class, metadata, inits);
    }

    public QVoteDetail(final Class<? extends VoteDetail> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.amount = (NumberPath<BigDecimal>)this.createNumber("amount", (Class)BigDecimal.class);
        this.createTime = (DateTimePath<Date>)this.createDateTime("createTime", (Class)Date.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.userId = (NumberPath<Long>)this.createNumber("userId", (Class)Long.class);
        this.voteAmount = (NumberPath<Integer>)this.createNumber("voteAmount", (Class)Integer.class);
        this.preCoin = (inits.isInitialized("preCoin") ? new QPreCoin(this.forProperty("preCoin"), inits.get("preCoin")) : null);
        this.vote = (inits.isInitialized("vote") ? new QVote(this.forProperty("vote")) : null);
    }

    static {
        INITS = PathInits.DIRECT2;
        voteDetail = new QVoteDetail("voteDetail");
    }
}
