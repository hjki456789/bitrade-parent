package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class OrderInfo {
    private String order_id;
    private String client_oid;
    private String price;
    private String notional;
    private String size;
    private String price_avg;
    private String order_type;
    private String timestamp;
    private String filled_size;
    private String state;
    private String side;
    private String type;
    private String instrument_id;
    private String filled_notional;
    private String fee_currency;
    private String fee;
    private String rebate_currency;
    private String rebate;

    public String getFee_currency() {
        return this.fee_currency;
    }

    public void setFee_currency(final String fee_currency) {
        this.fee_currency = fee_currency;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(final String fee) {
        this.fee = fee;
    }

    public String getRebate_currency() {
        return this.rebate_currency;
    }

    public void setRebate_currency(final String rebate_currency) {
        this.rebate_currency = rebate_currency;
    }

    public String getRebate() {
        return this.rebate;
    }

    public void setRebate(final String rebate) {
        this.rebate = rebate;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getNotional() {
        return this.notional;
    }

    public void setNotional(final String notional) {
        this.notional = notional;
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

    public String getFilled_size() {
        return this.filled_size;
    }

    public void setFilled_size(final String filled_size) {
        this.filled_size = filled_size;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getFilled_notional() {
        return this.filled_notional;
    }

    public void setFilled_notional(final String filled_notional) {
        this.filled_notional = filled_notional;
    }

    public String getPrice_avg() {
        return this.price_avg;
    }

    public void setPrice_avg(final String price_avg) {
        this.price_avg = price_avg;
    }
}
