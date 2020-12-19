package cn.ztuo.bitrade.exchanges.okex.bean.option.param;

import java.util.*;

public class OrderDataParam {
    private List<OrderParam> orderdata;
    private String underlying;

    public List<OrderParam> getOrderdata() {
        return this.orderdata;
    }

    public void setOrderdata(final List<OrderParam> orderdata) {
        this.orderdata = orderdata;
    }

    public String getUnderlying() {
        return this.underlying;
    }

    public void setUnderlying(final String underlying) {
        this.underlying = underlying;
    }

    @Override
    public String toString() {
        return "OrderDataParam{orderdata=" + this.orderdata + '}';
    }
}
