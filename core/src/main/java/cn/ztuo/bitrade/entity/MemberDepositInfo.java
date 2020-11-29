package cn.ztuo.bitrade.entity;

import org.hibernate.annotations.*;
import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;

@Entity
@Table
public class MemberDepositInfo
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Long memberId;
    private Long depositTypeId;
    @JoinColumn(name = "depositTypeId", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DepositTypeInfo depositTypeInfo;
    private BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date investTime;
    private int status;
    private BigDecimal profit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date preEndTime;
    private Long sequence;
    @Transient
    private String username;
    @Transient
    private String mobilePhone;
    @Transient
    private String email;
    @Transient
    private long remainDays;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getDepositTypeId() {
        return this.depositTypeId;
    }
    
    public DepositTypeInfo getDepositTypeInfo() {
        return this.depositTypeInfo;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public Date getInvestTime() {
        return this.investTime;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public BigDecimal getProfit() {
        return this.profit;
    }
    
    public Date getEndTime() {
        return this.endTime;
    }
    
    public Date getPreEndTime() {
        return this.preEndTime;
    }
    
    public Long getSequence() {
        return this.sequence;
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
    
    public long getRemainDays() {
        return this.remainDays;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setDepositTypeId(final Long depositTypeId) {
        this.depositTypeId = depositTypeId;
    }
    
    public void setDepositTypeInfo(final DepositTypeInfo depositTypeInfo) {
        this.depositTypeInfo = depositTypeInfo;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setInvestTime(final Date investTime) {
        this.investTime = investTime;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setProfit(final BigDecimal profit) {
        this.profit = profit;
    }
    
    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }
    
    public void setPreEndTime(final Date preEndTime) {
        this.preEndTime = preEndTime;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
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
    
    public void setRemainDays(final long remainDays) {
        this.remainDays = remainDays;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MemberDepositInfo)) {
            return false;
        }
        final MemberDepositInfo other = (MemberDepositInfo)o;
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
        final Object this$depositTypeId = this.getDepositTypeId();
        final Object other$depositTypeId = other.getDepositTypeId();
        Label_0139: {
            if (this$depositTypeId == null) {
                if (other$depositTypeId == null) {
                    break Label_0139;
                }
            }
            else if (this$depositTypeId.equals(other$depositTypeId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$depositTypeInfo = this.getDepositTypeInfo();
        final Object other$depositTypeInfo = other.getDepositTypeInfo();
        Label_0176: {
            if (this$depositTypeInfo == null) {
                if (other$depositTypeInfo == null) {
                    break Label_0176;
                }
            }
            else if (this$depositTypeInfo.equals(other$depositTypeInfo)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0213: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0213;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$investTime = this.getInvestTime();
        final Object other$investTime = other.getInvestTime();
        Label_0250: {
            if (this$investTime == null) {
                if (other$investTime == null) {
                    break Label_0250;
                }
            }
            else if (this$investTime.equals(other$investTime)) {
                break Label_0250;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$profit = this.getProfit();
        final Object other$profit = other.getProfit();
        Label_0300: {
            if (this$profit == null) {
                if (other$profit == null) {
                    break Label_0300;
                }
            }
            else if (this$profit.equals(other$profit)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$endTime = this.getEndTime();
        final Object other$endTime = other.getEndTime();
        Label_0337: {
            if (this$endTime == null) {
                if (other$endTime == null) {
                    break Label_0337;
                }
            }
            else if (this$endTime.equals(other$endTime)) {
                break Label_0337;
            }
            return false;
        }
        final Object this$preEndTime = this.getPreEndTime();
        final Object other$preEndTime = other.getPreEndTime();
        Label_0374: {
            if (this$preEndTime == null) {
                if (other$preEndTime == null) {
                    break Label_0374;
                }
            }
            else if (this$preEndTime.equals(other$preEndTime)) {
                break Label_0374;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0411: {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0411;
                }
            }
            else if (this$sequence.equals(other$sequence)) {
                break Label_0411;
            }
            return false;
        }
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0448: {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0448;
                }
            }
            else if (this$username.equals(other$username)) {
                break Label_0448;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0485: {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0485;
                }
            }
            else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0485;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null) {
            if (other$email == null) {
                return this.getRemainDays() == other.getRemainDays();
            }
        }
        else if (this$email.equals(other$email)) {
            return this.getRemainDays() == other.getRemainDays();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof MemberDepositInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $depositTypeId = this.getDepositTypeId();
        result = result * 59 + (($depositTypeId == null) ? 43 : $depositTypeId.hashCode());
        final Object $depositTypeInfo = this.getDepositTypeInfo();
        result = result * 59 + (($depositTypeInfo == null) ? 43 : $depositTypeInfo.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $investTime = this.getInvestTime();
        result = result * 59 + (($investTime == null) ? 43 : $investTime.hashCode());
        result = result * 59 + this.getStatus();
        final Object $profit = this.getProfit();
        result = result * 59 + (($profit == null) ? 43 : $profit.hashCode());
        final Object $endTime = this.getEndTime();
        result = result * 59 + (($endTime == null) ? 43 : $endTime.hashCode());
        final Object $preEndTime = this.getPreEndTime();
        result = result * 59 + (($preEndTime == null) ? 43 : $preEndTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $mobilePhone = this.getMobilePhone();
        result = result * 59 + (($mobilePhone == null) ? 43 : $mobilePhone.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final long $remainDays = this.getRemainDays();
        result = result * 59 + (int)($remainDays >>> 32 ^ $remainDays);
        return result;
    }
    
    @Override
    public String toString() {
        return "MemberDepositInfo(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", depositTypeId=" + this.getDepositTypeId() + ", depositTypeInfo=" + this.getDepositTypeInfo() + ", amount=" + this.getAmount() + ", investTime=" + this.getInvestTime() + ", status=" + this.getStatus() + ", profit=" + this.getProfit() + ", endTime=" + this.getEndTime() + ", preEndTime=" + this.getPreEndTime() + ", sequence=" + this.getSequence() + ", username=" + this.getUsername() + ", mobilePhone=" + this.getMobilePhone() + ", email=" + this.getEmail() + ", remainDays=" + this.getRemainDays() + ")";
    }
}
