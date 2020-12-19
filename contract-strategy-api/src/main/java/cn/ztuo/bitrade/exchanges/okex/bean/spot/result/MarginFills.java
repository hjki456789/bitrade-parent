package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class MarginFills {
    private String ledger_id;
    private String instrument_id;
    private String price;
    private String size;
    private Long order_id;
    private String timestamp;
    private String exec_type;
    private String fee;
    private String side;
    private String currency;

    public String getLedger_id() {
        return this.ledger_id;
    }

    public void setLedger_id(final String ledger_id) {
        this.ledger_id = ledger_id;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public Long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final Long order_id) {
        this.order_id = order_id;
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

    public String getFee() {
        return this.fee;
    }

    public void setFee(final String fee) {
        this.fee = fee;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }
}
