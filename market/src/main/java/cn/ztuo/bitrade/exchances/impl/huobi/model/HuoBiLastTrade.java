package cn.ztuo.bitrade.exchances.impl.huobi.model;

import java.math.*;

public class HuoBiLastTrade
{
    private BigDecimal price;
    private BigDecimal amount;
    private String direction;
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public String getDirection() {
        return this.direction;
    }
    
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setDirection(final String direction) {
        this.direction = direction;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HuoBiLastTrade)) {
            return false;
        }
        final HuoBiLastTrade other = (HuoBiLastTrade)o;
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
        final Object this$direction = this.getDirection();
        final Object other$direction = other.getDirection();
        if (this$direction == null) {
            if (other$direction == null) {
                return true;
            }
        }
        else if (this$direction.equals(other$direction)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HuoBiLastTrade;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $price = this.getPrice();
        result = result * 59 + (($price == null) ? 43 : $price.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $direction = this.getDirection();
        result = result * 59 + (($direction == null) ? 43 : $direction.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HuoBiLastTrade(price=" + this.getPrice() + ", amount=" + this.getAmount() + ", direction=" + this.getDirection() + ")";
    }
}
