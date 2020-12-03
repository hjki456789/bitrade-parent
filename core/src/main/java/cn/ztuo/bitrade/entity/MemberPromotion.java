package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.PromotionLevel;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Seven
 * @date 2019年03月08日
 */
@Entity
@Data
public class MemberPromotion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //邀请者Id
    private Long inviterId;
    //受邀者Id
    private Long inviteesId;

    @Enumerated(EnumType.ORDINAL)
    private PromotionLevel level;

    //york新增
    @JoinColumn(name = "inviteesId", updatable = false, insertable = false)
    @OneToOne
    private Member member;

    public MemberPromotion() {
    }

    public MemberPromotion(Long inviteesId) {
        this.inviteesId = inviteesId;
    }
}
