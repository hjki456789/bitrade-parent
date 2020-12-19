package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class EstimatedPrice {
    private String instrument_id;
    private String settlement_price;
    private String timestamp;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getSettlement_price() {
        return this.settlement_price;
    }

    public void setSettlement_price(final String settlement_price) {
        this.settlement_price = settlement_price;
    }
}
