package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class FindFuturesOrderResult {
    private String instrument_id;
    private String order_type;
    private String timestamp;
    private String rejected_at;
    private String algo_id;
    private String status;
    private String type;
    private String leverage;
    private String size;
    private String trigger_price;
    private String algo_price;
    private String real_amount;
    private String callback_rate;
    private String algo_variance;
    private String avg_amount;
    private String price_limit;
    private String deal_value;
    private String sweep_range;
    private String sweep_ratio;
    private String single_limit;
    private String time_interval;

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

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRejected_at() {
        return this.rejected_at;
    }

    public void setRejected_at(final String rejected_at) {
        this.rejected_at = rejected_at;
    }

    public String getAlgo_id() {
        return this.algo_id;
    }

    public void setAlgo_id(final String algo_id) {
        this.algo_id = algo_id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getLeverage() {
        return this.leverage;
    }

    public void setLeverage(final String leverage) {
        this.leverage = leverage;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getTrigger_price() {
        return this.trigger_price;
    }

    public void setTrigger_price(final String trigger_price) {
        this.trigger_price = trigger_price;
    }

    public String getAlgo_price() {
        return this.algo_price;
    }

    public void setAlgo_price(final String algo_price) {
        this.algo_price = algo_price;
    }

    public String getReal_amount() {
        return this.real_amount;
    }

    public void setReal_amount(final String real_amount) {
        this.real_amount = real_amount;
    }

    public String getCallback_rate() {
        return this.callback_rate;
    }

    public void setCallback_rate(final String callback_rate) {
        this.callback_rate = callback_rate;
    }

    public String getAlgo_variance() {
        return this.algo_variance;
    }

    public void setAlgo_variance(final String algo_variance) {
        this.algo_variance = algo_variance;
    }

    public String getAvg_amount() {
        return this.avg_amount;
    }

    public void setAvg_amount(final String avg_amount) {
        this.avg_amount = avg_amount;
    }

    public String getPrice_limit() {
        return this.price_limit;
    }

    public void setPrice_limit(final String price_limit) {
        this.price_limit = price_limit;
    }

    public String getDeal_value() {
        return this.deal_value;
    }

    public void setDeal_value(final String deal_value) {
        this.deal_value = deal_value;
    }

    public String getSweep_range() {
        return this.sweep_range;
    }

    public void setSweep_range(final String sweep_range) {
        this.sweep_range = sweep_range;
    }

    public String getSweep_ratio() {
        return this.sweep_ratio;
    }

    public void setSweep_ratio(final String sweep_ratio) {
        this.sweep_ratio = sweep_ratio;
    }

    public String getSingle_limit() {
        return this.single_limit;
    }

    public void setSingle_limit(final String single_limit) {
        this.single_limit = single_limit;
    }

    public String getTime_interval() {
        return this.time_interval;
    }

    public void setTime_interval(final String time_interval) {
        this.time_interval = time_interval;
    }
}
