package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderInterest implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @JoinColumn(name = "order_id")
    @ManyToOne
    private ContractExchangeOrder order;
    private BigDecimal balance;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;

    public ContractExchangeOrderInterest() {
        this.balance = BigDecimal.ZERO;
    }

    public long getId() {
        return this.id;
    }

    public ContractExchangeOrder getOrder() {
        return this.order;
    }

    public BigDecimal getBalance() {
        return this.balance;
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

    public void setId(final long id) {
        this.id = id;
    }

    public void setOrder(final ContractExchangeOrder order) {
        this.order = order;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
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
        if (!(o instanceof ContractExchangeOrderInterest)) {
            return false;
        }
        final ContractExchangeOrderInterest other = (ContractExchangeOrderInterest) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$order = this.getOrder();
        final Object other$order = other.getOrder();
        Label_0079:
        {
            if (this$order == null) {
                if (other$order == null) {
                    break Label_0079;
                }
            } else if (this$order.equals(other$order)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        Label_0116:
        {
            if (this$balance == null) {
                if (other$balance == null) {
                    break Label_0116;
                }
            } else if (this$balance.equals(other$balance)) {
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
                return this.getSequence() == other.getSequence();
            }
        } else if (this$createTime.equals(other$createTime)) {
            return this.getSequence() == other.getSequence();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractExchangeOrderInterest;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        final Object $order = this.getOrder();
        result = result * 59 + (($order == null) ? 43 : $order.hashCode());
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        return result;
    }

    @Override
    public String toString() {
        return "ContractExchangeOrderInterest(id=" + this.getId() + ", order=" + this.getOrder() + ", balance=" + this.getBalance() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
