package cn.ztuo.bitrade.exchanges.okex.bean.futures;

public class CursorPageParams {
    protected int before;
    protected int after;
    protected int limit;

    public int getBefore() {
        return this.before;
    }

    public void setBefore(final int before) {
        this.before = before;
    }

    public int getAfter() {
        return this.after;
    }

    public void setAfter(final int after) {
        this.after = after;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }
}
