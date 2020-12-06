package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderCalculate implements Serializable {
    @Id
    private long orderId;
    private BigDecimal symbolPrice;
    private BigDecimal profit;
    private BigDecimal rate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Long version;

    public ContractExchangeOrderCalculate() {
        this.symbolPrice = BigDecimal.ZERO;
        this.profit = BigDecimal.ZERO;
        this.rate = BigDecimal.ZERO;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public BigDecimal getSymbolPrice() {
        return this.symbolPrice;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public void setSymbolPrice(final BigDecimal symbolPrice) {
        this.symbolPrice = symbolPrice;
    }

    public void setProfit(final BigDecimal profit) {
        this.profit = profit;
    }

    public void setRate(final BigDecimal rate) {
        this.rate = rate;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractExchangeOrderCalculate)) {
            return false;
        }
        final ContractExchangeOrderCalculate other = (ContractExchangeOrderCalculate) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getOrderId() != other.getOrderId()) {
            return false;
        }
        final Object this$symbolPrice = this.getSymbolPrice();
        final Object other$symbolPrice = other.getSymbolPrice();
        Label_0079:
        {
            if (this$symbolPrice == null) {
                if (other$symbolPrice == null) {
                    break Label_0079;
                }
            } else if (this$symbolPrice.equals(other$symbolPrice)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$profit = this.getProfit();
        final Object other$profit = other.getProfit();
        Label_0116:
        {
            if (this$profit == null) {
                if (other$profit == null) {
                    break Label_0116;
                }
            } else if (this$profit.equals(other$profit)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$rate = this.getRate();
        final Object other$rate = other.getRate();
        Label_0153:
        {
            if (this$rate == null) {
                if (other$rate == null) {
                    break Label_0153;
                }
            } else if (this$rate.equals(other$rate)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0190:
        {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0190;
                }
            } else if (this$updateTime.equals(other$updateTime)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        if (this$version == null) {
            if (other$version == null) {
                return true;
            }
        } else if (this$version.equals(other$version)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractExchangeOrderCalculate;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $orderId = this.getOrderId();
        result = result * 59 + (int) ($orderId >>> 32 ^ $orderId);
        final Object $symbolPrice = this.getSymbolPrice();
        result = result * 59 + (($symbolPrice == null) ? 43 : $symbolPrice.hashCode());
        final Object $profit = this.getProfit();
        result = result * 59 + (($profit == null) ? 43 : $profit.hashCode());
        final Object $rate = this.getRate();
        result = result * 59 + (($rate == null) ? 43 : $rate.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractExchangeOrderCalculate(orderId=" + this.getOrderId() + ", symbolPrice=" + this.getSymbolPrice() + ", profit=" + this.getProfit() + ", rate=" + this.getRate() + ", updateTime=" + this.getUpdateTime() + ", version=" + this.getVersion() + ")";
    }
}
