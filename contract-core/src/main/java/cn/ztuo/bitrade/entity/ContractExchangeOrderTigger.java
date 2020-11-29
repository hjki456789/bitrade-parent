package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderTigger implements Serializable
{
    @Id
    private long orderId;
    private BigDecimal openPrice;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    
    public ContractExchangeOrderTigger() {
        this.openPrice = BigDecimal.ZERO;
        this.version = 0L;
    }
    
    public long getOrderId() {
        return this.orderId;
    }
    
    public BigDecimal getOpenPrice() {
        return this.openPrice;
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
    
    public void setOpenPrice(final BigDecimal openPrice) {
        this.openPrice = openPrice;
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
        if (!(o instanceof ContractExchangeOrderTigger)) {
            return false;
        }
        final ContractExchangeOrderTigger other = (ContractExchangeOrderTigger)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getOrderId() != other.getOrderId()) {
            return false;
        }
        final Object this$openPrice = this.getOpenPrice();
        final Object other$openPrice = other.getOpenPrice();
        Label_0079: {
            if (this$openPrice == null) {
                if (other$openPrice == null) {
                    break Label_0079;
                }
            }
            else if (this$openPrice.equals(other$openPrice)) {
                break Label_0079;
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
        return other instanceof ContractExchangeOrderTigger;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $orderId = this.getOrderId();
        result = result * 59 + (int)($orderId >>> 32 ^ $orderId);
        final Object $openPrice = this.getOpenPrice();
        result = result * 59 + (($openPrice == null) ? 43 : $openPrice.hashCode());
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
        return "ContractExchangeOrderTigger(orderId=" + this.getOrderId() + ", openPrice=" + this.getOpenPrice() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
