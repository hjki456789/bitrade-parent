package cn.ztuo.bitrade.exchanges.okex.bean.spot.param;

public class Order {
    private String product_id;
    private String side;
    private int type;
    private String size;
    private String price;
    private String funds;
    private String client_oid;

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(final String product_id) {
        this.product_id = product_id;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public int getType() {
        return this.type;
    }

    public void setType(final int type) {
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

    public String getFunds() {
        return this.funds;
    }

    public void setFunds(final String funds) {
        this.funds = funds;
    }
}
