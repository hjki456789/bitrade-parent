package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

import java.io.*;

import cn.ztuo.bitrade.entity.*;

@Service
public class ContractCoinNodeService {
    @Autowired
    private ContractCoinNodeRepository contractCoinNodeRepository;

    public Page<ContractCoinNode> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<ContractCoinNode>) this.contractCoinNodeRepository.findAll(predicate, pageable);
    }

    public ContractCoinNode findOne(final String id) {
        return (ContractCoinNode) this.contractCoinNodeRepository.getOne(id);
    }

    public ContractCoinNode getByCoinIdAndNodeId(final String coinId, final String nodeId) {
        return this.contractCoinNodeRepository.getByCoinIdAndNodeId(coinId, nodeId);
    }

    public ContractCoinNode insert(final String coinId, final String nodeId) {
        final ContractCoinNode contractCoinNode = new ContractCoinNode();
        final ContractCoin contractCoin = new ContractCoin(coinId);
        final ContractNode contractNode = new ContractNode(nodeId);
        contractCoinNode.setContractNode(contractNode);
        contractCoinNode.setContractCoin(contractCoin);
        contractCoinNode.setEnable(1);
        contractCoinNode.setSequence(System.currentTimeMillis());
        return (ContractCoinNode) this.contractCoinNodeRepository.save(contractCoinNode);
    }

    public ContractCoinNode update(final ContractCoinNode contractCoinNode) {
        return (ContractCoinNode) this.contractCoinNodeRepository.save(contractCoinNode);
    }

    public int updateEnable(final String id, final Integer enable) {
        return this.contractCoinNodeRepository.updateEnable(id, enable);
    }

    public void deleteByIds(final String[] ids) {
        for (final String id : ids) {
            this.contractCoinNodeRepository.deleteById(id);
        }
    }
}
