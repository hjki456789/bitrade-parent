package cn.ztuo.bitrade.exchanges.huobi.common.entity;

public class HuoBiCommonResponse<T> {
    private String status;
    private T data;
    private Long ts;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Long getTs() {
        return this.ts;
    }

    public void setTs(final Long ts) {
        this.ts = ts;
    }
}
