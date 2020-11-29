package cn.ztuo.bitrade.entity;

import java.io.*;
import java.math.*;
import cn.ztuo.bitrade.constant.*;
import javax.persistence.*;

@Entity
@Table(name = "contract_node")
public class ContractNode implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private String nodeName;
    private BigDecimal feeReturnRate;
    private BigDecimal profitLossReturnRate;
    private BigDecimal holdFeeReturnRate;
    private BigDecimal warningRate;
    private BigDecimal transferRate;
    private BigDecimal closeRate;
    private BigDecimal virtualRechargeAmount;
    private BigDecimal depositAmount;
    private Integer enable;
    private Long memberId;
    private IfNodeType memberStatus;
    @Transient
    private Member member;
    private BigDecimal depositLimitRate;
    private String lastNodeId;
    private String superNodeId;
    private Integer nodeLevel;
    private String nodeType;
    
    public ContractNode() {
        this.feeReturnRate = BigDecimal.ZERO;
        this.profitLossReturnRate = BigDecimal.ZERO;
        this.holdFeeReturnRate = BigDecimal.ZERO;
        this.warningRate = BigDecimal.ZERO;
        this.transferRate = BigDecimal.ZERO;
        this.closeRate = BigDecimal.ZERO;
        this.virtualRechargeAmount = BigDecimal.ZERO;
        this.depositAmount = BigDecimal.ZERO;
        this.depositLimitRate = BigDecimal.ZERO;
    }
    
    public ContractNode(final IfNodeType memberStatus) {
        this.feeReturnRate = BigDecimal.ZERO;
        this.profitLossReturnRate = BigDecimal.ZERO;
        this.holdFeeReturnRate = BigDecimal.ZERO;
        this.warningRate = BigDecimal.ZERO;
        this.transferRate = BigDecimal.ZERO;
        this.closeRate = BigDecimal.ZERO;
        this.virtualRechargeAmount = BigDecimal.ZERO;
        this.depositAmount = BigDecimal.ZERO;
        this.depositLimitRate = BigDecimal.ZERO;
        this.memberStatus = memberStatus;
    }
    
    public ContractNode(final String id) {
        this.feeReturnRate = BigDecimal.ZERO;
        this.profitLossReturnRate = BigDecimal.ZERO;
        this.holdFeeReturnRate = BigDecimal.ZERO;
        this.warningRate = BigDecimal.ZERO;
        this.transferRate = BigDecimal.ZERO;
        this.closeRate = BigDecimal.ZERO;
        this.virtualRechargeAmount = BigDecimal.ZERO;
        this.depositAmount = BigDecimal.ZERO;
        this.depositLimitRate = BigDecimal.ZERO;
        this.id = id;
    }
    
    public ContractNode(final Long memberId, final String nodeName, final BigDecimal feeReturnRate, final BigDecimal profitLossReturnRate, final BigDecimal holdFeeReturnRate, final BigDecimal virtualRechargeAmount, final BigDecimal depositAmount, final BigDecimal warningRate, final BigDecimal transferRate, final BigDecimal closeRate, final IfNodeType memberStatus, final Integer enable) {
        this.feeReturnRate = BigDecimal.ZERO;
        this.profitLossReturnRate = BigDecimal.ZERO;
        this.holdFeeReturnRate = BigDecimal.ZERO;
        this.warningRate = BigDecimal.ZERO;
        this.transferRate = BigDecimal.ZERO;
        this.closeRate = BigDecimal.ZERO;
        this.virtualRechargeAmount = BigDecimal.ZERO;
        this.depositAmount = BigDecimal.ZERO;
        this.depositLimitRate = BigDecimal.ZERO;
        this.memberId = memberId;
        this.nodeName = nodeName;
        this.feeReturnRate = feeReturnRate;
        this.profitLossReturnRate = profitLossReturnRate;
        this.holdFeeReturnRate = holdFeeReturnRate;
        this.virtualRechargeAmount = virtualRechargeAmount;
        this.depositAmount = depositAmount;
        this.warningRate = warningRate;
        this.transferRate = transferRate;
        this.closeRate = closeRate;
        this.memberStatus = memberStatus;
        this.enable = enable;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getNodeName() {
        return this.nodeName;
    }
    
    public BigDecimal getFeeReturnRate() {
        return this.feeReturnRate;
    }
    
    public BigDecimal getProfitLossReturnRate() {
        return this.profitLossReturnRate;
    }
    
    public BigDecimal getHoldFeeReturnRate() {
        return this.holdFeeReturnRate;
    }
    
    public BigDecimal getWarningRate() {
        return this.warningRate;
    }
    
    public BigDecimal getTransferRate() {
        return this.transferRate;
    }
    
    public BigDecimal getCloseRate() {
        return this.closeRate;
    }
    
    public BigDecimal getVirtualRechargeAmount() {
        return this.virtualRechargeAmount;
    }
    
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }
    
    public Integer getEnable() {
        return this.enable;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public IfNodeType getMemberStatus() {
        return this.memberStatus;
    }
    
    public Member getMember() {
        return this.member;
    }
    
    public BigDecimal getDepositLimitRate() {
        return this.depositLimitRate;
    }
    
    public String getLastNodeId() {
        return this.lastNodeId;
    }
    
    public String getSuperNodeId() {
        return this.superNodeId;
    }
    
    public Integer getNodeLevel() {
        return this.nodeLevel;
    }
    
    public String getNodeType() {
        return this.nodeType;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setNodeName(final String nodeName) {
        this.nodeName = nodeName;
    }
    
    public void setFeeReturnRate(final BigDecimal feeReturnRate) {
        this.feeReturnRate = feeReturnRate;
    }
    
    public void setProfitLossReturnRate(final BigDecimal profitLossReturnRate) {
        this.profitLossReturnRate = profitLossReturnRate;
    }
    
    public void setHoldFeeReturnRate(final BigDecimal holdFeeReturnRate) {
        this.holdFeeReturnRate = holdFeeReturnRate;
    }
    
    public void setWarningRate(final BigDecimal warningRate) {
        this.warningRate = warningRate;
    }
    
    public void setTransferRate(final BigDecimal transferRate) {
        this.transferRate = transferRate;
    }
    
    public void setCloseRate(final BigDecimal closeRate) {
        this.closeRate = closeRate;
    }
    
    public void setVirtualRechargeAmount(final BigDecimal virtualRechargeAmount) {
        this.virtualRechargeAmount = virtualRechargeAmount;
    }
    
    public void setDepositAmount(final BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
    
    public void setEnable(final Integer enable) {
        this.enable = enable;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setMemberStatus(final IfNodeType memberStatus) {
        this.memberStatus = memberStatus;
    }
    
    public void setMember(final Member member) {
        this.member = member;
    }
    
    public void setDepositLimitRate(final BigDecimal depositLimitRate) {
        this.depositLimitRate = depositLimitRate;
    }
    
    public void setLastNodeId(final String lastNodeId) {
        this.lastNodeId = lastNodeId;
    }
    
    public void setSuperNodeId(final String superNodeId) {
        this.superNodeId = superNodeId;
    }
    
    public void setNodeLevel(final Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }
    
    public void setNodeType(final String nodeType) {
        this.nodeType = nodeType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractNode)) {
            return false;
        }
        final ContractNode other = (ContractNode)o;
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
        final Object this$nodeName = this.getNodeName();
        final Object other$nodeName = other.getNodeName();
        Label_0102: {
            if (this$nodeName == null) {
                if (other$nodeName == null) {
                    break Label_0102;
                }
            }
            else if (this$nodeName.equals(other$nodeName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$feeReturnRate = this.getFeeReturnRate();
        final Object other$feeReturnRate = other.getFeeReturnRate();
        Label_0139: {
            if (this$feeReturnRate == null) {
                if (other$feeReturnRate == null) {
                    break Label_0139;
                }
            }
            else if (this$feeReturnRate.equals(other$feeReturnRate)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$profitLossReturnRate = this.getProfitLossReturnRate();
        final Object other$profitLossReturnRate = other.getProfitLossReturnRate();
        Label_0176: {
            if (this$profitLossReturnRate == null) {
                if (other$profitLossReturnRate == null) {
                    break Label_0176;
                }
            }
            else if (this$profitLossReturnRate.equals(other$profitLossReturnRate)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$holdFeeReturnRate = this.getHoldFeeReturnRate();
        final Object other$holdFeeReturnRate = other.getHoldFeeReturnRate();
        Label_0213: {
            if (this$holdFeeReturnRate == null) {
                if (other$holdFeeReturnRate == null) {
                    break Label_0213;
                }
            }
            else if (this$holdFeeReturnRate.equals(other$holdFeeReturnRate)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$warningRate = this.getWarningRate();
        final Object other$warningRate = other.getWarningRate();
        Label_0250: {
            if (this$warningRate == null) {
                if (other$warningRate == null) {
                    break Label_0250;
                }
            }
            else if (this$warningRate.equals(other$warningRate)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$transferRate = this.getTransferRate();
        final Object other$transferRate = other.getTransferRate();
        Label_0287: {
            if (this$transferRate == null) {
                if (other$transferRate == null) {
                    break Label_0287;
                }
            }
            else if (this$transferRate.equals(other$transferRate)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$closeRate = this.getCloseRate();
        final Object other$closeRate = other.getCloseRate();
        Label_0324: {
            if (this$closeRate == null) {
                if (other$closeRate == null) {
                    break Label_0324;
                }
            }
            else if (this$closeRate.equals(other$closeRate)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$virtualRechargeAmount = this.getVirtualRechargeAmount();
        final Object other$virtualRechargeAmount = other.getVirtualRechargeAmount();
        Label_0361: {
            if (this$virtualRechargeAmount == null) {
                if (other$virtualRechargeAmount == null) {
                    break Label_0361;
                }
            }
            else if (this$virtualRechargeAmount.equals(other$virtualRechargeAmount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$depositAmount = this.getDepositAmount();
        final Object other$depositAmount = other.getDepositAmount();
        Label_0398: {
            if (this$depositAmount == null) {
                if (other$depositAmount == null) {
                    break Label_0398;
                }
            }
            else if (this$depositAmount.equals(other$depositAmount)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$enable = this.getEnable();
        final Object other$enable = other.getEnable();
        Label_0435: {
            if (this$enable == null) {
                if (other$enable == null) {
                    break Label_0435;
                }
            }
            else if (this$enable.equals(other$enable)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0472: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0472;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$memberStatus = this.getMemberStatus();
        final Object other$memberStatus = other.getMemberStatus();
        Label_0509: {
            if (this$memberStatus == null) {
                if (other$memberStatus == null) {
                    break Label_0509;
                }
            }
            else if (this$memberStatus.equals(other$memberStatus)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0546: {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0546;
                }
            }
            else if (this$member.equals(other$member)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$depositLimitRate = this.getDepositLimitRate();
        final Object other$depositLimitRate = other.getDepositLimitRate();
        Label_0583: {
            if (this$depositLimitRate == null) {
                if (other$depositLimitRate == null) {
                    break Label_0583;
                }
            }
            else if (this$depositLimitRate.equals(other$depositLimitRate)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$lastNodeId = this.getLastNodeId();
        final Object other$lastNodeId = other.getLastNodeId();
        Label_0620: {
            if (this$lastNodeId == null) {
                if (other$lastNodeId == null) {
                    break Label_0620;
                }
            }
            else if (this$lastNodeId.equals(other$lastNodeId)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$superNodeId = this.getSuperNodeId();
        final Object other$superNodeId = other.getSuperNodeId();
        Label_0657: {
            if (this$superNodeId == null) {
                if (other$superNodeId == null) {
                    break Label_0657;
                }
            }
            else if (this$superNodeId.equals(other$superNodeId)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$nodeLevel = this.getNodeLevel();
        final Object other$nodeLevel = other.getNodeLevel();
        Label_0694: {
            if (this$nodeLevel == null) {
                if (other$nodeLevel == null) {
                    break Label_0694;
                }
            }
            else if (this$nodeLevel.equals(other$nodeLevel)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$nodeType = this.getNodeType();
        final Object other$nodeType = other.getNodeType();
        if (this$nodeType == null) {
            if (other$nodeType == null) {
                return true;
            }
        }
        else if (this$nodeType.equals(other$nodeType)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractNode;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $nodeName = this.getNodeName();
        result = result * 59 + (($nodeName == null) ? 43 : $nodeName.hashCode());
        final Object $feeReturnRate = this.getFeeReturnRate();
        result = result * 59 + (($feeReturnRate == null) ? 43 : $feeReturnRate.hashCode());
        final Object $profitLossReturnRate = this.getProfitLossReturnRate();
        result = result * 59 + (($profitLossReturnRate == null) ? 43 : $profitLossReturnRate.hashCode());
        final Object $holdFeeReturnRate = this.getHoldFeeReturnRate();
        result = result * 59 + (($holdFeeReturnRate == null) ? 43 : $holdFeeReturnRate.hashCode());
        final Object $warningRate = this.getWarningRate();
        result = result * 59 + (($warningRate == null) ? 43 : $warningRate.hashCode());
        final Object $transferRate = this.getTransferRate();
        result = result * 59 + (($transferRate == null) ? 43 : $transferRate.hashCode());
        final Object $closeRate = this.getCloseRate();
        result = result * 59 + (($closeRate == null) ? 43 : $closeRate.hashCode());
        final Object $virtualRechargeAmount = this.getVirtualRechargeAmount();
        result = result * 59 + (($virtualRechargeAmount == null) ? 43 : $virtualRechargeAmount.hashCode());
        final Object $depositAmount = this.getDepositAmount();
        result = result * 59 + (($depositAmount == null) ? 43 : $depositAmount.hashCode());
        final Object $enable = this.getEnable();
        result = result * 59 + (($enable == null) ? 43 : $enable.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $memberStatus = this.getMemberStatus();
        result = result * 59 + (($memberStatus == null) ? 43 : $memberStatus.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $depositLimitRate = this.getDepositLimitRate();
        result = result * 59 + (($depositLimitRate == null) ? 43 : $depositLimitRate.hashCode());
        final Object $lastNodeId = this.getLastNodeId();
        result = result * 59 + (($lastNodeId == null) ? 43 : $lastNodeId.hashCode());
        final Object $superNodeId = this.getSuperNodeId();
        result = result * 59 + (($superNodeId == null) ? 43 : $superNodeId.hashCode());
        final Object $nodeLevel = this.getNodeLevel();
        result = result * 59 + (($nodeLevel == null) ? 43 : $nodeLevel.hashCode());
        final Object $nodeType = this.getNodeType();
        result = result * 59 + (($nodeType == null) ? 43 : $nodeType.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractNode(id=" + this.getId() + ", nodeName=" + this.getNodeName() + ", feeReturnRate=" + this.getFeeReturnRate() + ", profitLossReturnRate=" + this.getProfitLossReturnRate() + ", holdFeeReturnRate=" + this.getHoldFeeReturnRate() + ", warningRate=" + this.getWarningRate() + ", transferRate=" + this.getTransferRate() + ", closeRate=" + this.getCloseRate() + ", virtualRechargeAmount=" + this.getVirtualRechargeAmount() + ", depositAmount=" + this.getDepositAmount() + ", enable=" + this.getEnable() + ", memberId=" + this.getMemberId() + ", memberStatus=" + this.getMemberStatus() + ", member=" + this.getMember() + ", depositLimitRate=" + this.getDepositLimitRate() + ", lastNodeId=" + this.getLastNodeId() + ", superNodeId=" + this.getSuperNodeId() + ", nodeLevel=" + this.getNodeLevel() + ", nodeType=" + this.getNodeType() + ")";
    }
}
