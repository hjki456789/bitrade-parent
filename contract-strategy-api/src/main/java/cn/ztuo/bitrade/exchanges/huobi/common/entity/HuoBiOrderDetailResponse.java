package cn.ztuo.bitrade.exchanges.huobi.common.entity;

import java.math.*;

public class HuoBiOrderDetailResponse {
    private Long order_id;
    private String order_id_str;
    private int status;
    private BigDecimal margin_frozen;
    private BigDecimal profit;

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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public BigDecimal getMargin_frozen() {
        return this.margin_frozen;
    }

    public void setMargin_frozen(final BigDecimal margin_frozen) {
        this.margin_frozen = margin_frozen;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    public void setProfit(final BigDecimal profit) {
        this.profit = profit;
    }
}
