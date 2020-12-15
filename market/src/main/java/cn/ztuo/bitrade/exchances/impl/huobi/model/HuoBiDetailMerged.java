package cn.ztuo.bitrade.exchances.impl.huobi.model;

import java.math.*;
import java.util.*;

public class HuoBiDetailMerged
{
    private Long id;
    private BigDecimal amount;
    private Long count;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal vol;
    private List<BigDecimal> bid;
    private List<BigDecimal> ask;
    
    public Long getId() {
        return this.id;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public Long getCount() {
        return this.count;
    }
    
    public BigDecimal getOpen() {
        return this.open;
    }
    
    public BigDecimal getClose() {
        return this.close;
    }
    
    public BigDecimal getLow() {
        return this.low;
    }
    
    public BigDecimal getHigh() {
        return this.high;
    }
    
    public BigDecimal getVol() {
        return this.vol;
    }
    
    public List<BigDecimal> getBid() {
        return this.bid;
    }
    
    public List<BigDecimal> getAsk() {
        return this.ask;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setCount(final Long count) {
        this.count = count;
    }
    
    public void setOpen(final BigDecimal open) {
        this.open = open;
    }
    
    public void setClose(final BigDecimal close) {
        this.close = close;
    }
    
    public void setLow(final BigDecimal low) {
        this.low = low;
    }
    
    public void setHigh(final BigDecimal high) {
        this.high = high;
    }
    
    public void setVol(final BigDecimal vol) {
        this.vol = vol;
    }
    
    public void setBid(final List<BigDecimal> bid) {
        this.bid = bid;
    }
    
    public void setAsk(final List<BigDecimal> ask) {
        this.ask = ask;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HuoBiDetailMerged)) {
            return false;
        }
        final HuoBiDetailMerged other = (HuoBiDetailMerged)o;
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
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0102: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0102;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$count = this.getCount();
        final Object other$count = other.getCount();
        Label_0139: {
            if (this$count == null) {
                if (other$count == null) {
                    break Label_0139;
                }
            }
            else if (this$count.equals(other$count)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$open = this.getOpen();
        final Object other$open = other.getOpen();
        Label_0176: {
            if (this$open == null) {
                if (other$open == null) {
                    break Label_0176;
                }
            }
            else if (this$open.equals(other$open)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$close = this.getClose();
        final Object other$close = other.getClose();
        Label_0213: {
            if (this$close == null) {
                if (other$close == null) {
                    break Label_0213;
                }
            }
            else if (this$close.equals(other$close)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$low = this.getLow();
        final Object other$low = other.getLow();
        Label_0250: {
            if (this$low == null) {
                if (other$low == null) {
                    break Label_0250;
                }
            }
            else if (this$low.equals(other$low)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$high = this.getHigh();
        final Object other$high = other.getHigh();
        Label_0287: {
            if (this$high == null) {
                if (other$high == null) {
                    break Label_0287;
                }
            }
            else if (this$high.equals(other$high)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$vol = this.getVol();
        final Object other$vol = other.getVol();
        Label_0324: {
            if (this$vol == null) {
                if (other$vol == null) {
                    break Label_0324;
                }
            }
            else if (this$vol.equals(other$vol)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$bid = this.getBid();
        final Object other$bid = other.getBid();
        Label_0361: {
            if (this$bid == null) {
                if (other$bid == null) {
                    break Label_0361;
                }
            }
            else if (this$bid.equals(other$bid)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$ask = this.getAsk();
        final Object other$ask = other.getAsk();
        if (this$ask == null) {
            if (other$ask == null) {
                return true;
            }
        }
        else if (this$ask.equals(other$ask)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HuoBiDetailMerged;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $count = this.getCount();
        result = result * 59 + (($count == null) ? 43 : $count.hashCode());
        final Object $open = this.getOpen();
        result = result * 59 + (($open == null) ? 43 : $open.hashCode());
        final Object $close = this.getClose();
        result = result * 59 + (($close == null) ? 43 : $close.hashCode());
        final Object $low = this.getLow();
        result = result * 59 + (($low == null) ? 43 : $low.hashCode());
        final Object $high = this.getHigh();
        result = result * 59 + (($high == null) ? 43 : $high.hashCode());
        final Object $vol = this.getVol();
        result = result * 59 + (($vol == null) ? 43 : $vol.hashCode());
        final Object $bid = this.getBid();
        result = result * 59 + (($bid == null) ? 43 : $bid.hashCode());
        final Object $ask = this.getAsk();
        result = result * 59 + (($ask == null) ? 43 : $ask.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HuoBiDetailMerged(id=" + this.getId() + ", amount=" + this.getAmount() + ", count=" + this.getCount() + ", open=" + this.getOpen() + ", close=" + this.getClose() + ", low=" + this.getLow() + ", high=" + this.getHigh() + ", vol=" + this.getVol() + ", bid=" + this.getBid() + ", ask=" + this.getAsk() + ")";
    }
}
