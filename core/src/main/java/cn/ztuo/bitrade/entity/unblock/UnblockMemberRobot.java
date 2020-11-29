package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class UnblockMemberRobot implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private String symbol;
    private int status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Long version;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public Date getExpireTime() {
        return this.expireTime;
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
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setExpireTime(final Date expireTime) {
        this.expireTime = expireTime;
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
        if (!(o instanceof UnblockMemberRobot)) {
            return false;
        }
        final UnblockMemberRobot other = (UnblockMemberRobot)o;
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
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0139: {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0139;
                }
            }
            else if (this$symbol.equals(other$symbol)) {
                break Label_0139;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$expireTime = this.getExpireTime();
        final Object other$expireTime = other.getExpireTime();
        Label_0189: {
            if (this$expireTime == null) {
                if (other$expireTime == null) {
                    break Label_0189;
                }
            }
            else if (this$expireTime.equals(other$expireTime)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0226: {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0226;
                }
            }
            else if (this$updateTime.equals(other$updateTime)) {
                break Label_0226;
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
        return other instanceof UnblockMemberRobot;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        result = result * 59 + this.getStatus();
        final Object $expireTime = this.getExpireTime();
        result = result * 59 + (($expireTime == null) ? 43 : $expireTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UnblockMemberRobot(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", symbol=" + this.getSymbol() + ", status=" + this.getStatus() + ", expireTime=" + this.getExpireTime() + ", updateTime=" + this.getUpdateTime() + ", version=" + this.getVersion() + ")";
    }
}
