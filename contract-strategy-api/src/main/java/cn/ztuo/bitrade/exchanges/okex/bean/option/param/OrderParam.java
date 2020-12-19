package cn.ztuo.bitrade.exchanges.okex.bean.option.param;

public class OrderParam {
    private String client_oid;
    private String instrument_id;
    private String side;
    private String order_type;
    private String price;
    private String size;
    private String match_price;

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
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

    public String getMatch_price() {
        return this.match_price;
    }

    public void setMatch_price(final String match_price) {
        this.match_price = match_price;
    }

    @Override
    public String toString() {
        return "OrderParam{client_oid='" + this.client_oid + '\'' + ", instrument_id='" + this.instrument_id + '\'' + ", side='" + this.side + '\'' + ", order_type='" + this.order_type + '\'' + ", price='" + this.price + '\'' + ", size='" + this.size + '\'' + ", match_price='" + this.match_price + '\'' + '}';
    }
}
