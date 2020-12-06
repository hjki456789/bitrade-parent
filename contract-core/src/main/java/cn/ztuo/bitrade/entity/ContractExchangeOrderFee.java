package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderFee implements Serializable {
    @Id
    private long orderId;
    private BigDecimal openFee;
    private BigDecimal closeFee;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private int contractType;

    public ContractExchangeOrderFee() {
        this.openFee = BigDecimal.ZERO;
        this.closeFee = BigDecimal.ZERO;
        this.contractType = 0;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public BigDecimal getOpenFee() {
        return this.openFee;
    }

    public BigDecimal getCloseFee() {
        return this.closeFee;
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

    public int getContractType() {
        return this.contractType;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public void setOpenFee(final BigDecimal openFee) {
        this.openFee = openFee;
    }

    public void setCloseFee(final BigDecimal closeFee) {
        this.closeFee = closeFee;
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

    public void setContractType(final int contractType) {
        this.contractType = contractType;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractExchangeOrderFee)) {
            return false;
        }
        final ContractExchangeOrderFee other = (ContractExchangeOrderFee) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getOrderId() != other.getOrderId()) {
            return false;
        }
        final Object this$openFee = this.getOpenFee();
        final Object other$openFee = other.getOpenFee();
        Label_0079:
        {
            if (this$openFee == null) {
                if (other$openFee == null) {
                    break Label_0079;
                }
            } else if (this$openFee.equals(other$openFee)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$closeFee = this.getCloseFee();
        final Object other$closeFee = other.getCloseFee();
        Label_0116:
        {
            if (this$closeFee == null) {
                if (other$closeFee == null) {
                    break Label_0116;
                }
            } else if (this$closeFee.equals(other$closeFee)) {
                break Label_0116;
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
                return this.getSequence() == other.getSequence() && this.getContractType() == other.getContractType();
            }
        } else if (this$createTime.equals(other$createTime)) {
            return this.getSequence() == other.getSequence() && this.getContractType() == other.getContractType();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractExchangeOrderFee;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $orderId = this.getOrderId();
        result = result * 59 + (int) ($orderId >>> 32 ^ $orderId);
        final Object $openFee = this.getOpenFee();
        result = result * 59 + (($openFee == null) ? 43 : $openFee.hashCode());
        final Object $closeFee = this.getCloseFee();
        result = result * 59 + (($closeFee == null) ? 43 : $closeFee.hashCode());
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        result = result * 59 + this.getContractType();
        return result;
    }

    @Override
    public String toString() {
        return "ContractExchangeOrderFee(orderId=" + this.getOrderId() + ", openFee=" + this.getOpenFee() + ", closeFee=" + this.getCloseFee() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", contractType=" + this.getContractType() + ")";
    }
}
