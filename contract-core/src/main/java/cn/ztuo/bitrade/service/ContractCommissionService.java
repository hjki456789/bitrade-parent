package cn.ztuo.bitrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.pagination.*;
import com.querydsl.jpa.impl.*;
import com.querydsl.core.types.*;
import org.apache.commons.collections.*;

import java.util.*;

import org.springframework.transaction.annotation.*;

import java.io.*;

import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import java.math.*;

import cn.ztuo.bitrade.entity.*;

@Service
public class ContractCommissionService {
    @Autowired
    private ContractCommissionRepository contractCommissionRepository;
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;
    @Autowired
    protected JPAQueryFactory queryFactory;

    public Page<ContractCommissionInfo> findAll(final Predicate predicate, final Pageable pageable) {
        return this.contractCommissionRepository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = true)
    public PageResult<ContractCommissionExcel> queryWhereOrPage(final List<BooleanExpression> booleanExpressionList, final Integer pageNo, final Integer pageSize) {
        final List<ContractCommissionExcel> resultList = new ArrayList<ContractCommissionExcel>();
        final JPAQuery<ContractCommissionInfo> jpaQuery = (JPAQuery<ContractCommissionInfo>) this.queryFactory.selectFrom((EntityPath) QContractCommissionInfo.contractCommissionInfo);
        final OrderSpecifier<Long> orderSpecifier = (OrderSpecifier<Long>) QContractCommissionInfo.contractCommissionInfo.sequence.desc();
        if (booleanExpressionList != null) {
            jpaQuery.where((Predicate[]) booleanExpressionList.toArray((Predicate[]) new BooleanExpression[booleanExpressionList.size()]));
        }
        List<ContractCommissionInfo> list;
        if (pageNo != null && pageSize != null) {
            list = (List<ContractCommissionInfo>) ((JPAQuery) ((JPAQuery) ((JPAQuery) jpaQuery.orderBy((OrderSpecifier) orderSpecifier)).offset((long) ((pageNo - 1) * pageSize))).limit((long) pageSize)).fetch();
        } else {
            list = (List<ContractCommissionInfo>) ((JPAQuery) jpaQuery.orderBy((OrderSpecifier) orderSpecifier)).fetch();
        }
        if (!CollectionUtils.isEmpty((Collection) list)) {
            for (final ContractCommissionInfo contractCommissionInfo : list) {
                final ContractCommissionExcel result = this.getContractCommissionExcelParam(contractCommissionInfo);
                if (result != null) {
                    resultList.add(result);
                }
            }
        }
        return (PageResult<ContractCommissionExcel>) new PageResult((List) resultList, Long.valueOf(jpaQuery.fetchCount()));
    }

    private ContractCommissionExcel getContractCommissionExcelParam(final ContractCommissionInfo contractCommissionInfo) {
        final ContractCommissionExcel dto = new ContractCommissionExcel();
        final Member member = contractCommissionInfo.getMember();
        if (member != null) {
            dto.setMemberId(member.getId());
            dto.setUsername(member.getUsername());
            dto.setEmail(member.getEmail());
            dto.setMobilePhone(member.getMobilePhone());
        }
        dto.setProxyId(contractCommissionInfo.getProxyId());
        dto.setAmount(contractCommissionInfo.getAmount());
        dto.setCoinName(contractCommissionInfo.getCoinName());
        final Integer status = (contractCommissionInfo.getStatus() == null) ? 0 : contractCommissionInfo.getStatus();
        dto.setStatus((status == 0) ? "\u672a\u53d1\u653e" : "\u5df2\u53d1\u653e");
        return dto;
    }

    public ContractCommissionInfo findOne(final String id) {
        return (ContractCommissionInfo) this.contractCommissionRepository.getOne(id);
    }

    public ContractCommissionInfo findByMemberId(final String memberId) {
        return this.contractCommissionRepository.findByMemberId(memberId);
    }

    public int updateStatus(final String id, final Integer status, final String adminId) {
        return this.contractCommissionRepository.updateStatus(id, status, adminId);
    }

    public void save(final ContractCommissionInfo contractCommissionInfo) {
        this.contractCommissionRepository.save(contractCommissionInfo);
    }

    @Transactional(rollbackFor = {Exception.class})
    public MessageResult grantCommissionPass(final ContractCommissionInfo dto) {
        final Long proxyId = dto.getProxyId();
        final Long memberId = dto.getMember().getId();
        final String coinId = dto.getCoinName();
        final BigDecimal amount = dto.getAmount();
        if (proxyId != null && proxyId != 0L) {
            ContractWallet proxyWallet = this.contractWalletService.findByMemberId(proxyId);
            if (proxyWallet == null) {
                proxyWallet = this.contractWalletService.insertContractWallet(proxyId, coinId);
            }
            if (proxyWallet.getBalance().compareTo(amount) == -1) {
                return MessageResult.error("上级节点用户账户余额不足，返佣审核失败!");
            }
            proxyWallet.setBalance(proxyWallet.getBalance().subtract(amount));
            this.contractWalletService.updateContractWalletBalance(proxyWallet);
            final ContractWalletFlowRecord proxyWalletFlowRecord = new ContractWalletFlowRecord(amount.negate(), proxyWallet.getBalance(), ContractWalletOperationType.SUPER_NODE_RETURN_COMMISSION, -1L, "\u7528\u6237\u8fd4\u4f63\uff0c\u6240\u5c5e\u4e0a\u7ea7\u7528\u6237\u51cf\u5c11\u76f8\u5e94\u4f63\u91d1");
            proxyWalletFlowRecord.setMember(new Member(proxyId));
            proxyWalletFlowRecord.setCoin(new Coin(coinId));
            this.contractWalletFlowRecordService.saveRecord(proxyWalletFlowRecord);
        }
        ContractWallet contractWallet = this.contractWalletService.findByMemberId(memberId);
        if (contractWallet == null) {
            contractWallet = this.contractWalletService.insertContractWallet(memberId, coinId);
        }
        contractWallet.setBalance(contractWallet.getBalance().add(amount));
        this.contractWalletService.updateContractWalletBalance(contractWallet);
        final ContractWalletFlowRecord walletFlowRecord = new ContractWalletFlowRecord(amount, contractWallet.getBalance(), ContractWalletOperationType.RETURN_COMMISSION, -1L, "\u7528\u6237\u8fd4\u4f63");
        walletFlowRecord.setMember(new Member(memberId));
        walletFlowRecord.setCoin(new Coin(coinId));
        this.contractWalletFlowRecordService.saveRecord(walletFlowRecord);
        this.updateStatus(dto.getId(), 1, dto.getSysUser());
        return MessageResult.success("SUCCESS!");
    }
}
