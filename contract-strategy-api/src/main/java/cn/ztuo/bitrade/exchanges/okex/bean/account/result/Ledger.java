package cn.ztuo.bitrade.exchanges.okex.bean.account.result;

public class Ledger {
    private String ledger_id;
    private String currency;
    private String balance;
    private String amount;
    private String fee;
    private String typeName;
    private String timestamp;

    public String getLedger_id() {
        return this.ledger_id;
    }

    public void setLedger_id(final String ledger_id) {
        this.ledger_id = ledger_id;
    }

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

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(final String fee) {
        this.fee = fee;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Ledger{ledger_id='" + this.ledger_id + '\'' + ", currency='" + this.currency + '\'' + ", balance='" + this.balance + '\'' + ", amount='" + this.amount + '\'' + ", fee='" + this.fee + '\'' + ", typeName='" + this.typeName + '\'' + ", timestamp='" + this.timestamp + '\'' + '}';
    }
}
