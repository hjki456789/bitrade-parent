package cn.ztuo.bitrade.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class CommunityTopCategoryConfig implements Serializable
{
    @Id
    private String id;
    private String name;
    private Integer orderId;
    private Integer selectMore;
    @Transient
    private List<CommunitySecondCategoryConfig> secondCategoryConfigs;
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public Integer getSelectMore() {
        return this.selectMore;
    }
    
    public List<CommunitySecondCategoryConfig> getSecondCategoryConfigs() {
        return this.secondCategoryConfigs;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }
    
    public void setSelectMore(final Integer selectMore) {
        this.selectMore = selectMore;
    }
    
    public void setSecondCategoryConfigs(final List<CommunitySecondCategoryConfig> secondCategoryConfigs) {
        this.secondCategoryConfigs = secondCategoryConfigs;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityTopCategoryConfig)) {
            return false;
        }
        final CommunityTopCategoryConfig other = (CommunityTopCategoryConfig)o;
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
        final Object this$selectMore = this.getSelectMore();
        final Object other$selectMore = other.getSelectMore();
        Label_0176: {
            if (this$selectMore == null) {
                if (other$selectMore == null) {
                    break Label_0176;
                }
            }
            else if (this$selectMore.equals(other$selectMore)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$secondCategoryConfigs = this.getSecondCategoryConfigs();
        final Object other$secondCategoryConfigs = other.getSecondCategoryConfigs();
        if (this$secondCategoryConfigs == null) {
            if (other$secondCategoryConfigs == null) {
                return true;
            }
        }
        else if (this$secondCategoryConfigs.equals(other$secondCategoryConfigs)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityTopCategoryConfig;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $orderId = this.getOrderId();
        result = result * 59 + (($orderId == null) ? 43 : $orderId.hashCode());
        final Object $selectMore = this.getSelectMore();
        result = result * 59 + (($selectMore == null) ? 43 : $selectMore.hashCode());
        final Object $secondCategoryConfigs = this.getSecondCategoryConfigs();
        result = result * 59 + (($secondCategoryConfigs == null) ? 43 : $secondCategoryConfigs.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityTopCategoryConfig(id=" + this.getId() + ", name=" + this.getName() + ", orderId=" + this.getOrderId() + ", selectMore=" + this.getSelectMore() + ", secondCategoryConfigs=" + this.getSecondCategoryConfigs() + ")";
    }
}
