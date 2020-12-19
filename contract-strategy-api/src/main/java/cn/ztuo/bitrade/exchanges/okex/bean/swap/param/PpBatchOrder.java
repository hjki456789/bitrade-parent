package cn.ztuo.bitrade.exchanges.okex.bean.swap.param;

public class PpBatchOrder {
    private String client_oid;
    private String size;
    private String type;
    private String match_price;
    private String price;
    private String order_type;

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    public PpBatchOrder() {
    }

    public PpBatchOrder(final String client_oid, final String size, final String type, final String match_price, final String price, final String order_type) {
        this.client_oid = client_oid;
        this.size = size;
        this.type = type;
        this.match_price = match_price;
        this.price = price;
        this.order_type = order_type;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getMatch_price() {
        return this.match_price;
    }

    public void setMatch_price(final String match_price) {
        this.match_price = match_price;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }
}
