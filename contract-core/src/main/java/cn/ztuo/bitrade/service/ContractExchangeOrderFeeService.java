package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

import java.math.*;

import org.apache.commons.collections.*;

import java.util.*;

@Service
public class ContractExchangeOrderFeeService {
    @Autowired
    private ContractExchangeOrderFeeRepository contractExchangeOrderFeeRepository;

    public ContractExchangeOrderFee save(final ContractExchangeOrderFee orderFee) {
        return (ContractExchangeOrderFee) this.contractExchangeOrderFeeRepository.saveAndFlush(orderFee);
    }

    public BigDecimal sumMembersFee(final List<Long> memberIds, final Date startDate, final Date endDate) {
        if (CollectionUtils.isEmpty((Collection) memberIds)) {
            return BigDecimal.ZERO;
        }
        final BigDecimal membersFee = this.contractExchangeOrderFeeRepository.sumMembersFee(memberIds, startDate, endDate);
        return (membersFee == null) ? BigDecimal.ZERO : membersFee;
    }

    public BigDecimal sumMembersTotalFee(final List<Long> memberIds) {
        if (CollectionUtils.isEmpty((Collection) memberIds)) {
            return BigDecimal.ZERO;
        }
        final BigDecimal membersFee = this.contractExchangeOrderFeeRepository.sumMembersTotalFee(memberIds);
        return (membersFee == null) ? BigDecimal.ZERO : membersFee;
    }

    public BigDecimal sumOpenFeeByMemberId(final long memberId, final int status, final int walletType) {
        return this.contractExchangeOrderFeeRepository.sumOpenFeeByMemberId(memberId, status, walletType);
    }

    public BigDecimal sumCloseFeeByMemberId(final long memberId, final int status, final int walletType) {
        return this.contractExchangeOrderFeeRepository.sumCloseFeeByMemberId(memberId, status, walletType);
    }

    public ContractExchangeOrderFee findByOrderId(final long orderId) {
        return this.contractExchangeOrderFeeRepository.findByOrderId(orderId);
    }
}
