package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.constant.IfNodeType;
import cn.ztuo.bitrade.constant.PageModel;
import cn.ztuo.bitrade.constant.PromotionLevel;
import cn.ztuo.bitrade.constant.UserType;
import cn.ztuo.bitrade.dao.MemberDao;
import cn.ztuo.bitrade.dao.MemberPromotionDao;
import cn.ztuo.bitrade.entity.Member;
import cn.ztuo.bitrade.entity.MemberPromotion;
import cn.ztuo.bitrade.service.Base.BaseService;
import cn.ztuo.bitrade.vo.RegisterPromotionVO;
import com.querydsl.core.types.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seven
 * @date 2019年03月08日
 */
@Service
public class MemberPromotionService extends BaseService {

    @Autowired
    private MemberPromotionDao memberPromotionDao;

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDao memberDao;

    public MemberPromotion save(MemberPromotion memberPromotion){
        return memberPromotionDao.save(memberPromotion);
    }

    public Page<RegisterPromotionVO> getPromotionDetails(long memberId, PageModel pageModel){

        StringBuilder headSql = new StringBuilder("select a.id id ,a.username presentee,a.email presenteeEmail, ")
                .append("a.mobile_phone presenteePhone,a.real_name presenteeRealName,a.registration_time promotionTime, ")
                .append("b.level promotionLevel ") ;

        StringBuilder endSql = new StringBuilder(" from member a join member_promotion b on a.id = b.invitees_id and b.inviter_id = "+memberId);

        StringBuilder countHead = new StringBuilder("select count(*) ") ;
        Page<RegisterPromotionVO> page = createNativePageQuery(countHead.append(endSql),headSql.append(endSql),pageModel,Transformers.aliasToBean(RegisterPromotionVO.class)) ;
        return page ;
    }

    public void updateSuperiorMember(final Long memberId, final Long superiorMemberId) {
        MemberPromotion memberPromotion = this.findByInviteesId(memberId);
        if (memberPromotion == null) {
            memberPromotion = new MemberPromotion();
            memberPromotion.setInviteesId(memberId);
            memberPromotion.setInviterId(superiorMemberId);
            memberPromotion.setLevel(PromotionLevel.ONE);
            this.memberPromotionDao.save(memberPromotion);
        }
        else {
            this.memberPromotionDao.updateInviterIdByInviteesId(memberId, superiorMemberId);
        }
        this.memberDao.updateInviterId(memberId, superiorMemberId);
    }

    public Page<MemberPromotion> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<MemberPromotion>)this.memberPromotionDao.findAll(predicate, pageable);
    }

    public MemberPromotion findByInviteesId(final Long inviteesId) {
        return this.memberPromotionDao.findByInviteesId(inviteesId);
    }

    public List<MemberPromotion> findByInviterId(final Long inviterId) {
        return this.memberPromotionDao.findByInviterId(inviterId);
    }

    public List<Long> findMemberIdByInviterId(final Long inviterId) {
        final List<Long> memberIdList = new ArrayList<Long>();
        final List<MemberPromotion> list = this.memberPromotionDao.findByInviterId(inviterId);
        if (!CollectionUtils.isEmpty(list)) {
            for (final MemberPromotion memberPromotion : list) {
                memberIdList.add(memberPromotion.getInviteesId());
            }
        }
        return memberIdList;
    }

    public List<Long> findAllTeamMember(List<Long> list, final Long memberId, final boolean isTopParent, final boolean isIncludeSelf) {
        if (list == null) {
            list = new ArrayList<Long>();
        }
        if (isTopParent) {
            if (isIncludeSelf) {
                list.add(memberId);
            }
        }
        else {
            list.add(memberId);
        }
        final Member member = this.memberService.findOne(memberId);
        if (member != null) {
            final int userType = (member.getUserType() == null) ? UserType.COMMON.getOrdinal() : member.getUserType().getOrdinal();
            if (UserType.LEAD_ORDER.getOrdinal() != userType) {
                list.add(memberId);
            }
        }
        final List<MemberPromotion> memberPromotionList = this.findByInviterId(memberId);
        if (CollectionUtils.isNotEmpty(memberPromotionList)) {
            for (final MemberPromotion info : memberPromotionList) {
                list = this.findAllTeamMember(list, info.getInviteesId(), false, true);
            }
        }
        return list;
    }

    public List<Long> findTeamMembers(List<Long> list, final Long memberId, final boolean isTopParent, final boolean isIncludeSelf) {
        if (list == null) {
            list = new ArrayList<Long>();
        }
        if (isTopParent) {
            if (isIncludeSelf) {
                list.add(memberId);
            }
        }
        else {
            list.add(memberId);
        }
        final List<MemberPromotion> memberPromotionList = this.findByInviterId(memberId);
        if (CollectionUtils.isNotEmpty(memberPromotionList)) {
            for (final MemberPromotion info : memberPromotionList) {
                list = this.findTeamMembers(list, info.getInviteesId(), false, true);
            }
        }
        return list;
    }

    public Long getSuperNodeId(final Long memberId) {
        final MemberPromotion memberPromotion = this.findByInviteesId(memberId);
        if (memberPromotion == null) {
            return memberId;
        }
        final Member member = this.memberService.findOne(memberPromotion.getInviterId());
        if (member == null) {
            return memberId;
        }
        if (IfNodeType.NODE.equals(member.getIfNode())) {
            return member.getId();
        }
        return this.getSuperNodeId(member.getId());
    }

}
