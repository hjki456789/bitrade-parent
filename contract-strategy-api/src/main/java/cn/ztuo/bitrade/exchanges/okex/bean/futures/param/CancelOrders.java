package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

import java.util.*;

public class CancelOrders {
    List<String> order_ids;
    List<String> client_oids;
    private String instrument_id;

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

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }
}
