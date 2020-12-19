package cn.ztuo.bitrade.exchanges.okex.bean.ett.param;

import java.math.*;

public class EttCreateOrderParam {
    private String ett;
    private Integer type;
    private BigDecimal amount;
    private BigDecimal size;
    private String clientOid;

    public String getEtt() {
        return this.ett;
    }

    public void setEtt(final String ett) {
        this.ett = ett;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(final BigDecimal size) {
        this.size = size;
    }

    public String getClientOid() {
        return this.clientOid;
    }

    public void setClientOid(final String clientOid) {
        this.clientOid = clientOid;
    }
}
