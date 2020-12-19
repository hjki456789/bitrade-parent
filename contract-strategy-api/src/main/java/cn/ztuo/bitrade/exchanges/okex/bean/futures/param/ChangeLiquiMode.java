package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

public class ChangeLiquiMode {
    private String currency;
    private String liqui_mode;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getLiqui_mode() {
        return this.liqui_mode;
    }

    public void setLiqui_mode(final String liqui_mode) {
        this.liqui_mode = liqui_mode;
    }
}
