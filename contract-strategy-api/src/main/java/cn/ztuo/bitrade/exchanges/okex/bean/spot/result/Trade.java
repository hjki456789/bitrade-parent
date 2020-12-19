package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class Trade {
    private String timestamp;
    private String trade_id;
    private String price;
    private String size;
    private String side;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
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

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }
}
