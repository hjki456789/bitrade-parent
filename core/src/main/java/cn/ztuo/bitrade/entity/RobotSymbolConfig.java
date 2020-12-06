package cn.ztuo.bitrade.entity;

import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table
public class RobotSymbolConfig {
    @Id
    private String symbol;
    private BigDecimal baseCoinBalance;
    private BigDecimal tradeCoinBalance;
    private BigDecimal robotOrderMax;
    private Long fromExchangeId;
    private String fromExchangeSymbol;
    private Long memberId;
    private BigDecimal depthAmountDownRate;
    private BigDecimal tradeAmountUpRate;
    private BigDecimal buyPriceDownRate;
    private BigDecimal sellPriceUpRate;
    private BigDecimal fee;
    private BigDecimal depthIntervalMin;
    private BigDecimal depthIntervalMax;
    private Integer status;
    private Integer arbitrageStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    private long version;
    @Transient
    private Member member;

    public RobotSymbolConfig() {
        this.baseCoinBalance = BigDecimal.ZERO;
        this.tradeCoinBalance = BigDecimal.ZERO;
        this.robotOrderMax = BigDecimal.ZERO;
        this.depthAmountDownRate = BigDecimal.ZERO;
        this.tradeAmountUpRate = BigDecimal.ZERO;
        this.buyPriceDownRate = BigDecimal.ZERO;
        this.sellPriceUpRate = BigDecimal.ZERO;
        this.fee = BigDecimal.ZERO;
        this.depthIntervalMin = BigDecimal.ZERO;
        this.depthIntervalMax = BigDecimal.ZERO;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public BigDecimal getBaseCoinBalance() {
        return this.baseCoinBalance;
    }

    public BigDecimal getTradeCoinBalance() {
        return this.tradeCoinBalance;
    }

    public BigDecimal getRobotOrderMax() {
        return this.robotOrderMax;
    }

    public Long getFromExchangeId() {
        return this.fromExchangeId;
    }

    public String getFromExchangeSymbol() {
        return this.fromExchangeSymbol;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public BigDecimal getDepthAmountDownRate() {
        return this.depthAmountDownRate;
    }

    public BigDecimal getTradeAmountUpRate() {
        return this.tradeAmountUpRate;
    }

    public BigDecimal getBuyPriceDownRate() {
        return this.buyPriceDownRate;
    }

    public BigDecimal getSellPriceUpRate() {
        return this.sellPriceUpRate;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public BigDecimal getDepthIntervalMin() {
        return this.depthIntervalMin;
    }

    public BigDecimal getDepthIntervalMax() {
        return this.depthIntervalMax;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getArbitrageStatus() {
        return this.arbitrageStatus;
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

    public Member getMember() {
        return this.member;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setBaseCoinBalance(final BigDecimal baseCoinBalance) {
        this.baseCoinBalance = baseCoinBalance;
    }

    public void setTradeCoinBalance(final BigDecimal tradeCoinBalance) {
        this.tradeCoinBalance = tradeCoinBalance;
    }

    public void setRobotOrderMax(final BigDecimal robotOrderMax) {
        this.robotOrderMax = robotOrderMax;
    }

    public void setFromExchangeId(final Long fromExchangeId) {
        this.fromExchangeId = fromExchangeId;
    }

    public void setFromExchangeSymbol(final String fromExchangeSymbol) {
        this.fromExchangeSymbol = fromExchangeSymbol;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setDepthAmountDownRate(final BigDecimal depthAmountDownRate) {
        this.depthAmountDownRate = depthAmountDownRate;
    }

    public void setTradeAmountUpRate(final BigDecimal tradeAmountUpRate) {
        this.tradeAmountUpRate = tradeAmountUpRate;
    }

    public void setBuyPriceDownRate(final BigDecimal buyPriceDownRate) {
        this.buyPriceDownRate = buyPriceDownRate;
    }

    public void setSellPriceUpRate(final BigDecimal sellPriceUpRate) {
        this.sellPriceUpRate = sellPriceUpRate;
    }

    public void setFee(final BigDecimal fee) {
        this.fee = fee;
    }

    public void setDepthIntervalMin(final BigDecimal depthIntervalMin) {
        this.depthIntervalMin = depthIntervalMin;
    }

    public void setDepthIntervalMax(final BigDecimal depthIntervalMax) {
        this.depthIntervalMax = depthIntervalMax;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setArbitrageStatus(final Integer arbitrageStatus) {
        this.arbitrageStatus = arbitrageStatus;
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

    public void setMember(final Member member) {
        this.member = member;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RobotSymbolConfig)) {
            return false;
        }
        final RobotSymbolConfig other = (RobotSymbolConfig) o;
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
        final Object this$baseCoinBalance = this.getBaseCoinBalance();
        final Object other$baseCoinBalance = other.getBaseCoinBalance();
        Label_0102:
        {
            if (this$baseCoinBalance == null) {
                if (other$baseCoinBalance == null) {
                    break Label_0102;
                }
            } else if (this$baseCoinBalance.equals(other$baseCoinBalance)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$tradeCoinBalance = this.getTradeCoinBalance();
        final Object other$tradeCoinBalance = other.getTradeCoinBalance();
        Label_0139:
        {
            if (this$tradeCoinBalance == null) {
                if (other$tradeCoinBalance == null) {
                    break Label_0139;
                }
            } else if (this$tradeCoinBalance.equals(other$tradeCoinBalance)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$robotOrderMax = this.getRobotOrderMax();
        final Object other$robotOrderMax = other.getRobotOrderMax();
        Label_0176:
        {
            if (this$robotOrderMax == null) {
                if (other$robotOrderMax == null) {
                    break Label_0176;
                }
            } else if (this$robotOrderMax.equals(other$robotOrderMax)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$fromExchangeId = this.getFromExchangeId();
        final Object other$fromExchangeId = other.getFromExchangeId();
        Label_0213:
        {
            if (this$fromExchangeId == null) {
                if (other$fromExchangeId == null) {
                    break Label_0213;
                }
            } else if (this$fromExchangeId.equals(other$fromExchangeId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$fromExchangeSymbol = this.getFromExchangeSymbol();
        final Object other$fromExchangeSymbol = other.getFromExchangeSymbol();
        Label_0250:
        {
            if (this$fromExchangeSymbol == null) {
                if (other$fromExchangeSymbol == null) {
                    break Label_0250;
                }
            } else if (this$fromExchangeSymbol.equals(other$fromExchangeSymbol)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0287:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0287;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$depthAmountDownRate = this.getDepthAmountDownRate();
        final Object other$depthAmountDownRate = other.getDepthAmountDownRate();
        Label_0324:
        {
            if (this$depthAmountDownRate == null) {
                if (other$depthAmountDownRate == null) {
                    break Label_0324;
                }
            } else if (this$depthAmountDownRate.equals(other$depthAmountDownRate)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$tradeAmountUpRate = this.getTradeAmountUpRate();
        final Object other$tradeAmountUpRate = other.getTradeAmountUpRate();
        Label_0361:
        {
            if (this$tradeAmountUpRate == null) {
                if (other$tradeAmountUpRate == null) {
                    break Label_0361;
                }
            } else if (this$tradeAmountUpRate.equals(other$tradeAmountUpRate)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$buyPriceDownRate = this.getBuyPriceDownRate();
        final Object other$buyPriceDownRate = other.getBuyPriceDownRate();
        Label_0398:
        {
            if (this$buyPriceDownRate == null) {
                if (other$buyPriceDownRate == null) {
                    break Label_0398;
                }
            } else if (this$buyPriceDownRate.equals(other$buyPriceDownRate)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$sellPriceUpRate = this.getSellPriceUpRate();
        final Object other$sellPriceUpRate = other.getSellPriceUpRate();
        Label_0435:
        {
            if (this$sellPriceUpRate == null) {
                if (other$sellPriceUpRate == null) {
                    break Label_0435;
                }
            } else if (this$sellPriceUpRate.equals(other$sellPriceUpRate)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$fee = this.getFee();
        final Object other$fee = other.getFee();
        Label_0472:
        {
            if (this$fee == null) {
                if (other$fee == null) {
                    break Label_0472;
                }
            } else if (this$fee.equals(other$fee)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$depthIntervalMin = this.getDepthIntervalMin();
        final Object other$depthIntervalMin = other.getDepthIntervalMin();
        Label_0509:
        {
            if (this$depthIntervalMin == null) {
                if (other$depthIntervalMin == null) {
                    break Label_0509;
                }
            } else if (this$depthIntervalMin.equals(other$depthIntervalMin)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$depthIntervalMax = this.getDepthIntervalMax();
        final Object other$depthIntervalMax = other.getDepthIntervalMax();
        Label_0546:
        {
            if (this$depthIntervalMax == null) {
                if (other$depthIntervalMax == null) {
                    break Label_0546;
                }
            } else if (this$depthIntervalMax.equals(other$depthIntervalMax)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0583:
        {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0583;
                }
            } else if (this$status.equals(other$status)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$arbitrageStatus = this.getArbitrageStatus();
        final Object other$arbitrageStatus = other.getArbitrageStatus();
        Label_0620:
        {
            if (this$arbitrageStatus == null) {
                if (other$arbitrageStatus == null) {
                    break Label_0620;
                }
            } else if (this$arbitrageStatus.equals(other$arbitrageStatus)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0657:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0657;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0657;
            }
            return false;
        }
        if (this.getSequence() != other.getSequence()) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        if (this$member == null) {
            if (other$member == null) {
                return true;
            }
        } else if (this$member.equals(other$member)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RobotSymbolConfig;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $baseCoinBalance = this.getBaseCoinBalance();
        result = result * 59 + (($baseCoinBalance == null) ? 43 : $baseCoinBalance.hashCode());
        final Object $tradeCoinBalance = this.getTradeCoinBalance();
        result = result * 59 + (($tradeCoinBalance == null) ? 43 : $tradeCoinBalance.hashCode());
        final Object $robotOrderMax = this.getRobotOrderMax();
        result = result * 59 + (($robotOrderMax == null) ? 43 : $robotOrderMax.hashCode());
        final Object $fromExchangeId = this.getFromExchangeId();
        result = result * 59 + (($fromExchangeId == null) ? 43 : $fromExchangeId.hashCode());
        final Object $fromExchangeSymbol = this.getFromExchangeSymbol();
        result = result * 59 + (($fromExchangeSymbol == null) ? 43 : $fromExchangeSymbol.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $depthAmountDownRate = this.getDepthAmountDownRate();
        result = result * 59 + (($depthAmountDownRate == null) ? 43 : $depthAmountDownRate.hashCode());
        final Object $tradeAmountUpRate = this.getTradeAmountUpRate();
        result = result * 59 + (($tradeAmountUpRate == null) ? 43 : $tradeAmountUpRate.hashCode());
        final Object $buyPriceDownRate = this.getBuyPriceDownRate();
        result = result * 59 + (($buyPriceDownRate == null) ? 43 : $buyPriceDownRate.hashCode());
        final Object $sellPriceUpRate = this.getSellPriceUpRate();
        result = result * 59 + (($sellPriceUpRate == null) ? 43 : $sellPriceUpRate.hashCode());
        final Object $fee = this.getFee();
        result = result * 59 + (($fee == null) ? 43 : $fee.hashCode());
        final Object $depthIntervalMin = this.getDepthIntervalMin();
        result = result * 59 + (($depthIntervalMin == null) ? 43 : $depthIntervalMin.hashCode());
        final Object $depthIntervalMax = this.getDepthIntervalMax();
        result = result * 59 + (($depthIntervalMax == null) ? 43 : $depthIntervalMax.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $arbitrageStatus = this.getArbitrageStatus();
        result = result * 59 + (($arbitrageStatus == null) ? 43 : $arbitrageStatus.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RobotSymbolConfig(symbol=" + this.getSymbol() + ", baseCoinBalance=" + this.getBaseCoinBalance() + ", tradeCoinBalance=" + this.getTradeCoinBalance() + ", robotOrderMax=" + this.getRobotOrderMax() + ", fromExchangeId=" + this.getFromExchangeId() + ", fromExchangeSymbol=" + this.getFromExchangeSymbol() + ", memberId=" + this.getMemberId() + ", depthAmountDownRate=" + this.getDepthAmountDownRate() + ", tradeAmountUpRate=" + this.getTradeAmountUpRate() + ", buyPriceDownRate=" + this.getBuyPriceDownRate() + ", sellPriceUpRate=" + this.getSellPriceUpRate() + ", fee=" + this.getFee() + ", depthIntervalMin=" + this.getDepthIntervalMin() + ", depthIntervalMax=" + this.getDepthIntervalMax() + ", status=" + this.getStatus() + ", arbitrageStatus=" + this.getArbitrageStatus() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", version=" + this.getVersion() + ", member=" + this.getMember() + ")";
    }
}
