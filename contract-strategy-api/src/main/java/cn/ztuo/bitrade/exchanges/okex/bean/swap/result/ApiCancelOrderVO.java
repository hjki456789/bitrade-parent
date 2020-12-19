package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiCancelOrderVO {
    private String order_id;
    private String result;

    public ApiCancelOrderVO() {
    }

    public ApiCancelOrderVO(final String order_id, final String result) {
        this.order_id = order_id;
        this.result = result;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
    }
}
