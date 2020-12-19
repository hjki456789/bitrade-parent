package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.math.*;
import java.util.*;

public class EttSettlementDefinePrice {
    private String date;
    private BigDecimal price;

    public String getDate() {
        return this.date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttSettlementDefinePrice that = (EttSettlementDefinePrice) o;
        return Objects.equals(this.date, that.date) && Objects.equals(this.price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.date, this.price);
    }

    @Override
    public String toString() {
        return "EttSettlementDefinePrice{date=" + this.date + ", price=" + this.price + '}';
    }
}
