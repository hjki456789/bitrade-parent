package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class ServerTime {
    private String iso;
    private String epoch;

    public String getIso() {
        return this.iso;
    }

    public void setIso(final String iso) {
        this.iso = iso;
    }

    public String getEpoch() {
        return this.epoch;
    }

    public void setEpoch(final String epoch) {
        this.epoch = epoch;
    }
}
