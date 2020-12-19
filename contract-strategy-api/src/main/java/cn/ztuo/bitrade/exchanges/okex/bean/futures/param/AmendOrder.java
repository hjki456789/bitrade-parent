package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class AmendOrder {
    private String cancel_on_fail;
    private String order_id;
    private String client_oid;
    private String request_id;
    private String new_size;
    private String new_price;

    public String getCancel_on_fail() {
        return this.cancel_on_fail;
    }

    public void setCancel_on_fail(final String cancel_on_fail) {
        this.cancel_on_fail = cancel_on_fail;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(final String request_id) {
        this.request_id = request_id;
    }

    public String getNew_size() {
        return this.new_size;
    }

    public void setNew_size(final String new_size) {
        this.new_size = new_size;
    }

    public String getNew_price() {
        return this.new_price;
    }

    public void setNew_price(final String new_price) {
        this.new_price = new_price;
    }
}
