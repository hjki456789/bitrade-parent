package cn.ztuo.bitrade.exchanges.okex.bean.account.param;

public class Transfer {
    private String currency;
    private String amount;
    private String from;
    private String to;
    private String sub_account;
    private String instrument_id;
    private String to_instrument_id;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getTo_instrument_id() {
        return this.to_instrument_id;
    }

    public void setTo_instrument_id(final String to_instrument_id) {
        this.to_instrument_id = to_instrument_id;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getSub_account() {
        return this.sub_account;
    }

    public void setSub_account(final String sub_account) {
        this.sub_account = sub_account;
    }
}
