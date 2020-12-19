package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.math.*;
import java.util.*;

public class EttConstituents {
    private String currency;
    private BigDecimal amount;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttConstituents that = (EttConstituents) o;
        return Objects.equals(this.currency, that.currency) && Objects.equals(this.amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.currency, this.amount);
    }

    @Override
    public String toString() {
        return "EttConstituents{currency='" + this.currency + '\'' + ", amount=" + this.amount + '}';
    }
}
