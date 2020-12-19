package cn.ztuo.bitrade.exchanges.okex.bean.swap.param;

import java.util.*;

public class CancelOrderAlgo {
    private String instrument_id;
    private List<String> algo_ids;
    private String order_type;

    public List<String> getAlgo_ids() {
        return this.algo_ids;
    }

    public void setAlgo_ids(final List<String> algo_ids) {
        this.algo_ids = algo_ids;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }
}
