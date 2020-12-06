package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.constant.TransactionType;
import cn.ztuo.bitrade.dao.MemberWalletDao;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.DateUtil;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import cn.ztuo.bitrade.constant.PageModel;
import cn.ztuo.bitrade.dao.MemberDepositDao;
import cn.ztuo.bitrade.service.Base.BaseService;
import cn.ztuo.bitrade.vo.MemberDepositVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberDepositService extends BaseService<MemberDeposit> {

    @Autowired
    private MemberDepositDao memberDepositDao;

    @Autowired
    private MemberWalletDao memberWalletDao;
    @Autowired
    private MemberTransactionService memberTransactionService;

    public Page<MemberDepositVO> page(List<BooleanExpression> predicates, PageModel pageModel) {
        JPAQuery<MemberDepositVO> query = queryFactory.select(Projections.fields(MemberDepositVO.class,
                QMemberDeposit.memberDeposit.id.as("id"),
                QMember.member.username,
                QMember.member.id.as("memberId"),
                QMemberDeposit.memberDeposit.address,
                QMemberDeposit.memberDeposit.amount,
                QMemberDeposit.memberDeposit.txid,
                QMemberDeposit.memberDeposit.createTime.as("createTime"),
                QMemberDeposit.memberDeposit.unit)).from(QMember.member, QMemberDeposit.memberDeposit)
                .where(predicates.toArray(new BooleanExpression[predicates.size()]));
        List<OrderSpecifier> orderSpecifiers = pageModel.getOrderSpecifiers();
        query.orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]));
        long total = query.fetchCount();
        query.offset(pageModel.getPageSize() * (pageModel.getPageNo() - 1)).limit(pageModel.getPageSize());
        List<MemberDepositVO> list = query.fetch();
        return new PageImpl<MemberDepositVO>(list, pageModel.getPageable(), total);
    }

    public Map<String, BigDecimal> sumMemberDeposit(final Long memberId) {
        final Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        final List<Object[]> list = this.memberDepositDao.sumMemberDeposit(memberId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (final Object[] objects : list) {
                map.put(objects[0].toString(), new BigDecimal(objects[1].toString()));
            }
        }
        return map;
    }


    public BigDecimal sumMemberDepositByUnit(final long memberId, final String unit) {
        return this.memberDepositDao.sumMemberDepositByUnit(memberId, unit);
    }

    public void save(final MemberDeposit memberDeposit) {
        this.memberDepositDao.save(memberDeposit);
        if (memberDeposit.getIsAddMemberBalance() == 1) {
            final Coin coin = new Coin();
            coin.setName(memberDeposit.getUnit());
            MemberWallet memberWallet = this.memberWalletDao.findByCoinAndMemberId(coin, memberDeposit.getMemberId());
            if (memberWallet == null) {
                memberWallet = new MemberWallet();
                memberWallet.setMemberId(memberDeposit.getMemberId());
                memberWallet.setCoin(coin);
                memberWallet = (MemberWallet) this.memberWalletDao.saveAndFlush(memberWallet);
            }
            memberWallet.setBalance(memberWallet.getBalance().add(memberDeposit.getAmount()));
            this.memberWalletDao.increaseBalance(memberWallet.getId(), memberDeposit.getAmount(), memberWallet.getVersion());
            final MemberTransaction memberTransaction = new MemberTransaction();
            memberTransaction.setFee(BigDecimal.ZERO);
            memberTransaction.setAmount(memberDeposit.getAmount());
            memberTransaction.setMemberId(memberDeposit.getMemberId());
            memberTransaction.setSymbol(memberDeposit.getUnit());
            memberTransaction.setType(TransactionType.MEMBER_DEPOSIT_ADD_BALANCE);
            memberTransaction.setCreateTime(DateUtil.getCurrentDate());
            this.memberTransactionService.save(memberTransaction);
        }
    }

    public MemberDeposit getByTxid(final String txid) {
        return this.memberDepositDao.getByTxid(txid);
    }

    public int updateCollectType(final Long id, final Integer collectType) {
        return this.memberDepositDao.updateCollectType(id, collectType);
    }

}
