package cn.ztuo.bitrade.exchances.entity;

import java.math.*;

public class Depth
{
    private BigDecimal price;
    private BigDecimal amount;
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Depth)) {
            return false;
        }
        final Depth other = (Depth)o;
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
        if (this$amount == null) {
            if (other$amount == null) {
                return true;
            }
        }
        else if (this$amount.equals(other$amount)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Depth;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $price = this.getPrice();
        result = result * 59 + (($price == null) ? 43 : $price.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Depth(price=" + this.getPrice() + ", amount=" + this.getAmount() + ")";
    }
}
