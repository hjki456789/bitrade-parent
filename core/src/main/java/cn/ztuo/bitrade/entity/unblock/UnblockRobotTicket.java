package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class UnblockRobotTicket implements Serializable
{
    @Id
    private String id;
    private int status;
    private int effectiveDay;
    private Date createTime;
    private Long memberId;
    private Date useTime;
    
    public String getId() {
        return this.id;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getEffectiveDay() {
        return this.effectiveDay;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Date getUseTime() {
        return this.useTime;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setEffectiveDay(final int effectiveDay) {
        this.effectiveDay = effectiveDay;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setUseTime(final Date useTime) {
        this.useTime = useTime;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnblockRobotTicket)) {
            return false;
        }
        final UnblockRobotTicket other = (UnblockRobotTicket)o;
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
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        if (this.getEffectiveDay() != other.getEffectiveDay()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0128: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0128;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0128;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0165: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0165;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0165;
            }
            return false;
        }
        final Object this$useTime = this.getUseTime();
        final Object other$useTime = other.getUseTime();
        if (this$useTime == null) {
            if (other$useTime == null) {
                return true;
            }
        }
        else if (this$useTime.equals(other$useTime)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UnblockRobotTicket;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        result = result * 59 + this.getStatus();
        result = result * 59 + this.getEffectiveDay();
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $useTime = this.getUseTime();
        result = result * 59 + (($useTime == null) ? 43 : $useTime.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UnblockRobotTicket(id=" + this.getId() + ", status=" + this.getStatus() + ", effectiveDay=" + this.getEffectiveDay() + ", createTime=" + this.getCreateTime() + ", memberId=" + this.getMemberId() + ", useTime=" + this.getUseTime() + ")";
    }
}
