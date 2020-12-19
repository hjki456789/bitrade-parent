package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiUserRiskVO {
    private String long_leverage;
    private String short_leverage;
    private String margin_mode;
    private String instrument_id;

    public ApiUserRiskVO() {
    }

    public ApiUserRiskVO(final String long_leverage, final String short_leverage, final String margin_mode, final String instrument_id) {
        this.long_leverage = long_leverage;
        this.short_leverage = short_leverage;
        this.margin_mode = margin_mode;
        this.instrument_id = instrument_id;
    }

    public String getLong_leverage() {
        return this.long_leverage;
    }

    public void setLong_leverage(final String long_leverage) {
        this.long_leverage = long_leverage;
    }

    public String getShort_leverage() {
        return this.short_leverage;
    }

    public void setShort_leverage(final String short_leverage) {
        this.short_leverage = short_leverage;
    }

    public String getMargin_mode() {
        return this.margin_mode;
    }

    public void setMargin_mode(final String margin_mode) {
        this.margin_mode = margin_mode;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }
}
