package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class UserMarginBillDto {
    private Long ledger_id;
    private String timestamp;
    private String amount;
    private String balance;
    private String type;
    private String currency;
    private UserMarginBillDto.Details details;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getLedger_id() {
        return this.ledger_id;
    }

    public void setLedger_id(final Long ledger_id) {
        this.ledger_id = ledger_id;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public UserMarginBillDto.Details getDetails() {
        return this.details;
    }

    public void setDetails(final UserMarginBillDto.Details details) {
        this.details = details;
    }
}
