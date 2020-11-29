package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import cn.ztuo.bitrade.entity.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class UnblockMemberReward implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long originMemberId;
    private Integer generation;
    private String coin;
    private BigDecimal amount;
    private BigDecimal afterBalance;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Long sequence;
    @JoinColumn(name = "memberId", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne
    private Member member;
    @JoinColumn(name = "originMemberId", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne
    private Member originMember;
    @Transient
    private BigDecimal balance;

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public Long getOriginMemberId() {
        return this.originMemberId;
    }

    public Integer getGeneration() {
        return this.generation;
    }

    public String getCoin() {
        return this.coin;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getAfterBalance() {
        return this.afterBalance;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public Member getMember() {
        return this.member;
    }

    public Member getOriginMember() {
        return this.originMember;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setOriginMemberId(final Long originMemberId) {
        this.originMemberId = originMemberId;
    }

    public void setGeneration(final Integer generation) {
        this.generation = generation;
    }

    public void setCoin(final String coin) {
        this.coin = coin;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setAfterBalance(final BigDecimal afterBalance) {
        this.afterBalance = afterBalance;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setOriginMember(final Member originMember) {
        this.originMember = originMember;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnblockMemberReward)) {
            return false;
        }
        final UnblockMemberReward other = (UnblockMemberReward)o;
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
        final Object this$originMemberId = this.getOriginMemberId();
        final Object other$originMemberId = other.getOriginMemberId();
        Label_0139: {
            if (this$originMemberId == null) {
                if (other$originMemberId == null) {
                    break Label_0139;
                }
            }
            else if (this$originMemberId.equals(other$originMemberId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$generation = this.getGeneration();
        final Object other$generation = other.getGeneration();
        Label_0176: {
            if (this$generation == null) {
                if (other$generation == null) {
                    break Label_0176;
                }
            }
            else if (this$generation.equals(other$generation)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0213: {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0213;
                }
            }
            else if (this$coin.equals(other$coin)) {
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
        final Object this$afterBalance = this.getAfterBalance();
        final Object other$afterBalance = other.getAfterBalance();
        Label_0287: {
            if (this$afterBalance == null) {
                if (other$afterBalance == null) {
                    break Label_0287;
                }
            }
            else if (this$afterBalance.equals(other$afterBalance)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0324: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0324;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0361: {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0361;
                }
            }
            else if (this$sequence.equals(other$sequence)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0398: {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0398;
                }
            }
            else if (this$member.equals(other$member)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$originMember = this.getOriginMember();
        final Object other$originMember = other.getOriginMember();
        Label_0435: {
            if (this$originMember == null) {
                if (other$originMember == null) {
                    break Label_0435;
                }
            }
            else if (this$originMember.equals(other$originMember)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        if (this$balance == null) {
            if (other$balance == null) {
                return true;
            }
        }
        else if (this$balance.equals(other$balance)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnblockMemberReward;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $originMemberId = this.getOriginMemberId();
        result = result * 59 + (($originMemberId == null) ? 43 : $originMemberId.hashCode());
        final Object $generation = this.getGeneration();
        result = result * 59 + (($generation == null) ? 43 : $generation.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $afterBalance = this.getAfterBalance();
        result = result * 59 + (($afterBalance == null) ? 43 : $afterBalance.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $originMember = this.getOriginMember();
        result = result * 59 + (($originMember == null) ? 43 : $originMember.hashCode());
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UnblockMemberReward(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", originMemberId=" + this.getOriginMemberId() + ", generation=" + this.getGeneration() + ", coin=" + this.getCoin() + ", amount=" + this.getAmount() + ", afterBalance=" + this.getAfterBalance() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", member=" + this.getMember() + ", originMember=" + this.getOriginMember() + ", balance=" + this.getBalance() + ")";
    }
}
