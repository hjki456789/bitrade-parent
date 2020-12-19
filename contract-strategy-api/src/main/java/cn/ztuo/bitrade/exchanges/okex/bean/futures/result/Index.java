package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Index {
    private String instrument_id;
    private String index;
    private String timestamp;

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(final String index) {
        this.index = index;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
