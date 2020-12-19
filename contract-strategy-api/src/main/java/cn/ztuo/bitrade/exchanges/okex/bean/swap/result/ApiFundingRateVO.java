package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiFundingRateVO {
    private String instrument_id;
    private String funding_rate;
    private String funding_rate_new;
    private String interest_rate;
    private String funding_time;

    public ApiFundingRateVO() {
    }

    public ApiFundingRateVO(final String instrument_id, final String funding_rate, final String funding_rate_new, final String interest_rate, final String funding_time) {
        this.instrument_id = instrument_id;
        this.funding_rate = funding_rate;
        this.funding_rate_new = funding_rate_new;
        this.interest_rate = interest_rate;
        this.funding_time = funding_time;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getFunding_rate() {
        return this.funding_rate;
    }

    public void setFunding_rate(final String funding_rate) {
        this.funding_rate = funding_rate;
    }

    public String getFunding_rate_new() {
        return this.funding_rate_new;
    }

    public void setFunding_rate_new(final String funding_rate_new) {
        this.funding_rate_new = funding_rate_new;
    }

    public String getInterest_rate() {
        return this.interest_rate;
    }

    public void setInterest_rate(final String interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getFunding_time() {
        return this.funding_time;
    }

    public void setFunding_time(final String funding_time) {
        this.funding_time = funding_time;
    }
}
