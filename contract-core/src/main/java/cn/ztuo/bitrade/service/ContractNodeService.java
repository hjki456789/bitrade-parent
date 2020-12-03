package cn.ztuo.bitrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import cn.ztuo.bitrade.util.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.transaction.annotation.*;

@Service
public class ContractNodeService
{
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private ContractWalletFlowRecordService contractWalletFlowRecordService;
    @Autowired
    private VirtualRechargeFrozenFlowRepository virtualRechargeFrozenFlowRepository;
    @Autowired
    private ContractNodeRepository contractNodeRepository;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberPromotionService memberPromotionService;

    public Page<ContractNode> findAllContractNode(final Predicate predicate, final Pageable pageable) {
        final List<ContractNode> contractNodeList = new ArrayList<ContractNode>();
        final Page<Member> memberPage = (Page<Member>)this.memberService.findAll(predicate, pageable);
        if (memberPage == null) {
            return null;
        }
        for (final Member member : memberPage.getContent()) {
            final ContractNode contractNode = this.contractNodeRepository.findByMemberId(member.getId());
            if (contractNode != null) {
                contractNode.setMember(member);
                contractNodeList.add(contractNode);
            }
        }
        return (Page<ContractNode>)new PageImpl((List)contractNodeList, pageable, memberPage.getTotalElements());
    }

    public Page<ContractNode> findAllPromotionContractNode(final Predicate predicate,Pageable pageable) {
        final List<ContractNode> contractNodeList = new ArrayList<ContractNode>();
        final Page<MemberPromotion> memberPromotionsPage = (Page<MemberPromotion>)this.memberPromotionService.findAll(predicate, pageable);
        if (memberPromotionsPage == null) {
            return null;
        }
        for (final MemberPromotion memberPromotion : memberPromotionsPage.getContent()) {
            final Member member = memberPromotion.getMember();
            final ContractNode contractNode = this.contractNodeRepository.findByMemberId(member.getId());
            contractNode.setMember(member);
            contractNodeList.add(contractNode);
        }
        return (Page<ContractNode>)new PageImpl((List)contractNodeList);
    }

