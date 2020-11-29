package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class ContractMemberStatusRecord implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long nodeMemberId;
    private String memberIds;
    private Integer status;
    private Date time;
    private Long sequence;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getNodeMemberId() {
        return this.nodeMemberId;
    }
    
    public String getMemberIds() {
        return this.memberIds;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public Date getTime() {
        return this.time;
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
    
    public void setNodeMemberId(final Long nodeMemberId) {
        this.nodeMemberId = nodeMemberId;
    }
    
    public void setMemberIds(final String memberIds) {
        this.memberIds = memberIds;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    public void setTime(final Date time) {
        this.time = time;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractMemberStatusRecord)) {
            return false;
        }
        final ContractMemberStatusRecord other = (ContractMemberStatusRecord)o;
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
        final Object this$nodeMemberId = this.getNodeMemberId();
        final Object other$nodeMemberId = other.getNodeMemberId();
        Label_0139: {
            if (this$nodeMemberId == null) {
                if (other$nodeMemberId == null) {
                    break Label_0139;
                }
            }
            else if (this$nodeMemberId.equals(other$nodeMemberId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$memberIds = this.getMemberIds();
        final Object other$memberIds = other.getMemberIds();
        Label_0176: {
            if (this$memberIds == null) {
                if (other$memberIds == null) {
                    break Label_0176;
                }
            }
            else if (this$memberIds.equals(other$memberIds)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0213: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0213;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        Label_0250: {
            if (this$time == null) {
                if (other$time == null) {
                    break Label_0250;
                }
            }
            else if (this$time.equals(other$time)) {
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
        return other instanceof ContractMemberStatusRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $nodeMemberId = this.getNodeMemberId();
        result = result * 59 + (($nodeMemberId == null) ? 43 : $nodeMemberId.hashCode());
        final Object $memberIds = this.getMemberIds();
        result = result * 59 + (($memberIds == null) ? 43 : $memberIds.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $time = this.getTime();
        result = result * 59 + (($time == null) ? 43 : $time.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractMemberStatusRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", nodeMemberId=" + this.getNodeMemberId() + ", memberIds=" + this.getMemberIds() + ", status=" + this.getStatus() + ", time=" + this.getTime() + ", sequence=" + this.getSequence() + ")";
    }
}
