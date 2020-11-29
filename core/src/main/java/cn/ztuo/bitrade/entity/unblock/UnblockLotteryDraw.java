package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class UnblockLotteryDraw implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long transCount;
    private Long lotteryTransCount;
    private Long lotteryDrawCount;
    private Date createTime;
    private Date updateTime;
    @Transient
    private String username;
    @Transient
    private String mobilePhone;
    @Transient
    private String email;
    @Transient
    private Long lotteryBuyCount;
    @Transient
    private Long lotteryOverCount;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getTransCount() {
        return this.transCount;
    }
    
    public Long getLotteryTransCount() {
        return this.lotteryTransCount;
    }
    
    public Long getLotteryDrawCount() {
        return this.lotteryDrawCount;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getMobilePhone() {
        return this.mobilePhone;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public Long getLotteryBuyCount() {
        return this.lotteryBuyCount;
    }
    
    public Long getLotteryOverCount() {
        return this.lotteryOverCount;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setTransCount(final Long transCount) {
        this.transCount = transCount;
    }
    
    public void setLotteryTransCount(final Long lotteryTransCount) {
        this.lotteryTransCount = lotteryTransCount;
    }
    
    public void setLotteryDrawCount(final Long lotteryDrawCount) {
        this.lotteryDrawCount = lotteryDrawCount;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setLotteryBuyCount(final Long lotteryBuyCount) {
        this.lotteryBuyCount = lotteryBuyCount;
    }
    
    public void setLotteryOverCount(final Long lotteryOverCount) {
        this.lotteryOverCount = lotteryOverCount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnblockLotteryDraw)) {
            return false;
        }
        final UnblockLotteryDraw other = (UnblockLotteryDraw)o;
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
        final Object this$transCount = this.getTransCount();
        final Object other$transCount = other.getTransCount();
        Label_0139: {
            if (this$transCount == null) {
                if (other$transCount == null) {
                    break Label_0139;
                }
            }
            else if (this$transCount.equals(other$transCount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$lotteryTransCount = this.getLotteryTransCount();
        final Object other$lotteryTransCount = other.getLotteryTransCount();
        Label_0176: {
            if (this$lotteryTransCount == null) {
                if (other$lotteryTransCount == null) {
                    break Label_0176;
                }
            }
            else if (this$lotteryTransCount.equals(other$lotteryTransCount)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$lotteryDrawCount = this.getLotteryDrawCount();
        final Object other$lotteryDrawCount = other.getLotteryDrawCount();
        Label_0213: {
            if (this$lotteryDrawCount == null) {
                if (other$lotteryDrawCount == null) {
                    break Label_0213;
                }
            }
            else if (this$lotteryDrawCount.equals(other$lotteryDrawCount)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0250: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0250;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0287: {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0287;
                }
            }
            else if (this$updateTime.equals(other$updateTime)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0324: {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0324;
                }
            }
            else if (this$username.equals(other$username)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0361: {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0361;
                }
            }
            else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0398: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0398;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$lotteryBuyCount = this.getLotteryBuyCount();
        final Object other$lotteryBuyCount = other.getLotteryBuyCount();
        Label_0435: {
            if (this$lotteryBuyCount == null) {
                if (other$lotteryBuyCount == null) {
                    break Label_0435;
                }
            }
            else if (this$lotteryBuyCount.equals(other$lotteryBuyCount)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$lotteryOverCount = this.getLotteryOverCount();
        final Object other$lotteryOverCount = other.getLotteryOverCount();
        if (this$lotteryOverCount == null) {
            if (other$lotteryOverCount == null) {
                return true;
            }
        }
        else if (this$lotteryOverCount.equals(other$lotteryOverCount)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UnblockLotteryDraw;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $transCount = this.getTransCount();
        result = result * 59 + (($transCount == null) ? 43 : $transCount.hashCode());
        final Object $lotteryTransCount = this.getLotteryTransCount();
        result = result * 59 + (($lotteryTransCount == null) ? 43 : $lotteryTransCount.hashCode());
        final Object $lotteryDrawCount = this.getLotteryDrawCount();
        result = result * 59 + (($lotteryDrawCount == null) ? 43 : $lotteryDrawCount.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $mobilePhone = this.getMobilePhone();
        result = result * 59 + (($mobilePhone == null) ? 43 : $mobilePhone.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $lotteryBuyCount = this.getLotteryBuyCount();
        result = result * 59 + (($lotteryBuyCount == null) ? 43 : $lotteryBuyCount.hashCode());
        final Object $lotteryOverCount = this.getLotteryOverCount();
        result = result * 59 + (($lotteryOverCount == null) ? 43 : $lotteryOverCount.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UnblockLotteryDraw(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", transCount=" + this.getTransCount() + ", lotteryTransCount=" + this.getLotteryTransCount() + ", lotteryDrawCount=" + this.getLotteryDrawCount() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", username=" + this.getUsername() + ", mobilePhone=" + this.getMobilePhone() + ", email=" + this.getEmail() + ", lotteryBuyCount=" + this.getLotteryBuyCount() + ", lotteryOverCount=" + this.getLotteryOverCount() + ")";
    }
}
