package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderClose implements Serializable
{
    @Id
    private long orderId;
    private BigDecimal closePrice;
    private BigDecimal profitOrLoss;
    private BigDecimal closeProfitOrLoss;
    private ContractExchangeOrderCloseType type;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    
    public ContractExchangeOrderClose() {
        this.closePrice = BigDecimal.ZERO;
        this.profitOrLoss = BigDecimal.ZERO;
        this.closeProfitOrLoss = BigDecimal.ZERO;
    }
    
    public long getOrderId() {
        return this.orderId;
    }
    
    public BigDecimal getClosePrice() {
        return this.closePrice;
    }
    
    public BigDecimal getProfitOrLoss() {
        return this.profitOrLoss;
    }
    
    public BigDecimal getCloseProfitOrLoss() {
        return this.closeProfitOrLoss;
    }
    
    public ContractExchangeOrderCloseType getType() {
        return this.type;
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
    
    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }
    
    public void setClosePrice(final BigDecimal closePrice) {
        this.closePrice = closePrice;
    }
    
    public void setProfitOrLoss(final BigDecimal profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }
    
    public void setCloseProfitOrLoss(final BigDecimal closeProfitOrLoss) {
        this.closeProfitOrLoss = closeProfitOrLoss;
    }
    
    public void setType(final ContractExchangeOrderCloseType type) {
        this.type = type;
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
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractExchangeOrderClose)) {
            return false;
        }
        final ContractExchangeOrderClose other = (ContractExchangeOrderClose)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getOrderId() != other.getOrderId()) {
            return false;
        }
        final Object this$closePrice = this.getClosePrice();
        final Object other$closePrice = other.getClosePrice();
        Label_0079: {
            if (this$closePrice == null) {
                if (other$closePrice == null) {
                    break Label_0079;
                }
            }
            else if (this$closePrice.equals(other$closePrice)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$profitOrLoss = this.getProfitOrLoss();
        final Object other$profitOrLoss = other.getProfitOrLoss();
        Label_0116: {
            if (this$profitOrLoss == null) {
                if (other$profitOrLoss == null) {
                    break Label_0116;
                }
            }
            else if (this$profitOrLoss.equals(other$profitOrLoss)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$closeProfitOrLoss = this.getCloseProfitOrLoss();
        final Object other$closeProfitOrLoss = other.getCloseProfitOrLoss();
        Label_0153: {
            if (this$closeProfitOrLoss == null) {
                if (other$closeProfitOrLoss == null) {
                    break Label_0153;
                }
            }
            else if (this$closeProfitOrLoss.equals(other$closeProfitOrLoss)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0190: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0190;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0190;
            }
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null) {
            if (other$createTime == null) {
                return this.getSequence() == other.getSequence();
            }
        }
        else if (this$createTime.equals(other$createTime)) {
            return this.getSequence() == other.getSequence();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractExchangeOrderClose;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $orderId = this.getOrderId();
        result = result * 59 + (int)($orderId >>> 32 ^ $orderId);
        final Object $closePrice = this.getClosePrice();
        result = result * 59 + (($closePrice == null) ? 43 : $closePrice.hashCode());
        final Object $profitOrLoss = this.getProfitOrLoss();
        result = result * 59 + (($profitOrLoss == null) ? 43 : $profitOrLoss.hashCode());
        final Object $closeProfitOrLoss = this.getCloseProfitOrLoss();
        result = result * 59 + (($closeProfitOrLoss == null) ? 43 : $closeProfitOrLoss.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final long $version = this.getVersion();
        result = result * 59 + (int)($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int)($sequence >>> 32 ^ $sequence);
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractExchangeOrderClose(orderId=" + this.getOrderId() + ", closePrice=" + this.getClosePrice() + ", profitOrLoss=" + this.getProfitOrLoss() + ", closeProfitOrLoss=" + this.getCloseProfitOrLoss() + ", type=" + this.getType() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
