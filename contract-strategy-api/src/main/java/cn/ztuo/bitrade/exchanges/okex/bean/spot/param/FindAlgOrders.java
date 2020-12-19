package cn.ztuo.bitrade.exchanges.okex.bean.spot.param;

public class FindAlgOrders {
    private String instrument_id;
    private String order_type;
    private String status;
    private String algo_id;
    private String before;
    private String after;
    private String limit;

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getAlgo_id() {
        return this.algo_id;
    }

    public void setAlgo_id(final String algo_id) {
        this.algo_id = algo_id;
    }

    public String getBefore() {
        return this.before;
    }

    public void setBefore(final String before) {
        this.before = before;
    }

    public String getAfter() {
        return this.after;
    }

    public void setAfter(final String after) {
        this.after = after;
    }

    public String getLimit() {
        return this.limit;
    }

    public void setLimit(final String limit) {
        this.limit = limit;
    }
}
