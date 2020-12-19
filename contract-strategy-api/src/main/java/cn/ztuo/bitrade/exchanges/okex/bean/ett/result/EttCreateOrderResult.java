package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.util.*;

public class EttCreateOrderResult {
    private String order_id;
    private String client_oid;
    private Boolean result;

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(final Boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EttCreateOrderResult that = (EttCreateOrderResult) o;
        return Objects.equals(this.order_id, that.order_id) && Objects.equals(this.client_oid, that.client_oid) && Objects.equals(this.result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.order_id, this.client_oid, this.result);
    }

    @Override
    public String toString() {
        return "EttCreateOrderResult{order_id='" + this.order_id + '\'' + ", client_oid='" + this.client_oid + '\'' + ", result=" + this.result + '}';
    }
}
