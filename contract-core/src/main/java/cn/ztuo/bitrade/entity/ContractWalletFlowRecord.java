package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;

import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractWalletFlowRecord implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    @JoinColumn(name = "coin_id")
    @ManyToOne
    private Coin coin;
    private BigDecimal amount;
    private BigDecimal afterBalance;
    private ContractWalletOperationType operationType;
    private long relationDetailId;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private String remark;
    private int contractType;

    public ContractWalletFlowRecord() {
        this.amount = BigDecimal.ZERO;
        this.afterBalance = BigDecimal.ZERO;
        this.contractType = 0;
    }

    public ContractWalletFlowRecord(final BigDecimal amount, final BigDecimal afterBalance, final ContractWalletOperationType operationType, final long relationDetailId, final String remark) {
        this.amount = BigDecimal.ZERO;
        this.afterBalance = BigDecimal.ZERO;
        this.contractType = 0;
        this.amount = amount;
        this.afterBalance = afterBalance;
        this.operationType = operationType;
        this.relationDetailId = relationDetailId;
        this.remark = remark;
        this.createTime = new Date();
        this.sequence = System.currentTimeMillis();
    }

    public long getId() {
        return this.id;
    }

    public Member getMember() {
        return this.member;
    }

    public Coin getCoin() {
        return this.coin;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getAfterBalance() {
        return this.afterBalance;
    }

    public ContractWalletOperationType getOperationType() {
        return this.operationType;
    }

    public long getRelationDetailId() {
        return this.relationDetailId;
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

    public int getContractType() {
        return this.contractType;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setCoin(final Coin coin) {
        this.coin = coin;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setAfterBalance(final BigDecimal afterBalance) {
        this.afterBalance = afterBalance;
    }

    public void setOperationType(final ContractWalletOperationType operationType) {
        this.operationType = operationType;
    }

    public void setRelationDetailId(final long relationDetailId) {
        this.relationDetailId = relationDetailId;
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

    public void setContractType(final int contractType) {
        this.contractType = contractType;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractWalletFlowRecord)) {
            return false;
        }
        final ContractWalletFlowRecord other = (ContractWalletFlowRecord) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0079:
        {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0079;
                }
            } else if (this$member.equals(other$member)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0116:
        {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0116;
                }
            } else if (this$coin.equals(other$coin)) {
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
        final Object this$afterBalance = this.getAfterBalance();
        final Object other$afterBalance = other.getAfterBalance();
        Label_0190:
        {
            if (this$afterBalance == null) {
                if (other$afterBalance == null) {
                    break Label_0190;
                }
            } else if (this$afterBalance.equals(other$afterBalance)) {
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
        if (this.getRelationDetailId() != other.getRelationDetailId()) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0292:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0292;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0292;
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
                return this.getContractType() == other.getContractType();
            }
        } else if (this$remark.equals(other$remark)) {
            return this.getContractType() == other.getContractType();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractWalletFlowRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $afterBalance = this.getAfterBalance();
        result = result * 59 + (($afterBalance == null) ? 43 : $afterBalance.hashCode());
        final Object $operationType = this.getOperationType();
        result = result * 59 + (($operationType == null) ? 43 : $operationType.hashCode());
        final long $relationDetailId = this.getRelationDetailId();
        result = result * 59 + (int) ($relationDetailId >>> 32 ^ $relationDetailId);
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        result = result * 59 + this.getContractType();
        return result;
    }

    @Override
    public String toString() {
        return "ContractWalletFlowRecord(id=" + this.getId() + ", member=" + this.getMember() + ", coin=" + this.getCoin() + ", amount=" + this.getAmount() + ", afterBalance=" + this.getAfterBalance() + ", operationType=" + this.getOperationType() + ", relationDetailId=" + this.getRelationDetailId() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", remark=" + this.getRemark() + ", contractType=" + this.getContractType() + ")";
    }
}
