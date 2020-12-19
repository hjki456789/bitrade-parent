package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

import java.util.*;

public class OrderCancelResult {
    private String instrument_id;
    private List<String> order_ids;
    private String result;

    public OrderCancelResult(final String instrument_id, final List<String> order_ids, final String result) {
        this.instrument_id = "";
        this.order_ids = new LinkedList<String>();
        this.result = "";
        this.instrument_id = instrument_id;
        this.order_ids = order_ids;
        this.result = result;
    }

    public OrderCancelResult() {
        this.instrument_id = "";
        this.order_ids = new LinkedList<String>();
        this.result = "";
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public List<String> getOrder_ids() {
        return this.order_ids;
    }

    public void setOrder_ids(final List<String> order_ids) {
        this.order_ids = order_ids;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
    }
}
