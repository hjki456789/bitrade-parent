package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class PriceLimit {
    private String instrument_id;
    private String highest;
    private String lowest;
    private String timestamp;

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
}
