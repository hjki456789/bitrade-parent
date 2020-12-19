package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiIndexVO {
    private String instrument_id;
    private String index;
    private String timestamp;

    public ApiIndexVO() {
    }

    public ApiIndexVO(final String instrument_id, final String index, final String timestamp) {
        this.instrument_id = instrument_id;
        this.index = index;
        this.timestamp = timestamp;
    }

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
