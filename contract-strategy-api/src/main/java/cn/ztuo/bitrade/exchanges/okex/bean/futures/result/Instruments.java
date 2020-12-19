package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Instruments {
    private String instrument_id;
    private String underlying;
    private String base_currency;
    private String quote_currency;
    private String settlement_currency;
    private String contract_val;
    private String listing;
    private String delivery;
    private String tick_size;
    private String alias;
    private String is_inverse;
    private String contract_val_currency;
    private String trade_increment;

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getUnderlying() {
        return this.underlying;
    }

    public void setUnderlying(final String underlying) {
        this.underlying = underlying;
    }

    public String getBase_currency() {
        return this.base_currency;
    }

    public void setBase_currency(final String base_currency) {
        this.base_currency = base_currency;
    }

    public String getQuote_currency() {
        return this.quote_currency;
    }

    public void setQuote_currency(final String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public String getSettlement_currency() {
        return this.settlement_currency;
    }

    public void setSettlement_currency(final String settlement_currency) {
        this.settlement_currency = settlement_currency;
    }

    public String getContract_val() {
        return this.contract_val;
    }

    public void setContract_val(final String contract_val) {
        this.contract_val = contract_val;
    }

    public String getListing() {
        return this.listing;
    }

    public void setListing(final String listing) {
        this.listing = listing;
    }

    public String getDelivery() {
        return this.delivery;
    }

    public void setDelivery(final String delivery) {
        this.delivery = delivery;
    }

    public String getTick_size() {
        return this.tick_size;
    }

    public void setTick_size(final String tick_size) {
        this.tick_size = tick_size;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }

    public String getIs_inverse() {
        return this.is_inverse;
    }

    public void setIs_inverse(final String is_inverse) {
        this.is_inverse = is_inverse;
    }

    public String getContract_val_currency() {
        return this.contract_val_currency;
    }

    public void setContract_val_currency(final String contract_val_currency) {
        this.contract_val_currency = contract_val_currency;
    }

    public String getTrade_increment() {
        return this.trade_increment;
    }

    public void setTrade_increment(final String trade_increment) {
        this.trade_increment = trade_increment;
    }

    @Override
    public String toString() {
        return "Instruments{instrument_id='" + this.instrument_id + '\'' + ", underlying='" + this.underlying + '\'' + ", base_currency='" + this.base_currency + '\'' + ", quote_currency='" + this.quote_currency + '\'' + ", settlement_currency='" + this.settlement_currency + '\'' + ", contract_val='" + this.contract_val + '\'' + ", listing='" + this.listing + '\'' + ", delivery='" + this.delivery + '\'' + ", tick_size='" + this.tick_size + '\'' + ", alias='" + this.alias + '\'' + ", is_inverse='" + this.is_inverse + '\'' + ", contract_val_currency='" + this.contract_val_currency + '\'' + ", trade_increment='" + this.trade_increment + '\'' + '}';
    }
}
