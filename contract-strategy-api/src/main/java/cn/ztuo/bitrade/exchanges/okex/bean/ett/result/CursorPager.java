package cn.ztuo.bitrade.exchanges.okex.bean.ett.result;

import java.util.*;

public class CursorPager<T> {
    private List<T> data;
    private String before;
    private String after;
    private int limit;

    public List<T> getData() {
        return this.data;
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public String getBefore() {
        return this.before;
    }

    public void setBefore(final String before) {
        this.before = before;
    }

    public String getAfter() {
        return this.after;
    }

    public void setAfter(final String after) {
        this.after = after;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }
}
