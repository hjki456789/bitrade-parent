package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

import java.io.*;
import java.math.*;

import org.apache.commons.collections.*;

import java.util.*;

@Service
public class ContractExchangeOrderCalculateService {
    @Autowired
    private ContractExchangeOrderCalculateRepository contractExchangeOrderCalculateRepository;

    public ContractExchangeOrderCalculate findByOrderId(final Long orderId) {
        return (ContractExchangeOrderCalculate) this.contractExchangeOrderCalculateRepository.getOne(orderId);
    }

    public ContractExchangeOrderCalculate save(final ContractExchangeOrderCalculate calculate) {
        return (ContractExchangeOrderCalculate) this.contractExchangeOrderCalculateRepository.saveAndFlush(calculate);
    }

    public Integer update(final ContractExchangeOrderCalculate calculate) {
        return this.contractExchangeOrderCalculateRepository.updateProfit(calculate.getOrderId(), calculate.getSymbolPrice(), calculate.getProfit(), calculate.getRate(), calculate.getVersion());
    }

    public BigDecimal getCalculateProfit(final Long memberId, final Date startDate, final Date endDate) {
        final BigDecimal totalCalculateProfit = this.contractExchangeOrderCalculateRepository.getCalculateProfit(memberId, startDate, endDate);
        return (totalCalculateProfit == null) ? new BigDecimal(0) : totalCalculateProfit;
    }

    public BigDecimal sumMembersCalculateProfit(final List<Long> memberIds, final Date startDate, final Date endDate) {
        if (CollectionUtils.isEmpty((Collection) memberIds)) {
            return new BigDecimal(0);
        }
        final BigDecimal totalCalculateProfit = this.contractExchangeOrderCalculateRepository.sumMembersCalculateProfit(memberIds, startDate, endDate);
        return (totalCalculateProfit == null) ? new BigDecimal(0) : totalCalculateProfit;
    }

    public BigDecimal getMemberCalculateProfit(final Long memberId, final String coin, final int status, final int walletType) {
        return this.contractExchangeOrderCalculateRepository.getMemberCalculateProfit(memberId, coin, status, walletType);
    }

    public List<ContractExchangeOrderCalculate> findAllByMemberId(final long memberId, final String coin, final int status, final int walletType) {
        return this.contractExchangeOrderCalculateRepository.findAllByMemberId(memberId, coin, status, walletType);
    }
}
