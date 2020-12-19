package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Ticker {
    private String instrument_id;
    private String best_bid;
    private String best_ask;
    private String high_24h;
    private String low_24h;
    private String last;
    private String volume_24h;
    private String timestamp;
    private String last_qty;
    private String best_ask_size;
    private String best_bid_size;

    public String getLast_qty() {
        return this.last_qty;
    }

    public void setLast_qty(final String last_qty) {
        this.last_qty = last_qty;
    }

    public String getBest_ask_size() {
        return this.best_ask_size;
    }

    public void setBest_ask_size(final String best_ask_size) {
        this.best_ask_size = best_ask_size;
    }

    public String getBest_bid_size() {
        return this.best_bid_size;
    }

    public void setBest_bid_size(final String best_bid_size) {
        this.best_bid_size = best_bid_size;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getBest_bid() {
        return this.best_bid;
    }

    public void setBest_bid(final String best_bid) {
        this.best_bid = best_bid;
    }

    public String getBest_ask() {
        return this.best_ask;
    }

    public void setBest_ask(final String best_ask) {
        this.best_ask = best_ask;
    }

    public String getHigh_24h() {
        return this.high_24h;
    }

    public void setHigh_24h(final String high_24h) {
        this.high_24h = high_24h;
    }

    public String getLow_24h() {
        return this.low_24h;
    }

    public void setLow_24h(final String low_24h) {
        this.low_24h = low_24h;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(final String last) {
        this.last = last;
    }

    public String getVolume_24h() {
        return this.volume_24h;
    }

    public void setVolume_24h(final String volume_24h) {
        this.volume_24h = volume_24h;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
