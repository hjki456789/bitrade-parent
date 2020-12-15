package cn.ztuo.bitrade.exchances.entity;

import java.math.*;

public class LastTrade
{
    private BigDecimal price;
    private BigDecimal amount;
    private Side side;
    
    public LastTrade() {
        this.price = BigDecimal.ZERO;
        this.amount = BigDecimal.ZERO;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public Side getSide() {
        return this.side;
    }
    
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setSide(final Side side) {
        this.side = side;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LastTrade)) {
            return false;
        }
        final LastTrade other = (LastTrade)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        Label_0065: {
            if (this$price == null) {
                if (other$price == null) {
                    break Label_0065;
                }
            }
            else if (this$price.equals(other$price)) {
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
        final Object this$side = this.getSide();
        final Object other$side = other.getSide();
        if (this$side == null) {
            if (other$side == null) {
                return true;
            }
        }
        else if (this$side.equals(other$side)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof LastTrade;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $price = this.getPrice();
        result = result * 59 + (($price == null) ? 43 : $price.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $side = this.getSide();
        result = result * 59 + (($side == null) ? 43 : $side.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "LastTrade(price=" + this.getPrice() + ", amount=" + this.getAmount() + ", side=" + this.getSide() + ")";
    }
}
