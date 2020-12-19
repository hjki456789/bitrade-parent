package cn.ztuo.bitrade.exchanges.okex.bean.account.param;

public class Withdraw {
    private String amount;
    private String currency;
    private String destination;
    private String to_address;
    private String trade_pwd;
    private String fee;
    private String tag;

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(final String fee) {
        this.fee = fee;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getTrade_pwd() {
        return this.trade_pwd;
    }

    public void setTrade_pwd(final String trade_pwd) {
        this.trade_pwd = trade_pwd;
    }

    public String getTo_address() {
        return this.to_address;
    }

    public void setTo_address(final String to_address) {
        this.to_address = to_address;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }
}
