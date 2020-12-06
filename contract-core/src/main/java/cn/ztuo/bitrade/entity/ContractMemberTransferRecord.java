package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractMemberTransferRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @JoinColumn(name = "member_id")
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Member member;
    @JoinColumn(name = "orginal_proxy_id")
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Member orginalProxyMember;
    @JoinColumn(name = "new_proxy_id")
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Member newProxyMember;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;

    public String getId() {
        return this.id;
    }

    public Member getMember() {
        return this.member;
    }

    public Member getOrginalProxyMember() {
        return this.orginalProxyMember;
    }

    public Member getNewProxyMember() {
        return this.newProxyMember;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public long getSequence() {
        return this.sequence;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setOrginalProxyMember(final Member orginalProxyMember) {
        this.orginalProxyMember = orginalProxyMember;
    }

    public void setNewProxyMember(final Member newProxyMember) {
        this.newProxyMember = newProxyMember;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractMemberTransferRecord)) {
            return false;
        }
        final ContractMemberTransferRecord other = (ContractMemberTransferRecord) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065:
        {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            } else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0102:
        {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0102;
                }
            } else if (this$member.equals(other$member)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$orginalProxyMember = this.getOrginalProxyMember();
        final Object other$orginalProxyMember = other.getOrginalProxyMember();
        Label_0139:
        {
            if (this$orginalProxyMember == null) {
                if (other$orginalProxyMember == null) {
                    break Label_0139;
                }
            } else if (this$orginalProxyMember.equals(other$orginalProxyMember)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$newProxyMember = this.getNewProxyMember();
        final Object other$newProxyMember = other.getNewProxyMember();
        Label_0176:
        {
            if (this$newProxyMember == null) {
                if (other$newProxyMember == null) {
                    break Label_0176;
                }
            } else if (this$newProxyMember.equals(other$newProxyMember)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null) {
            if (other$createTime == null) {
                return this.getSequence() == other.getSequence();
            }
        } else if (this$createTime.equals(other$createTime)) {
            return this.getSequence() == other.getSequence();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractMemberTransferRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $orginalProxyMember = this.getOrginalProxyMember();
        result = result * 59 + (($orginalProxyMember == null) ? 43 : $orginalProxyMember.hashCode());
        final Object $newProxyMember = this.getNewProxyMember();
        result = result * 59 + (($newProxyMember == null) ? 43 : $newProxyMember.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        return result;
    }

    @Override
    public String toString() {
        return "ContractMemberTransferRecord(id=" + this.getId() + ", member=" + this.getMember() + ", orginalProxyMember=" + this.getOrginalProxyMember() + ", newProxyMember=" + this.getNewProxyMember() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
