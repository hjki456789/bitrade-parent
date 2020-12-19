package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class ClosePosition {
    private String instrument_id;
    private Integer type;

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }
}
