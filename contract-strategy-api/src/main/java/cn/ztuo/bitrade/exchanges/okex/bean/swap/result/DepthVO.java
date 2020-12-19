package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

import java.util.*;

public class DepthVO {
    private List<Object[]> asks;
    private List<Object[]> bids;
    private String timestamp;

    public DepthVO(final List<Object[]> asks, final List<Object[]> bids, final String timeStamp) {
        this.asks = asks;
        this.bids = bids;
        this.timestamp = timeStamp;
    }

    public DepthVO() {
    }

    public List<Object[]> getAsks() {
        return this.asks;
    }

    public void setAsks(final List<Object[]> asks) {
        this.asks = asks;
    }

    public List<Object[]> getBids() {
        return this.bids;
    }

    public void setBids(final List<Object[]> bids) {
        this.bids = bids;
    }

    public String getTimeStamp() {
        return this.timestamp;
    }

    public void setTimeStamp(final String timeStamp) {
        this.timestamp = timeStamp;
    }
}
