package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class ClosePositions {
    private String instrument_id;
    private String direction;

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }
}
