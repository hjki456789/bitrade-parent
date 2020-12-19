package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiRateVO {
    private String instrument_id;
    private String rate;
    private String timestamp;

    public ApiRateVO() {
    }

    public ApiRateVO(final String instrument_id, final String rate, final String timestamp) {
        this.instrument_id = instrument_id;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(final String rate) {
        this.rate = rate;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
