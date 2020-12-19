package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

import java.util.*;

public class CancelFuturesOrder {
    private String instrument_id;
    private List<String> algo_ids;
    private String order_type;
    private String error_code;
    private String error_message;

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(final String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(final String error_message) {
        this.error_message = error_message;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public List<String> getAlgo_ids() {
        return this.algo_ids;
    }

    public void setAlgo_ids(final List<String> algo_ids) {
        this.algo_ids = algo_ids;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    @Override
    public String toString() {
        return "CancelFuturesOrder{instrument_id='" + this.instrument_id + '\'' + ", algo_ids=" + this.algo_ids + ", order_type='" + this.order_type + '\'' + ", error_code='" + this.error_code + '\'' + ", error_message='" + this.error_message + '\'' + '}';
    }
}
