package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class FindOrderParam {
    private String instrument_id;
    private String order_type;
    private String status;
    private String algo_ids;
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

    public String getAlgo_ids() {
        return this.algo_ids;
    }

    public void setAlgo_ids(final String algo_ids) {
        this.algo_ids = algo_ids;
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
