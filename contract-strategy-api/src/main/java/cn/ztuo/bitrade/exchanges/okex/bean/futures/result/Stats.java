package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Stats {
    private String product_id;
    private Double open;
    private Double high;
    private Double low;
    private Double volume;

    public String getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(final String product_id) {
        this.product_id = product_id;
    }

    public Double getOpen() {
        return this.open;
    }

    public void setOpen(final Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return this.high;
    }

    public void setHigh(final Double high) {
        this.high = high;
    }

    public Double getLow() {
        return this.low;
    }

    public void setLow(final Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return this.volume;
    }

    public void setVolume(final Double volume) {
        this.volume = volume;
    }
}
