package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.util.*;

public class EttAccount {
    private String currency;
    private String balance;
    private String holds;
    private String available;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public String getHolds() {
        return this.holds;
    }

    public void setHolds(final String holds) {
        this.holds = holds;
    }

    public String getAvailable() {
        return this.available;
    }

    public void setAvailable(final String available) {
        this.available = available;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttAccount that = (EttAccount) o;
        return Objects.equals(this.currency, that.currency) && Objects.equals(this.balance, that.balance) && Objects.equals(this.holds, that.holds) && Objects.equals(this.available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.currency, this.balance, this.holds, this.available);
    }

    @Override
    public String toString() {
        return "EttAccount{currency='" + this.currency + '\'' + ", balance='" + this.balance + '\'' + ", holds='" + this.holds + '\'' + ", available='" + this.available + '\'' + '}';
    }
}
