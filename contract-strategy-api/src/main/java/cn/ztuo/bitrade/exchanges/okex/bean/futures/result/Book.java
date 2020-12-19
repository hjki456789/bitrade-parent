package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

import com.alibaba.fastjson.*;

public class Book {
    JSONArray asks;
    JSONArray bids;
    String timestamp;

    public JSONArray getAsks() {
        return this.asks;
    }

    public void setAsks(final JSONArray asks) {
        this.asks = asks;
    }

    public JSONArray getBids() {
        return this.bids;
    }

    public void setBids(final JSONArray bids) {
        this.bids = bids;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Book{asks=" + this.asks + ", bids=" + this.bids + ", timestamp='" + this.timestamp + '\'' + '}';
    }
}
