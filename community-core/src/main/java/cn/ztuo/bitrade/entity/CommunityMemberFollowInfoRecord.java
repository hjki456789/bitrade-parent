package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import java.util.*;

@Entity
@Table
public class CommunityMemberFollowInfoRecord implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long followId;
    private CommunityMemberFollowInfoRecordType type;
    private String oldInfo;
    private Date createTime;
    private Long sequence;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getFollowId() {
        return this.followId;
    }
    
    public CommunityMemberFollowInfoRecordType getType() {
        return this.type;
    }
    
    public String getOldInfo() {
        return this.oldInfo;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setFollowId(final Long followId) {
        this.followId = followId;
    }
    
    public void setType(final CommunityMemberFollowInfoRecordType type) {
        this.type = type;
    }
    
    public void setOldInfo(final String oldInfo) {
        this.oldInfo = oldInfo;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityMemberFollowInfoRecord)) {
            return false;
        }
        final CommunityMemberFollowInfoRecord other = (CommunityMemberFollowInfoRecord)o;
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
        final Object this$followId = this.getFollowId();
        final Object other$followId = other.getFollowId();
        Label_0139: {
            if (this$followId == null) {
                if (other$followId == null) {
                    break Label_0139;
                }
            }
            else if (this$followId.equals(other$followId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0176: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0176;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$oldInfo = this.getOldInfo();
        final Object other$oldInfo = other.getOldInfo();
        Label_0213: {
            if (this$oldInfo == null) {
                if (other$oldInfo == null) {
                    break Label_0213;
                }
            }
            else if (this$oldInfo.equals(other$oldInfo)) {
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
        if (this$sequence == null) {
            if (other$sequence == null) {
                return true;
            }
        }
        else if (this$sequence.equals(other$sequence)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityMemberFollowInfoRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $followId = this.getFollowId();
        result = result * 59 + (($followId == null) ? 43 : $followId.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $oldInfo = this.getOldInfo();
        result = result * 59 + (($oldInfo == null) ? 43 : $oldInfo.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityMemberFollowInfoRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", followId=" + this.getFollowId() + ", type=" + this.getType() + ", oldInfo=" + this.getOldInfo() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
