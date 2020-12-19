package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiDealVO {
    private String trade_id;
    private String price;
    private String amount;
    private String side;
    private String timestamp;

    public ApiDealVO(final String trade_id, final String price, final String amount, final String side, final String timestamp) {
        this.trade_id = trade_id;
        this.price = price;
        this.amount = amount;
        this.side = side;
        this.timestamp = timestamp;
    }

    public ApiDealVO() {
    }

    public String getTrade_id() {
        return this.trade_id;
    }

    public void setTrade_id(final String trade_id) {
        this.trade_id = trade_id;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
