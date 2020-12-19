package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiOrderVO {
    private String order_id;
    private String client_oid;
    private String error_code;
    private String error_message;
    private String result;

    public ApiOrderVO() {
    }

    public ApiOrderVO(final String order_id, final String client_oid, final String error_code, final String error_message, final String result) {
        this.order_id = order_id;
        this.client_oid = client_oid;
        this.error_code = error_code;
        this.error_message = error_message;
        this.result = result;
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

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(final String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(final String error_message) {
        this.error_message = error_message;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
    }
}
