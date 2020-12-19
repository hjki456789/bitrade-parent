package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class ServerTimeDto {
    private Long epoch;
    private String iso;

    public Long getEpoch() {
        return this.epoch;
    }

    public void setEpoch(final Long epoch) {
        this.epoch = epoch;
    }

    public String getIso() {
        return this.iso;
    }

    public void setIso(final String iso) {
        this.iso = iso;
    }
}
