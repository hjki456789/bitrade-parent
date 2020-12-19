package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiFundingTimeVO {
    private String instrument_id;
    private String funding_time;

    public ApiFundingTimeVO() {
    }

    public ApiFundingTimeVO(final String instrument_id, final String funding_time) {
        this.instrument_id = instrument_id;
        this.funding_time = funding_time;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getFunding_time() {
        return this.funding_time;
    }

    public void setFunding_time(final String funding_time) {
        this.funding_time = funding_time;
    }
}
