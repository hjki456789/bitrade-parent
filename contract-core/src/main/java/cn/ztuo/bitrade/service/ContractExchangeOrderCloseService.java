package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import java.math.*;
import org.apache.commons.collections.*;
import java.util.*;
import java.io.*;
import org.slf4j.*;

@Service
public class ContractExchangeOrderCloseService
{
    private static final Logger log;
    @Autowired
    private ContractExchangeOrderCloseRepository contractExchangeOrderCloseRepository;
    
    public ContractExchangeOrderClose save(final ContractExchangeOrderClose orderClose) {
        return (ContractExchangeOrderClose)this.contractExchangeOrderCloseRepository.saveAndFlush((Object)orderClose);
    }
    
    public BigDecimal getMemberProfitLoss(final Long memberId, final Date startDate, final Date endDate) {
        final BigDecimal memberProfitLoss = this.contractExchangeOrderCloseRepository.getMemberProfitLoss(memberId, startDate, endDate);
        return (memberProfitLoss == null) ? BigDecimal.ZERO : memberProfitLoss;
    }
    
    public BigDecimal sumMemberTotalProfitLoss(final Long memberId) {
        final BigDecimal memberProfitLoss = this.contractExchangeOrderCloseRepository.sumMemberTotalProfitLoss(memberId);
        return (memberProfitLoss == null) ? BigDecimal.ZERO : memberProfitLoss;
    }
    
    public BigDecimal sumMembersProfitLoss(final List<Long> memberIds, final Date startDate, final Date endDate) {
        if (CollectionUtils.isEmpty((Collection)memberIds)) {
            return BigDecimal.ZERO;
        }
        final BigDecimal membersTotalProfitLoss = this.contractExchangeOrderCloseRepository.sumMembersProfitLoss(memberIds, startDate, endDate);
        return (membersTotalProfitLoss == null) ? BigDecimal.ZERO : membersTotalProfitLoss;
    }
    
    public BigDecimal sumMembersProfitLossRate(final Long memberId, final Date startDate, final Date endDate) {
        final BigDecimal membersTotalProfitLoss = this.contractExchangeOrderCloseRepository.sumMembersProfitLossRate(memberId, startDate, endDate);
        return (membersTotalProfitLoss == null) ? BigDecimal.ZERO : membersTotalProfitLoss;
    }
    
    public BigDecimal sumMembersTotalProfitLoss(final List<Long> memberIds) {
        if (CollectionUtils.isEmpty((Collection)memberIds)) {
            return BigDecimal.ZERO;
        }
        final BigDecimal membersTotalProfitLoss = this.contractExchangeOrderCloseRepository.sumMembersTotalProfitLoss(memberIds);
        return (membersTotalProfitLoss == null) ? BigDecimal.ZERO : membersTotalProfitLoss;
    }
    
    public ContractExchangeOrderClose get(final Long id) {
        return (ContractExchangeOrderClose)this.contractExchangeOrderCloseRepository.findOne((Serializable)id);
    }
    
    public BigDecimal getMinProfitLossRate(final Long memberId, final Date startDate, final Date endDate) {
        final BigDecimal minProfitLossRate = this.contractExchangeOrderCloseRepository.getMinProfitLossRate(memberId, startDate, endDate);
        return (minProfitLossRate == null) ? BigDecimal.ZERO : minProfitLossRate;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ContractExchangeOrderCloseService.class);
    }
}
