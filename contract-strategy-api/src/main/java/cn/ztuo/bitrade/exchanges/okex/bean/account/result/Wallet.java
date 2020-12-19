package cn.ztuo.bitrade.exchanges.okex.bean.account.result;

public class Wallet {
    private String currency;
    private String balance;
    private String hold;
    private String available;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public String getHold() {
        return this.hold;
    }

    public void setHold(final String hold) {
        this.hold = hold;
    }

    public String getAvailable() {
        return this.available;
    }

    public void setAvailable(final String available) {
        this.available = available;
    }
}
