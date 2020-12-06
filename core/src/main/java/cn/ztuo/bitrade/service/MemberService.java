package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.dao.MemberWalletDao;
import cn.ztuo.bitrade.util.BigDecimalUtils;
import cn.ztuo.bitrade.util.Md5;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import cn.ztuo.bitrade.dao.MemberDao;
import cn.ztuo.bitrade.dao.MemberSignRecordDao;
import cn.ztuo.bitrade.dao.MemberTransactionDao;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.exception.AuthenticationException;
import cn.ztuo.bitrade.pagination.Criteria;
import cn.ztuo.bitrade.pagination.PageResult;
import cn.ztuo.bitrade.pagination.Restrictions;
import cn.ztuo.bitrade.service.Base.BaseService;

import cn.ztuo.bitrade.vo.ChannelVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class MemberService extends BaseService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberSignRecordDao signRecordDao;

    @Autowired
    private MemberTransactionDao transactionDao;

    @Autowired
    private MemberWalletDao memberWalletDao;

    @Autowired
    private MemberApplicationService memberApplicationService;

    /**
     * 条件查询对象 pageNo pageSize 同时传时分页
     *
     * @param booleanExpressionList
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<Member> queryWhereOrPage(List<BooleanExpression> booleanExpressionList, Integer pageNo, Integer pageSize) {
        List<Member> list;
        JPAQuery<Member> jpaQuery = queryFactory.selectFrom(QMember.member)
                .where(booleanExpressionList.toArray(new BooleanExpression[booleanExpressionList.size()]));
        jpaQuery.orderBy(QMember.member.id.desc());
        if (pageNo != null && pageSize != null) {
            list = jpaQuery.offset((pageNo - 1) * pageSize).limit(pageSize).fetch();
        } else {
            list = jpaQuery.fetch();
        }
        return new PageResult<>(list, jpaQuery.fetchCount());
    }

    public Member save(Member member) {
        return memberDao.saveAndFlush(member);
    }

    public List<Member> findAll(Predicate predicate) {
        //return Collections.;
        Iterable<Member> iterable = memberDao.findAll(predicate);
        List<Member> list = IteratorUtils.toList(iterable.iterator());
        return list;
    }

    public Member saveAndFlush(Member member) {
        return memberDao.saveAndFlush(member);
    }

    @Transactional(rollbackFor = Exception.class)
    public Member loginWithToken(String token, String host) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        if (host.equals("WEB")) {
            return memberDao.findMemberByTokenWebAndTokenWebExpireTimeAfterAndStatus(token, new Date(), CommonStatus.NORMAL);
        }
        Member mr = memberDao.findMemberByTokenAndTokenExpireTimeAfterAndStatus(token, new Date(), CommonStatus.NORMAL);
        return mr;
    }

    public Member login(String username, String password) throws Exception {
        Member member = memberDao.findMemberByMobilePhoneOrEmail(username, username);
        if (member == null) {
            throw new AuthenticationException(msService.getMessage("ACCOUNT_OR_PWD_ERROR"));
        } else if (member.getLoginLock() != null && member.getLoginLock().equals(BooleanEnum.IS_TRUE)) {
            throw new AuthenticationException(msService.getMessage("PWD_REPEAT_ERROR"));
        } else if (!Md5.md5Digest(password + member.getSalt()).toLowerCase().equals(member.getPassword())) {
            log.info("账号或密码错误");
            return null;
            //throw new AuthenticationException(msService.getMessage("PWD_REPEAT_ERROR"));
        } else if (member.getStatus().equals(CommonStatus.ILLEGAL)) {
            throw new AuthenticationException(msService.getMessage("ACCOUNT_IS_LOCK"));
        }
        return member;
    }

    /**
     * @author MrGao
     * @description
     * @date 2017/12/25 18:42
     */
    public Member findOne(Long id) {
        return memberDao.findById(id).orElse(null);
    }

    /**
     * @author MrGao
     * @description 查询所有会员
     * @date 2017/12/25 18:43
     */
    public List<Member> findAll() {
        return memberDao.findAll();
    }


    /**
     * 查询会员总数
     *
     * @return
     */
    public long count() {
        return memberDao.count();
    }

    public Page<Member> findAll(Predicate predicate, PageModel pageModel) {
        return memberDao.findAll(predicate, pageModel.getPageable());
    }

    public List<Member> findPromotionMember(Long id) {
        return memberDao.findAllByInviterId(id);
    }

    /**
     * @author MrGao
     * @description 分页
     * @date 2018/1/12 15:35
     */
    public Page<Member> page(Integer pageNo, Integer pageSize, CommonStatus status) {
        //排序方式 (需要倒序 这样    Criteria.sort("id","createTime.desc") ) //参数实体类为字段名
        Sort orders = Criteria.sortStatic("id");
        //分页参数
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
        //查询条件
        Criteria<Member> specification = new Criteria<Member>();
        specification.add(Restrictions.eq("status", status, false));
        return memberDao.findAll(specification, pageRequest);
    }

    public Page<Member> page(Integer pageNo, Integer pageSize) {
        //排序方式 (需要倒序 这样    Criteria.sort("id","createTime.desc") ) //参数实体类为字段名
        Sort orders = Criteria.sortStatic("id");
        //分页参数
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
        return memberDao.findAll(pageRequest);
    }

    public boolean emailIsExist(String email) {
        List<Member> list = memberDao.getAllByEmailEquals(email);
        return (list != null && list.size() > 0) ? true : false;
    }

    public boolean usernameIsExist(String username) {
        List<Member> list = memberDao.getAllByUsernameEquals(username);
        return (list != null && list.size() > 0) ? true : false;
    }

    public boolean phoneIsExist(String phone) {
        List<Member> list = memberDao.getAllByMobilePhoneEquals(phone);
        return (list != null && list.size() > 0) ? true : false;
    }

    public Member findByUsername(String username) {
        return memberDao.findByUsername(username);
    }

    public Member findByEmail(String email) {
        return memberDao.findMemberByEmail(email);
    }

    public Member findByPhone(String phone) {
        return memberDao.findMemberByMobilePhone(phone);
    }

    public Member findByPhoneOrEmail(String str) {
        return memberDao.findMemberByMobilePhoneOrEmail(str, str);

    }

    public Page<Member> findAll(Predicate predicate, Pageable pageable) {
        return memberDao.findAll(predicate, pageable);
    }

    public String findUserNameById(long id) {
        return memberDao.findUserNameById(id);
    }

    //签到事件
    @Transactional(rollbackFor = Exception.class)
    public void signInIncident(Member member, MemberWallet memberWallet, cn.ztuo.bitrade.entity.Sign sign) {
        member.setSignInAbility(false);//失去签到能力
        memberWalletDao.increaseBalance(memberWallet.getId(), sign.getAmount()); //签到收益

        // 签到记录
        signRecordDao.save(new cn.ztuo.bitrade.entity.MemberSignRecord(member, sign));
        //账单明细
        cn.ztuo.bitrade.entity.MemberTransaction memberTransaction = new cn.ztuo.bitrade.entity.MemberTransaction();
        memberTransaction.setMemberId(member.getId());
        memberTransaction.setAmount(sign.getAmount());
        memberTransaction.setType(TransactionType.ACTIVITY_AWARD);
        memberTransaction.setSymbol(sign.getCoin().getUnit());
        transactionDao.save(memberTransaction);
    }

    //重置会员签到
    public void resetSignIn() {
        memberDao.resetSignIn();
    }

    public void updateCertifiedBusinessStatusByIdList(List<Long> idList) {
        memberDao.updateCertifiedBusinessStatusByIdList(idList, CertifiedBusinessStatus.DEPOSIT_LESS);
    }

    public List<ChannelVO> getChannelCount(List<Long> memberIds) {
        List<Object[]> list = memberDao.getChannelCount(memberIds);
        List<ChannelVO> channelVOList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Object[] objs : list) {
                Number memberId = (Number) objs[0];
                Number channelCount = (Number) objs[1];
                Number channelReward = (Number) objs[2];
                ChannelVO channelVO = new ChannelVO(memberId.longValue(), channelCount.intValue(), new BigDecimal(channelReward.doubleValue()));
                channelVOList.add(channelVO);
            }
        }
        return channelVOList;
    }

    public void lock(String username) {
        memberDao.updateLoginLock(username, BooleanEnum.IS_TRUE);
    }

    public void unLock(String username) {
        memberDao.updateLoginLock(username, BooleanEnum.IS_FALSE);
    }

    public Integer saveWallet(String coinId, Long memberId, BigDecimal balance) {
        return memberDao.saveWallet(coinId, memberId, balance);
    }

    @Transactional(readOnly = true)
    public Long findCount(Predicate predicate) {
        return memberDao.count(predicate);
    }


    public Member findMemberByPromotionCode(String code) {
        return memberDao.findMemberByPromotionCode(code);
    }

    public List<Integer> selectMemberWalletForUpdate(Long memberId) {
        return memberDao.selectMemberWalletForUpdate(memberId);
    }

    public int alterUserType(final Long memberId, final Integer userType) {
        UserType type = UserType.COMMON;
        if (UserType.LEAD_ORDER.getOrdinal() == userType) {
            type = UserType.LEAD_ORDER;
        }
        return this.memberDao.updateUserType(memberId, type);
    }

    public List<Long> queryMemberIds(final Member member) {
        final List<BooleanExpression> booleanExpressionList = new ArrayList<BooleanExpression>();
        if (member != null) {
            if (null != member.getId()) {
                booleanExpressionList.add(QMember.member.id.eq(member.getId()));
            }
            if (!StringUtils.isEmpty(member.getUsername())) {
                booleanExpressionList.add(QMember.member.username.like("%" + member.getUsername() + "%"));
            }
            if (!StringUtils.isEmpty(member.getMobilePhone())) {
                booleanExpressionList.add(QMember.member.mobilePhone.like("%" + member.getMobilePhone() + "%"));
            }
            if (!StringUtils.isEmpty(member.getEmail())) {
                booleanExpressionList.add(QMember.member.email.like("%" + member.getEmail() + "%"));
            }
        }
        final JPAQuery<Member> jpaQuery = (JPAQuery<Member>) this.queryFactory.selectFrom((EntityPath) QMember.member).where((Predicate[]) booleanExpressionList.toArray((Predicate[]) new BooleanExpression[booleanExpressionList.size()]));
        final List<Member> list = (List<Member>) jpaQuery.fetch();
        if (!CollectionUtils.isEmpty(list)) {
            final List<Long> members = new ArrayList<Long>();
            for (final Member m : list) {
                members.add(m.getId());
            }
            return members;
        }
        return null;
    }

    public int updateMemberGradeId(final Long memberId, final long memberGradeId) {
        return this.memberDao.updateMemberGradeId(memberId, memberGradeId);
    }

    public List<Long> getPredicateMemberIds(final Long memberId, final String username, final String realName, final String mobilePhone, final String email) {
        final List<Long> memberIds = new ArrayList<Long>();
        final List<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (memberId != null) {
            booleanExpressions.add(QMember.member.id.eq(memberId));
        }
        if (!StringUtils.isEmpty(username)) {
            booleanExpressions.add(QMember.member.username.like("%" + username + "%"));
        }
        if (!StringUtils.isEmpty(realName)) {
            booleanExpressions.add(QMember.member.realName.like("%" + realName + "%"));
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            booleanExpressions.add(QMember.member.mobilePhone.like("%" + mobilePhone + "%"));
        }
        if (!StringUtils.isEmpty(email)) {
            booleanExpressions.add(QMember.member.email.like("%" + email + "%"));
        }
        if (org.springframework.util.CollectionUtils.isEmpty(booleanExpressions)) {
            return memberIds;
        }
        final JPAQuery<Member> jpaQuery = (JPAQuery<Member>) this.queryFactory.selectFrom((EntityPath) QMember.member).where((Predicate[]) booleanExpressions.toArray((Predicate[]) new BooleanExpression[booleanExpressions.size()]));
        jpaQuery.orderBy(QMember.member.id.desc());
        final List<Member> list = (List<Member>) jpaQuery.fetch();
        if (org.springframework.util.CollectionUtils.isEmpty(list)) {
            memberIds.add(-1L);
        }
        for (final Member member : list) {
            memberIds.add(member.getId());
        }
        return memberIds;
    }

    /**
     * 功能描述: 重置实名认证
     *
     * @auther:
     * @Description
     * @param: [member]
     * @return: void
     * @date: 2020/12/6 18:06
     */
    @Transactional
    public void resetRealNameVerify(final Member member) {
        member.setIdNumber(null);
        member.setRealName(null);
        member.setApplicationTime(null);
        member.setRealNameStatus(RealNameStatus.AUDITING);
        member.setMemberLevel(MemberLevelEnum.GENERAL);
        member.setKycStatus(0);
        this.save(member);
        final MemberApplication application = this.memberApplicationService.findByMemberId(member.getId());
        if (application != null) {
            application.setAuditStatus(AuditStatus.AUDIT_ING);
            application.setKycStatus(5);
            this.memberApplicationService.save(application);
        }
    }
}
