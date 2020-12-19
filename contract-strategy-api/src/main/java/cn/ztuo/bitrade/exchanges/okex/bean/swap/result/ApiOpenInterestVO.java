package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiOpenInterestVO {
    private String instrument_id;
    private String amount;
    private String timestamp;

    public ApiOpenInterestVO() {
    }

    public ApiOpenInterestVO(final String instrument_id, final String amount, final String timestamp) {
        this.instrument_id = instrument_id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}