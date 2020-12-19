package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class Order {
    protected String instrument_id;
    private String client_oid;
    private String type;
    private String size;
    private String match_price;
    private String order_type;
    private String price;

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
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

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setinstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }
}
