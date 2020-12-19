package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiAccountVO {
    private String equity;
    private String total_avail_balance;
    private String margin;
    private String realized_pnl;
    private String unrealized_pnl;
    private String margin_ratio;
    private String instrument_id;
    private String margin_frozen;
    private String timestamp;
    private String margin_mode;
    private ApiAccountVO info;

    public ApiAccountVO() {
    }

    public ApiAccountVO(final String equity, final String total_avail_balance, final String margin, final String realized_pnl, final String unrealized_pnl, final String margin_ratio, final String instrument_id, final String margin_frozen, final String timestamp, final String margin_mode, final ApiAccountVO info) {
        this.equity = equity;
        this.total_avail_balance = total_avail_balance;
        this.margin = margin;
        this.realized_pnl = realized_pnl;
        this.unrealized_pnl = unrealized_pnl;
        this.margin_ratio = margin_ratio;
        this.instrument_id = instrument_id;
        this.margin_frozen = margin_frozen;
        this.timestamp = timestamp;
        this.margin_mode = margin_mode;
        this.info = info;
    }

    public String getEquity() {
        return this.equity;
    }

    public void setEquity(final String equity) {
        this.equity = equity;
    }

    public String getTotal_avail_balance() {
        return this.total_avail_balance;
    }

    public void setTotal_avail_balance(final String total_avail_balance) {
        this.total_avail_balance = total_avail_balance;
    }

    public String getMargin() {
        return this.margin;
    }

    public void setMargin(final String margin) {
        this.margin = margin;
    }

    public String getRealized_pnl() {
        return this.realized_pnl;
    }

    public void setRealized_pnl(final String realized_pnl) {
        this.realized_pnl = realized_pnl;
    }

    public String getUnrealized_pnl() {
        return this.unrealized_pnl;
    }

    public void setUnrealized_pnl(final String unrealized_pnl) {
        this.unrealized_pnl = unrealized_pnl;
    }

    public String getMargin_ratio() {
        return this.margin_ratio;
    }

    public void setMargin_ratio(final String margin_ratio) {
        this.margin_ratio = margin_ratio;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getMargin_frozen() {
        return this.margin_frozen;
    }

    public void setMargin_frozen(final String margin_frozen) {
        this.margin_frozen = margin_frozen;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMargin_mode() {
        return this.margin_mode;
    }

    public void setMargin_mode(final String margin_mode) {
        this.margin_mode = margin_mode;
    }

    public ApiAccountVO getInfo() {
        return this.info;
    }

    public void setInfo(final ApiAccountVO info) {
        this.info = info;
    }
}
