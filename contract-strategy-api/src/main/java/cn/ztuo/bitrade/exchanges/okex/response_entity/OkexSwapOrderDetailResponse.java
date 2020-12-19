package cn.ztuo.bitrade.exchanges.okex.response_entity;

public class OkexSwapOrderDetailResponse {
    private String instrument_id;
    private String client_oid;
    private String size;
    private String timestamp;
    private String filled_qty;
    private String fee;
    private String order_id;
    private String price;
    private String price_avg;
    private String type;
    private String contract_val;
    private String order_type;
    private String state;
    private String trigger_price;
    private String leverage;

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFilled_qty() {
        return this.filled_qty;
    }

    public void setFilled_qty(final String filled_qty) {
        this.filled_qty = filled_qty;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(final String fee) {
        this.fee = fee;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getPrice_avg() {
        return this.price_avg;
    }

    public void setPrice_avg(final String price_avg) {
        this.price_avg = price_avg;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getContract_val() {
        return this.contract_val;
    }

    public void setContract_val(final String contract_val) {
        this.contract_val = contract_val;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    public String getState() {
        return this.state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getTrigger_price() {
        return this.trigger_price;
    }

    public void setTrigger_price(final String trigger_price) {
        this.trigger_price = trigger_price;
    }

    public String getLeverage() {
        return this.leverage;
    }

    public void setLeverage(final String leverage) {
        this.leverage = leverage;
    }
}
