package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class ModifyMarginParam {
    private String instrument_id;
    private String direction;
    private String type;
    private String amount;

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

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }
}
