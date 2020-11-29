package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class VirtualRechargeFrozenFlow implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private Long memberId;
    private String coinId;
    private BigDecimal balance;
    private BigDecimal afterBalance;
    private VirtualRechargeFrozenFlowOperationType operationType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private String remark;
    
    public VirtualRechargeFrozenFlow() {
        this.balance = BigDecimal.ZERO;
        this.afterBalance = BigDecimal.ZERO;
    }
    
    public VirtualRechargeFrozenFlow(final Long memberId, final String coinId, final BigDecimal balance, final BigDecimal afterBalance, final VirtualRechargeFrozenFlowOperationType operationType) {
        this.balance = BigDecimal.ZERO;
        this.afterBalance = BigDecimal.ZERO;
        this.memberId = memberId;
        this.coinId = coinId;
        this.balance = balance;
        this.afterBalance = afterBalance;
        this.operationType = operationType;
        this.remark = operationType.getCnName();
        this.createTime = new Date();
        this.sequence = System.currentTimeMillis();
    }
    
    public long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public String getCoinId() {
        return this.coinId;
    }
    
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public BigDecimal getAfterBalance() {
        return this.afterBalance;
    }
    
    public VirtualRechargeFrozenFlowOperationType getOperationType() {
        return this.operationType;
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
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setCoinId(final String coinId) {
        this.coinId = coinId;
    }
    
    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }
    
    public void setAfterBalance(final BigDecimal afterBalance) {
        this.afterBalance = afterBalance;
    }
    
    public void setOperationType(final VirtualRechargeFrozenFlowOperationType operationType) {
        this.operationType = operationType;
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
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VirtualRechargeFrozenFlow)) {
            return false;
        }
        final VirtualRechargeFrozenFlow other = (VirtualRechargeFrozenFlow)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0079: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0079;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$coinId = this.getCoinId();
        final Object other$coinId = other.getCoinId();
        Label_0116: {
            if (this$coinId == null) {
                if (other$coinId == null) {
                    break Label_0116;
                }
            }
            else if (this$coinId.equals(other$coinId)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        Label_0153: {
            if (this$balance == null) {
                if (other$balance == null) {
                    break Label_0153;
                }
            }
            else if (this$balance.equals(other$balance)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$afterBalance = this.getAfterBalance();
        final Object other$afterBalance = other.getAfterBalance();
        Label_0190: {
            if (this$afterBalance == null) {
                if (other$afterBalance == null) {
                    break Label_0190;
                }
            }
            else if (this$afterBalance.equals(other$afterBalance)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$operationType = this.getOperationType();
        final Object other$operationType = other.getOperationType();
        Label_0227: {
            if (this$operationType == null) {
                if (other$operationType == null) {
                    break Label_0227;
                }
            }
            else if (this$operationType.equals(other$operationType)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0264: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0264;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0264;
            }
            return false;
        }
        if (this.getSequence() != other.getSequence()) {
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        if (this$remark == null) {
            if (other$remark == null) {
                return true;
            }
        }
        else if (this$remark.equals(other$remark)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof VirtualRechargeFrozenFlow;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $coinId = this.getCoinId();
        result = result * 59 + (($coinId == null) ? 43 : $coinId.hashCode());
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        final Object $afterBalance = this.getAfterBalance();
        result = result * 59 + (($afterBalance == null) ? 43 : $afterBalance.hashCode());
        final Object $operationType = this.getOperationType();
        result = result * 59 + (($operationType == null) ? 43 : $operationType.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int)($sequence >>> 32 ^ $sequence);
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "VirtualRechargeFrozenFlow(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", coinId=" + this.getCoinId() + ", balance=" + this.getBalance() + ", afterBalance=" + this.getAfterBalance() + ", operationType=" + this.getOperationType() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", remark=" + this.getRemark() + ")";
    }
}
