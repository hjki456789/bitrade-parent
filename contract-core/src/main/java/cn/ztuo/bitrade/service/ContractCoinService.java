package cn.ztuo.bitrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.domain.*;

@Service
public class ContractCoinService
{
    @Autowired
    private ContractCoinRepository contractCoinRepository;
    @Autowired
    private ContractCoinNodeRepository contractCoinNodeRepository;

    public List<ContractCoin> findAllEnabled() {
            Specification<ContractCoin> spec = (Specification<ContractCoin>)((root, criteriaQuery, criteriaBuilder) -> {
                criteriaQuery.where(criteriaBuilder.equal(root.get("enable"), 1));
                return null;
        });
        final Sort.Order order = new Sort.Order(Sort.Direction.ASC, "sort");
        final Sort sort =Sort.by(new Sort.Order[] { order });
        return (List<ContractCoin>)this.contractCoinRepository.findAll((Specification)spec, sort);
    }

    public Page<ContractCoin> findAll(Predicate predicate,Pageable pageable) {
        return (Page<ContractCoin>)this.contractCoinRepository.findAll(predicate, pageable);
    }

    public ContractCoin save(final ContractCoin contractCoin) {
        return (ContractCoin)this.contractCoinRepository.save(contractCoin);
    }

    public ContractCoin findOne(final String id) {
        return (ContractCoin)this.contractCoinRepository.getOne(id);
    }

    public void delete(final String[] ids) {
        for (final String id : ids) {
            this.contractCoinRepository.deleteById(id);
            this.contractCoinNodeRepository.deteleByCoinId(id);
        }
    }

    public int updateEnable(final String id, final Integer enable) {
        return this.contractCoinRepository.updateEnable(id, enable);
    }

    public ContractCoin findBySymbol(final String symbol) {
        return this.contractCoinRepository.findBySymbol(symbol);
    }

    public List<String> findAllSymbol() {
        return this.contractCoinRepository.findAllSymbol();
    }
}
