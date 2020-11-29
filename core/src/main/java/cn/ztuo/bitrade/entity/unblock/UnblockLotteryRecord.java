package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import java.util.*;
import java.math.*;
import javax.persistence.*;

@Entity
@Table
public class UnblockLotteryRecord implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private long sequence;
    private Long lotteryId;
    private String lotteryLevel;
    private String lotteryInstruction;
    private Integer isWinne;
    private Date createTime;
    private String coin;
    private BigDecimal amount;
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
    
    public long getSequence() {
        return this.sequence;
    }
    
    public Long getLotteryId() {
        return this.lotteryId;
    }
    
    public String getLotteryLevel() {
        return this.lotteryLevel;
    }
    
    public String getLotteryInstruction() {
        return this.lotteryInstruction;
    }
    
    public Integer getIsWinne() {
        return this.isWinne;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public String getCoin() {
        return this.coin;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
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
    
    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }
    
    public void setLotteryId(final Long lotteryId) {
        this.lotteryId = lotteryId;
    }
    
    public void setLotteryLevel(final String lotteryLevel) {
        this.lotteryLevel = lotteryLevel;
    }
    
    public void setLotteryInstruction(final String lotteryInstruction) {
        this.lotteryInstruction = lotteryInstruction;
    }
    
    public void setIsWinne(final Integer isWinne) {
        this.isWinne = isWinne;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setCoin(final String coin) {
        this.coin = coin;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
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
        if (!(o instanceof UnblockLotteryRecord)) {
            return false;
        }
        final UnblockLotteryRecord other = (UnblockLotteryRecord)o;
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
        if (this.getSequence() != other.getSequence()) {
            return false;
        }
        final Object this$lotteryId = this.getLotteryId();
        final Object other$lotteryId = other.getLotteryId();
        Label_0153: {
            if (this$lotteryId == null) {
                if (other$lotteryId == null) {
                    break Label_0153;
                }
            }
            else if (this$lotteryId.equals(other$lotteryId)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$lotteryLevel = this.getLotteryLevel();
        final Object other$lotteryLevel = other.getLotteryLevel();
        Label_0190: {
            if (this$lotteryLevel == null) {
                if (other$lotteryLevel == null) {
                    break Label_0190;
                }
            }
            else if (this$lotteryLevel.equals(other$lotteryLevel)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$lotteryInstruction = this.getLotteryInstruction();
        final Object other$lotteryInstruction = other.getLotteryInstruction();
        Label_0227: {
            if (this$lotteryInstruction == null) {
                if (other$lotteryInstruction == null) {
                    break Label_0227;
                }
            }
            else if (this$lotteryInstruction.equals(other$lotteryInstruction)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$isWinne = this.getIsWinne();
        final Object other$isWinne = other.getIsWinne();
        Label_0264: {
            if (this$isWinne == null) {
                if (other$isWinne == null) {
                    break Label_0264;
                }
            }
            else if (this$isWinne.equals(other$isWinne)) {
                break Label_0264;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0301: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0301;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0301;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0338: {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0338;
                }
            }
            else if (this$coin.equals(other$coin)) {
                break Label_0338;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0375: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0375;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0375;
            }
            return false;
        }
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0412: {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0412;
                }
            }
            else if (this$username.equals(other$username)) {
                break Label_0412;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0449: {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0449;
                }
            }
            else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0449;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null) {
            if (other$email == null) {
                return true;
            }
        }
        else if (this$email.equals(other$email)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UnblockLotteryRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int)($sequence >>> 32 ^ $sequence);
        final Object $lotteryId = this.getLotteryId();
        result = result * 59 + (($lotteryId == null) ? 43 : $lotteryId.hashCode());
        final Object $lotteryLevel = this.getLotteryLevel();
        result = result * 59 + (($lotteryLevel == null) ? 43 : $lotteryLevel.hashCode());
        final Object $lotteryInstruction = this.getLotteryInstruction();
        result = result * 59 + (($lotteryInstruction == null) ? 43 : $lotteryInstruction.hashCode());
        final Object $isWinne = this.getIsWinne();
        result = result * 59 + (($isWinne == null) ? 43 : $isWinne.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
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
        return "UnblockLotteryRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", sequence=" + this.getSequence() + ", lotteryId=" + this.getLotteryId() + ", lotteryLevel=" + this.getLotteryLevel() + ", lotteryInstruction=" + this.getLotteryInstruction() + ", isWinne=" + this.getIsWinne() + ", createTime=" + this.getCreateTime() + ", coin=" + this.getCoin() + ", amount=" + this.getAmount() + ", username=" + this.getUsername() + ", mobilePhone=" + this.getMobilePhone() + ", email=" + this.getEmail() + ")";
    }
}
