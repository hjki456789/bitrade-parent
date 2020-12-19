package cn.ztuo.bitrade.exchanges.okex.bean.futures.param;

import java.util.*;

public class AmendDateParam {
    private List<AmendOrder> amend_data;

    public List<AmendOrder> getAmend_data() {
        return this.amend_data;
    }

    public void setAmend_data(final List<AmendOrder> amend_data) {
        this.amend_data = amend_data;
    }
}
