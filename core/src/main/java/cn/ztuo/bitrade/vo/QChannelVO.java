package cn.ztuo.bitrade.vo;

import com.querydsl.core.types.dsl.*;

import java.math.*;

import com.querydsl.core.types.*;

public class QChannelVO extends BeanPath<ChannelVO> {
    private static final long serialVersionUID = 604030287L;
    public static final QChannelVO channelVO;
    public final NumberPath<Integer> channelCount;
    public final NumberPath<BigDecimal> channelReward;
    public final NumberPath<Long> memberId;

    public QChannelVO(final String variable) {
        super((Class) ChannelVO.class, PathMetadataFactory.forVariable(variable));
        this.channelCount = (NumberPath<Integer>) this.createNumber("channelCount", (Class) Integer.class);
        this.channelReward = (NumberPath<BigDecimal>) this.createNumber("channelReward", (Class) BigDecimal.class);
        this.memberId = (NumberPath<Long>) this.createNumber("memberId", (Class) Long.class);
    }

    public QChannelVO(final Path<? extends ChannelVO> path) {
        super(path.getType(), path.getMetadata());
        this.channelCount = (NumberPath<Integer>) this.createNumber("channelCount", (Class) Integer.class);
        this.channelReward = (NumberPath<BigDecimal>) this.createNumber("channelReward", (Class) BigDecimal.class);
        this.memberId = (NumberPath<Long>) this.createNumber("memberId", (Class) Long.class);
    }

    public QChannelVO(final PathMetadata metadata) {
        super((Class) ChannelVO.class, metadata);
        this.channelCount = (NumberPath<Integer>) this.createNumber("channelCount", (Class) Integer.class);
        this.channelReward = (NumberPath<BigDecimal>) this.createNumber("channelReward", (Class) BigDecimal.class);
        this.memberId = (NumberPath<Long>) this.createNumber("memberId", (Class) Long.class);
    }

    static {
        channelVO = new QChannelVO("channelVO");
    }
}
