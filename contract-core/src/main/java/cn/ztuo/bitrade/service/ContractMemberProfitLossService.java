package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import java.math.*;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class ContractMemberProfitLossService
{
    @Autowired
    private ContractMemberProfitLossRepository contractMemberProfitLossRepository;
    
    public void updateProfitLoss(final Long memberId, final BigDecimal profitLossAmount, final ProfitLossType type, final String startTime, final String endTime) {
        if (profitLossAmount.compareTo(new BigDecimal(0)) == 0) {
            return;
        }
        ContractMemberProfitLoss info = this.contractMemberProfitLossRepository.getByMemberIdAndTypeAndStartTimeAndEndTime(memberId, type.getOrdinal(), startTime, endTime);
        if (info != null) {
            if (!profitLossAmount.equals(info.getProfitLossAmount())) {
                info.setId(info.getId());
                info.setProfitLossAmount(profitLossAmount);
                this.contractMemberProfitLossRepository.save((Object)info);
            }
        }
        else {
            info = new ContractMemberProfitLoss(memberId, profitLossAmount, type, startTime, endTime);
            this.contractMemberProfitLossRepository.save((Object)info);
        }
    }
    
    public void updateTotalProfitLoss(final Long memberId, final BigDecimal profitLossAmount, final ProfitLossType type) {
        if (profitLossAmount.compareTo(new BigDecimal(0)) == 0) {
            return;
        }
        ContractMemberProfitLoss info = this.contractMemberProfitLossRepository.getByMemberIdAndType(memberId, type.getOrdinal());
        if (info != null) {
            if (!profitLossAmount.equals(info.getProfitLossAmount())) {
                info.setId(info.getId());
                info.setProfitLossAmount(profitLossAmount);
                this.contractMemberProfitLossRepository.save((Object)info);
            }
        }
        else {
            info = new ContractMemberProfitLoss(memberId, profitLossAmount, type, (String)null, (String)null);
            this.contractMemberProfitLossRepository.save((Object)info);
        }
    }
    
    public BigDecimal sumMemberProfitLossByMmeberIdAndType(final Long memberId, final ProfitLossType type) {
        final BigDecimal result = this.contractMemberProfitLossRepository.sumProfitLossByMemberIdAndType(memberId, type.getOrdinal());
        return (result == null) ? new BigDecimal(0) : result;
    }
}
