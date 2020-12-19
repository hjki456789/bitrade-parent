package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiMarkPriceVO {
    private String instrument_id;
    private String mark_price;
    private String timestamp;

    public ApiMarkPriceVO() {
    }

    public ApiMarkPriceVO(final String instrument_id, final String mark_price, final String timestamp) {
        this.instrument_id = instrument_id;
        this.mark_price = mark_price;
        this.timestamp = timestamp;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getMark_price() {
        return this.mark_price;
    }

    public void setMark_price(final String mark_price) {
        this.mark_price = mark_price;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
