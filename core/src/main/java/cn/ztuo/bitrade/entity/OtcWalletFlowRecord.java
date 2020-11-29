package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import cn.ztuo.bitrade.enums.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class OtcWalletFlowRecord implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    @JoinColumn(name = "coin_id")
    @ManyToOne
    private Coin coin;
    private OtcWalletBalanceType balanceType;
    private BigDecimal amount;
    private BigDecimal afterBalance;
    private OtcWalletOperationType operationType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private String remark;
    
    public OtcWalletFlowRecord() {
        this.amount = BigDecimal.ZERO;
        this.afterBalance = BigDecimal.ZERO;
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
    
    public OtcWalletBalanceType getBalanceType() {
        return this.balanceType;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public BigDecimal getAfterBalance() {
        return this.afterBalance;
    }
    
    public OtcWalletOperationType getOperationType() {
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
    
    public void setMember(final Member member) {
        this.member = member;
    }
    
    public void setCoin(final Coin coin) {
        this.coin = coin;
    }
    
    public void setBalanceType(final OtcWalletBalanceType balanceType) {
        this.balanceType = balanceType;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setAfterBalance(final BigDecimal afterBalance) {
        this.afterBalance = afterBalance;
    }
    
    public void setOperationType(final OtcWalletOperationType operationType) {
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
        if (!(o instanceof OtcWalletFlowRecord)) {
            return false;
        }
        final OtcWalletFlowRecord other = (OtcWalletFlowRecord)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0079: {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0079;
                }
            }
            else if (this$member.equals(other$member)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0116: {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0116;
                }
            }
            else if (this$coin.equals(other$coin)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$balanceType = this.getBalanceType();
        final Object other$balanceType = other.getBalanceType();
        Label_0153: {
            if (this$balanceType == null) {
                if (other$balanceType == null) {
                    break Label_0153;
                }
            }
            else if (this$balanceType.equals(other$balanceType)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0190: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0190;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$afterBalance = this.getAfterBalance();
        final Object other$afterBalance = other.getAfterBalance();
        Label_0227: {
            if (this$afterBalance == null) {
                if (other$afterBalance == null) {
                    break Label_0227;
                }
            }
            else if (this$afterBalance.equals(other$afterBalance)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$operationType = this.getOperationType();
        final Object other$operationType = other.getOperationType();
        Label_0264: {
            if (this$operationType == null) {
                if (other$operationType == null) {
                    break Label_0264;
                }
            }
            else if (this$operationType.equals(other$operationType)) {
                break Label_0264;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0301: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0301;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0301;
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
        return other instanceof OtcWalletFlowRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $balanceType = this.getBalanceType();
        result = result * 59 + (($balanceType == null) ? 43 : $balanceType.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
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
        return "OtcWalletFlowRecord(id=" + this.getId() + ", member=" + this.getMember() + ", coin=" + this.getCoin() + ", balanceType=" + this.getBalanceType() + ", amount=" + this.getAmount() + ", afterBalance=" + this.getAfterBalance() + ", operationType=" + this.getOperationType() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", remark=" + this.getRemark() + ")";
    }
}
