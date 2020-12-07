package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import cn.ztuo.bitrade.entity.enumconstants.*;
import java.math.*;
import java.util.*;

@Entity
@Table
public class CommunityMember implements Serializable
{
    @Id
    private Long memberId;
    private String username;
    private String areaName;
    private String personalIntroduction;
    private CommunityMemberSex sex;
    private String labels;
    private CommunityMemberType type;
    private CommunityMemberStatus status;
    private BigDecimal totalProfitRate;
    private BigDecimal threeWeekWinRate;
    private BigDecimal threeWeekCallbackRate;
    private BigDecimal followExtractRate;
    private Date createTime;
    private Long sequence;
    private Long version;
    private Integer actualTimeCount;
    private Integer transactionDays;
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getAreaName() {
        return this.areaName;
    }
    
    public String getPersonalIntroduction() {
        return this.personalIntroduction;
    }
    
    public CommunityMemberSex getSex() {
        return this.sex;
    }
    
    public String getLabels() {
        return this.labels;
    }
    
    public CommunityMemberType getType() {
        return this.type;
    }
    
    public CommunityMemberStatus getStatus() {
        return this.status;
    }
    
    public BigDecimal getTotalProfitRate() {
        return this.totalProfitRate;
    }
    
    public BigDecimal getThreeWeekWinRate() {
        return this.threeWeekWinRate;
    }
    
    public BigDecimal getThreeWeekCallbackRate() {
        return this.threeWeekCallbackRate;
    }
    
