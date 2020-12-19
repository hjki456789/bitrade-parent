package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class ModifyFixedMargin {
    private String underlying;
    private String type;

    public String getUnderlying() {
        return this.underlying;
    }

    public void setUnderlying(final String underlying) {
        this.underlying = underlying;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
