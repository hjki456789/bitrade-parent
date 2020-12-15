package cn.ztuo.bitrade.exchances.impl.huobi.model;

import java.util.*;
import java.math.*;

public class HuoBiDepth
{
    private List<List<BigDecimal>> bids;
    private List<List<BigDecimal>> asks;
    
    public List<List<BigDecimal>> getBids() {
        return this.bids;
    }
    
    public List<List<BigDecimal>> getAsks() {
        return this.asks;
    }
    
    public void setBids(final List<List<BigDecimal>> bids) {
        this.bids = bids;
    }
    
    public void setAsks(final List<List<BigDecimal>> asks) {
        this.asks = asks;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HuoBiDepth)) {
            return false;
        }
        final HuoBiDepth other = (HuoBiDepth)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$bids = this.getBids();
        final Object other$bids = other.getBids();
        Label_0065: {
            if (this$bids == null) {
                if (other$bids == null) {
                    break Label_0065;
                }
            }
            else if (this$bids.equals(other$bids)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$asks = this.getAsks();
        final Object other$asks = other.getAsks();
        if (this$asks == null) {
            if (other$asks == null) {
                return true;
            }
        }
        else if (this$asks.equals(other$asks)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HuoBiDepth;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bids = this.getBids();
        result = result * 59 + (($bids == null) ? 43 : $bids.hashCode());
        final Object $asks = this.getAsks();
        result = result * 59 + (($asks == null) ? 43 : $asks.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HuoBiDepth(bids=" + this.getBids() + ", asks=" + this.getAsks() + ")";
    }
}
