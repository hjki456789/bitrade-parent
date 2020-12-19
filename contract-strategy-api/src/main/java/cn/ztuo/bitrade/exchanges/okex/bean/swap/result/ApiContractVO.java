package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiContractVO {
    private String instrument_id;
    private String underlying_index;
    private String quote_currency;
    private String coin;
    private String contract_val;
    private String listing;
    private String delivery;
    private String size_increment;
    private String tick_size;

    public ApiContractVO(final String instrument_id, final String underlying_index, final String quote_currency, final String coin, final String contract_val, final String listing, final String delivery, final String size_increment, final String tick_size) {
        this.instrument_id = instrument_id;
        this.underlying_index = underlying_index;
        this.quote_currency = quote_currency;
        this.coin = coin;
        this.contract_val = contract_val;
        this.listing = listing;
        this.delivery = delivery;
        this.size_increment = size_increment;
        this.tick_size = tick_size;
    }

    public ApiContractVO() {
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getUnderlying_index() {
        return this.underlying_index;
    }

    public void setUnderlying_index(final String underlying_index) {
        this.underlying_index = underlying_index;
    }

    public String getQuote_currency() {
        return this.quote_currency;
    }

    public void setQuote_currency(final String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public String getCoin() {
        return this.coin;
    }

    public void setCoin(final String coin) {
        this.coin = coin;
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

    public String getSize_increment() {
        return this.size_increment;
    }

    public void setSize_increment(final String size_increment) {
        this.size_increment = size_increment;
    }

    public String getTick_size() {
        return this.tick_size;
    }

    public void setTick_size(final String tick_size) {
        this.tick_size = tick_size;
    }
}
