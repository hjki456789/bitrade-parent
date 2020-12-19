package cn.ztuo.bitrade.exchanges.okex.bean.account.result;

import java.math.*;

public class WithdrawFee {
    private BigDecimal min_fee;
    private BigDecimal max_fee;
    private String currency;

    public BigDecimal getMin_fee() {
        return this.min_fee;
    }

    public void setMin_fee(final BigDecimal min_fee) {
        this.min_fee = min_fee;
    }

    public BigDecimal getMax_fee() {
        return this.max_fee;
    }

    public void setMax_fee(final BigDecimal max_fee) {
        this.max_fee = max_fee;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "WithdrawFee{min_fee=" + this.min_fee + ", max_fee=" + this.max_fee + ", currency='" + this.currency + '\'' + '}';
    }
}
