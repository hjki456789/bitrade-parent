package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

import java.util.*;

public class Orders {
    protected String instrument_id;
    List<OrdersItem> orders_data;

    public List<OrdersItem> getOrders_data() {
        return this.orders_data;
    }

    public void setOrders_data(final List<OrdersItem> orders_data) {
        this.orders_data = orders_data;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }
}
