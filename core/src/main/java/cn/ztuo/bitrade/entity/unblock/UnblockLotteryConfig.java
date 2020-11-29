package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

@Entity
@Table
public class UnblockLotteryConfig implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String lotteryLevel;
    private BigDecimal lotteryRate;
    private String lotteryInstruction;
    private String lotteryRule;
    private Date createTime;
    private BigDecimal buyPrice;
    private String coin;
    private BigDecimal amount;
    private BigDecimal insuranceRate;
    private int dayWinnerNumLimit;
    
    public Long getId() {
        return this.id;
    }
    
    public String getLotteryLevel() {
        return this.lotteryLevel;
    }
    
    public BigDecimal getLotteryRate() {
        return this.lotteryRate;
    }
    
    public String getLotteryInstruction() {
        return this.lotteryInstruction;
    }
    
    public String getLotteryRule() {
        return this.lotteryRule;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public BigDecimal getBuyPrice() {
        return this.buyPrice;
    }
    
    public String getCoin() {
        return this.coin;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public BigDecimal getInsuranceRate() {
        return this.insuranceRate;
    }
    
    public int getDayWinnerNumLimit() {
        return this.dayWinnerNumLimit;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setLotteryLevel(final String lotteryLevel) {
        this.lotteryLevel = lotteryLevel;
    }
    
    public void setLotteryRate(final BigDecimal lotteryRate) {
        this.lotteryRate = lotteryRate;
    }
    
    public void setLotteryInstruction(final String lotteryInstruction) {
        this.lotteryInstruction = lotteryInstruction;
    }
    
    public void setLotteryRule(final String lotteryRule) {
        this.lotteryRule = lotteryRule;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setBuyPrice(final BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }
    
    public void setCoin(final String coin) {
        this.coin = coin;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setInsuranceRate(final BigDecimal insuranceRate) {
        this.insuranceRate = insuranceRate;
    }
    
    public void setDayWinnerNumLimit(final int dayWinnerNumLimit) {
        this.dayWinnerNumLimit = dayWinnerNumLimit;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnblockLotteryConfig)) {
            return false;
        }
        final UnblockLotteryConfig other = (UnblockLotteryConfig)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$lotteryLevel = this.getLotteryLevel();
        final Object other$lotteryLevel = other.getLotteryLevel();
        Label_0102: {
            if (this$lotteryLevel == null) {
                if (other$lotteryLevel == null) {
                    break Label_0102;
                }
            }
            else if (this$lotteryLevel.equals(other$lotteryLevel)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$lotteryRate = this.getLotteryRate();
        final Object other$lotteryRate = other.getLotteryRate();
        Label_0139: {
            if (this$lotteryRate == null) {
                if (other$lotteryRate == null) {
                    break Label_0139;
                }
            }
            else if (this$lotteryRate.equals(other$lotteryRate)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$lotteryInstruction = this.getLotteryInstruction();
        final Object other$lotteryInstruction = other.getLotteryInstruction();
        Label_0176: {
            if (this$lotteryInstruction == null) {
                if (other$lotteryInstruction == null) {
                    break Label_0176;
                }
            }
            else if (this$lotteryInstruction.equals(other$lotteryInstruction)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$lotteryRule = this.getLotteryRule();
        final Object other$lotteryRule = other.getLotteryRule();
        Label_0213: {
            if (this$lotteryRule == null) {
                if (other$lotteryRule == null) {
                    break Label_0213;
                }
            }
            else if (this$lotteryRule.equals(other$lotteryRule)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0250: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0250;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$buyPrice = this.getBuyPrice();
        final Object other$buyPrice = other.getBuyPrice();
        Label_0287: {
            if (this$buyPrice == null) {
                if (other$buyPrice == null) {
                    break Label_0287;
                }
            }
            else if (this$buyPrice.equals(other$buyPrice)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0324: {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0324;
                }
            }
            else if (this$coin.equals(other$coin)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0361: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0361;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$insuranceRate = this.getInsuranceRate();
        final Object other$insuranceRate = other.getInsuranceRate();
        if (this$insuranceRate == null) {
            if (other$insuranceRate == null) {
                return this.getDayWinnerNumLimit() == other.getDayWinnerNumLimit();
            }
        }
        else if (this$insuranceRate.equals(other$insuranceRate)) {
            return this.getDayWinnerNumLimit() == other.getDayWinnerNumLimit();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UnblockLotteryConfig;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $lotteryLevel = this.getLotteryLevel();
        result = result * 59 + (($lotteryLevel == null) ? 43 : $lotteryLevel.hashCode());
        final Object $lotteryRate = this.getLotteryRate();
        result = result * 59 + (($lotteryRate == null) ? 43 : $lotteryRate.hashCode());
        final Object $lotteryInstruction = this.getLotteryInstruction();
        result = result * 59 + (($lotteryInstruction == null) ? 43 : $lotteryInstruction.hashCode());
        final Object $lotteryRule = this.getLotteryRule();
        result = result * 59 + (($lotteryRule == null) ? 43 : $lotteryRule.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $buyPrice = this.getBuyPrice();
        result = result * 59 + (($buyPrice == null) ? 43 : $buyPrice.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $insuranceRate = this.getInsuranceRate();
        result = result * 59 + (($insuranceRate == null) ? 43 : $insuranceRate.hashCode());
        result = result * 59 + this.getDayWinnerNumLimit();
        return result;
    }
    
    @Override
    public String toString() {
        return "UnblockLotteryConfig(id=" + this.getId() + ", lotteryLevel=" + this.getLotteryLevel() + ", lotteryRate=" + this.getLotteryRate() + ", lotteryInstruction=" + this.getLotteryInstruction() + ", lotteryRule=" + this.getLotteryRule() + ", createTime=" + this.getCreateTime() + ", buyPrice=" + this.getBuyPrice() + ", coin=" + this.getCoin() + ", amount=" + this.getAmount() + ", insuranceRate=" + this.getInsuranceRate() + ", dayWinnerNumLimit=" + this.getDayWinnerNumLimit() + ")";
    }
}
