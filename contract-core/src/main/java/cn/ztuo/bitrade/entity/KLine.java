package cn.ztuo.bitrade.entity;

import java.math.*;

public class KLine {
    private String symbol;
    private BigDecimal openPrice;
    private BigDecimal highestPrice;
    private BigDecimal lowestPrice;
    private BigDecimal closePrice;
    private long time;
    private String period;
    private int count;
    private BigDecimal volume;
    private BigDecimal turnover;

    public KLine() {
        this.openPrice = BigDecimal.ZERO;
        this.highestPrice = BigDecimal.ZERO;
        this.lowestPrice = BigDecimal.ZERO;
        this.closePrice = BigDecimal.ZERO;
        this.volume = BigDecimal.ZERO;
        this.turnover = BigDecimal.ZERO;
    }

    public KLine(final String period) {
        this.openPrice = BigDecimal.ZERO;
        this.highestPrice = BigDecimal.ZERO;
        this.lowestPrice = BigDecimal.ZERO;
        this.closePrice = BigDecimal.ZERO;
        this.volume = BigDecimal.ZERO;
        this.turnover = BigDecimal.ZERO;
        this.period = period;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public BigDecimal getOpenPrice() {
        return this.openPrice;
    }

    public BigDecimal getHighestPrice() {
        return this.highestPrice;
    }

    public BigDecimal getLowestPrice() {
        return this.lowestPrice;
    }

    public BigDecimal getClosePrice() {
        return this.closePrice;
    }

    public long getTime() {
        return this.time;
    }

    public String getPeriod() {
        return this.period;
    }

    public int getCount() {
        return this.count;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    public BigDecimal getTurnover() {
        return this.turnover;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setOpenPrice(final BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public void setHighestPrice(final BigDecimal highestPrice) {
        this.highestPrice = highestPrice;
    }

    public void setLowestPrice(final BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public void setClosePrice(final BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public void setPeriod(final String period) {
        this.period = period;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public void setVolume(final BigDecimal volume) {
        this.volume = volume;
    }

    public void setTurnover(final BigDecimal turnover) {
        this.turnover = turnover;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof KLine)) {
            return false;
        }
        final KLine other = (KLine) o;
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
        final Object this$openPrice = this.getOpenPrice();
        final Object other$openPrice = other.getOpenPrice();
        Label_0102:
        {
            if (this$openPrice == null) {
                if (other$openPrice == null) {
                    break Label_0102;
                }
            } else if (this$openPrice.equals(other$openPrice)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$highestPrice = this.getHighestPrice();
        final Object other$highestPrice = other.getHighestPrice();
        Label_0139:
        {
            if (this$highestPrice == null) {
                if (other$highestPrice == null) {
                    break Label_0139;
                }
            } else if (this$highestPrice.equals(other$highestPrice)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$lowestPrice = this.getLowestPrice();
        final Object other$lowestPrice = other.getLowestPrice();
        Label_0176:
        {
            if (this$lowestPrice == null) {
                if (other$lowestPrice == null) {
                    break Label_0176;
                }
            } else if (this$lowestPrice.equals(other$lowestPrice)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$closePrice = this.getClosePrice();
        final Object other$closePrice = other.getClosePrice();
        Label_0213:
        {
            if (this$closePrice == null) {
                if (other$closePrice == null) {
                    break Label_0213;
                }
            } else if (this$closePrice.equals(other$closePrice)) {
                break Label_0213;
            }
            return false;
        }
        if (this.getTime() != other.getTime()) {
            return false;
        }
        final Object this$period = this.getPeriod();
        final Object other$period = other.getPeriod();
        Label_0264:
        {
            if (this$period == null) {
                if (other$period == null) {
                    break Label_0264;
                }
            } else if (this$period.equals(other$period)) {
                break Label_0264;
            }
            return false;
        }
        if (this.getCount() != other.getCount()) {
            return false;
        }
        final Object this$volume = this.getVolume();
        final Object other$volume = other.getVolume();
        Label_0314:
        {
            if (this$volume == null) {
                if (other$volume == null) {
                    break Label_0314;
                }
            } else if (this$volume.equals(other$volume)) {
                break Label_0314;
            }
            return false;
        }
        final Object this$turnover = this.getTurnover();
        final Object other$turnover = other.getTurnover();
        if (this$turnover == null) {
            if (other$turnover == null) {
                return true;
            }
        } else if (this$turnover.equals(other$turnover)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof KLine;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $openPrice = this.getOpenPrice();
        result = result * 59 + (($openPrice == null) ? 43 : $openPrice.hashCode());
        final Object $highestPrice = this.getHighestPrice();
        result = result * 59 + (($highestPrice == null) ? 43 : $highestPrice.hashCode());
        final Object $lowestPrice = this.getLowestPrice();
        result = result * 59 + (($lowestPrice == null) ? 43 : $lowestPrice.hashCode());
        final Object $closePrice = this.getClosePrice();
        result = result * 59 + (($closePrice == null) ? 43 : $closePrice.hashCode());
        final long $time = this.getTime();
        result = result * 59 + (int) ($time >>> 32 ^ $time);
        final Object $period = this.getPeriod();
        result = result * 59 + (($period == null) ? 43 : $period.hashCode());
        result = result * 59 + this.getCount();
        final Object $volume = this.getVolume();
        result = result * 59 + (($volume == null) ? 43 : $volume.hashCode());
        final Object $turnover = this.getTurnover();
        result = result * 59 + (($turnover == null) ? 43 : $turnover.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "KLine(symbol=" + this.getSymbol() + ", openPrice=" + this.getOpenPrice() + ", highestPrice=" + this.getHighestPrice() + ", lowestPrice=" + this.getLowestPrice() + ", closePrice=" + this.getClosePrice() + ", time=" + this.getTime() + ", period=" + this.getPeriod() + ", count=" + this.getCount() + ", volume=" + this.getVolume() + ", turnover=" + this.getTurnover() + ")";
    }
}
