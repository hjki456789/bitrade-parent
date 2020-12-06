package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;

import cn.ztuo.bitrade.constant.*;

@Entity
@Table(name = "contract_member_profit_loss")
public class ContractMemberProfitLoss implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private BigDecimal profitLossAmount;
    private ProfitLossType type;
    private String weekStartTime;
    private String weekEndTime;

    public ContractMemberProfitLoss() {
        this.profitLossAmount = BigDecimal.ZERO;
    }

    public ContractMemberProfitLoss(final Long memberId, final BigDecimal profitLossAmount, final ProfitLossType type, final String weekStartTime, final String weekEndTime) {
        this.profitLossAmount = BigDecimal.ZERO;
        this.memberId = memberId;
        this.profitLossAmount = profitLossAmount;
        this.type = type;
        this.weekStartTime = weekStartTime;
        this.weekEndTime = weekEndTime;
    }

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public BigDecimal getProfitLossAmount() {
        return this.profitLossAmount;
    }

    public ProfitLossType getType() {
        return this.type;
    }

    public String getWeekStartTime() {
        return this.weekStartTime;
    }

    public String getWeekEndTime() {
        return this.weekEndTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setProfitLossAmount(final BigDecimal profitLossAmount) {
        this.profitLossAmount = profitLossAmount;
    }

    public void setType(final ProfitLossType type) {
        this.type = type;
    }

    public void setWeekStartTime(final String weekStartTime) {
        this.weekStartTime = weekStartTime;
    }

    public void setWeekEndTime(final String weekEndTime) {
        this.weekEndTime = weekEndTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractMemberProfitLoss)) {
            return false;
        }
        final ContractMemberProfitLoss other = (ContractMemberProfitLoss) o;
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
        final Object this$profitLossAmount = this.getProfitLossAmount();
        final Object other$profitLossAmount = other.getProfitLossAmount();
        Label_0139:
        {
            if (this$profitLossAmount == null) {
                if (other$profitLossAmount == null) {
                    break Label_0139;
                }
            } else if (this$profitLossAmount.equals(other$profitLossAmount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0176:
        {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0176;
                }
            } else if (this$type.equals(other$type)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$weekStartTime = this.getWeekStartTime();
        final Object other$weekStartTime = other.getWeekStartTime();
        Label_0213:
        {
            if (this$weekStartTime == null) {
                if (other$weekStartTime == null) {
                    break Label_0213;
                }
            } else if (this$weekStartTime.equals(other$weekStartTime)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$weekEndTime = this.getWeekEndTime();
        final Object other$weekEndTime = other.getWeekEndTime();
        if (this$weekEndTime == null) {
            if (other$weekEndTime == null) {
                return true;
            }
        } else if (this$weekEndTime.equals(other$weekEndTime)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractMemberProfitLoss;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $profitLossAmount = this.getProfitLossAmount();
        result = result * 59 + (($profitLossAmount == null) ? 43 : $profitLossAmount.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $weekStartTime = this.getWeekStartTime();
        result = result * 59 + (($weekStartTime == null) ? 43 : $weekStartTime.hashCode());
        final Object $weekEndTime = this.getWeekEndTime();
        result = result * 59 + (($weekEndTime == null) ? 43 : $weekEndTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractMemberProfitLoss(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", profitLossAmount=" + this.getProfitLossAmount() + ", type=" + this.getType() + ", weekStartTime=" + this.getWeekStartTime() + ", weekEndTime=" + this.getWeekEndTime() + ")";
    }
}
