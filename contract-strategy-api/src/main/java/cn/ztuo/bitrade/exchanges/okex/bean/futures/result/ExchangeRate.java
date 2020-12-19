package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class ExchangeRate {
    private String instrument_id;
    private Double rate;
    private String timestamp;

    public Double getRate() {
        return this.rate;
    }

    public void setRate(final Double rate) {
        this.rate = rate;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
