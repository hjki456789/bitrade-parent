package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.dao.base.BaseDao;
import cn.ztuo.bitrade.entity.MemberPromotion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Seven
 * @date 2019年03月08日
 */
public interface MemberPromotionDao extends BaseDao<MemberPromotion> {

    @Query(value = "select * from member_promotion where invitees_id =:inviteesId limit 1", nativeQuery = true)
    MemberPromotion findByInviteesId(@Param("inviteesId") final Long p0);

    @Query(value = "select * from member_promotion where inviter_id =:inviterId", nativeQuery = true)
    List<MemberPromotion> findByInviterId(@Param("inviterId") final Long p0);

    @Transactional
    @Modifying
    @Query(value = "update member_promotion set inviter_id = :inviterId where invitees_id = :inviteesId ", nativeQuery = true)
    int updateInviterIdByInviteesId(@Param("inviteesId") final Long p0, @Param("inviterId") final Long p1);

}

