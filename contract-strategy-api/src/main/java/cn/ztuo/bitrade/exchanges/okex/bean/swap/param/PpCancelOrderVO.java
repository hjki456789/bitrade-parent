package cn.ztuo.bitrade.exchanges.okex.bean.swap.param;

import java.util.*;

public class PpCancelOrderVO {
    private List<String> ids;
    private List<String> clientOids;

    public List<String> getClientOids() {
        return this.clientOids;
    }

    public void setClientOids(final List<String> clientOids) {
        this.clientOids = clientOids;
    }

    public PpCancelOrderVO() {
        this.ids = new LinkedList<String>();
        this.clientOids = new ArrayList<String>();
    }

    public List<String> getIds() {
        return this.ids;
    }

    public void setIds(final List<String> ids) {
        this.ids = ids;
    }

    public PpCancelOrderVO(final List<String> ids) {
        this.ids = new LinkedList<String>();
        this.clientOids = new ArrayList<String>();
        this.ids = ids;
    }
}
