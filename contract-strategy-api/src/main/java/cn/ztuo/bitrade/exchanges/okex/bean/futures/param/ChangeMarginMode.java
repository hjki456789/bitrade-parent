package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class ChangeMarginMode {
    private String underlying;
    private String margin_mode;

    public String getUnderlying() {
        return this.underlying;
    }

    public void setUnderlying(final String underlying) {
        this.underlying = underlying;
    }

    public String getMargin_mode() {
        return this.margin_mode;
    }

    public void setMargin_mode(final String margin_mode) {
        this.margin_mode = margin_mode;
    }
}
