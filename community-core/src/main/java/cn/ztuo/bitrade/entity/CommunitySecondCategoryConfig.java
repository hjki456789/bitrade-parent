package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;

@Entity
@Table
public class CommunitySecondCategoryConfig implements Serializable
{
    @Id
    private String id;
    private String name;
    private String topId;
    private BigDecimal min;
    private BigDecimal max;
    private Integer orderId;
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTopId() {
        return this.topId;
    }
    
    public BigDecimal getMin() {
        return this.min;
    }
    
    public BigDecimal getMax() {
        return this.max;
    }
    
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setTopId(final String topId) {
        this.topId = topId;
    }
    
    public void setMin(final BigDecimal min) {
        this.min = min;
    }
    
    public void setMax(final BigDecimal max) {
        this.max = max;
    }
    
    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunitySecondCategoryConfig)) {
            return false;
        }
        final CommunitySecondCategoryConfig other = (CommunitySecondCategoryConfig)o;
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
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0102: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0102;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$topId = this.getTopId();
        final Object other$topId = other.getTopId();
        Label_0139: {
            if (this$topId == null) {
                if (other$topId == null) {
                    break Label_0139;
                }
            }
            else if (this$topId.equals(other$topId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$min = this.getMin();
        final Object other$min = other.getMin();
        Label_0176: {
            if (this$min == null) {
                if (other$min == null) {
                    break Label_0176;
                }
            }
            else if (this$min.equals(other$min)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$max = this.getMax();
        final Object other$max = other.getMax();
        Label_0213: {
            if (this$max == null) {
                if (other$max == null) {
                    break Label_0213;
                }
            }
            else if (this$max.equals(other$max)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        if (this$orderId == null) {
            if (other$orderId == null) {
                return true;
            }
        }
        else if (this$orderId.equals(other$orderId)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunitySecondCategoryConfig;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $topId = this.getTopId();
        result = result * 59 + (($topId == null) ? 43 : $topId.hashCode());
        final Object $min = this.getMin();
        result = result * 59 + (($min == null) ? 43 : $min.hashCode());
        final Object $max = this.getMax();
        result = result * 59 + (($max == null) ? 43 : $max.hashCode());
        final Object $orderId = this.getOrderId();
        result = result * 59 + (($orderId == null) ? 43 : $orderId.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunitySecondCategoryConfig(id=" + this.getId() + ", name=" + this.getName() + ", topId=" + this.getTopId() + ", min=" + this.getMin() + ", max=" + this.getMax() + ", orderId=" + this.getOrderId() + ")";
    }
}
