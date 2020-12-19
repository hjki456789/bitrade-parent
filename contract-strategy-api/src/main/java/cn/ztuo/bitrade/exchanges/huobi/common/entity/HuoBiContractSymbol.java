package cn.ztuo.bitrade.exchanges.huobi.common.entity;

import java.math.*;

public class HuoBiContractSymbol {
    private String symbol;
    private String contract_code;
    private BigDecimal contract_size;
    private BigDecimal price_tick;
    private String create_date;
    private Integer contract_status;
    private Long settlement_date;

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String getContract_code() {
        return this.contract_code;
    }

    public void setContract_code(final String contract_code) {
        this.contract_code = contract_code;
    }

    public BigDecimal getContract_size() {
        return this.contract_size;
    }

    public void setContract_size(final BigDecimal contract_size) {
        this.contract_size = contract_size;
    }

    public BigDecimal getPrice_tick() {
        return this.price_tick;
    }

    public void setPrice_tick(final BigDecimal price_tick) {
        this.price_tick = price_tick;
    }

    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(final String create_date) {
        this.create_date = create_date;
    }

    public Integer getContract_status() {
        return this.contract_status;
    }

    public void setContract_status(final Integer contract_status) {
        this.contract_status = contract_status;
    }

    public Long getSettlement_date() {
        return this.settlement_date;
    }

    public void setSettlement_date(final Long settlement_date) {
        this.settlement_date = settlement_date;
    }
}
