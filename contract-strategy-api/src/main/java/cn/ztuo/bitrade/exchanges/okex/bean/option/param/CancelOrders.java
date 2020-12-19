package cn.ztuo.bitrade.exchanges.okex.bean.option.param;

import java.util.*;

public class CancelOrders {
    private List<String> order_ids;
    private List<String> client_oids;
    private String underlying;

    public String getUnderlying() {
        return this.underlying;
    }

    public void setUnderlying(final String underlying) {
        this.underlying = underlying;
    }

    public List<String> getOrder_ids() {
        return this.order_ids;
    }

    public void setOrder_ids(final List<String> order_ids) {
        this.order_ids = order_ids;
    }

    public List<String> getClient_oids() {
        return this.client_oids;
    }

    public void setClient_oids(final List<String> client_oids) {
        this.client_oids = client_oids;
    }
}
