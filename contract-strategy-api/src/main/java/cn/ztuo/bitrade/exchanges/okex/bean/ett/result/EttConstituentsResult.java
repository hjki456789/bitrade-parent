package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.math.*;
import java.util.*;

public class EttConstituentsResult {
    private String ett;
    private BigDecimal net_value;
    private List<EttConstituents> constituents;

    public String getEtt() {
        return this.ett;
    }

    public void setEtt(final String ett) {
        this.ett = ett;
    }

    public BigDecimal getNet_value() {
        return this.net_value;
    }

    public void setNet_value(final BigDecimal net_value) {
        this.net_value = net_value;
    }

    public List<EttConstituents> getConstituents() {
        return this.constituents;
    }

    public void setConstituents(final List<EttConstituents> constituents) {
        this.constituents = constituents;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttConstituentsResult that = (EttConstituentsResult) o;
        return Objects.equals(this.ett, that.ett) && Objects.equals(this.net_value, that.net_value) && Objects.equals(this.constituents, that.constituents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ett, this.net_value, this.constituents);
    }

    @Override
    public String toString() {
        return "EttConstituentsResult{ett='" + this.ett + '\'' + ", net_value=" + this.net_value + ", constituents=" + this.constituents + '}';
    }
}
