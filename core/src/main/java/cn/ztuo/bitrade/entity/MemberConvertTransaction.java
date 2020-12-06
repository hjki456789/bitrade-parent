package cn.ztuo.bitrade.entity;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import java.util.*;

import org.hibernate.annotations.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "member_convert_transaction")
public class MemberConvertTransaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    @NotBlank(message = "\u88ab\u8f6c\u6362\u5e01\u4e0d\u5f97\u4e3a\u7a7a")
    private String baseCoin;
    @NotBlank(message = "\u8f6c\u6362\u5e01\u4e0d\u5f97\u4e3a\u7a7a")
    private String convertCoin;
    @Column(columnDefinition = "double(18,8) comment '\u88ab\u5bf9\u6362\u91d1\u989d'")
    private Double amount;
    @Column(columnDefinition = "double(18,8) comment '\u5151\u6362\u91d1\u989d'")
    private Double convertAmount;
    @Column(columnDefinition = "double(18,8) comment '\u624b\u7eed\u8d39'")
    private Double feeAmount;
    @Column(columnDefinition = "double(18,8) comment '\u5151\u6362\u6c47\u7387'")
    private Double rate;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Long sequence;

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public String getBaseCoin() {
        return this.baseCoin;
    }

    public String getConvertCoin() {
        return this.convertCoin;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Double getConvertAmount() {
        return this.convertAmount;
    }

    public Double getFeeAmount() {
        return this.feeAmount;
    }

    public Double getRate() {
        return this.rate;
    }

    public Date getCreateTime() {
        return this.createTime;
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

    public void setBaseCoin(final String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public void setConvertCoin(final String convertCoin) {
        this.convertCoin = convertCoin;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public void setConvertAmount(final Double convertAmount) {
        this.convertAmount = convertAmount;
    }

    public void setFeeAmount(final Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public void setRate(final Double rate) {
        this.rate = rate;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MemberConvertTransaction)) {
            return false;
        }
        final MemberConvertTransaction other = (MemberConvertTransaction) o;
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
        final Object this$baseCoin = this.getBaseCoin();
        final Object other$baseCoin = other.getBaseCoin();
        Label_0139:
        {
            if (this$baseCoin == null) {
                if (other$baseCoin == null) {
                    break Label_0139;
                }
            } else if (this$baseCoin.equals(other$baseCoin)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$convertCoin = this.getConvertCoin();
        final Object other$convertCoin = other.getConvertCoin();
        Label_0176:
        {
            if (this$convertCoin == null) {
                if (other$convertCoin == null) {
                    break Label_0176;
                }
            } else if (this$convertCoin.equals(other$convertCoin)) {
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
        final Object this$convertAmount = this.getConvertAmount();
        final Object other$convertAmount = other.getConvertAmount();
        Label_0250:
        {
            if (this$convertAmount == null) {
                if (other$convertAmount == null) {
                    break Label_0250;
                }
            } else if (this$convertAmount.equals(other$convertAmount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$feeAmount = this.getFeeAmount();
        final Object other$feeAmount = other.getFeeAmount();
        Label_0287:
        {
            if (this$feeAmount == null) {
                if (other$feeAmount == null) {
                    break Label_0287;
                }
            } else if (this$feeAmount.equals(other$feeAmount)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$rate = this.getRate();
        final Object other$rate = other.getRate();
        Label_0324:
        {
            if (this$rate == null) {
                if (other$rate == null) {
                    break Label_0324;
                }
            } else if (this$rate.equals(other$rate)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0361:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0361;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        if (this$sequence == null) {
            if (other$sequence == null) {
                return true;
            }
        } else if (this$sequence.equals(other$sequence)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MemberConvertTransaction;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $baseCoin = this.getBaseCoin();
        result = result * 59 + (($baseCoin == null) ? 43 : $baseCoin.hashCode());
        final Object $convertCoin = this.getConvertCoin();
        result = result * 59 + (($convertCoin == null) ? 43 : $convertCoin.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $convertAmount = this.getConvertAmount();
        result = result * 59 + (($convertAmount == null) ? 43 : $convertAmount.hashCode());
        final Object $feeAmount = this.getFeeAmount();
        result = result * 59 + (($feeAmount == null) ? 43 : $feeAmount.hashCode());
        final Object $rate = this.getRate();
        result = result * 59 + (($rate == null) ? 43 : $rate.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MemberConvertTransaction(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", baseCoin=" + this.getBaseCoin() + ", convertCoin=" + this.getConvertCoin() + ", amount=" + this.getAmount() + ", convertAmount=" + this.getConvertAmount() + ", feeAmount=" + this.getFeeAmount() + ", rate=" + this.getRate() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}
