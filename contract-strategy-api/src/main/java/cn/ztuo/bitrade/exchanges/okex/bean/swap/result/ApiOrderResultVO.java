package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

import java.util.*;

public class ApiOrderResultVO {
    private List<ApiOrderResultVO.PerOrderResult> order_info;
    private String result;

    public ApiOrderResultVO() {
    }

    public ApiOrderResultVO(final List<ApiOrderResultVO.PerOrderResult> order_info, final String result) {
        this.order_info = order_info;
        this.result = result;
    }

    public List<ApiOrderResultVO.PerOrderResult> getOrder_info() {
        return this.order_info;
    }

    public void setOrder_info(final List<ApiOrderResultVO.PerOrderResult> order_info) {
        this.order_info = order_info;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
    }
}
