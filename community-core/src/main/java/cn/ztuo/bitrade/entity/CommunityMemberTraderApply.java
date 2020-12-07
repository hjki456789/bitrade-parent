package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import java.util.*;

@Entity
@Table
public class CommunityMemberTraderApply implements Serializable
{
    @Id
    private String id;
    private Long memberId;
    private CommunityMemberTraderApplyStatus status;
    private String remark;
    private Date replyTime;
    private Date createTime;
    private Long sequence;
    private String realName;
    private String cardId;
    private String phone;
    
    public String getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public CommunityMemberTraderApplyStatus getStatus() {
        return this.status;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public Date getReplyTime() {
        return this.replyTime;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    
    public String getRealName() {
        return this.realName;
    }
    
    public String getCardId() {
        return this.cardId;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setStatus(final CommunityMemberTraderApplyStatus status) {
        this.status = status;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setReplyTime(final Date replyTime) {
        this.replyTime = replyTime;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    public void setRealName(final String realName) {
        this.realName = realName;
    }
    
    public void setCardId(final String cardId) {
        this.cardId = cardId;
    }
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityMemberTraderApply)) {
            return false;
        }
        final CommunityMemberTraderApply other = (CommunityMemberTraderApply)o;
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
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0139: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0139;
                }
            }
            else if (this$status.equals(other$status)) {
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
        final Object this$replyTime = this.getReplyTime();
        final Object other$replyTime = other.getReplyTime();
        Label_0213: {
            if (this$replyTime == null) {
                if (other$replyTime == null) {
                    break Label_0213;
                }
            }
            else if (this$replyTime.equals(other$replyTime)) {
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
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0287: {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0287;
                }
            }
            else if (this$sequence.equals(other$sequence)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$realName = this.getRealName();
        final Object other$realName = other.getRealName();
        Label_0324: {
            if (this$realName == null) {
                if (other$realName == null) {
                    break Label_0324;
                }
            }
            else if (this$realName.equals(other$realName)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$cardId = this.getCardId();
        final Object other$cardId = other.getCardId();
        Label_0361: {
            if (this$cardId == null) {
                if (other$cardId == null) {
                    break Label_0361;
                }
            }
            else if (this$cardId.equals(other$cardId)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null) {
            if (other$phone == null) {
                return true;
            }
        }
        else if (this$phone.equals(other$phone)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityMemberTraderApply;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $replyTime = this.getReplyTime();
        result = result * 59 + (($replyTime == null) ? 43 : $replyTime.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $realName = this.getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        final Object $cardId = this.getCardId();
        result = result * 59 + (($cardId == null) ? 43 : $cardId.hashCode());
        final Object $phone = this.getPhone();
        result = result * 59 + (($phone == null) ? 43 : $phone.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityMemberTraderApply(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", status=" + this.getStatus() + ", remark=" + this.getRemark() + ", replyTime=" + this.getReplyTime() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", realName=" + this.getRealName() + ", cardId=" + this.getCardId() + ", phone=" + this.getPhone() + ")";
    }
}
