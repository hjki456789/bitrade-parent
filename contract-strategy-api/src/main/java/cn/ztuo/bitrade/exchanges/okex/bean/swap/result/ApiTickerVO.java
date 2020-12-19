package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiTickerVO {
    private String instrument_id;
    private String last;
    private String high_24h;
    private String low_24h;
    private String volume_24h;
    private String timestamp;

    public ApiTickerVO(final String instrument_id, final String last, final String high_24h, final String low_24h, final String volume_24h, final String timestamp) {
        this.instrument_id = instrument_id;
        this.last = last;
        this.high_24h = high_24h;
        this.low_24h = low_24h;
        this.volume_24h = volume_24h;
        this.timestamp = timestamp;
    }

    public ApiTickerVO() {
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(final String last) {
        this.last = last;
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
