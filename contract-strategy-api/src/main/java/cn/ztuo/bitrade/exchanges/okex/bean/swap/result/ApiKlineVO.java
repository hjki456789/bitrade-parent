package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiKlineVO {
    private String timestamp;
    private String low;
    private String high;
    private String open;
    private String close;
    private String volume;
    private String currency_volume;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLow() {
        return this.low;
    }

    public void setLow(final String low) {
        this.low = low;
    }

    public String getHigh() {
        return this.high;
    }

    public void setHigh(final String high) {
        this.high = high;
    }

    public String getOpen() {
        return this.open;
    }

    public void setOpen(final String open) {
        this.open = open;
    }

    public String getClose() {
        return this.close;
    }

    public void setClose(final String close) {
        this.close = close;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(final String volume) {
        this.volume = volume;
    }

    public String getCurrency_volume() {
        return this.currency_volume;
    }

    public void setCurrency_volume(final String currency_volume) {
        this.currency_volume = currency_volume;
    }

    public ApiKlineVO() {
    }

    public ApiKlineVO(final String timestamp, final String low, final String high, final String open, final String close, final String volume, final String currency_volume) {
        this.timestamp = timestamp;
        this.low = low;
        this.high = high;
        this.open = open;
        this.close = close;
        this.volume = volume;
        this.currency_volume = currency_volume;
    }
}
