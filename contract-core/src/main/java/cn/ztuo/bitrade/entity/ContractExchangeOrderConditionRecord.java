package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;

import cn.ztuo.bitrade.constant.*;

import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractExchangeOrderConditionRecord implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @JoinColumn(name = "order_id")
    @ManyToOne
    private ContractExchangeOrder order;
    private BooleanEnum ifStopLoss;
    private BigDecimal stopLossPrice;
    private BooleanEnum ifStopProfit;
    private BigDecimal stopProfitPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;

    public ContractExchangeOrderConditionRecord() {
        this.stopLossPrice = BigDecimal.ZERO;
        this.stopProfitPrice = BigDecimal.ZERO;
    }

    public long getId() {
        return this.id;
    }

    public ContractExchangeOrder getOrder() {
        return this.order;
    }

    public BooleanEnum getIfStopLoss() {
        return this.ifStopLoss;
    }

    public BigDecimal getStopLossPrice() {
        return this.stopLossPrice;
    }

    public BooleanEnum getIfStopProfit() {
        return this.ifStopProfit;
    }

    public BigDecimal getStopProfitPrice() {
        return this.stopProfitPrice;
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

    public void setIfStopLoss(final BooleanEnum ifStopLoss) {
        this.ifStopLoss = ifStopLoss;
    }

    public void setStopLossPrice(final BigDecimal stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public void setIfStopProfit(final BooleanEnum ifStopProfit) {
        this.ifStopProfit = ifStopProfit;
    }

    public void setStopProfitPrice(final BigDecimal stopProfitPrice) {
        this.stopProfitPrice = stopProfitPrice;
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
        if (!(o instanceof ContractExchangeOrderConditionRecord)) {
            return false;
        }
        final ContractExchangeOrderConditionRecord other = (ContractExchangeOrderConditionRecord) o;
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
        final Object this$ifStopLoss = this.getIfStopLoss();
        final Object other$ifStopLoss = other.getIfStopLoss();
        Label_0116:
        {
            if (this$ifStopLoss == null) {
                if (other$ifStopLoss == null) {
                    break Label_0116;
                }
            } else if (this$ifStopLoss.equals(other$ifStopLoss)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$stopLossPrice = this.getStopLossPrice();
        final Object other$stopLossPrice = other.getStopLossPrice();
        Label_0153:
        {
            if (this$stopLossPrice == null) {
                if (other$stopLossPrice == null) {
                    break Label_0153;
                }
            } else if (this$stopLossPrice.equals(other$stopLossPrice)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$ifStopProfit = this.getIfStopProfit();
        final Object other$ifStopProfit = other.getIfStopProfit();
        Label_0190:
        {
            if (this$ifStopProfit == null) {
                if (other$ifStopProfit == null) {
                    break Label_0190;
                }
            } else if (this$ifStopProfit.equals(other$ifStopProfit)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$stopProfitPrice = this.getStopProfitPrice();
        final Object other$stopProfitPrice = other.getStopProfitPrice();
        Label_0227:
        {
            if (this$stopProfitPrice == null) {
                if (other$stopProfitPrice == null) {
                    break Label_0227;
                }
            } else if (this$stopProfitPrice.equals(other$stopProfitPrice)) {
                break Label_0227;
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
        return other instanceof ContractExchangeOrderConditionRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        final Object $order = this.getOrder();
        result = result * 59 + (($order == null) ? 43 : $order.hashCode());
        final Object $ifStopLoss = this.getIfStopLoss();
        result = result * 59 + (($ifStopLoss == null) ? 43 : $ifStopLoss.hashCode());
        final Object $stopLossPrice = this.getStopLossPrice();
        result = result * 59 + (($stopLossPrice == null) ? 43 : $stopLossPrice.hashCode());
        final Object $ifStopProfit = this.getIfStopProfit();
        result = result * 59 + (($ifStopProfit == null) ? 43 : $ifStopProfit.hashCode());
        final Object $stopProfitPrice = this.getStopProfitPrice();
        result = result * 59 + (($stopProfitPrice == null) ? 43 : $stopProfitPrice.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        return result;
    }

    @Override
    public String toString() {
        return "ContractExchangeOrderConditionRecord(id=" + this.getId() + ", order=" + this.getOrder() + ", ifStopLoss=" + this.getIfStopLoss() + ", stopLossPrice=" + this.getStopLossPrice() + ", ifStopProfit=" + this.getIfStopProfit() + ", stopProfitPrice=" + this.getStopProfitPrice() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
