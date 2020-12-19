package cn.ztuo.bitrade.entity;

import java.math.*;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member_team")
public class MemberTeam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long lowerMemberId;
    private Integer generation;
    private BigDecimal amount;
    @JoinColumn(name = "lowerMemberId", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne
    private Member lowerMember;
    private int isValidMember;

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public Long getLowerMemberId() {
        return this.lowerMemberId;
    }

    public Integer getGeneration() {
        return this.generation;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Member getLowerMember() {
        return this.lowerMember;
    }

    public int getIsValidMember() {
        return this.isValidMember;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setLowerMemberId(final Long lowerMemberId) {
        this.lowerMemberId = lowerMemberId;
    }

    public void setGeneration(final Integer generation) {
        this.generation = generation;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setLowerMember(final Member lowerMember) {
        this.lowerMember = lowerMember;
    }

    public void setIsValidMember(final int isValidMember) {
        this.isValidMember = isValidMember;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MemberTeam)) {
            return false;
        }
        final MemberTeam other = (MemberTeam) o;
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
        final Object this$lowerMemberId = this.getLowerMemberId();
        final Object other$lowerMemberId = other.getLowerMemberId();
        Label_0139:
        {
            if (this$lowerMemberId == null) {
                if (other$lowerMemberId == null) {
                    break Label_0139;
                }
            } else if (this$lowerMemberId.equals(other$lowerMemberId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$generation = this.getGeneration();
        final Object other$generation = other.getGeneration();
        Label_0176:
        {
            if (this$generation == null) {
                if (other$generation == null) {
                    break Label_0176;
                }
            } else if (this$generation.equals(other$generation)) {
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
        final Object this$lowerMember = this.getLowerMember();
        final Object other$lowerMember = other.getLowerMember();
        if (this$lowerMember == null) {
            if (other$lowerMember == null) {
                return this.getIsValidMember() == other.getIsValidMember();
            }
        } else if (this$lowerMember.equals(other$lowerMember)) {
            return this.getIsValidMember() == other.getIsValidMember();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MemberTeam;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $lowerMemberId = this.getLowerMemberId();
        result = result * 59 + (($lowerMemberId == null) ? 43 : $lowerMemberId.hashCode());
        final Object $generation = this.getGeneration();
        result = result * 59 + (($generation == null) ? 43 : $generation.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $lowerMember = this.getLowerMember();
        result = result * 59 + (($lowerMember == null) ? 43 : $lowerMember.hashCode());
        result = result * 59 + this.getIsValidMember();
        return result;
    }

    @Override
    public String toString() {
        return "MemberTeam(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", lowerMemberId=" + this.getLowerMemberId() + ", generation=" + this.getGeneration() + ", amount=" + this.getAmount() + ", lowerMember=" + this.getLowerMember() + ", isValidMember=" + this.getIsValidMember() + ")";
    }
}
