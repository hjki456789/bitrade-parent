package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.data.jpa.domain.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import java.io.*;
import javax.persistence.criteria.*;

@Service
public class ContractCoinService
{
    @Autowired
    private ContractCoinRepository contractCoinRepository;
    @Autowired
    private ContractCoinNodeRepository contractCoinNodeRepository;
    
    public List<ContractCoin> findAllEnabled() {
        final Specification<ContractCoin> spec = (Specification<ContractCoin>)((root, criteriaQuery, criteriaBuilder) -> {
            final Path<String> enable = (Path<String>)root.get("enable");
            criteriaQuery.where((Expression)criteriaBuilder.equal((Expression)enable, (Object)1));
            return null;
        });
        final Sort.Order order = new Sort.Order(Sort.Direction.ASC, "sort");
        final Sort sort = new Sort(new Sort.Order[] { order });
        return (List<ContractCoin>)this.contractCoinRepository.findAll((Specification)spec, sort);
    }
    
    public Page<ContractCoin> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<ContractCoin>)this.contractCoinRepository.findAll(predicate, pageable);
    }
    
    public ContractCoin save(final ContractCoin contractCoin) {
        return (ContractCoin)this.contractCoinRepository.save((Object)contractCoin);
    }
    
    public ContractCoin findOne(final String id) {
        return (ContractCoin)this.contractCoinRepository.findOne((Serializable)id);
    }
    
    public void delete(final String[] ids) {
        for (final String id : ids) {
            this.contractCoinRepository.delete((Serializable)id);
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
