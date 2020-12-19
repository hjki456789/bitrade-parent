package cn.ztuo.bitrade.exchanges.huobi.common.entity;

public class HuoBiOrderResponse {
    private Long order_id;
    private String order_id_str;

    public Long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final Long order_id) {
        this.order_id = order_id;
    }

    public String getOrder_id_str() {
        return this.order_id_str;
    }

    public void setOrder_id_str(final String order_id_str) {
        this.order_id_str = order_id_str;
    }
}
