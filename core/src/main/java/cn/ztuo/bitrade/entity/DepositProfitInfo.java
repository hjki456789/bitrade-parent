package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table
public class DepositProfitInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private String coinName;
    private Long depositId;
    private BigDecimal amount;
    private BigDecimal fundAmount;
    private BigDecimal depositAmount;
    private Long sequence;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    @Transient
    private String username;
    @Transient
    private String mobilePhone;
    @Transient
    private String email;

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public String getCoinName() {
        return this.coinName;
    }

    public Long getDepositId() {
        return this.depositId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getFundAmount() {
        return this.fundAmount;
    }

    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public Date getTime() {
        return this.time;
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setCoinName(final String coinName) {
        this.coinName = coinName;
    }

    public void setDepositId(final Long depositId) {
        this.depositId = depositId;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setFundAmount(final BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    public void setDepositAmount(final BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    public void setTime(final Date time) {
        this.time = time;
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

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DepositProfitInfo)) {
            return false;
        }
        final DepositProfitInfo other = (DepositProfitInfo) o;
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
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0102:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0102;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$coinName = this.getCoinName();
        final Object other$coinName = other.getCoinName();
        Label_0139:
        {
            if (this$coinName == null) {
                if (other$coinName == null) {
                    break Label_0139;
                }
            } else if (this$coinName.equals(other$coinName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$depositId = this.getDepositId();
        final Object other$depositId = other.getDepositId();
        Label_0176:
        {
            if (this$depositId == null) {
                if (other$depositId == null) {
                    break Label_0176;
                }
            } else if (this$depositId.equals(other$depositId)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0213:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0213;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$fundAmount = this.getFundAmount();
        final Object other$fundAmount = other.getFundAmount();
        Label_0250:
        {
            if (this$fundAmount == null) {
                if (other$fundAmount == null) {
                    break Label_0250;
                }
            } else if (this$fundAmount.equals(other$fundAmount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$depositAmount = this.getDepositAmount();
        final Object other$depositAmount = other.getDepositAmount();
        Label_0287:
        {
            if (this$depositAmount == null) {
                if (other$depositAmount == null) {
                    break Label_0287;
                }
            } else if (this$depositAmount.equals(other$depositAmount)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0324:
        {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0324;
                }
            } else if (this$sequence.equals(other$sequence)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        Label_0361:
        {
            if (this$time == null) {
                if (other$time == null) {
                    break Label_0361;
                }
            } else if (this$time.equals(other$time)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0398:
        {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0398;
                }
            } else if (this$username.equals(other$username)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0435:
        {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0435;
                }
            } else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null) {
            if (other$email == null) {
                return true;
            }
        } else if (this$email.equals(other$email)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DepositProfitInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $coinName = this.getCoinName();
        result = result * 59 + (($coinName == null) ? 43 : $coinName.hashCode());
        final Object $depositId = this.getDepositId();
        result = result * 59 + (($depositId == null) ? 43 : $depositId.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $fundAmount = this.getFundAmount();
        result = result * 59 + (($fundAmount == null) ? 43 : $fundAmount.hashCode());
        final Object $depositAmount = this.getDepositAmount();
        result = result * 59 + (($depositAmount == null) ? 43 : $depositAmount.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $time = this.getTime();
        result = result * 59 + (($time == null) ? 43 : $time.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $mobilePhone = this.getMobilePhone();
        result = result * 59 + (($mobilePhone == null) ? 43 : $mobilePhone.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "DepositProfitInfo(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", coinName=" + this.getCoinName() + ", depositId=" + this.getDepositId() + ", amount=" + this.getAmount() + ", fundAmount=" + this.getFundAmount() + ", depositAmount=" + this.getDepositAmount() + ", sequence=" + this.getSequence() + ", time=" + this.getTime() + ", username=" + this.getUsername() + ", mobilePhone=" + this.getMobilePhone() + ", email=" + this.getEmail() + ")";
    }
}
