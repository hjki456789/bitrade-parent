package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiDealDetailVO {
    private Long trade_id;
    private String instrument_id;
    private String order_id;
    private String price;
    private String order_qty;
    private String fee;
    private String timestamp;
    private String exec_type;
    private String side;

    public ApiDealDetailVO() {
    }

    public Long getTrade_id() {
        return this.trade_id;
    }

    public void setTrade_id(final Long trade_id) {
        this.trade_id = trade_id;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
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

    public String getOrder_qty() {
        return this.order_qty;
    }

    public void setOrder_qty(final String order_qty) {
        this.order_qty = order_qty;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(final String fee) {
        this.fee = fee;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getExec_type() {
        return this.exec_type;
    }

    public void setExec_type(final String exec_type) {
        this.exec_type = exec_type;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public ApiDealDetailVO(final Long trade_id, final String instrument_id, final String order_id, final String price, final String order_qty, final String fee, final String timestamp, final String exec_type, final String side) {
        this.trade_id = trade_id;
        this.instrument_id = instrument_id;
        this.order_id = order_id;
        this.price = price;
        this.order_qty = order_qty;
        this.fee = fee;
        this.timestamp = timestamp;
        this.exec_type = exec_type;
        this.side = side;
    }
}