    public Page<ContractNode> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<ContractNode> page = (Page<ContractNode>)this.contractNodeRepository.findAll(predicate, pageable);
        if (page != null && page.getContent() != null) {
            for (final ContractNode contractNode : page.getContent()) {
                final Member member = (Member)this.memberDao.getOne(contractNode.getMemberId());
                contractNode.setMember(member);
            }
        }
        return page;
    }

    public List<ContractNode> findList(final ContractNode contractNode) {
        final Specification<ContractNode> spec = (Specification<ContractNode>)((root, criteriaQuery, criteriaBuilder) -> {
            if (contractNode.getEnable() != null) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("enable"), (Object)contractNode.getEnable()));
            }
            if (contractNode.getNodeLevel() != null) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("nodeLevel"), (Object)contractNode.getNodeLevel()));
            }
            if (contractNode.getMemberStatus() != null) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("memberStatus"), (Object)contractNode.getMemberStatus()));
            }
            return null;
        });
        final Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        final Sort sort = Sort.by(new Sort.Order[] { order });
        return (List<ContractNode>)this.contractNodeRepository.findAll((Specification)spec, sort);
    }

    public ContractNode findOne(final String nodeId) {
        final ContractNode contractNode = (ContractNode)this.contractNodeRepository.getOne(nodeId);
        if (contractNode != null) {
            final Member member = (Member)this.memberDao.getOne(contractNode.getMemberId());
            contractNode.setMember(member);
        }
        return contractNode;
    }

    public List<ContractNode> findListByMemberStatuses(final List<Integer> memberStatuses) {
        return this.contractNodeRepository.findListByMemberStatuses(memberStatuses);
    }

    public ContractNode findByMemberId(final Long memberId) {
        final ContractNode contractNode = this.contractNodeRepository.findByMemberId(memberId);
        if (contractNode != null) {
            final Member member = (Member)this.memberDao.getOne(contractNode.getMemberId());
            contractNode.setMember(member);
        }
        return contractNode;
    }

    @Transactional(rollbackFor = { Exception.class })
    public MessageResult addContractNode(final ContractNode contractNode) {
        final Long memberId = contractNode.getMemberId();
        final BigDecimal depositAmount = contractNode.getDepositAmount();
        final BigDecimal virtualRechargeAmount = contractNode.getVirtualRechargeAmount();
        final String coinId = "USDT";
        ContractWallet contractWallet = this.contractWalletService.findByMemberIdAndCoin(memberId, coinId);
        if (contractWallet == null) {
            contractWallet = this.contractWalletService.insertContractWallet(memberId, coinId);
        }
        contractWallet.setVirtualRechargeFrozenBalance((contractWallet.getVirtualRechargeFrozenBalance() == null) ? BigDecimal.ZERO : contractWallet.getVirtualRechargeFrozenBalance());
        if (depositAmount.compareTo(contractWallet.getBalance()) == 1) {
            return MessageResult.error("\u5408\u7ea6\u7528\u6237\u4f59\u989d\u4e0d\u8db3!");
        }
        contractWallet.setBalance(contractWallet.getBalance().subtract(depositAmount));
        contractWallet.setVirtualRechargeFrozenBalance(contractWallet.getVirtualRechargeFrozenBalance().add(virtualRechargeAmount));
        final int num = this.contractWalletService.updateContractWalletBalance(contractWallet);
        if (num > 0) {
            if (depositAmount.compareTo(new BigDecimal(0)) == 1) {
                final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord(depositAmount.negate(), contractWallet.getBalance(), ContractWalletOperationType.BE_NODE, -1L, ContractWalletOperationType.BE_NODE.getCnName());
                contractWalletFlowRecord.setMember(new Member(memberId));
                contractWalletFlowRecord.setCoin(new Coin(coinId));
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
            }
            final VirtualRechargeFrozenFlow virtualRechargeFrozenFlow = new VirtualRechargeFrozenFlow(memberId, coinId, virtualRechargeAmount, contractWallet.getVirtualRechargeFrozenBalance(), VirtualRechargeFrozenFlowOperationType.BE_NODE);
            this.virtualRechargeFrozenFlowRepository.save(virtualRechargeFrozenFlow);
            this.contractNodeRepository.save(contractNode);
            this.memberDao.updateIfNode(memberId, IfNodeType.NODE);
        }
        return MessageResult.success("SUCCESS");
    }

    public ContractNode save(final ContractNode contractNode) {
        return (ContractNode)this.contractNodeRepository.save(contractNode);
    }

    public int updateEnable(final String id, final Integer enable) {
        return this.contractNodeRepository.updateEnable(id, enable);
    }

    public void delete(final String[] ids) {
        for (final String id : ids) {
            final ContractNode contractNode = this.findOne(id);
            if (contractNode != null && contractNode.getMemberId() != null) {
                this.cancel(contractNode.getMemberId());
            }
        }
    }

    @Transactional(rollbackFor = { Exception.class })
    public MessageResult addDepositAmount(final ContractNode contractNode, final BigDecimal depositAmountAdd, final BigDecimal virtualRechargeAmountAdd) {
        final Long memberId = contractNode.getMemberId();
        final String coinId = "USDT";
        ContractWallet contractWallet = this.contractWalletService.findByMemberIdAndCoin(memberId, coinId);
        if (contractWallet == null) {
            contractWallet = this.contractWalletService.insertContractWallet(memberId, coinId);
        }
        if (depositAmountAdd.compareTo(contractWallet.getBalance()) == 1) {
            return MessageResult.error("\u5408\u7ea6\u7528\u6237\u4f59\u989d\u4e0d\u8db3!");
        }
        contractNode.setDepositAmount(contractNode.getDepositAmount().add(depositAmountAdd));
        contractNode.setVirtualRechargeAmount(contractNode.getVirtualRechargeAmount().add(virtualRechargeAmountAdd));
        this.contractNodeRepository.save(contractNode);
        contractWallet.setBalance(contractWallet.getBalance().subtract(depositAmountAdd));
        contractWallet.setVirtualRechargeFrozenBalance(contractWallet.getVirtualRechargeFrozenBalance().add(virtualRechargeAmountAdd));
        final int num = this.contractWalletService.updateContractWalletBalance(contractWallet);
        if (num > 0) {
            if (depositAmountAdd.compareTo(new BigDecimal(0)) == 1) {
                final ContractWalletFlowRecord contractWalletFlowRecord = new ContractWalletFlowRecord(depositAmountAdd.negate(), contractWallet.getBalance(), ContractWalletOperationType.ADD_DEPOSITAMOUNT, -1L, "\u589e\u52a0\u4fdd\u8bc1\u91d1");
                contractWalletFlowRecord.setMember(new Member(memberId));
                contractWalletFlowRecord.setCoin(new Coin(coinId));
                this.contractWalletFlowRecordService.saveRecord(contractWalletFlowRecord);
            }
            final VirtualRechargeFrozenFlow virtualRechargeFrozenFlow = new VirtualRechargeFrozenFlow(memberId, coinId, virtualRechargeAmountAdd, contractWallet.getVirtualRechargeFrozenBalance(), VirtualRechargeFrozenFlowOperationType.ADD_DEPOSITAMOUNT);
            this.virtualRechargeFrozenFlowRepository.save(virtualRechargeFrozenFlow);
        }
        return MessageResult.success("SUCCESS");
    }

    @Transactional(rollbackFor = { Exception.class })
    public MessageResult addMarketManager(final Long memberId, final String nodeName, final BigDecimal profitLossReturnRate) {
        final int num = this.memberDao.updateIfNode(memberId, IfNodeType.MARKET);
        if (num > 0) {
            final BigDecimal init = new BigDecimal(0);
            final ContractNode contractNode = new ContractNode(memberId, nodeName, init, profitLossReturnRate, init, init, init, init, init, init, IfNodeType.MARKET, Integer.valueOf(1));
            this.contractNodeRepository.save(contractNode);
            return MessageResult.success("SUCCESS");
        }
        return MessageResult.error("FAIL");
    }

    @Transactional(rollbackFor = { Exception.class })
    public MessageResult addProxy(final Long memberId, final String nodeName, final BigDecimal feeReturnRate, final BigDecimal profitLossReturnRate, final BigDecimal holdFeeReturnRate) {
        final int num = this.memberDao.updateIfNode(memberId, IfNodeType.PROXY);
        if (num > 0) {
            final BigDecimal init = new BigDecimal(0);
            final ContractNode contractNode = new ContractNode(memberId, nodeName, feeReturnRate, profitLossReturnRate, holdFeeReturnRate, init, init, init, init, init, IfNodeType.PROXY, Integer.valueOf(1));
            this.contractNodeRepository.save(contractNode);
            return MessageResult.success("SUCCESS");
        }
        return MessageResult.error("FAIL");
    }

    @Transactional(rollbackFor = { Exception.class })
    public MessageResult cancel(final Long memberId) {
        final int num = this.memberDao.updateIfNode(memberId, IfNodeType.COMMON);
        if (num > 0) {
            this.contractNodeRepository.deleteByMemberId(memberId);
            return MessageResult.success("SUCCESS");
        }
        return MessageResult.error("FAIL");
    }

    @Transactional(rollbackFor = { Exception.class })
    public int updateVirtualRechargeAmount(final Long memberId, final BigDecimal virtualRechargeAmount) {
        final ContractNode contractNode = this.contractNodeRepository.findByMemberId(memberId);
        if (contractNode != null) {
            contractNode.setVirtualRechargeAmount(virtualRechargeAmount);
            this.contractNodeRepository.save(contractNode);
            return 1;
        }
        return 0;
    }
}
