package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

@Entity
@Table
public class CommunityTraderProfitRecord implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private String coinId;
    private BigDecimal rate;
    private BigDecimal amount;
    private Long fromContractOrderId;
    private Long fromMemberId;
    private String belongDay;
    private Date createTime;
    private Long sequence;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public String getCoinId() {
        return this.coinId;
    }
    
    public BigDecimal getRate() {
        return this.rate;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public Long getFromContractOrderId() {
        return this.fromContractOrderId;
    }
    
    public Long getFromMemberId() {
        return this.fromMemberId;
    }
    
    public String getBelongDay() {
        return this.belongDay;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setCoinId(final String coinId) {
        this.coinId = coinId;
    }
    
    public void setRate(final BigDecimal rate) {
        this.rate = rate;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setFromContractOrderId(final Long fromContractOrderId) {
        this.fromContractOrderId = fromContractOrderId;
    }
    
    public void setFromMemberId(final Long fromMemberId) {
        this.fromMemberId = fromMemberId;
    }
    
    public void setBelongDay(final String belongDay) {
        this.belongDay = belongDay;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityTraderProfitRecord)) {
            return false;
        }
        final CommunityTraderProfitRecord other = (CommunityTraderProfitRecord)o;
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
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0102: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0102;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$coinId = this.getCoinId();
        final Object other$coinId = other.getCoinId();
        Label_0139: {
            if (this$coinId == null) {
                if (other$coinId == null) {
                    break Label_0139;
                }
            }
            else if (this$coinId.equals(other$coinId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$rate = this.getRate();
        final Object other$rate = other.getRate();
        Label_0176: {
            if (this$rate == null) {
                if (other$rate == null) {
                    break Label_0176;
                }
            }
            else if (this$rate.equals(other$rate)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0213: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0213;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$fromContractOrderId = this.getFromContractOrderId();
        final Object other$fromContractOrderId = other.getFromContractOrderId();
        Label_0250: {
            if (this$fromContractOrderId == null) {
                if (other$fromContractOrderId == null) {
                    break Label_0250;
                }
            }
            else if (this$fromContractOrderId.equals(other$fromContractOrderId)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$fromMemberId = this.getFromMemberId();
        final Object other$fromMemberId = other.getFromMemberId();
        Label_0287: {
            if (this$fromMemberId == null) {
                if (other$fromMemberId == null) {
                    break Label_0287;
                }
            }
            else if (this$fromMemberId.equals(other$fromMemberId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$belongDay = this.getBelongDay();
        final Object other$belongDay = other.getBelongDay();
        Label_0324: {
            if (this$belongDay == null) {
                if (other$belongDay == null) {
                    break Label_0324;
                }
            }
            else if (this$belongDay.equals(other$belongDay)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0361: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0361;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        if (this$sequence == null) {
            if (other$sequence == null) {
                return true;
            }
        }
        else if (this$sequence.equals(other$sequence)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityTraderProfitRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $coinId = this.getCoinId();
        result = result * 59 + (($coinId == null) ? 43 : $coinId.hashCode());
        final Object $rate = this.getRate();
        result = result * 59 + (($rate == null) ? 43 : $rate.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $fromContractOrderId = this.getFromContractOrderId();
        result = result * 59 + (($fromContractOrderId == null) ? 43 : $fromContractOrderId.hashCode());
        final Object $fromMemberId = this.getFromMemberId();
        result = result * 59 + (($fromMemberId == null) ? 43 : $fromMemberId.hashCode());
        final Object $belongDay = this.getBelongDay();
        result = result * 59 + (($belongDay == null) ? 43 : $belongDay.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityTraderProfitRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", coinId=" + this.getCoinId() + ", rate=" + this.getRate() + ", amount=" + this.getAmount() + ", fromContractOrderId=" + this.getFromContractOrderId() + ", fromMemberId=" + this.getFromMemberId() + ", belongDay=" + this.getBelongDay() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
