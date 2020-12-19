package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.math.*;
import java.util.*;

public class EttOrder {
    private String order_id;
    private String created_at;
    private Integer type;
    private String ett;
    private String quote_currency;
    private BigDecimal amount;
    private BigDecimal size;
    private BigDecimal price;
    private String status;

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public String getEtt() {
        return this.ett;
    }

    public void setEtt(final String ett) {
        this.ett = ett;
    }

    public String getQuote_currency() {
        return this.quote_currency;
    }

    public void setQuote_currency(final String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(final BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttOrder ettOrder = (EttOrder) o;
        return Objects.equals(this.order_id, ettOrder.order_id) && Objects.equals(this.created_at, ettOrder.created_at) && Objects.equals(this.type, ettOrder.type) && Objects.equals(this.ett, ettOrder.ett) && Objects.equals(this.quote_currency, ettOrder.quote_currency) && Objects.equals(this.amount, ettOrder.amount) && Objects.equals(this.size, ettOrder.size) && Objects.equals(this.price, ettOrder.price) && Objects.equals(this.status, ettOrder.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.order_id, this.created_at, this.type, this.ett, this.quote_currency, this.amount, this.size, this.price, this.status);
    }

    @Override
    public String toString() {
        return "EttOrder{order_id='" + this.order_id + '\'' + ", created_at=" + this.created_at + ", type=" + this.type + ", ett='" + this.ett + '\'' + ", quote_currency='" + this.quote_currency + '\'' + ", amount=" + this.amount + ", size=" + this.size + ", price=" + this.price + ", status='" + this.status + '\'' + '}';
    }
}
