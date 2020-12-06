package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;

import java.math.*;

import cn.ztuo.bitrade.entity.*;

@Service
public class ContractExchangeOrderInterestService {
    @Autowired
    private ContractExchangeOrderInterestRepository contractExchangeOrderInterestRepository;

    public BigDecimal sumInterestBalanceByOrderId(final Long orderId) {
        return this.contractExchangeOrderInterestRepository.sumInterestBalanceByOrderId(orderId);
    }

    public ContractExchangeOrderInterest save(final ContractExchangeOrderInterest interest) {
        return (ContractExchangeOrderInterest) this.contractExchangeOrderInterestRepository.saveAndFlush(interest);
    }

    public BigDecimal sumInterestFeeByMemberId(final long memberId, final int status, final int walletType) {
        return this.contractExchangeOrderInterestRepository.sumInterestFeeByMemberId(memberId, status, walletType);
    }
}
