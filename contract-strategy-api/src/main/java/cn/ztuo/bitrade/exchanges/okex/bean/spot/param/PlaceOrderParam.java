package cn.ztuo.bitrade.exchanges.okex.bean.spot.param;

public class PlaceOrderParam {
    private String instrument_id;
    private String client_oid;
    private String order_id;
    private String side;
    private String type;
    private String size;
    private String price;
    private String notional;
    private String order_type;
    private String margin_trading;

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getMargin_trading() {
        return this.margin_trading;
    }

    public void setMargin_trading(final String margin_trading) {
        this.margin_trading = margin_trading;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getNotional() {
        return this.notional;
    }

    public void setNotional(final String notional) {
        this.notional = notional;
    }
}
