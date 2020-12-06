package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractRobotSymbolConfig {
    @Id
    private String symbol;
    private Long fromExchangeId;
    private String fromExchangeSymbol;
    private BigDecimal amountMultiple;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private long version;

    public String getSymbol() {
        return this.symbol;
    }

    public Long getFromExchangeId() {
        return this.fromExchangeId;
    }

    public String getFromExchangeSymbol() {
        return this.fromExchangeSymbol;
    }

    public BigDecimal getAmountMultiple() {
        return this.amountMultiple;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public long getSequence() {
        return this.sequence;
    }

    public long getVersion() {
        return this.version;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setFromExchangeId(final Long fromExchangeId) {
        this.fromExchangeId = fromExchangeId;
    }

    public void setFromExchangeSymbol(final String fromExchangeSymbol) {
        this.fromExchangeSymbol = fromExchangeSymbol;
    }

    public void setAmountMultiple(final BigDecimal amountMultiple) {
        this.amountMultiple = amountMultiple;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }

    public void setVersion(final long version) {
        this.version = version;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractRobotSymbolConfig)) {
            return false;
        }
        final ContractRobotSymbolConfig other = (ContractRobotSymbolConfig) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0065:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0065;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$fromExchangeId = this.getFromExchangeId();
        final Object other$fromExchangeId = other.getFromExchangeId();
        Label_0102:
        {
            if (this$fromExchangeId == null) {
                if (other$fromExchangeId == null) {
                    break Label_0102;
                }
            } else if (this$fromExchangeId.equals(other$fromExchangeId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$fromExchangeSymbol = this.getFromExchangeSymbol();
        final Object other$fromExchangeSymbol = other.getFromExchangeSymbol();
        Label_0139:
        {
            if (this$fromExchangeSymbol == null) {
                if (other$fromExchangeSymbol == null) {
                    break Label_0139;
                }
            } else if (this$fromExchangeSymbol.equals(other$fromExchangeSymbol)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$amountMultiple = this.getAmountMultiple();
        final Object other$amountMultiple = other.getAmountMultiple();
        Label_0176:
        {
            if (this$amountMultiple == null) {
                if (other$amountMultiple == null) {
                    break Label_0176;
                }
            } else if (this$amountMultiple.equals(other$amountMultiple)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0213:
        {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0213;
                }
            } else if (this$status.equals(other$status)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null) {
            if (other$createTime == null) {
                return this.getSequence() == other.getSequence() && this.getVersion() == other.getVersion();
            }
        } else if (this$createTime.equals(other$createTime)) {
            return this.getSequence() == other.getSequence() && this.getVersion() == other.getVersion();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractRobotSymbolConfig;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $fromExchangeId = this.getFromExchangeId();
        result = result * 59 + (($fromExchangeId == null) ? 43 : $fromExchangeId.hashCode());
        final Object $fromExchangeSymbol = this.getFromExchangeSymbol();
        result = result * 59 + (($fromExchangeSymbol == null) ? 43 : $fromExchangeSymbol.hashCode());
        final Object $amountMultiple = this.getAmountMultiple();
        result = result * 59 + (($amountMultiple == null) ? 43 : $amountMultiple.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        return result;
    }

    @Override
    public String toString() {
        return "ContractRobotSymbolConfig(symbol=" + this.getSymbol() + ", fromExchangeId=" + this.getFromExchangeId() + ", fromExchangeSymbol=" + this.getFromExchangeSymbol() + ", amountMultiple=" + this.getAmountMultiple() + ", status=" + this.getStatus() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", version=" + this.getVersion() + ")";
    }
}