    public BigDecimal getFollowExtractRate() {
        return this.followExtractRate;
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
    
    public Integer getActualTimeCount() {
        return this.actualTimeCount;
    }
    
    public Integer getTransactionDays() {
        return this.transactionDays;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public void setAreaName(final String areaName) {
        this.areaName = areaName;
    }
    
    public void setPersonalIntroduction(final String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }
    
    public void setSex(final CommunityMemberSex sex) {
        this.sex = sex;
    }
    
    public void setLabels(final String labels) {
        this.labels = labels;
    }
    
    public void setType(final CommunityMemberType type) {
        this.type = type;
    }
    
    public void setStatus(final CommunityMemberStatus status) {
        this.status = status;
    }
    
    public void setTotalProfitRate(final BigDecimal totalProfitRate) {
        this.totalProfitRate = totalProfitRate;
    }
    
    public void setThreeWeekWinRate(final BigDecimal threeWeekWinRate) {
        this.threeWeekWinRate = threeWeekWinRate;
    }
    
    public void setThreeWeekCallbackRate(final BigDecimal threeWeekCallbackRate) {
        this.threeWeekCallbackRate = threeWeekCallbackRate;
    }
    
    public void setFollowExtractRate(final BigDecimal followExtractRate) {
        this.followExtractRate = followExtractRate;
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
    
    public void setActualTimeCount(final Integer actualTimeCount) {
        this.actualTimeCount = actualTimeCount;
    }
    
    public void setTransactionDays(final Integer transactionDays) {
        this.transactionDays = transactionDays;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityMember)) {
            return false;
        }
        final CommunityMember other = (CommunityMember)o;
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
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0102: {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0102;
                }
            }
            else if (this$username.equals(other$username)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$areaName = this.getAreaName();
        final Object other$areaName = other.getAreaName();
        Label_0139: {
            if (this$areaName == null) {
                if (other$areaName == null) {
                    break Label_0139;
                }
            }
            else if (this$areaName.equals(other$areaName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$personalIntroduction = this.getPersonalIntroduction();
        final Object other$personalIntroduction = other.getPersonalIntroduction();
        Label_0176: {
            if (this$personalIntroduction == null) {
                if (other$personalIntroduction == null) {
                    break Label_0176;
                }
            }
            else if (this$personalIntroduction.equals(other$personalIntroduction)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$sex = this.getSex();
        final Object other$sex = other.getSex();
        Label_0213: {
            if (this$sex == null) {
                if (other$sex == null) {
                    break Label_0213;
                }
            }
            else if (this$sex.equals(other$sex)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$labels = this.getLabels();
        final Object other$labels = other.getLabels();
        Label_0250: {
            if (this$labels == null) {
                if (other$labels == null) {
                    break Label_0250;
                }
            }
            else if (this$labels.equals(other$labels)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0287: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0287;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0324: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0324;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$totalProfitRate = this.getTotalProfitRate();
        final Object other$totalProfitRate = other.getTotalProfitRate();
        Label_0361: {
            if (this$totalProfitRate == null) {
                if (other$totalProfitRate == null) {
                    break Label_0361;
                }
            }
            else if (this$totalProfitRate.equals(other$totalProfitRate)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$threeWeekWinRate = this.getThreeWeekWinRate();
        final Object other$threeWeekWinRate = other.getThreeWeekWinRate();
        Label_0398: {
            if (this$threeWeekWinRate == null) {
                if (other$threeWeekWinRate == null) {
                    break Label_0398;
                }
            }
            else if (this$threeWeekWinRate.equals(other$threeWeekWinRate)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$threeWeekCallbackRate = this.getThreeWeekCallbackRate();
        final Object other$threeWeekCallbackRate = other.getThreeWeekCallbackRate();
        Label_0435: {
            if (this$threeWeekCallbackRate == null) {
                if (other$threeWeekCallbackRate == null) {
                    break Label_0435;
                }
            }
            else if (this$threeWeekCallbackRate.equals(other$threeWeekCallbackRate)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$followExtractRate = this.getFollowExtractRate();
        final Object other$followExtractRate = other.getFollowExtractRate();
        Label_0472: {
            if (this$followExtractRate == null) {
                if (other$followExtractRate == null) {
                    break Label_0472;
                }
            }
            else if (this$followExtractRate.equals(other$followExtractRate)) {
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
        final Object this$actualTimeCount = this.getActualTimeCount();
        final Object other$actualTimeCount = other.getActualTimeCount();
        Label_0620: {
            if (this$actualTimeCount == null) {
                if (other$actualTimeCount == null) {
                    break Label_0620;
                }
            }
            else if (this$actualTimeCount.equals(other$actualTimeCount)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$transactionDays = this.getTransactionDays();
        final Object other$transactionDays = other.getTransactionDays();
        if (this$transactionDays == null) {
            if (other$transactionDays == null) {
                return true;
            }
        }
        else if (this$transactionDays.equals(other$transactionDays)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityMember;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $areaName = this.getAreaName();
        result = result * 59 + (($areaName == null) ? 43 : $areaName.hashCode());
        final Object $personalIntroduction = this.getPersonalIntroduction();
        result = result * 59 + (($personalIntroduction == null) ? 43 : $personalIntroduction.hashCode());
        final Object $sex = this.getSex();
        result = result * 59 + (($sex == null) ? 43 : $sex.hashCode());
        final Object $labels = this.getLabels();
        result = result * 59 + (($labels == null) ? 43 : $labels.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $totalProfitRate = this.getTotalProfitRate();
        result = result * 59 + (($totalProfitRate == null) ? 43 : $totalProfitRate.hashCode());
        final Object $threeWeekWinRate = this.getThreeWeekWinRate();
        result = result * 59 + (($threeWeekWinRate == null) ? 43 : $threeWeekWinRate.hashCode());
        final Object $threeWeekCallbackRate = this.getThreeWeekCallbackRate();
        result = result * 59 + (($threeWeekCallbackRate == null) ? 43 : $threeWeekCallbackRate.hashCode());
        final Object $followExtractRate = this.getFollowExtractRate();
        result = result * 59 + (($followExtractRate == null) ? 43 : $followExtractRate.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        final Object $actualTimeCount = this.getActualTimeCount();
        result = result * 59 + (($actualTimeCount == null) ? 43 : $actualTimeCount.hashCode());
        final Object $transactionDays = this.getTransactionDays();
        result = result * 59 + (($transactionDays == null) ? 43 : $transactionDays.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityMember(memberId=" + this.getMemberId() + ", username=" + this.getUsername() + ", areaName=" + this.getAreaName() + ", personalIntroduction=" + this.getPersonalIntroduction() + ", sex=" + this.getSex() + ", labels=" + this.getLabels() + ", type=" + this.getType() + ", status=" + this.getStatus() + ", totalProfitRate=" + this.getTotalProfitRate() + ", threeWeekWinRate=" + this.getThreeWeekWinRate() + ", threeWeekCallbackRate=" + this.getThreeWeekCallbackRate() + ", followExtractRate=" + this.getFollowExtractRate() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", version=" + this.getVersion() + ", actualTimeCount=" + this.getActualTimeCount() + ", transactionDays=" + this.getTransactionDays() + ")";
    }
}
