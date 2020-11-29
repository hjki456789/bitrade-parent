package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "contract_double_direction_order_profit_info")
public class ContractDoubleDirectionOrderProfitInfo
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long orderId;
    private BigDecimal profit;
    private int days;
    private int remainDays;
    private int status;
    private Long sequence;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    public ContractDoubleDirectionOrderProfitInfo() {
        this.profit = BigDecimal.ZERO;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getOrderId() {
        return this.orderId;
    }
    
    public BigDecimal getProfit() {
        return this.profit;
    }
    
    public int getDays() {
        return this.days;
    }
    
    public int getRemainDays() {
        return this.remainDays;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }
    
    public void setProfit(final BigDecimal profit) {
        this.profit = profit;
    }
    
    public void setDays(final int days) {
        this.days = days;
    }
    
    public void setRemainDays(final int remainDays) {
        this.remainDays = remainDays;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractDoubleDirectionOrderProfitInfo)) {
            return false;
        }
        final ContractDoubleDirectionOrderProfitInfo other = (ContractDoubleDirectionOrderProfitInfo)o;
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
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        Label_0139: {
            if (this$orderId == null) {
                if (other$orderId == null) {
                    break Label_0139;
                }
            }
            else if (this$orderId.equals(other$orderId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$profit = this.getProfit();
        final Object other$profit = other.getProfit();
        Label_0176: {
            if (this$profit == null) {
                if (other$profit == null) {
                    break Label_0176;
                }
            }
            else if (this$profit.equals(other$profit)) {
                break Label_0176;
            }
            return false;
        }
        if (this.getDays() != other.getDays()) {
            return false;
        }
        if (this.getRemainDays() != other.getRemainDays()) {
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0252: {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0252;
                }
            }
            else if (this$sequence.equals(other$sequence)) {
                break Label_0252;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null) {
            if (other$createTime == null) {
                return true;
            }
        }
        else if (this$createTime.equals(other$createTime)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractDoubleDirectionOrderProfitInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $orderId = this.getOrderId();
        result = result * 59 + (($orderId == null) ? 43 : $orderId.hashCode());
        final Object $profit = this.getProfit();
        result = result * 59 + (($profit == null) ? 43 : $profit.hashCode());
        result = result * 59 + this.getDays();
        result = result * 59 + this.getRemainDays();
        result = result * 59 + this.getStatus();
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractDoubleDirectionOrderProfitInfo(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", orderId=" + this.getOrderId() + ", profit=" + this.getProfit() + ", days=" + this.getDays() + ", remainDays=" + this.getRemainDays() + ", status=" + this.getStatus() + ", sequence=" + this.getSequence() + ", createTime=" + this.getCreateTime() + ")";
    }
}
