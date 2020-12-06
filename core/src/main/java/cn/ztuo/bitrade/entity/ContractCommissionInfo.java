package cn.ztuo.bitrade.entity;

import java.io.*;
import java.math.*;

import cn.ztuo.bitrade.constant.*;

import javax.persistence.*;

@Entity
@Table(name = "contract_commission_info")
public class ContractCommissionInfo implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private Long proxyId;
    private BigDecimal amount;
    private String coinName;
    private String sysUser;
    private Integer status;
    private IfNodeType memberStatus;
    private Integer type;
    private String updateTime;
    private String createTime;
    private Long sequence;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    public ContractCommissionInfo() {
    }

    public ContractCommissionInfo(final Long memberId, final Long proxyId, final BigDecimal amount, final String coinName, final Integer status, final String updateTime, final String createTime, final Long sequence, final IfNodeType memberStatus, final Integer type) {
        this.proxyId = proxyId;
        this.amount = amount;
        this.coinName = coinName;
        this.status = status;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.sequence = sequence;
        this.member = new Member(memberId);
        this.memberStatus = memberStatus;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public Long getProxyId() {
        return this.proxyId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public String getCoinName() {
        return this.coinName;
    }

    public String getSysUser() {
        return this.sysUser;
    }

    public Integer getStatus() {
        return this.status;
    }

    public IfNodeType getMemberStatus() {
        return this.memberStatus;
    }

    public Integer getType() {
        return this.type;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public Member getMember() {
        return this.member;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setProxyId(final Long proxyId) {
        this.proxyId = proxyId;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setCoinName(final String coinName) {
        this.coinName = coinName;
    }

    public void setSysUser(final String sysUser) {
        this.sysUser = sysUser;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setMemberStatus(final IfNodeType memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public void setUpdateTime(final String updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractCommissionInfo)) {
            return false;
        }
        final ContractCommissionInfo other = (ContractCommissionInfo) o;
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
        final Object this$proxyId = this.getProxyId();
        final Object other$proxyId = other.getProxyId();
        Label_0102:
        {
            if (this$proxyId == null) {
                if (other$proxyId == null) {
                    break Label_0102;
                }
            } else if (this$proxyId.equals(other$proxyId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0139:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0139;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$coinName = this.getCoinName();
        final Object other$coinName = other.getCoinName();
        Label_0176:
        {
            if (this$coinName == null) {
                if (other$coinName == null) {
                    break Label_0176;
                }
            } else if (this$coinName.equals(other$coinName)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$sysUser = this.getSysUser();
        final Object other$sysUser = other.getSysUser();
        Label_0213:
        {
            if (this$sysUser == null) {
                if (other$sysUser == null) {
                    break Label_0213;
                }
            } else if (this$sysUser.equals(other$sysUser)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0250:
        {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0250;
                }
            } else if (this$status.equals(other$status)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$memberStatus = this.getMemberStatus();
        final Object other$memberStatus = other.getMemberStatus();
        Label_0287:
        {
            if (this$memberStatus == null) {
                if (other$memberStatus == null) {
                    break Label_0287;
                }
            } else if (this$memberStatus.equals(other$memberStatus)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0324:
        {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0324;
                }
            } else if (this$type.equals(other$type)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0361:
        {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0361;
                }
            } else if (this$updateTime.equals(other$updateTime)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0398:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0398;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0435:
        {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0435;
                }
            } else if (this$sequence.equals(other$sequence)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        if (this$member == null) {
            if (other$member == null) {
                return true;
            }
        } else if (this$member.equals(other$member)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractCommissionInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $proxyId = this.getProxyId();
        result = result * 59 + (($proxyId == null) ? 43 : $proxyId.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $coinName = this.getCoinName();
        result = result * 59 + (($coinName == null) ? 43 : $coinName.hashCode());
        final Object $sysUser = this.getSysUser();
        result = result * 59 + (($sysUser == null) ? 43 : $sysUser.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $memberStatus = this.getMemberStatus();
        result = result * 59 + (($memberStatus == null) ? 43 : $memberStatus.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractCommissionInfo(id=" + this.getId() + ", proxyId=" + this.getProxyId() + ", amount=" + this.getAmount() + ", coinName=" + this.getCoinName() + ", sysUser=" + this.getSysUser() + ", status=" + this.getStatus() + ", memberStatus=" + this.getMemberStatus() + ", type=" + this.getType() + ", updateTime=" + this.getUpdateTime() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", member=" + this.getMember() + ")";
    }
}
