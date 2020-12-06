package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;

import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractWalletFrozenFlowRecord implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private Long memberId;
    private String coinId;
    private BigDecimal amount;
    private BigDecimal afterFrozenBalance;
    private ContractWalletFrozenOperationType operationType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private String remark;

    public ContractWalletFrozenFlowRecord() {
        this.amount = BigDecimal.ZERO;
        this.afterFrozenBalance = BigDecimal.ZERO;
    }

    public ContractWalletFrozenFlowRecord(final Long memberId, final String coinId, final BigDecimal amount, final BigDecimal afterFrozenBalance, final ContractWalletFrozenOperationType operationType, final String remark) {
        this.amount = BigDecimal.ZERO;
        this.afterFrozenBalance = BigDecimal.ZERO;
        this.memberId = memberId;
        this.coinId = coinId;
        this.amount = amount;
        this.afterFrozenBalance = afterFrozenBalance;
        this.operationType = operationType;
        this.createTime = new Date();
        this.sequence = System.currentTimeMillis();
        this.remark = remark;
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

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getAfterFrozenBalance() {
        return this.afterFrozenBalance;
    }

    public ContractWalletFrozenOperationType getOperationType() {
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

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setAfterFrozenBalance(final BigDecimal afterFrozenBalance) {
        this.afterFrozenBalance = afterFrozenBalance;
    }

    public void setOperationType(final ContractWalletFrozenOperationType operationType) {
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
        if (!(o instanceof ContractWalletFrozenFlowRecord)) {
            return false;
        }
        final ContractWalletFrozenFlowRecord other = (ContractWalletFrozenFlowRecord) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0079:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0079;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$coinId = this.getCoinId();
        final Object other$coinId = other.getCoinId();
        Label_0116:
        {
            if (this$coinId == null) {
                if (other$coinId == null) {
                    break Label_0116;
                }
            } else if (this$coinId.equals(other$coinId)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0153:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0153;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$afterFrozenBalance = this.getAfterFrozenBalance();
        final Object other$afterFrozenBalance = other.getAfterFrozenBalance();
        Label_0190:
        {
            if (this$afterFrozenBalance == null) {
                if (other$afterFrozenBalance == null) {
                    break Label_0190;
                }
            } else if (this$afterFrozenBalance.equals(other$afterFrozenBalance)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$operationType = this.getOperationType();
        final Object other$operationType = other.getOperationType();
        Label_0227:
        {
            if (this$operationType == null) {
                if (other$operationType == null) {
                    break Label_0227;
                }
            } else if (this$operationType.equals(other$operationType)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0264:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0264;
                }
            } else if (this$createTime.equals(other$createTime)) {
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
        } else if (this$remark.equals(other$remark)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractWalletFrozenFlowRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $coinId = this.getCoinId();
        result = result * 59 + (($coinId == null) ? 43 : $coinId.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $afterFrozenBalance = this.getAfterFrozenBalance();
        result = result * 59 + (($afterFrozenBalance == null) ? 43 : $afterFrozenBalance.hashCode());
        final Object $operationType = this.getOperationType();
        result = result * 59 + (($operationType == null) ? 43 : $operationType.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractWalletFrozenFlowRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", coinId=" + this.getCoinId() + ", amount=" + this.getAmount() + ", afterFrozenBalance=" + this.getAfterFrozenBalance() + ", operationType=" + this.getOperationType() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", remark=" + this.getRemark() + ")";
    }
}
