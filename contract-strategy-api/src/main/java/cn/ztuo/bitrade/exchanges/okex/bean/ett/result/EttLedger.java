package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.math.*;
import java.util.*;

public class EttLedger {
    private Long ledger_id;
    private String currency;
    private BigDecimal balance;
    private BigDecimal amount;
    private String type;
    private String created_at;
    private Long details;

    public Long getLedger_id() {
        return this.ledger_id;
    }

    public void setLedger_id(final Long ledger_id) {
        this.ledger_id = ledger_id;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }

    public Long getDetails() {
        return this.details;
    }

    public void setDetails(final Long details) {
        this.details = details;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttLedger ettLedger = (EttLedger) o;
        return Objects.equals(this.ledger_id, ettLedger.ledger_id) && Objects.equals(this.currency, ettLedger.currency) && Objects.equals(this.balance, ettLedger.balance) && Objects.equals(this.amount, ettLedger.amount) && Objects.equals(this.type, ettLedger.type) && Objects.equals(this.created_at, ettLedger.created_at) && Objects.equals(this.details, ettLedger.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ledger_id, this.currency, this.balance, this.amount, this.type, this.created_at, this.details);
    }

    @Override
    public String toString() {
        return "EttLedger{ledger_id='" + this.ledger_id + '\'' + ", currency='" + this.currency + '\'' + ", balance=" + this.balance + ", amount=" + this.amount + ", type='" + this.type + '\'' + ", created_at=" + this.created_at + ", details='" + this.details + '\'' + '}';
    }
}
