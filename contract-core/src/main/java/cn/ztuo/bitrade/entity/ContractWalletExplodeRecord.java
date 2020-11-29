package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import java.math.*;

@Entity
@Table
public class ContractWalletExplodeRecord implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private long memberId;
    private String coin;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private String remark;
    private BigDecimal riskRate;
    private BigDecimal frozenAmount;
    private BigDecimal beforeBalance;
    private BigDecimal afterBalance;
    
    public ContractWalletExplodeRecord() {
        this.beforeBalance = BigDecimal.ZERO;
        this.afterBalance = BigDecimal.ZERO;
    }
    
    public long getId() {
        return this.id;
    }
    
    public long getMemberId() {
        return this.memberId;
    }
    
    public String getCoin() {
        return this.coin;
    }
    
    public long getVersion() {
        return this.version;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public long getSequence() {
        return this.sequence;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public BigDecimal getRiskRate() {
        return this.riskRate;
    }
    
    public BigDecimal getFrozenAmount() {
        return this.frozenAmount;
    }
    
    public BigDecimal getBeforeBalance() {
        return this.beforeBalance;
    }
    
    public BigDecimal getAfterBalance() {
        return this.afterBalance;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setMemberId(final long memberId) {
        this.memberId = memberId;
    }
    
    public void setCoin(final String coin) {
        this.coin = coin;
    }
    
    public void setVersion(final long version) {
        this.version = version;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setRiskRate(final BigDecimal riskRate) {
        this.riskRate = riskRate;
    }
    
    public void setFrozenAmount(final BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
    
    public void setBeforeBalance(final BigDecimal beforeBalance) {
        this.beforeBalance = beforeBalance;
    }
    
    public void setAfterBalance(final BigDecimal afterBalance) {
        this.afterBalance = afterBalance;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractWalletExplodeRecord)) {
            return false;
        }
        final ContractWalletExplodeRecord other = (ContractWalletExplodeRecord)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        if (this.getMemberId() != other.getMemberId()) {
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0093: {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0093;
                }
            }
            else if (this$coin.equals(other$coin)) {
                break Label_0093;
            }
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0144: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0144;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0144;
            }
            return false;
        }
        if (this.getSequence() != other.getSequence()) {
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0195: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0195;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0195;
            }
            return false;
        }
        final Object this$riskRate = this.getRiskRate();
        final Object other$riskRate = other.getRiskRate();
        Label_0232: {
            if (this$riskRate == null) {
                if (other$riskRate == null) {
                    break Label_0232;
                }
            }
            else if (this$riskRate.equals(other$riskRate)) {
                break Label_0232;
            }
            return false;
        }
        final Object this$frozenAmount = this.getFrozenAmount();
        final Object other$frozenAmount = other.getFrozenAmount();
        Label_0269: {
            if (this$frozenAmount == null) {
                if (other$frozenAmount == null) {
                    break Label_0269;
                }
            }
            else if (this$frozenAmount.equals(other$frozenAmount)) {
                break Label_0269;
            }
            return false;
        }
        final Object this$beforeBalance = this.getBeforeBalance();
        final Object other$beforeBalance = other.getBeforeBalance();
        Label_0306: {
            if (this$beforeBalance == null) {
                if (other$beforeBalance == null) {
                    break Label_0306;
                }
            }
            else if (this$beforeBalance.equals(other$beforeBalance)) {
                break Label_0306;
            }
            return false;
        }
        final Object this$afterBalance = this.getAfterBalance();
        final Object other$afterBalance = other.getAfterBalance();
        if (this$afterBalance == null) {
            if (other$afterBalance == null) {
                return true;
            }
        }
        else if (this$afterBalance.equals(other$afterBalance)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractWalletExplodeRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final long $memberId = this.getMemberId();
        result = result * 59 + (int)($memberId >>> 32 ^ $memberId);
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final long $version = this.getVersion();
        result = result * 59 + (int)($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int)($sequence >>> 32 ^ $sequence);
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $riskRate = this.getRiskRate();
        result = result * 59 + (($riskRate == null) ? 43 : $riskRate.hashCode());
        final Object $frozenAmount = this.getFrozenAmount();
        result = result * 59 + (($frozenAmount == null) ? 43 : $frozenAmount.hashCode());
        final Object $beforeBalance = this.getBeforeBalance();
        result = result * 59 + (($beforeBalance == null) ? 43 : $beforeBalance.hashCode());
        final Object $afterBalance = this.getAfterBalance();
        result = result * 59 + (($afterBalance == null) ? 43 : $afterBalance.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractWalletExplodeRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", coin=" + this.getCoin() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", remark=" + this.getRemark() + ", riskRate=" + this.getRiskRate() + ", frozenAmount=" + this.getFrozenAmount() + ", beforeBalance=" + this.getBeforeBalance() + ", afterBalance=" + this.getAfterBalance() + ")";
    }
}
