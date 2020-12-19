package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

import java.util.*;

public class ApiPositionsVO {
    private String margin_mode;
    private List<ApiPositionVO> holding;

    public ApiPositionsVO(final String margin_mode, final List<ApiPositionVO> holding) {
        this.margin_mode = margin_mode;
        this.holding = holding;
    }

    public ApiPositionsVO() {
    }

    public String getMargin_mode() {
        return this.margin_mode;
    }

    public void setMargin_mode(final String margin_mode) {
        this.margin_mode = margin_mode;
    }

    public List<ApiPositionVO> getHolding() {
        return this.holding;
    }

    public void setHolding(final List<ApiPositionVO> holding) {
        this.holding = holding;
    }
}
