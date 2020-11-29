package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class UnblockCommonConfig implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String value;
    private String remark;
    private Integer orderId;
    private Date updateTime;
    private Long version;
    
    public Long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public Long getVersion() {
        return this.version;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
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
        if (!(o instanceof UnblockCommonConfig)) {
            return false;
        }
        final UnblockCommonConfig other = (UnblockCommonConfig)o;
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
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        Label_0139: {
            if (this$value == null) {
                if (other$value == null) {
                    break Label_0139;
                }
            }
            else if (this$value.equals(other$value)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0176: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0176;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        Label_0213: {
            if (this$orderId == null) {
                if (other$orderId == null) {
                    break Label_0213;
                }
            }
            else if (this$orderId.equals(other$orderId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0250: {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0250;
                }
            }
            else if (this$updateTime.equals(other$updateTime)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        if (this$version == null) {
            if (other$version == null) {
                return true;
            }
        }
        else if (this$version.equals(other$version)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UnblockCommonConfig;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $value = this.getValue();
        result = result * 59 + (($value == null) ? 43 : $value.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $orderId = this.getOrderId();
        result = result * 59 + (($orderId == null) ? 43 : $orderId.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UnblockCommonConfig(id=" + this.getId() + ", name=" + this.getName() + ", value=" + this.getValue() + ", remark=" + this.getRemark() + ", orderId=" + this.getOrderId() + ", updateTime=" + this.getUpdateTime() + ", version=" + this.getVersion() + ")";
    }
}
