package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiPriceLimitVO {
    private String instrument_id;
    private String highest;
    private String lowest;
    private String timestamp;

    public ApiPriceLimitVO() {
    }

    public ApiPriceLimitVO(final String instrument_id, final String highest, final String lowest, final String timestamp) {
        this.instrument_id = instrument_id;
        this.highest = highest;
        this.lowest = lowest;
        this.timestamp = timestamp;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getHighest() {
        return this.highest;
    }

    public void setHighest(final String highest) {
        this.highest = highest;
    }

    public String getLowest() {
        return this.lowest;
    }

    public void setLowest(final String lowest) {
        this.lowest = lowest;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
