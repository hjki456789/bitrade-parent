package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class OrderResult {
    private String client_oid;
    private String order_id;
    private boolean result;
    private String error_code;
    private String error_message;

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(final boolean result) {
        this.result = result;
    }

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(final String error_code) {
        this.error_code = error_code;
    }

    public String getError_messsage() {
        return this.error_message;
    }

    public void setError_messsage(final String error_messsage) {
        this.error_message = error_messsage;
    }
}
