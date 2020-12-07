package cn.ztuo.bitrade.entity;

import java.io.*;
import java.math.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class CommunityMemberFollowInfo implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long leaderMemberId;
    private String coinId;
    private CommunityMemberFollowInfoType type;
    private BigDecimal amount;
    private BigDecimal dayMaxAmount;
    private BigDecimal holdingMaxAmount;
    private BigDecimal stopLossRate;
    private CommunityMemberFollowInfoStatus status;
    private BigDecimal totalAmount;
    private BigDecimal totalProfit;
    private Date createTime;
    private Long sequence;
    private Long version;
    @Transient
    private String leaderUsername;
    @Transient
    private String leaderAvatar;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getLeaderMemberId() {
        return this.leaderMemberId;
    }
    
    public String getCoinId() {
        return this.coinId;
    }
    
    public CommunityMemberFollowInfoType getType() {
        return this.type;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public BigDecimal getDayMaxAmount() {
        return this.dayMaxAmount;
    }
    
    public BigDecimal getHoldingMaxAmount() {
        return this.holdingMaxAmount;
    }
    
    public BigDecimal getStopLossRate() {
        return this.stopLossRate;
    }
    
    public CommunityMemberFollowInfoStatus getStatus() {
        return this.status;
    }
    
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }
    
    public BigDecimal getTotalProfit() {
        return this.totalProfit;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    
    public Long getVersion() {
        return this.version;
    }
    
    public String getLeaderUsername() {
        return this.leaderUsername;
    }
    
    public String getLeaderAvatar() {
        return this.leaderAvatar;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setLeaderMemberId(final Long leaderMemberId) {
        this.leaderMemberId = leaderMemberId;
    }
    
    public void setCoinId(final String coinId) {
        this.coinId = coinId;
    }
    
    public void setType(final CommunityMemberFollowInfoType type) {
        this.type = type;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setDayMaxAmount(final BigDecimal dayMaxAmount) {
        this.dayMaxAmount = dayMaxAmount;
    }
    
    public void setHoldingMaxAmount(final BigDecimal holdingMaxAmount) {
        this.holdingMaxAmount = holdingMaxAmount;
    }
    
    public void setStopLossRate(final BigDecimal stopLossRate) {
        this.stopLossRate = stopLossRate;
    }
    
    public void setStatus(final CommunityMemberFollowInfoStatus status) {
        this.status = status;
    }
    
    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void setTotalProfit(final BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    public void setVersion(final Long version) {
        this.version = version;
    }
    
    public void setLeaderUsername(final String leaderUsername) {
        this.leaderUsername = leaderUsername;
    }
    
    public void setLeaderAvatar(final String leaderAvatar) {
        this.leaderAvatar = leaderAvatar;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityMemberFollowInfo)) {
            return false;
        }
        final CommunityMemberFollowInfo other = (CommunityMemberFollowInfo)o;
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
        final Object this$leaderMemberId = this.getLeaderMemberId();
        final Object other$leaderMemberId = other.getLeaderMemberId();
        Label_0139: {
            if (this$leaderMemberId == null) {
                if (other$leaderMemberId == null) {
                    break Label_0139;
                }
            }
            else if (this$leaderMemberId.equals(other$leaderMemberId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$coinId = this.getCoinId();
        final Object other$coinId = other.getCoinId();
        Label_0176: {
            if (this$coinId == null) {
                if (other$coinId == null) {
                    break Label_0176;
                }
            }
            else if (this$coinId.equals(other$coinId)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0213: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0213;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0250: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0250;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$dayMaxAmount = this.getDayMaxAmount();
        final Object other$dayMaxAmount = other.getDayMaxAmount();
        Label_0287: {
            if (this$dayMaxAmount == null) {
                if (other$dayMaxAmount == null) {
                    break Label_0287;
                }
            }
            else if (this$dayMaxAmount.equals(other$dayMaxAmount)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$holdingMaxAmount = this.getHoldingMaxAmount();
        final Object other$holdingMaxAmount = other.getHoldingMaxAmount();
        Label_0324: {
            if (this$holdingMaxAmount == null) {
                if (other$holdingMaxAmount == null) {
                    break Label_0324;
                }
            }
            else if (this$holdingMaxAmount.equals(other$holdingMaxAmount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$stopLossRate = this.getStopLossRate();
        final Object other$stopLossRate = other.getStopLossRate();
        Label_0361: {
            if (this$stopLossRate == null) {
                if (other$stopLossRate == null) {
                    break Label_0361;
                }
            }
            else if (this$stopLossRate.equals(other$stopLossRate)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0398: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0398;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$totalAmount = this.getTotalAmount();
        final Object other$totalAmount = other.getTotalAmount();
        Label_0435: {
            if (this$totalAmount == null) {
                if (other$totalAmount == null) {
                    break Label_0435;
                }
            }
            else if (this$totalAmount.equals(other$totalAmount)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$totalProfit = this.getTotalProfit();
        final Object other$totalProfit = other.getTotalProfit();
        Label_0472: {
            if (this$totalProfit == null) {
                if (other$totalProfit == null) {
                    break Label_0472;
                }
            }
            else if (this$totalProfit.equals(other$totalProfit)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0509: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0509;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0546: {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0546;
                }
            }
            else if (this$sequence.equals(other$sequence)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        Label_0583: {
            if (this$version == null) {
                if (other$version == null) {
                    break Label_0583;
                }
            }
            else if (this$version.equals(other$version)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$leaderUsername = this.getLeaderUsername();
        final Object other$leaderUsername = other.getLeaderUsername();
        Label_0620: {
            if (this$leaderUsername == null) {
                if (other$leaderUsername == null) {
                    break Label_0620;
                }
            }
            else if (this$leaderUsername.equals(other$leaderUsername)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$leaderAvatar = this.getLeaderAvatar();
        final Object other$leaderAvatar = other.getLeaderAvatar();
        if (this$leaderAvatar == null) {
            if (other$leaderAvatar == null) {
                return true;
            }
        }
        else if (this$leaderAvatar.equals(other$leaderAvatar)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityMemberFollowInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $leaderMemberId = this.getLeaderMemberId();
        result = result * 59 + (($leaderMemberId == null) ? 43 : $leaderMemberId.hashCode());
        final Object $coinId = this.getCoinId();
        result = result * 59 + (($coinId == null) ? 43 : $coinId.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $dayMaxAmount = this.getDayMaxAmount();
        result = result * 59 + (($dayMaxAmount == null) ? 43 : $dayMaxAmount.hashCode());
        final Object $holdingMaxAmount = this.getHoldingMaxAmount();
        result = result * 59 + (($holdingMaxAmount == null) ? 43 : $holdingMaxAmount.hashCode());
        final Object $stopLossRate = this.getStopLossRate();
        result = result * 59 + (($stopLossRate == null) ? 43 : $stopLossRate.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $totalAmount = this.getTotalAmount();
        result = result * 59 + (($totalAmount == null) ? 43 : $totalAmount.hashCode());
        final Object $totalProfit = this.getTotalProfit();
        result = result * 59 + (($totalProfit == null) ? 43 : $totalProfit.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        final Object $leaderUsername = this.getLeaderUsername();
        result = result * 59 + (($leaderUsername == null) ? 43 : $leaderUsername.hashCode());
        final Object $leaderAvatar = this.getLeaderAvatar();
        result = result * 59 + (($leaderAvatar == null) ? 43 : $leaderAvatar.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityMemberFollowInfo(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", leaderMemberId=" + this.getLeaderMemberId() + ", coinId=" + this.getCoinId() + ", type=" + this.getType() + ", amount=" + this.getAmount() + ", dayMaxAmount=" + this.getDayMaxAmount() + ", holdingMaxAmount=" + this.getHoldingMaxAmount() + ", stopLossRate=" + this.getStopLossRate() + ", status=" + this.getStatus() + ", totalAmount=" + this.getTotalAmount() + ", totalProfit=" + this.getTotalProfit() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", version=" + this.getVersion() + ", leaderUsername=" + this.getLeaderUsername() + ", leaderAvatar=" + this.getLeaderAvatar() + ")";
    }
}
