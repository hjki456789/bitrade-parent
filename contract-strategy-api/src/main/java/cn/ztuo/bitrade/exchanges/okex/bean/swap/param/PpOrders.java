package cn.ztuo.bitrade.exchanges.okex.bean.swap.param;

import java.util.*;

public class PpOrders {
    private String instrument_id;
    private List<PpBatchOrder> order_data;

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public void setOrder_data(final List<PpBatchOrder> order_data) {
        this.order_data = order_data;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public List<PpBatchOrder> getOrder_data() {
        return this.order_data;
    }

    public PpOrders() {
    }

    public PpOrders(final String instrumentId, final List<PpBatchOrder> orderData) {
        this.instrument_id = instrumentId;
        this.order_data = orderData;
    }
}
