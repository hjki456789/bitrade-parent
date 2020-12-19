package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

import java.util.*;

public class Book {
    private List<String[]> asks;
    private List<String[]> bids;
    private String timestamp;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public List<String[]> getAsks() {
        return this.asks;
    }

    public void setAsks(final List<String[]> asks) {
        this.asks = asks;
    }

    public List<String[]> getBids() {
        return this.bids;
    }

    public void setBids(final List<String[]> bids) {
        this.bids = bids;
    }
}
