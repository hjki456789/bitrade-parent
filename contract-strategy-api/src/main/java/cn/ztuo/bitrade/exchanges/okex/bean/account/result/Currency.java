package cn.ztuo.bitrade.exchanges.okex.bean.account.result;

import java.math.*;

public class Currency {
    private String currency;
    private String name;
    private Integer can_withdraw;
    private Integer can_deposit;
    private BigDecimal min_withdrawal;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getCan_withdraw() {
        return this.can_withdraw;
    }

    public void setCan_withdraw(final Integer can_withdraw) {
        this.can_withdraw = can_withdraw;
    }

    public Integer getCan_deposit() {
        return this.can_deposit;
    }

    public void setCan_deposit(final Integer can_deposit) {
        this.can_deposit = can_deposit;
    }

    public BigDecimal getMin_withdrawal() {
        return this.min_withdrawal;
    }

    public void setMin_withdrawal(final BigDecimal min_withdrawal) {
        this.min_withdrawal = min_withdrawal;
    }
}
