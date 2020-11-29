package cn.ztuo.bitrade.entity;

import java.io.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractMember implements Serializable
{
    @Id
    private Long memberId;
    @JoinColumn(name = "member_id")
    @OneToOne
    private Member member;
    private BooleanEnum ifProxy;
    private Long proxyId;
    private String invitationCode;
    private BigDecimal contractRebateRate;
    private BigDecimal spotRebateRate;
    private int status;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    @JoinColumn(name = "node_id")
    @ManyToOne
    private ContractNode contractNode;
    @JoinColumn(name = "member_id")
    @OneToMany
    @JsonIgnoreProperties({ "member" })
    private List<ContractWallet> contractWalletList;
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Member getMember() {
        return this.member;
    }
    
    public BooleanEnum getIfProxy() {
        return this.ifProxy;
    }
    
    public Long getProxyId() {
        return this.proxyId;
    }
    
    public String getInvitationCode() {
        return this.invitationCode;
    }
    
    public BigDecimal getContractRebateRate() {
        return this.contractRebateRate;
    }
    
    public BigDecimal getSpotRebateRate() {
        return this.spotRebateRate;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public long getVersion() {
        return this.version;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public long getSequence() {
        return this.sequence;
    }
    
    public ContractNode getContractNode() {
        return this.contractNode;
    }
    
    public List<ContractWallet> getContractWalletList() {
        return this.contractWalletList;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setMember(final Member member) {
        this.member = member;
    }
    
    public void setIfProxy(final BooleanEnum ifProxy) {
        this.ifProxy = ifProxy;
    }
    
    public void setProxyId(final Long proxyId) {
        this.proxyId = proxyId;
    }
    
    public void setInvitationCode(final String invitationCode) {
        this.invitationCode = invitationCode;
    }
    
    public void setContractRebateRate(final BigDecimal contractRebateRate) {
        this.contractRebateRate = contractRebateRate;
    }
    
    public void setSpotRebateRate(final BigDecimal spotRebateRate) {
        this.spotRebateRate = spotRebateRate;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setVersion(final long version) {
        this.version = version;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }
    
    public void setContractNode(final ContractNode contractNode) {
        this.contractNode = contractNode;
    }
    
    public void setContractWalletList(final List<ContractWallet> contractWalletList) {
        this.contractWalletList = contractWalletList;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractMember)) {
            return false;
        }
        final ContractMember other = (ContractMember)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0065: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0065;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0102: {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0102;
                }
            }
            else if (this$member.equals(other$member)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ifProxy = this.getIfProxy();
        final Object other$ifProxy = other.getIfProxy();
        Label_0139: {
            if (this$ifProxy == null) {
                if (other$ifProxy == null) {
                    break Label_0139;
                }
            }
            else if (this$ifProxy.equals(other$ifProxy)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$proxyId = this.getProxyId();
        final Object other$proxyId = other.getProxyId();
        Label_0176: {
            if (this$proxyId == null) {
                if (other$proxyId == null) {
                    break Label_0176;
                }
            }
            else if (this$proxyId.equals(other$proxyId)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$invitationCode = this.getInvitationCode();
        final Object other$invitationCode = other.getInvitationCode();
        Label_0213: {
            if (this$invitationCode == null) {
                if (other$invitationCode == null) {
                    break Label_0213;
                }
            }
            else if (this$invitationCode.equals(other$invitationCode)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$contractRebateRate = this.getContractRebateRate();
        final Object other$contractRebateRate = other.getContractRebateRate();
        Label_0250: {
            if (this$contractRebateRate == null) {
                if (other$contractRebateRate == null) {
                    break Label_0250;
                }
            }
            else if (this$contractRebateRate.equals(other$contractRebateRate)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$spotRebateRate = this.getSpotRebateRate();
        final Object other$spotRebateRate = other.getSpotRebateRate();
        Label_0287: {
            if (this$spotRebateRate == null) {
                if (other$spotRebateRate == null) {
                    break Label_0287;
                }
            }
            else if (this$spotRebateRate.equals(other$spotRebateRate)) {
                break Label_0287;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0351: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0351;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0351;
            }
            return false;
        }
        if (this.getSequence() != other.getSequence()) {
            return false;
        }
        final Object this$contractNode = this.getContractNode();
        final Object other$contractNode = other.getContractNode();
        Label_0402: {
            if (this$contractNode == null) {
                if (other$contractNode == null) {
                    break Label_0402;
                }
            }
            else if (this$contractNode.equals(other$contractNode)) {
                break Label_0402;
            }
            return false;
        }
        final Object this$contractWalletList = this.getContractWalletList();
        final Object other$contractWalletList = other.getContractWalletList();
        if (this$contractWalletList == null) {
            if (other$contractWalletList == null) {
                return true;
            }
        }
        else if (this$contractWalletList.equals(other$contractWalletList)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractMember;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $ifProxy = this.getIfProxy();
        result = result * 59 + (($ifProxy == null) ? 43 : $ifProxy.hashCode());
        final Object $proxyId = this.getProxyId();
        result = result * 59 + (($proxyId == null) ? 43 : $proxyId.hashCode());
        final Object $invitationCode = this.getInvitationCode();
        result = result * 59 + (($invitationCode == null) ? 43 : $invitationCode.hashCode());
        final Object $contractRebateRate = this.getContractRebateRate();
        result = result * 59 + (($contractRebateRate == null) ? 43 : $contractRebateRate.hashCode());
        final Object $spotRebateRate = this.getSpotRebateRate();
        result = result * 59 + (($spotRebateRate == null) ? 43 : $spotRebateRate.hashCode());
        result = result * 59 + this.getStatus();
        final long $version = this.getVersion();
        result = result * 59 + (int)($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int)($sequence >>> 32 ^ $sequence);
        final Object $contractNode = this.getContractNode();
        result = result * 59 + (($contractNode == null) ? 43 : $contractNode.hashCode());
        final Object $contractWalletList = this.getContractWalletList();
        result = result * 59 + (($contractWalletList == null) ? 43 : $contractWalletList.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractMember(memberId=" + this.getMemberId() + ", member=" + this.getMember() + ", ifProxy=" + this.getIfProxy() + ", proxyId=" + this.getProxyId() + ", invitationCode=" + this.getInvitationCode() + ", contractRebateRate=" + this.getContractRebateRate() + ", spotRebateRate=" + this.getSpotRebateRate() + ", status=" + this.getStatus() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", contractNode=" + this.getContractNode() + ", contractWalletList=" + this.getContractWalletList() + ")";
    }
}
