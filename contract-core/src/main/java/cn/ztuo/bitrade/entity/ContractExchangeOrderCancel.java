package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;

import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderCancel implements Serializable {
    @Id
    private long orderId;
    private ContractExchangeOrderCancelType type;
    private String reasonMsg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;

    public long getOrderId() {
        return this.orderId;
    }

    public ContractExchangeOrderCancelType getType() {
        return this.type;
    }

    public String getReasonMsg() {
        return this.reasonMsg;
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

    public void setType(final ContractExchangeOrderCancelType type) {
        this.type = type;
    }

    public void setReasonMsg(final String reasonMsg) {
        this.reasonMsg = reasonMsg;
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
        if (!(o instanceof ContractExchangeOrderCancel)) {
            return false;
        }
        final ContractExchangeOrderCancel other = (ContractExchangeOrderCancel) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getOrderId() != other.getOrderId()) {
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0079:
        {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0079;
                }
            } else if (this$type.equals(other$type)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$reasonMsg = this.getReasonMsg();
        final Object other$reasonMsg = other.getReasonMsg();
        Label_0116:
        {
            if (this$reasonMsg == null) {
                if (other$reasonMsg == null) {
                    break Label_0116;
                }
            } else if (this$reasonMsg.equals(other$reasonMsg)) {
                break Label_0116;
            }
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
        return other instanceof ContractExchangeOrderCancel;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $orderId = this.getOrderId();
        result = result * 59 + (int) ($orderId >>> 32 ^ $orderId);
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $reasonMsg = this.getReasonMsg();
        result = result * 59 + (($reasonMsg == null) ? 43 : $reasonMsg.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        return result;
    }

    @Override
    public String toString() {
        return "ContractExchangeOrderCancel(orderId=" + this.getOrderId() + ", type=" + this.getType() + ", reasonMsg=" + this.getReasonMsg() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
