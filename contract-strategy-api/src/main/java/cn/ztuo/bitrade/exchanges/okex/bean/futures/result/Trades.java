package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Trades {
    private String trade_id;
    private String side;
    private String price;
    private String qty;
    private String timestamp;

    public String getTrade_id() {
        return this.trade_id;
    }

    public void setTrade_id(final String trade_id) {
        this.trade_id = trade_id;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(final String qty) {
        this.qty = qty;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Trades{trade_id='" + this.trade_id + '\'' + ", side='" + this.side + '\'' + ", price='" + this.price + '\'' + ", qty='" + this.qty + '\'' + ", timestamp='" + this.timestamp + '\'' + '}';
    }
}
