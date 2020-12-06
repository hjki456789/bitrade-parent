package cn.ztuo.bitrade.entity;

import java.io.*;
import java.math.*;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class FeeStastic implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private BigDecimal amount;
    private BigDecimal bonusAmount;
    private String unit;
    private String symbol;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    private Long sequence;

    public Long getId() {
        return this.id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getBonusAmount() {
        return this.bonusAmount;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Date getDate() {
        return this.date;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setBonusAmount(final BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FeeStastic)) {
            return false;
        }
        final FeeStastic other = (FeeStastic) o;
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
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0102:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0102;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$bonusAmount = this.getBonusAmount();
        final Object other$bonusAmount = other.getBonusAmount();
        Label_0139:
        {
            if (this$bonusAmount == null) {
                if (other$bonusAmount == null) {
                    break Label_0139;
                }
            } else if (this$bonusAmount.equals(other$bonusAmount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$unit = this.getUnit();
        final Object other$unit = other.getUnit();
        Label_0176:
        {
            if (this$unit == null) {
                if (other$unit == null) {
                    break Label_0176;
                }
            } else if (this$unit.equals(other$unit)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0213:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0213;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        Label_0250:
        {
            if (this$date == null) {
                if (other$date == null) {
                    break Label_0250;
                }
            } else if (this$date.equals(other$date)) {
                break Label_0250;
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
        return other instanceof FeeStastic;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $bonusAmount = this.getBonusAmount();
        result = result * 59 + (($bonusAmount == null) ? 43 : $bonusAmount.hashCode());
        final Object $unit = this.getUnit();
        result = result * 59 + (($unit == null) ? 43 : $unit.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $date = this.getDate();
        result = result * 59 + (($date == null) ? 43 : $date.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FeeStastic(id=" + this.getId() + ", amount=" + this.getAmount() + ", bonusAmount=" + this.getBonusAmount() + ", unit=" + this.getUnit() + ", symbol=" + this.getSymbol() + ", date=" + this.getDate() + ", sequence=" + this.getSequence() + ")";
    }
}
